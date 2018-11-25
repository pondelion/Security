// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import java.util.*;

// Referenced classes of package android.support.v4.app:
//            NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions, RemoteInputCompatApi20

class NotificationCompatApi24
{
    public static class Builder
        implements NotificationBuilderWithBuilderAccessor, NotificationBuilderWithActions
    {

        public void addAction(NotificationCompatBase.Action action)
        {
            android.app.Notification.Action.Builder builder = new android.app.Notification.Action.Builder(action.getIcon(), action.getTitle(), action.getActionIntent());
            if(action.getRemoteInputs() != null)
            {
                android.app.RemoteInput aremoteinput[] = RemoteInputCompatApi20.fromCompat(action.getRemoteInputs());
                int j = aremoteinput.length;
                for(int i = 0; i < j; i++)
                    builder.addRemoteInput(aremoteinput[i]);

            }
            Bundle bundle;
            if(action.getExtras() != null)
                bundle = new Bundle(action.getExtras());
            else
                bundle = new Bundle();
            bundle.putBoolean("android.support.allowGeneratedReplies", action.getAllowGeneratedReplies());
            builder.addExtras(bundle);
            builder.setAllowGeneratedReplies(action.getAllowGeneratedReplies());
            b.addAction(builder.build());
        }

        public Notification build()
        {
            return b.build();
        }

        public android.app.Notification.Builder getBuilder()
        {
            return b;
        }

        private android.app.Notification.Builder b;

        public Builder(Context context, Notification notification, CharSequence charsequence, CharSequence charsequence1, CharSequence charsequence2, RemoteViews remoteviews, int i, 
                PendingIntent pendingintent, PendingIntent pendingintent1, Bitmap bitmap, int j, int k, boolean flag, boolean flag1, 
                boolean flag2, int l, CharSequence charsequence3, boolean flag3, String s, ArrayList arraylist, Bundle bundle, 
                int i1, int j1, Notification notification1, String s1, boolean flag4, String s2, CharSequence acharsequence[], 
                RemoteViews remoteviews1, RemoteViews remoteviews2, RemoteViews remoteviews3)
        {
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
            b = context.setFullScreenIntent(pendingintent1, flag1).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(flag2).setPriority(l).setProgress(j, k, flag).setLocalOnly(flag3).setExtras(bundle).setGroup(s1).setGroupSummary(flag4).setSortKey(s2).setCategory(s).setColor(i1).setVisibility(j1).setPublicVersion(notification1).setRemoteInputHistory(acharsequence);
            if(remoteviews1 != null)
                b.setCustomContentView(remoteviews1);
            if(remoteviews2 != null)
                b.setCustomBigContentView(remoteviews2);
            if(remoteviews3 != null)
                b.setCustomHeadsUpContentView(remoteviews3);
            for(context = arraylist.iterator(); context.hasNext(); b.addPerson(notification))
                notification = (String)context.next();

        }
    }


    NotificationCompatApi24()
    {
    }

    public static void addMessagingStyle(NotificationBuilderWithBuilderAccessor notificationbuilderwithbuilderaccessor, CharSequence charsequence, CharSequence charsequence1, List list, List list1, List list2, List list3, List list4)
    {
        charsequence = (new android.app.Notification.MessagingStyle(charsequence)).setConversationTitle(charsequence1);
        for(int i = 0; i < list.size(); i++)
        {
            charsequence1 = new android.app.Notification.MessagingStyle.Message((CharSequence)list.get(i), ((Long)list1.get(i)).longValue(), (CharSequence)list2.get(i));
            if(list3.get(i) != null)
                charsequence1.setData((String)list3.get(i), (Uri)list4.get(i));
            charsequence.addMessage(charsequence1);
        }

        charsequence.setBuilder(notificationbuilderwithbuilderaccessor.getBuilder());
    }

    public static final String CATEGORY_ALARM = "alarm";
    public static final String CATEGORY_CALL = "call";
    public static final String CATEGORY_EMAIL = "email";
    public static final String CATEGORY_ERROR = "err";
    public static final String CATEGORY_EVENT = "event";
    public static final String CATEGORY_MESSAGE = "msg";
    public static final String CATEGORY_PROGRESS = "progress";
    public static final String CATEGORY_PROMO = "promo";
    public static final String CATEGORY_RECOMMENDATION = "recommendation";
    public static final String CATEGORY_SERVICE = "service";
    public static final String CATEGORY_SOCIAL = "social";
    public static final String CATEGORY_STATUS = "status";
    public static final String CATEGORY_SYSTEM = "sys";
    public static final String CATEGORY_TRANSPORT = "transport";
}
