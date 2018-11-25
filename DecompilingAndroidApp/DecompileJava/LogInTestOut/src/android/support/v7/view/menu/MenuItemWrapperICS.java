// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportMenuItem;
import android.util.Log;
import android.view.ActionProvider;
import android.view.CollapsibleActionView;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.FrameLayout;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.view.menu:
//            BaseMenuWrapper, BaseWrapper

public class MenuItemWrapperICS extends BaseMenuWrapper
    implements MenuItem
{
    class ActionProviderWrapper extends android.support.v4.view.ActionProvider
    {

        public boolean hasSubMenu()
        {
            return mInner.hasSubMenu();
        }

        public View onCreateActionView()
        {
            return mInner.onCreateActionView();
        }

        public boolean onPerformDefaultAction()
        {
            return mInner.onPerformDefaultAction();
        }

        public void onPrepareSubMenu(SubMenu submenu)
        {
            mInner.onPrepareSubMenu(getSubMenuWrapper(submenu));
        }

        final ActionProvider mInner;
        final MenuItemWrapperICS this$0;

        public ActionProviderWrapper(Context context, ActionProvider actionprovider)
        {
            this$0 = MenuItemWrapperICS.this;
            super(context);
            mInner = actionprovider;
        }
    }

    static class CollapsibleActionViewWrapper extends FrameLayout
        implements android.support.v7.view.CollapsibleActionView
    {

        View getWrappedView()
        {
            return (View)mWrappedView;
        }

        public void onActionViewCollapsed()
        {
            mWrappedView.onActionViewCollapsed();
        }

        public void onActionViewExpanded()
        {
            mWrappedView.onActionViewExpanded();
        }

        final CollapsibleActionView mWrappedView;

        CollapsibleActionViewWrapper(View view)
        {
            super(view.getContext());
            mWrappedView = (CollapsibleActionView)view;
            addView(view);
        }
    }

    private class OnActionExpandListenerWrapper extends BaseWrapper
        implements android.support.v4.view.MenuItemCompat.OnActionExpandListener
    {

        public boolean onMenuItemActionCollapse(MenuItem menuitem)
        {
            return ((android.view.MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionCollapse(getMenuItemWrapper(menuitem));
        }

        public boolean onMenuItemActionExpand(MenuItem menuitem)
        {
            return ((android.view.MenuItem.OnActionExpandListener)mWrappedObject).onMenuItemActionExpand(getMenuItemWrapper(menuitem));
        }

        final MenuItemWrapperICS this$0;

        OnActionExpandListenerWrapper(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
        {
            this$0 = MenuItemWrapperICS.this;
            super(onactionexpandlistener);
        }
    }

    private class OnMenuItemClickListenerWrapper extends BaseWrapper
        implements android.view.MenuItem.OnMenuItemClickListener
    {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            return ((android.view.MenuItem.OnMenuItemClickListener)mWrappedObject).onMenuItemClick(getMenuItemWrapper(menuitem));
        }

        final MenuItemWrapperICS this$0;

        OnMenuItemClickListenerWrapper(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
        {
            this$0 = MenuItemWrapperICS.this;
            super(onmenuitemclicklistener);
        }
    }


    MenuItemWrapperICS(Context context, SupportMenuItem supportmenuitem)
    {
        super(context, supportmenuitem);
    }

    public boolean collapseActionView()
    {
        return ((SupportMenuItem)mWrappedObject).collapseActionView();
    }

    ActionProviderWrapper createActionProviderWrapper(ActionProvider actionprovider)
    {
        return new ActionProviderWrapper(mContext, actionprovider);
    }

    public boolean expandActionView()
    {
        return ((SupportMenuItem)mWrappedObject).expandActionView();
    }

    public ActionProvider getActionProvider()
    {
        android.support.v4.view.ActionProvider actionprovider = ((SupportMenuItem)mWrappedObject).getSupportActionProvider();
        if(actionprovider instanceof ActionProviderWrapper)
            return ((ActionProviderWrapper)actionprovider).mInner;
        else
            return null;
    }

    public View getActionView()
    {
        View view1 = ((SupportMenuItem)mWrappedObject).getActionView();
        View view = view1;
        if(view1 instanceof CollapsibleActionViewWrapper)
            view = ((CollapsibleActionViewWrapper)view1).getWrappedView();
        return view;
    }

    public char getAlphabeticShortcut()
    {
        return ((SupportMenuItem)mWrappedObject).getAlphabeticShortcut();
    }

    public int getGroupId()
    {
        return ((SupportMenuItem)mWrappedObject).getGroupId();
    }

    public Drawable getIcon()
    {
        return ((SupportMenuItem)mWrappedObject).getIcon();
    }

    public Intent getIntent()
    {
        return ((SupportMenuItem)mWrappedObject).getIntent();
    }

    public int getItemId()
    {
        return ((SupportMenuItem)mWrappedObject).getItemId();
    }

    public android.view.ContextMenu.ContextMenuInfo getMenuInfo()
    {
        return ((SupportMenuItem)mWrappedObject).getMenuInfo();
    }

    public char getNumericShortcut()
    {
        return ((SupportMenuItem)mWrappedObject).getNumericShortcut();
    }

    public int getOrder()
    {
        return ((SupportMenuItem)mWrappedObject).getOrder();
    }

    public SubMenu getSubMenu()
    {
        return getSubMenuWrapper(((SupportMenuItem)mWrappedObject).getSubMenu());
    }

    public CharSequence getTitle()
    {
        return ((SupportMenuItem)mWrappedObject).getTitle();
    }

    public CharSequence getTitleCondensed()
    {
        return ((SupportMenuItem)mWrappedObject).getTitleCondensed();
    }

    public boolean hasSubMenu()
    {
        return ((SupportMenuItem)mWrappedObject).hasSubMenu();
    }

    public boolean isActionViewExpanded()
    {
        return ((SupportMenuItem)mWrappedObject).isActionViewExpanded();
    }

    public boolean isCheckable()
    {
        return ((SupportMenuItem)mWrappedObject).isCheckable();
    }

    public boolean isChecked()
    {
        return ((SupportMenuItem)mWrappedObject).isChecked();
    }

    public boolean isEnabled()
    {
        return ((SupportMenuItem)mWrappedObject).isEnabled();
    }

    public boolean isVisible()
    {
        return ((SupportMenuItem)mWrappedObject).isVisible();
    }

    public MenuItem setActionProvider(ActionProvider actionprovider)
    {
        SupportMenuItem supportmenuitem = (SupportMenuItem)mWrappedObject;
        if(actionprovider != null)
            actionprovider = createActionProviderWrapper(actionprovider);
        else
            actionprovider = null;
        supportmenuitem.setSupportActionProvider(actionprovider);
        return this;
    }

    public MenuItem setActionView(int i)
    {
        ((SupportMenuItem)mWrappedObject).setActionView(i);
        View view = ((SupportMenuItem)mWrappedObject).getActionView();
        if(view instanceof CollapsibleActionView)
            ((SupportMenuItem)mWrappedObject).setActionView(new CollapsibleActionViewWrapper(view));
        return this;
    }

    public MenuItem setActionView(View view)
    {
        Object obj = view;
        if(view instanceof CollapsibleActionView)
            obj = new CollapsibleActionViewWrapper(view);
        ((SupportMenuItem)mWrappedObject).setActionView(((View) (obj)));
        return this;
    }

    public MenuItem setAlphabeticShortcut(char c)
    {
        ((SupportMenuItem)mWrappedObject).setAlphabeticShortcut(c);
        return this;
    }

    public MenuItem setCheckable(boolean flag)
    {
        ((SupportMenuItem)mWrappedObject).setCheckable(flag);
        return this;
    }

    public MenuItem setChecked(boolean flag)
    {
        ((SupportMenuItem)mWrappedObject).setChecked(flag);
        return this;
    }

    public MenuItem setEnabled(boolean flag)
    {
        ((SupportMenuItem)mWrappedObject).setEnabled(flag);
        return this;
    }

    public void setExclusiveCheckable(boolean flag)
    {
        try
        {
            if(mSetExclusiveCheckableMethod == null)
                mSetExclusiveCheckableMethod = ((SupportMenuItem)mWrappedObject).getClass().getDeclaredMethod("setExclusiveCheckable", new Class[] {
                    Boolean.TYPE
                });
            mSetExclusiveCheckableMethod.invoke(mWrappedObject, new Object[] {
                Boolean.valueOf(flag)
            });
            return;
        }
        catch(Exception exception)
        {
            Log.w("MenuItemWrapper", "Error while calling setExclusiveCheckable", exception);
        }
    }

    public MenuItem setIcon(int i)
    {
        ((SupportMenuItem)mWrappedObject).setIcon(i);
        return this;
    }

    public MenuItem setIcon(Drawable drawable)
    {
        ((SupportMenuItem)mWrappedObject).setIcon(drawable);
        return this;
    }

    public MenuItem setIntent(Intent intent)
    {
        ((SupportMenuItem)mWrappedObject).setIntent(intent);
        return this;
    }

    public MenuItem setNumericShortcut(char c)
    {
        ((SupportMenuItem)mWrappedObject).setNumericShortcut(c);
        return this;
    }

    public MenuItem setOnActionExpandListener(android.view.MenuItem.OnActionExpandListener onactionexpandlistener)
    {
        SupportMenuItem supportmenuitem = (SupportMenuItem)mWrappedObject;
        if(onactionexpandlistener != null)
            onactionexpandlistener = new OnActionExpandListenerWrapper(onactionexpandlistener);
        else
            onactionexpandlistener = null;
        supportmenuitem.setSupportOnActionExpandListener(onactionexpandlistener);
        return this;
    }

    public MenuItem setOnMenuItemClickListener(android.view.MenuItem.OnMenuItemClickListener onmenuitemclicklistener)
    {
        SupportMenuItem supportmenuitem = (SupportMenuItem)mWrappedObject;
        if(onmenuitemclicklistener != null)
            onmenuitemclicklistener = new OnMenuItemClickListenerWrapper(onmenuitemclicklistener);
        else
            onmenuitemclicklistener = null;
        supportmenuitem.setOnMenuItemClickListener(onmenuitemclicklistener);
        return this;
    }

    public MenuItem setShortcut(char c, char c1)
    {
        ((SupportMenuItem)mWrappedObject).setShortcut(c, c1);
        return this;
    }

    public void setShowAsAction(int i)
    {
        ((SupportMenuItem)mWrappedObject).setShowAsAction(i);
    }

    public MenuItem setShowAsActionFlags(int i)
    {
        ((SupportMenuItem)mWrappedObject).setShowAsActionFlags(i);
        return this;
    }

    public MenuItem setTitle(int i)
    {
        ((SupportMenuItem)mWrappedObject).setTitle(i);
        return this;
    }

    public MenuItem setTitle(CharSequence charsequence)
    {
        ((SupportMenuItem)mWrappedObject).setTitle(charsequence);
        return this;
    }

    public MenuItem setTitleCondensed(CharSequence charsequence)
    {
        ((SupportMenuItem)mWrappedObject).setTitleCondensed(charsequence);
        return this;
    }

    public MenuItem setVisible(boolean flag)
    {
        return ((SupportMenuItem)mWrappedObject).setVisible(flag);
    }

    static final String LOG_TAG = "MenuItemWrapper";
    private Method mSetExclusiveCheckableMethod;
}
