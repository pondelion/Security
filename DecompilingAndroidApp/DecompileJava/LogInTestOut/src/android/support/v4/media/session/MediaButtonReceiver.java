// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media.session;

import android.app.PendingIntent;
import android.content.*;
import android.content.pm.*;
import android.util.Log;
import android.view.KeyEvent;
import java.util.List;

// Referenced classes of package android.support.v4.media.session:
//            PlaybackStateCompat, MediaSessionCompat, MediaControllerCompat

public class MediaButtonReceiver extends BroadcastReceiver
{

    public MediaButtonReceiver()
    {
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, long l)
    {
        ComponentName componentname = getMediaButtonReceiverComponent(context);
        if(componentname == null)
        {
            Log.w("MediaButtonReceiver", "A unique media button receiver could not be found in the given context, so couldn't build a pending intent.");
            return null;
        } else
        {
            return buildMediaButtonPendingIntent(context, componentname, l);
        }
    }

    public static PendingIntent buildMediaButtonPendingIntent(Context context, ComponentName componentname, long l)
    {
        if(componentname == null)
        {
            Log.w("MediaButtonReceiver", "The component name of media button receiver should be provided.");
            return null;
        }
        int i = PlaybackStateCompat.toKeyCode(l);
        if(i == 0)
        {
            Log.w("MediaButtonReceiver", (new StringBuilder()).append("Cannot build a media button pending intent with the given action: ").append(l).toString());
            return null;
        } else
        {
            Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(componentname);
            intent.putExtra("android.intent.extra.KEY_EVENT", new KeyEvent(0, i));
            return PendingIntent.getBroadcast(context, i, intent, 0);
        }
    }

    static ComponentName getMediaButtonReceiverComponent(Context context)
    {
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setPackage(context.getPackageName());
        context = context.getPackageManager().queryBroadcastReceivers(intent, 0);
        if(context.size() == 1)
        {
            context = (ResolveInfo)context.get(0);
            return new ComponentName(((ResolveInfo) (context)).activityInfo.packageName, ((ResolveInfo) (context)).activityInfo.name);
        }
        if(context.size() > 1)
            Log.w("MediaButtonReceiver", "More than one BroadcastReceiver that handles android.intent.action.MEDIA_BUTTON was found, returning null.");
        return null;
    }

    public static KeyEvent handleIntent(MediaSessionCompat mediasessioncompat, Intent intent)
    {
        if(mediasessioncompat == null || intent == null || !"android.intent.action.MEDIA_BUTTON".equals(intent.getAction()) || !intent.hasExtra("android.intent.extra.KEY_EVENT"))
        {
            return null;
        } else
        {
            intent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            mediasessioncompat.getController().dispatchMediaButtonEvent(intent);
            return intent;
        }
    }

    public void onReceive(Context context, Intent intent)
    {
        Intent intent1 = new Intent("android.intent.action.MEDIA_BUTTON");
        intent1.setPackage(context.getPackageName());
        PackageManager packagemanager = context.getPackageManager();
        List list = packagemanager.queryIntentServices(intent1, 0);
        Object obj = list;
        if(list.isEmpty())
        {
            intent1.setAction("android.media.browse.MediaBrowserService");
            obj = packagemanager.queryIntentServices(intent1, 0);
        }
        if(((List) (obj)).isEmpty())
            throw new IllegalStateException("Could not find any Service that handles android.intent.action.MEDIA_BUTTON or a media browser service implementation");
        if(((List) (obj)).size() != 1)
        {
            throw new IllegalStateException((new StringBuilder()).append("Expected 1 Service that handles ").append(intent1.getAction()).append(", found ").append(((List) (obj)).size()).toString());
        } else
        {
            obj = (ResolveInfo)((List) (obj)).get(0);
            intent.setComponent(new ComponentName(((ResolveInfo) (obj)).serviceInfo.packageName, ((ResolveInfo) (obj)).serviceInfo.name));
            context.startService(intent);
            return;
        }
    }

    private static final String TAG = "MediaButtonReceiver";
}
