// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.content.res;

import android.content.res.*;
import android.graphics.Color;
import android.support.v4.graphics.ColorUtils;
import android.util.*;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v7.content.res:
//            GrowingArrayUtils

final class AppCompatColorStateListInflater
{

    private AppCompatColorStateListInflater()
    {
    }

    public static ColorStateList createFromXml(Resources resources, XmlPullParser xmlpullparser, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        AttributeSet attributeset = Xml.asAttributeSet(xmlpullparser);
        int i;
        do
            i = xmlpullparser.next();
        while(i != 2 && i != 1);
        if(i != 2)
            throw new XmlPullParserException("No start tag found");
        else
            return createFromXmlInner(resources, xmlpullparser, attributeset, theme);
    }

    private static ColorStateList createFromXmlInner(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        String s = xmlpullparser.getName();
        if(!s.equals("selector"))
            throw new XmlPullParserException((new StringBuilder()).append(xmlpullparser.getPositionDescription()).append(": invalid color state list tag ").append(s).toString());
        else
            return inflate(resources, xmlpullparser, attributeset, theme);
    }

    private static ColorStateList inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        int j1 = xmlpullparser.getDepth() + 1;
        int ai[][] = new int[20][];
        int ai1[] = new int[ai.length];
        int j = 0;
        do
        {
            int i = xmlpullparser.next();
            if(i == 1)
                break;
            int k = xmlpullparser.getDepth();
            if(k < j1 && i == 3)
                break;
            if(i != 2 || k > j1 || !xmlpullparser.getName().equals("item"))
                continue;
            int ai2[] = obtainAttributes(resources, theme, attributeset, android.support.v7.appcompat.R.styleable.ColorStateListItem);
            int k1 = ai2.getColor(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_color, -65281);
            float f = 1.0F;
            int l1;
            if(ai2.hasValue(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_alpha))
                f = ai2.getFloat(android.support.v7.appcompat.R.styleable.ColorStateListItem_android_alpha, 1.0F);
            else
            if(ai2.hasValue(android.support.v7.appcompat.R.styleable.ColorStateListItem_alpha))
                f = ai2.getFloat(android.support.v7.appcompat.R.styleable.ColorStateListItem_alpha, 1.0F);
            ai2.recycle();
            l1 = attributeset.getAttributeCount();
            ai2 = new int[l1];
            k = 0;
            i = 0;
            while(k < l1) 
            {
                int l = attributeset.getAttributeNameResource(k);
                if(l != 0x10101a5 && l != 0x101031f && l != android.support.v7.appcompat.R.attr.alpha)
                {
                    int i1 = i + 1;
                    if(!attributeset.getAttributeBooleanValue(k, false))
                        l = -l;
                    ai2[i] = l;
                    i = i1;
                }
                k++;
            }
            ai2 = StateSet.trimStateSet(ai2, i);
            i = modulateColorAlpha(k1, f);
            if(j != 0)
                if(ai2.length != 0);
            ai1 = GrowingArrayUtils.append(ai1, j, i);
            ai = (int[][])GrowingArrayUtils.append(ai, j, ai2);
            j++;
        } while(true);
        resources = new int[j];
        xmlpullparser = new int[j][];
        System.arraycopy(ai1, 0, resources, 0, j);
        System.arraycopy(ai, 0, xmlpullparser, 0, j);
        return new ColorStateList(xmlpullparser, resources);
    }

    private static int modulateColorAlpha(int i, float f)
    {
        return ColorUtils.setAlphaComponent(i, Math.round((float)Color.alpha(i) * f));
    }

    private static TypedArray obtainAttributes(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, int ai[])
    {
        if(theme == null)
            return resources.obtainAttributes(attributeset, ai);
        else
            return theme.obtainStyledAttributes(attributeset, ai, 0, 0);
    }

    private static final int DEFAULT_COLOR = 0xffff0000;
}
