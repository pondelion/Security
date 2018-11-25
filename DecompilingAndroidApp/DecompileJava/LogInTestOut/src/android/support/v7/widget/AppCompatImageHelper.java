// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.drawable.RippleDrawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.widget.ImageView;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray, DrawableUtils

public class AppCompatImageHelper
{

    public AppCompatImageHelper(ImageView imageview)
    {
        mView = imageview;
    }

    boolean hasOverlappingRendering()
    {
        android.graphics.drawable.Drawable drawable = mView.getBackground();
        return android.os.Build.VERSION.SDK_INT < 21 || !(drawable instanceof RippleDrawable);
    }

    public void loadFromAttributes(AttributeSet attributeset, int i)
    {
        AttributeSet attributeset1;
        AttributeSet attributeset2;
        Object obj;
        obj = null;
        attributeset2 = null;
        attributeset1 = obj;
        android.graphics.drawable.Drawable drawable1 = mView.getDrawable();
        android.graphics.drawable.Drawable drawable;
        drawable = drawable1;
        if(drawable1 != null)
            break MISSING_BLOCK_LABEL_115;
        attributeset1 = obj;
        attributeset = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, android.support.v7.appcompat.R.styleable.AppCompatImageView, i, 0);
        attributeset1 = attributeset;
        i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.AppCompatImageView_srcCompat, -1);
        attributeset2 = attributeset;
        drawable = drawable1;
        if(i == -1)
            break MISSING_BLOCK_LABEL_115;
        attributeset1 = attributeset;
        drawable1 = AppCompatResources.getDrawable(mView.getContext(), i);
        attributeset2 = attributeset;
        drawable = drawable1;
        if(drawable1 == null)
            break MISSING_BLOCK_LABEL_115;
        attributeset1 = attributeset;
        mView.setImageDrawable(drawable1);
        drawable = drawable1;
        attributeset2 = attributeset;
        if(drawable == null)
            break MISSING_BLOCK_LABEL_128;
        attributeset1 = attributeset2;
        DrawableUtils.fixDrawable(drawable);
        if(attributeset2 != null)
            attributeset2.recycle();
        return;
        attributeset;
        if(attributeset1 != null)
            attributeset1.recycle();
        throw attributeset;
    }

    public void setImageResource(int i)
    {
        if(i != 0)
        {
            android.graphics.drawable.Drawable drawable = AppCompatResources.getDrawable(mView.getContext(), i);
            if(drawable != null)
                DrawableUtils.fixDrawable(drawable);
            mView.setImageDrawable(drawable);
            return;
        } else
        {
            mView.setImageDrawable(null);
            return;
        }
    }

    private final ImageView mView;
}
