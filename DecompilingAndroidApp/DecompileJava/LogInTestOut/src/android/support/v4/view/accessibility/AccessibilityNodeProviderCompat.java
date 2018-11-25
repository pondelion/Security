// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view.accessibility;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.view.accessibility:
//            AccessibilityNodeInfoCompat, AccessibilityNodeProviderCompatJellyBean, AccessibilityNodeProviderCompatKitKat

public class AccessibilityNodeProviderCompat
{
    static interface AccessibilityNodeProviderImpl
    {

        public abstract Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat accessibilitynodeprovidercompat);
    }

    private static class AccessibilityNodeProviderJellyBeanImpl extends AccessibilityNodeProviderStubImpl
    {

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat accessibilitynodeprovidercompat)
        {
            return AccessibilityNodeProviderCompatJellyBean.newAccessibilityNodeProviderBridge(accessibilitynodeprovidercompat. new AccessibilityNodeProviderCompatJellyBean.AccessibilityNodeInfoBridge() {

                public Object createAccessibilityNodeInfo(int i)
                {
                    AccessibilityNodeInfoCompat accessibilitynodeinfocompat = compat.createAccessibilityNodeInfo(i);
                    if(accessibilitynodeinfocompat == null)
                        return null;
                    else
                        return accessibilitynodeinfocompat.getInfo();
                }

                public List findAccessibilityNodeInfosByText(String s, int i)
                {
                    List list = compat.findAccessibilityNodeInfosByText(s, i);
                    if(list != null) goto _L2; else goto _L1
_L1:
                    s = null;
_L4:
                    return s;
_L2:
                    ArrayList arraylist = new ArrayList();
                    int j = list.size();
                    i = 0;
                    do
                    {
                        s = arraylist;
                        if(i >= j)
                            continue;
                        arraylist.add(((AccessibilityNodeInfoCompat)list.get(i)).getInfo());
                        i++;
                    } while(true);
                    if(true) goto _L4; else goto _L3
_L3:
                }

                public boolean performAction(int i, int j, Bundle bundle)
                {
                    return compat.performAction(i, j, bundle);
                }

                final AccessibilityNodeProviderJellyBeanImpl this$0;
                final AccessibilityNodeProviderCompat val$compat;

            
            {
                this$0 = final_accessibilitynodeproviderjellybeanimpl;
                compat = AccessibilityNodeProviderCompat.this;
                super();
            }
            }
);
        }

        AccessibilityNodeProviderJellyBeanImpl()
        {
        }
    }

    private static class AccessibilityNodeProviderKitKatImpl extends AccessibilityNodeProviderStubImpl
    {

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat accessibilitynodeprovidercompat)
        {
            return AccessibilityNodeProviderCompatKitKat.newAccessibilityNodeProviderBridge(accessibilitynodeprovidercompat. new AccessibilityNodeProviderCompatKitKat.AccessibilityNodeInfoBridge() {

                public Object createAccessibilityNodeInfo(int i)
                {
                    AccessibilityNodeInfoCompat accessibilitynodeinfocompat = compat.createAccessibilityNodeInfo(i);
                    if(accessibilitynodeinfocompat == null)
                        return null;
                    else
                        return accessibilitynodeinfocompat.getInfo();
                }

                public List findAccessibilityNodeInfosByText(String s, int i)
                {
                    List list = compat.findAccessibilityNodeInfosByText(s, i);
                    if(list != null) goto _L2; else goto _L1
_L1:
                    s = null;
_L4:
                    return s;
_L2:
                    ArrayList arraylist = new ArrayList();
                    int j = list.size();
                    i = 0;
                    do
                    {
                        s = arraylist;
                        if(i >= j)
                            continue;
                        arraylist.add(((AccessibilityNodeInfoCompat)list.get(i)).getInfo());
                        i++;
                    } while(true);
                    if(true) goto _L4; else goto _L3
_L3:
                }

                public Object findFocus(int i)
                {
                    AccessibilityNodeInfoCompat accessibilitynodeinfocompat = compat.findFocus(i);
                    if(accessibilitynodeinfocompat == null)
                        return null;
                    else
                        return accessibilitynodeinfocompat.getInfo();
                }

                public boolean performAction(int i, int j, Bundle bundle)
                {
                    return compat.performAction(i, j, bundle);
                }

                final AccessibilityNodeProviderKitKatImpl this$0;
                final AccessibilityNodeProviderCompat val$compat;

            
            {
                this$0 = final_accessibilitynodeproviderkitkatimpl;
                compat = AccessibilityNodeProviderCompat.this;
                super();
            }
            }
);
        }

        AccessibilityNodeProviderKitKatImpl()
        {
        }
    }

    static class AccessibilityNodeProviderStubImpl
        implements AccessibilityNodeProviderImpl
    {

        public Object newAccessibilityNodeProviderBridge(AccessibilityNodeProviderCompat accessibilitynodeprovidercompat)
        {
            return null;
        }

        AccessibilityNodeProviderStubImpl()
        {
        }
    }


    public AccessibilityNodeProviderCompat()
    {
        mProvider = IMPL.newAccessibilityNodeProviderBridge(this);
    }

    public AccessibilityNodeProviderCompat(Object obj)
    {
        mProvider = obj;
    }

    public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
    {
        return null;
    }

    public List findAccessibilityNodeInfosByText(String s, int i)
    {
        return null;
    }

    public AccessibilityNodeInfoCompat findFocus(int i)
    {
        return null;
    }

    public Object getProvider()
    {
        return mProvider;
    }

    public boolean performAction(int i, int j, Bundle bundle)
    {
        return false;
    }

    public static final int HOST_VIEW_ID = -1;
    private static final AccessibilityNodeProviderImpl IMPL;
    private final Object mProvider;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
            IMPL = new AccessibilityNodeProviderKitKatImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 16)
            IMPL = new AccessibilityNodeProviderJellyBeanImpl();
        else
            IMPL = new AccessibilityNodeProviderStubImpl();
    }
}
