// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Service;
import android.support.v4.os.BuildCompat;
import java.lang.annotation.Annotation;

// Referenced classes of package android.support.v4.app:
//            ServiceCompatApi24

public final class ServiceCompat
{
    static class Api24ServiceCompatImpl
        implements ServiceCompatImpl
    {

        public void stopForeground(Service service, int i)
        {
            ServiceCompatApi24.stopForeground(service, i);
        }

        Api24ServiceCompatImpl()
        {
        }
    }

    static class BaseServiceCompatImpl
        implements ServiceCompatImpl
    {

        public void stopForeground(Service service, int i)
        {
            boolean flag;
            if((i & 1) != 0)
                flag = true;
            else
                flag = false;
            service.stopForeground(flag);
        }

        BaseServiceCompatImpl()
        {
        }
    }

    static interface ServiceCompatImpl
    {

        public abstract void stopForeground(Service service, int i);
    }

    public static interface StopForegroundFlags
        extends Annotation
    {
    }


    private ServiceCompat()
    {
    }

    public static void stopForeground(Service service, int i)
    {
        IMPL.stopForeground(service, i);
    }

    static final ServiceCompatImpl IMPL;
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    static 
    {
        if(BuildCompat.isAtLeastN())
            IMPL = new Api24ServiceCompatImpl();
        else
            IMPL = new BaseServiceCompatImpl();
    }
}
