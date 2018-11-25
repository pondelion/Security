// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v4.widget:
//            PopupWindowCompatApi21, PopupWindowCompatKitKat, PopupWindowCompatApi23

public final class PopupWindowCompat
{
    static class Api21PopupWindowImpl extends KitKatPopupWindowImpl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            return PopupWindowCompatApi21.getOverlapAnchor(popupwindow);
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
            PopupWindowCompatApi21.setOverlapAnchor(popupwindow, flag);
        }

        Api21PopupWindowImpl()
        {
        }
    }

    static class Api23PopupWindowImpl extends Api21PopupWindowImpl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            return PopupWindowCompatApi23.getOverlapAnchor(popupwindow);
        }

        public int getWindowLayoutType(PopupWindow popupwindow)
        {
            return PopupWindowCompatApi23.getWindowLayoutType(popupwindow);
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
            PopupWindowCompatApi23.setOverlapAnchor(popupwindow, flag);
        }

        public void setWindowLayoutType(PopupWindow popupwindow, int i)
        {
            PopupWindowCompatApi23.setWindowLayoutType(popupwindow, i);
        }

        Api23PopupWindowImpl()
        {
        }
    }

    static class BasePopupWindowImpl
        implements PopupWindowImpl
    {

        public boolean getOverlapAnchor(PopupWindow popupwindow)
        {
            return false;
        }

        public int getWindowLayoutType(PopupWindow popupwindow)
        {
            if(!sGetWindowLayoutTypeMethodAttempted)
            {
                int i;
                try
                {
                    sGetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("getWindowLayoutType", new Class[0]);
                    sGetWindowLayoutTypeMethod.setAccessible(true);
                }
                catch(Exception exception) { }
                sGetWindowLayoutTypeMethodAttempted = true;
            }
            if(sGetWindowLayoutTypeMethod == null)
                break MISSING_BLOCK_LABEL_58;
            i = ((Integer)sGetWindowLayoutTypeMethod.invoke(popupwindow, new Object[0])).intValue();
            return i;
            popupwindow;
            return 0;
        }

        public void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
        {
        }

        public void setWindowLayoutType(PopupWindow popupwindow, int i)
        {
            if(!sSetWindowLayoutTypeMethodAttempted)
            {
                try
                {
                    sSetWindowLayoutTypeMethod = android/widget/PopupWindow.getDeclaredMethod("setWindowLayoutType", new Class[] {
                        Integer.TYPE
                    });
                    sSetWindowLayoutTypeMethod.setAccessible(true);
                }
                catch(Exception exception) { }
                sSetWindowLayoutTypeMethodAttempted = true;
            }
            if(sSetWindowLayoutTypeMethod == null)
                break MISSING_BLOCK_LABEL_62;
            sSetWindowLayoutTypeMethod.invoke(popupwindow, new Object[] {
                Integer.valueOf(i)
            });
            return;
            popupwindow;
        }

        public void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
        {
            int l = i;
            if((GravityCompat.getAbsoluteGravity(k, ViewCompat.getLayoutDirection(view)) & 7) == 5)
                l = i - (popupwindow.getWidth() - view.getWidth());
            popupwindow.showAsDropDown(view, l, j);
        }

        private static Method sGetWindowLayoutTypeMethod;
        private static boolean sGetWindowLayoutTypeMethodAttempted;
        private static Method sSetWindowLayoutTypeMethod;
        private static boolean sSetWindowLayoutTypeMethodAttempted;

        BasePopupWindowImpl()
        {
        }
    }

    static class KitKatPopupWindowImpl extends BasePopupWindowImpl
    {

        public void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
        {
            PopupWindowCompatKitKat.showAsDropDown(popupwindow, view, i, j, k);
        }

        KitKatPopupWindowImpl()
        {
        }
    }

    static interface PopupWindowImpl
    {

        public abstract boolean getOverlapAnchor(PopupWindow popupwindow);

        public abstract int getWindowLayoutType(PopupWindow popupwindow);

        public abstract void setOverlapAnchor(PopupWindow popupwindow, boolean flag);

        public abstract void setWindowLayoutType(PopupWindow popupwindow, int i);

        public abstract void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k);
    }


    private PopupWindowCompat()
    {
    }

    public static boolean getOverlapAnchor(PopupWindow popupwindow)
    {
        return IMPL.getOverlapAnchor(popupwindow);
    }

    public static int getWindowLayoutType(PopupWindow popupwindow)
    {
        return IMPL.getWindowLayoutType(popupwindow);
    }

    public static void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
        IMPL.setOverlapAnchor(popupwindow, flag);
    }

    public static void setWindowLayoutType(PopupWindow popupwindow, int i)
    {
        IMPL.setWindowLayoutType(popupwindow, i);
    }

    public static void showAsDropDown(PopupWindow popupwindow, View view, int i, int j, int k)
    {
        IMPL.showAsDropDown(popupwindow, view, i, j, k);
    }

    static final PopupWindowImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 23)
            IMPL = new Api23PopupWindowImpl();
        else
        if(i >= 21)
            IMPL = new Api21PopupWindowImpl();
        else
        if(i >= 19)
            IMPL = new KitKatPopupWindowImpl();
        else
            IMPL = new BasePopupWindowImpl();
    }
}
