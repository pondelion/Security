// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.media.session.MediaSession;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MediaSessionCompatApi24
{
    public static interface Callback
        extends MediaSessionCompatApi23.Callback
    {

        public abstract void onPrepare();

        public abstract void onPrepareFromMediaId(String s, Bundle bundle);

        public abstract void onPrepareFromSearch(String s, Bundle bundle);

        public abstract void onPrepareFromUri(Uri uri, Bundle bundle);
    }

    static class CallbackProxy extends MediaSessionCompatApi23.CallbackProxy
    {

        public void onPrepare()
        {
            ((Callback)mCallback).onPrepare();
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
            ((Callback)mCallback).onPrepareFromMediaId(s, bundle);
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
            ((Callback)mCallback).onPrepareFromSearch(s, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
            ((Callback)mCallback).onPrepareFromUri(uri, bundle);
        }

        public CallbackProxy(Callback callback)
        {
            super(callback);
        }
    }


    MediaSessionCompatApi24()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }

    public static String getCallingPackage(Object obj)
    {
        obj = (MediaSession)obj;
        obj = (String)obj.getClass().getMethod("getCallingPackage", new Class[0]).invoke(obj, new Object[0]);
        return ((String) (obj));
        obj;
_L2:
        Log.e("MediaSessionCompatApi24", "Cannot execute MediaSession.getCallingPackage()", ((Throwable) (obj)));
        return null;
        obj;
        continue; /* Loop/switch isn't completed */
        obj;
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "MediaSessionCompatApi24";
}
