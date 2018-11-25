// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.animation;

import android.view.View;

// Referenced classes of package android.support.v4.animation:
//            ValueAnimatorCompat

interface AnimatorProvider
{

    public abstract void clearInterpolator(View view);

    public abstract ValueAnimatorCompat emptyValueAnimator();
}
