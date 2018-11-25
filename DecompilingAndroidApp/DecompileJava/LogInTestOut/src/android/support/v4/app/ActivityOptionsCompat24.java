// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.*;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;

class ActivityOptionsCompat24
{

    private ActivityOptionsCompat24(ActivityOptions activityoptions)
    {
        mActivityOptions = activityoptions;
    }

    public static ActivityOptionsCompat24 makeBasic()
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeBasic());
    }

    public static ActivityOptionsCompat24 makeClipRevealAnimation(View view, int i, int j, int k, int l)
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeClipRevealAnimation(view, i, j, k, l));
    }

    public static ActivityOptionsCompat24 makeCustomAnimation(Context context, int i, int j)
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeCustomAnimation(context, i, j));
    }

    public static ActivityOptionsCompat24 makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeScaleUpAnimation(view, i, j, k, l));
    }

    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(Activity activity, View view, String s)
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, view, s));
    }

    public static ActivityOptionsCompat24 makeSceneTransitionAnimation(Activity activity, View aview[], String as[])
    {
        Pair apair[] = null;
        if(aview != null)
        {
            Pair apair1[] = new Pair[aview.length];
            int i = 0;
            do
            {
                apair = apair1;
                if(i >= apair1.length)
                    break;
                apair1[i] = Pair.create(aview[i], as[i]);
                i++;
            } while(true);
        }
        return new ActivityOptionsCompat24(ActivityOptions.makeSceneTransitionAnimation(activity, apair));
    }

    public static ActivityOptionsCompat24 makeTaskLaunchBehind()
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeTaskLaunchBehind());
    }

    public static ActivityOptionsCompat24 makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        return new ActivityOptionsCompat24(ActivityOptions.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
    }

    public Rect getLaunchBounds()
    {
        return mActivityOptions.getLaunchBounds();
    }

    public void requestUsageTimeReport(PendingIntent pendingintent)
    {
        mActivityOptions.requestUsageTimeReport(pendingintent);
    }

    public ActivityOptionsCompat24 setLaunchBounds(Rect rect)
    {
        return new ActivityOptionsCompat24(mActivityOptions.setLaunchBounds(rect));
    }

    public Bundle toBundle()
    {
        return mActivityOptions.toBundle();
    }

    public void update(ActivityOptionsCompat24 activityoptionscompat24)
    {
        mActivityOptions.update(activityoptionscompat24.mActivityOptions);
    }

    private final ActivityOptions mActivityOptions;
}
