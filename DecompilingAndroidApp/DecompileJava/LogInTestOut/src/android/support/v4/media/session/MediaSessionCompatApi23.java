// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaSessionCompatApi23
{
    public static interface Callback
        extends MediaSessionCompatApi21.Callback
    {

        public abstract void onPlayFromUri(Uri uri, Bundle bundle);
    }

    static class CallbackProxy extends MediaSessionCompatApi21.CallbackProxy
    {

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
            ((Callback)mCallback).onPlayFromUri(uri, bundle);
        }

        public CallbackProxy(Callback callback)
        {
            super(callback);
        }
    }


    MediaSessionCompatApi23()
    {
    }

    public static Object createCallback(Callback callback)
    {
        return new CallbackProxy(callback);
    }
}
