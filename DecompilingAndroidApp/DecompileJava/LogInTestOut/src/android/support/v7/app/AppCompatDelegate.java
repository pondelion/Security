// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.os.BuildCompat;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import java.lang.annotation.Annotation;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplN, AppCompatDelegateImplV23, AppCompatDelegateImplV14, AppCompatDelegateImplV11, 
//            AppCompatDelegateImplV9, AppCompatCallback, ActionBar

public abstract class AppCompatDelegate
{
    public static interface NightMode
        extends Annotation
    {
    }


    AppCompatDelegate()
    {
    }

    public static AppCompatDelegate create(Activity activity, AppCompatCallback appcompatcallback)
    {
        return create(((Context) (activity)), activity.getWindow(), appcompatcallback);
    }

    public static AppCompatDelegate create(Dialog dialog, AppCompatCallback appcompatcallback)
    {
        return create(dialog.getContext(), dialog.getWindow(), appcompatcallback);
    }

    private static AppCompatDelegate create(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(BuildCompat.isAtLeastN())
            return new AppCompatDelegateImplN(context, window, appcompatcallback);
        if(i >= 23)
            return new AppCompatDelegateImplV23(context, window, appcompatcallback);
        if(i >= 14)
            return new AppCompatDelegateImplV14(context, window, appcompatcallback);
        if(i >= 11)
            return new AppCompatDelegateImplV11(context, window, appcompatcallback);
        else
            return new AppCompatDelegateImplV9(context, window, appcompatcallback);
    }

    public static int getDefaultNightMode()
    {
        return sDefaultNightMode;
    }

    public static boolean isCompatVectorFromResourcesEnabled()
    {
        return sCompatVectorFromResourcesEnabled;
    }

    public static void setCompatVectorFromResourcesEnabled(boolean flag)
    {
        sCompatVectorFromResourcesEnabled = flag;
    }

    public static void setDefaultNightMode(int i)
    {
        switch(i)
        {
        default:
            Log.d("AppCompatDelegate", "setDefaultNightMode() called with an unknown mode");
            return;

        case -1: 
        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
            sDefaultNightMode = i;
            break;
        }
    }

    public abstract void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams);

    public abstract boolean applyDayNight();

    public abstract View createView(View view, String s, Context context, AttributeSet attributeset);

    public abstract View findViewById(int i);

    public abstract ActionBarDrawerToggle.Delegate getDrawerToggleDelegate();

    public abstract MenuInflater getMenuInflater();

    public abstract ActionBar getSupportActionBar();

    public abstract boolean hasWindowFeature(int i);

    public abstract void installViewFactory();

    public abstract void invalidateOptionsMenu();

    public abstract boolean isHandleNativeActionModesEnabled();

    public abstract void onConfigurationChanged(Configuration configuration);

    public abstract void onCreate(Bundle bundle);

    public abstract void onDestroy();

    public abstract void onPostCreate(Bundle bundle);

    public abstract void onPostResume();

    public abstract void onSaveInstanceState(Bundle bundle);

    public abstract void onStart();

    public abstract void onStop();

    public abstract boolean requestWindowFeature(int i);

    public abstract void setContentView(int i);

    public abstract void setContentView(View view);

    public abstract void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams);

    public abstract void setHandleNativeActionModesEnabled(boolean flag);

    public abstract void setLocalNightMode(int i);

    public abstract void setSupportActionBar(Toolbar toolbar);

    public abstract void setTitle(CharSequence charsequence);

    public abstract ActionMode startSupportActionMode(android.support.v7.view.ActionMode.Callback callback);

    public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
    public static final int FEATURE_SUPPORT_ACTION_BAR = 108;
    public static final int FEATURE_SUPPORT_ACTION_BAR_OVERLAY = 109;
    public static final int MODE_NIGHT_AUTO = 0;
    public static final int MODE_NIGHT_FOLLOW_SYSTEM = -1;
    public static final int MODE_NIGHT_NO = 1;
    static final int MODE_NIGHT_UNSPECIFIED = -100;
    public static final int MODE_NIGHT_YES = 2;
    static final String TAG = "AppCompatDelegate";
    private static boolean sCompatVectorFromResourcesEnabled = false;
    private static int sDefaultNightMode = -1;

}
