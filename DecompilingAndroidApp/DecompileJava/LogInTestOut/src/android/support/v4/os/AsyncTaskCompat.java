// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.AsyncTask;

// Referenced classes of package android.support.v4.os:
//            AsyncTaskCompatHoneycomb

public final class AsyncTaskCompat
{

    private AsyncTaskCompat()
    {
    }

    public static transient AsyncTask executeParallel(AsyncTask asynctask, Object aobj[])
    {
        if(asynctask == null)
            throw new IllegalArgumentException("task can not be null");
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            AsyncTaskCompatHoneycomb.executeParallel(asynctask, aobj);
            return asynctask;
        } else
        {
            asynctask.execute(aobj);
            return asynctask;
        }
    }
}
