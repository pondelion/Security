// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewPropertyAnimator;

// Referenced classes of package android.support.v4.view:
//            ViewPropertyAnimatorUpdateListener

class ViewPropertyAnimatorCompatKK
{

    ViewPropertyAnimatorCompatKK()
    {
    }

    public static void setUpdateListener(View view, ViewPropertyAnimatorUpdateListener viewpropertyanimatorupdatelistener)
    {
        android.animation.ValueAnimator.AnimatorUpdateListener animatorupdatelistener = null;
        if(viewpropertyanimatorupdatelistener != null)
            animatorupdatelistener = new android.animation.ValueAnimator.AnimatorUpdateListener(viewpropertyanimatorupdatelistener, view) {

                public void onAnimationUpdate(ValueAnimator valueanimator)
                {
                    listener.onAnimationUpdate(view);
                }

                final ViewPropertyAnimatorUpdateListener val$listener;
                final View val$view;

            
            {
                listener = viewpropertyanimatorupdatelistener;
                view = view1;
                super();
            }
            }
;
        view.animate().setUpdateListener(animatorupdatelistener);
    }
}
