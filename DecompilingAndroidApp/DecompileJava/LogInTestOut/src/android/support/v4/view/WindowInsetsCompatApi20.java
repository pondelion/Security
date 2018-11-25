// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.WindowInsets;

class WindowInsetsCompatApi20
{

    WindowInsetsCompatApi20()
    {
    }

    public static Object consumeSystemWindowInsets(Object obj)
    {
        return ((WindowInsets)obj).consumeSystemWindowInsets();
    }

    public static Object getSourceWindowInsets(Object obj)
    {
        return new WindowInsets((WindowInsets)obj);
    }

    public static int getSystemWindowInsetBottom(Object obj)
    {
        return ((WindowInsets)obj).getSystemWindowInsetBottom();
    }

    public static int getSystemWindowInsetLeft(Object obj)
    {
        return ((WindowInsets)obj).getSystemWindowInsetLeft();
    }

    public static int getSystemWindowInsetRight(Object obj)
    {
        return ((WindowInsets)obj).getSystemWindowInsetRight();
    }

    public static int getSystemWindowInsetTop(Object obj)
    {
        return ((WindowInsets)obj).getSystemWindowInsetTop();
    }

    public static boolean hasInsets(Object obj)
    {
        return ((WindowInsets)obj).hasInsets();
    }

    public static boolean hasSystemWindowInsets(Object obj)
    {
        return ((WindowInsets)obj).hasSystemWindowInsets();
    }

    public static boolean isRound(Object obj)
    {
        return ((WindowInsets)obj).isRound();
    }

    public static Object replaceSystemWindowInsets(Object obj, int i, int j, int k, int l)
    {
        return ((WindowInsets)obj).replaceSystemWindowInsets(i, j, k, l);
    }
}
