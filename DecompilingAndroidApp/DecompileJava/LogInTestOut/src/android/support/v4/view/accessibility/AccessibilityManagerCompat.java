// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.Collections;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityManagerCompatIcs, AccessibilityManagerCompatKitKat

public final class AccessibilityManagerCompat
{
    static class AccessibilityManagerIcsImpl extends AccessibilityManagerStubImpl
    {

        public boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return AccessibilityManagerCompatIcs.addAccessibilityStateChangeListener(accessibilitymanager, newAccessibilityStateChangeListener(accessibilitystatechangelistener));
        }

        public List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
        {
            return AccessibilityManagerCompatIcs.getEnabledAccessibilityServiceList(accessibilitymanager, i);
        }

        public List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
        {
            return AccessibilityManagerCompatIcs.getInstalledAccessibilityServiceList(accessibilitymanager);
        }

        public boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
        {
            return AccessibilityManagerCompatIcs.isTouchExplorationEnabled(accessibilitymanager);
        }

        public AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper(accessibilitystatechangelistener, accessibilitystatechangelistener. new AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerBridge() {

                public void onAccessibilityStateChanged(boolean flag)
                {
                    listener.onAccessibilityStateChanged(flag);
                }

                final AccessibilityManagerIcsImpl this$0;
                final AccessibilityStateChangeListener val$listener;

            
            {
                this$0 = final_accessibilitymanagericsimpl;
                listener = AccessibilityStateChangeListener.this;
                super();
            }
            }
);
        }

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return AccessibilityManagerCompatIcs.removeAccessibilityStateChangeListener(accessibilitymanager, newAccessibilityStateChangeListener(accessibilitystatechangelistener));
        }

        AccessibilityManagerIcsImpl()
        {
        }
    }

    static class AccessibilityManagerKitKatImpl extends AccessibilityManagerIcsImpl
    {

        public boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return AccessibilityManagerCompatKitKat.addTouchExplorationStateChangeListener(accessibilitymanager, newTouchExplorationStateChangeListener(touchexplorationstatechangelistener));
        }

        public AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return new AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper(touchexplorationstatechangelistener, touchexplorationstatechangelistener. new AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerBridge() {

                public void onTouchExplorationStateChanged(boolean flag)
                {
                    listener.onTouchExplorationStateChanged(flag);
                }

                final AccessibilityManagerKitKatImpl this$0;
                final TouchExplorationStateChangeListener val$listener;

            
            {
                this$0 = final_accessibilitymanagerkitkatimpl;
                listener = TouchExplorationStateChangeListener.this;
                super();
            }
            }
);
        }

        public boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return AccessibilityManagerCompatKitKat.removeTouchExplorationStateChangeListener(accessibilitymanager, newTouchExplorationStateChangeListener(touchexplorationstatechangelistener));
        }

        AccessibilityManagerKitKatImpl()
        {
        }
    }

    static class AccessibilityManagerStubImpl
        implements AccessibilityManagerVersionImpl
    {

        public boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return false;
        }

        public boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return false;
        }

        public List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
        {
            return Collections.emptyList();
        }

        public List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
        {
            return Collections.emptyList();
        }

        public boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
        {
            return false;
        }

        public AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return null;
        }

        public AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return null;
        }

        public boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
        {
            return false;
        }

        public boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
        {
            return false;
        }

        AccessibilityManagerStubImpl()
        {
        }
    }

    static interface AccessibilityManagerVersionImpl
    {

        public abstract boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener);

        public abstract boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener);

        public abstract List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i);

        public abstract List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager);

        public abstract boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager);

        public abstract AccessibilityManagerCompatIcs.AccessibilityStateChangeListenerWrapper newAccessibilityStateChangeListener(AccessibilityStateChangeListener accessibilitystatechangelistener);

        public abstract AccessibilityManagerCompatKitKat.TouchExplorationStateChangeListenerWrapper newTouchExplorationStateChangeListener(TouchExplorationStateChangeListener touchexplorationstatechangelistener);

        public abstract boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener);

        public abstract boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener);
    }

    public static interface AccessibilityStateChangeListener
    {

        public abstract void onAccessibilityStateChanged(boolean flag);
    }

    public static abstract class AccessibilityStateChangeListenerCompat
        implements AccessibilityStateChangeListener
    {

        public AccessibilityStateChangeListenerCompat()
        {
        }
    }

    public static interface TouchExplorationStateChangeListener
    {

        public abstract void onTouchExplorationStateChanged(boolean flag);
    }


    private AccessibilityManagerCompat()
    {
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        return IMPL.addAccessibilityStateChangeListener(accessibilitymanager, accessibilitystatechangelistener);
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        return IMPL.addTouchExplorationStateChangeListener(accessibilitymanager, touchexplorationstatechangelistener);
    }

    public static List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
    {
        return IMPL.getEnabledAccessibilityServiceList(accessibilitymanager, i);
    }

    public static List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
    {
        return IMPL.getInstalledAccessibilityServiceList(accessibilitymanager);
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
    {
        return IMPL.isTouchExplorationEnabled(accessibilitymanager);
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListener accessibilitystatechangelistener)
    {
        return IMPL.removeAccessibilityStateChangeListener(accessibilitymanager, accessibilitystatechangelistener);
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, TouchExplorationStateChangeListener touchexplorationstatechangelistener)
    {
        return IMPL.removeTouchExplorationStateChangeListener(accessibilitymanager, touchexplorationstatechangelistener);
    }

    private static final AccessibilityManagerVersionImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new AccessibilityManagerKitKatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new AccessibilityManagerIcsImpl();
        else
            IMPL = new AccessibilityManagerStubImpl();
    }
}
