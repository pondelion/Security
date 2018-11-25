// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;

// Referenced classes of package android.support.v4.view:
//            ViewCompatHC

class ViewCompatLollipop
{
    public static interface OnApplyWindowInsetsListenerBridge
    {

        public abstract Object onApplyWindowInsets(View view, Object obj);
    }


    ViewCompatLollipop()
    {
    }

    public static Object dispatchApplyWindowInsets(View view, Object obj)
    {
        WindowInsets windowinsets = (WindowInsets)obj;
        view = view.dispatchApplyWindowInsets(windowinsets);
        if(view != windowinsets)
            obj = new WindowInsets(view);
        return obj;
    }

    public static boolean dispatchNestedFling(View view, float f, float f1, boolean flag)
    {
        return view.dispatchNestedFling(f, f1, flag);
    }

    public static boolean dispatchNestedPreFling(View view, float f, float f1)
    {
        return view.dispatchNestedPreFling(f, f1);
    }

    public static boolean dispatchNestedPreScroll(View view, int i, int j, int ai[], int ai1[])
    {
        return view.dispatchNestedPreScroll(i, j, ai, ai1);
    }

    public static boolean dispatchNestedScroll(View view, int i, int j, int k, int l, int ai[])
    {
        return view.dispatchNestedScroll(i, j, k, l, ai);
    }

    static ColorStateList getBackgroundTintList(View view)
    {
        return view.getBackgroundTintList();
    }

    static android.graphics.PorterDuff.Mode getBackgroundTintMode(View view)
    {
        return view.getBackgroundTintMode();
    }

    public static float getElevation(View view)
    {
        return view.getElevation();
    }

    private static Rect getEmptyTempRect()
    {
        if(sThreadLocalRect == null)
            sThreadLocalRect = new ThreadLocal();
        Rect rect1 = (Rect)sThreadLocalRect.get();
        Rect rect = rect1;
        if(rect1 == null)
        {
            rect = new Rect();
            sThreadLocalRect.set(rect);
        }
        rect.setEmpty();
        return rect;
    }

    public static String getTransitionName(View view)
    {
        return view.getTransitionName();
    }

    public static float getTranslationZ(View view)
    {
        return view.getTranslationZ();
    }

    public static float getZ(View view)
    {
        return view.getZ();
    }

    public static boolean hasNestedScrollingParent(View view)
    {
        return view.hasNestedScrollingParent();
    }

    public static boolean isImportantForAccessibility(View view)
    {
        return view.isImportantForAccessibility();
    }

    public static boolean isNestedScrollingEnabled(View view)
    {
        return view.isNestedScrollingEnabled();
    }

    static void offsetLeftAndRight(View view, int i)
    {
        Rect rect = getEmptyTempRect();
        boolean flag = false;
        android.view.ViewParent viewparent = view.getParent();
        if(viewparent instanceof View)
        {
            View view1 = (View)viewparent;
            rect.set(view1.getLeft(), view1.getTop(), view1.getRight(), view1.getBottom());
            if(!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                flag = true;
            else
                flag = false;
        }
        ViewCompatHC.offsetLeftAndRight(view, i);
        if(flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            ((View)viewparent).invalidate(rect);
    }

    static void offsetTopAndBottom(View view, int i)
    {
        Rect rect = getEmptyTempRect();
        boolean flag = false;
        android.view.ViewParent viewparent = view.getParent();
        if(viewparent instanceof View)
        {
            View view1 = (View)viewparent;
            rect.set(view1.getLeft(), view1.getTop(), view1.getRight(), view1.getBottom());
            if(!rect.intersects(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
                flag = true;
            else
                flag = false;
        }
        ViewCompatHC.offsetTopAndBottom(view, i);
        if(flag && rect.intersect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom()))
            ((View)viewparent).invalidate(rect);
    }

    public static Object onApplyWindowInsets(View view, Object obj)
    {
        WindowInsets windowinsets = (WindowInsets)obj;
        view = view.onApplyWindowInsets(windowinsets);
        if(view != windowinsets)
            obj = new WindowInsets(view);
        return obj;
    }

    public static void requestApplyInsets(View view)
    {
        view.requestApplyInsets();
    }

    static void setBackgroundTintList(View view, ColorStateList colorstatelist)
    {
        view.setBackgroundTintList(colorstatelist);
        if(android.os.Build.VERSION.SDK_INT == 21)
        {
            colorstatelist = view.getBackground();
            boolean flag;
            if(view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null)
                flag = true;
            else
                flag = false;
            if(colorstatelist != null && flag)
            {
                if(colorstatelist.isStateful())
                    colorstatelist.setState(view.getDrawableState());
                view.setBackground(colorstatelist);
            }
        }
    }

    static void setBackgroundTintMode(View view, android.graphics.PorterDuff.Mode mode)
    {
        view.setBackgroundTintMode(mode);
        if(android.os.Build.VERSION.SDK_INT == 21)
        {
            mode = view.getBackground();
            boolean flag;
            if(view.getBackgroundTintList() != null && view.getBackgroundTintMode() != null)
                flag = true;
            else
                flag = false;
            if(mode != null && flag)
            {
                if(mode.isStateful())
                    mode.setState(view.getDrawableState());
                view.setBackground(mode);
            }
        }
    }

    public static void setElevation(View view, float f)
    {
        view.setElevation(f);
    }

    public static void setNestedScrollingEnabled(View view, boolean flag)
    {
        view.setNestedScrollingEnabled(flag);
    }

    public static void setOnApplyWindowInsetsListener(View view, OnApplyWindowInsetsListenerBridge onapplywindowinsetslistenerbridge)
    {
        if(onapplywindowinsetslistenerbridge == null)
        {
            view.setOnApplyWindowInsetsListener(null);
            return;
        } else
        {
            view.setOnApplyWindowInsetsListener(new android.view.View.OnApplyWindowInsetsListener(onapplywindowinsetslistenerbridge) {

                public WindowInsets onApplyWindowInsets(View view1, WindowInsets windowinsets)
                {
                    return (WindowInsets)bridge.onApplyWindowInsets(view1, windowinsets);
                }

                final OnApplyWindowInsetsListenerBridge val$bridge;

            
            {
                bridge = onapplywindowinsetslistenerbridge;
                super();
            }
            }
);
            return;
        }
    }

    public static void setTransitionName(View view, String s)
    {
        view.setTransitionName(s);
    }

    public static void setTranslationZ(View view, float f)
    {
        view.setTranslationZ(f);
    }

    public static void setZ(View view, float f)
    {
        view.setZ(f);
    }

    public static boolean startNestedScroll(View view, int i)
    {
        return view.startNestedScroll(i);
    }

    public static void stopNestedScroll(View view)
    {
        view.stopNestedScroll();
    }

    private static ThreadLocal sThreadLocalRect;
}
