// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatApi23
{

    AccessibilityNodeInfoCompatApi23()
    {
    }

    public static Object getActionContextClick()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK;
    }

    public static Object getActionScrollDown()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN;
    }

    public static Object getActionScrollLeft()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT;
    }

    public static Object getActionScrollRight()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT;
    }

    public static Object getActionScrollToPosition()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION;
    }

    public static Object getActionScrollUp()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP;
    }

    public static Object getActionShowOnScreen()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN;
    }

    public static boolean isContextClickable(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).isContextClickable();
    }

    public static void setContextClickable(Object obj, boolean flag)
    {
        ((AccessibilityNodeInfo)obj).setContextClickable(flag);
    }
}
