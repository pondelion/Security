// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.view.View;

// Referenced classes of package android.support.v4.app:
//            ActivityOptionsCompat24, ActivityOptionsCompat23, ActivityOptionsCompat21, ActivityOptionsCompatJB

public class ActivityOptionsCompat
{
    private static class ActivityOptionsImpl21 extends ActivityOptionsCompat
    {

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImpl21)
            {
                activityoptionscompat = (ActivityOptionsImpl21)activityoptionscompat;
                mImpl.update(((ActivityOptionsImpl21) (activityoptionscompat)).mImpl);
            }
        }

        private final ActivityOptionsCompat21 mImpl;

        ActivityOptionsImpl21(ActivityOptionsCompat21 activityoptionscompat21)
        {
            mImpl = activityoptionscompat21;
        }
    }

    private static class ActivityOptionsImpl23 extends ActivityOptionsCompat
    {

        public void requestUsageTimeReport(PendingIntent pendingintent)
        {
            mImpl.requestUsageTimeReport(pendingintent);
        }

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImpl23)
            {
                activityoptionscompat = (ActivityOptionsImpl23)activityoptionscompat;
                mImpl.update(((ActivityOptionsImpl23) (activityoptionscompat)).mImpl);
            }
        }

        private final ActivityOptionsCompat23 mImpl;

        ActivityOptionsImpl23(ActivityOptionsCompat23 activityoptionscompat23)
        {
            mImpl = activityoptionscompat23;
        }
    }

    private static class ActivityOptionsImpl24 extends ActivityOptionsCompat
    {

        public Rect getLaunchBounds()
        {
            return mImpl.getLaunchBounds();
        }

        public void requestUsageTimeReport(PendingIntent pendingintent)
        {
            mImpl.requestUsageTimeReport(pendingintent);
        }

        public ActivityOptionsCompat setLaunchBounds(Rect rect)
        {
            return new ActivityOptionsImpl24(mImpl.setLaunchBounds(rect));
        }

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImpl24)
            {
                activityoptionscompat = (ActivityOptionsImpl24)activityoptionscompat;
                mImpl.update(((ActivityOptionsImpl24) (activityoptionscompat)).mImpl);
            }
        }

        private final ActivityOptionsCompat24 mImpl;

        ActivityOptionsImpl24(ActivityOptionsCompat24 activityoptionscompat24)
        {
            mImpl = activityoptionscompat24;
        }
    }

    private static class ActivityOptionsImplJB extends ActivityOptionsCompat
    {

        public Bundle toBundle()
        {
            return mImpl.toBundle();
        }

        public void update(ActivityOptionsCompat activityoptionscompat)
        {
            if(activityoptionscompat instanceof ActivityOptionsImplJB)
            {
                activityoptionscompat = (ActivityOptionsImplJB)activityoptionscompat;
                mImpl.update(((ActivityOptionsImplJB) (activityoptionscompat)).mImpl);
            }
        }

        private final ActivityOptionsCompatJB mImpl;

        ActivityOptionsImplJB(ActivityOptionsCompatJB activityoptionscompatjb)
        {
            mImpl = activityoptionscompatjb;
        }
    }


    protected ActivityOptionsCompat()
    {
    }

    public static ActivityOptionsCompat makeBasic()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeBasic());
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeBasic());
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeClipRevealAnimation(View view, int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeClipRevealAnimation(view, i, j, k, l));
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeClipRevealAnimation(view, i, j, k, l));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeCustomAnimation(Context context, int i, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeCustomAnimation(context, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeCustomAnimation(context, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeCustomAnimation(context, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeCustomAnimation(context, i, j));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeScaleUpAnimation(View view, int i, int j, int k, int l)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeScaleUpAnimation(view, i, j, k, l));
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeScaleUpAnimation(view, i, j, k, l));
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeScaleUpAnimation(view, i, j, k, l));
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeScaleUpAnimation(view, i, j, k, l));
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, View view, String s)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, view, s));
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, view, s));
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, view, s));
        else
            return new ActivityOptionsCompat();
    }

    public static transient ActivityOptionsCompat makeSceneTransitionAnimation(Activity activity, Pair apair[])
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            View aview[] = null;
            String as[] = null;
            if(apair != null)
            {
                View aview1[] = new View[apair.length];
                String as1[] = new String[apair.length];
                int i = 0;
                do
                {
                    as = as1;
                    aview = aview1;
                    if(i >= apair.length)
                        break;
                    aview1[i] = (View)apair[i].first;
                    as1[i] = (String)apair[i].second;
                    i++;
                } while(true);
            }
            if(android.os.Build.VERSION.SDK_INT >= 24)
                return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeSceneTransitionAnimation(activity, aview, as));
            if(android.os.Build.VERSION.SDK_INT >= 23)
                return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeSceneTransitionAnimation(activity, aview, as));
            else
                return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeSceneTransitionAnimation(activity, aview, as));
        } else
        {
            return new ActivityOptionsCompat();
        }
    }

    public static ActivityOptionsCompat makeTaskLaunchBehind()
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeTaskLaunchBehind());
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeTaskLaunchBehind());
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeTaskLaunchBehind());
        else
            return new ActivityOptionsCompat();
    }

    public static ActivityOptionsCompat makeThumbnailScaleUpAnimation(View view, Bitmap bitmap, int i, int j)
    {
        if(android.os.Build.VERSION.SDK_INT >= 24)
            return new ActivityOptionsImpl24(ActivityOptionsCompat24.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return new ActivityOptionsImpl23(ActivityOptionsCompat23.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return new ActivityOptionsImpl21(ActivityOptionsCompat21.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        if(android.os.Build.VERSION.SDK_INT >= 16)
            return new ActivityOptionsImplJB(ActivityOptionsCompatJB.makeThumbnailScaleUpAnimation(view, bitmap, i, j));
        else
            return new ActivityOptionsCompat();
    }

    public Rect getLaunchBounds()
    {
        return null;
    }

    public void requestUsageTimeReport(PendingIntent pendingintent)
    {
    }

    public ActivityOptionsCompat setLaunchBounds(Rect rect)
    {
        return null;
    }

    public Bundle toBundle()
    {
        return null;
    }

    public void update(ActivityOptionsCompat activityoptionscompat)
    {
    }

    public static final String EXTRA_USAGE_TIME_REPORT = "android.activity.usage_time";
    public static final String EXTRA_USAGE_TIME_REPORT_PACKAGES = "android.usage_time_packages";
}
