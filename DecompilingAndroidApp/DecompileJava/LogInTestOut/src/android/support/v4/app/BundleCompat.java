// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.os.Bundle;
import android.os.IBinder;

// Referenced classes of package android.support.v4.app:
//            BundleCompatJellybeanMR2, BundleCompatGingerbread

public final class BundleCompat
{

    private BundleCompat()
    {
    }

    public static IBinder getBinder(Bundle bundle, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
            return BundleCompatJellybeanMR2.getBinder(bundle, s);
        else
            return BundleCompatGingerbread.getBinder(bundle, s);
    }

    public static void putBinder(Bundle bundle, String s, IBinder ibinder)
    {
        if(android.os.Build.VERSION.SDK_INT >= 18)
        {
            BundleCompatJellybeanMR2.putBinder(bundle, s, ibinder);
            return;
        } else
        {
            BundleCompatGingerbread.putBinder(bundle, s, ibinder);
            return;
        }
    }
}
