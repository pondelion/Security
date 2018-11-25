// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityWindowInfo;

class AccessibilityWindowInfoCompatApi24
{

    AccessibilityWindowInfoCompatApi24()
    {
    }

    public static Object getAnchor(Object obj)
    {
        return ((AccessibilityWindowInfo)obj).getAnchor();
    }

    public static CharSequence getTitle(Object obj)
    {
        return ((AccessibilityWindowInfo)obj).getTitle();
    }
}
