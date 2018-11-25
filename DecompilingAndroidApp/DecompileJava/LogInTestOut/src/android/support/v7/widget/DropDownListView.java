// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.MotionEvent;
import android.view.View;

// Referenced classes of package android.support.v7.widget:
//            ListViewCompat

class DropDownListView extends ListViewCompat
{

    public DropDownListView(Context context, boolean flag)
    {
        super(context, null, android.support.v7.appcompat.R.attr.dropDownListViewStyle);
        mHijackFocus = flag;
        setCacheColorHint(0);
    }

    private void clearPressedItem()
    {
        mDrawsInPressedState = false;
        setPressed(false);
        drawableStateChanged();
        View view = getChildAt(mMotionPosition - getFirstVisiblePosition());
        if(view != null)
            view.setPressed(false);
        if(mClickAnimation != null)
        {
            mClickAnimation.cancel();
            mClickAnimation = null;
        }
    }

    private void clickPressedItem(View view, int i)
    {
        performItemClick(view, i, getItemIdAtPosition(i));
    }

    private void setPressedItem(View view, int i, float f, float f1)
    {
        mDrawsInPressedState = true;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            drawableHotspotChanged(f, f1);
        if(!isPressed())
            setPressed(true);
        layoutChildren();
        if(mMotionPosition != -1)
        {
            View view1 = getChildAt(mMotionPosition - getFirstVisiblePosition());
            if(view1 != null && view1 != view && view1.isPressed())
                view1.setPressed(false);
        }
        mMotionPosition = i;
        float f2 = view.getLeft();
        float f3 = view.getTop();
        if(android.os.Build.VERSION.SDK_INT >= 21)
            view.drawableHotspotChanged(f - f2, f1 - f3);
        if(!view.isPressed())
            view.setPressed(true);
        positionSelectorLikeTouchCompat(i, view, f, f1);
        setSelectorEnabled(false);
        refreshDrawableState();
    }

    public boolean hasFocus()
    {
        return mHijackFocus || super.hasFocus();
    }

    public boolean hasWindowFocus()
    {
        return mHijackFocus || super.hasWindowFocus();
    }

    public boolean isFocused()
    {
        return mHijackFocus || super.isFocused();
    }

    public boolean isInTouchMode()
    {
        return mHijackFocus && mListSelectionHidden || super.isInTouchMode();
    }

    public boolean onForwardedEvent(MotionEvent motionevent, int i)
    {
        boolean flag;
        int j;
        boolean flag1;
        boolean flag2;
        flag1 = true;
        flag2 = true;
        flag = false;
        j = MotionEventCompat.getActionMasked(motionevent);
        j;
        JVM INSTR tableswitch 1 3: default 44
    //                   1 117
    //                   2 120
    //                   3 109;
           goto _L1 _L2 _L3 _L4
_L1:
        flag1 = flag2;
        i = ((flag) ? 1 : 0);
_L10:
        if(!flag1 || i != 0)
            clearPressedItem();
        if(!flag1) goto _L6; else goto _L5
_L5:
        if(mScrollHelper == null)
            mScrollHelper = new ListViewAutoScrollHelper(this);
        mScrollHelper.setEnabled(true);
        mScrollHelper.onTouch(this, motionevent);
_L8:
        return flag1;
_L4:
        flag1 = false;
        i = ((flag) ? 1 : 0);
        continue; /* Loop/switch isn't completed */
_L2:
        flag1 = false;
_L3:
        int k = motionevent.findPointerIndex(i);
        if(k < 0)
        {
            flag1 = false;
            i = ((flag) ? 1 : 0);
        } else
        {
            i = (int)motionevent.getX(k);
            int l = (int)motionevent.getY(k);
            k = pointToPosition(i, l);
            if(k == -1)
            {
                i = 1;
            } else
            {
                View view = getChildAt(k - getFirstVisiblePosition());
                setPressedItem(view, k, i, l);
                boolean flag3 = true;
                i = ((flag) ? 1 : 0);
                flag1 = flag3;
                if(j == 1)
                {
                    clickPressedItem(view, k);
                    i = ((flag) ? 1 : 0);
                    flag1 = flag3;
                }
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(mScrollHelper == null) goto _L8; else goto _L7
_L7:
        mScrollHelper.setEnabled(false);
        return flag1;
        if(true) goto _L10; else goto _L9
_L9:
    }

    void setListSelectionHidden(boolean flag)
    {
        mListSelectionHidden = flag;
    }

    protected boolean touchModeDrawsInPressedStateCompat()
    {
        return mDrawsInPressedState || super.touchModeDrawsInPressedStateCompat();
    }

    private ViewPropertyAnimatorCompat mClickAnimation;
    private boolean mDrawsInPressedState;
    private boolean mHijackFocus;
    private boolean mListSelectionHidden;
    private ListViewAutoScrollHelper mScrollHelper;
}
