// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatProgressBarHelper, TintTypedArray, DrawableUtils

class AppCompatSeekBarHelper extends AppCompatProgressBarHelper
{

    AppCompatSeekBarHelper(SeekBar seekbar)
    {
        AppCompatProgressBarHelper(seekbar);
        mTickMarkTintList = null;
        mTickMarkTintMode = null;
        mHasTickMarkTint = false;
        mHasTickMarkTintMode = false;
        mView = seekbar;
    }

    private void applyTickMarkTint()
    {
        if(mTickMark != null && (mHasTickMarkTint || mHasTickMarkTintMode))
        {
            mTickMark = DrawableCompat.wrap(mTickMark.mutate());
            if(mHasTickMarkTint)
                DrawableCompat.setTintList(mTickMark, mTickMarkTintList);
            if(mHasTickMarkTintMode)
                DrawableCompat.setTintMode(mTickMark, mTickMarkTintMode);
            if(mTickMark.isStateful())
                mTickMark.setState(mView.getDrawableState());
        }
    }

    void drawTickMarks(Canvas canvas)
    {
        int j = 1;
        if(mTickMark != null)
        {
            int k = mView.getMax();
            if(k > 1)
            {
                int i = mTickMark.getIntrinsicWidth();
                int l = mTickMark.getIntrinsicHeight();
                float f;
                if(i >= 0)
                    i /= 2;
                else
                    i = 1;
                if(l >= 0)
                    j = l / 2;
                mTickMark.setBounds(-i, -j, i, j);
                f = (float)(mView.getWidth() - mView.getPaddingLeft() - mView.getPaddingRight()) / (float)k;
                j = canvas.save();
                canvas.translate(mView.getPaddingLeft(), mView.getHeight() / 2);
                for(i = 0; i <= k; i++)
                {
                    mTickMark.draw(canvas);
                    canvas.translate(f, 0.0F);
                }

                canvas.restoreToCount(j);
            }
        }
    }

    void drawableStateChanged()
    {
        Drawable drawable = mTickMark;
        if(drawable != null && drawable.isStateful() && drawable.setState(mView.getDrawableState()))
            mView.invalidateDrawable(drawable);
    }

    Drawable getTickMark()
    {
        return mTickMark;
    }

    ColorStateList getTickMarkTintList()
    {
        return mTickMarkTintList;
    }

    android.graphics.PorterDuff.Mode getTickMarkTintMode()
    {
        return mTickMarkTintMode;
    }

    void jumpDrawablesToCurrentState()
    {
        if(mTickMark != null)
            mTickMark.jumpToCurrentState();
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        loadFromAttributes(attributeset, i);
        attributeset = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, android.support.v7.appcompat.R.styleable.AppCompatSeekBar, i, 0);
        Drawable drawable = attributeset.getDrawableIfKnown(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_android_thumb);
        if(drawable != null)
            mView.setThumb(drawable);
        setTickMark(attributeset.getDrawable(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMark));
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTintMode))
        {
            mTickMarkTintMode = DrawableUtils.parseTintMode(attributeset.getInt(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTintMode, -1), mTickMarkTintMode);
            mHasTickMarkTintMode = true;
        }
        if(attributeset.hasValue(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTint))
        {
            mTickMarkTintList = attributeset.getColorStateList(android.support.v7.appcompat.R.styleable.AppCompatSeekBar_tickMarkTint);
            mHasTickMarkTint = true;
        }
        attributeset.recycle();
        applyTickMarkTint();
    }

    void setTickMark(Drawable drawable)
    {
        if(mTickMark != null)
            mTickMark.setCallback(null);
        mTickMark = drawable;
        if(drawable != null)
        {
            drawable.setCallback(mView);
            DrawableCompat.setLayoutDirection(drawable, ViewCompat.getLayoutDirection(mView));
            if(drawable.isStateful())
                drawable.setState(mView.getDrawableState());
            applyTickMarkTint();
        }
        mView.invalidate();
    }

    void setTickMarkTintList(ColorStateList colorstatelist)
    {
        mTickMarkTintList = colorstatelist;
        mHasTickMarkTint = true;
        applyTickMarkTint();
    }

    void setTickMarkTintMode(android.graphics.PorterDuff.Mode mode)
    {
        mTickMarkTintMode = mode;
        mHasTickMarkTintMode = true;
        applyTickMarkTint();
    }

    private boolean mHasTickMarkTint;
    private boolean mHasTickMarkTintMode;
    private Drawable mTickMark;
    private ColorStateList mTickMarkTintList;
    private android.graphics.PorterDuff.Mode mTickMarkTintMode;
    private final SeekBar mView;
}
