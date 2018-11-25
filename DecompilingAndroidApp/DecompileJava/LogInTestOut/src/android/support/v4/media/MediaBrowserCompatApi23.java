// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;

class MediaBrowserCompatApi23
{
    static interface ItemCallback
    {

        public abstract void onError(String s);

        public abstract void onItemLoaded(Parcel parcel);
    }

    static class ItemCallbackProxy extends android.media.browse.MediaBrowser.ItemCallback
    {

        public void onError(String s)
        {
            mItemCallback.onError(s);
        }

        public void onItemLoaded(android.media.browse.MediaBrowser.MediaItem mediaitem)
        {
            Parcel parcel = Parcel.obtain();
            mediaitem.writeToParcel(parcel, 0);
            mItemCallback.onItemLoaded(parcel);
        }

        protected final ItemCallback mItemCallback;

        public ItemCallbackProxy(ItemCallback itemcallback)
        {
            mItemCallback = itemcallback;
        }
    }


    MediaBrowserCompatApi23()
    {
    }

    public static Object createItemCallback(ItemCallback itemcallback)
    {
        return new ItemCallbackProxy(itemcallback);
    }

    public static void getItem(Object obj, String s, Object obj1)
    {
        ((MediaBrowser)obj).getItem(s, (android.media.browse.MediaBrowser.ItemCallback)obj1);
    }
}
