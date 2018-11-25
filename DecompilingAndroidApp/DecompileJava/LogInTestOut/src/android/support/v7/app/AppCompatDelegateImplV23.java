// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.UiModeManager;
import android.content.Context;
import android.view.ActionMode;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV14, AppCompatCallback

class AppCompatDelegateImplV23 extends AppCompatDelegateImplV14
{
    class AppCompatWindowCallbackV23 extends AppCompatDelegateImplV14.AppCompatWindowCallbackV14
    {

        public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback)
        {
            return null;
        }

        public ActionMode onWindowStartingActionMode(android.view.ActionMode.Callback callback, int i)
        {
            if(!isHandleNativeActionModesEnabled()) goto _L2; else goto _L1
_L1:
            i;
            JVM INSTR tableswitch 0 0: default 28
        //                       0 35;
               goto _L2 _L3
_L2:
            return super.onWindowStartingActionMode(callback, i);
_L3:
            return startAsSupportActionMode(callback);
        }

        final AppCompatDelegateImplV23 this$0;

        AppCompatWindowCallbackV23(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImplV23.this;
            super(AppCompatDelegateImplV23.this, callback);
        }
    }


    AppCompatDelegateImplV23(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        super(context, window, appcompatcallback);
        mUiModeManager = (UiModeManager)context.getSystemService("uimode");
    }

    int mapNightMode(int i)
    {
        if(i == 0 && mUiModeManager.getNightMode() == 0)
            return -1;
        else
            return super.mapNightMode(i);
    }

    android.view.Window.Callback wrapWindowCallback(android.view.Window.Callback callback)
    {
        return new AppCompatWindowCallbackV23(callback);
    }

    private final UiModeManager mUiModeManager;
}
