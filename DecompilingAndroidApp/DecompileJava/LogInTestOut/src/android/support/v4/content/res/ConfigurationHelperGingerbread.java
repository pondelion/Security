// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.util.DisplayMetrics;

class ConfigurationHelperGingerbread
{

    ConfigurationHelperGingerbread()
    {
    }

    static int getDensityDpi(Resources resources)
    {
        return resources.getDisplayMetrics().densityDpi;
    }

    static int getScreenHeightDp(Resources resources)
    {
        resources = resources.getDisplayMetrics();
        return (int)((float)((DisplayMetrics) (resources)).heightPixels / ((DisplayMetrics) (resources)).density);
    }

    static int getScreenWidthDp(Resources resources)
    {
        resources = resources.getDisplayMetrics();
        return (int)((float)((DisplayMetrics) (resources)).widthPixels / ((DisplayMetrics) (resources)).density);
    }

    static int getSmallestScreenWidthDp(Resources resources)
    {
        return Math.min(getScreenWidthDp(resources), getScreenHeightDp(resources));
    }
}
