// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;
import java.util.List;

class AccessibilityManagerCompatIcs
{
    static interface AccessibilityStateChangeListenerBridge
    {

        public abstract void onAccessibilityStateChanged(boolean flag);
    }

    public static class AccessibilityStateChangeListenerWrapper
        implements android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (AccessibilityStateChangeListenerWrapper)obj;
                if(mListener == null)
                {
                    if(((AccessibilityStateChangeListenerWrapper) (obj)).mListener != null)
                        return false;
                } else
                {
                    return mListener.equals(((AccessibilityStateChangeListenerWrapper) (obj)).mListener);
                }
            }
            return true;
        }

        public int hashCode()
        {
            if(mListener == null)
                return 0;
            else
                return mListener.hashCode();
        }

        public void onAccessibilityStateChanged(boolean flag)
        {
            mListenerBridge.onAccessibilityStateChanged(flag);
        }

        Object mListener;
        AccessibilityStateChangeListenerBridge mListenerBridge;

        public AccessibilityStateChangeListenerWrapper(Object obj, AccessibilityStateChangeListenerBridge accessibilitystatechangelistenerbridge)
        {
            mListener = obj;
            mListenerBridge = accessibilitystatechangelistenerbridge;
        }
    }


    AccessibilityManagerCompatIcs()
    {
    }

    public static boolean addAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListenerWrapper accessibilitystatechangelistenerwrapper)
    {
        return accessibilitymanager.addAccessibilityStateChangeListener(accessibilitystatechangelistenerwrapper);
    }

    public static List getEnabledAccessibilityServiceList(AccessibilityManager accessibilitymanager, int i)
    {
        return accessibilitymanager.getEnabledAccessibilityServiceList(i);
    }

    public static List getInstalledAccessibilityServiceList(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.getInstalledAccessibilityServiceList();
    }

    public static boolean isTouchExplorationEnabled(AccessibilityManager accessibilitymanager)
    {
        return accessibilitymanager.isTouchExplorationEnabled();
    }

    public static boolean removeAccessibilityStateChangeListener(AccessibilityManager accessibilitymanager, AccessibilityStateChangeListenerWrapper accessibilitystatechangelistenerwrapper)
    {
        return accessibilitymanager.removeAccessibilityStateChangeListener(accessibilitystatechangelistenerwrapper);
    }
}
