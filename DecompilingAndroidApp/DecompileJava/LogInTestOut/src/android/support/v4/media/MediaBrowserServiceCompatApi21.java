// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.content.Context;
import android.content.Intent;
import android.os.*;
import android.service.media.MediaBrowserService;
import java.util.*;

class MediaBrowserServiceCompatApi21
{
    static class BrowserRoot
    {

        final Bundle mExtras;
        final String mRootId;

        BrowserRoot(String s, Bundle bundle)
        {
            mRootId = s;
            mExtras = bundle;
        }
    }

    static class MediaBrowserServiceAdaptor extends MediaBrowserService
    {

        public android.service.media.MediaBrowserService.BrowserRoot onGetRoot(String s, int i, Bundle bundle)
        {
            s = mServiceProxy.onGetRoot(s, i, bundle);
            if(s == null)
                return null;
            else
                return new android.service.media.MediaBrowserService.BrowserRoot(((BrowserRoot) (s)).mRootId, ((BrowserRoot) (s)).mExtras);
        }

        public void onLoadChildren(String s, android.service.media.MediaBrowserService.Result result)
        {
            mServiceProxy.onLoadChildren(s, new ResultWrapper(result));
        }

        final ServiceCompatProxy mServiceProxy;

        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy servicecompatproxy)
        {
            attachBaseContext(context);
            mServiceProxy = servicecompatproxy;
        }
    }

    static class ResultWrapper
    {

        public void detach()
        {
            mResultObj.detach();
        }

        List parcelListToItemList(List list)
        {
            if(list != null) goto _L2; else goto _L1
_L1:
            list = null;
_L4:
            return list;
_L2:
            ArrayList arraylist = new ArrayList();
            Iterator iterator = list.iterator();
            do
            {
                list = arraylist;
                if(!iterator.hasNext())
                    continue;
                list = (Parcel)iterator.next();
                list.setDataPosition(0);
                arraylist.add(android.media.browse.MediaBrowser.MediaItem.CREATOR.createFromParcel(list));
                list.recycle();
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public void sendResult(Object obj)
        {
            if(obj instanceof List)
            {
                mResultObj.sendResult(parcelListToItemList((List)obj));
                return;
            }
            if(obj instanceof Parcel)
            {
                obj = (Parcel)obj;
                mResultObj.sendResult(android.media.browse.MediaBrowser.MediaItem.CREATOR.createFromParcel(((Parcel) (obj))));
                ((Parcel) (obj)).recycle();
                return;
            } else
            {
                mResultObj.sendResult(null);
                return;
            }
        }

        android.service.media.MediaBrowserService.Result mResultObj;

        ResultWrapper(android.service.media.MediaBrowserService.Result result)
        {
            mResultObj = result;
        }
    }

    public static interface ServiceCompatProxy
    {

        public abstract BrowserRoot onGetRoot(String s, int i, Bundle bundle);

        public abstract void onLoadChildren(String s, ResultWrapper resultwrapper);
    }


    MediaBrowserServiceCompatApi21()
    {
    }

    public static Object createService(Context context, ServiceCompatProxy servicecompatproxy)
    {
        return new MediaBrowserServiceAdaptor(context, servicecompatproxy);
    }

    public static void notifyChildrenChanged(Object obj, String s)
    {
        ((MediaBrowserService)obj).notifyChildrenChanged(s);
    }

    public static IBinder onBind(Object obj, Intent intent)
    {
        return ((MediaBrowserService)obj).onBind(intent);
    }

    public static void onCreate(Object obj)
    {
        ((MediaBrowserService)obj).onCreate();
    }

    public static void setSessionToken(Object obj, Object obj1)
    {
        ((MediaBrowserService)obj).setSessionToken((android.media.session.MediaSession.Token)obj1);
    }
}
