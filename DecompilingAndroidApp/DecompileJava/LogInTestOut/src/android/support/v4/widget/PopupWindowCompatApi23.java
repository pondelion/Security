// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.widget.PopupWindow;

class PopupWindowCompatApi23
{

    PopupWindowCompatApi23()
    {
    }

    static boolean getOverlapAnchor(PopupWindow popupwindow)
    {
        return popupwindow.getOverlapAnchor();
    }

    static int getWindowLayoutType(PopupWindow popupwindow)
    {
        return popupwindow.getWindowLayoutType();
    }

    static void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
        popupwindow.setOverlapAnchor(flag);
    }

    static void setWindowLayoutType(PopupWindow popupwindow, int i)
    {
        popupwindow.setWindowLayoutType(i);
    }
}
