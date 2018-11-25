// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.util.AttributeSet;
import android.util.TypedValue;

// Referenced classes of package android.support.v7.widget:
//            AppCompatDrawableManager

public class TintTypedArray
{

    private TintTypedArray(Context context, TypedArray typedarray)
    {
        mContext = context;
        mWrapped = typedarray;
    }

    public static TintTypedArray obtainStyledAttributes(Context context, int i, int ai[])
    {
        return new TintTypedArray(context, context.obtainStyledAttributes(i, ai));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeset, int ai[])
    {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeset, ai));
    }

    public static TintTypedArray obtainStyledAttributes(Context context, AttributeSet attributeset, int ai[], int i, int j)
    {
        return new TintTypedArray(context, context.obtainStyledAttributes(attributeset, ai, i, j));
    }

    public boolean getBoolean(int i, boolean flag)
    {
        return mWrapped.getBoolean(i, flag);
    }

    public int getChangingConfigurations()
    {
        return mWrapped.getChangingConfigurations();
    }

    public int getColor(int i, int j)
    {
        return mWrapped.getColor(i, j);
    }

    public ColorStateList getColorStateList(int i)
    {
        if(mWrapped.hasValue(i))
        {
            int j = mWrapped.getResourceId(i, 0);
            if(j != 0)
            {
                ColorStateList colorstatelist = AppCompatResources.getColorStateList(mContext, j);
                if(colorstatelist != null)
                    return colorstatelist;
            }
        }
        return mWrapped.getColorStateList(i);
    }

    public float getDimension(int i, float f)
    {
        return mWrapped.getDimension(i, f);
    }

    public int getDimensionPixelOffset(int i, int j)
    {
        return mWrapped.getDimensionPixelOffset(i, j);
    }

    public int getDimensionPixelSize(int i, int j)
    {
        return mWrapped.getDimensionPixelSize(i, j);
    }

    public Drawable getDrawable(int i)
    {
        if(mWrapped.hasValue(i))
        {
            int j = mWrapped.getResourceId(i, 0);
            if(j != 0)
                return AppCompatResources.getDrawable(mContext, j);
        }
        return mWrapped.getDrawable(i);
    }

    public Drawable getDrawableIfKnown(int i)
    {
        if(mWrapped.hasValue(i))
        {
            i = mWrapped.getResourceId(i, 0);
            if(i != 0)
                return AppCompatDrawableManager.get().getDrawable(mContext, i, true);
        }
        return null;
    }

    public float getFloat(int i, float f)
    {
        return mWrapped.getFloat(i, f);
    }

    public float getFraction(int i, int j, int k, float f)
    {
        return mWrapped.getFraction(i, j, k, f);
    }

    public int getIndex(int i)
    {
        return mWrapped.getIndex(i);
    }

    public int getIndexCount()
    {
        return mWrapped.getIndexCount();
    }

    public int getInt(int i, int j)
    {
        return mWrapped.getInt(i, j);
    }

    public int getInteger(int i, int j)
    {
        return mWrapped.getInteger(i, j);
    }

    public int getLayoutDimension(int i, int j)
    {
        return mWrapped.getLayoutDimension(i, j);
    }

    public int getLayoutDimension(int i, String s)
    {
        return mWrapped.getLayoutDimension(i, s);
    }

    public String getNonResourceString(int i)
    {
        return mWrapped.getNonResourceString(i);
    }

    public String getPositionDescription()
    {
        return mWrapped.getPositionDescription();
    }

    public int getResourceId(int i, int j)
    {
        return mWrapped.getResourceId(i, j);
    }

    public Resources getResources()
    {
        return mWrapped.getResources();
    }

    public String getString(int i)
    {
        return mWrapped.getString(i);
    }

    public CharSequence getText(int i)
    {
        return mWrapped.getText(i);
    }

    public CharSequence[] getTextArray(int i)
    {
        return mWrapped.getTextArray(i);
    }

    public int getType(int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return mWrapped.getType(i);
        if(mTypedValue == null)
            mTypedValue = new TypedValue();
        mWrapped.getValue(i, mTypedValue);
        return mTypedValue.type;
    }

    public boolean getValue(int i, TypedValue typedvalue)
    {
        return mWrapped.getValue(i, typedvalue);
    }

    public boolean hasValue(int i)
    {
        return mWrapped.hasValue(i);
    }

    public int length()
    {
        return mWrapped.length();
    }

    public TypedValue peekValue(int i)
    {
        return mWrapped.peekValue(i);
    }

    public void recycle()
    {
        mWrapped.recycle();
    }

    private final Context mContext;
    private TypedValue mTypedValue;
    private final TypedArray mWrapped;
}
