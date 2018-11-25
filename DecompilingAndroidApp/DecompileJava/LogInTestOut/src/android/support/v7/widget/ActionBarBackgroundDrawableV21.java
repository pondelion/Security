// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.Outline;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v7.widget:
//            ActionBarBackgroundDrawable, ActionBarContainer

class ActionBarBackgroundDrawableV21 extends ActionBarBackgroundDrawable
{

    public ActionBarBackgroundDrawableV21(ActionBarContainer actionbarcontainer)
    {
        super(actionbarcontainer);
    }

    public void getOutline(Outline outline)
    {
        if(mContainer.mIsSplit)
        {
            if(mContainer.mSplitBackground != null)
                mContainer.mSplitBackground.getOutline(outline);
        } else
        if(mContainer.mBackground != null)
        {
            mContainer.mBackground.getOutline(outline);
            return;
        }
    }
}
