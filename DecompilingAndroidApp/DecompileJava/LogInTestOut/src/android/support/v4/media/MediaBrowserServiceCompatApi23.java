// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.content.Context;

class MediaBrowserServiceCompatApi23
{
    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi21.MediaBrowserServiceAdaptor
    {

        public void onLoadItem(String s, android.service.media.MediaBrowserService.Result result)
        {
            ((ServiceCompatProxy)mServiceProxy).onLoadItem(s, new MediaBrowserServiceCompatApi21.ResultWrapper(result));
        }

        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy servicecompatproxy)
        {
            super(context, servicecompatproxy);
        }
    }

    public static interface ServiceCompatProxy
        extends MediaBrowserServiceCompatApi21.ServiceCompatProxy
    {

        public abstract void onLoadItem(String s, MediaBrowserServiceCompatApi21.ResultWrapper resultwrapper);
    }


    MediaBrowserServiceCompatApi23()
    {
    }

    public static Object createService(Context context, ServiceCompatProxy servicecompatproxy)
    {
        return new MediaBrowserServiceAdaptor(context, servicecompatproxy);
    }
}
