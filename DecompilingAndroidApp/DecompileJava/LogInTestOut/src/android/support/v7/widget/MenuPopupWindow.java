// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.view.menu.*;
import android.transition.Transition;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import android.widget.HeaderViewListAdapter;
import android.widget.PopupWindow;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            ListPopupWindow, MenuItemHoverListener, DropDownListView

public class MenuPopupWindow extends ListPopupWindow
    implements MenuItemHoverListener
{
    public static class MenuDropDownListView extends DropDownListView
    {

        public void clearSelection()
        {
            setSelection(-1);
        }

        public volatile boolean hasFocus()
        {
            return super.hasFocus();
        }

        public volatile boolean hasWindowFocus()
        {
            return super.hasWindowFocus();
        }

        public volatile boolean isFocused()
        {
            return super.isFocused();
        }

        public volatile boolean isInTouchMode()
        {
            return super.isInTouchMode();
        }

        public volatile boolean onForwardedEvent(MotionEvent motionevent, int i)
        {
            return super.onForwardedEvent(motionevent, i);
        }

        public boolean onHoverEvent(MotionEvent motionevent)
        {
            if(mHoverListener != null)
            {
                Object obj = getAdapter();
                int i;
                MenuItemImpl menuitemimpl;
                MenuItem menuitem;
                if(obj instanceof HeaderViewListAdapter)
                {
                    obj = (HeaderViewListAdapter)obj;
                    i = ((HeaderViewListAdapter) (obj)).getHeadersCount();
                    obj = (MenuAdapter)((HeaderViewListAdapter) (obj)).getWrappedAdapter();
                } else
                {
                    i = 0;
                    obj = (MenuAdapter)obj;
                }
                menuitem = null;
                menuitemimpl = menuitem;
                if(motionevent.getAction() != 10)
                {
                    int j = pointToPosition((int)motionevent.getX(), (int)motionevent.getY());
                    menuitemimpl = menuitem;
                    if(j != -1)
                    {
                        i = j - i;
                        menuitemimpl = menuitem;
                        if(i >= 0)
                        {
                            menuitemimpl = menuitem;
                            if(i < ((MenuAdapter) (obj)).getCount())
                                menuitemimpl = ((MenuAdapter) (obj)).getItem(i);
                        }
                    }
                }
                menuitem = mHoveredMenuItem;
                if(menuitem != menuitemimpl)
                {
                    obj = ((MenuAdapter) (obj)).getAdapterMenu();
                    if(menuitem != null)
                        mHoverListener.onItemHoverExit(((MenuBuilder) (obj)), menuitem);
                    mHoveredMenuItem = menuitemimpl;
                    if(menuitemimpl != null)
                        mHoverListener.onItemHoverEnter(((MenuBuilder) (obj)), menuitemimpl);
                }
            }
            return super.onHoverEvent(motionevent);
        }

        public boolean onKeyDown(int i, KeyEvent keyevent)
        {
            ListMenuItemView listmenuitemview = (ListMenuItemView)getSelectedView();
            if(listmenuitemview != null && i == mAdvanceKey)
            {
                if(listmenuitemview.isEnabled() && listmenuitemview.getItemData().hasSubMenu())
                    performItemClick(listmenuitemview, getSelectedItemPosition(), getSelectedItemId());
                return true;
            }
            if(listmenuitemview != null && i == mRetreatKey)
            {
                setSelection(-1);
                ((MenuAdapter)getAdapter()).getAdapterMenu().close(false);
                return true;
            } else
            {
                return super.onKeyDown(i, keyevent);
            }
        }

        public void setHoverListener(MenuItemHoverListener menuitemhoverlistener)
        {
            mHoverListener = menuitemhoverlistener;
        }

        final int mAdvanceKey;
        private MenuItemHoverListener mHoverListener;
        private MenuItem mHoveredMenuItem;
        final int mRetreatKey;

        public MenuDropDownListView(Context context, boolean flag)
        {
            super(context, flag);
            context = context.getResources().getConfiguration();
            if(android.os.Build.VERSION.SDK_INT >= 17 && 1 == context.getLayoutDirection())
            {
                mAdvanceKey = 21;
                mRetreatKey = 22;
                return;
            } else
            {
                mAdvanceKey = 22;
                mRetreatKey = 21;
                return;
            }
        }
    }


    public MenuPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
    }

    DropDownListView createDropDownListView(Context context, boolean flag)
    {
        context = new MenuDropDownListView(context, flag);
        context.setHoverListener(this);
        return context;
    }

    public void onItemHoverEnter(MenuBuilder menubuilder, MenuItem menuitem)
    {
        if(mHoverListener != null)
            mHoverListener.onItemHoverEnter(menubuilder, menuitem);
    }

    public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
    {
        if(mHoverListener != null)
            mHoverListener.onItemHoverExit(menubuilder, menuitem);
    }

    public void setEnterTransition(Object obj)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            mPopup.setEnterTransition((Transition)obj);
    }

    public void setExitTransition(Object obj)
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            mPopup.setExitTransition((Transition)obj);
    }

    public void setHoverListener(MenuItemHoverListener menuitemhoverlistener)
    {
        mHoverListener = menuitemhoverlistener;
    }

    public void setTouchModal(boolean flag)
    {
        if(sSetTouchModalMethod == null)
            break MISSING_BLOCK_LABEL_28;
        sSetTouchModalMethod.invoke(mPopup, new Object[] {
            Boolean.valueOf(flag)
        });
        return;
        Exception exception;
        exception;
        Log.i("MenuPopupWindow", "Could not invoke setTouchModal() on PopupWindow. Oh well.");
        return;
    }

    private static final String TAG = "MenuPopupWindow";
    private static Method sSetTouchModalMethod;
    private MenuItemHoverListener mHoverListener;

    static 
    {
        try
        {
            sSetTouchModalMethod = android/widget/PopupWindow.getDeclaredMethod("setTouchModal", new Class[] {
                Boolean.TYPE
            });
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            Log.i("MenuPopupWindow", "Could not find method setTouchModal() on PopupWindow. Oh well.");
        }
    }
}
