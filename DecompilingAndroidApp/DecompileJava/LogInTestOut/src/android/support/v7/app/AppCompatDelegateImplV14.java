// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Activity;
import android.content.*;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV11, TwilightManager, AppCompatCallback

class AppCompatDelegateImplV14 extends AppCompatDelegateImplV11
{
    class AppCompatWindowCallbackV14 extends AppCompatDelegateImplBase.AppCompatWindowCallbackBase
    {

        public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
        {
            if(isHandleNativeActionModesEnabled())
                return startAsSupportActionMode(callback);
            else
                return super.onWindowStartingActionMode(callback);
        }

        final ActionMode startAsSupportActionMode(android.view.ActionMode.Callback callback)
        {
            callback = new android.support.v7.view.SupportActionModeWrapper.CallbackWrapper(mContext, callback);
            android.support.v7.view.ActionMode actionmode = startSupportActionMode(callback);
            if(actionmode != null)
                return callback.getActionModeWrapper(actionmode);
            else
                return null;
        }

        final AppCompatDelegateImplV14 this$0;

        AppCompatWindowCallbackV14(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImplV14.this;
            super(AppCompatDelegateImplV14.this, callback);
        }
    }

    final class AutoNightModeManager
    {

        final void cleanup()
        {
            if(mAutoTimeChangeReceiver != null)
            {
                mContext.unregisterReceiver(mAutoTimeChangeReceiver);
                mAutoTimeChangeReceiver = null;
            }
        }

        final void dispatchTimeChanged()
        {
            boolean flag = mTwilightManager.isNight();
            if(flag != mIsNight)
            {
                mIsNight = flag;
                applyDayNight();
            }
        }

        final int getApplyableNightMode()
        {
            return !mIsNight ? 1 : 2;
        }

        final void setup()
        {
            cleanup();
            if(mAutoTimeChangeReceiver == null)
                mAutoTimeChangeReceiver = new BroadcastReceiver() {

                    public void onReceive(Context context, Intent intent)
                    {
                        dispatchTimeChanged();
                    }

                    final AutoNightModeManager this$1;

            
            {
                this$1 = AutoNightModeManager.this;
                super();
            }
                }
;
            if(mAutoTimeChangeReceiverFilter == null)
            {
                mAutoTimeChangeReceiverFilter = new IntentFilter();
                mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_SET");
                mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIMEZONE_CHANGED");
                mAutoTimeChangeReceiverFilter.addAction("android.intent.action.TIME_TICK");
            }
            mContext.registerReceiver(mAutoTimeChangeReceiver, mAutoTimeChangeReceiverFilter);
        }

        private BroadcastReceiver mAutoTimeChangeReceiver;
        private IntentFilter mAutoTimeChangeReceiverFilter;
        private boolean mIsNight;
        private TwilightManager mTwilightManager;
        final AppCompatDelegateImplV14 this$0;

        AutoNightModeManager(TwilightManager twilightmanager)
        {
            this$0 = AppCompatDelegateImplV14.this;
            super();
            mTwilightManager = twilightmanager;
            mIsNight = twilightmanager.isNight();
        }
    }


    AppCompatDelegateImplV14(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        super(context, window, appcompatcallback);
        mLocalNightMode = -100;
        mHandleNativeActionModes = true;
    }

    private void ensureAutoNightModeManager()
    {
        if(mAutoNightModeManager == null)
            mAutoNightModeManager = new AutoNightModeManager(TwilightManager.getInstance(mContext));
    }

    private int getNightMode()
    {
        if(mLocalNightMode != -100)
            return mLocalNightMode;
        else
            return getDefaultNightMode();
    }

    private boolean shouldRecreateOnNightModeChange()
    {
        if(mApplyDayNightCalled && (mContext instanceof Activity))
        {
            PackageManager packagemanager = mContext.getPackageManager();
            int i;
            try
            {
                i = packagemanager.getActivityInfo(new ComponentName(mContext, mContext.getClass()), 0).configChanges;
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                Log.d("AppCompatDelegate", "Exception while getting ActivityInfo", namenotfoundexception);
                return true;
            }
            return (i & 0x200) == 0;
        } else
        {
            return false;
        }
    }

    private boolean updateForNightMode(int i)
    {
        Resources resources = mContext.getResources();
        Configuration configuration = resources.getConfiguration();
        int j = configuration.uiMode;
        if(i == 2)
            i = 32;
        else
            i = 16;
        if((j & 0x30) != i)
        {
            if(shouldRecreateOnNightModeChange())
            {
                ((Activity)mContext).recreate();
            } else
            {
                configuration = new Configuration(configuration);
                configuration.uiMode = configuration.uiMode & 0xffffffcf | i;
                resources.updateConfiguration(configuration, null);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public boolean applyDayNight()
    {
        boolean flag = false;
        int i = getNightMode();
        int j = mapNightMode(i);
        if(j != -1)
            flag = updateForNightMode(j);
        if(i == 0)
        {
            ensureAutoNightModeManager();
            mAutoNightModeManager.setup();
        }
        mApplyDayNightCalled = true;
        return flag;
    }

    final AutoNightModeManager getAutoNightModeManager()
    {
        ensureAutoNightModeManager();
        return mAutoNightModeManager;
    }

    public boolean isHandleNativeActionModesEnabled()
    {
        return mHandleNativeActionModes;
    }

    int mapNightMode(int i)
    {
        switch(i)
        {
        default:
            return i;

        case 0: // '\0'
            ensureAutoNightModeManager();
            return mAutoNightModeManager.getApplyableNightMode();

        case -100: 
            return -1;
        }
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if(bundle != null && mLocalNightMode == -100)
            mLocalNightMode = bundle.getInt("appcompat:local_night_mode", -100);
    }

    public void onDestroy()
    {
        super.onDestroy();
        if(mAutoNightModeManager != null)
            mAutoNightModeManager.cleanup();
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if(mLocalNightMode != -100)
            bundle.putInt("appcompat:local_night_mode", mLocalNightMode);
    }

    public void onStart()
    {
        super.onStart();
        applyDayNight();
    }

    public void onStop()
    {
        super.onStop();
        if(mAutoNightModeManager != null)
            mAutoNightModeManager.cleanup();
    }

    public void setHandleNativeActionModesEnabled(boolean flag)
    {
        mHandleNativeActionModes = flag;
    }

    public void setLocalNightMode(int i)
    {
        i;
        JVM INSTR tableswitch -1 2: default 32
    //                   -1 41
    //                   0 41
    //                   1 41
    //                   2 41;
           goto _L1 _L2 _L2 _L2 _L2
_L1:
        Log.i("AppCompatDelegate", "setLocalNightMode() called with an unknown mode");
_L4:
        return;
_L2:
        if(mLocalNightMode != i)
        {
            mLocalNightMode = i;
            if(mApplyDayNightCalled)
            {
                applyDayNight();
                return;
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    android.view.Window.Callback wrapWindowCallback(android.view.Window.Callback callback)
    {
        return new AppCompatWindowCallbackV14(callback);
    }

    private static final String KEY_LOCAL_NIGHT_MODE = "appcompat:local_night_mode";
    private boolean mApplyDayNightCalled;
    private AutoNightModeManager mAutoNightModeManager;
    private boolean mHandleNativeActionModes;
    private int mLocalNightMode;
}
