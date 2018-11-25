// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.widget.ImageButton;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatImageHelper

public class AppCompatImageButton extends ImageButton
    implements TintableBackgroundView
{

    public AppCompatImageButton(Context context)
    {
        AppCompatImageButton(context, null);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeset)
    {
        AppCompatImageButton(context, attributeset, android.support.v7.appcompat.R.attr.imageButtonStyle);
    }

    public AppCompatImageButton(Context context, AttributeSet attributeset, int i)
    {
        ImageButton(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mImageHelper = new AppCompatImageHelper(this);
        mImageHelper.loadFromAttributes(attributeset, i);
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
    }

    public ColorStateList getSupportBackgroundTintList()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintList();
        else
            return null;
    }

    public android.graphics.PorterDuff.Mode getSupportBackgroundTintMode()
    {
        if(mBackgroundTintHelper != null)
            return mBackgroundTintHelper.getSupportBackgroundTintMode();
        else
            return null;
    }

    public boolean hasOverlappingRendering()
    {
        return mImageHelper.hasOverlappingRendering() && hasOverlappingRendering();
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        setBackgroundDrawable(drawable);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i)
    {
        setBackgroundResource(i);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundResource(i);
    }

    public void setImageResource(int i)
    {
        mImageHelper.setImageResource(i);
    }

    public void setSupportBackgroundTintList(ColorStateList colorstatelist)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintList(colorstatelist);
    }

    public void setSupportBackgroundTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.setSupportBackgroundTintMode(mode);
    }

    private AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatImageHelper mImageHelper;
}
