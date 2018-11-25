// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.graphics.drawable.DrawerArrowDrawable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            ActionBarDrawerToggleHoneycomb

public class ActionBarDrawerToggle
    implements android.support.v4.widget.DrawerLayout.DrawerListener
{
    public static interface Delegate
    {

        public abstract Context getActionBarThemedContext();

        public abstract Drawable getThemeUpIndicator();

        public abstract boolean isNavigationVisible();

        public abstract void setActionBarDescription(int i);

        public abstract void setActionBarUpIndicator(Drawable drawable, int i);
    }

    public static interface DelegateProvider
    {

        public abstract Delegate getDrawerToggleDelegate();
    }

    static class DummyDelegate
        implements Delegate
    {

        public Context getActionBarThemedContext()
        {
            return mActivity;
        }

        public Drawable getThemeUpIndicator()
        {
            return null;
        }

        public boolean isNavigationVisible()
        {
            return true;
        }

        public void setActionBarDescription(int i)
        {
        }

        public void setActionBarUpIndicator(Drawable drawable, int i)
        {
        }

        final Activity mActivity;

        DummyDelegate(Activity activity)
        {
            mActivity = activity;
        }
    }

    private static class HoneycombDelegate
        implements Delegate
    {

        public Context getActionBarThemedContext()
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
                return actionbar.getThemedContext();
            else
                return mActivity;
        }

        public Drawable getThemeUpIndicator()
        {
            return ActionBarDrawerToggleHoneycomb.getThemeUpIndicator(mActivity);
        }

        public boolean isNavigationVisible()
        {
            ActionBar actionbar = mActivity.getActionBar();
            return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
        }

        public void setActionBarDescription(int i)
        {
            mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarDescription(mSetIndicatorInfo, mActivity, i);
        }

        public void setActionBarUpIndicator(Drawable drawable, int i)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
            {
                actionbar.setDisplayShowHomeEnabled(true);
                mSetIndicatorInfo = ActionBarDrawerToggleHoneycomb.setActionBarUpIndicator(mSetIndicatorInfo, mActivity, drawable, i);
                actionbar.setDisplayShowHomeEnabled(false);
            }
        }

        final Activity mActivity;
        ActionBarDrawerToggleHoneycomb.SetIndicatorInfo mSetIndicatorInfo;

        HoneycombDelegate(Activity activity)
        {
            mActivity = activity;
        }
    }

    private static class JellybeanMr2Delegate
        implements Delegate
    {

        public Context getActionBarThemedContext()
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
                return actionbar.getThemedContext();
            else
                return mActivity;
        }

        public Drawable getThemeUpIndicator()
        {
            TypedArray typedarray = getActionBarThemedContext().obtainStyledAttributes(null, new int[] {
                0x101030b
            }, 0x10102ce, 0);
            Drawable drawable = typedarray.getDrawable(0);
            typedarray.recycle();
            return drawable;
        }

        public boolean isNavigationVisible()
        {
            ActionBar actionbar = mActivity.getActionBar();
            return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
        }

        public void setActionBarDescription(int i)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
                actionbar.setHomeActionContentDescription(i);
        }

        public void setActionBarUpIndicator(Drawable drawable, int i)
        {
            ActionBar actionbar = mActivity.getActionBar();
            if(actionbar != null)
            {
                actionbar.setHomeAsUpIndicator(drawable);
                actionbar.setHomeActionContentDescription(i);
            }
        }

        final Activity mActivity;

        JellybeanMr2Delegate(Activity activity)
        {
            mActivity = activity;
        }
    }

    static class ToolbarCompatDelegate
        implements Delegate
    {

        public Context getActionBarThemedContext()
        {
            return mToolbar.getContext();
        }

        public Drawable getThemeUpIndicator()
        {
            return mDefaultUpIndicator;
        }

        public boolean isNavigationVisible()
        {
            return true;
        }

        public void setActionBarDescription(int i)
        {
            if(i == 0)
            {
                mToolbar.setNavigationContentDescription(mDefaultContentDescription);
                return;
            } else
            {
                mToolbar.setNavigationContentDescription(i);
                return;
            }
        }

        public void setActionBarUpIndicator(Drawable drawable, int i)
        {
            mToolbar.setNavigationIcon(drawable);
            setActionBarDescription(i);
        }

        final CharSequence mDefaultContentDescription;
        final Drawable mDefaultUpIndicator;
        final Toolbar mToolbar;

        ToolbarCompatDelegate(Toolbar toolbar)
        {
            mToolbar = toolbar;
            mDefaultUpIndicator = toolbar.getNavigationIcon();
            mDefaultContentDescription = toolbar.getNavigationContentDescription();
        }
    }


    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, int i, int j)
    {
        this(activity, null, drawerlayout, null, i, j);
    }

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawerlayout, Toolbar toolbar, int i, int j)
    {
        this(activity, toolbar, drawerlayout, null, i, j);
    }

    ActionBarDrawerToggle(Activity activity, Toolbar toolbar, DrawerLayout drawerlayout, DrawerArrowDrawable drawerarrowdrawable, int i, int j)
    {
        mDrawerIndicatorEnabled = true;
        mWarnedForDisplayHomeAsUp = false;
        if(toolbar != null)
        {
            mActivityImpl = new ToolbarCompatDelegate(toolbar);
            toolbar.setNavigationOnClickListener(new android.view.View.OnClickListener() {

                public void onClick(View view)
                {
                    if(mDrawerIndicatorEnabled)
                        toggle();
                    else
                    if(mToolbarNavigationClickListener != null)
                    {
                        mToolbarNavigationClickListener.onClick(view);
                        return;
                    }
                }

                final ActionBarDrawerToggle this$0;

            
            {
                this$0 = ActionBarDrawerToggle.this;
                super();
            }
            }
);
        } else
        if(activity instanceof DelegateProvider)
            mActivityImpl = ((DelegateProvider)activity).getDrawerToggleDelegate();
        else
        if(android.os.Build.VERSION.SDK_INT >= 18)
            mActivityImpl = new JellybeanMr2Delegate(activity);
        else
        if(android.os.Build.VERSION.SDK_INT >= 11)
            mActivityImpl = new HoneycombDelegate(activity);
        else
            mActivityImpl = new DummyDelegate(activity);
        mDrawerLayout = drawerlayout;
        mOpenDrawerContentDescRes = i;
        mCloseDrawerContentDescRes = j;
        if(drawerarrowdrawable == null)
            mSlider = new DrawerArrowDrawable(mActivityImpl.getActionBarThemedContext());
        else
            mSlider = drawerarrowdrawable;
        mHomeAsUpIndicator = getThemeUpIndicator();
    }

    private void setPosition(float f)
    {
        if(f != 1.0F) goto _L2; else goto _L1
_L1:
        mSlider.setVerticalMirror(true);
_L4:
        mSlider.setProgress(f);
        return;
_L2:
        if(f == 0.0F)
            mSlider.setVerticalMirror(false);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public DrawerArrowDrawable getDrawerArrowDrawable()
    {
        return mSlider;
    }

    Drawable getThemeUpIndicator()
    {
        return mActivityImpl.getThemeUpIndicator();
    }

    public android.view.View.OnClickListener getToolbarNavigationClickListener()
    {
        return mToolbarNavigationClickListener;
    }

    public boolean isDrawerIndicatorEnabled()
    {
        return mDrawerIndicatorEnabled;
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        if(!mHasCustomUpIndicator)
            mHomeAsUpIndicator = getThemeUpIndicator();
        syncState();
    }

    public void onDrawerClosed(View view)
    {
        setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mOpenDrawerContentDescRes);
    }

    public void onDrawerOpened(View view)
    {
        setPosition(1.0F);
        if(mDrawerIndicatorEnabled)
            setActionBarDescription(mCloseDrawerContentDescRes);
    }

    public void onDrawerSlide(View view, float f)
    {
        setPosition(Math.min(1.0F, Math.max(0.0F, f)));
    }

    public void onDrawerStateChanged(int i)
    {
    }

    public boolean onOptionsItemSelected(MenuItem menuitem)
    {
        if(menuitem != null && menuitem.getItemId() == 0x102002c && mDrawerIndicatorEnabled)
        {
            toggle();
            return true;
        } else
        {
            return false;
        }
    }

    void setActionBarDescription(int i)
    {
        mActivityImpl.setActionBarDescription(i);
    }

    void setActionBarUpIndicator(Drawable drawable, int i)
    {
        if(!mWarnedForDisplayHomeAsUp && !mActivityImpl.isNavigationVisible())
        {
            Log.w("ActionBarDrawerToggle", "DrawerToggle may not show up because NavigationIcon is not visible. You may need to call actionbar.setDisplayHomeAsUpEnabled(true);");
            mWarnedForDisplayHomeAsUp = true;
        }
        mActivityImpl.setActionBarUpIndicator(drawable, i);
    }

    public void setDrawerArrowDrawable(DrawerArrowDrawable drawerarrowdrawable)
    {
        mSlider = drawerarrowdrawable;
        syncState();
    }

    public void setDrawerIndicatorEnabled(boolean flag)
    {
        if(flag != mDrawerIndicatorEnabled)
        {
            if(flag)
            {
                DrawerArrowDrawable drawerarrowdrawable = mSlider;
                int i;
                if(mDrawerLayout.isDrawerOpen(0x800003))
                    i = mCloseDrawerContentDescRes;
                else
                    i = mOpenDrawerContentDescRes;
                setActionBarUpIndicator(drawerarrowdrawable, i);
            } else
            {
                setActionBarUpIndicator(mHomeAsUpIndicator, 0);
            }
            mDrawerIndicatorEnabled = flag;
        }
    }

    public void setHomeAsUpIndicator(int i)
    {
        Drawable drawable = null;
        if(i != 0)
            drawable = mDrawerLayout.getResources().getDrawable(i);
        setHomeAsUpIndicator(drawable);
    }

    public void setHomeAsUpIndicator(Drawable drawable)
    {
        if(drawable == null)
        {
            mHomeAsUpIndicator = getThemeUpIndicator();
            mHasCustomUpIndicator = false;
        } else
        {
            mHomeAsUpIndicator = drawable;
            mHasCustomUpIndicator = true;
        }
        if(!mDrawerIndicatorEnabled)
            setActionBarUpIndicator(mHomeAsUpIndicator, 0);
    }

    public void setToolbarNavigationClickListener(android.view.View.OnClickListener onclicklistener)
    {
        mToolbarNavigationClickListener = onclicklistener;
    }

    public void syncState()
    {
        if(mDrawerLayout.isDrawerOpen(0x800003))
            setPosition(1.0F);
        else
            setPosition(0.0F);
        if(mDrawerIndicatorEnabled)
        {
            DrawerArrowDrawable drawerarrowdrawable = mSlider;
            int i;
            if(mDrawerLayout.isDrawerOpen(0x800003))
                i = mCloseDrawerContentDescRes;
            else
                i = mOpenDrawerContentDescRes;
            setActionBarUpIndicator(drawerarrowdrawable, i);
        }
    }

    void toggle()
    {
        int i = mDrawerLayout.getDrawerLockMode(0x800003);
        if(mDrawerLayout.isDrawerVisible(0x800003) && i != 2)
            mDrawerLayout.closeDrawer(0x800003);
        else
        if(i != 1)
        {
            mDrawerLayout.openDrawer(0x800003);
            return;
        }
    }

    private final Delegate mActivityImpl;
    private final int mCloseDrawerContentDescRes;
    boolean mDrawerIndicatorEnabled;
    private final DrawerLayout mDrawerLayout;
    private boolean mHasCustomUpIndicator;
    private Drawable mHomeAsUpIndicator;
    private final int mOpenDrawerContentDescRes;
    private DrawerArrowDrawable mSlider;
    android.view.View.OnClickListener mToolbarNavigationClickListener;
    private boolean mWarnedForDisplayHomeAsUp;
}
