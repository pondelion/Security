// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            ICUCompat, TextUtilsCompatJellybeanMr1

public final class TextUtilsCompat
{
    private static class TextUtilsCompatImpl
    {

        private static int getLayoutDirectionFromFirstChar(Locale locale)
        {
            switch(Character.getDirectionality(locale.getDisplayName(locale).charAt(0)))
            {
            default:
                return 0;

            case 1: // '\001'
            case 2: // '\002'
                return 1;
            }
        }

        public int getLayoutDirectionFromLocale(Locale locale)
        {
            if(locale != null && !locale.equals(TextUtilsCompat.ROOT))
            {
                String s = ICUCompat.maximizeAndGetScript(locale);
                if(s == null)
                    return getLayoutDirectionFromFirstChar(locale);
                if(s.equalsIgnoreCase(TextUtilsCompat.ARAB_SCRIPT_SUBTAG) || s.equalsIgnoreCase(TextUtilsCompat.HEBR_SCRIPT_SUBTAG))
                    return 1;
            }
            return 0;
        }

        public String htmlEncode(String s)
        {
            int i;
            StringBuilder stringbuilder;
            stringbuilder = new StringBuilder();
            i = 0;
_L8:
            char c;
            if(i >= s.length())
                break MISSING_BLOCK_LABEL_145;
            c = s.charAt(i);
            c;
            JVM INSTR lookupswitch 5: default 76
        //                       34: 134
        //                       38: 112
        //                       39: 123
        //                       60: 90
        //                       62: 101;
               goto _L1 _L2 _L3 _L4 _L5 _L6
_L2:
            break MISSING_BLOCK_LABEL_134;
_L5:
            break; /* Loop/switch isn't completed */
_L1:
            stringbuilder.append(c);
_L9:
            i++;
            if(true) goto _L8; else goto _L7
_L7:
            stringbuilder.append("&lt;");
              goto _L9
_L6:
            stringbuilder.append("&gt;");
              goto _L9
_L3:
            stringbuilder.append("&amp;");
              goto _L9
_L4:
            stringbuilder.append("&#39;");
              goto _L9
            stringbuilder.append("&quot;");
              goto _L9
            return stringbuilder.toString();
        }

        TextUtilsCompatImpl()
        {
        }
    }

    private static class TextUtilsCompatJellybeanMr1Impl extends TextUtilsCompatImpl
    {

        public int getLayoutDirectionFromLocale(Locale locale)
        {
            return TextUtilsCompatJellybeanMr1.getLayoutDirectionFromLocale(locale);
        }

        public String htmlEncode(String s)
        {
            return TextUtilsCompatJellybeanMr1.htmlEncode(s);
        }

        TextUtilsCompatJellybeanMr1Impl()
        {
        }
    }


    private TextUtilsCompat()
    {
    }

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        return IMPL.getLayoutDirectionFromLocale(locale);
    }

    public static String htmlEncode(String s)
    {
        return IMPL.htmlEncode(s);
    }

    static String ARAB_SCRIPT_SUBTAG = "Arab";
    static String HEBR_SCRIPT_SUBTAG = "Hebr";
    private static final TextUtilsCompatImpl IMPL;
    public static final Locale ROOT = new Locale("", "");

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 17)
            IMPL = new TextUtilsCompatJellybeanMr1Impl();
        else
            IMPL = new TextUtilsCompatImpl();
    }
}
