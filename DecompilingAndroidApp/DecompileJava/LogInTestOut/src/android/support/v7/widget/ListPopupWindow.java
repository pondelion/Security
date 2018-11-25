// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.view.menu.ShowableListMenu;
import android.util.*;
import android.view.*;
import android.widget.*;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            AppCompatPopupWindow, DropDownListView, ForwardingListener

public class ListPopupWindow
    implements ShowableListMenu
{
    private class ListSelectorHider
        implements Runnable
    {

        public void run()
        {
            clearListSelection();
        }

        final ListPopupWindow this$0;

        ListSelectorHider()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }

    private class PopupDataSetObserver extends DataSetObserver
    {

        public void onChanged()
        {
            if(isShowing())
                show();
        }

        public void onInvalidated()
        {
            dismiss();
        }

        final ListPopupWindow this$0;

        PopupDataSetObserver()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }

    private class PopupScrollListener
        implements android.widget.AbsListView.OnScrollListener
    {

        public void onScroll(AbsListView abslistview, int i, int j, int k)
        {
        }

        public void onScrollStateChanged(AbsListView abslistview, int i)
        {
            if(i == 1 && !isInputMethodNotNeeded() && mPopup.getContentView() != null)
            {
                mHandler.removeCallbacks(mResizePopupRunnable);
                mResizePopupRunnable.run();
            }
        }

        final ListPopupWindow this$0;

        PopupScrollListener()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }

    private class PopupTouchInterceptor
        implements android.view.View.OnTouchListener
    {

        public boolean onTouch(View view, MotionEvent motionevent)
        {
            int i;
            int j;
            int k;
            i = motionevent.getAction();
            j = (int)motionevent.getX();
            k = (int)motionevent.getY();
            if(i != 0 || mPopup == null || !mPopup.isShowing() || j < 0 || j >= mPopup.getWidth() || k < 0 || k >= mPopup.getHeight()) goto _L2; else goto _L1
_L1:
            mHandler.postDelayed(mResizePopupRunnable, 250L);
_L4:
            return false;
_L2:
            if(i == 1)
                mHandler.removeCallbacks(mResizePopupRunnable);
            if(true) goto _L4; else goto _L3
_L3:
        }

        final ListPopupWindow this$0;

        PopupTouchInterceptor()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }

    private class ResizePopupRunnable
        implements Runnable
    {

        public void run()
        {
            if(mDropDownList != null && ViewCompat.isAttachedToWindow(mDropDownList) && mDropDownList.getCount() > mDropDownList.getChildCount() && mDropDownList.getChildCount() <= mListItemExpandMaximum)
            {
                mPopup.setInputMethodMode(2);
                show();
            }
        }

        final ListPopupWindow this$0;

        ResizePopupRunnable()
        {
            this$0 = ListPopupWindow.this;
            super();
        }
    }


    public ListPopupWindow(Context context)
    {
        this(context, null, android.support.v7.appcompat.R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, android.support.v7.appcompat.R.attr.listPopupWindowStyle);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public ListPopupWindow(Context context, AttributeSet attributeset, int i, int j)
    {
        mDropDownHeight = -2;
        mDropDownWidth = -2;
        mDropDownWindowLayoutType = 1002;
        mIsAnimatedFromAnchor = true;
        mDropDownGravity = 0;
        mDropDownAlwaysVisible = false;
        mForceIgnoreOutsideTouch = false;
        mListItemExpandMaximum = 0x7fffffff;
        mPromptPosition = 0;
        mResizePopupRunnable = new ResizePopupRunnable();
        mTouchInterceptor = new PopupTouchInterceptor();
        mScrollListener = new PopupScrollListener();
        mHideSelector = new ListSelectorHider();
        mTempRect = new Rect();
        mContext = context;
        mHandler = new Handler(context.getMainLooper());
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ListPopupWindow, i, j);
        mDropDownHorizontalOffset = typedarray.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.ListPopupWindow_android_dropDownHorizontalOffset, 0);
        mDropDownVerticalOffset = typedarray.getDimensionPixelOffset(android.support.v7.appcompat.R.styleable.ListPopupWindow_android_dropDownVerticalOffset, 0);
        if(mDropDownVerticalOffset != 0)
            mDropDownVerticalOffsetSet = true;
        typedarray.recycle();
        if(android.os.Build.VERSION.SDK_INT >= 11)
            mPopup = new AppCompatPopupWindow(context, attributeset, i, j);
        else
            mPopup = new AppCompatPopupWindow(context, attributeset, i);
        mPopup.setInputMethodMode(1);
    }

    private int buildDropDown()
    {
        int i;
        int j;
        int k;
        int l;
        boolean flag;
        Object obj;
        Object obj1;
        View view;
        Object obj2;
        j = 0;
        i = 0;
        if(mDropDownList != null)
            break MISSING_BLOCK_LABEL_485;
        obj2 = mContext;
        mShowDropDownRunnable = new Runnable() {

            public void run()
            {
                View view1 = getAnchorView();
                if(view1 != null && view1.getWindowToken() != null)
                    show();
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super();
            }
        }
;
        if(!mModal)
            flag = true;
        else
            flag = false;
        mDropDownList = createDropDownListView(((Context) (obj2)), flag);
        if(mDropDownListHighlight != null)
            mDropDownList.setSelector(mDropDownListHighlight);
        mDropDownList.setAdapter(mAdapter);
        mDropDownList.setOnItemClickListener(mItemClickListener);
        mDropDownList.setFocusable(true);
        mDropDownList.setFocusableInTouchMode(true);
        mDropDownList.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView adapterview, View view1, int i1, long l1)
            {
                if(i1 != -1)
                {
                    adapterview = mDropDownList;
                    if(adapterview != null)
                        adapterview.setListSelectionHidden(false);
                }
            }

            public void onNothingSelected(AdapterView adapterview)
            {
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super();
            }
        }
);
        mDropDownList.setOnScrollListener(mScrollListener);
        if(mItemSelectedListener != null)
            mDropDownList.setOnItemSelectedListener(mItemSelectedListener);
        obj1 = mDropDownList;
        view = mPromptView;
        obj = obj1;
        if(view == null) goto _L2; else goto _L1
_L1:
        obj = new LinearLayout(((Context) (obj2)));
        ((LinearLayout) (obj)).setOrientation(1);
        obj2 = new android.widget.LinearLayout.LayoutParams(-1, 0, 1.0F);
        mPromptPosition;
        JVM INSTR tableswitch 0 1: default 228
    //                   0 459
    //                   1 440;
           goto _L3 _L4 _L5
_L3:
        Log.e("ListPopupWindow", (new StringBuilder()).append("Invalid hint position ").append(mPromptPosition).toString());
_L6:
        if(mDropDownWidth >= 0)
        {
            i = 0x80000000;
            j = mDropDownWidth;
        } else
        {
            i = 0;
            j = 0;
        }
        view.measure(android.view.View.MeasureSpec.makeMeasureSpec(j, i), 0);
        obj1 = (android.widget.LinearLayout.LayoutParams)view.getLayoutParams();
        i = view.getMeasuredHeight() + ((android.widget.LinearLayout.LayoutParams) (obj1)).topMargin + ((android.widget.LinearLayout.LayoutParams) (obj1)).bottomMargin;
_L2:
        mPopup.setContentView(((View) (obj)));
_L7:
        obj = mPopup.getBackground();
        if(obj != null)
        {
            ((Drawable) (obj)).getPadding(mTempRect);
            j = mTempRect.top + mTempRect.bottom;
            k = j;
            if(!mDropDownVerticalOffsetSet)
            {
                mDropDownVerticalOffset = -mTempRect.top;
                k = j;
            }
        } else
        {
            mTempRect.setEmpty();
            k = 0;
        }
        if(mPopup.getInputMethodMode() == 2)
            flag = true;
        else
            flag = false;
        l = getMaxAvailableHeight(getAnchorView(), mDropDownVerticalOffset, flag);
        if(mDropDownAlwaysVisible || mDropDownHeight == -1)
            return l + k;
        break MISSING_BLOCK_LABEL_559;
_L5:
        ((LinearLayout) (obj)).addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj2)));
        ((LinearLayout) (obj)).addView(view);
          goto _L6
_L4:
        ((LinearLayout) (obj)).addView(view);
        ((LinearLayout) (obj)).addView(((View) (obj1)), ((android.view.ViewGroup.LayoutParams) (obj2)));
          goto _L6
        obj = (ViewGroup)mPopup.getContentView();
        obj = mPromptView;
        i = j;
        if(obj != null)
        {
            obj1 = (android.widget.LinearLayout.LayoutParams)((View) (obj)).getLayoutParams();
            i = ((View) (obj)).getMeasuredHeight() + ((android.widget.LinearLayout.LayoutParams) (obj1)).topMargin + ((android.widget.LinearLayout.LayoutParams) (obj1)).bottomMargin;
        }
          goto _L7
        mDropDownWidth;
        JVM INSTR tableswitch -2 -1: default 584
    //                   -2 644
    //                   -1 683;
           goto _L8 _L9 _L10
_L8:
        j = android.view.View.MeasureSpec.makeMeasureSpec(mDropDownWidth, 0x40000000);
_L12:
        l = mDropDownList.measureHeightOfChildrenCompat(j, 0, -1, l - i, -1);
        j = i;
        if(l > 0)
            j = i + (k + (mDropDownList.getPaddingTop() + mDropDownList.getPaddingBottom()));
        return l + j;
_L9:
        j = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x80000000);
        continue; /* Loop/switch isn't completed */
_L10:
        j = android.view.View.MeasureSpec.makeMeasureSpec(mContext.getResources().getDisplayMetrics().widthPixels - (mTempRect.left + mTempRect.right), 0x40000000);
        if(true) goto _L12; else goto _L11
_L11:
    }

    private int getMaxAvailableHeight(View view, int i, boolean flag)
    {
        if(sGetMaxAvailableHeightMethod == null)
            break MISSING_BLOCK_LABEL_60;
        int j = ((Integer)sGetMaxAvailableHeightMethod.invoke(mPopup, new Object[] {
            view, Integer.valueOf(i), Boolean.valueOf(flag)
        })).intValue();
        return j;
        Exception exception;
        exception;
        Log.i("ListPopupWindow", "Could not call getMaxAvailableHeightMethod(View, int, boolean) on PopupWindow. Using the public version.");
        return mPopup.getMaxAvailableHeight(view, i);
    }

    private static boolean isConfirmKey(int i)
    {
        return i == 66 || i == 23;
    }

    private void removePromptView()
    {
        if(mPromptView != null)
        {
            android.view.ViewParent viewparent = mPromptView.getParent();
            if(viewparent instanceof ViewGroup)
                ((ViewGroup)viewparent).removeView(mPromptView);
        }
    }

    private void setPopupClipToScreenEnabled(boolean flag)
    {
        if(sClipToWindowEnabledMethod == null)
            break MISSING_BLOCK_LABEL_28;
        sClipToWindowEnabledMethod.invoke(mPopup, new Object[] {
            Boolean.valueOf(flag)
        });
        return;
        Exception exception;
        exception;
        Log.i("ListPopupWindow", "Could not call setClipToScreenEnabled() on PopupWindow. Oh well.");
        return;
    }

    public void clearListSelection()
    {
        DropDownListView dropdownlistview = mDropDownList;
        if(dropdownlistview != null)
        {
            dropdownlistview.setListSelectionHidden(true);
            dropdownlistview.requestLayout();
        }
    }

    public android.view.View.OnTouchListener createDragToOpenListener(View view)
    {
        return new ForwardingListener(view) {

            public volatile ShowableListMenu getPopup()
            {
                return getPopup();
            }

            public ListPopupWindow getPopup()
            {
                return ListPopupWindow.this;
            }

            final ListPopupWindow this$0;

            
            {
                this$0 = ListPopupWindow.this;
                super(view);
            }
        }
;
    }

    DropDownListView createDropDownListView(Context context, boolean flag)
    {
        return new DropDownListView(context, flag);
    }

    public void dismiss()
    {
        mPopup.dismiss();
        removePromptView();
        mPopup.setContentView(null);
        mDropDownList = null;
        mHandler.removeCallbacks(mResizePopupRunnable);
    }

    public View getAnchorView()
    {
        return mDropDownAnchorView;
    }

    public int getAnimationStyle()
    {
        return mPopup.getAnimationStyle();
    }

    public Drawable getBackground()
    {
        return mPopup.getBackground();
    }

    public int getHeight()
    {
        return mDropDownHeight;
    }

    public int getHorizontalOffset()
    {
        return mDropDownHorizontalOffset;
    }

    public int getInputMethodMode()
    {
        return mPopup.getInputMethodMode();
    }

    public ListView getListView()
    {
        return mDropDownList;
    }

    public int getPromptPosition()
    {
        return mPromptPosition;
    }

    public Object getSelectedItem()
    {
        if(!isShowing())
            return null;
        else
            return mDropDownList.getSelectedItem();
    }

    public long getSelectedItemId()
    {
        if(!isShowing())
            return 0x8000000000000000L;
        else
            return mDropDownList.getSelectedItemId();
    }

    public int getSelectedItemPosition()
    {
        if(!isShowing())
            return -1;
        else
            return mDropDownList.getSelectedItemPosition();
    }

    public View getSelectedView()
    {
        if(!isShowing())
            return null;
        else
            return mDropDownList.getSelectedView();
    }

    public int getSoftInputMode()
    {
        return mPopup.getSoftInputMode();
    }

    public int getVerticalOffset()
    {
        if(!mDropDownVerticalOffsetSet)
            return 0;
        else
            return mDropDownVerticalOffset;
    }

    public int getWidth()
    {
        return mDropDownWidth;
    }

    public boolean isDropDownAlwaysVisible()
    {
        return mDropDownAlwaysVisible;
    }

    public boolean isInputMethodNotNeeded()
    {
        return mPopup.getInputMethodMode() == 2;
    }

    public boolean isModal()
    {
        return mModal;
    }

    public boolean isShowing()
    {
        return mPopup.isShowing();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        if(!isShowing() || i == 62 || mDropDownList.getSelectedItemPosition() < 0 && isConfirmKey(i)) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        boolean flag;
        int l;
        l = mDropDownList.getSelectedItemPosition();
        ListAdapter listadapter;
        if(!mPopup.isAboveAnchor())
            flag = true;
        else
            flag = false;
        listadapter = mAdapter;
        j = 0x7fffffff;
        k = 0x80000000;
        if(listadapter != null)
        {
            boolean flag1 = listadapter.areAllItemsEnabled();
            if(flag1)
                j = 0;
            else
                j = mDropDownList.lookForSelectablePosition(0, true);
            if(flag1)
                k = listadapter.getCount() - 1;
            else
                k = mDropDownList.lookForSelectablePosition(listadapter.getCount() - 1, false);
        }
        if((!flag || i != 19 || l > j) && (flag || i != 20 || l < k)) goto _L4; else goto _L3
_L3:
        clearListSelection();
        mPopup.setInputMethodMode(1);
        show();
_L7:
        return true;
_L4:
        mDropDownList.setListSelectionHidden(false);
        if(!mDropDownList.onKeyDown(i, keyevent))
            break; /* Loop/switch isn't completed */
        mPopup.setInputMethodMode(2);
        mDropDownList.requestFocusFromTouch();
        show();
        i;
        JVM INSTR lookupswitch 4: default 280
    //                   19: 154
    //                   20: 154
    //                   23: 154
    //                   66: 154;
           goto _L2 _L5 _L5 _L5 _L5
_L5:
        if(true) goto _L7; else goto _L6
_L2:
        return false;
_L6:
        if(!flag || i != 20)
            continue; /* Loop/switch isn't completed */
        if(l != k) goto _L2; else goto _L8
_L8:
        return true;
        if(flag || i != 19 || l != j) goto _L2; else goto _L9
_L9:
        return true;
    }

    public boolean onKeyPreIme(int i, KeyEvent keyevent)
    {
        if(i == 4 && isShowing())
        {
            Object obj = mDropDownAnchorView;
            if(keyevent.getAction() == 0 && keyevent.getRepeatCount() == 0)
            {
                obj = ((View) (obj)).getKeyDispatcherState();
                if(obj != null)
                    ((android.view.KeyEvent.DispatcherState) (obj)).startTracking(keyevent, this);
                return true;
            }
            if(keyevent.getAction() == 1)
            {
                obj = ((View) (obj)).getKeyDispatcherState();
                if(obj != null)
                    ((android.view.KeyEvent.DispatcherState) (obj)).handleUpEvent(keyevent);
                if(keyevent.isTracking() && !keyevent.isCanceled())
                {
                    dismiss();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if(isShowing() && mDropDownList.getSelectedItemPosition() >= 0)
        {
            boolean flag = mDropDownList.onKeyUp(i, keyevent);
            if(flag && isConfirmKey(i))
                dismiss();
            return flag;
        } else
        {
            return false;
        }
    }

    public boolean performItemClick(int i)
    {
        if(isShowing())
        {
            if(mItemClickListener != null)
            {
                DropDownListView dropdownlistview = mDropDownList;
                View view = dropdownlistview.getChildAt(i - dropdownlistview.getFirstVisiblePosition());
                ListAdapter listadapter = dropdownlistview.getAdapter();
                mItemClickListener.onItemClick(dropdownlistview, view, i, listadapter.getItemId(i));
            }
            return true;
        } else
        {
            return false;
        }
    }

    public void postShow()
    {
        mHandler.post(mShowDropDownRunnable);
    }

    public void setAdapter(ListAdapter listadapter)
    {
        if(mObserver != null) goto _L2; else goto _L1
_L1:
        mObserver = new PopupDataSetObserver();
_L4:
        mAdapter = listadapter;
        if(mAdapter != null)
            listadapter.registerDataSetObserver(mObserver);
        if(mDropDownList != null)
            mDropDownList.setAdapter(mAdapter);
        return;
_L2:
        if(mAdapter != null)
            mAdapter.unregisterDataSetObserver(mObserver);
        if(true) goto _L4; else goto _L3
_L3:
    }

    public void setAnchorView(View view)
    {
        mDropDownAnchorView = view;
    }

    public void setAnimationStyle(int i)
    {
        mPopup.setAnimationStyle(i);
    }

    public void setBackgroundDrawable(Drawable drawable)
    {
        mPopup.setBackgroundDrawable(drawable);
    }

    public void setContentWidth(int i)
    {
        Drawable drawable = mPopup.getBackground();
        if(drawable != null)
        {
            drawable.getPadding(mTempRect);
            mDropDownWidth = mTempRect.left + mTempRect.right + i;
            return;
        } else
        {
            setWidth(i);
            return;
        }
    }

    public void setDropDownAlwaysVisible(boolean flag)
    {
        mDropDownAlwaysVisible = flag;
    }

    public void setDropDownGravity(int i)
    {
        mDropDownGravity = i;
    }

    public void setEpicenterBounds(Rect rect)
    {
        mEpicenterBounds = rect;
    }

    public void setForceIgnoreOutsideTouch(boolean flag)
    {
        mForceIgnoreOutsideTouch = flag;
    }

    public void setHeight(int i)
    {
        mDropDownHeight = i;
    }

    public void setHorizontalOffset(int i)
    {
        mDropDownHorizontalOffset = i;
    }

    public void setInputMethodMode(int i)
    {
        mPopup.setInputMethodMode(i);
    }

    void setListItemExpandMax(int i)
    {
        mListItemExpandMaximum = i;
    }

    public void setListSelector(Drawable drawable)
    {
        mDropDownListHighlight = drawable;
    }

    public void setModal(boolean flag)
    {
        mModal = flag;
        mPopup.setFocusable(flag);
    }

    public void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener)
    {
        mPopup.setOnDismissListener(ondismisslistener);
    }

    public void setOnItemClickListener(android.widget.AdapterView.OnItemClickListener onitemclicklistener)
    {
        mItemClickListener = onitemclicklistener;
    }

    public void setOnItemSelectedListener(android.widget.AdapterView.OnItemSelectedListener onitemselectedlistener)
    {
        mItemSelectedListener = onitemselectedlistener;
    }

    public void setPromptPosition(int i)
    {
        mPromptPosition = i;
    }

    public void setPromptView(View view)
    {
        boolean flag = isShowing();
        if(flag)
            removePromptView();
        mPromptView = view;
        if(flag)
            show();
    }

    public void setSelection(int i)
    {
        DropDownListView dropdownlistview = mDropDownList;
        if(isShowing() && dropdownlistview != null)
        {
            dropdownlistview.setListSelectionHidden(false);
            dropdownlistview.setSelection(i);
            if(android.os.Build.VERSION.SDK_INT >= 11 && dropdownlistview.getChoiceMode() != 0)
                dropdownlistview.setItemChecked(i, true);
        }
    }

    public void setSoftInputMode(int i)
    {
        mPopup.setSoftInputMode(i);
    }

    public void setVerticalOffset(int i)
    {
        mDropDownVerticalOffset = i;
        mDropDownVerticalOffsetSet = true;
    }

    public void setWidth(int i)
    {
        mDropDownWidth = i;
    }

    public void setWindowLayoutType(int i)
    {
        mDropDownWindowLayoutType = i;
    }

    public void show()
    {
        boolean flag = true;
        boolean flag1 = false;
        byte byte1 = -1;
        int k = buildDropDown();
        boolean flag2 = isInputMethodNotNeeded();
        PopupWindowCompat.setWindowLayoutType(mPopup, mDropDownWindowLayoutType);
        if(mPopup.isShowing())
        {
            int i;
            if(mDropDownWidth == -1)
                i = -1;
            else
            if(mDropDownWidth == -2)
                i = getAnchorView().getWidth();
            else
                i = mDropDownWidth;
            if(mDropDownHeight == -1)
            {
                if(!flag2)
                    k = -1;
                if(flag2)
                {
                    PopupWindow popupwindow = mPopup;
                    int l;
                    int i1;
                    View view;
                    if(mDropDownWidth == -1)
                        l = -1;
                    else
                        l = 0;
                    popupwindow.setWidth(l);
                    mPopup.setHeight(0);
                } else
                {
                    PopupWindow popupwindow1 = mPopup;
                    byte byte0;
                    if(mDropDownWidth == -1)
                        byte0 = -1;
                    else
                        byte0 = 0;
                    popupwindow1.setWidth(byte0);
                    mPopup.setHeight(-1);
                }
            } else
            if(mDropDownHeight != -2)
                k = mDropDownHeight;
            popupwindow = mPopup;
            flag = flag1;
            if(!mForceIgnoreOutsideTouch)
            {
                flag = flag1;
                if(!mDropDownAlwaysVisible)
                    flag = true;
            }
            popupwindow.setOutsideTouchable(flag);
            popupwindow = mPopup;
            view = getAnchorView();
            l = mDropDownHorizontalOffset;
            i1 = mDropDownVerticalOffset;
            if(i < 0)
                i = -1;
            if(k < 0)
                k = byte1;
            popupwindow.update(view, l, i1, i, k);
        } else
        {
            int j;
            PopupWindow popupwindow2;
            if(mDropDownWidth == -1)
                j = -1;
            else
            if(mDropDownWidth == -2)
                j = getAnchorView().getWidth();
            else
                j = mDropDownWidth;
            if(mDropDownHeight == -1)
                k = -1;
            else
            if(mDropDownHeight != -2)
                k = mDropDownHeight;
            mPopup.setWidth(j);
            mPopup.setHeight(k);
            setPopupClipToScreenEnabled(true);
            popupwindow2 = mPopup;
            if(mForceIgnoreOutsideTouch || mDropDownAlwaysVisible)
                flag = false;
            popupwindow2.setOutsideTouchable(flag);
            mPopup.setTouchInterceptor(mTouchInterceptor);
            if(sSetEpicenterBoundsMethod != null)
                try
                {
                    sSetEpicenterBoundsMethod.invoke(mPopup, new Object[] {
                        mEpicenterBounds
                    });
                }
                catch(Exception exception)
                {
                    Log.e("ListPopupWindow", "Could not invoke setEpicenterBounds on PopupWindow", exception);
                }
            PopupWindowCompat.showAsDropDown(mPopup, getAnchorView(), mDropDownHorizontalOffset, mDropDownVerticalOffset, mDropDownGravity);
            mDropDownList.setSelection(-1);
            if(!mModal || mDropDownList.isInTouchMode())
                clearListSelection();
            if(!mModal)
            {
                mHandler.post(mHideSelector);
                return;
            }
        }
    }

    private static final boolean DEBUG = false;
    static final int EXPAND_LIST_TIMEOUT = 250;
    public static final int INPUT_METHOD_FROM_FOCUSABLE = 0;
    public static final int INPUT_METHOD_NEEDED = 1;
    public static final int INPUT_METHOD_NOT_NEEDED = 2;
    public static final int MATCH_PARENT = -1;
    public static final int POSITION_PROMPT_ABOVE = 0;
    public static final int POSITION_PROMPT_BELOW = 1;
    private static final String TAG = "ListPopupWindow";
    public static final int WRAP_CONTENT = -2;
    private static Method sClipToWindowEnabledMethod;
    private static Method sGetMaxAvailableHeightMethod;
    private static Method sSetEpicenterBoundsMethod;
    private ListAdapter mAdapter;
    private Context mContext;
    private boolean mDropDownAlwaysVisible;
    private View mDropDownAnchorView;
    private int mDropDownGravity;
    private int mDropDownHeight;
    private int mDropDownHorizontalOffset;
    DropDownListView mDropDownList;
    private Drawable mDropDownListHighlight;
    private int mDropDownVerticalOffset;
    private boolean mDropDownVerticalOffsetSet;
    private int mDropDownWidth;
    private int mDropDownWindowLayoutType;
    private Rect mEpicenterBounds;
    private boolean mForceIgnoreOutsideTouch;
    final Handler mHandler;
    private final ListSelectorHider mHideSelector;
    private boolean mIsAnimatedFromAnchor;
    private android.widget.AdapterView.OnItemClickListener mItemClickListener;
    private android.widget.AdapterView.OnItemSelectedListener mItemSelectedListener;
    int mListItemExpandMaximum;
    private boolean mModal;
    private DataSetObserver mObserver;
    PopupWindow mPopup;
    private int mPromptPosition;
    private View mPromptView;
    final ResizePopupRunnable mResizePopupRunnable;
    private final PopupScrollListener mScrollListener;
    private Runnable mShowDropDownRunnable;
    private final Rect mTempRect;
    private final PopupTouchInterceptor mTouchInterceptor;

    static 
    {
        try
        {
            sClipToWindowEnabledMethod = android/widget/PopupWindow.getDeclaredMethod("setClipToScreenEnabled", new Class[] {
                Boolean.TYPE
            });
        }
        catch(NoSuchMethodException nosuchmethodexception)
        {
            Log.i("ListPopupWindow", "Could not find method setClipToScreenEnabled() on PopupWindow. Oh well.");
        }
        try
        {
            sGetMaxAvailableHeightMethod = android/widget/PopupWindow.getDeclaredMethod("getMaxAvailableHeight", new Class[] {
                android/view/View, Integer.TYPE, Boolean.TYPE
            });
        }
        catch(NoSuchMethodException nosuchmethodexception1)
        {
            Log.i("ListPopupWindow", "Could not find method getMaxAvailableHeight(View, int, boolean) on PopupWindow. Oh well.");
        }
        try
        {
            sSetEpicenterBoundsMethod = android/widget/PopupWindow.getDeclaredMethod("setEpicenterBounds", new Class[] {
                android/graphics/Rect
            });
        }
        catch(NoSuchMethodException nosuchmethodexception2)
        {
            Log.i("ListPopupWindow", "Could not find method setEpicenterBounds(Rect) on PopupWindow. Oh well.");
        }
    }
}
