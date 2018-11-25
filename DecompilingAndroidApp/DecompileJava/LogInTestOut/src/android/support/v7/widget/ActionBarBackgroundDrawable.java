// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.widget:
//            ActionBarContainer

class ActionBarBackgroundDrawable extends Drawable
{

    public ActionBarBackgroundDrawable(ActionBarContainer actionbarcontainer)
    {
        mContainer = actionbarcontainer;
    }

    public void draw(Canvas canvas)
    {
        if(mContainer.mIsSplit)
        {
            if(mContainer.mSplitBackground != null)
                mContainer.mSplitBackground.draw(canvas);
        } else
        {
            if(mContainer.mBackground != null)
                mContainer.mBackground.draw(canvas);
            if(mContainer.mStackedBackground != null && mContainer.mIsStacked)
            {
                mContainer.mStackedBackground.draw(canvas);
                return;
            }
        }
    }

    public int getOpacity()
    {
        return 0;
    }

    public void setAlpha(int i)
    {
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
    }

    final ActionBarContainer mContainer;
}
