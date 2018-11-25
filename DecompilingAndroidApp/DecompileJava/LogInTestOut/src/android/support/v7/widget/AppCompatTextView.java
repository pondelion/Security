// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.widget.TextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper

public class AppCompatTextView extends TextView
    implements TintableBackgroundView
{

    public AppCompatTextView(Context context)
    {
        this(context, null);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x1010084);
    }

    public AppCompatTextView(Context context, AttributeSet attributeset, int i)
    {
        super(TintContextWrapper.wrap(context), attributeset, i);
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mTextHelper = AppCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged()
    {
        super.drawableStateChanged();
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.applySupportBackgroundTint();
        if(mTextHelper != null)
            mTextHelper.applyCompoundDrawablesTints();
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

    public void setBackgroundDrawable(Drawable drawable)
    {
        super.setBackgroundDrawable(drawable);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundDrawable(drawable);
    }

    public void setBackgroundResource(int i)
    {
        super.setBackgroundResource(i);
        if(mBackgroundTintHelper != null)
            mBackgroundTintHelper.onSetBackgroundResource(i);
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

    public void setTextAppearance(Context context, int i)
    {
        super.setTextAppearance(context, i);
        if(mTextHelper != null)
            mTextHelper.onSetTextAppearance(context, i);
    }

    private AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatTextHelper mTextHelper;
}
