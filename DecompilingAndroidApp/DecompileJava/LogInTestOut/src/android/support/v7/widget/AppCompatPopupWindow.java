// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.support.v4.widget.PopupWindowCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.PopupWindow;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

class AppCompatPopupWindow extends PopupWindow
{

    public AppCompatPopupWindow(Context context, AttributeSet attributeset, int i)
    {
        PopupWindow(context, attributeset, i);
        init(context, attributeset, i, 0);
    }

    public AppCompatPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        PopupWindow(context, attributeset, i, j);
        init(context, attributeset, i, j);
    }

    private void init(Context context, AttributeSet attributeset, int i, int j)
    {
        context = TintTypedArray.obtainStyledAttributes(context, attributeset, android.support.v7.appcompat.R.styleable.PopupWindow, i, j);
        if(context.hasValue(android.support.v7.appcompat.R.styleable.PopupWindow_overlapAnchor))
            setSupportOverlapAnchor(context.getBoolean(android.support.v7.appcompat.R.styleable.PopupWindow_overlapAnchor, false));
        setBackgroundDrawable(context.getDrawable(android.support.v7.appcompat.R.styleable.PopupWindow_android_popupBackground));
        i = android.os.Build.VERSION.SDK_INT;
        if(j != 0 && i < 11 && context.hasValue(android.support.v7.appcompat.R.styleable.PopupWindow_android_popupAnimationStyle))
            setAnimationStyle(context.getResourceId(android.support.v7.appcompat.R.styleable.PopupWindow_android_popupAnimationStyle, -1));
        context.recycle();
        if(android.os.Build.VERSION.SDK_INT < 14)
            wrapOnScrollChangedListener(this);
    }

    private static void wrapOnScrollChangedListener(PopupWindow popupwindow)
    {
        try
        {
            Field field = android/widget/PopupWindow.getDeclaredField("mAnchor");
            field.setAccessible(true);
            Field field1 = android/widget/PopupWindow.getDeclaredField("mOnScrollChangedListener");
            field1.setAccessible(true);
            field1.set(popupwindow, new android.view.ViewTreeObserver.OnScrollChangedListener(field, popupwindow, (android.view.ViewTreeObserver.OnScrollChangedListener)field1.get(popupwindow)) {

                public void onScrollChanged()
                {
                    WeakReference weakreference = (WeakReference)fieldAnchor.get(popup);
                    if(weakreference == null)
                        break MISSING_BLOCK_LABEL_38;
                    if(weakreference.get() == null)
                        return;
                    try
                    {
                        originalListener.onScrollChanged();
                        return;
                    }
                    catch(IllegalAccessException illegalaccessexception) { }
                }

                final Field val$fieldAnchor;
                final android.view.ViewTreeObserver.OnScrollChangedListener val$originalListener;
                final PopupWindow val$popup;

            
            {
                fieldAnchor = field;
                popup = popupwindow;
                originalListener = onscrollchangedlistener;
                Object();
            }
            }
);
            return;
        }
        // Misplaced declaration of an exception variable
        catch(PopupWindow popupwindow)
        {
            Log.d("AppCompatPopupWindow", "Exception while installing workaround OnScrollChangedListener", popupwindow);
        }
    }

    public boolean getSupportOverlapAnchor()
    {
        if(COMPAT_OVERLAP_ANCHOR)
            return mOverlapAnchor;
        else
            return PopupWindowCompat.getOverlapAnchor(this);
    }

    public void setSupportOverlapAnchor(boolean flag)
    {
        if(COMPAT_OVERLAP_ANCHOR)
        {
            mOverlapAnchor = flag;
            return;
        } else
        {
            PopupWindowCompat.setOverlapAnchor(this, flag);
            return;
        }
    }

    public void showAsDropDown(View view, int i, int j)
    {
        int k = j;
        if(COMPAT_OVERLAP_ANCHOR)
        {
            k = j;
            if(mOverlapAnchor)
                k = j - view.getHeight();
        }
        showAsDropDown(view, i, k);
    }

    public void showAsDropDown(View view, int i, int j, int k)
    {
        int l = j;
        if(COMPAT_OVERLAP_ANCHOR)
        {
            l = j;
            if(mOverlapAnchor)
                l = j - view.getHeight();
        }
        showAsDropDown(view, i, l, k);
    }

    public void update(View view, int i, int j, int k, int l)
    {
        int i1 = j;
        if(COMPAT_OVERLAP_ANCHOR)
        {
            i1 = j;
            if(mOverlapAnchor)
                i1 = j - view.getHeight();
        }
        update(view, i, i1, k, l);
    }

    private static final boolean COMPAT_OVERLAP_ANCHOR;
    private static final String TAG = "AppCompatPopupWindow";
    private boolean mOverlapAnchor;

    static 
    {
        boolean flag;
        if(android.os.Build.VERSION.SDK_INT < 21)
            flag = true;
        else
            flag = false;
        COMPAT_OVERLAP_ANCHOR = flag;
    }
}
