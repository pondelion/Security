// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.content.res.TypedArray;
import org.xmlpull.v1.XmlPullParser;

class TypedArrayUtils
{

    TypedArrayUtils()
    {
    }

    public static boolean getNamedBoolean(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, boolean flag)
    {
        if(!hasAttribute(xmlpullparser, s))
            return flag;
        else
            return typedarray.getBoolean(i, flag);
    }

    public static int getNamedColor(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, int j)
    {
        if(!hasAttribute(xmlpullparser, s))
            return j;
        else
            return typedarray.getColor(i, j);
    }

    public static float getNamedFloat(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, float f)
    {
        if(!hasAttribute(xmlpullparser, s))
            return f;
        else
            return typedarray.getFloat(i, f);
    }

    public static int getNamedInt(TypedArray typedarray, XmlPullParser xmlpullparser, String s, int i, int j)
    {
        if(!hasAttribute(xmlpullparser, s))
            return j;
        else
            return typedarray.getInt(i, j);
    }

    public static boolean hasAttribute(XmlPullParser xmlpullparser, String s)
    {
        return xmlpullparser.getAttributeValue("http://schemas.android.com/apk/res/android", s) != null;
    }

    private static final String NAMESPACE = "http://schemas.android.com/apk/res/android";
}
