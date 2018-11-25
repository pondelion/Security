// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaControllerCompatApi24
{
    public static class TransportControls extends MediaControllerCompatApi23.TransportControls
    {

        public static void prepare(Object obj)
        {
            ((android.media.session.MediaController.TransportControls)obj).prepare();
        }

        public static void prepareFromMediaId(Object obj, String s, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).prepareFromMediaId(s, bundle);
        }

        public static void prepareFromSearch(Object obj, String s, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).prepareFromSearch(s, bundle);
        }

        public static void prepareFromUri(Object obj, Uri uri, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).prepareFromUri(uri, bundle);
        }

        public TransportControls()
        {
        }
    }


    MediaControllerCompatApi24()
    {
    }
}
