// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import android.net.TrafficStats;
import android.os.ParcelFileDescriptor;
import java.net.*;

// Referenced classes of package android.support.v4.net:
//            DatagramSocketWrapper

class TrafficStatsCompatIcs
{

    TrafficStatsCompatIcs()
    {
    }

    public static void clearThreadStatsTag()
    {
        TrafficStats.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {
        return TrafficStats.getThreadStatsTag();
    }

    public static void incrementOperationCount(int i)
    {
        TrafficStats.incrementOperationCount(i);
    }

    public static void incrementOperationCount(int i, int j)
    {
        TrafficStats.incrementOperationCount(i, j);
    }

    public static void setThreadStatsTag(int i)
    {
        TrafficStats.setThreadStatsTag(i);
    }

    public static void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        TrafficStats.tagSocket(new DatagramSocketWrapper(datagramsocket, parcelfiledescriptor.getFileDescriptor()));
        parcelfiledescriptor.detachFd();
    }

    public static void tagSocket(Socket socket)
        throws SocketException
    {
        TrafficStats.tagSocket(socket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        ParcelFileDescriptor parcelfiledescriptor = ParcelFileDescriptor.fromDatagramSocket(datagramsocket);
        TrafficStats.untagSocket(new DatagramSocketWrapper(datagramsocket, parcelfiledescriptor.getFileDescriptor()));
        parcelfiledescriptor.detachFd();
    }

    public static void untagSocket(Socket socket)
        throws SocketException
    {
        TrafficStats.untagSocket(socket);
    }
}
