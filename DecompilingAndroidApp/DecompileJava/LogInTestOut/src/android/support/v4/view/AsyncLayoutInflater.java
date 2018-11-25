// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import java.util.concurrent.ArrayBlockingQueue;

public final class AsyncLayoutInflater
{
    private static class BasicInflater extends LayoutInflater
    {

        public LayoutInflater cloneInContext(Context context)
        {
            return new BasicInflater(context);
        }

        protected View onCreateView(String s, AttributeSet attributeset)
            throws ClassNotFoundException
        {
            int i;
            int j;
            String as[];
            as = sClassPrefixList;
            j = as.length;
            i = 0;
_L3:
            if(i >= j) goto _L2; else goto _L1
_L1:
            Object obj = as[i];
            obj = createView(s, ((String) (obj)), attributeset);
            if(obj != null)
                return ((View) (obj));
            continue; /* Loop/switch isn't completed */
            ClassNotFoundException classnotfoundexception;
            classnotfoundexception;
            i++;
              goto _L3
_L2:
            return super.onCreateView(s, attributeset);
        }

        private static final String sClassPrefixList[] = {
            "android.widget.", "android.webkit.", "android.app."
        };


        BasicInflater(Context context)
        {
            super(context);
        }
    }

    private static class InflateRequest
    {

        OnInflateFinishedListener callback;
        AsyncLayoutInflater inflater;
        ViewGroup parent;
        int resid;
        View view;

        InflateRequest()
        {
        }
    }

    private static class InflateThread extends Thread
    {

        public static InflateThread getInstance()
        {
            return sInstance;
        }

        public void enqueue(InflateRequest inflaterequest)
        {
            try
            {
                mQueue.put(inflaterequest);
                return;
            }
            // Misplaced declaration of an exception variable
            catch(InflateRequest inflaterequest)
            {
                throw new RuntimeException("Failed to enqueue async inflate request", inflaterequest);
            }
        }

        public InflateRequest obtainRequest()
        {
            InflateRequest inflaterequest1 = (InflateRequest)mRequestPool.acquire();
            InflateRequest inflaterequest = inflaterequest1;
            if(inflaterequest1 == null)
                inflaterequest = new InflateRequest();
            return inflaterequest;
        }

        public void releaseRequest(InflateRequest inflaterequest)
        {
            inflaterequest.callback = null;
            inflaterequest.inflater = null;
            inflaterequest.parent = null;
            inflaterequest.resid = 0;
            inflaterequest.view = null;
            mRequestPool.release(inflaterequest);
        }

        public void run()
        {
_L2:
            InflateRequest inflaterequest = (InflateRequest)mQueue.take();
            InterruptedException interruptedexception;
            try
            {
                inflaterequest.view = inflaterequest.inflater.mInflater.inflate(inflaterequest.resid, inflaterequest.parent, false);
            }
            catch(RuntimeException runtimeexception)
            {
                Log.w("AsyncLayoutInflater", "Failed to inflate resource in the background! Retrying on the UI thread", runtimeexception);
            }
            Message.obtain(inflaterequest.inflater.mHandler, 0, inflaterequest).sendToTarget();
            continue; /* Loop/switch isn't completed */
            interruptedexception;
            Log.w("AsyncLayoutInflater", interruptedexception);
            if(true) goto _L2; else goto _L1
_L1:
        }

        private static final InflateThread sInstance;
        private ArrayBlockingQueue mQueue;
        private android.support.v4.util.Pools.SynchronizedPool mRequestPool;

        static 
        {
            sInstance = new InflateThread();
            sInstance.start();
        }

        private InflateThread()
        {
            mQueue = new ArrayBlockingQueue(10);
            mRequestPool = new android.support.v4.util.Pools.SynchronizedPool(10);
        }
    }

    public static interface OnInflateFinishedListener
    {

        public abstract void onInflateFinished(View view, int i, ViewGroup viewgroup);
    }


    public AsyncLayoutInflater(Context context)
    {
        mHandlerCallback = new android.os.Handler.Callback() {

            public boolean handleMessage(Message message)
            {
                message = (InflateRequest)message.obj;
                if(((InflateRequest) (message)).view == null)
                    message.view = mInflater.inflate(((InflateRequest) (message)).resid, ((InflateRequest) (message)).parent, false);
                ((InflateRequest) (message)).callback.onInflateFinished(((InflateRequest) (message)).view, ((InflateRequest) (message)).resid, ((InflateRequest) (message)).parent);
                mInflateThread.releaseRequest(message);
                return true;
            }

            final AsyncLayoutInflater this$0;

            
            {
                this$0 = AsyncLayoutInflater.this;
                super();
            }
        }
;
        mInflater = new BasicInflater(context);
        mHandler = new Handler(mHandlerCallback);
        mInflateThread = InflateThread.getInstance();
    }

    public void inflate(int i, ViewGroup viewgroup, OnInflateFinishedListener oninflatefinishedlistener)
    {
        if(oninflatefinishedlistener == null)
        {
            throw new NullPointerException("callback argument may not be null!");
        } else
        {
            InflateRequest inflaterequest = mInflateThread.obtainRequest();
            inflaterequest.inflater = this;
            inflaterequest.resid = i;
            inflaterequest.parent = viewgroup;
            inflaterequest.callback = oninflatefinishedlistener;
            mInflateThread.enqueue(inflaterequest);
            return;
        }
    }

    private static final String TAG = "AsyncLayoutInflater";
    Handler mHandler;
    private android.os.Handler.Callback mHandlerCallback;
    InflateThread mInflateThread;
    LayoutInflater mInflater;
}
