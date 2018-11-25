// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view;

import android.content.Context;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.ActionBarContextView;
import android.view.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.view:
//            ActionMode, SupportMenuInflater

public class StandaloneActionMode extends ActionMode
    implements android.support.v7.view.menu.MenuBuilder.Callback
{

    public StandaloneActionMode(Context context, ActionBarContextView actionbarcontextview, ActionMode.Callback callback, boolean flag)
    {
        mContext = context;
        mContextView = actionbarcontextview;
        mCallback = callback;
        mMenu = (new MenuBuilder(actionbarcontextview.getContext())).setDefaultShowAsAction(1);
        mMenu.setCallback(this);
        mFocusable = flag;
    }

    public void finish()
    {
        if(mFinished)
        {
            return;
        } else
        {
            mFinished = true;
            mContextView.sendAccessibilityEvent(32);
            mCallback.onDestroyActionMode(this);
            return;
        }
    }

    public View getCustomView()
    {
        if(mCustomView != null)
            return (View)mCustomView.get();
        else
            return null;
    }

    public Menu getMenu()
    {
        return mMenu;
    }

    public MenuInflater getMenuInflater()
    {
        return new SupportMenuInflater(mContextView.getContext());
    }

    public CharSequence getSubtitle()
    {
        return mContextView.getSubtitle();
    }

    public CharSequence getTitle()
    {
        return mContextView.getTitle();
    }

    public void invalidate()
    {
        mCallback.onPrepareActionMode(this, mMenu);
    }

    public boolean isTitleOptional()
    {
        return mContextView.isTitleOptional();
    }

    public boolean isUiFocusable()
    {
        return mFocusable;
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
    }

    public void onCloseSubMenu(SubMenuBuilder submenubuilder)
    {
    }

    public boolean onMenuItemSelected(MenuBuilder menubuilder, MenuItem menuitem)
    {
        return mCallback.onActionItemClicked(this, menuitem);
    }

    public void onMenuModeChange(MenuBuilder menubuilder)
    {
        invalidate();
        mContextView.showOverflowMenu();
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        if(!submenubuilder.hasVisibleItems())
        {
            return true;
        } else
        {
            (new MenuPopupHelper(mContextView.getContext(), submenubuilder)).show();
            return true;
        }
    }

    public void setCustomView(View view)
    {
        mContextView.setCustomView(view);
        if(view != null)
            view = new WeakReference(view);
        else
            view = null;
        mCustomView = view;
    }

    public void setSubtitle(int i)
    {
        setSubtitle(((CharSequence) (mContext.getString(i))));
    }

    public void setSubtitle(CharSequence charsequence)
    {
        mContextView.setSubtitle(charsequence);
    }

    public void setTitle(int i)
    {
        setTitle(((CharSequence) (mContext.getString(i))));
    }

    public void setTitle(CharSequence charsequence)
    {
        mContextView.setTitle(charsequence);
    }

    public void setTitleOptionalHint(boolean flag)
    {
        super.setTitleOptionalHint(flag);
        mContextView.setTitleOptional(flag);
    }

    private ActionMode.Callback mCallback;
    private Context mContext;
    private ActionBarContextView mContextView;
    private WeakReference mCustomView;
    private boolean mFinished;
    private boolean mFocusable;
    private MenuBuilder mMenu;
}
