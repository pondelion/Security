// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;
import android.view.View;

class ViewCompatJellybeanMr2
{

    ViewCompatJellybeanMr2()
    {
    }

    public static Rect getClipBounds(View view)
    {
        return view.getClipBounds();
    }

    public static boolean isInLayout(View view)
    {
        return view.isInLayout();
    }

    public static void setClipBounds(View view, Rect rect)
    {
        view.setClipBounds(rect);
    }
}
