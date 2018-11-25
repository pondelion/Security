// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Service;

class ServiceCompatApi24
{

    ServiceCompatApi24()
    {
    }

    public static void stopForeground(Service service, int i)
    {
        service.stopForeground(i);
    }
}
