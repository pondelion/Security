// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.MultiAutoCompleteTextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, TintTypedArray, AppCompatBackgroundHelper, AppCompatTextHelper

public class AppCompatMultiAutoCompleteTextView extends MultiAutoCompleteTextView
    implements TintableBackgroundView
{

    public AppCompatMultiAutoCompleteTextView(Context context)
    {
        AppCompatMultiAutoCompleteTextView(context, null);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeset)
    {
        AppCompatMultiAutoCompleteTextView(context, attributeset, android.support.v7.appcompat.R.attr.autoCompleteTextViewStyle);
    }

    public AppCompatMultiAutoCompleteTextView(Context context, AttributeSet attributeset, int i)
    {
        MultiAutoCompleteTextView(TintContextWrapper.wrap(context), attributeset, i);
        context = TintTypedArray.obtainStyledAttributes(getContext(), attributeset, TINT_ATTRS, i, 0);
        if(context.hasValue(0))
            setDropDownBackgroundDrawable(context.getDrawable(0));
        context.recycle();
        mBackgroundTintHelper = new AppCompatBackgroundHelper(this);
        mBackgroundTintHelper.loadFromAttributes(attributeset, i);
        mTextHelper = AppCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
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

    public void setDropDownBackgroundResource(int i)
    {
        setDropDownBackgroundDrawable(AppCompatResources.getDrawable(getContext(), i));
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
        setTextAppearance(context, i);
        if(mTextHelper != null)
            mTextHelper.onSetTextAppearance(context, i);
    }

    private static final int TINT_ATTRS[] = {
        0x1010176
    };
    private AppCompatBackgroundHelper mBackgroundTintHelper;
    private AppCompatTextHelper mTextHelper;

}
