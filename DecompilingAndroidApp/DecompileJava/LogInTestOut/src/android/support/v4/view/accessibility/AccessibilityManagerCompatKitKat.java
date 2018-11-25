// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.view.accessibility.AccessibilityManager;

class AccessibilityManagerCompatKitKat
{
    static interface TouchExplorationStateChangeListenerBridge
    {

        public abstract void onTouchExplorationStateChanged(boolean flag);
    }

    public static class TouchExplorationStateChangeListenerWrapper
        implements android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
    {

        public boolean equals(Object obj)
        {
            if(this != obj)
            {
                if(obj == null || getClass() != obj.getClass())
                    return false;
                obj = (TouchExplorationStateChangeListenerWrapper)obj;
                if(mListener == null)
                {
                    if(((TouchExplorationStateChangeListenerWrapper) (obj)).mListener != null)
                        return false;
                } else
                {
                    return mListener.equals(((TouchExplorationStateChangeListenerWrapper) (obj)).mListener);
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

        public void onTouchExplorationStateChanged(boolean flag)
        {
            mListenerBridge.onTouchExplorationStateChanged(flag);
        }

        final Object mListener;
        final TouchExplorationStateChangeListenerBridge mListenerBridge;

        public TouchExplorationStateChangeListenerWrapper(Object obj, TouchExplorationStateChangeListenerBridge touchexplorationstatechangelistenerbridge)
        {
            mListener = obj;
            mListenerBridge = touchexplorationstatechangelistenerbridge;
        }
    }


    AccessibilityManagerCompatKitKat()
    {
    }

    public static boolean addTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, Object obj)
    {
        return accessibilitymanager.addTouchExplorationStateChangeListener((android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener)obj);
    }

    public static Object newTouchExplorationStateChangeListener(TouchExplorationStateChangeListenerBridge touchexplorationstatechangelistenerbridge)
    {
        return new android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener(touchexplorationstatechangelistenerbridge) {

            public void onTouchExplorationStateChanged(boolean flag)
            {
                bridge.onTouchExplorationStateChanged(flag);
            }

            final TouchExplorationStateChangeListenerBridge val$bridge;

            
            {
                bridge = touchexplorationstatechangelistenerbridge;
                super();
            }
        }
;
    }

    public static boolean removeTouchExplorationStateChangeListener(AccessibilityManager accessibilitymanager, Object obj)
    {
        return accessibilitymanager.removeTouchExplorationStateChangeListener((android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener)obj);
    }
}
