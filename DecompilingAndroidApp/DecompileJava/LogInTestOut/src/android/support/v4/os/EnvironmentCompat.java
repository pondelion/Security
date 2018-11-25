// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

// Referenced classes of package android.support.v4.os:
//            EnvironmentCompatKitKat

public final class EnvironmentCompat
{

    private EnvironmentCompat()
    {
    }

    public static String getStorageState(File file)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            return EnvironmentCompatKitKat.getStorageState(file);
        if(!file.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
            break MISSING_BLOCK_LABEL_61;
        file = Environment.getExternalStorageState();
        return file;
        file;
        Log.w("EnvironmentCompat", (new StringBuilder()).append("Failed to resolve canonical path: ").append(file).toString());
        return "unknown";
    }

    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";
}
