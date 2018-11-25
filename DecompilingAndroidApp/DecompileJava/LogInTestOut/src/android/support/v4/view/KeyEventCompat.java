// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package android.support.v4.view:
//            KeyEventCompatHoneycomb

public final class KeyEventCompat
{
    static class BaseKeyEventVersionImpl
        implements KeyEventVersionImpl
    {

        private static int metaStateFilterDirectionalModifiers(int i, int j, int k, int l, int i1)
        {
            boolean flag1 = true;
            boolean flag;
            if((j & k) != 0)
                flag = true;
            else
                flag = false;
            l |= i1;
            if((j & l) != 0)
                j = ((flag1) ? 1 : 0);
            else
                j = 0;
            if(flag)
            {
                if(j != 0)
                    throw new IllegalArgumentException("bad arguments");
                l = i & ~l;
            } else
            {
                l = i;
                if(j != 0)
                    return i & ~k;
            }
            return l;
        }

        public boolean isCtrlPressed(KeyEvent keyevent)
        {
            return false;
        }

        public boolean metaStateHasModifiers(int i, int j)
        {
            return metaStateFilterDirectionalModifiers(metaStateFilterDirectionalModifiers(normalizeMetaState(i) & 0xf7, j, 1, 64, 128), j, 2, 16, 32) == j;
        }

        public boolean metaStateHasNoModifiers(int i)
        {
            return (normalizeMetaState(i) & 0xf7) == 0;
        }

        public int normalizeMetaState(int i)
        {
            int j = i;
            if((i & 0xc0) != 0)
                j = i | 1;
            i = j;
            if((j & 0x30) != 0)
                i = j | 2;
            return i & 0xf7;
        }

        private static final int META_ALL_MASK = 247;
        private static final int META_MODIFIER_MASK = 247;

        BaseKeyEventVersionImpl()
        {
        }
    }

    static class HoneycombKeyEventVersionImpl extends BaseKeyEventVersionImpl
    {

        public boolean isCtrlPressed(KeyEvent keyevent)
        {
            return KeyEventCompatHoneycomb.isCtrlPressed(keyevent);
        }

        public boolean metaStateHasModifiers(int i, int j)
        {
            return KeyEventCompatHoneycomb.metaStateHasModifiers(i, j);
        }

        public boolean metaStateHasNoModifiers(int i)
        {
            return KeyEventCompatHoneycomb.metaStateHasNoModifiers(i);
        }

        public int normalizeMetaState(int i)
        {
            return KeyEventCompatHoneycomb.normalizeMetaState(i);
        }

        HoneycombKeyEventVersionImpl()
        {
        }
    }

    static interface KeyEventVersionImpl
    {

        public abstract boolean isCtrlPressed(KeyEvent keyevent);

        public abstract boolean metaStateHasModifiers(int i, int j);

        public abstract boolean metaStateHasNoModifiers(int i);

        public abstract int normalizeMetaState(int i);
    }


    private KeyEventCompat()
    {
    }

    public static boolean dispatch(KeyEvent keyevent, android.view.KeyEvent.Callback callback, Object obj, Object obj1)
    {
        return keyevent.dispatch(callback, (android.view.KeyEvent.DispatcherState)obj, obj1);
    }

    public static Object getKeyDispatcherState(View view)
    {
        return view.getKeyDispatcherState();
    }

    public static boolean hasModifiers(KeyEvent keyevent, int i)
    {
        return IMPL.metaStateHasModifiers(keyevent.getMetaState(), i);
    }

    public static boolean hasNoModifiers(KeyEvent keyevent)
    {
        return IMPL.metaStateHasNoModifiers(keyevent.getMetaState());
    }

    public static boolean isCtrlPressed(KeyEvent keyevent)
    {
        return IMPL.isCtrlPressed(keyevent);
    }

    public static boolean isTracking(KeyEvent keyevent)
    {
        return keyevent.isTracking();
    }

    public static boolean metaStateHasModifiers(int i, int j)
    {
        return IMPL.metaStateHasModifiers(i, j);
    }

    public static boolean metaStateHasNoModifiers(int i)
    {
        return IMPL.metaStateHasNoModifiers(i);
    }

    public static int normalizeMetaState(int i)
    {
        return IMPL.normalizeMetaState(i);
    }

    public static void startTracking(KeyEvent keyevent)
    {
        keyevent.startTracking();
    }

    static final KeyEventVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new HoneycombKeyEventVersionImpl();
        else
            IMPL = new BaseKeyEventVersionImpl();
    }
}
