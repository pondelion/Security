// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;

class AppOpsManagerCompat23
{

    AppOpsManagerCompat23()
    {
    }

    public static int noteOp(Context context, String s, int i, String s1)
    {
        return ((AppOpsManager)context.getSystemService(android/app/AppOpsManager)).noteOp(s, i, s1);
    }

    public static int noteProxyOp(Context context, String s, String s1)
    {
        return ((AppOpsManager)context.getSystemService(android/app/AppOpsManager)).noteProxyOp(s, s1);
    }

    public static String permissionToOp(String s)
    {
        return AppOpsManager.permissionToOp(s);
    }
}
