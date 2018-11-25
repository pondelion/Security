// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.net.Uri;
import android.os.Bundle;

class MediaControllerCompatApi23
{
    public static class TransportControls extends MediaControllerCompatApi21.TransportControls
    {

        public static void playFromUri(Object obj, Uri uri, Bundle bundle)
        {
            ((android.media.session.MediaController.TransportControls)obj).playFromUri(uri, bundle);
        }

        public TransportControls()
        {
        }
    }


    MediaControllerCompatApi23()
    {
    }
}
