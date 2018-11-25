// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text.util;

import android.support.v4.util.PatternsCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.webkit.WebView;
import android.widget.TextView;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LinkifyCompat
{
    private static class LinkSpec
    {

        int end;
        URLSpan frameworkAddedSpan;
        int start;
        String url;

        LinkSpec()
        {
        }
    }

    public static interface LinkifyMask
        extends Annotation
    {
    }


    private LinkifyCompat()
    {
    }

    private static void addLinkMovementMethod(TextView textview)
    {
        android.text.method.MovementMethod movementmethod = textview.getMovementMethod();
        if((movementmethod == null || !(movementmethod instanceof LinkMovementMethod)) && textview.getLinksClickable())
            textview.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s)
    {
        addLinks(textview, pattern, s, null, null, null);
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s, android.text.util.Linkify.MatchFilter matchfilter, android.text.util.Linkify.TransformFilter transformfilter)
    {
        addLinks(textview, pattern, s, null, matchfilter, transformfilter);
    }

    public static final void addLinks(TextView textview, Pattern pattern, String s, String as[], android.text.util.Linkify.MatchFilter matchfilter, android.text.util.Linkify.TransformFilter transformfilter)
    {
        SpannableString spannablestring = SpannableString.valueOf(textview.getText());
        if(addLinks(((Spannable) (spannablestring)), pattern, s, as, matchfilter, transformfilter))
        {
            textview.setText(spannablestring);
            addLinkMovementMethod(textview);
        }
    }

    public static final boolean addLinks(Spannable spannable, int i)
    {
        if(i == 0)
            return false;
        URLSpan aurlspan[] = (URLSpan[])spannable.getSpans(0, spannable.length(), android/text/style/URLSpan);
        for(int j = aurlspan.length - 1; j >= 0; j--)
            spannable.removeSpan(aurlspan[j]);

        if((i & 4) != 0)
            Linkify.addLinks(spannable, 4);
        Object obj = new ArrayList();
        if((i & 1) != 0)
        {
            Pattern pattern = PatternsCompat.AUTOLINK_WEB_URL;
            android.text.util.Linkify.MatchFilter matchfilter = Linkify.sUrlMatchFilter;
            gatherLinks(((ArrayList) (obj)), spannable, pattern, new String[] {
                "http://", "https://", "rtsp://"
            }, matchfilter, null);
        }
        if((i & 2) != 0)
            gatherLinks(((ArrayList) (obj)), spannable, PatternsCompat.AUTOLINK_EMAIL_ADDRESS, new String[] {
                "mailto:"
            }, null, null);
        if((i & 8) != 0)
            gatherMapLinks(((ArrayList) (obj)), spannable);
        pruneOverlaps(((ArrayList) (obj)), spannable);
        if(((ArrayList) (obj)).size() == 0)
            return false;
        obj = ((ArrayList) (obj)).iterator();
        do
        {
            if(!((Iterator) (obj)).hasNext())
                break;
            LinkSpec linkspec = (LinkSpec)((Iterator) (obj)).next();
            if(linkspec.frameworkAddedSpan == null)
                applyLink(linkspec.url, linkspec.start, linkspec.end, spannable);
        } while(true);
        return true;
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s)
    {
        return addLinks(spannable, pattern, s, null, null, null);
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s, android.text.util.Linkify.MatchFilter matchfilter, android.text.util.Linkify.TransformFilter transformfilter)
    {
        return addLinks(spannable, pattern, s, null, matchfilter, transformfilter);
    }

    public static final boolean addLinks(Spannable spannable, Pattern pattern, String s, String as[], android.text.util.Linkify.MatchFilter matchfilter, android.text.util.Linkify.TransformFilter transformfilter)
    {
        String s1;
label0:
        {
            s1 = s;
            if(s == null)
                s1 = "";
            if(as != null)
            {
                s = as;
                if(as.length >= 1)
                    break label0;
            }
            s = EMPTY_STRING;
        }
        String as1[] = new String[s.length + 1];
        as1[0] = s1.toLowerCase(Locale.ROOT);
        int i = 0;
        while(i < s.length) 
        {
            as = s[i];
            if(as == null)
                as = "";
            else
                as = as.toLowerCase(Locale.ROOT);
            as1[i + 1] = as;
            i++;
        }
        boolean flag = false;
        pattern = pattern.matcher(spannable);
        do
        {
            if(!pattern.find())
                break;
            int j = pattern.start();
            int k = pattern.end();
            boolean flag1 = true;
            if(matchfilter != null)
                flag1 = matchfilter.acceptMatch(spannable, j, k);
            if(flag1)
            {
                applyLink(makeUrl(pattern.group(0), as1, pattern, transformfilter), j, k, spannable);
                flag = true;
            }
        } while(true);
        return flag;
    }

    public static final boolean addLinks(TextView textview, int i)
    {
        if(i != 0) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        Object obj;
        obj = textview.getText();
        if(!(obj instanceof Spannable))
            break; /* Loop/switch isn't completed */
        if(addLinks((Spannable)obj, i))
        {
            addLinkMovementMethod(textview);
            return true;
        }
        if(true) goto _L1; else goto _L3
_L3:
        obj = SpannableString.valueOf(((CharSequence) (obj)));
        if(addLinks(((Spannable) (obj)), i))
        {
            addLinkMovementMethod(textview);
            textview.setText(((CharSequence) (obj)));
            return true;
        }
        if(true) goto _L1; else goto _L4
_L4:
    }

    private static void applyLink(String s, int i, int j, Spannable spannable)
    {
        spannable.setSpan(new URLSpan(s), i, j, 33);
    }

    private static void gatherLinks(ArrayList arraylist, Spannable spannable, Pattern pattern, String as[], android.text.util.Linkify.MatchFilter matchfilter, android.text.util.Linkify.TransformFilter transformfilter)
    {
        pattern = pattern.matcher(spannable);
        do
        {
            if(!pattern.find())
                break;
            int i = pattern.start();
            int j = pattern.end();
            if(matchfilter == null || matchfilter.acceptMatch(spannable, i, j))
            {
                LinkSpec linkspec = new LinkSpec();
                linkspec.url = makeUrl(pattern.group(0), as, pattern, transformfilter);
                linkspec.start = i;
                linkspec.end = j;
                arraylist.add(linkspec);
            }
        } while(true);
    }

    private static final void gatherMapLinks(ArrayList arraylist, Spannable spannable)
    {
        int i;
        spannable = spannable.toString();
        i = 0;
_L2:
        int j;
        int k;
        LinkSpec linkspec;
        String s;
        try
        {
            s = WebView.findAddress(spannable);
        }
        // Misplaced declaration of an exception variable
        catch(ArrayList arraylist)
        {
            return;
        }
        if(s == null)
            break; /* Loop/switch isn't completed */
        j = spannable.indexOf(s);
        if(j < 0)
            return;
        linkspec = new LinkSpec();
        k = j + s.length();
        linkspec.start = i + j;
        linkspec.end = i + k;
        spannable = spannable.substring(k);
        i += k;
        s = URLEncoder.encode(s, "UTF-8");
        linkspec.url = (new StringBuilder()).append("geo:0,0?q=").append(s).toString();
        arraylist.add(linkspec);
        continue; /* Loop/switch isn't completed */
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static String makeUrl(String s, String as[], Matcher matcher, android.text.util.Linkify.TransformFilter transformfilter)
    {
        String s1 = s;
        if(transformfilter != null)
            s1 = transformfilter.transformUrl(matcher, s);
        boolean flag1 = false;
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                s = s1;
                if(i < as.length)
                {
                    if(!s1.regionMatches(true, 0, as[i], 0, as[i].length()))
                        break label0;
                    boolean flag2 = true;
                    flag = flag2;
                    s = s1;
                    if(!s1.regionMatches(false, 0, as[i], 0, as[i].length()))
                    {
                        s = (new StringBuilder()).append(as[i]).append(s1.substring(as[i].length())).toString();
                        flag = flag2;
                    }
                }
                matcher = s;
                if(!flag)
                {
                    matcher = s;
                    if(as.length > 0)
                        matcher = (new StringBuilder()).append(as[0]).append(s).toString();
                }
                return matcher;
            }
            i++;
        } while(true);
    }

    private static final void pruneOverlaps(ArrayList arraylist, Spannable spannable)
    {
        int k;
        int l;
        URLSpan aurlspan[] = (URLSpan[])spannable.getSpans(0, spannable.length(), android/text/style/URLSpan);
        for(int i = 0; i < aurlspan.length; i++)
        {
            LinkSpec linkspec = new LinkSpec();
            linkspec.frameworkAddedSpan = aurlspan[i];
            linkspec.start = spannable.getSpanStart(aurlspan[i]);
            linkspec.end = spannable.getSpanEnd(aurlspan[i]);
            arraylist.add(linkspec);
        }

        Collections.sort(arraylist, COMPARATOR);
        l = arraylist.size();
        k = 0;
_L7:
        int j;
        Object obj;
        LinkSpec linkspec1;
        if(k >= l - 1)
            break; /* Loop/switch isn't completed */
        obj = (LinkSpec)arraylist.get(k);
        linkspec1 = (LinkSpec)arraylist.get(k + 1);
        j = -1;
        if(((LinkSpec) (obj)).start > linkspec1.start || ((LinkSpec) (obj)).end <= linkspec1.start) goto _L2; else goto _L1
_L1:
        if(linkspec1.end > ((LinkSpec) (obj)).end) goto _L4; else goto _L3
_L3:
        j = k + 1;
_L5:
        if(j == -1)
            break; /* Loop/switch isn't completed */
        obj = ((LinkSpec)arraylist.get(j)).frameworkAddedSpan;
        if(obj != null)
            spannable.removeSpan(obj);
        arraylist.remove(j);
        l--;
        continue; /* Loop/switch isn't completed */
_L4:
        if(((LinkSpec) (obj)).end - ((LinkSpec) (obj)).start > linkspec1.end - linkspec1.start)
            j = k + 1;
        else
        if(((LinkSpec) (obj)).end - ((LinkSpec) (obj)).start < linkspec1.end - linkspec1.start)
            j = k;
        if(true) goto _L5; else goto _L2
_L2:
        k++;
        if(true) goto _L7; else goto _L6
_L6:
    }

    private static final Comparator COMPARATOR = new Comparator() {

        public final int compare(LinkSpec linkspec, LinkSpec linkspec1)
        {
            if(linkspec.start >= linkspec1.start)
            {
                if(linkspec.start > linkspec1.start)
                    return 1;
                if(linkspec.end < linkspec1.end)
                    return 1;
                if(linkspec.end <= linkspec1.end)
                    return 0;
            }
            return -1;
        }

        public volatile int compare(Object obj, Object obj1)
        {
            return compare((LinkSpec)obj, (LinkSpec)obj1);
        }

    }
;
    private static final String EMPTY_STRING[] = new String[0];

}
