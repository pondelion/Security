// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import java.net.*;

// Referenced classes of package android.support.v4.net:
//            TrafficStatsCompatApi24, TrafficStatsCompatIcs

public final class TrafficStatsCompat
{
    static class Api24TrafficStatsCompatImpl extends IcsTrafficStatsCompatImpl
    {

        public void tagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException
        {
            TrafficStatsCompatApi24.tagDatagramSocket(datagramsocket);
        }

        public void untagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException
        {
            TrafficStatsCompatApi24.untagDatagramSocket(datagramsocket);
        }

        Api24TrafficStatsCompatImpl()
        {
        }
    }

    static class BaseTrafficStatsCompatImpl
        implements TrafficStatsCompatImpl
    {

        public void clearThreadStatsTag()
        {
            ((SocketTags)mThreadSocketTags.get()).statsTag = -1;
        }

        public int getThreadStatsTag()
        {
            return ((SocketTags)mThreadSocketTags.get()).statsTag;
        }

        public void incrementOperationCount(int i)
        {
        }

        public void incrementOperationCount(int i, int j)
        {
        }

        public void setThreadStatsTag(int i)
        {
            ((SocketTags)mThreadSocketTags.get()).statsTag = i;
        }

        public void tagDatagramSocket(DatagramSocket datagramsocket)
        {
        }

        public void tagSocket(Socket socket)
        {
        }

        public void untagDatagramSocket(DatagramSocket datagramsocket)
        {
        }

        public void untagSocket(Socket socket)
        {
        }

        private ThreadLocal mThreadSocketTags;

        BaseTrafficStatsCompatImpl()
        {
            mThreadSocketTags = new _cls1();
        }
    }

    private static class BaseTrafficStatsCompatImpl.SocketTags
    {

        public int statsTag;

        BaseTrafficStatsCompatImpl.SocketTags()
        {
            statsTag = -1;
        }
    }

    static class IcsTrafficStatsCompatImpl
        implements TrafficStatsCompatImpl
    {

        public void clearThreadStatsTag()
        {
            TrafficStatsCompatIcs.clearThreadStatsTag();
        }

        public int getThreadStatsTag()
        {
            return TrafficStatsCompatIcs.getThreadStatsTag();
        }

        public void incrementOperationCount(int i)
        {
            TrafficStatsCompatIcs.incrementOperationCount(i);
        }

        public void incrementOperationCount(int i, int j)
        {
            TrafficStatsCompatIcs.incrementOperationCount(i, j);
        }

        public void setThreadStatsTag(int i)
        {
            TrafficStatsCompatIcs.setThreadStatsTag(i);
        }

        public void tagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException
        {
            TrafficStatsCompatIcs.tagDatagramSocket(datagramsocket);
        }

        public void tagSocket(Socket socket)
            throws SocketException
        {
            TrafficStatsCompatIcs.tagSocket(socket);
        }

        public void untagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException
        {
            TrafficStatsCompatIcs.untagDatagramSocket(datagramsocket);
        }

        public void untagSocket(Socket socket)
            throws SocketException
        {
            TrafficStatsCompatIcs.untagSocket(socket);
        }

        IcsTrafficStatsCompatImpl()
        {
        }
    }

    static interface TrafficStatsCompatImpl
    {

        public abstract void clearThreadStatsTag();

        public abstract int getThreadStatsTag();

        public abstract void incrementOperationCount(int i);

        public abstract void incrementOperationCount(int i, int j);

        public abstract void setThreadStatsTag(int i);

        public abstract void tagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException;

        public abstract void tagSocket(Socket socket)
            throws SocketException;

        public abstract void untagDatagramSocket(DatagramSocket datagramsocket)
            throws SocketException;

        public abstract void untagSocket(Socket socket)
            throws SocketException;
    }


    private TrafficStatsCompat()
    {
    }

    public static void clearThreadStatsTag()
    {
        IMPL.clearThreadStatsTag();
    }

    public static int getThreadStatsTag()
    {
        return IMPL.getThreadStatsTag();
    }

    public static void incrementOperationCount(int i)
    {
        IMPL.incrementOperationCount(i);
    }

    public static void incrementOperationCount(int i, int j)
    {
        IMPL.incrementOperationCount(i, j);
    }

    public static void setThreadStatsTag(int i)
    {
        IMPL.setThreadStatsTag(i);
    }

    public static void tagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        IMPL.tagDatagramSocket(datagramsocket);
    }

    public static void tagSocket(Socket socket)
        throws SocketException
    {
        IMPL.tagSocket(socket);
    }

    public static void untagDatagramSocket(DatagramSocket datagramsocket)
        throws SocketException
    {
        IMPL.untagDatagramSocket(datagramsocket);
    }

    public static void untagSocket(Socket socket)
        throws SocketException
    {
        IMPL.untagSocket(socket);
    }

    private static final TrafficStatsCompatImpl IMPL;

    static 
    {
        if("N".equals(android.os.Build.VERSION.CODENAME))
            IMPL = new Api24TrafficStatsCompatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new IcsTrafficStatsCompatImpl();
        else
            IMPL = new BaseTrafficStatsCompatImpl();
    }

    // Unreferenced inner class android/support/v4/net/TrafficStatsCompat$BaseTrafficStatsCompatImpl$1

/* anonymous class */
    class BaseTrafficStatsCompatImpl._cls1 extends ThreadLocal
    {

        protected BaseTrafficStatsCompatImpl.SocketTags initialValue()
        {
            return new BaseTrafficStatsCompatImpl.SocketTags();
        }

        protected volatile Object initialValue()
        {
            return initialValue();
        }

        final BaseTrafficStatsCompatImpl this$0;

            
            {
                this$0 = BaseTrafficStatsCompatImpl.this;
                super();
            }
    }

}
