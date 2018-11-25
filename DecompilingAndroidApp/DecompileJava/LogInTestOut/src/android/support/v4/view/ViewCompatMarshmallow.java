// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.View;

class ViewCompatMarshmallow
{

    ViewCompatMarshmallow()
    {
    }

    public static int getScrollIndicators(View view)
    {
        return view.getScrollIndicators();
    }

    static void offsetLeftAndRight(View view, int i)
    {
        view.offsetLeftAndRight(i);
    }

    static void offsetTopAndBottom(View view, int i)
    {
        view.offsetTopAndBottom(i);
    }

    public static void setScrollIndicators(View view, int i)
    {
        view.setScrollIndicators(i);
    }

    public static void setScrollIndicators(View view, int i, int j)
    {
        view.setScrollIndicators(i, j);
    }
}
