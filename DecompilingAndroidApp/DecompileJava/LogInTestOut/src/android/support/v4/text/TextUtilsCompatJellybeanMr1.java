// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import android.text.TextUtils;
import java.util.Locale;

class TextUtilsCompatJellybeanMr1
{

    TextUtilsCompatJellybeanMr1()
    {
    }

    public static int getLayoutDirectionFromLocale(Locale locale)
    {
        return TextUtils.getLayoutDirectionFromLocale(locale);
    }

    public static String htmlEncode(String s)
    {
        return TextUtils.htmlEncode(s);
    }
}
