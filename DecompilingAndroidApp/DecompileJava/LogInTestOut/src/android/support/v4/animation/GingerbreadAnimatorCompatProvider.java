// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.animation;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.animation:
//            AnimatorProvider, ValueAnimatorCompat, AnimatorListenerCompat, AnimatorUpdateListenerCompat

class GingerbreadAnimatorCompatProvider
    implements AnimatorProvider
{
    private static class GingerbreadFloatValueAnimator
        implements ValueAnimatorCompat
    {

        private void dispatchCancel()
        {
            for(int i = mListeners.size() - 1; i >= 0; i--)
                ((AnimatorListenerCompat)mListeners.get(i)).onAnimationCancel(this);

        }

        private void dispatchEnd()
        {
            for(int i = mListeners.size() - 1; i >= 0; i--)
                ((AnimatorListenerCompat)mListeners.get(i)).onAnimationEnd(this);

        }

        private void dispatchStart()
        {
            for(int i = mListeners.size() - 1; i >= 0; i--)
                ((AnimatorListenerCompat)mListeners.get(i)).onAnimationStart(this);

        }

        private long getTime()
        {
            return mTarget.getDrawingTime();
        }

        private void notifyUpdateListeners()
        {
            for(int i = mUpdateListeners.size() - 1; i >= 0; i--)
                ((AnimatorUpdateListenerCompat)mUpdateListeners.get(i)).onAnimationUpdate(this);

        }

        public void addListener(AnimatorListenerCompat animatorlistenercompat)
        {
            mListeners.add(animatorlistenercompat);
        }

        public void addUpdateListener(AnimatorUpdateListenerCompat animatorupdatelistenercompat)
        {
            mUpdateListeners.add(animatorupdatelistenercompat);
        }

        public void cancel()
        {
            if(mEnded)
                return;
            mEnded = true;
            if(mStarted)
                dispatchCancel();
            dispatchEnd();
        }

        public float getAnimatedFraction()
        {
            return mFraction;
        }

        public void setDuration(long l)
        {
            if(!mStarted)
                mDuration = l;
        }

        public void setTarget(View view)
        {
            mTarget = view;
        }

        public void start()
        {
            if(mStarted)
            {
                return;
            } else
            {
                mStarted = true;
                dispatchStart();
                mFraction = 0.0F;
                mStartTime = getTime();
                mTarget.postDelayed(mLoopRunnable, 16L);
                return;
            }
        }

        private long mDuration;
        private boolean mEnded;
        private float mFraction;
        List mListeners;
        private Runnable mLoopRunnable;
        private long mStartTime;
        private boolean mStarted;
        View mTarget;
        List mUpdateListeners;






/*
        static float access$302(GingerbreadFloatValueAnimator gingerbreadfloatvalueanimator, float f)
        {
            gingerbreadfloatvalueanimator.mFraction = f;
            return f;
        }

*/




        public GingerbreadFloatValueAnimator()
        {
            mListeners = new ArrayList();
            mUpdateListeners = new ArrayList();
            mDuration = 200L;
            mFraction = 0.0F;
            mStarted = false;
            mEnded = false;
            mLoopRunnable = new _cls1();
        }
    }


    GingerbreadAnimatorCompatProvider()
    {
    }

    public void clearInterpolator(View view)
    {
    }

    public ValueAnimatorCompat emptyValueAnimator()
    {
        return new GingerbreadFloatValueAnimator();
    }

    // Unreferenced inner class android/support/v4/animation/GingerbreadAnimatorCompatProvider$GingerbreadFloatValueAnimator$1

/* anonymous class */
    class GingerbreadFloatValueAnimator._cls1
        implements Runnable
    {

        public void run()
        {
            float f = ((float)(getTime() - mStartTime) * 1.0F) / (float)mDuration;
            if(f > 1.0F || mTarget.getParent() == null)
                f = 1.0F;
            mFraction = f;
            notifyUpdateListeners();
            if(mFraction >= 1.0F)
            {
                dispatchEnd();
                return;
            } else
            {
                mTarget.postDelayed(mLoopRunnable, 16L);
                return;
            }
        }

        final GingerbreadFloatValueAnimator this$0;

            
            {
                this$0 = GingerbreadFloatValueAnimator.this;
                super();
            }
    }

}
