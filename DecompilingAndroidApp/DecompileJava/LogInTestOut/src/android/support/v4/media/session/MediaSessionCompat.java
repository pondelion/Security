// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.*;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.Uri;
import android.os.*;
import android.support.v4.media.*;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import java.lang.annotation.Annotation;
import java.util.*;

// Referenced classes of package android.support.v4.media.session:
//            MediaControllerCompat, PlaybackStateCompat, MediaSessionCompatApi24, MediaSessionCompatApi23, 
//            MediaSessionCompatApi21, MediaSessionCompatApi22, MediaButtonReceiver, MediaSessionCompatApi14, 
//            IMediaControllerCallback, MediaSessionCompatApi18, MediaSessionCompatApi19, ParcelableVolumeInfo

public class MediaSessionCompat
{
    public static abstract class Callback
    {

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
        }

        public void onCustomAction(String s, Bundle bundle)
        {
        }

        public void onFastForward()
        {
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            return false;
        }

        public void onPause()
        {
        }

        public void onPlay()
        {
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
        }

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onPrepare()
        {
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
        }

        public void onRewind()
        {
        }

        public void onSeekTo(long l)
        {
        }

        public void onSetRating(RatingCompat ratingcompat)
        {
        }

        public void onSkipToNext()
        {
        }

        public void onSkipToPrevious()
        {
        }

        public void onSkipToQueueItem(long l)
        {
        }

        public void onStop()
        {
        }

        final Object mCallbackObj;

        public Callback()
        {
            if(android.os.Build.VERSION.SDK_INT >= 24)
            {
                mCallbackObj = MediaSessionCompatApi24.createCallback(new StubApi24());
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 23)
            {
                mCallbackObj = MediaSessionCompatApi23.createCallback(new StubApi23());
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                mCallbackObj = MediaSessionCompatApi21.createCallback(new StubApi21());
                return;
            } else
            {
                mCallbackObj = null;
                return;
            }
        }
    }

    private class Callback.StubApi21
        implements MediaSessionCompatApi21.Callback
    {

        public void onCommand(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            Callback.this.onCommand(s, bundle, resultreceiver);
        }

        public void onCustomAction(String s, Bundle bundle)
        {
            if(s.equals("android.support.v4.media.session.action.PLAY_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPlayFromUri(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE"))
            {
                onPrepare();
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromMediaId(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH"))
            {
                s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromSearch(s, bundle);
                return;
            }
            if(s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI"))
            {
                s = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                onPrepareFromUri(s, bundle);
                return;
            } else
            {
                Callback.this.onCustomAction(s, bundle);
                return;
            }
        }

        public void onFastForward()
        {
            Callback.this.onFastForward();
        }

        public boolean onMediaButtonEvent(Intent intent)
        {
            return Callback.this.onMediaButtonEvent(intent);
        }

        public void onPause()
        {
            Callback.this.onPause();
        }

        public void onPlay()
        {
            Callback.this.onPlay();
        }

        public void onPlayFromMediaId(String s, Bundle bundle)
        {
            Callback.this.onPlayFromMediaId(s, bundle);
        }

        public void onPlayFromSearch(String s, Bundle bundle)
        {
            Callback.this.onPlayFromSearch(s, bundle);
        }

        public void onRewind()
        {
            Callback.this.onRewind();
        }

        public void onSeekTo(long l)
        {
            Callback.this.onSeekTo(l);
        }

        public void onSetRating(Object obj)
        {
            Callback.this.onSetRating(RatingCompat.fromRating(obj));
        }

        public void onSkipToNext()
        {
            Callback.this.onSkipToNext();
        }

        public void onSkipToPrevious()
        {
            Callback.this.onSkipToPrevious();
        }

        public void onSkipToQueueItem(long l)
        {
            Callback.this.onSkipToQueueItem(l);
        }

        public void onStop()
        {
            Callback.this.onStop();
        }

        final Callback this$0;

        Callback.StubApi21()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private class Callback.StubApi23 extends Callback.StubApi21
        implements MediaSessionCompatApi23.Callback
    {

        public void onPlayFromUri(Uri uri, Bundle bundle)
        {
            Callback.this.onPlayFromUri(uri, bundle);
        }

        final Callback this$0;

        Callback.StubApi23()
        {
            this$0 = Callback.this;
            super();
        }
    }

    private class Callback.StubApi24 extends Callback.StubApi23
        implements MediaSessionCompatApi24.Callback
    {

        public void onPrepare()
        {
            Callback.this.onPrepare();
        }

        public void onPrepareFromMediaId(String s, Bundle bundle)
        {
            Callback.this.onPrepareFromMediaId(s, bundle);
        }

        public void onPrepareFromSearch(String s, Bundle bundle)
        {
            Callback.this.onPrepareFromSearch(s, bundle);
        }

        public void onPrepareFromUri(Uri uri, Bundle bundle)
        {
            Callback.this.onPrepareFromUri(uri, bundle);
        }

        final Callback this$0;

        Callback.StubApi24()
        {
            this$0 = Callback.this;
            super();
        }
    }

    static interface MediaSessionImpl
    {

        public abstract String getCallingPackage();

        public abstract Object getMediaSession();

        public abstract Object getRemoteControlClient();

        public abstract Token getSessionToken();

        public abstract boolean isActive();

        public abstract void release();

        public abstract void sendSessionEvent(String s, Bundle bundle);

        public abstract void setActive(boolean flag);

        public abstract void setCallback(Callback callback, Handler handler);

        public abstract void setExtras(Bundle bundle);

        public abstract void setFlags(int i);

        public abstract void setMediaButtonReceiver(PendingIntent pendingintent);

        public abstract void setMetadata(MediaMetadataCompat mediametadatacompat);

        public abstract void setPlaybackState(PlaybackStateCompat playbackstatecompat);

        public abstract void setPlaybackToLocal(int i);

        public abstract void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat);

        public abstract void setQueue(List list);

        public abstract void setQueueTitle(CharSequence charsequence);

        public abstract void setRatingType(int i);

        public abstract void setSessionActivity(PendingIntent pendingintent);
    }

    static class MediaSessionImplApi21
        implements MediaSessionImpl
    {

        public String getCallingPackage()
        {
            if(android.os.Build.VERSION.SDK_INT < 24)
                return null;
            else
                return MediaSessionCompatApi24.getCallingPackage(mSessionObj);
        }

        public Object getMediaSession()
        {
            return mSessionObj;
        }

        public Object getRemoteControlClient()
        {
            return null;
        }

        public Token getSessionToken()
        {
            return mToken;
        }

        public boolean isActive()
        {
            return MediaSessionCompatApi21.isActive(mSessionObj);
        }

        public void release()
        {
            MediaSessionCompatApi21.release(mSessionObj);
        }

        public void sendSessionEvent(String s, Bundle bundle)
        {
            MediaSessionCompatApi21.sendSessionEvent(mSessionObj, s, bundle);
        }

        public void setActive(boolean flag)
        {
            MediaSessionCompatApi21.setActive(mSessionObj, flag);
        }

        public void setCallback(Callback callback, Handler handler)
        {
            Object obj = mSessionObj;
            if(callback == null)
                callback = null;
            else
                callback = ((Callback) (callback.mCallbackObj));
            MediaSessionCompatApi21.setCallback(obj, callback, handler);
        }

        public void setExtras(Bundle bundle)
        {
            MediaSessionCompatApi21.setExtras(mSessionObj, bundle);
        }

        public void setFlags(int i)
        {
            MediaSessionCompatApi21.setFlags(mSessionObj, i);
        }

        public void setMediaButtonReceiver(PendingIntent pendingintent)
        {
            mMediaButtonIntent = pendingintent;
            MediaSessionCompatApi21.setMediaButtonReceiver(mSessionObj, pendingintent);
        }

        public void setMetadata(MediaMetadataCompat mediametadatacompat)
        {
            Object obj = mSessionObj;
            if(mediametadatacompat == null)
                mediametadatacompat = null;
            else
                mediametadatacompat = ((MediaMetadataCompat) (mediametadatacompat.getMediaMetadata()));
            MediaSessionCompatApi21.setMetadata(obj, mediametadatacompat);
        }

        public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
        {
            Object obj = mSessionObj;
            if(playbackstatecompat == null)
                playbackstatecompat = null;
            else
                playbackstatecompat = ((PlaybackStateCompat) (playbackstatecompat.getPlaybackState()));
            MediaSessionCompatApi21.setPlaybackState(obj, playbackstatecompat);
        }

        public void setPlaybackToLocal(int i)
        {
            MediaSessionCompatApi21.setPlaybackToLocal(mSessionObj, i);
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
        {
            MediaSessionCompatApi21.setPlaybackToRemote(mSessionObj, volumeprovidercompat.getVolumeProvider());
        }

        public void setQueue(List list)
        {
            ArrayList arraylist = null;
            if(list != null)
            {
                ArrayList arraylist1 = new ArrayList();
                list = list.iterator();
                do
                {
                    arraylist = arraylist1;
                    if(!list.hasNext())
                        break;
                    arraylist1.add(((QueueItem)list.next()).getQueueItem());
                } while(true);
            }
            MediaSessionCompatApi21.setQueue(mSessionObj, arraylist);
        }

        public void setQueueTitle(CharSequence charsequence)
        {
            MediaSessionCompatApi21.setQueueTitle(mSessionObj, charsequence);
        }

        public void setRatingType(int i)
        {
            if(android.os.Build.VERSION.SDK_INT < 22)
            {
                return;
            } else
            {
                MediaSessionCompatApi22.setRatingType(mSessionObj, i);
                return;
            }
        }

        public void setSessionActivity(PendingIntent pendingintent)
        {
            MediaSessionCompatApi21.setSessionActivity(mSessionObj, pendingintent);
        }

        private PendingIntent mMediaButtonIntent;
        private final Object mSessionObj;
        private final Token mToken;

        public MediaSessionImplApi21(Context context, String s)
        {
            mSessionObj = MediaSessionCompatApi21.createSession(context, s);
            mToken = new Token(MediaSessionCompatApi21.getSessionToken(mSessionObj));
        }

        public MediaSessionImplApi21(Object obj)
        {
            mSessionObj = MediaSessionCompatApi21.verifySession(obj);
            mToken = new Token(MediaSessionCompatApi21.getSessionToken(mSessionObj));
        }
    }

    static class MediaSessionImplBase
        implements MediaSessionImpl
    {

        private void sendEvent(String s, Bundle bundle)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onEvent(s, bundle);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendExtras(Bundle bundle)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onExtrasChanged(bundle);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendMetadata(MediaMetadataCompat mediametadatacompat)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onMetadataChanged(mediametadatacompat);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendQueue(List list)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onQueueChanged(list);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendQueueTitle(CharSequence charsequence)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onQueueTitleChanged(charsequence);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private void sendSessionDestroyed()
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onSessionDestroyed();
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
            mControllerCallbacks.kill();
        }

        private void sendState(PlaybackStateCompat playbackstatecompat)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onPlaybackStateChanged(playbackstatecompat);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        private boolean update()
        {
            boolean flag1 = false;
            if(!mIsActive) goto _L2; else goto _L1
_L1:
            if(mIsMbrRegistered || (mFlags & 1) == 0) goto _L4; else goto _L3
_L3:
            boolean flag;
            if(android.os.Build.VERSION.SDK_INT >= 18)
                MediaSessionCompatApi18.registerMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
            else
                ((AudioManager)mContext.getSystemService("audio")).registerMediaButtonEventReceiver(mMediaButtonReceiverComponentName);
            mIsMbrRegistered = true;
_L13:
            flag = flag1;
            if(android.os.Build.VERSION.SDK_INT < 14) goto _L6; else goto _L5
_L5:
            if(mIsRccRegistered || (mFlags & 2) == 0) goto _L8; else goto _L7
_L7:
            MediaSessionCompatApi14.registerRemoteControlClient(mContext, mRccObj);
            mIsRccRegistered = true;
            flag = true;
_L6:
            return flag;
_L4:
            if(mIsMbrRegistered && (mFlags & 1) == 0)
            {
                if(android.os.Build.VERSION.SDK_INT >= 18)
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
                else
                    ((AudioManager)mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(mMediaButtonReceiverComponentName);
                mIsMbrRegistered = false;
            }
            continue; /* Loop/switch isn't completed */
_L8:
            flag = flag1;
            if(!mIsRccRegistered) goto _L6; else goto _L9
_L9:
            flag = flag1;
            if((mFlags & 2) != 0) goto _L6; else goto _L10
_L10:
            MediaSessionCompatApi14.setState(mRccObj, 0);
            MediaSessionCompatApi14.unregisterRemoteControlClient(mContext, mRccObj);
            mIsRccRegistered = false;
            return false;
_L2:
            if(mIsMbrRegistered)
            {
                if(android.os.Build.VERSION.SDK_INT >= 18)
                    MediaSessionCompatApi18.unregisterMediaButtonEventReceiver(mContext, mMediaButtonReceiverIntent, mMediaButtonReceiverComponentName);
                else
                    ((AudioManager)mContext.getSystemService("audio")).unregisterMediaButtonEventReceiver(mMediaButtonReceiverComponentName);
                mIsMbrRegistered = false;
            }
            flag = flag1;
            if(!mIsRccRegistered) goto _L6; else goto _L11
_L11:
            MediaSessionCompatApi14.setState(mRccObj, 0);
            MediaSessionCompatApi14.unregisterRemoteControlClient(mContext, mRccObj);
            mIsRccRegistered = false;
            return false;
            if(true) goto _L13; else goto _L12
_L12:
        }

        void adjustVolume(int i, int j)
        {
            if(mVolumeType == 2)
            {
                if(mVolumeProvider != null)
                    mVolumeProvider.onAdjustVolume(i);
                return;
            } else
            {
                mAudioManager.adjustStreamVolume(mLocalStream, i, j);
                return;
            }
        }

        public String getCallingPackage()
        {
            return null;
        }

        public Object getMediaSession()
        {
            return null;
        }

        public Object getRemoteControlClient()
        {
            return mRccObj;
        }

        public Token getSessionToken()
        {
            return mToken;
        }

        PlaybackStateCompat getStateWithUpdatedPosition()
        {
            long l1 = -1L;
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            PlaybackStateCompat playbackstatecompat = mState;
            long l = l1;
            if(mMetadata == null)
                break MISSING_BLOCK_LABEL_54;
            l = l1;
            if(mMetadata.containsKey("android.media.metadata.DURATION"))
                l = mMetadata.getLong("android.media.metadata.DURATION");
            obj;
            JVM INSTR monitorexit ;
            Object obj1;
            obj1 = null;
            obj = obj1;
            if(playbackstatecompat == null) goto _L2; else goto _L1
_L1:
            if(playbackstatecompat.getState() == 3 || playbackstatecompat.getState() == 4) goto _L4; else goto _L3
_L3:
            obj = obj1;
            if(playbackstatecompat.getState() != 5) goto _L2; else goto _L4
_L4:
            long l2;
            l1 = playbackstatecompat.getLastPositionUpdateTime();
            l2 = SystemClock.elapsedRealtime();
            obj = obj1;
            if(l1 <= 0L) goto _L2; else goto _L5
_L5:
            l1 = (long)(playbackstatecompat.getPlaybackSpeed() * (float)(l2 - l1)) + playbackstatecompat.getPosition();
            if(l < 0L || l1 <= l) goto _L7; else goto _L6
_L6:
            obj = new PlaybackStateCompat.Builder(playbackstatecompat);
            ((PlaybackStateCompat.Builder) (obj)).setState(playbackstatecompat.getState(), l, playbackstatecompat.getPlaybackSpeed(), l2);
            obj = ((PlaybackStateCompat.Builder) (obj)).build();
_L2:
            Exception exception;
            if(obj == null)
                return playbackstatecompat;
            else
                return ((PlaybackStateCompat) (obj));
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
_L7:
            l = l1;
            if(l1 < 0L)
                l = 0L;
            if(true) goto _L6; else goto _L8
_L8:
        }

        public boolean isActive()
        {
            return mIsActive;
        }

        void postToHandler(int i)
        {
            postToHandler(i, null);
        }

        void postToHandler(int i, Object obj)
        {
            postToHandler(i, obj, null);
        }

        void postToHandler(int i, Object obj, Bundle bundle)
        {
            synchronized(mLock)
            {
                if(mHandler != null)
                    mHandler.post(i, obj, bundle);
            }
            return;
            obj;
            obj1;
            JVM INSTR monitorexit ;
            throw obj;
        }

        public void release()
        {
            mIsActive = false;
            mDestroyed = true;
            update();
            sendSessionDestroyed();
        }

        public void sendSessionEvent(String s, Bundle bundle)
        {
            sendEvent(s, bundle);
        }

        void sendVolumeInfoChanged(ParcelableVolumeInfo parcelablevolumeinfo)
        {
            int i = mControllerCallbacks.beginBroadcast() - 1;
            while(i >= 0) 
            {
                IMediaControllerCallback imediacontrollercallback = (IMediaControllerCallback)mControllerCallbacks.getBroadcastItem(i);
                try
                {
                    imediacontrollercallback.onVolumeInfoChanged(parcelablevolumeinfo);
                }
                catch(RemoteException remoteexception) { }
                i--;
            }
            mControllerCallbacks.finishBroadcast();
        }

        public void setActive(boolean flag)
        {
            if(flag != mIsActive)
            {
                mIsActive = flag;
                if(update())
                {
                    setMetadata(mMetadata);
                    setPlaybackState(mState);
                    return;
                }
            }
        }

        public void setCallback(Callback callback, Handler handler)
        {
            mCallback = callback;
            if(callback != null) goto _L2; else goto _L1
_L1:
            if(android.os.Build.VERSION.SDK_INT >= 18)
                MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(mRccObj, null);
            if(android.os.Build.VERSION.SDK_INT >= 19)
                MediaSessionCompatApi19.setOnMetadataUpdateListener(mRccObj, null);
_L4:
            return;
_L2:
            callback = handler;
            if(handler == null)
                callback = new Handler();
            synchronized(mLock)
            {
                mHandler = new MessageHandler(callback.getLooper());
            }
            callback = new MediaSessionCompatApi19.Callback() {

                public void onSeekTo(long l)
                {
                    postToHandler(18, Long.valueOf(l));
                }

                public void onSetRating(Object obj)
                {
                    postToHandler(19, RatingCompat.fromRating(obj));
                }

                final MediaSessionImplBase this$0;

            
            {
                this$0 = MediaSessionImplBase.this;
                super();
            }
            }
;
            if(android.os.Build.VERSION.SDK_INT >= 18)
            {
                handler = ((Handler) (MediaSessionCompatApi18.createPlaybackPositionUpdateListener(callback)));
                MediaSessionCompatApi18.setOnPlaybackPositionUpdateListener(mRccObj, handler);
            }
            if(android.os.Build.VERSION.SDK_INT < 19) goto _L4; else goto _L3
_L3:
            callback = ((Callback) (MediaSessionCompatApi19.createMetadataUpdateListener(callback)));
            MediaSessionCompatApi19.setOnMetadataUpdateListener(mRccObj, callback);
            return;
            callback;
            handler;
            JVM INSTR monitorexit ;
            throw callback;
        }

        public void setExtras(Bundle bundle)
        {
            mExtras = bundle;
            sendExtras(bundle);
        }

        public void setFlags(int i)
        {
            synchronized(mLock)
            {
                mFlags = i;
            }
            update();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public void setMediaButtonReceiver(PendingIntent pendingintent)
        {
        }

        public void setMetadata(MediaMetadataCompat mediametadatacompat)
        {
            Object obj3 = null;
            Object obj1 = null;
            obj = mediametadatacompat;
            if(mediametadatacompat != null)
                obj = (new android.support.v4.media.MediaMetadataCompat.Builder(mediametadatacompat, MediaSessionCompat.sMaxBitmapSize)).build();
            synchronized(mLock)
            {
                mMetadata = ((MediaMetadataCompat) (obj));
            }
            sendMetadata(((MediaMetadataCompat) (obj)));
            if(mIsActive)
            {
                if(android.os.Build.VERSION.SDK_INT >= 19)
                {
                    obj3 = mRccObj;
                    long l;
                    if(obj == null)
                        mediametadatacompat = obj1;
                    else
                        mediametadatacompat = ((MediaMetadataCompat) (obj)).getBundle();
                    if(mState == null)
                        l = 0L;
                    else
                        l = mState.getActions();
                    MediaSessionCompatApi19.setMetadata(obj3, mediametadatacompat, l);
                    return;
                }
                if(android.os.Build.VERSION.SDK_INT >= 14)
                {
                    Object obj2 = mRccObj;
                    if(obj == null)
                        mediametadatacompat = ((MediaMetadataCompat) (obj3));
                    else
                        mediametadatacompat = ((MediaMetadataCompat) (obj)).getBundle();
                    MediaSessionCompatApi14.setMetadata(obj2, mediametadatacompat);
                    return;
                }
            }
            return;
            obj;
            mediametadatacompat;
            JVM INSTR monitorexit ;
            throw obj;
        }

        public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
        {
            synchronized(mLock)
            {
                mState = playbackstatecompat;
            }
            sendState(playbackstatecompat);
            if(mIsActive) goto _L2; else goto _L1
_L1:
            return;
            playbackstatecompat;
            obj;
            JVM INSTR monitorexit ;
            throw playbackstatecompat;
_L2:
            if(playbackstatecompat != null)
                break; /* Loop/switch isn't completed */
            if(android.os.Build.VERSION.SDK_INT >= 14)
            {
                MediaSessionCompatApi14.setState(mRccObj, 0);
                MediaSessionCompatApi14.setTransportControlFlags(mRccObj, 0L);
                return;
            }
            if(true) goto _L1; else goto _L3
_L3:
            if(android.os.Build.VERSION.SDK_INT < 18) goto _L5; else goto _L4
_L4:
            MediaSessionCompatApi18.setState(mRccObj, playbackstatecompat.getState(), playbackstatecompat.getPosition(), playbackstatecompat.getPlaybackSpeed(), playbackstatecompat.getLastPositionUpdateTime());
_L7:
            if(android.os.Build.VERSION.SDK_INT >= 19)
            {
                MediaSessionCompatApi19.setTransportControlFlags(mRccObj, playbackstatecompat.getActions());
                return;
            }
            break; /* Loop/switch isn't completed */
_L5:
            if(android.os.Build.VERSION.SDK_INT >= 14)
                MediaSessionCompatApi14.setState(mRccObj, playbackstatecompat.getState());
            if(true) goto _L7; else goto _L6
_L6:
            if(android.os.Build.VERSION.SDK_INT >= 18)
            {
                MediaSessionCompatApi18.setTransportControlFlags(mRccObj, playbackstatecompat.getActions());
                return;
            }
            if(android.os.Build.VERSION.SDK_INT >= 14)
            {
                MediaSessionCompatApi14.setTransportControlFlags(mRccObj, playbackstatecompat.getActions());
                return;
            }
            if(true) goto _L1; else goto _L8
_L8:
        }

        public void setPlaybackToLocal(int i)
        {
            if(mVolumeProvider != null)
                mVolumeProvider.setCallback(null);
            mVolumeType = 1;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, 2, mAudioManager.getStreamMaxVolume(mLocalStream), mAudioManager.getStreamVolume(mLocalStream)));
        }

        public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
        {
            if(volumeprovidercompat == null)
                throw new IllegalArgumentException("volumeProvider may not be null");
            if(mVolumeProvider != null)
                mVolumeProvider.setCallback(null);
            mVolumeType = 2;
            mVolumeProvider = volumeprovidercompat;
            sendVolumeInfoChanged(new ParcelableVolumeInfo(mVolumeType, mLocalStream, mVolumeProvider.getVolumeControl(), mVolumeProvider.getMaxVolume(), mVolumeProvider.getCurrentVolume()));
            volumeprovidercompat.setCallback(mVolumeCallback);
        }

        public void setQueue(List list)
        {
            mQueue = list;
            sendQueue(list);
        }

        public void setQueueTitle(CharSequence charsequence)
        {
            mQueueTitle = charsequence;
            sendQueueTitle(charsequence);
        }

        public void setRatingType(int i)
        {
            mRatingType = i;
        }

        public void setSessionActivity(PendingIntent pendingintent)
        {
            synchronized(mLock)
            {
                mSessionActivity = pendingintent;
            }
            return;
            pendingintent;
            obj;
            JVM INSTR monitorexit ;
            throw pendingintent;
        }

        void setVolumeTo(int i, int j)
        {
            if(mVolumeType == 2)
            {
                if(mVolumeProvider != null)
                    mVolumeProvider.onSetVolumeTo(i);
                return;
            } else
            {
                mAudioManager.setStreamVolume(mLocalStream, i, j);
                return;
            }
        }

        final AudioManager mAudioManager;
        volatile Callback mCallback;
        private final Context mContext;
        final RemoteCallbackList mControllerCallbacks = new RemoteCallbackList();
        boolean mDestroyed;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        private boolean mIsActive;
        private boolean mIsMbrRegistered;
        private boolean mIsRccRegistered;
        int mLocalStream;
        final Object mLock = new Object();
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        private final Object mRccObj;
        PendingIntent mSessionActivity;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub = new MediaSessionStub();
        final String mTag;
        private final Token mToken;
        private android.support.v4.media.VolumeProviderCompat.Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;

        public MediaSessionImplBase(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
        {
            mDestroyed = false;
            mIsActive = false;
            mIsRccRegistered = false;
            mIsMbrRegistered = false;
            mVolumeCallback = new _cls1();
            ComponentName componentname1 = componentname;
            if(componentname == null)
            {
                componentname = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
                componentname1 = componentname;
                if(componentname == null)
                {
                    Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                    componentname1 = componentname;
                }
            }
            componentname = pendingintent;
            if(componentname1 != null)
            {
                componentname = pendingintent;
                if(pendingintent == null)
                {
                    componentname = new Intent("android.intent.action.MEDIA_BUTTON");
                    componentname.setComponent(componentname1);
                    componentname = PendingIntent.getBroadcast(context, 0, componentname, 0);
                }
            }
            if(componentname1 == null)
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            mContext = context;
            mPackageName = context.getPackageName();
            mAudioManager = (AudioManager)context.getSystemService("audio");
            mTag = s;
            mMediaButtonReceiverComponentName = componentname1;
            mMediaButtonReceiverIntent = componentname;
            mToken = new Token(mStub);
            mRatingType = 0;
            mVolumeType = 1;
            mLocalStream = 3;
            if(android.os.Build.VERSION.SDK_INT >= 14)
            {
                mRccObj = MediaSessionCompatApi14.createRemoteControlClient(componentname);
                return;
            } else
            {
                mRccObj = null;
                return;
            }
        }
    }

    private static final class MediaSessionImplBase.Command
    {

        public final String command;
        public final Bundle extras;
        public final ResultReceiver stub;

        public MediaSessionImplBase.Command(String s, Bundle bundle, ResultReceiver resultreceiver)
        {
            command = s;
            extras = bundle;
            stub = resultreceiver;
        }
    }

    class MediaSessionImplBase.MediaSessionStub extends IMediaSession.Stub
    {

        public void adjustVolume(int i, int j, String s)
        {
            MediaSessionImplBase.this.adjustVolume(i, j);
        }

        public void fastForward()
            throws RemoteException
        {
            postToHandler(16);
        }

        public Bundle getExtras()
        {
            Bundle bundle;
            synchronized(mLock)
            {
                bundle = mExtras;
            }
            return bundle;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public long getFlags()
        {
            long l;
            synchronized(mLock)
            {
                l = mFlags;
            }
            return l;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public PendingIntent getLaunchPendingIntent()
        {
            PendingIntent pendingintent;
            synchronized(mLock)
            {
                pendingintent = mSessionActivity;
            }
            return pendingintent;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public MediaMetadataCompat getMetadata()
        {
            return mMetadata;
        }

        public String getPackageName()
        {
            return mPackageName;
        }

        public PlaybackStateCompat getPlaybackState()
        {
            return getStateWithUpdatedPosition();
        }

        public List getQueue()
        {
            List list;
            synchronized(mLock)
            {
                list = mQueue;
            }
            return list;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public CharSequence getQueueTitle()
        {
            return mQueueTitle;
        }

        public int getRatingType()
        {
            return mRatingType;
        }

        public String getTag()
        {
            return mTag;
        }

        public ParcelableVolumeInfo getVolumeAttributes()
        {
            Object obj = mLock;
            obj;
            JVM INSTR monitorenter ;
            int l;
            int i1;
            VolumeProviderCompat volumeprovidercompat;
            l = mVolumeType;
            i1 = mLocalStream;
            volumeprovidercompat = mVolumeProvider;
            if(l != 2) goto _L2; else goto _L1
_L1:
            int i;
            int j;
            int k;
            i = volumeprovidercompat.getVolumeControl();
            j = volumeprovidercompat.getMaxVolume();
            k = volumeprovidercompat.getCurrentVolume();
_L3:
            obj;
            JVM INSTR monitorexit ;
            return new ParcelableVolumeInfo(l, i1, i, j, k);
_L2:
            i = 2;
            j = mAudioManager.getStreamMaxVolume(i1);
            k = mAudioManager.getStreamVolume(i1);
              goto _L3
            Exception exception;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public boolean isTransportControlEnabled()
        {
            return (mFlags & 2) != 0;
        }

        public void next()
            throws RemoteException
        {
            postToHandler(14);
        }

        public void pause()
            throws RemoteException
        {
            postToHandler(12);
        }

        public void play()
            throws RemoteException
        {
            postToHandler(7);
        }

        public void playFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(8, s, bundle);
        }

        public void playFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(9, s, bundle);
        }

        public void playFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(10, uri, bundle);
        }

        public void prepare()
            throws RemoteException
        {
            postToHandler(3);
        }

        public void prepareFromMediaId(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(4, s, bundle);
        }

        public void prepareFromSearch(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(5, s, bundle);
        }

        public void prepareFromUri(Uri uri, Bundle bundle)
            throws RemoteException
        {
            postToHandler(6, uri, bundle);
        }

        public void previous()
            throws RemoteException
        {
            postToHandler(15);
        }

        public void rate(RatingCompat ratingcompat)
            throws RemoteException
        {
            postToHandler(19, ratingcompat);
        }

        public void registerCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            if(mDestroyed)
            {
                try
                {
                    imediacontrollercallback.onSessionDestroyed();
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(IMediaControllerCallback imediacontrollercallback)
                {
                    return;
                }
            } else
            {
                mControllerCallbacks.register(imediacontrollercallback);
                return;
            }
        }

        public void rewind()
            throws RemoteException
        {
            postToHandler(17);
        }

        public void seekTo(long l)
            throws RemoteException
        {
            postToHandler(18, Long.valueOf(l));
        }

        public void sendCommand(String s, Bundle bundle, ResultReceiverWrapper resultreceiverwrapper)
        {
            postToHandler(1, new MediaSessionImplBase.Command(s, bundle, resultreceiverwrapper.mResultReceiver));
        }

        public void sendCustomAction(String s, Bundle bundle)
            throws RemoteException
        {
            postToHandler(20, s, bundle);
        }

        public boolean sendMediaButton(KeyEvent keyevent)
        {
            boolean flag;
            if((mFlags & 1) != 0)
                flag = true;
            else
                flag = false;
            if(flag)
                postToHandler(21, keyevent);
            return flag;
        }

        public void setVolumeTo(int i, int j, String s)
        {
            MediaSessionImplBase.this.setVolumeTo(i, j);
        }

        public void skipToQueueItem(long l)
        {
            postToHandler(11, Long.valueOf(l));
        }

        public void stop()
            throws RemoteException
        {
            postToHandler(13);
        }

        public void unregisterCallbackListener(IMediaControllerCallback imediacontrollercallback)
        {
            mControllerCallbacks.unregister(imediacontrollercallback);
        }

        final MediaSessionImplBase this$0;

        MediaSessionImplBase.MediaSessionStub()
        {
            this$0 = MediaSessionImplBase.this;
            super();
        }
    }

    private class MediaSessionImplBase.MessageHandler extends Handler
    {

        private void onMediaButtonEvent(KeyEvent keyevent, Callback callback)
        {
            boolean flag2 = true;
            if(keyevent != null && keyevent.getAction() == 0) goto _L2; else goto _L1
_L1:
            return;
_L2:
            boolean flag;
            boolean flag1;
            long l;
            if(mState == null)
                l = 0L;
            else
                l = mState.getActions();
            keyevent.getKeyCode();
            JVM INSTR lookupswitch 9: default 116
        //                       79: 117
        //                       85: 117
        //                       86: 261
        //                       87: 229
        //                       88: 245
        //                       89: 291
        //                       90: 275
        //                       126: 197
        //                       127: 213;
               goto _L3 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L8:
            continue; /* Loop/switch isn't completed */
_L3:
            return;
_L4:
            if(mState != null && mState.getState() == 3)
                flag = true;
            else
                flag = false;
            if((516L & l) != 0L)
                flag1 = true;
            else
                flag1 = false;
            if((514L & l) == 0L)
                flag2 = false;
            if(flag && flag2)
            {
                callback.onPause();
                return;
            }
            continue; /* Loop/switch isn't completed */
_L10:
            if((4L & l) == 0L) goto _L1; else goto _L12
_L12:
            callback.onPlay();
            return;
_L11:
            if((2L & l) == 0L) goto _L1; else goto _L13
_L13:
            callback.onPause();
            return;
_L6:
            if((32L & l) == 0L) goto _L1; else goto _L14
_L14:
            callback.onSkipToNext();
            return;
_L7:
            if((16L & l) == 0L) goto _L1; else goto _L15
_L15:
            callback.onSkipToPrevious();
            return;
_L5:
            if((1L & l) == 0L) goto _L1; else goto _L16
_L16:
            callback.onStop();
            return;
_L9:
            if((64L & l) == 0L) goto _L1; else goto _L17
_L17:
            callback.onFastForward();
            return;
            if((8L & l) == 0L) goto _L1; else goto _L18
_L18:
            callback.onRewind();
            return;
            if(flag || !flag1) goto _L1; else goto _L19
_L19:
            callback.onPlay();
            return;
        }

        public void handleMessage(Message message)
        {
            Callback callback = mCallback;
            if(callback != null) goto _L2; else goto _L1
_L1:
            return;
_L2:
            switch(message.what)
            {
            default:
                return;

            case 1: // '\001'
                message = (MediaSessionImplBase.Command)message.obj;
                callback.onCommand(((MediaSessionImplBase.Command) (message)).command, ((MediaSessionImplBase.Command) (message)).extras, ((MediaSessionImplBase.Command) (message)).stub);
                return;

            case 21: // '\025'
                message = (KeyEvent)message.obj;
                Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                intent.putExtra("android.intent.extra.KEY_EVENT", message);
                if(!callback.onMediaButtonEvent(intent))
                {
                    onMediaButtonEvent(message, callback);
                    return;
                }
                break;

            case 3: // '\003'
                callback.onPrepare();
                return;

            case 4: // '\004'
                callback.onPrepareFromMediaId((String)message.obj, message.getData());
                return;

            case 5: // '\005'
                callback.onPrepareFromSearch((String)message.obj, message.getData());
                return;

            case 6: // '\006'
                callback.onPrepareFromUri((Uri)message.obj, message.getData());
                return;

            case 7: // '\007'
                callback.onPlay();
                return;

            case 8: // '\b'
                callback.onPlayFromMediaId((String)message.obj, message.getData());
                return;

            case 9: // '\t'
                callback.onPlayFromSearch((String)message.obj, message.getData());
                return;

            case 10: // '\n'
                callback.onPlayFromUri((Uri)message.obj, message.getData());
                return;

            case 11: // '\013'
                callback.onSkipToQueueItem(((Long)message.obj).longValue());
                return;

            case 12: // '\f'
                callback.onPause();
                return;

            case 13: // '\r'
                callback.onStop();
                return;

            case 14: // '\016'
                callback.onSkipToNext();
                return;

            case 15: // '\017'
                callback.onSkipToPrevious();
                return;

            case 16: // '\020'
                callback.onFastForward();
                return;

            case 17: // '\021'
                callback.onRewind();
                return;

            case 18: // '\022'
                callback.onSeekTo(((Long)message.obj).longValue());
                return;

            case 19: // '\023'
                callback.onSetRating((RatingCompat)message.obj);
                return;

            case 20: // '\024'
                callback.onCustomAction((String)message.obj, message.getData());
                return;

            case 2: // '\002'
                adjustVolume(((Integer)message.obj).intValue(), 0);
                return;

            case 22: // '\026'
                setVolumeTo(((Integer)message.obj).intValue(), 0);
                return;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        public void post(int i)
        {
            post(i, null);
        }

        public void post(int i, Object obj)
        {
            obtainMessage(i, obj).sendToTarget();
        }

        public void post(int i, Object obj, int j)
        {
            obtainMessage(i, j, 0, obj).sendToTarget();
        }

        public void post(int i, Object obj, Bundle bundle)
        {
            obj = obtainMessage(i, obj);
            ((Message) (obj)).setData(bundle);
            ((Message) (obj)).sendToTarget();
        }

        private static final int KEYCODE_MEDIA_PAUSE = 127;
        private static final int KEYCODE_MEDIA_PLAY = 126;
        private static final int MSG_ADJUST_VOLUME = 2;
        private static final int MSG_COMMAND = 1;
        private static final int MSG_CUSTOM_ACTION = 20;
        private static final int MSG_FAST_FORWARD = 16;
        private static final int MSG_MEDIA_BUTTON = 21;
        private static final int MSG_NEXT = 14;
        private static final int MSG_PAUSE = 12;
        private static final int MSG_PLAY = 7;
        private static final int MSG_PLAY_MEDIA_ID = 8;
        private static final int MSG_PLAY_SEARCH = 9;
        private static final int MSG_PLAY_URI = 10;
        private static final int MSG_PREPARE = 3;
        private static final int MSG_PREPARE_MEDIA_ID = 4;
        private static final int MSG_PREPARE_SEARCH = 5;
        private static final int MSG_PREPARE_URI = 6;
        private static final int MSG_PREVIOUS = 15;
        private static final int MSG_RATE = 19;
        private static final int MSG_REWIND = 17;
        private static final int MSG_SEEK_TO = 18;
        private static final int MSG_SET_VOLUME = 22;
        private static final int MSG_SKIP_TO_ITEM = 11;
        private static final int MSG_STOP = 13;
        final MediaSessionImplBase this$0;

        public MediaSessionImplBase.MessageHandler(Looper looper)
        {
            this$0 = MediaSessionImplBase.this;
            super(looper);
        }
    }

    public static interface OnActiveChangeListener
    {

        public abstract void onActiveChanged();
    }

    public static final class QueueItem
        implements Parcelable
    {

        public static QueueItem fromQueueItem(Object obj)
        {
            if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
                return null;
            else
                return new QueueItem(obj, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(obj)), MediaSessionCompatApi21.QueueItem.getQueueId(obj));
        }

        public static List fromQueueItemList(List list)
        {
            if(list != null && android.os.Build.VERSION.SDK_INT >= 21) goto _L2; else goto _L1
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
                arraylist.add(fromQueueItem(iterator.next()));
            } while(true);
            if(true) goto _L4; else goto _L3
_L3:
        }

        public static QueueItem obtain(Object obj)
        {
            return fromQueueItem(obj);
        }

        public int describeContents()
        {
            return 0;
        }

        public MediaDescriptionCompat getDescription()
        {
            return mDescription;
        }

        public long getQueueId()
        {
            return mId;
        }

        public Object getQueueItem()
        {
            if(mItem != null || android.os.Build.VERSION.SDK_INT < 21)
            {
                return mItem;
            } else
            {
                mItem = MediaSessionCompatApi21.QueueItem.createItem(mDescription.getMediaDescription(), mId);
                return mItem;
            }
        }

        public String toString()
        {
            return (new StringBuilder()).append("MediaSession.QueueItem {Description=").append(mDescription).append(", Id=").append(mId).append(" }").toString();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mDescription.writeToParcel(parcel, i);
            parcel.writeLong(mId);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public QueueItem createFromParcel(Parcel parcel)
            {
                return new QueueItem(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public QueueItem[] newArray(int i)
            {
                return new QueueItem[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;


        QueueItem(Parcel parcel)
        {
            mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            mId = parcel.readLong();
        }

        public QueueItem(MediaDescriptionCompat mediadescriptioncompat, long l)
        {
            this(null, mediadescriptioncompat, l);
        }

        private QueueItem(Object obj, MediaDescriptionCompat mediadescriptioncompat, long l)
        {
            if(mediadescriptioncompat == null)
                throw new IllegalArgumentException("Description cannot be null.");
            if(l == -1L)
            {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            } else
            {
                mDescription = mediadescriptioncompat;
                mId = l;
                mItem = obj;
                return;
            }
        }
    }

    static final class ResultReceiverWrapper
        implements Parcelable
    {

        public int describeContents()
        {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            mResultReceiver.writeToParcel(parcel, i);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public ResultReceiverWrapper createFromParcel(Parcel parcel)
            {
                return new ResultReceiverWrapper(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public ResultReceiverWrapper[] newArray(int i)
            {
                return new ResultReceiverWrapper[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private ResultReceiver mResultReceiver;



        ResultReceiverWrapper(Parcel parcel)
        {
            mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
        }

        public ResultReceiverWrapper(ResultReceiver resultreceiver)
        {
            mResultReceiver = resultreceiver;
        }
    }

    public static interface SessionFlags
        extends Annotation
    {
    }

    public static final class Token
        implements Parcelable
    {

        public static Token fromToken(Object obj)
        {
            if(obj == null || android.os.Build.VERSION.SDK_INT < 21)
                return null;
            else
                return new Token(MediaSessionCompatApi21.verifyToken(obj));
        }

        public int describeContents()
        {
            return 0;
        }

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(!(obj instanceof Token))
                    return false;
                obj = (Token)obj;
                if(mInner == null)
                {
                    if(((Token) (obj)).mInner != null)
                        return false;
                } else
                if(((Token) (obj)).mInner == null)
                    return false;
                else
                    return mInner.equals(((Token) (obj)).mInner);
            }
            return true;
        }

        public Object getToken()
        {
            return mInner;
        }

        public int hashCode()
        {
            if(mInner == null)
                return 0;
            else
                return mInner.hashCode();
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            if(android.os.Build.VERSION.SDK_INT >= 21)
            {
                parcel.writeParcelable((Parcelable)mInner, i);
                return;
            } else
            {
                parcel.writeStrongBinder((IBinder)mInner);
                return;
            }
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public Token createFromParcel(Parcel parcel)
            {
                if(android.os.Build.VERSION.SDK_INT >= 21)
                    parcel = parcel.readParcelable(null);
                else
                    parcel = parcel.readStrongBinder();
                return new Token(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public Token[] newArray(int i)
            {
                return new Token[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private final Object mInner;


        Token(Object obj)
        {
            mInner = obj;
        }
    }


    private MediaSessionCompat(Context context, MediaSessionImpl mediasessionimpl)
    {
        mActiveListeners = new ArrayList();
        mImpl = mediasessionimpl;
        mController = new MediaControllerCompat(context, this);
    }

    public MediaSessionCompat(Context context, String s)
    {
        this(context, s, null, null);
    }

    public MediaSessionCompat(Context context, String s, ComponentName componentname, PendingIntent pendingintent)
    {
        mActiveListeners = new ArrayList();
        if(context == null)
            throw new IllegalArgumentException("context must not be null");
        if(TextUtils.isEmpty(s))
            throw new IllegalArgumentException("tag must not be null or empty");
        if(android.os.Build.VERSION.SDK_INT >= 21)
            mImpl = new MediaSessionImplApi21(context, s);
        else
            mImpl = new MediaSessionImplBase(context, s, componentname, pendingintent);
        mController = new MediaControllerCompat(context, this);
        if(sMaxBitmapSize == 0)
            sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320F, context.getResources().getDisplayMetrics());
    }

    public static MediaSessionCompat fromMediaSession(Context context, Object obj)
    {
        if(context == null || obj == null || android.os.Build.VERSION.SDK_INT < 21)
            return null;
        else
            return new MediaSessionCompat(context, new MediaSessionImplApi21(obj));
    }

    public static MediaSessionCompat obtain(Context context, Object obj)
    {
        return fromMediaSession(context, obj);
    }

    public void addOnActiveChangeListener(OnActiveChangeListener onactivechangelistener)
    {
        if(onactivechangelistener == null)
        {
            throw new IllegalArgumentException("Listener may not be null");
        } else
        {
            mActiveListeners.add(onactivechangelistener);
            return;
        }
    }

    public String getCallingPackage()
    {
        return mImpl.getCallingPackage();
    }

    public MediaControllerCompat getController()
    {
        return mController;
    }

    public Object getMediaSession()
    {
        return mImpl.getMediaSession();
    }

    public Object getRemoteControlClient()
    {
        return mImpl.getRemoteControlClient();
    }

    public Token getSessionToken()
    {
        return mImpl.getSessionToken();
    }

    public boolean isActive()
    {
        return mImpl.isActive();
    }

    public void release()
    {
        mImpl.release();
    }

    public void removeOnActiveChangeListener(OnActiveChangeListener onactivechangelistener)
    {
        if(onactivechangelistener == null)
        {
            throw new IllegalArgumentException("Listener may not be null");
        } else
        {
            mActiveListeners.remove(onactivechangelistener);
            return;
        }
    }

    public void sendSessionEvent(String s, Bundle bundle)
    {
        if(TextUtils.isEmpty(s))
        {
            throw new IllegalArgumentException("event cannot be null or empty");
        } else
        {
            mImpl.sendSessionEvent(s, bundle);
            return;
        }
    }

    public void setActive(boolean flag)
    {
        mImpl.setActive(flag);
        for(Iterator iterator = mActiveListeners.iterator(); iterator.hasNext(); ((OnActiveChangeListener)iterator.next()).onActiveChanged());
    }

    public void setCallback(Callback callback)
    {
        setCallback(callback, null);
    }

    public void setCallback(Callback callback, Handler handler)
    {
        MediaSessionImpl mediasessionimpl = mImpl;
        if(handler == null)
            handler = new Handler();
        mediasessionimpl.setCallback(callback, handler);
    }

    public void setExtras(Bundle bundle)
    {
        mImpl.setExtras(bundle);
    }

    public void setFlags(int i)
    {
        mImpl.setFlags(i);
    }

    public void setMediaButtonReceiver(PendingIntent pendingintent)
    {
        mImpl.setMediaButtonReceiver(pendingintent);
    }

    public void setMetadata(MediaMetadataCompat mediametadatacompat)
    {
        mImpl.setMetadata(mediametadatacompat);
    }

    public void setPlaybackState(PlaybackStateCompat playbackstatecompat)
    {
        mImpl.setPlaybackState(playbackstatecompat);
    }

    public void setPlaybackToLocal(int i)
    {
        mImpl.setPlaybackToLocal(i);
    }

    public void setPlaybackToRemote(VolumeProviderCompat volumeprovidercompat)
    {
        if(volumeprovidercompat == null)
        {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        } else
        {
            mImpl.setPlaybackToRemote(volumeprovidercompat);
            return;
        }
    }

    public void setQueue(List list)
    {
        mImpl.setQueue(list);
    }

    public void setQueueTitle(CharSequence charsequence)
    {
        mImpl.setQueueTitle(charsequence);
    }

    public void setRatingType(int i)
    {
        mImpl.setRatingType(i);
    }

    public void setSessionActivity(PendingIntent pendingintent)
    {
        mImpl.setSessionActivity(pendingintent);
    }

    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;

    // Unreferenced inner class android/support/v4/media/session/MediaSessionCompat$MediaSessionImplBase$1

/* anonymous class */
    class MediaSessionImplBase._cls1 extends android.support.v4.media.VolumeProviderCompat.Callback
    {

        public void onVolumeChanged(VolumeProviderCompat volumeprovidercompat)
        {
            if(mVolumeProvider != volumeprovidercompat)
            {
                return;
            } else
            {
                volumeprovidercompat = new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeprovidercompat.getVolumeControl(), volumeprovidercompat.getMaxVolume(), volumeprovidercompat.getCurrentVolume());
                sendVolumeInfoChanged(volumeprovidercompat);
                return;
            }
        }

        final MediaSessionImplBase this$0;

            
            {
                this$0 = MediaSessionImplBase.this;
                super();
            }
    }

}
