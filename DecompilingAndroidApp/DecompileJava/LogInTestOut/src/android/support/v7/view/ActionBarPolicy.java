// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.DisplayMetrics;
import android.view.ViewConfiguration;

public class ActionBarPolicy
{

    private ActionBarPolicy(Context context)
    {
        mContext = context;
    }

    public static ActionBarPolicy get(Context context)
    {
        return new ActionBarPolicy(context);
    }

    public boolean enableHomeButtonByDefault()
    {
        return mContext.getApplicationInfo().targetSdkVersion < 14;
    }

    public int getEmbeddedMenuWidthLimit()
    {
        return mContext.getResources().getDisplayMetrics().widthPixels / 2;
    }

    public int getMaxActionButtons()
    {
        Resources resources = mContext.getResources();
        int i = ConfigurationHelper.getScreenWidthDp(resources);
        int j = ConfigurationHelper.getScreenHeightDp(resources);
        if(ConfigurationHelper.getSmallestScreenWidthDp(resources) > 600 || i > 600 || i > 960 && j > 720 || i > 720 && j > 960)
            return 5;
        if(i >= 500 || i > 640 && j > 480 || i > 480 && j > 640)
            return 4;
        return i < 360 ? 2 : 3;
    }

    public int getStackedTabMaxWidth()
    {
        return mContext.getResources().getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_bar_stacked_tab_max_width);
    }

    public int getTabContainerHeight()
    {
        TypedArray typedarray = mContext.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.ActionBar, android.support.v7.appcompat.R.attr.actionBarStyle, 0);
        int j = typedarray.getLayoutDimension(android.support.v7.appcompat.R.styleable.ActionBar_height, 0);
        Resources resources = mContext.getResources();
        int i = j;
        if(!hasEmbeddedTabs())
            i = Math.min(j, resources.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_action_bar_stacked_max_height));
        typedarray.recycle();
        return i;
    }

    public boolean hasEmbeddedTabs()
    {
        return mContext.getResources().getBoolean(android.support.v7.appcompat.R.bool.abc_action_bar_embed_tabs);
    }

    public boolean showsOverflowMenuButton()
    {
        while(android.os.Build.VERSION.SDK_INT >= 19 || !ViewConfigurationCompat.hasPermanentMenuKey(ViewConfiguration.get(mContext))) 
            return true;
        return false;
    }

    private Context mContext;
}
