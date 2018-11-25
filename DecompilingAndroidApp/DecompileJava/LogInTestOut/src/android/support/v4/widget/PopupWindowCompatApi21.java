// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.util.Log;
import android.widget.PopupWindow;
import java.lang.reflect.Field;

class PopupWindowCompatApi21
{

    PopupWindowCompatApi21()
    {
    }

    static boolean getOverlapAnchor(PopupWindow popupwindow)
    {
        if(sOverlapAnchorField == null)
            break MISSING_BLOCK_LABEL_32;
        boolean flag = ((Boolean)sOverlapAnchorField.get(popupwindow)).booleanValue();
        return flag;
        popupwindow;
        Log.i("PopupWindowCompatApi21", "Could not get overlap anchor field in PopupWindow", popupwindow);
        return false;
    }

    static void setOverlapAnchor(PopupWindow popupwindow, boolean flag)
    {
        if(sOverlapAnchorField == null)
            break MISSING_BLOCK_LABEL_17;
        sOverlapAnchorField.set(popupwindow, Boolean.valueOf(flag));
        return;
        popupwindow;
        Log.i("PopupWindowCompatApi21", "Could not set overlap anchor field in PopupWindow", popupwindow);
        return;
    }

    private static final String TAG = "PopupWindowCompatApi21";
    private static Field sOverlapAnchorField;

    static 
    {
        try
        {
            sOverlapAnchorField = android/widget/PopupWindow.getDeclaredField("mOverlapAnchor");
            sOverlapAnchorField.setAccessible(true);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            Log.i("PopupWindowCompatApi21", "Could not fetch mOverlapAnchor field from PopupWindow", nosuchfieldexception);
        }
    }
}
