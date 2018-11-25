// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

class ActivityCompatApi23
{
    public static interface OnSharedElementsReadyListenerBridge
    {

        public abstract void onSharedElementsReady();
    }

    public static interface RequestPermissionsRequestCodeValidator
    {

        public abstract void validateRequestPermissionsRequestCode(int i);
    }

    public static abstract class SharedElementCallback23 extends ActivityCompat21.SharedElementCallback21
    {

        public abstract void onSharedElementsArrived(List list, List list1, OnSharedElementsReadyListenerBridge onsharedelementsreadylistenerbridge);

        public SharedElementCallback23()
        {
        }
    }

    private static class SharedElementCallbackImpl extends SharedElementCallback
    {

        public Parcelable onCaptureSharedElementSnapshot(View view, Matrix matrix, RectF rectf)
        {
            return mCallback.onCaptureSharedElementSnapshot(view, matrix, rectf);
        }

        public View onCreateSnapshotView(Context context, Parcelable parcelable)
        {
            return mCallback.onCreateSnapshotView(context, parcelable);
        }

        public void onMapSharedElements(List list, Map map)
        {
            mCallback.onMapSharedElements(list, map);
        }

        public void onRejectSharedElements(List list)
        {
            mCallback.onRejectSharedElements(list);
        }

        public void onSharedElementEnd(List list, List list1, List list2)
        {
            mCallback.onSharedElementEnd(list, list1, list2);
        }

        public void onSharedElementStart(List list, List list1, List list2)
        {
            mCallback.onSharedElementStart(list, list1, list2);
        }

        public void onSharedElementsArrived(List list, List list1, android.app.SharedElementCallback.OnSharedElementsReadyListener onsharedelementsreadylistener)
        {
            mCallback.onSharedElementsArrived(list, list1, onsharedelementsreadylistener. new OnSharedElementsReadyListenerBridge() {

                public void onSharedElementsReady()
                {
                    listener.onSharedElementsReady();
                }

                final SharedElementCallbackImpl this$0;
                final android.app.SharedElementCallback.OnSharedElementsReadyListener val$listener;

            
            {
                this$0 = final_sharedelementcallbackimpl;
                listener = android.app.SharedElementCallback.OnSharedElementsReadyListener.this;
                super();
            }
            }
);
        }

        private SharedElementCallback23 mCallback;

        public SharedElementCallbackImpl(SharedElementCallback23 sharedelementcallback23)
        {
            mCallback = sharedelementcallback23;
        }
    }


    ActivityCompatApi23()
    {
    }

    private static SharedElementCallback createCallback(SharedElementCallback23 sharedelementcallback23)
    {
        SharedElementCallbackImpl sharedelementcallbackimpl = null;
        if(sharedelementcallback23 != null)
            sharedelementcallbackimpl = new SharedElementCallbackImpl(sharedelementcallback23);
        return sharedelementcallbackimpl;
    }

    public static void requestPermissions(Activity activity, String as[], int i)
    {
        if(activity instanceof RequestPermissionsRequestCodeValidator)
            ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(i);
        activity.requestPermissions(as, i);
    }

    public static void setEnterSharedElementCallback(Activity activity, SharedElementCallback23 sharedelementcallback23)
    {
        activity.setEnterSharedElementCallback(createCallback(sharedelementcallback23));
    }

    public static void setExitSharedElementCallback(Activity activity, SharedElementCallback23 sharedelementcallback23)
    {
        activity.setExitSharedElementCallback(createCallback(sharedelementcallback23));
    }

    public static boolean shouldShowRequestPermissionRationale(Activity activity, String s)
    {
        return activity.shouldShowRequestPermissionRationale(s);
    }
}
