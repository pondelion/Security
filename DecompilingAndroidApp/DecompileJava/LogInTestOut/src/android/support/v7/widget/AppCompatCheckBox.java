// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.TintableCompoundButton;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CheckBox;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatCompoundButtonHelper

public class AppCompatCheckBox extends CheckBox
    implements TintableCompoundButton
{

    public AppCompatCheckBox(Context context)
    {
        AppCompatCheckBox(context, null);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeset)
    {
        AppCompatCheckBox(context, attributeset, android.support.v7.appcompat.R.attr.checkboxStyle);
    }

    public AppCompatCheckBox(Context context, AttributeSet attributeset, int i)
    {
        CheckBox(TintContextWrapper.wrap(context), attributeset, i);
        mCompoundButtonHelper = new AppCompatCompoundButtonHelper(this);
        mCompoundButtonHelper.loadFromAttributes(attributeset, i);
    }

    public int getCompoundPaddingLeft()
    {
        int j = getCompoundPaddingLeft();
        int i = j;
        if(mCompoundButtonHelper != null)
            i = mCompoundButtonHelper.getCompoundPaddingLeft(j);
        return i;
    }

    public ColorStateList getSupportButtonTintList()
    {
        if(mCompoundButtonHelper != null)
            return mCompoundButtonHelper.getSupportButtonTintList();
        else
            return null;
    }

    public android.graphics.PorterDuff.Mode getSupportButtonTintMode()
    {
        if(mCompoundButtonHelper != null)
            return mCompoundButtonHelper.getSupportButtonTintMode();
        else
            return null;
    }

    public void setButtonDrawable(int i)
    {
        setButtonDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setButtonDrawable(Drawable drawable)
    {
        setButtonDrawable(drawable);
        if(mCompoundButtonHelper != null)
            mCompoundButtonHelper.onSetButtonDrawable();
    }

    public void setSupportButtonTintList(ColorStateList colorstatelist)
    {
        if(mCompoundButtonHelper != null)
            mCompoundButtonHelper.setSupportButtonTintList(colorstatelist);
    }

    public void setSupportButtonTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mCompoundButtonHelper != null)
            mCompoundButtonHelper.setSupportButtonTintMode(mode);
    }

    private AppCompatCompoundButtonHelper mCompoundButtonHelper;
}
