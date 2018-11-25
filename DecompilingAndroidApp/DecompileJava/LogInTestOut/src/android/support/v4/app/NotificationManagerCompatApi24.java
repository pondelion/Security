// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.NotificationManager;

class NotificationManagerCompatApi24
{

    NotificationManagerCompatApi24()
    {
    }

    public static boolean areNotificationsEnabled(NotificationManager notificationmanager)
    {
        return notificationmanager.areNotificationsEnabled();
    }

    public static int getImportance(NotificationManager notificationmanager)
    {
        return notificationmanager.getImportance();
    }
}
