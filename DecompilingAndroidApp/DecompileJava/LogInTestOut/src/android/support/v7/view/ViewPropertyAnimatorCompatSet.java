// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view;

import android.support.v4.view.*;
import android.view.View;
import android.view.animation.Interpolator;
import java.util.ArrayList;
import java.util.Iterator;

public class ViewPropertyAnimatorCompatSet
{

    public ViewPropertyAnimatorCompatSet()
    {
        mDuration = -1L;
    }

    public void cancel()
    {
        if(!mIsStarted)
            return;
        for(Iterator iterator = mAnimators.iterator(); iterator.hasNext(); ((ViewPropertyAnimatorCompat)iterator.next()).cancel());
        mIsStarted = false;
    }

    void onAnimationsEnded()
    {
        mIsStarted = false;
    }

    public ViewPropertyAnimatorCompatSet play(ViewPropertyAnimatorCompat viewpropertyanimatorcompat)
    {
        if(!mIsStarted)
            mAnimators.add(viewpropertyanimatorcompat);
        return this;
    }

    public ViewPropertyAnimatorCompatSet playSequentially(ViewPropertyAnimatorCompat viewpropertyanimatorcompat, ViewPropertyAnimatorCompat viewpropertyanimatorcompat1)
    {
        mAnimators.add(viewpropertyanimatorcompat);
        viewpropertyanimatorcompat1.setStartDelay(viewpropertyanimatorcompat.getDuration());
        mAnimators.add(viewpropertyanimatorcompat1);
        return this;
    }

    public ViewPropertyAnimatorCompatSet setDuration(long l)
    {
        if(!mIsStarted)
            mDuration = l;
        return this;
    }

    public ViewPropertyAnimatorCompatSet setInterpolator(Interpolator interpolator)
    {
        if(!mIsStarted)
            mInterpolator = interpolator;
        return this;
    }

    public ViewPropertyAnimatorCompatSet setListener(ViewPropertyAnimatorListener viewpropertyanimatorlistener)
    {
        if(!mIsStarted)
            mListener = viewpropertyanimatorlistener;
        return this;
    }

    public void start()
    {
        if(mIsStarted)
            return;
        ViewPropertyAnimatorCompat viewpropertyanimatorcompat;
        for(Iterator iterator = mAnimators.iterator(); iterator.hasNext(); viewpropertyanimatorcompat.start())
        {
            viewpropertyanimatorcompat = (ViewPropertyAnimatorCompat)iterator.next();
            if(mDuration >= 0L)
                viewpropertyanimatorcompat.setDuration(mDuration);
            if(mInterpolator != null)
                viewpropertyanimatorcompat.setInterpolator(mInterpolator);
            if(mListener != null)
                viewpropertyanimatorcompat.setListener(mProxyListener);
        }

        mIsStarted = true;
    }

    final ArrayList mAnimators = new ArrayList();
    private long mDuration;
    private Interpolator mInterpolator;
    private boolean mIsStarted;
    ViewPropertyAnimatorListener mListener;
    private final ViewPropertyAnimatorListenerAdapter mProxyListener = new ViewPropertyAnimatorListenerAdapter() {

        public void onAnimationEnd(View view)
        {
            int i = mProxyEndCount + 1;
            mProxyEndCount = i;
            if(i == mAnimators.size())
            {
                if(mListener != null)
                    mListener.onAnimationEnd(null);
                onEnd();
            }
        }

        public void onAnimationStart(View view)
        {
            if(!mProxyStarted)
            {
                mProxyStarted = true;
                if(mListener != null)
                {
                    mListener.onAnimationStart(null);
                    return;
                }
            }
        }

        void onEnd()
        {
            mProxyEndCount = 0;
            mProxyStarted = false;
            onAnimationsEnded();
        }

        private int mProxyEndCount;
        private boolean mProxyStarted;
        final ViewPropertyAnimatorCompatSet this$0;

            
            {
                this$0 = ViewPropertyAnimatorCompatSet.this;
                super();
                mProxyStarted = false;
                mProxyEndCount = 0;
            }
    }
;
}
