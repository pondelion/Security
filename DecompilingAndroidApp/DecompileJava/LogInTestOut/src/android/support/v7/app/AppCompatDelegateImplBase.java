// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.view.*;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.TintTypedArray;
import android.view.*;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegate, ActionBar, AppCompatCallback

abstract class AppCompatDelegateImplBase extends AppCompatDelegate
{
    private class ActionBarDrawableToggleImpl
        implements ActionBarDrawerToggle.Delegate
    {

        public Context getActionBarThemedContext()
        {
            return AppCompatDelegateImplBase.this.getActionBarThemedContext();
        }

        public Drawable getThemeUpIndicator()
        {
            TintTypedArray tinttypedarray = TintTypedArray.obtainStyledAttributes(getActionBarThemedContext(), null, new int[] {
                android.support.v7.appcompat.R.attr.homeAsUpIndicator
            });
            Drawable drawable = tinttypedarray.getDrawable(0);
            tinttypedarray.recycle();
            return drawable;
        }

        public boolean isNavigationVisible()
        {
            ActionBar actionbar = getSupportActionBar();
            return actionbar != null && (actionbar.getDisplayOptions() & 4) != 0;
        }

        public void setActionBarDescription(int i)
        {
            ActionBar actionbar = getSupportActionBar();
            if(actionbar != null)
                actionbar.setHomeActionContentDescription(i);
        }

        public void setActionBarUpIndicator(Drawable drawable, int i)
        {
            ActionBar actionbar = getSupportActionBar();
            if(actionbar != null)
            {
                actionbar.setHomeAsUpIndicator(drawable);
                actionbar.setHomeActionContentDescription(i);
            }
        }

        final AppCompatDelegateImplBase this$0;

        ActionBarDrawableToggleImpl()
        {
            this$0 = AppCompatDelegateImplBase.this;
            super();
        }
    }

    class AppCompatWindowCallbackBase extends WindowCallbackWrapper
    {

        public boolean dispatchKeyEvent(KeyEvent keyevent)
        {
            return AppCompatDelegateImplBase.this.dispatchKeyEvent(keyevent) || super.dispatchKeyEvent(keyevent);
        }

        public boolean dispatchKeyShortcutEvent(KeyEvent keyevent)
        {
            return super.dispatchKeyShortcutEvent(keyevent) || onKeyShortcut(keyevent.getKeyCode(), keyevent);
        }

        public void onContentChanged()
        {
        }

        public boolean onCreatePanelMenu(int i, Menu menu)
        {
            if(i == 0 && !(menu instanceof MenuBuilder))
                return false;
            else
                return super.onCreatePanelMenu(i, menu);
        }

        public boolean onMenuOpened(int i, Menu menu)
        {
            super.onMenuOpened(i, menu);
            AppCompatDelegateImplBase.this.onMenuOpened(i, menu);
            return true;
        }

        public void onPanelClosed(int i, Menu menu)
        {
            super.onPanelClosed(i, menu);
            AppCompatDelegateImplBase.this.onPanelClosed(i, menu);
        }

        public boolean onPreparePanel(int i, View view, Menu menu)
        {
            boolean flag;
            MenuBuilder menubuilder;
            if(menu instanceof MenuBuilder)
                menubuilder = (MenuBuilder)menu;
            else
                menubuilder = null;
            if(i == 0 && menubuilder == null)
            {
                flag = false;
            } else
            {
                if(menubuilder != null)
                    menubuilder.setOverrideVisibleItems(true);
                boolean flag1 = super.onPreparePanel(i, view, menu);
                flag = flag1;
                if(menubuilder != null)
                {
                    menubuilder.setOverrideVisibleItems(false);
                    return flag1;
                }
            }
            return flag;
        }

        final AppCompatDelegateImplBase this$0;

        AppCompatWindowCallbackBase(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImplBase.this;
            super(callback);
        }
    }


    AppCompatDelegateImplBase(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        mContext = context;
        mWindow = window;
        mAppCompatCallback = appcompatcallback;
        mOriginalWindowCallback = mWindow.getCallback();
        if(mOriginalWindowCallback instanceof AppCompatWindowCallbackBase)
            throw new IllegalStateException("AppCompat has already installed itself into the Window");
        mAppCompatWindowCallback = wrapWindowCallback(mOriginalWindowCallback);
        mWindow.setCallback(mAppCompatWindowCallback);
        context = TintTypedArray.obtainStyledAttributes(context, null, sWindowBackgroundStyleable);
        window = context.getDrawableIfKnown(0);
        if(window != null)
            mWindow.setBackgroundDrawable(window);
        context.recycle();
    }

    public boolean applyDayNight()
    {
        return false;
    }

    abstract boolean dispatchKeyEvent(KeyEvent keyevent);

    final Context getActionBarThemedContext()
    {
        Context context = null;
        Object obj = getSupportActionBar();
        if(obj != null)
            context = ((ActionBar) (obj)).getThemedContext();
        obj = context;
        if(context == null)
            obj = mContext;
        return ((Context) (obj));
    }

    public final ActionBarDrawerToggle.Delegate getDrawerToggleDelegate()
    {
        return new ActionBarDrawableToggleImpl();
    }

    public MenuInflater getMenuInflater()
    {
        if(mMenuInflater == null)
        {
            initWindowDecorActionBar();
            Context context;
            if(mActionBar != null)
                context = mActionBar.getThemedContext();
            else
                context = mContext;
            mMenuInflater = new SupportMenuInflater(context);
        }
        return mMenuInflater;
    }

    public ActionBar getSupportActionBar()
    {
        initWindowDecorActionBar();
        return mActionBar;
    }

    final CharSequence getTitle()
    {
        if(mOriginalWindowCallback instanceof Activity)
            return ((Activity)mOriginalWindowCallback).getTitle();
        else
            return mTitle;
    }

    final android.view.Window.Callback getWindowCallback()
    {
        return mWindow.getCallback();
    }

    abstract void initWindowDecorActionBar();

    final boolean isDestroyed()
    {
        return mIsDestroyed;
    }

    public boolean isHandleNativeActionModesEnabled()
    {
        return false;
    }

    final boolean isStarted()
    {
        return mIsStarted;
    }

    public void onDestroy()
    {
        mIsDestroyed = true;
    }

    abstract boolean onKeyShortcut(int i, KeyEvent keyevent);

    abstract boolean onMenuOpened(int i, Menu menu);

    abstract void onPanelClosed(int i, Menu menu);

    public void onSaveInstanceState(Bundle bundle)
    {
    }

    public void onStart()
    {
        mIsStarted = true;
    }

    public void onStop()
    {
        mIsStarted = false;
    }

    abstract void onTitleChanged(CharSequence charsequence);

    final ActionBar peekSupportActionBar()
    {
        return mActionBar;
    }

    public void setHandleNativeActionModesEnabled(boolean flag)
    {
    }

    public void setLocalNightMode(int i)
    {
    }

    public final void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        onTitleChanged(charsequence);
    }

    abstract ActionMode startSupportActionModeFromWindow(android.support.v7.view.ActionMode.Callback callback);

    android.view.Window.Callback wrapWindowCallback(android.view.Window.Callback callback)
    {
        return new AppCompatWindowCallbackBase(callback);
    }

    static final boolean DEBUG = false;
    static final String EXCEPTION_HANDLER_MESSAGE_SUFFIX = ". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.";
    private static final boolean SHOULD_INSTALL_EXCEPTION_HANDLER;
    private static boolean sInstalledExceptionHandler;
    private static final int sWindowBackgroundStyleable[] = {
        0x1010054
    };
    ActionBar mActionBar;
    final AppCompatCallback mAppCompatCallback;
    final android.view.Window.Callback mAppCompatWindowCallback;
    final Context mContext;
    boolean mHasActionBar;
    private boolean mIsDestroyed;
    boolean mIsFloating;
    private boolean mIsStarted;
    MenuInflater mMenuInflater;
    final android.view.Window.Callback mOriginalWindowCallback;
    boolean mOverlayActionBar;
    boolean mOverlayActionMode;
    private CharSequence mTitle;
    final Window mWindow;
    boolean mWindowNoTitle;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT < 21)
            flag = true;
        else
            flag = false;
        SHOULD_INSTALL_EXCEPTION_HANDLER = flag;
        if(SHOULD_INSTALL_EXCEPTION_HANDLER && !sInstalledExceptionHandler)
        {
            Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()) {

                private boolean shouldWrapException(Throwable throwable)
                {
                    boolean flag1;
label0:
                    {
                        boolean flag2 = false;
                        flag1 = flag2;
                        if(!(throwable instanceof android.content.res.Resources.NotFoundException))
                            break label0;
                        throwable = throwable.getMessage();
                        flag1 = flag2;
                        if(throwable == null)
                            break label0;
                        if(!throwable.contains("drawable"))
                        {
                            flag1 = flag2;
                            if(!throwable.contains("Drawable"))
                                break label0;
                        }
                        flag1 = true;
                    }
                    return flag1;
                }

                public void uncaughtException(Thread thread, Throwable throwable)
                {
                    if(shouldWrapException(throwable))
                    {
                        android.content.res.Resources.NotFoundException notfoundexception = new android.content.res.Resources.NotFoundException((new StringBuilder()).append(throwable.getMessage()).append(". If the resource you are trying to use is a vector resource, you may be referencing it in an unsupported way. See AppCompatDelegate.setCompatVectorFromResourcesEnabled() for more info.").toString());
                        notfoundexception.initCause(throwable.getCause());
                        notfoundexception.setStackTrace(throwable.getStackTrace());
                        defHandler.uncaughtException(thread, notfoundexception);
                        return;
                    } else
                    {
                        defHandler.uncaughtException(thread, throwable);
                        return;
                    }
                }

                final Thread.UncaughtExceptionHandler val$defHandler;

            
            {
                defHandler = uncaughtexceptionhandler;
                super();
            }
            }
);
            sInstalledExceptionHandler = true;
        }
    }
}
