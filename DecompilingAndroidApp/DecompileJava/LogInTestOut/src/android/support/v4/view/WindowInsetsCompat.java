// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.graphics.Rect;

// Referenced classes of package android.support.v4.view:
//            WindowInsetsCompatApi20, WindowInsetsCompatApi21

public class WindowInsetsCompat
{
    private static class WindowInsetsCompatApi20Impl extends WindowInsetsCompatBaseImpl
    {

        public WindowInsetsCompat consumeSystemWindowInsets(Object obj)
        {
            return new WindowInsetsCompat(WindowInsetsCompatApi20.consumeSystemWindowInsets(obj));
        }

        public Object getSourceWindowInsets(Object obj)
        {
            return WindowInsetsCompatApi20.getSourceWindowInsets(obj);
        }

        public int getSystemWindowInsetBottom(Object obj)
        {
            return WindowInsetsCompatApi20.getSystemWindowInsetBottom(obj);
        }

        public int getSystemWindowInsetLeft(Object obj)
        {
            return WindowInsetsCompatApi20.getSystemWindowInsetLeft(obj);
        }

        public int getSystemWindowInsetRight(Object obj)
        {
            return WindowInsetsCompatApi20.getSystemWindowInsetRight(obj);
        }

        public int getSystemWindowInsetTop(Object obj)
        {
            return WindowInsetsCompatApi20.getSystemWindowInsetTop(obj);
        }

        public boolean hasInsets(Object obj)
        {
            return WindowInsetsCompatApi20.hasInsets(obj);
        }

        public boolean hasSystemWindowInsets(Object obj)
        {
            return WindowInsetsCompatApi20.hasSystemWindowInsets(obj);
        }

        public boolean isRound(Object obj)
        {
            return WindowInsetsCompatApi20.isRound(obj);
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object obj, int i, int j, int k, int l)
        {
            return new WindowInsetsCompat(WindowInsetsCompatApi20.replaceSystemWindowInsets(obj, i, j, k, l));
        }

        WindowInsetsCompatApi20Impl()
        {
        }
    }

    private static class WindowInsetsCompatApi21Impl extends WindowInsetsCompatApi20Impl
    {

        public WindowInsetsCompat consumeStableInsets(Object obj)
        {
            return new WindowInsetsCompat(WindowInsetsCompatApi21.consumeStableInsets(obj));
        }

        public int getStableInsetBottom(Object obj)
        {
            return WindowInsetsCompatApi21.getStableInsetBottom(obj);
        }

        public int getStableInsetLeft(Object obj)
        {
            return WindowInsetsCompatApi21.getStableInsetLeft(obj);
        }

        public int getStableInsetRight(Object obj)
        {
            return WindowInsetsCompatApi21.getStableInsetRight(obj);
        }

        public int getStableInsetTop(Object obj)
        {
            return WindowInsetsCompatApi21.getStableInsetTop(obj);
        }

        public boolean hasStableInsets(Object obj)
        {
            return WindowInsetsCompatApi21.hasStableInsets(obj);
        }

        public boolean isConsumed(Object obj)
        {
            return WindowInsetsCompatApi21.isConsumed(obj);
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object obj, Rect rect)
        {
            return new WindowInsetsCompat(WindowInsetsCompatApi21.replaceSystemWindowInsets(obj, rect));
        }

        WindowInsetsCompatApi21Impl()
        {
        }
    }

    private static class WindowInsetsCompatBaseImpl
        implements WindowInsetsCompatImpl
    {

        public WindowInsetsCompat consumeStableInsets(Object obj)
        {
            return null;
        }

        public WindowInsetsCompat consumeSystemWindowInsets(Object obj)
        {
            return null;
        }

        public Object getSourceWindowInsets(Object obj)
        {
            return null;
        }

        public int getStableInsetBottom(Object obj)
        {
            return 0;
        }

        public int getStableInsetLeft(Object obj)
        {
            return 0;
        }

        public int getStableInsetRight(Object obj)
        {
            return 0;
        }

        public int getStableInsetTop(Object obj)
        {
            return 0;
        }

        public int getSystemWindowInsetBottom(Object obj)
        {
            return 0;
        }

        public int getSystemWindowInsetLeft(Object obj)
        {
            return 0;
        }

        public int getSystemWindowInsetRight(Object obj)
        {
            return 0;
        }

        public int getSystemWindowInsetTop(Object obj)
        {
            return 0;
        }

        public boolean hasInsets(Object obj)
        {
            return false;
        }

        public boolean hasStableInsets(Object obj)
        {
            return false;
        }

        public boolean hasSystemWindowInsets(Object obj)
        {
            return false;
        }

        public boolean isConsumed(Object obj)
        {
            return false;
        }

        public boolean isRound(Object obj)
        {
            return false;
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object obj, int i, int j, int k, int l)
        {
            return null;
        }

        public WindowInsetsCompat replaceSystemWindowInsets(Object obj, Rect rect)
        {
            return null;
        }

        WindowInsetsCompatBaseImpl()
        {
        }
    }

    private static interface WindowInsetsCompatImpl
    {

        public abstract WindowInsetsCompat consumeStableInsets(Object obj);

        public abstract WindowInsetsCompat consumeSystemWindowInsets(Object obj);

        public abstract Object getSourceWindowInsets(Object obj);

        public abstract int getStableInsetBottom(Object obj);

        public abstract int getStableInsetLeft(Object obj);

        public abstract int getStableInsetRight(Object obj);

        public abstract int getStableInsetTop(Object obj);

        public abstract int getSystemWindowInsetBottom(Object obj);

        public abstract int getSystemWindowInsetLeft(Object obj);

        public abstract int getSystemWindowInsetRight(Object obj);

        public abstract int getSystemWindowInsetTop(Object obj);

        public abstract boolean hasInsets(Object obj);

        public abstract boolean hasStableInsets(Object obj);

        public abstract boolean hasSystemWindowInsets(Object obj);

        public abstract boolean isConsumed(Object obj);

        public abstract boolean isRound(Object obj);

        public abstract WindowInsetsCompat replaceSystemWindowInsets(Object obj, int i, int j, int k, int l);

        public abstract WindowInsetsCompat replaceSystemWindowInsets(Object obj, Rect rect);
    }


    public WindowInsetsCompat(WindowInsetsCompat windowinsetscompat)
    {
        if(windowinsetscompat == null)
            windowinsetscompat = null;
        else
            windowinsetscompat = ((WindowInsetsCompat) (IMPL.getSourceWindowInsets(windowinsetscompat.mInsets)));
        mInsets = windowinsetscompat;
    }

    WindowInsetsCompat(Object obj)
    {
        mInsets = obj;
    }

    static Object unwrap(WindowInsetsCompat windowinsetscompat)
    {
        if(windowinsetscompat == null)
            return null;
        else
            return windowinsetscompat.mInsets;
    }

    static WindowInsetsCompat wrap(Object obj)
    {
        if(obj == null)
            return null;
        else
            return new WindowInsetsCompat(obj);
    }

    public WindowInsetsCompat consumeStableInsets()
    {
        return IMPL.consumeStableInsets(mInsets);
    }

    public WindowInsetsCompat consumeSystemWindowInsets()
    {
        return IMPL.consumeSystemWindowInsets(mInsets);
    }

    public boolean equals(Object obj)
    {
        if(this != obj)
        {
            if(obj == null || getClass() != obj.getClass())
                return false;
            obj = (WindowInsetsCompat)obj;
            if(mInsets == null)
            {
                if(((WindowInsetsCompat) (obj)).mInsets != null)
                    return false;
            } else
            {
                return mInsets.equals(((WindowInsetsCompat) (obj)).mInsets);
            }
        }
        return true;
    }

    public int getStableInsetBottom()
    {
        return IMPL.getStableInsetBottom(mInsets);
    }

    public int getStableInsetLeft()
    {
        return IMPL.getStableInsetLeft(mInsets);
    }

    public int getStableInsetRight()
    {
        return IMPL.getStableInsetRight(mInsets);
    }

    public int getStableInsetTop()
    {
        return IMPL.getStableInsetTop(mInsets);
    }

    public int getSystemWindowInsetBottom()
    {
        return IMPL.getSystemWindowInsetBottom(mInsets);
    }

    public int getSystemWindowInsetLeft()
    {
        return IMPL.getSystemWindowInsetLeft(mInsets);
    }

    public int getSystemWindowInsetRight()
    {
        return IMPL.getSystemWindowInsetRight(mInsets);
    }

    public int getSystemWindowInsetTop()
    {
        return IMPL.getSystemWindowInsetTop(mInsets);
    }

    public boolean hasInsets()
    {
        return IMPL.hasInsets(mInsets);
    }

    public boolean hasStableInsets()
    {
        return IMPL.hasStableInsets(mInsets);
    }

    public boolean hasSystemWindowInsets()
    {
        return IMPL.hasSystemWindowInsets(mInsets);
    }

    public int hashCode()
    {
        if(mInsets == null)
            return 0;
        else
            return mInsets.hashCode();
    }

    public boolean isConsumed()
    {
        return IMPL.isConsumed(mInsets);
    }

    public boolean isRound()
    {
        return IMPL.isRound(mInsets);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(int i, int j, int k, int l)
    {
        return IMPL.replaceSystemWindowInsets(mInsets, i, j, k, l);
    }

    public WindowInsetsCompat replaceSystemWindowInsets(Rect rect)
    {
        return IMPL.replaceSystemWindowInsets(mInsets, rect);
    }

    private static final WindowInsetsCompatImpl IMPL;
    private final Object mInsets;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 21)
            IMPL = new WindowInsetsCompatApi21Impl();
        else
        if(i >= 20)
            IMPL = new WindowInsetsCompatApi20Impl();
        else
            IMPL = new WindowInsetsCompatBaseImpl();
    }
}
