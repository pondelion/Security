// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.CancellationSignal;

class CancellationSignalCompatJellybean
{

    CancellationSignalCompatJellybean()
    {
    }

    public static void cancel(Object obj)
    {
        ((CancellationSignal)obj).cancel();
    }

    public static Object create()
    {
        return new CancellationSignal();
    }
}
