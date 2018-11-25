// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityEvent;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityRecordCompat, AccessibilityEventCompatIcs, AccessibilityEventCompatJellyBean, AccessibilityEventCompatKitKat

public final class AccessibilityEventCompat
{
    static class AccessibilityEventIcsImpl extends AccessibilityEventStubImpl
    {

        public void appendRecord(AccessibilityEvent accessibilityevent, Object obj)
        {
            AccessibilityEventCompatIcs.appendRecord(accessibilityevent, obj);
        }

        public Object getRecord(AccessibilityEvent accessibilityevent, int i)
        {
            return AccessibilityEventCompatIcs.getRecord(accessibilityevent, i);
        }

        public int getRecordCount(AccessibilityEvent accessibilityevent)
        {
            return AccessibilityEventCompatIcs.getRecordCount(accessibilityevent);
        }

        AccessibilityEventIcsImpl()
        {
        }
    }

    static class AccessibilityEventJellyBeanImpl extends AccessibilityEventIcsImpl
    {

        public int getAction(AccessibilityEvent accessibilityevent)
        {
            return AccessibilityEventCompatJellyBean.getAction(accessibilityevent);
        }

        public int getMovementGranularity(AccessibilityEvent accessibilityevent)
        {
            return AccessibilityEventCompatJellyBean.getMovementGranularity(accessibilityevent);
        }

        public void setAction(AccessibilityEvent accessibilityevent, int i)
        {
            AccessibilityEventCompatJellyBean.setAction(accessibilityevent, i);
        }

        public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
        {
            AccessibilityEventCompatJellyBean.setMovementGranularity(accessibilityevent, i);
        }

        AccessibilityEventJellyBeanImpl()
        {
        }
    }

    static class AccessibilityEventKitKatImpl extends AccessibilityEventJellyBeanImpl
    {

        public int getContentChangeTypes(AccessibilityEvent accessibilityevent)
        {
            return AccessibilityEventCompatKitKat.getContentChangeTypes(accessibilityevent);
        }

        public void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
        {
            AccessibilityEventCompatKitKat.setContentChangeTypes(accessibilityevent, i);
        }

        AccessibilityEventKitKatImpl()
        {
        }
    }

    static class AccessibilityEventStubImpl
        implements AccessibilityEventVersionImpl
    {

        public void appendRecord(AccessibilityEvent accessibilityevent, Object obj)
        {
        }

        public int getAction(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public int getContentChangeTypes(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public int getMovementGranularity(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public Object getRecord(AccessibilityEvent accessibilityevent, int i)
        {
            return null;
        }

        public int getRecordCount(AccessibilityEvent accessibilityevent)
        {
            return 0;
        }

        public void setAction(AccessibilityEvent accessibilityevent, int i)
        {
        }

        public void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
        {
        }

        public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
        {
        }

        AccessibilityEventStubImpl()
        {
        }
    }

    static interface AccessibilityEventVersionImpl
    {

        public abstract void appendRecord(AccessibilityEvent accessibilityevent, Object obj);

        public abstract int getAction(AccessibilityEvent accessibilityevent);

        public abstract int getContentChangeTypes(AccessibilityEvent accessibilityevent);

        public abstract int getMovementGranularity(AccessibilityEvent accessibilityevent);

        public abstract Object getRecord(AccessibilityEvent accessibilityevent, int i);

        public abstract int getRecordCount(AccessibilityEvent accessibilityevent);

        public abstract void setAction(AccessibilityEvent accessibilityevent, int i);

        public abstract void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i);

        public abstract void setMovementGranularity(AccessibilityEvent accessibilityevent, int i);
    }


    private AccessibilityEventCompat()
    {
    }

    public static void appendRecord(AccessibilityEvent accessibilityevent, AccessibilityRecordCompat accessibilityrecordcompat)
    {
        IMPL.appendRecord(accessibilityevent, accessibilityrecordcompat.getImpl());
    }

    public static AccessibilityRecordCompat asRecord(AccessibilityEvent accessibilityevent)
    {
        return new AccessibilityRecordCompat(accessibilityevent);
    }

    public static int getContentChangeTypes(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getContentChangeTypes(accessibilityevent);
    }

    public static AccessibilityRecordCompat getRecord(AccessibilityEvent accessibilityevent, int i)
    {
        return new AccessibilityRecordCompat(IMPL.getRecord(accessibilityevent, i));
    }

    public static int getRecordCount(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getRecordCount(accessibilityevent);
    }

    public static void setContentChangeTypes(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setContentChangeTypes(accessibilityevent, i);
    }

    public int getAction(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getAction(accessibilityevent);
    }

    public int getMovementGranularity(AccessibilityEvent accessibilityevent)
    {
        return IMPL.getMovementGranularity(accessibilityevent);
    }

    public void setAction(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setAction(accessibilityevent, i);
    }

    public void setMovementGranularity(AccessibilityEvent accessibilityevent, int i)
    {
        IMPL.setMovementGranularity(accessibilityevent, i);
    }

    public static final int CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION = 4;
    public static final int CONTENT_CHANGE_TYPE_SUBTREE = 1;
    public static final int CONTENT_CHANGE_TYPE_TEXT = 2;
    public static final int CONTENT_CHANGE_TYPE_UNDEFINED = 0;
    private static final AccessibilityEventVersionImpl IMPL;
    public static final int TYPES_ALL_MASK = -1;
    public static final int TYPE_ANNOUNCEMENT = 16384;
    public static final int TYPE_ASSIST_READING_CONTEXT = 0x1000000;
    public static final int TYPE_GESTURE_DETECTION_END = 0x80000;
    public static final int TYPE_GESTURE_DETECTION_START = 0x40000;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_END = 1024;
    public static final int TYPE_TOUCH_EXPLORATION_GESTURE_START = 512;
    public static final int TYPE_TOUCH_INTERACTION_END = 0x200000;
    public static final int TYPE_TOUCH_INTERACTION_START = 0x100000;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUSED = 32768;
    public static final int TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED = 0x10000;
    public static final int TYPE_VIEW_CONTEXT_CLICKED = 0x800000;
    public static final int TYPE_VIEW_HOVER_ENTER = 128;
    public static final int TYPE_VIEW_HOVER_EXIT = 256;
    public static final int TYPE_VIEW_SCROLLED = 4096;
    public static final int TYPE_VIEW_TEXT_SELECTION_CHANGED = 8192;
    public static final int TYPE_VIEW_TEXT_TRAVERSED_AT_MOVEMENT_GRANULARITY = 0x20000;
    public static final int TYPE_WINDOWS_CHANGED = 0x400000;
    public static final int TYPE_WINDOW_CONTENT_CHANGED = 2048;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new AccessibilityEventKitKatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityEventJellyBeanImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityEventIcsImpl();
        else
            IMPL = new AccessibilityEventStubImpl();
    }
}
