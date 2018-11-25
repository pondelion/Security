// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.service.media.MediaBrowserService;
import android.util.Log;
import java.lang.reflect.Field;
import java.util.*;

class MediaBrowserServiceCompatApi24
{
    static class MediaBrowserServiceAdaptor extends MediaBrowserServiceCompatApi23.MediaBrowserServiceAdaptor
    {

        public void onLoadChildren(String s, android.service.media.MediaBrowserService.Result result, Bundle bundle)
        {
            ((ServiceCompatProxy)mServiceProxy).onLoadChildren(s, new ResultWrapper(result), bundle);
        }

        MediaBrowserServiceAdaptor(Context context, ServiceCompatProxy servicecompatproxy)
        {
            super(context, servicecompatproxy);
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

        public void sendResult(List list, int i)
        {
            try
            {
                MediaBrowserServiceCompatApi24.sResultFlags.setInt(mResultObj, i);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                Log.w("MBSCompatApi24", illegalaccessexception);
            }
            mResultObj.sendResult(parcelListToItemList(list));
        }

        android.service.media.MediaBrowserService.Result mResultObj;

        ResultWrapper(android.service.media.MediaBrowserService.Result result)
        {
            mResultObj = result;
        }
    }

    public static interface ServiceCompatProxy
        extends MediaBrowserServiceCompatApi23.ServiceCompatProxy
    {

        public abstract void onLoadChildren(String s, ResultWrapper resultwrapper, Bundle bundle);
    }


    MediaBrowserServiceCompatApi24()
    {
    }

    public static Object createService(Context context, ServiceCompatProxy servicecompatproxy)
    {
        return new MediaBrowserServiceAdaptor(context, servicecompatproxy);
    }

    public static Bundle getBrowserRootHints(Object obj)
    {
        return ((MediaBrowserService)obj).getBrowserRootHints();
    }

    public static void notifyChildrenChanged(Object obj, String s, Bundle bundle)
    {
        ((MediaBrowserService)obj).notifyChildrenChanged(s, bundle);
    }

    private static final String TAG = "MBSCompatApi24";
    private static Field sResultFlags;

    static 
    {
        try
        {
            sResultFlags = android/service/media/MediaBrowserService$Result.getDeclaredField("mFlags");
            sResultFlags.setAccessible(true);
        }
        catch(NoSuchFieldException nosuchfieldexception)
        {
            Log.w("MBSCompatApi24", nosuchfieldexception);
        }
    }

}
