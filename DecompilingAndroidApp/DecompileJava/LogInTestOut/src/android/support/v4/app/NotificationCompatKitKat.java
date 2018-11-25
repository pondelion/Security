// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            NotificationCompatJellybean, NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions

class NotificationCompatKitKat
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        public void addAction(NotificationCompatBase.Action action)
        {
            mActionExtrasList.add(NotificationCompatJellybean.writeActionAndGetExtras(b, action));
        }

        public Notification build()
        {
            Object obj = NotificationCompatJellybean.buildActionExtrasMap(mActionExtrasList);
            if(obj != null)
                mExtras.putSparseParcelableArray("android.support.actionExtras", ((SparseArray) (obj)));
            b.setExtras(mExtras);
            obj = b.build();
            if(mContentView != null)
                obj.contentView = mContentView;
            if(mBigContentView != null)
                obj.bigContentView = mBigContentView;
            return ((Notification) (obj));
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;
        private List mActionExtrasList;
        private RemoteViews mBigContentView;
        private RemoteViews mContentView;
        private Bundle mExtras;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, ArrayList arraylist, Bundle bundle, String s, 
                boolean flag4, String s1, RemoteViews remoteviews1, RemoteViews remoteviews2)
        {
            mActionExtrasList = new ArrayList();
            context = (new android.app.Notification.Builder(context)).setWhen(notification.when).setShowWhen(flag1).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteviews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if((notification.flags & 2) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setOngoing(flag1);
            if((notification.flags & 8) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setOnlyAlertOnce(flag1);
            if((notification.flags & 0x10) != 0)
                flag1 = true;
            else
                flag1 = false;
            context = context.setAutoCancel(flag1).setDefaults(notification.defaults).setContentTitle(charsequence).setContentText(charsequence1).setSubText(charsequence3).setContentInfo(charsequence2).setContentIntent(pendingintent).setDeleteIntent(notification.deleteIntent);
            if((notification.flags & 0x80) != 0)
                flag1 = true;
            else
                flag1 = false;
            b = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag);
            mExtras = new Bundle();
            if(bundle != null)
                mExtras.putAll(bundle);
            if(arraylist != null && !arraylist.isEmpty())
                mExtras.putStringArray("android.people", (String[])arraylist.toArray(new String[arraylist.size()]));
            if(flag3)
                mExtras.putBoolean("android.support.localOnly", true);
            if(s != null)
            {
                mExtras.putString("android.support.groupKey", s);
                if(flag4)
                    mExtras.putBoolean("android.support.isGroupSummary", true);
                else
                    mExtras.putBoolean("android.support.useSideChannel", true);
            }
            if(s1 != null)
                mExtras.putString("android.support.sortKey", s1);
            mContentView = remoteviews1;
            mBigContentView = remoteviews2;
        }
    }


    NotificationCompatKitKat()
    {
    }

    public static NotificationCompatBase.Action getAction(Notification notification, int i, NotificationCompatBase.Action.Factory factory, RemoteInputCompatBase.RemoteInput.Factory factory1)
    {
        android.app.Notification.Action action = notification.actions[i];
        Object obj = null;
        SparseArray sparsearray = notification.extras.getSparseParcelableArray("android.support.actionExtras");
        notification = obj;
        if(sparsearray != null)
            notification = (Bundle)sparsearray.get(i);
        return NotificationCompatJellybean.readAction(factory, factory1, action.icon, action.title, action.actionIntent, notification);
    }

    public static int getActionCount(Notification notification)
    {
        if(notification.actions != null)
            return notification.actions.length;
        else
            return 0;
    }

    public static Bundle getExtras(Notification notification)
    {
        return notification.extras;
    }

    public static String getGroup(Notification notification)
    {
        return notification.extras.getString("android.support.groupKey");
    }

    public static boolean getLocalOnly(Notification notification)
    {
        return notification.extras.getBoolean("android.support.localOnly");
    }

    public static String getSortKey(Notification notification)
    {
        return notification.extras.getString("android.support.sortKey");
    }

    public static boolean isGroupSummary(Notification notification)
    {
        return notification.extras.getBoolean("android.support.isGroupSummary");
    }
}
