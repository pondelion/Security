// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityNodeInfo;

class AccessibilityNodeInfoCompatApi24
{

    AccessibilityNodeInfoCompatApi24()
    {
    }

    public static Object getActionSetProgress()
    {
        return android.view.accessibility.AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS;
    }

    public static int getDrawingOrder(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).getDrawingOrder();
    }

    public static boolean isImportantForAccessibility(Object obj)
    {
        return ((AccessibilityNodeInfo)obj).isImportantForAccessibility();
    }

    public static void setDrawingOrder(Object obj, int i)
    {
        ((AccessibilityNodeInfo)obj).setDrawingOrder(i);
    }

    public static void setImportantForAccessibility(Object obj, boolean flag)
    {
        ((AccessibilityNodeInfo)obj).setImportantForAccessibility(flag);
    }
}
