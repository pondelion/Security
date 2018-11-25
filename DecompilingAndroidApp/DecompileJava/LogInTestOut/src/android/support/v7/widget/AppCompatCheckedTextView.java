// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.CheckedTextView;

// Referenced classes of package android.support.v7.widget:
//            TintContextWrapper, AppCompatTextHelper, TintTypedArray

public class AppCompatCheckedTextView extends CheckedTextView
{

    public AppCompatCheckedTextView(Context context)
    {
        AppCompatCheckedTextView(context, null);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeset)
    {
        AppCompatCheckedTextView(context, attributeset, 0x10103c8);
    }

    public AppCompatCheckedTextView(Context context, AttributeSet attributeset, int i)
    {
        CheckedTextView(TintContextWrapper.wrap(context), attributeset, i);
        mTextHelper = AppCompatTextHelper.create(this);
        mTextHelper.loadFromAttributes(attributeset, i);
        mTextHelper.applyCompoundDrawablesTints();
        context = TintTypedArray.obtainStyledAttributes(getContext(), attributeset, TINT_ATTRS, i, 0);
        setCheckMarkDrawable(context.getDrawable(0));
        context.recycle();
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
        if(mTextHelper != null)
            mTextHelper.applyCompoundDrawablesTints();
    }

    public void setCheckMarkDrawable(int i)
    {
        setCheckMarkDrawable(AppCompatResources.getDrawable(getContext(), i));
    }

    public void setTextAppearance(Context context, int i)
    {
        setTextAppearance(context, i);
        if(mTextHelper != null)
            mTextHelper.onSetTextAppearance(context, i);
    }

    private static final int TINT_ATTRS[] = {
        0x1010108
    };
    private AppCompatTextHelper mTextHelper;

}
