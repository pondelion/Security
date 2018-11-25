// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.os.AsyncTask;
import java.util.concurrent.Executor;

class ExecutorCompatHoneycomb
{

    ExecutorCompatHoneycomb()
    {
    }

    public static Executor getParallelExecutor()
    {
        return AsyncTask.THREAD_POOL_EXECUTOR;
    }
}
