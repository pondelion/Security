// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;

import android.content.Context;
import java.io.File;

class ContextCompatApi24
{

    ContextCompatApi24()
    {
    }

    public static Context createDeviceProtectedStorageContext(Context context)
    {
        return context.createDeviceProtectedStorageContext();
    }

    public static File getDataDir(Context context)
    {
        return context.getDataDir();
    }

    public static boolean isDeviceProtectedStorage(Context context)
    {
        return context.isDeviceProtectedStorage();
    }
}
