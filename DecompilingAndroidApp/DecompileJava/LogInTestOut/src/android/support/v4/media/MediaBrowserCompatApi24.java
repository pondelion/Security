// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Bundle;
import java.util.List;

class MediaBrowserCompatApi24
{
    static interface SubscriptionCallback
        extends MediaBrowserCompatApi21.SubscriptionCallback
    {

        public abstract void onChildrenLoaded(String s, List list, Bundle bundle);

        public abstract void onError(String s, Bundle bundle);
    }

    static class SubscriptionCallbackProxy extends MediaBrowserCompatApi21.SubscriptionCallbackProxy
    {

        public void onChildrenLoaded(String s, List list, Bundle bundle)
        {
            ((SubscriptionCallback)mSubscriptionCallback).onChildrenLoaded(s, list, bundle);
        }

        public void onError(String s, Bundle bundle)
        {
            ((SubscriptionCallback)mSubscriptionCallback).onError(s, bundle);
        }

        public SubscriptionCallbackProxy(SubscriptionCallback subscriptioncallback)
        {
            super(subscriptioncallback);
        }
    }


    MediaBrowserCompatApi24()
    {
    }

    public static Object createSubscriptionCallback(SubscriptionCallback subscriptioncallback)
    {
        return new SubscriptionCallbackProxy(subscriptioncallback);
    }

    public static void subscribe(Object obj, String s, Bundle bundle, Object obj1)
    {
        ((MediaBrowser)obj).subscribe(s, bundle, (android.media.browse.MediaBrowser.SubscriptionCallback)obj1);
    }

    public static void unsubscribe(Object obj, String s, Object obj1)
    {
        ((MediaBrowser)obj).unsubscribe(s, (android.media.browse.MediaBrowser.SubscriptionCallback)obj1);
    }
}
