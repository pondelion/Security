// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import java.lang.reflect.*;

class NotificationManagerCompatKitKat
{

    NotificationManagerCompatKitKat()
    {
    }

    public static boolean areNotificationsEnabled(Context context)
    {
        int i;
        AppOpsManager appopsmanager;
        appopsmanager = (AppOpsManager)context.getSystemService("appops");
        ApplicationInfo applicationinfo = context.getApplicationInfo();
        context = context.getApplicationContext().getPackageName();
        i = applicationinfo.uid;
        Class class1 = Class.forName(android/app/AppOpsManager.getName());
        i = ((Integer)class1.getMethod("checkOpNoThrow", new Class[] {
            Integer.TYPE, Integer.TYPE, java/lang/String
        }).invoke(appopsmanager, new Object[] {
            Integer.valueOf(((Integer)class1.getDeclaredField("OP_POST_NOTIFICATION").get(java/lang/Integer)).intValue()), Integer.valueOf(i), context
        })).intValue();
        return i == 0;
        context;
_L2:
        return true;
        context;
        continue; /* Loop/switch isn't completed */
        context;
        continue; /* Loop/switch isn't completed */
        context;
        continue; /* Loop/switch isn't completed */
        context;
        continue; /* Loop/switch isn't completed */
        context;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
}
