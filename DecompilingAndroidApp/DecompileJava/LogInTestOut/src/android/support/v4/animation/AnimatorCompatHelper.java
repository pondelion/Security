// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.animation;

import android.view.View;

// Referenced classes of package android.support.v4.animation:
//            HoneycombMr1AnimatorCompatProvider, GingerbreadAnimatorCompatProvider, AnimatorProvider, ValueAnimatorCompat

public final class AnimatorCompatHelper
{

    private AnimatorCompatHelper()
    {
    }

    public static void clearInterpolator(View view)
    {
        IMPL.clearInterpolator(view);
    }

    public static ValueAnimatorCompat emptyValueAnimator()
    {
        return IMPL.emptyValueAnimator();
    }

    private static final AnimatorProvider IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 12)
            IMPL = new HoneycombMr1AnimatorCompatProvider();
        else
            IMPL = new GingerbreadAnimatorCompatProvider();
    }
}
