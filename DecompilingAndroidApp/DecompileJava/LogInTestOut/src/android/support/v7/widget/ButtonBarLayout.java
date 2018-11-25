// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class ButtonBarLayout extends LinearLayout
{

    public ButtonBarLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mLastWidthSize = -1;
        boolean flag;
        if(ConfigurationHelper.getScreenHeightDp(getResources()) >= 320)
            flag = true;
        else
            flag = false;
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ButtonBarLayout);
        mAllowStacking = context.getBoolean(android.support.v7.appcompat.R.styleable.ButtonBarLayout_allowStacking, flag);
        context.recycle();
    }

    private boolean isStacked()
    {
        return getOrientation() == 1;
    }

    private void setStacked(boolean flag)
    {
        int i;
        View view;
        if(flag)
            i = 1;
        else
            i = 0;
        setOrientation(i);
        if(flag)
            i = 5;
        else
            i = 80;
        setGravity(i);
        view = findViewById(android.support.v7.appcompat.R.id.spacer);
        if(view != null)
        {
            if(flag)
                i = 8;
            else
                i = 4;
            view.setVisibility(i);
        }
        for(i = getChildCount() - 2; i >= 0; i--)
            bringChildToFront(getChildAt(i));

    }

    protected void onMeasure(int i, int j)
    {
        int k;
        boolean flag2;
        int j1;
        flag2 = false;
        boolean flag3 = false;
        j1 = android.view.View.MeasureSpec.getSize(i);
        if(mAllowStacking)
        {
            if(j1 > mLastWidthSize && isStacked())
                setStacked(false);
            mLastWidthSize = j1;
        }
        boolean flag = false;
        boolean flag1;
        if(!isStacked() && android.view.View.MeasureSpec.getMode(i) == 0x40000000)
        {
            k = android.view.View.MeasureSpec.makeMeasureSpec(j1, 0x80000000);
            flag = true;
        } else
        {
            k = i;
        }
        super.onMeasure(k, j);
        flag1 = flag;
        if(!mAllowStacking) goto _L2; else goto _L1
_L1:
        flag1 = flag;
        if(isStacked()) goto _L2; else goto _L3
_L3:
        if(android.os.Build.VERSION.SDK_INT < 11) goto _L5; else goto _L4
_L4:
        k = ((flag3) ? 1 : 0);
        if((ViewCompat.getMeasuredWidthAndState(this) & 0xff000000) == 0x1000000)
            k = 1;
_L7:
        flag1 = flag;
        if(k != 0)
        {
            setStacked(true);
            flag1 = true;
        }
_L2:
        if(flag1)
            super.onMeasure(i, j);
        return;
_L5:
        int l = 0;
        k = 0;
        for(int i1 = getChildCount(); k < i1; k++)
            l += getChildAt(k).getMeasuredWidth();

        k = ((flag2) ? 1 : 0);
        if(getPaddingLeft() + l + getPaddingRight() > j1)
            k = 1;
        if(true) goto _L7; else goto _L6
_L6:
    }

    public void setAllowStacking(boolean flag)
    {
        if(mAllowStacking != flag)
        {
            mAllowStacking = flag;
            if(!mAllowStacking && getOrientation() == 1)
                setStacked(false);
            requestLayout();
        }
    }

    private static final int ALLOW_STACKING_MIN_HEIGHT_DP = 320;
    private boolean mAllowStacking;
    private int mLastWidthSize;
}
