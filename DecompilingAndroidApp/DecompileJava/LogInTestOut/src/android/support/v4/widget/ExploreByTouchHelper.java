// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.util.SparseArrayCompat;
import android.support.v4.view.*;
import android.support.v4.view.accessibility.*;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.widget:
//            FocusStrategy

public abstract class ExploreByTouchHelper extends AccessibilityDelegateCompat
{
    private class MyNodeProvider extends AccessibilityNodeProviderCompat
    {

        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int i)
        {
            return AccessibilityNodeInfoCompat.obtain(obtainAccessibilityNodeInfo(i));
        }

        public boolean performAction(int i, int j, Bundle bundle)
        {
            return ExploreByTouchHelper.this.performAction(i, j, bundle);
        }

        final ExploreByTouchHelper this$0;

        MyNodeProvider()
        {
            this$0 = ExploreByTouchHelper.this;
            super();
        }
    }


    public ExploreByTouchHelper(View view)
    {
        mAccessibilityFocusedVirtualViewId = 0x80000000;
        mKeyboardFocusedVirtualViewId = 0x80000000;
        mHoveredVirtualViewId = 0x80000000;
        if(view == null)
            throw new IllegalArgumentException("View may not be null");
        mHost = view;
        mManager = (AccessibilityManager)view.getContext().getSystemService("accessibility");
        view.setFocusable(true);
        if(ViewCompat.getImportantForAccessibility(view) == 0)
            ViewCompat.setImportantForAccessibility(view, 1);
    }

    private boolean clearAccessibilityFocus(int i)
    {
        if(mAccessibilityFocusedVirtualViewId == i)
        {
            mAccessibilityFocusedVirtualViewId = 0x80000000;
            mHost.invalidate();
            sendEventForVirtualView(i, 0x10000);
            return true;
        } else
        {
            return false;
        }
    }

    private boolean clickKeyboardFocusedVirtualView()
    {
        return mKeyboardFocusedVirtualViewId != 0x80000000 && onPerformActionForVirtualView(mKeyboardFocusedVirtualViewId, 16, null);
    }

    private AccessibilityEvent createEvent(int i, int j)
    {
        switch(i)
        {
        default:
            return createEventForChild(i, j);

        case -1: 
            return createEventForHost(j);
        }
    }

    private AccessibilityEvent createEventForChild(int i, int j)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(j);
        AccessibilityRecordCompat accessibilityrecordcompat = AccessibilityEventCompat.asRecord(accessibilityevent);
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = obtainAccessibilityNodeInfo(i);
        accessibilityrecordcompat.getText().add(accessibilitynodeinfocompat.getText());
        accessibilityrecordcompat.setContentDescription(accessibilitynodeinfocompat.getContentDescription());
        accessibilityrecordcompat.setScrollable(accessibilitynodeinfocompat.isScrollable());
        accessibilityrecordcompat.setPassword(accessibilitynodeinfocompat.isPassword());
        accessibilityrecordcompat.setEnabled(accessibilitynodeinfocompat.isEnabled());
        accessibilityrecordcompat.setChecked(accessibilitynodeinfocompat.isChecked());
        onPopulateEventForVirtualView(i, accessibilityevent);
        if(accessibilityevent.getText().isEmpty() && accessibilityevent.getContentDescription() == null)
        {
            throw new RuntimeException("Callbacks must add text or a content description in populateEventForVirtualViewId()");
        } else
        {
            accessibilityrecordcompat.setClassName(accessibilitynodeinfocompat.getClassName());
            accessibilityrecordcompat.setSource(mHost, i);
            accessibilityevent.setPackageName(mHost.getContext().getPackageName());
            return accessibilityevent;
        }
    }

    private AccessibilityEvent createEventForHost(int i)
    {
        AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(i);
        ViewCompat.onInitializeAccessibilityEvent(mHost, accessibilityevent);
        return accessibilityevent;
    }

    private AccessibilityNodeInfoCompat createNodeForChild(int i)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat;
        accessibilitynodeinfocompat = AccessibilityNodeInfoCompat.obtain();
        accessibilitynodeinfocompat.setEnabled(true);
        accessibilitynodeinfocompat.setFocusable(true);
        accessibilitynodeinfocompat.setClassName("android.view.View");
        accessibilitynodeinfocompat.setBoundsInParent(INVALID_PARENT_BOUNDS);
        accessibilitynodeinfocompat.setBoundsInScreen(INVALID_PARENT_BOUNDS);
        onPopulateNodeForVirtualView(i, accessibilitynodeinfocompat);
        if(accessibilitynodeinfocompat.getText() == null && accessibilitynodeinfocompat.getContentDescription() == null)
            throw new RuntimeException("Callbacks must add text or a content description in populateNodeForVirtualViewId()");
        accessibilitynodeinfocompat.getBoundsInParent(mTempParentRect);
        if(mTempParentRect.equals(INVALID_PARENT_BOUNDS))
            throw new RuntimeException("Callbacks must set parent bounds in populateNodeForVirtualViewId()");
        int j = accessibilitynodeinfocompat.getActions();
        if((j & 0x40) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        if((j & 0x80) != 0)
            throw new RuntimeException("Callbacks must not add ACTION_CLEAR_ACCESSIBILITY_FOCUS in populateNodeForVirtualViewId()");
        accessibilitynodeinfocompat.setPackageName(mHost.getContext().getPackageName());
        accessibilitynodeinfocompat.setSource(mHost, i);
        accessibilitynodeinfocompat.setParent(mHost);
        boolean flag;
        if(mAccessibilityFocusedVirtualViewId == i)
        {
            accessibilitynodeinfocompat.setAccessibilityFocused(true);
            accessibilitynodeinfocompat.addAction(128);
        } else
        {
            accessibilitynodeinfocompat.setAccessibilityFocused(false);
            accessibilitynodeinfocompat.addAction(64);
        }
        if(mKeyboardFocusedVirtualViewId == i)
            flag = true;
        else
            flag = false;
        if(!flag) goto _L2; else goto _L1
_L1:
        accessibilitynodeinfocompat.addAction(2);
_L4:
        accessibilitynodeinfocompat.setFocused(flag);
        if(intersectVisibleToUser(mTempParentRect))
        {
            accessibilitynodeinfocompat.setVisibleToUser(true);
            accessibilitynodeinfocompat.setBoundsInParent(mTempParentRect);
        }
        accessibilitynodeinfocompat.getBoundsInScreen(mTempScreenRect);
        if(mTempScreenRect.equals(INVALID_PARENT_BOUNDS))
        {
            mHost.getLocationOnScreen(mTempGlobalRect);
            accessibilitynodeinfocompat.getBoundsInParent(mTempScreenRect);
            mTempScreenRect.offset(mTempGlobalRect[0] - mHost.getScrollX(), mTempGlobalRect[1] - mHost.getScrollY());
            accessibilitynodeinfocompat.setBoundsInScreen(mTempScreenRect);
        }
        return accessibilitynodeinfocompat;
_L2:
        if(accessibilitynodeinfocompat.isFocusable())
            accessibilitynodeinfocompat.addAction(1);
        if(true) goto _L4; else goto _L3
_L3:
    }

    private AccessibilityNodeInfoCompat createNodeForHost()
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat = AccessibilityNodeInfoCompat.obtain(mHost);
        ViewCompat.onInitializeAccessibilityNodeInfo(mHost, accessibilitynodeinfocompat);
        ArrayList arraylist = new ArrayList();
        getVisibleVirtualViews(arraylist);
        if(accessibilitynodeinfocompat.getChildCount() > 0 && arraylist.size() > 0)
            throw new RuntimeException("Views cannot have both real and virtual children");
        int i = 0;
        for(int j = arraylist.size(); i < j; i++)
            accessibilitynodeinfocompat.addChild(mHost, ((Integer)arraylist.get(i)).intValue());

        return accessibilitynodeinfocompat;
    }

    private SparseArrayCompat getAllNodes()
    {
        ArrayList arraylist = new ArrayList();
        getVisibleVirtualViews(arraylist);
        SparseArrayCompat sparsearraycompat = new SparseArrayCompat();
        for(int i = 0; i < arraylist.size(); i++)
            sparsearraycompat.put(i, createNodeForChild(i));

        return sparsearraycompat;
    }

    private void getBoundsInParent(int i, Rect rect)
    {
        obtainAccessibilityNodeInfo(i).getBoundsInParent(rect);
    }

    private static Rect guessPreviouslyFocusedRect(View view, int i, Rect rect)
    {
        int j = view.getWidth();
        int k = view.getHeight();
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
            rect.set(j, 0, j, k);
            return rect;

        case 33: // '!'
            rect.set(0, k, j, k);
            return rect;

        case 66: // 'B'
            rect.set(-1, 0, -1, k);
            return rect;

        case 130: 
            rect.set(0, -1, j, -1);
            break;
        }
        return rect;
    }

    private boolean intersectVisibleToUser(Rect rect)
    {
_L2:
        return false;
        if(rect == null || rect.isEmpty() || mHost.getWindowVisibility() != 0) goto _L2; else goto _L1
_L1:
        Object obj = mHost.getParent();
_L4:
        if(!(obj instanceof View))
            continue; /* Loop/switch isn't completed */
        obj = (View)obj;
        if(ViewCompat.getAlpha(((View) (obj))) <= 0.0F || ((View) (obj)).getVisibility() != 0) goto _L2; else goto _L3
_L3:
        obj = ((View) (obj)).getParent();
          goto _L4
        if(obj == null || !mHost.getLocalVisibleRect(mTempVisibleRect)) goto _L2; else goto _L5
_L5:
        return rect.intersect(mTempVisibleRect);
    }

    private static int keyToDirection(int i)
    {
        switch(i)
        {
        case 20: // '\024'
        default:
            return 130;

        case 21: // '\025'
            return 17;

        case 19: // '\023'
            return 33;

        case 22: // '\026'
            return 66;
        }
    }

    private boolean moveFocus(int i, Rect rect)
    {
        AccessibilityNodeInfoCompat accessibilitynodeinfocompat;
        SparseArrayCompat sparsearraycompat;
        sparsearraycompat = getAllNodes();
        int j = mKeyboardFocusedVirtualViewId;
        if(j == 0x80000000)
            accessibilitynodeinfocompat = null;
        else
            accessibilitynodeinfocompat = (AccessibilityNodeInfoCompat)sparsearraycompat.get(j);
        switch(i)
        {
        default:
            throw new IllegalArgumentException("direction must be one of {FOCUS_FORWARD, FOCUS_BACKWARD, FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");

        case 17: // '\021'
        case 33: // '!'
        case 66: // 'B'
        case 130: 
            break MISSING_BLOCK_LABEL_159;

        case 1: // '\001'
        case 2: // '\002'
            boolean flag;
            if(ViewCompat.getLayoutDirection(mHost) == 1)
                flag = true;
            else
                flag = false;
            rect = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInRelativeDirection(sparsearraycompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilitynodeinfocompat, i, flag, false);
            break;
        }
_L1:
        Rect rect1;
        if(rect == null)
            i = 0x80000000;
        else
            i = sparsearraycompat.keyAt(sparsearraycompat.indexOfValue(rect));
        return requestKeyboardFocusForVirtualView(i);
        rect1 = new Rect();
        if(mKeyboardFocusedVirtualViewId != 0x80000000)
            getBoundsInParent(mKeyboardFocusedVirtualViewId, rect1);
        else
        if(rect != null)
            rect1.set(rect);
        else
            guessPreviouslyFocusedRect(mHost, i, rect1);
        rect = (AccessibilityNodeInfoCompat)FocusStrategy.findNextFocusInAbsoluteDirection(sparsearraycompat, SPARSE_VALUES_ADAPTER, NODE_ADAPTER, accessibilitynodeinfocompat, rect1, i);
          goto _L1
    }

    private boolean performActionForChild(int i, int j, Bundle bundle)
    {
        switch(j)
        {
        default:
            return onPerformActionForVirtualView(i, j, bundle);

        case 64: // '@'
            return requestAccessibilityFocus(i);

        case 128: 
            return clearAccessibilityFocus(i);

        case 1: // '\001'
            return requestKeyboardFocusForVirtualView(i);

        case 2: // '\002'
            return clearKeyboardFocusForVirtualView(i);
        }
    }

    private boolean performActionForHost(int i, Bundle bundle)
    {
        return ViewCompat.performAccessibilityAction(mHost, i, bundle);
    }

    private boolean requestAccessibilityFocus(int i)
    {
        while(!mManager.isEnabled() || !AccessibilityManagerCompat.isTouchExplorationEnabled(mManager) || mAccessibilityFocusedVirtualViewId == i) 
            return false;
        if(mAccessibilityFocusedVirtualViewId != 0x80000000)
            clearAccessibilityFocus(mAccessibilityFocusedVirtualViewId);
        mAccessibilityFocusedVirtualViewId = i;
        mHost.invalidate();
        sendEventForVirtualView(i, 32768);
        return true;
    }

    private void updateHoveredVirtualView(int i)
    {
        if(mHoveredVirtualViewId == i)
        {
            return;
        } else
        {
            int j = mHoveredVirtualViewId;
            mHoveredVirtualViewId = i;
            sendEventForVirtualView(i, 128);
            sendEventForVirtualView(j, 256);
            return;
        }
    }

    public final boolean clearKeyboardFocusForVirtualView(int i)
    {
        if(mKeyboardFocusedVirtualViewId != i)
        {
            return false;
        } else
        {
            mKeyboardFocusedVirtualViewId = 0x80000000;
            onVirtualViewKeyboardFocusChanged(i, false);
            sendEventForVirtualView(i, 8);
            return true;
        }
    }

    public final boolean dispatchHoverEvent(MotionEvent motionevent)
    {
        boolean flag = true;
        if(mManager.isEnabled() && AccessibilityManagerCompat.isTouchExplorationEnabled(mManager))
        {
            switch(motionevent.getAction())
            {
            case 8: // '\b'
            default:
                return false;

            case 7: // '\007'
            case 9: // '\t'
                int i = getVirtualViewAt(motionevent.getX(), motionevent.getY());
                updateHoveredVirtualView(i);
                if(i == 0x80000000)
                    flag = false;
                return flag;

            case 10: // '\n'
                break;
            }
            if(mAccessibilityFocusedVirtualViewId != 0x80000000)
            {
                updateHoveredVirtualView(0x80000000);
                return true;
            }
        }
        return false;
    }

    public final boolean dispatchKeyEvent(KeyEvent keyevent)
    {
        boolean flag;
        boolean flag1;
        boolean flag2;
        flag2 = false;
        flag = false;
        flag1 = flag;
        if(keyevent.getAction() == 1) goto _L2; else goto _L1
_L1:
        int i = keyevent.getKeyCode();
        i;
        JVM INSTR lookupswitch 7: default 92
    //                   19: 99
    //                   20: 99
    //                   21: 99
    //                   22: 99
    //                   23: 162
    //                   61: 191
    //                   66: 162;
           goto _L3 _L4 _L4 _L4 _L4 _L5 _L6 _L5
_L3:
        flag1 = flag;
_L2:
        return flag1;
_L4:
        flag1 = flag;
        if(!KeyEventCompat.hasNoModifiers(keyevent))
            continue; /* Loop/switch isn't completed */
        int j = keyToDirection(i);
        int k = keyevent.getRepeatCount();
        i = 0;
        flag = flag2;
        do
        {
            flag1 = flag;
            if(i >= k + 1)
                continue; /* Loop/switch isn't completed */
            flag1 = flag;
            if(!moveFocus(j, null))
                continue; /* Loop/switch isn't completed */
            flag = true;
            i++;
        } while(true);
_L5:
        flag1 = flag;
        if(KeyEventCompat.hasNoModifiers(keyevent))
        {
            flag1 = flag;
            if(keyevent.getRepeatCount() == 0)
            {
                clickKeyboardFocusedVirtualView();
                return true;
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        if(KeyEventCompat.hasNoModifiers(keyevent))
            return moveFocus(2, null);
        flag1 = flag;
        if(KeyEventCompat.hasModifiers(keyevent, 1))
            return moveFocus(1, null);
        if(true) goto _L2; else goto _L7
_L7:
    }

    public final int getAccessibilityFocusedVirtualViewId()
    {
        return mAccessibilityFocusedVirtualViewId;
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        if(mNodeProvider == null)
            mNodeProvider = new MyNodeProvider();
        return mNodeProvider;
    }

    public int getFocusedVirtualView()
    {
        return getAccessibilityFocusedVirtualViewId();
    }

    public final int getKeyboardFocusedVirtualViewId()
    {
        return mKeyboardFocusedVirtualViewId;
    }

    protected abstract int getVirtualViewAt(float f, float f1);

    protected abstract void getVisibleVirtualViews(List list);

    public final void invalidateRoot()
    {
        invalidateVirtualView(-1, 1);
    }

    public final void invalidateVirtualView(int i)
    {
        invalidateVirtualView(i, 0);
    }

    public final void invalidateVirtualView(int i, int j)
    {
        if(i != 0x80000000 && mManager.isEnabled())
        {
            android.view.ViewParent viewparent = mHost.getParent();
            if(viewparent != null)
            {
                AccessibilityEvent accessibilityevent = createEvent(i, 2048);
                AccessibilityEventCompat.setContentChangeTypes(accessibilityevent, j);
                ViewParentCompat.requestSendAccessibilityEvent(viewparent, mHost, accessibilityevent);
            }
        }
    }

    AccessibilityNodeInfoCompat obtainAccessibilityNodeInfo(int i)
    {
        if(i == -1)
            return createNodeForHost();
        else
            return createNodeForChild(i);
    }

    public final void onFocusChanged(boolean flag, int i, Rect rect)
    {
        if(mKeyboardFocusedVirtualViewId != 0x80000000)
            clearKeyboardFocusForVirtualView(mKeyboardFocusedVirtualViewId);
        if(flag)
            moveFocus(i, rect);
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        super.onInitializeAccessibilityEvent(view, accessibilityevent);
        onPopulateEventForHost(accessibilityevent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        onPopulateNodeForHost(accessibilitynodeinfocompat);
    }

    protected abstract boolean onPerformActionForVirtualView(int i, int j, Bundle bundle);

    protected void onPopulateEventForHost(AccessibilityEvent accessibilityevent)
    {
    }

    protected void onPopulateEventForVirtualView(int i, AccessibilityEvent accessibilityevent)
    {
    }

    protected void onPopulateNodeForHost(AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
    }

    protected abstract void onPopulateNodeForVirtualView(int i, AccessibilityNodeInfoCompat accessibilitynodeinfocompat);

    protected void onVirtualViewKeyboardFocusChanged(int i, boolean flag)
    {
    }

    boolean performAction(int i, int j, Bundle bundle)
    {
        switch(i)
        {
        default:
            return performActionForChild(i, j, bundle);

        case -1: 
            return performActionForHost(j, bundle);
        }
    }

    public final boolean requestKeyboardFocusForVirtualView(int i)
    {
        while(!mHost.isFocused() && !mHost.requestFocus() || mKeyboardFocusedVirtualViewId == i) 
            return false;
        if(mKeyboardFocusedVirtualViewId != 0x80000000)
            clearKeyboardFocusForVirtualView(mKeyboardFocusedVirtualViewId);
        mKeyboardFocusedVirtualViewId = i;
        onVirtualViewKeyboardFocusChanged(i, true);
        sendEventForVirtualView(i, 8);
        return true;
    }

    public final boolean sendEventForVirtualView(int i, int j)
    {
        android.view.ViewParent viewparent;
        if(i != 0x80000000 && mManager.isEnabled())
            if((viewparent = mHost.getParent()) != null)
            {
                AccessibilityEvent accessibilityevent = createEvent(i, j);
                return ViewParentCompat.requestSendAccessibilityEvent(viewparent, mHost, accessibilityevent);
            }
        return false;
    }

    private static final String DEFAULT_CLASS_NAME = "android.view.View";
    public static final int HOST_ID = -1;
    public static final int INVALID_ID = 0x80000000;
    private static final Rect INVALID_PARENT_BOUNDS = new Rect(0x7fffffff, 0x7fffffff, 0x80000000, 0x80000000);
    private static final FocusStrategy.BoundsAdapter NODE_ADAPTER = new FocusStrategy.BoundsAdapter() {

        public void obtainBounds(AccessibilityNodeInfoCompat accessibilitynodeinfocompat, Rect rect)
        {
            accessibilitynodeinfocompat.getBoundsInParent(rect);
        }

        public volatile void obtainBounds(Object obj, Rect rect)
        {
            obtainBounds((AccessibilityNodeInfoCompat)obj, rect);
        }

    }
;
    private static final FocusStrategy.CollectionAdapter SPARSE_VALUES_ADAPTER = new FocusStrategy.CollectionAdapter() {

        public AccessibilityNodeInfoCompat get(SparseArrayCompat sparsearraycompat, int i)
        {
            return (AccessibilityNodeInfoCompat)sparsearraycompat.valueAt(i);
        }

        public volatile Object get(Object obj, int i)
        {
            return get((SparseArrayCompat)obj, i);
        }

        public int size(SparseArrayCompat sparsearraycompat)
        {
            return sparsearraycompat.size();
        }

        public volatile int size(Object obj)
        {
            return size((SparseArrayCompat)obj);
        }

    }
;
    private int mAccessibilityFocusedVirtualViewId;
    private final View mHost;
    private int mHoveredVirtualViewId;
    private int mKeyboardFocusedVirtualViewId;
    private final AccessibilityManager mManager;
    private MyNodeProvider mNodeProvider;
    private final int mTempGlobalRect[] = new int[2];
    private final Rect mTempParentRect = new Rect();
    private final Rect mTempScreenRect = new Rect();
    private final Rect mTempVisibleRect = new Rect();

}
