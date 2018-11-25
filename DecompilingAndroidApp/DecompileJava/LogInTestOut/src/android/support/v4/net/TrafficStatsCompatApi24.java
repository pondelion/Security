// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import android.net.TrafficStats;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TrafficStatsCompatApi24
{

    public TrafficStatsCompatApi24()
    {
    }

    public static void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        TrafficStats.tagDatagramSocket(datagramsocket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        TrafficStats.untagDatagramSocket(datagramsocket);
    }
}
