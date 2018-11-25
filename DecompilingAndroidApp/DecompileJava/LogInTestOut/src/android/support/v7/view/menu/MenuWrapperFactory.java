// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.support.v4.internal.view.*;
import android.view.*;

// Referenced classes of package android.support.v7.view.menu:
//            MenuWrapperICS, MenuItemWrapperJB, MenuItemWrapperICS, SubMenuWrapperICS

public final class MenuWrapperFactory
{

    private MenuWrapperFactory()
    {
    }

    public static Menu wrapSupportMenu(Context context, SupportMenu supportmenu)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new MenuWrapperICS(context, supportmenu);
        else
            throw new UnsupportedOperationException();
    }

    public static MenuItem wrapSupportMenuItem(Context context, SupportMenuItem supportmenuitem)
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return new MenuItemWrapperJB(context, supportmenuitem);
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new MenuItemWrapperICS(context, supportmenuitem);
        else
            throw new UnsupportedOperationException();
    }

    public static SubMenu wrapSupportSubMenu(Context context, SupportSubMenu supportsubmenu)
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            return new SubMenuWrapperICS(context, supportsubmenu);
        else
            throw new UnsupportedOperationException();
    }
}
