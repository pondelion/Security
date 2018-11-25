// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;

class ConfigurationHelperJellybeanMr1
{

    ConfigurationHelperJellybeanMr1()
    {
    }

    static int getDensityDpi(Resources resources)
    {
        return resources.getConfiguration().densityDpi;
    }
}
