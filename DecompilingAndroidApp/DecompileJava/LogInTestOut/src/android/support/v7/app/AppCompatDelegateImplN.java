// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.view.Menu;
import android.view.Window;
import java.util.List;

// Referenced classes of package android.support.v7.app:
//            AppCompatDelegateImplV23, AppCompatCallback

class AppCompatDelegateImplN extends AppCompatDelegateImplV23
{
    class AppCompatWindowCallbackN extends AppCompatDelegateImplV23.AppCompatWindowCallbackV23
    {

        public void onProvideKeyboardShortcuts(List list, Menu menu, int i)
        {
            AppCompatDelegateImplV9.PanelFeatureState panelfeaturestate = getPanelState(0, true);
            if(panelfeaturestate != null && panelfeaturestate.menu != null)
            {
                super.onProvideKeyboardShortcuts(list, panelfeaturestate.menu, i);
                return;
            } else
            {
                super.onProvideKeyboardShortcuts(list, menu, i);
                return;
            }
        }

        final AppCompatDelegateImplN this$0;

        AppCompatWindowCallbackN(android.view.Window.Callback callback)
        {
            this$0 = AppCompatDelegateImplN.this;
            super(AppCompatDelegateImplN.this, callback);
        }
    }


    AppCompatDelegateImplN(Context context, Window window, AppCompatCallback appcompatcallback)
    {
        super(context, window, appcompatcallback);
    }

    android.view.Window.Callback wrapWindowCallback(android.view.Window.Callback callback)
    {
        return new AppCompatWindowCallbackN(callback);
    }
}
