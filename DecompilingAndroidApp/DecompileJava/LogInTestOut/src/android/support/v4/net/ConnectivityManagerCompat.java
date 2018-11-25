// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// Referenced classes of package android.support.v4.net:
//            ConnectivityManagerCompatHoneycombMR2, ConnectivityManagerCompatJellyBean

public final class ConnectivityManagerCompat
{
    static class BaseConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            connectivitymanager = connectivitymanager.getActiveNetworkInfo();
            if(connectivitymanager != null) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            switch(connectivitymanager.getType())
            {
            default:
                return true;

            case 1: // '\001'
                return false;

            case 0: // '\0'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 5: // '\005'
            case 6: // '\006'
                break;
            }
            if(true) goto _L1; else goto _L3
_L3:
        }

        BaseConnectivityManagerCompatImpl()
        {
        }
    }

    static interface ConnectivityManagerCompatImpl
    {

        public abstract boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager);
    }

    static class HoneycombMR2ConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return ConnectivityManagerCompatHoneycombMR2.isActiveNetworkMetered(connectivitymanager);
        }

        HoneycombMR2ConnectivityManagerCompatImpl()
        {
        }
    }

    static class JellyBeanConnectivityManagerCompatImpl
        implements ConnectivityManagerCompatImpl
    {

        public boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
        {
            return ConnectivityManagerCompatJellyBean.isActiveNetworkMetered(connectivitymanager);
        }

        JellyBeanConnectivityManagerCompatImpl()
        {
        }
    }


    private ConnectivityManagerCompat()
    {
    }

    public static NetworkInfo getNetworkInfoFromBroadcast(ConnectivityManager connectivitymanager, Intent intent)
    {
        intent = (NetworkInfo)intent.getParcelableExtra("networkInfo");
        if(intent != null)
            return connectivitymanager.getNetworkInfo(intent.getType());
        else
            return null;
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager connectivitymanager)
    {
        return IMPL.isActiveNetworkMetered(connectivitymanager);
    }

    private static final ConnectivityManagerCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new JellyBeanConnectivityManagerCompatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 13)
            IMPL = new HoneycombMR2ConnectivityManagerCompatImpl();
        else
            IMPL = new BaseConnectivityManagerCompatImpl();
    }
}
