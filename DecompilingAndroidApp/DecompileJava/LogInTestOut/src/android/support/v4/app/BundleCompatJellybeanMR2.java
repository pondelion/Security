// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Bundle;
import android.os.IBinder;

class BundleCompatJellybeanMR2
{

    BundleCompatJellybeanMR2()
    {
    }

    public static IBinder getBinder(Bundle bundle, String s)
    {
        return bundle.getBinder(s);
    }

    public static void putBinder(Bundle bundle, String s, IBinder ibinder)
    {
        bundle.putBinder(s, ibinder);
    }
}
