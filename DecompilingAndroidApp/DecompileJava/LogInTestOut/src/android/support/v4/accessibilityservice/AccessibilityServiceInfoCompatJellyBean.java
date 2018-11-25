// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.accessibilityservice;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.pm.PackageManager;

class AccessibilityServiceInfoCompatJellyBean
{

    AccessibilityServiceInfoCompatJellyBean()
    {
    }

    public static String loadDescription(AccessibilityServiceInfo accessibilityserviceinfo, PackageManager packagemanager)
    {
        return accessibilityserviceinfo.loadDescription(packagemanager);
    }
}
