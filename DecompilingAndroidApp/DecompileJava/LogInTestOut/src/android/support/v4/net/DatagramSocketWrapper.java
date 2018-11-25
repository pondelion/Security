// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.net;

import java.io.*;
import java.net.*;

class DatagramSocketWrapper extends Socket
{
    private static class DatagramSocketImplWrapper extends SocketImpl
    {

        protected void accept(SocketImpl socketimpl)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected int available()
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void bind(InetAddress inetaddress, int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void close()
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void connect(String s, int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void connect(InetAddress inetaddress, int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void connect(SocketAddress socketaddress, int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void create(boolean flag)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected InputStream getInputStream()
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        public Object getOption(int i)
            throws SocketException
        {
            throw new UnsupportedOperationException();
        }

        protected OutputStream getOutputStream()
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void listen(int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        protected void sendUrgentData(int i)
            throws IOException
        {
            throw new UnsupportedOperationException();
        }

        public void setOption(int i, Object obj)
            throws SocketException
        {
            throw new UnsupportedOperationException();
        }

        public DatagramSocketImplWrapper(DatagramSocket datagramsocket, FileDescriptor filedescriptor)
        {
            localport = datagramsocket.getLocalPort();
            fd = filedescriptor;
        }
    }


    public DatagramSocketWrapper(DatagramSocket datagramsocket, FileDescriptor filedescriptor)
        throws SocketException
    {
        super(new DatagramSocketImplWrapper(datagramsocket, filedescriptor));
    }
}
