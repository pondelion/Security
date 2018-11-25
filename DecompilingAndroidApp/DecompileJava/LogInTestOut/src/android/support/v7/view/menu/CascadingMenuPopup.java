// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.*;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.MenuItemHoverListener;
import android.support.v7.widget.MenuPopupWindow;
import android.util.DisplayMetrics;
import android.view.*;
import android.widget.*;
import java.lang.annotation.Annotation;
import java.util.*;

// Referenced classes of package android.support.v7.view.menu:
//            MenuPopup, MenuPresenter, MenuBuilder, MenuAdapter, 
//            SubMenuBuilder

final class CascadingMenuPopup extends MenuPopup
    implements MenuPresenter, android.view.View.OnKeyListener, android.widget.PopupWindow.OnDismissListener
{
    private static class CascadingMenuInfo
    {

        public ListView getListView()
        {
            return window.getListView();
        }

        public final MenuBuilder menu;
        public final int position;
        public final MenuPopupWindow window;

        public CascadingMenuInfo(MenuPopupWindow menupopupwindow, MenuBuilder menubuilder, int i)
        {
            window = menupopupwindow;
            menu = menubuilder;
            position = i;
        }
    }

    public static interface HorizPosition
        extends Annotation
    {
    }


    public CascadingMenuPopup(Context context, View view, int i, int j, boolean flag)
    {
        mRawDropDownGravity = 0;
        mDropDownGravity = 0;
        mContext = context;
        mAnchorView = view;
        mPopupStyleAttr = i;
        mPopupStyleRes = j;
        mOverflowOnly = flag;
        mForceShowIcon = false;
        mLastPosition = getInitialMenuPosition();
        context = context.getResources();
        mMenuMaxWidth = Math.max(context.getDisplayMetrics().widthPixels / 2, context.getDimensionPixelSize(android.support.v7.appcompat.R.dimen.abc_config_prefDialogWidth));
    }

    private MenuPopupWindow createPopupWindow()
    {
        MenuPopupWindow menupopupwindow = new MenuPopupWindow(mContext, null, mPopupStyleAttr, mPopupStyleRes);
        menupopupwindow.setHoverListener(mMenuItemHoverListener);
        menupopupwindow.setOnItemClickListener(this);
        menupopupwindow.setOnDismissListener(this);
        menupopupwindow.setAnchorView(mAnchorView);
        menupopupwindow.setDropDownGravity(mDropDownGravity);
        menupopupwindow.setModal(true);
        return menupopupwindow;
    }

    private int findIndexOfAddedMenu(MenuBuilder menubuilder)
    {
        int i = 0;
        for(int j = mShowingMenus.size(); i < j; i++)
            if(menubuilder == ((CascadingMenuInfo)mShowingMenus.get(i)).menu)
                return i;

        return -1;
    }

    private MenuItem findMenuItemForSubmenu(MenuBuilder menubuilder, MenuBuilder menubuilder1)
    {
        int i = 0;
        for(int j = menubuilder.size(); i < j; i++)
        {
            MenuItem menuitem = menubuilder.getItem(i);
            if(menuitem.hasSubMenu() && menubuilder1 == menuitem.getSubMenu())
                return menuitem;
        }

        return null;
    }

    private View findParentViewForSubmenu(CascadingMenuInfo cascadingmenuinfo, MenuBuilder menubuilder)
    {
        menubuilder = findMenuItemForSubmenu(cascadingmenuinfo.menu, menubuilder);
        if(menubuilder != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i;
        ListView listview = cascadingmenuinfo.getListView();
        cascadingmenuinfo = listview.getAdapter();
        int j;
        int k;
        byte byte0;
        int l;
        if(cascadingmenuinfo instanceof HeaderViewListAdapter)
        {
            cascadingmenuinfo = (HeaderViewListAdapter)cascadingmenuinfo;
            j = cascadingmenuinfo.getHeadersCount();
            cascadingmenuinfo = (MenuAdapter)cascadingmenuinfo.getWrappedAdapter();
        } else
        {
            j = 0;
            cascadingmenuinfo = (MenuAdapter)cascadingmenuinfo;
        }
        byte0 = -1;
        i = 0;
        l = cascadingmenuinfo.getCount();
_L4:
        k = byte0;
        if(i >= l)
            continue; /* Loop/switch isn't completed */
        if(menubuilder != cascadingmenuinfo.getItem(i))
            break MISSING_BLOCK_LABEL_136;
        k = i;
        if(k == -1) goto _L1; else goto _L3
_L3:
        i = (k + j) - listview.getFirstVisiblePosition();
        if(i >= 0 && i < listview.getChildCount())
            return listview.getChildAt(i);
          goto _L1
        i++;
          goto _L4
    }

    private int getInitialMenuPosition()
    {
        int i = 1;
        if(ViewCompat.getLayoutDirection(mAnchorView) == 1)
            i = 0;
        return i;
    }

    private int getNextMenuPosition(int i)
    {
        ListView listview = ((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).getListView();
        int ai[] = new int[2];
        listview.getLocationOnScreen(ai);
        Rect rect = new Rect();
        mShownAnchorView.getWindowVisibleDisplayFrame(rect);
        if(mLastPosition == 1)
            return ai[0] + listview.getWidth() + i <= rect.right ? 1 : 0;
        return ai[0] - i >= 0 ? 0 : 1;
    }

    private void showMenu(MenuBuilder menubuilder)
    {
        Object obj2 = LayoutInflater.from(mContext);
        Object obj = new MenuAdapter(menubuilder, ((LayoutInflater) (obj2)), mOverflowOnly);
        int l;
        Object obj1;
        MenuPopupWindow menupopupwindow;
        if(!isShowing() && mForceShowIcon)
            ((MenuAdapter) (obj)).setForceShowIcon(true);
        else
        if(isShowing())
            ((MenuAdapter) (obj)).setForceShowIcon(MenuPopup.shouldPreserveIconSpacing(menubuilder));
        l = measureIndividualMenuWidth(((android.widget.ListAdapter) (obj)), null, mContext, mMenuMaxWidth);
        menupopupwindow = createPopupWindow();
        menupopupwindow.setAdapter(((android.widget.ListAdapter) (obj)));
        menupopupwindow.setContentWidth(l);
        menupopupwindow.setDropDownGravity(mDropDownGravity);
        if(mShowingMenus.size() > 0)
        {
            obj = (CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1);
            obj1 = findParentViewForSubmenu(((CascadingMenuInfo) (obj)), menubuilder);
        } else
        {
            obj = null;
            obj1 = null;
        }
        if(obj1 != null)
        {
            menupopupwindow.setTouchModal(false);
            menupopupwindow.setEnterTransition(null);
            int j = getNextMenuPosition(l);
            int i;
            int k;
            int i1;
            int ai[];
            if(j == 1)
                i = 1;
            else
                i = 0;
            mLastPosition = j;
            ai = new int[2];
            ((View) (obj1)).getLocationInWindow(ai);
            i1 = ((CascadingMenuInfo) (obj)).window.getHorizontalOffset() + ai[0];
            j = ((CascadingMenuInfo) (obj)).window.getVerticalOffset();
            k = ai[1];
            if((mDropDownGravity & 5) == 5)
            {
                if(i != 0)
                    i = i1 + l;
                else
                    i = i1 - ((View) (obj1)).getWidth();
            } else
            if(i != 0)
                i = i1 + ((View) (obj1)).getWidth();
            else
                i = i1 - l;
            menupopupwindow.setHorizontalOffset(i);
            menupopupwindow.setVerticalOffset(j + k);
        } else
        {
            if(mHasXOffset)
                menupopupwindow.setHorizontalOffset(mXOffset);
            if(mHasYOffset)
                menupopupwindow.setVerticalOffset(mYOffset);
            menupopupwindow.setEpicenterBounds(getEpicenterBounds());
        }
        obj1 = new CascadingMenuInfo(menupopupwindow, menubuilder, mLastPosition);
        mShowingMenus.add(obj1);
        menupopupwindow.show();
        if(obj == null && mShowTitle && menubuilder.getHeaderTitle() != null)
        {
            obj = menupopupwindow.getListView();
            obj1 = (FrameLayout)((LayoutInflater) (obj2)).inflate(android.support.v7.appcompat.R.layout.abc_popup_menu_header_item_layout, ((android.view.ViewGroup) (obj)), false);
            obj2 = (TextView)((FrameLayout) (obj1)).findViewById(0x1020016);
            ((FrameLayout) (obj1)).setEnabled(false);
            ((TextView) (obj2)).setText(menubuilder.getHeaderTitle());
            ((ListView) (obj)).addHeaderView(((View) (obj1)), null, false);
            menupopupwindow.show();
        }
    }

    public void addMenu(MenuBuilder menubuilder)
    {
        menubuilder.addMenuPresenter(this, mContext);
        if(isShowing())
        {
            showMenu(menubuilder);
            return;
        } else
        {
            mPendingMenus.add(menubuilder);
            return;
        }
    }

    protected boolean closeMenuOnSubMenuOpened()
    {
        return false;
    }

    public void dismiss()
    {
        int i = mShowingMenus.size();
        if(i > 0)
        {
            CascadingMenuInfo acascadingmenuinfo[] = (CascadingMenuInfo[])mShowingMenus.toArray(new CascadingMenuInfo[i]);
            for(i--; i >= 0; i--)
            {
                CascadingMenuInfo cascadingmenuinfo = acascadingmenuinfo[i];
                if(cascadingmenuinfo.window.isShowing())
                    cascadingmenuinfo.window.dismiss();
            }

        }
    }

    public boolean flagActionItems()
    {
        return false;
    }

    public ListView getListView()
    {
        if(mShowingMenus.isEmpty())
            return null;
        else
            return ((CascadingMenuInfo)mShowingMenus.get(mShowingMenus.size() - 1)).getListView();
    }

    public boolean isShowing()
    {
        return mShowingMenus.size() > 0 && ((CascadingMenuInfo)mShowingMenus.get(0)).window.isShowing();
    }

    public void onCloseMenu(MenuBuilder menubuilder, boolean flag)
    {
        int i = findIndexOfAddedMenu(menubuilder);
        if(i >= 0)
        {
            int j = i + 1;
            if(j < mShowingMenus.size())
                ((CascadingMenuInfo)mShowingMenus.get(j)).menu.close(false);
            CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.remove(i);
            cascadingmenuinfo.menu.removeMenuPresenter(this);
            if(mShouldCloseImmediately)
            {
                cascadingmenuinfo.window.setExitTransition(null);
                cascadingmenuinfo.window.setAnimationStyle(0);
            }
            cascadingmenuinfo.window.dismiss();
            i = mShowingMenus.size();
            if(i > 0)
                mLastPosition = ((CascadingMenuInfo)mShowingMenus.get(i - 1)).position;
            else
                mLastPosition = getInitialMenuPosition();
            if(i == 0)
            {
                dismiss();
                if(mPresenterCallback != null)
                    mPresenterCallback.onCloseMenu(menubuilder, true);
                if(mTreeObserver != null)
                {
                    if(mTreeObserver.isAlive())
                        mTreeObserver.removeGlobalOnLayoutListener(mGlobalLayoutListener);
                    mTreeObserver = null;
                }
                mOnDismissListener.onDismiss();
                return;
            }
            if(flag)
            {
                ((CascadingMenuInfo)mShowingMenus.get(0)).menu.close(false);
                return;
            }
        }
    }

    public void onDismiss()
    {
        Object obj = null;
        int i = 0;
        int j = mShowingMenus.size();
        do
        {
label0:
            {
                CascadingMenuInfo cascadingmenuinfo = obj;
                if(i < j)
                {
                    cascadingmenuinfo = (CascadingMenuInfo)mShowingMenus.get(i);
                    if(cascadingmenuinfo.window.isShowing())
                        break label0;
                }
                if(cascadingmenuinfo != null)
                    cascadingmenuinfo.menu.close(false);
                return;
            }
            i++;
        } while(true);
    }

    public boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if(keyevent.getAction() == 1 && i == 82)
        {
            dismiss();
            return true;
        } else
        {
            return false;
        }
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
    }

    public Parcelable onSaveInstanceState()
    {
        return null;
    }

    public boolean onSubMenuSelected(SubMenuBuilder submenubuilder)
    {
        Iterator iterator = mShowingMenus.iterator();
_L4:
        if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        CascadingMenuInfo cascadingmenuinfo = (CascadingMenuInfo)iterator.next();
        if(submenubuilder != cascadingmenuinfo.menu) goto _L4; else goto _L3
_L3:
        cascadingmenuinfo.getListView().requestFocus();
_L6:
        return true;
_L2:
        if(submenubuilder.hasVisibleItems())
        {
            addMenu(submenubuilder);
            if(mPresenterCallback != null)
            {
                mPresenterCallback.onOpenSubMenu(submenubuilder);
                return true;
            }
        } else
        {
            return false;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void setAnchorView(View view)
    {
        if(mAnchorView != view)
        {
            mAnchorView = view;
            mDropDownGravity = GravityCompat.getAbsoluteGravity(mRawDropDownGravity, ViewCompat.getLayoutDirection(mAnchorView));
        }
    }

    public void setCallback(MenuPresenter.Callback callback)
    {
        mPresenterCallback = callback;
    }

    public void setForceShowIcon(boolean flag)
    {
        mForceShowIcon = flag;
    }

    public void setGravity(int i)
    {
        if(mRawDropDownGravity != i)
        {
            mRawDropDownGravity = i;
            mDropDownGravity = GravityCompat.getAbsoluteGravity(i, ViewCompat.getLayoutDirection(mAnchorView));
        }
    }

    public void setHorizontalOffset(int i)
    {
        mHasXOffset = true;
        mXOffset = i;
    }

    public void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener)
    {
        mOnDismissListener = ondismisslistener;
    }

    public void setShowTitle(boolean flag)
    {
        mShowTitle = flag;
    }

    public void setVerticalOffset(int i)
    {
        mHasYOffset = true;
        mYOffset = i;
    }

    public void show()
    {
        if(!isShowing())
        {
            for(Iterator iterator = mPendingMenus.iterator(); iterator.hasNext(); showMenu((MenuBuilder)iterator.next()));
            mPendingMenus.clear();
            mShownAnchorView = mAnchorView;
            if(mShownAnchorView != null)
            {
                boolean flag;
                if(mTreeObserver == null)
                    flag = true;
                else
                    flag = false;
                mTreeObserver = mShownAnchorView.getViewTreeObserver();
                if(flag)
                {
                    mTreeObserver.addOnGlobalLayoutListener(mGlobalLayoutListener);
                    return;
                }
            }
        }
    }

    public void updateMenuView(boolean flag)
    {
        for(Iterator iterator = mShowingMenus.iterator(); iterator.hasNext(); toMenuAdapter(((CascadingMenuInfo)iterator.next()).getListView().getAdapter()).notifyDataSetChanged());
    }

    static final int HORIZ_POSITION_LEFT = 0;
    static final int HORIZ_POSITION_RIGHT = 1;
    static final int SUBMENU_TIMEOUT_MS = 200;
    private View mAnchorView;
    private final Context mContext;
    private int mDropDownGravity;
    private boolean mForceShowIcon;
    private final android.view.ViewTreeObserver.OnGlobalLayoutListener mGlobalLayoutListener = new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

        public void onGlobalLayout()
        {
            if(isShowing() && mShowingMenus.size() > 0 && !((CascadingMenuInfo)mShowingMenus.get(0)).window.isModal())
            {
                View view1 = mShownAnchorView;
                if(view1 == null || !view1.isShown())
                {
                    dismiss();
                } else
                {
                    Iterator iterator = mShowingMenus.iterator();
                    while(iterator.hasNext()) 
                        ((CascadingMenuInfo)iterator.next()).window.show();
                }
            }
        }

        final CascadingMenuPopup this$0;

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }
;
    private boolean mHasXOffset;
    private boolean mHasYOffset;
    private int mLastPosition;
    private final MenuItemHoverListener mMenuItemHoverListener = new MenuItemHoverListener() {

        public void onItemHoverEnter(MenuBuilder menubuilder, final MenuItem item)
        {
            mSubMenuHoverHandler.removeCallbacksAndMessages(null);
            byte byte0 = -1;
            int k = 0;
            int i1 = mShowingMenus.size();
            int l;
label0:
            do
            {
label1:
                {
                    l = byte0;
                    if(k < i1)
                    {
                        if(menubuilder != ((CascadingMenuInfo)mShowingMenus.get(k)).menu)
                            break label1;
                        l = k;
                    }
                    if(l == -1)
                        return;
                    break label0;
                }
                k++;
            } while(true);
            k = l + 1;
            long l1;
            final CascadingMenuInfo nextInfo;
            if(k < mShowingMenus.size())
                nextInfo = (CascadingMenuInfo)mShowingMenus.get(k);
            else
                nextInfo = null;
            item = menubuilder. new Runnable() {

                public void run()
                {
                    if(nextInfo != null)
                    {
                        mShouldCloseImmediately = true;
                        nextInfo.menu.close(false);
                        mShouldCloseImmediately = false;
                    }
                    if(item.isEnabled() && item.hasSubMenu())
                        menu.performItemAction(item, 0);
                }

                final _cls2 this$1;
                final MenuItem val$item;
                final MenuBuilder val$menu;
                final CascadingMenuInfo val$nextInfo;

            
            {
                this$1 = final__pcls2;
                nextInfo = cascadingmenuinfo;
                item = menuitem;
                menu = MenuBuilder.this;
                super();
            }
            }
;
            l1 = SystemClock.uptimeMillis();
            mSubMenuHoverHandler.postAtTime(item, menubuilder, l1 + 200L);
        }

        public void onItemHoverExit(MenuBuilder menubuilder, MenuItem menuitem)
        {
            mSubMenuHoverHandler.removeCallbacksAndMessages(menubuilder);
        }

        final CascadingMenuPopup this$0;

            
            {
                this$0 = CascadingMenuPopup.this;
                super();
            }
    }
;
    private final int mMenuMaxWidth;
    private android.widget.PopupWindow.OnDismissListener mOnDismissListener;
    private final boolean mOverflowOnly;
    private final List mPendingMenus = new LinkedList();
    private final int mPopupStyleAttr;
    private final int mPopupStyleRes;
    private MenuPresenter.Callback mPresenterCallback;
    private int mRawDropDownGravity;
    boolean mShouldCloseImmediately;
    private boolean mShowTitle;
    final List mShowingMenus = new ArrayList();
    View mShownAnchorView;
    final Handler mSubMenuHoverHandler = new Handler();
    private ViewTreeObserver mTreeObserver;
    private int mXOffset;
    private int mYOffset;
}
