// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.*;
import android.text.TextUtils;

// Referenced classes of package android.support.v4.media:
//            MediaDescriptionCompatApi21, MediaDescriptionCompatApi23

public final class MediaDescriptionCompat
    implements Parcelable
{
    public static final class Builder
    {

        public MediaDescriptionCompat build()
        {
            return new MediaDescriptionCompat(mMediaId, mTitle, mSubtitle, mDescription, mIcon, mIconUri, mExtras, mMediaUri);
        }

        public Builder setDescription(CharSequence charsequence)
        {
            mDescription = charsequence;
            return this;
        }

        public Builder setExtras(Bundle bundle)
        {
            mExtras = bundle;
            return this;
        }

        public Builder setIconBitmap(Bitmap bitmap)
        {
            mIcon = bitmap;
            return this;
        }

        public Builder setIconUri(Uri uri)
        {
            mIconUri = uri;
            return this;
        }

        public Builder setMediaId(String s)
        {
            mMediaId = s;
            return this;
        }

        public Builder setMediaUri(Uri uri)
        {
            mMediaUri = uri;
            return this;
        }

        public Builder setSubtitle(CharSequence charsequence)
        {
            mSubtitle = charsequence;
            return this;
        }

        public Builder setTitle(CharSequence charsequence)
        {
            mTitle = charsequence;
            return this;
        }

        private CharSequence mDescription;
        private Bundle mExtras;
        private Bitmap mIcon;
        private Uri mIconUri;
        private String mMediaId;
        private Uri mMediaUri;
        private CharSequence mSubtitle;
        private CharSequence mTitle;

        public Builder()
        {
        }
    }


    MediaDescriptionCompat(Parcel parcel)
    {
        mMediaId = parcel.readString();
        mTitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mSubtitle = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mDescription = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        mIcon = (Bitmap)parcel.readParcelable(null);
        mIconUri = (Uri)parcel.readParcelable(null);
        mExtras = parcel.readBundle();
        mMediaUri = (Uri)parcel.readParcelable(null);
    }

    MediaDescriptionCompat(String s, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, Bitmap bitmap, Uri uri, Bundle bundle, 
            Uri uri1)
    {
        mMediaId = s;
        mTitle = charsequence;
        mSubtitle = charsequence1;
        mDescription = charsequence2;
        mIcon = bitmap;
        mIconUri = uri;
        mExtras = bundle;
        mMediaUri = uri1;
    }

    public static MediaDescriptionCompat fromMediaDescription(Object obj)
    {
        Builder builder;
        if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
            return null;
        builder = new Builder();
        builder.setMediaId(MediaDescriptionCompatApi21.getMediaId(obj));
        builder.setTitle(MediaDescriptionCompatApi21.getTitle(obj));
        builder.setSubtitle(MediaDescriptionCompatApi21.getSubtitle(obj));
        builder.setDescription(MediaDescriptionCompatApi21.getDescription(obj));
        builder.setIconBitmap(MediaDescriptionCompatApi21.getIconBitmap(obj));
        builder.setIconUri(MediaDescriptionCompatApi21.getIconUri(obj));
        Bundle bundle1 = MediaDescriptionCompatApi21.getExtras(obj);
        Object obj1;
        Bundle bundle;
        if(bundle1 == null)
            obj1 = null;
        else
            obj1 = (Uri)bundle1.getParcelable("android.support.v4.media.description.MEDIA_URI");
        bundle = bundle1;
        if(obj1 != null)
            if(bundle1.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && bundle1.size() == 2)
            {
                bundle = null;
            } else
            {
                bundle1.remove("android.support.v4.media.description.MEDIA_URI");
                bundle1.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
                bundle = bundle1;
            }
        builder.setExtras(bundle);
        if(obj1 == null) goto _L2; else goto _L1
_L1:
        builder.setMediaUri(((Uri) (obj1)));
_L4:
        obj1 = builder.build();
        obj1.mDescriptionObj = obj;
        return ((MediaDescriptionCompat) (obj1));
_L2:
        if(android.os.Build.VERSION.SDK_INT >= 23)
            builder.setMediaUri(MediaDescriptionCompatApi23.getMediaUri(obj));
        if(true) goto _L4; else goto _L3
_L3:
    }

    public int describeContents()
    {
        return 0;
    }

    public CharSequence getDescription()
    {
        return mDescription;
    }

    public Bundle getExtras()
    {
        return mExtras;
    }

    public Bitmap getIconBitmap()
    {
        return mIcon;
    }

    public Uri getIconUri()
    {
        return mIconUri;
    }

    public Object getMediaDescription()
    {
        if(mDescriptionObj != null || android.os.Build.VERSION.SDK_INT < 21)
            return mDescriptionObj;
        Object obj = MediaDescriptionCompatApi21.Builder.newInstance();
        MediaDescriptionCompatApi21.Builder.setMediaId(obj, mMediaId);
        MediaDescriptionCompatApi21.Builder.setTitle(obj, mTitle);
        MediaDescriptionCompatApi21.Builder.setSubtitle(obj, mSubtitle);
        MediaDescriptionCompatApi21.Builder.setDescription(obj, mDescription);
        MediaDescriptionCompatApi21.Builder.setIconBitmap(obj, mIcon);
        MediaDescriptionCompatApi21.Builder.setIconUri(obj, mIconUri);
        Bundle bundle1 = mExtras;
        Bundle bundle = bundle1;
        if(android.os.Build.VERSION.SDK_INT < 23)
        {
            bundle = bundle1;
            if(mMediaUri != null)
            {
                bundle = bundle1;
                if(bundle1 == null)
                {
                    bundle = new Bundle();
                    bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
                }
                bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", mMediaUri);
            }
        }
        MediaDescriptionCompatApi21.Builder.setExtras(obj, bundle);
        if(android.os.Build.VERSION.SDK_INT >= 23)
            MediaDescriptionCompatApi23.Builder.setMediaUri(obj, mMediaUri);
        mDescriptionObj = MediaDescriptionCompatApi21.Builder.build(obj);
        return mDescriptionObj;
    }

    public String getMediaId()
    {
        return mMediaId;
    }

    public Uri getMediaUri()
    {
        return mMediaUri;
    }

    public CharSequence getSubtitle()
    {
        return mSubtitle;
    }

    public CharSequence getTitle()
    {
        return mTitle;
    }

    public String toString()
    {
        return (new StringBuilder()).append(mTitle).append(", ").append(mSubtitle).append(", ").append(mDescription).toString();
    }

    public void writeToParcel(Parcel parcel, int i)
    {
        if(android.os.Build.VERSION.SDK_INT < 21)
        {
            parcel.writeString(mMediaId);
            TextUtils.writeToParcel(mTitle, parcel, i);
            TextUtils.writeToParcel(mSubtitle, parcel, i);
            TextUtils.writeToParcel(mDescription, parcel, i);
            parcel.writeParcelable(mIcon, i);
            parcel.writeParcelable(mIconUri, i);
            parcel.writeBundle(mExtras);
            parcel.writeParcelable(mMediaUri, i);
            return;
        } else
        {
            MediaDescriptionCompatApi21.writeToParcel(getMediaDescription(), parcel, i);
            return;
        }
    }

    public static final long BT_FOLDER_TYPE_ALBUMS = 2L;
    public static final long BT_FOLDER_TYPE_ARTISTS = 3L;
    public static final long BT_FOLDER_TYPE_GENRES = 4L;
    public static final long BT_FOLDER_TYPE_MIXED = 0L;
    public static final long BT_FOLDER_TYPE_PLAYLISTS = 5L;
    public static final long BT_FOLDER_TYPE_TITLES = 1L;
    public static final long BT_FOLDER_TYPE_YEARS = 6L;
    public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

        public MediaDescriptionCompat createFromParcel(Parcel parcel)
        {
            if(android.os.Build.VERSION.SDK_INT < 21)
                return new MediaDescriptionCompat(parcel);
            else
                return MediaDescriptionCompat.fromMediaDescription(MediaDescriptionCompatApi21.fromParcel(parcel));
        }

        public volatile Object createFromParcel(Parcel parcel)
        {
            return createFromParcel(parcel);
        }

        public MediaDescriptionCompat[] newArray(int i)
        {
            return new MediaDescriptionCompat[i];
        }

        public volatile Object[] newArray(int i)
        {
            return newArray(i);
        }

    }
;
    public static final String DESCRIPTION_KEY_MEDIA_URI = "android.support.v4.media.description.MEDIA_URI";
    public static final String DESCRIPTION_KEY_NULL_BUNDLE_FLAG = "android.support.v4.media.description.NULL_BUNDLE_FLAG";
    public static final String EXTRA_BT_FOLDER_TYPE = "android.media.extra.BT_FOLDER_TYPE";
    private final CharSequence mDescription;
    private Object mDescriptionObj;
    private final Bundle mExtras;
    private final Bitmap mIcon;
    private final Uri mIconUri;
    private final String mMediaId;
    private final Uri mMediaUri;
    private final CharSequence mSubtitle;
    private final CharSequence mTitle;

}
