// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.view.TintableBackgroundView;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatBackgroundHelper, AppCompatTextHelper

public class AppCompatButton extends Button
    implements TintableBackgroundView
{

    public AppCompatButton(Context context)
    {
        AppCompatButton(context, null);
    }

    public AppCompatButton(Context context, AttributeSet attributeset)
    {
        AppCompatButton(context, attributeset, android.support.v7.appcompat.R.attr.buttonStyle);
    }

    public AppCompatButton(Context context, AttributeSet attributeset, int i)
    {
        Button(TintContextWrapper.wrap(context), attributeset, i);
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

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        onInitializeAccessibilityEvent(accessibilityevent);
        accessibilityevent.setClassName(android/widget/Button.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilitynodeinfo)
    {
        onInitializeAccessibilityNodeInfo(accessibilitynodeinfo);
        accessibilitynodeinfo.setClassName(android/widget/Button.getName());
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

    public void setSupportAllCaps(boolean flag)
    {
        if(mTextHelper != null)
            mTextHelper.setAllCaps(flag);
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

    private final AppCompatBackgroundHelper mBackgroundTintHelper;
    private final AppCompatTextHelper mTextHelper;
}
