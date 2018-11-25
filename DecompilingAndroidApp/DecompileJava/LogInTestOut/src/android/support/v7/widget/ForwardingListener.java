// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.view.menu.ShowableListMenu;
import android.view.*;

// Referenced classes of package android.support.v7.widget:
//            DropDownListView

public abstract class ForwardingListener
    implements android.view.View.OnTouchListener
{
    private class DisallowIntercept
        implements Runnable
    {

        public void run()
        {
            ViewParent viewparent = mSrc.getParent();
            if(viewparent != null)
                viewparent.requestDisallowInterceptTouchEvent(true);
        }

        final ForwardingListener this$0;

        DisallowIntercept()
        {
            this$0 = ForwardingListener.this;
            super();
        }
    }

    private class TriggerLongPress
        implements Runnable
    {

        public void run()
        {
            onLongPress();
        }

        final ForwardingListener this$0;

        TriggerLongPress()
        {
            this$0 = ForwardingListener.this;
            super();
        }
    }


    public ForwardingListener(View view)
    {
        mSrc = view;
        mScaledTouchSlop = ViewConfiguration.get(view.getContext()).getScaledTouchSlop();
        mLongPressTimeout = (mTapTimeout + ViewConfiguration.getLongPressTimeout()) / 2;
    }

    private void clearCallbacks()
    {
        if(mTriggerLongPress != null)
            mSrc.removeCallbacks(mTriggerLongPress);
        if(mDisallowIntercept != null)
            mSrc.removeCallbacks(mDisallowIntercept);
    }

    private boolean onTouchForwarded(MotionEvent motionevent)
    {
        boolean flag = true;
        View view = mSrc;
        Object obj = getPopup();
        if(obj != null && ((ShowableListMenu) (obj)).isShowing())
            if((obj = (DropDownListView)((ShowableListMenu) (obj)).getListView()) != null && ((DropDownListView) (obj)).isShown())
            {
                MotionEvent motionevent1 = MotionEvent.obtainNoHistory(motionevent);
                toGlobalMotionEvent(view, motionevent1);
                toLocalMotionEvent(((View) (obj)), motionevent1);
                boolean flag1 = ((DropDownListView) (obj)).onForwardedEvent(motionevent1, mActivePointerId);
                motionevent1.recycle();
                int i = MotionEventCompat.getActionMasked(motionevent);
                if(i != 1 && i != 3)
                    i = 1;
                else
                    i = 0;
                if(!flag1 || !i)
                    flag = false;
                return flag;
            }
        return false;
    }

    private boolean onTouchObserved(MotionEvent motionevent)
    {
        View view = mSrc;
        if(view.isEnabled()) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        switch(MotionEventCompat.getActionMasked(motionevent))
        {
        default:
            return false;

        case 0: // '\0'
            mActivePointerId = motionevent.getPointerId(0);
            if(mDisallowIntercept == null)
                mDisallowIntercept = new DisallowIntercept();
            view.postDelayed(mDisallowIntercept, mTapTimeout);
            if(mTriggerLongPress == null)
                mTriggerLongPress = new TriggerLongPress();
            view.postDelayed(mTriggerLongPress, mLongPressTimeout);
            return false;

        case 2: // '\002'
            int i = motionevent.findPointerIndex(mActivePointerId);
            if(i >= 0 && !pointInView(view, motionevent.getX(i), motionevent.getY(i), mScaledTouchSlop))
            {
                clearCallbacks();
                view.getParent().requestDisallowInterceptTouchEvent(true);
                return true;
            }
            break;

        case 1: // '\001'
        case 3: // '\003'
            clearCallbacks();
            return false;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    private static boolean pointInView(View view, float f, float f1, float f2)
    {
        return f >= -f2 && f1 >= -f2 && f < (float)(view.getRight() - view.getLeft()) + f2 && f1 < (float)(view.getBottom() - view.getTop()) + f2;
    }

    private boolean toGlobalMotionEvent(View view, MotionEvent motionevent)
    {
        int ai[] = mTmpLocation;
        view.getLocationOnScreen(ai);
        motionevent.offsetLocation(ai[0], ai[1]);
        return true;
    }

    private boolean toLocalMotionEvent(View view, MotionEvent motionevent)
    {
        int ai[] = mTmpLocation;
        view.getLocationOnScreen(ai);
        motionevent.offsetLocation(-ai[0], -ai[1]);
        return true;
    }

    public abstract ShowableListMenu getPopup();

    protected boolean onForwardingStarted()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if(showablelistmenu != null && !showablelistmenu.isShowing())
            showablelistmenu.show();
        return true;
    }

    protected boolean onForwardingStopped()
    {
        ShowableListMenu showablelistmenu = getPopup();
        if(showablelistmenu != null && showablelistmenu.isShowing())
            showablelistmenu.dismiss();
        return true;
    }

    void onLongPress()
    {
        clearCallbacks();
        View view;
        for(view = mSrc; !view.isEnabled() || view.isLongClickable() || !onForwardingStarted();)
            return;

        view.getParent().requestDisallowInterceptTouchEvent(true);
        long l = SystemClock.uptimeMillis();
        MotionEvent motionevent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        view.onTouchEvent(motionevent);
        motionevent.recycle();
        mForwarding = true;
    }

    public boolean onTouch(View view, MotionEvent motionevent)
    {
        boolean flag2;
        boolean flag3;
        flag2 = false;
        flag3 = mForwarding;
        if(!flag3) goto _L2; else goto _L1
_L1:
        boolean flag;
        if(onTouchForwarded(motionevent) || !onForwardingStopped())
            flag = true;
        else
            flag = false;
_L4:
label0:
        {
            mForwarding = flag;
            if(!flag)
            {
                flag = flag2;
                if(!flag3)
                    break label0;
            }
            flag = true;
        }
        return flag;
_L2:
        boolean flag1;
        if(onTouchObserved(motionevent) && onForwardingStarted())
            flag1 = true;
        else
            flag1 = false;
        flag = flag1;
        if(flag1)
        {
            long l = SystemClock.uptimeMillis();
            view = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
            mSrc.onTouchEvent(view);
            view.recycle();
            flag = flag1;
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private int mActivePointerId;
    private Runnable mDisallowIntercept;
    private boolean mForwarding;
    private final int mLongPressTimeout;
    private final float mScaledTouchSlop;
    final View mSrc;
    private final int mTapTimeout = ViewConfiguration.getTapTimeout();
    private final int mTmpLocation[] = new int[2];
    private Runnable mTriggerLongPress;
}
