// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Configuration;
import android.content.res.Resources;

class ConfigurationHelperHoneycombMr2
{

    ConfigurationHelperHoneycombMr2()
    {
    }

    static int getScreenHeightDp(Resources resources)
    {
        return resources.getConfiguration().screenHeightDp;
    }

    static int getScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().screenWidthDp;
    }

    static int getSmallestScreenWidthDp(Resources resources)
    {
        return resources.getConfiguration().smallestScreenWidthDp;
    }
}
