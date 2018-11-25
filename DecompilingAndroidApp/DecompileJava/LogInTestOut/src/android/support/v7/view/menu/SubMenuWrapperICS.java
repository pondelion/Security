// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.*;

// Referenced classes of package android.support.v7.view.menu:
//            MenuWrapperICS

class SubMenuWrapperICS extends MenuWrapperICS
    implements SubMenu
{

    SubMenuWrapperICS(Context context, SupportSubMenu supportsubmenu)
    {
        super(context, supportsubmenu);
    }

    public void clearHeader()
    {
        getWrappedObject().clearHeader();
    }

    public MenuItem getItem()
    {
        return getMenuItemWrapper(getWrappedObject().getItem());
    }

    public SupportSubMenu getWrappedObject()
    {
        return (SupportSubMenu)mWrappedObject;
    }

    public volatile Object getWrappedObject()
    {
        return getWrappedObject();
    }

    public SubMenu setHeaderIcon(int i)
    {
        getWrappedObject().setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable)
    {
        getWrappedObject().setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i)
    {
        getWrappedObject().setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charsequence)
    {
        getWrappedObject().setHeaderTitle(charsequence);
        return this;
    }

    public SubMenu setHeaderView(View view)
    {
        getWrappedObject().setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i)
    {
        getWrappedObject().setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable)
    {
        getWrappedObject().setIcon(drawable);
        return this;
    }
}
