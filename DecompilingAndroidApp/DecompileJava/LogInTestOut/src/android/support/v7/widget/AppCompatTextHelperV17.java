// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            AppCompatTextHelper, AppCompatDrawableManager, TintInfo

class AppCompatTextHelperV17 extends AppCompatTextHelper
{

    AppCompatTextHelperV17(TextView textview)
    {
        super(textview);
    }

    void applyCompoundDrawablesTints()
    {
        super.applyCompoundDrawablesTints();
        if(mDrawableStartTint != null || mDrawableEndTint != null)
        {
            android.graphics.drawable.Drawable adrawable[] = mView.getCompoundDrawablesRelative();
            applyCompoundDrawableTint(adrawable[0], mDrawableStartTint);
            applyCompoundDrawableTint(adrawable[2], mDrawableEndTint);
        }
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        super.loadFromAttributes(attributeset, i);
        Context context = mView.getContext();
        AppCompatDrawableManager appcompatdrawablemanager = AppCompatDrawableManager.get();
        attributeset = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.AppCompatTextHelper, i, 0);
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart))
            mDrawableStartTint = createTintInfo(context, appcompatdrawablemanager, attributeset.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableStart, 0));
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd))
            mDrawableEndTint = createTintInfo(context, appcompatdrawablemanager, attributeset.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatTextHelper_android_drawableEnd, 0));
        attributeset.recycle();
    }

    private TintInfo mDrawableEndTint;
    private TintInfo mDrawableStartTint;
}
