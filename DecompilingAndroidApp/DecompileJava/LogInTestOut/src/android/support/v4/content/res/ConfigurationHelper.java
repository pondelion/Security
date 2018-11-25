// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.Resources;

// Referenced classes of package android.support.v4.content.res:
//            ConfigurationHelperGingerbread, ConfigurationHelperHoneycombMr2, ConfigurationHelperJellybeanMr1

public final class ConfigurationHelper
{
    private static interface ConfigurationHelperImpl
    {

        public abstract int getDensityDpi(Resources resources);

        public abstract int getScreenHeightDp(Resources resources);

        public abstract int getScreenWidthDp(Resources resources);

        public abstract int getSmallestScreenWidthDp(Resources resources);
    }

    private static class GingerbreadImpl
        implements ConfigurationHelperImpl
    {

        public int getDensityDpi(Resources resources)
        {
            return ConfigurationHelperGingerbread.getDensityDpi(resources);
        }

        public int getScreenHeightDp(Resources resources)
        {
            return ConfigurationHelperGingerbread.getScreenHeightDp(resources);
        }

        public int getScreenWidthDp(Resources resources)
        {
            return ConfigurationHelperGingerbread.getScreenWidthDp(resources);
        }

        public int getSmallestScreenWidthDp(Resources resources)
        {
            return ConfigurationHelperGingerbread.getSmallestScreenWidthDp(resources);
        }

        GingerbreadImpl()
        {
        }
    }

    private static class HoneycombMr2Impl extends GingerbreadImpl
    {

        public int getScreenHeightDp(Resources resources)
        {
            return ConfigurationHelperHoneycombMr2.getScreenHeightDp(resources);
        }

        public int getScreenWidthDp(Resources resources)
        {
            return ConfigurationHelperHoneycombMr2.getScreenWidthDp(resources);
        }

        public int getSmallestScreenWidthDp(Resources resources)
        {
            return ConfigurationHelperHoneycombMr2.getSmallestScreenWidthDp(resources);
        }

        HoneycombMr2Impl()
        {
        }
    }

    private static class JellybeanMr1Impl extends HoneycombMr2Impl
    {

        public int getDensityDpi(Resources resources)
        {
            return ConfigurationHelperJellybeanMr1.getDensityDpi(resources);
        }

        JellybeanMr1Impl()
        {
        }
    }


    private ConfigurationHelper()
    {
    }

    public static int getDensityDpi(Resources resources)
    {
        return IMPL.getDensityDpi(resources);
    }

    public static int getScreenHeightDp(Resources resources)
    {
        return IMPL.getScreenHeightDp(resources);
    }

    public static int getScreenWidthDp(Resources resources)
    {
        return IMPL.getScreenWidthDp(resources);
    }

    public static int getSmallestScreenWidthDp(Resources resources)
    {
        return IMPL.getSmallestScreenWidthDp(resources);
    }

    private static final ConfigurationHelperImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 17)
            IMPL = new JellybeanMr1Impl();
        else
        if(i >= 13)
            IMPL = new HoneycombMr2Impl();
        else
            IMPL = new GingerbreadImpl();
    }
}
