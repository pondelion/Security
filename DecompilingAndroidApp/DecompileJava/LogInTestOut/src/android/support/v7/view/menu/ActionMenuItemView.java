// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.content.res.*;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v4.content.res.ConfigurationHelper;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.ForwardingListener;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

// Referenced classes of package android.support.v7.view.menu:
//            MenuItemImpl, ShowableListMenu

public class ActionMenuItemView extends AppCompatTextView
    implements MenuView.ItemView, android.view.View.OnClickListener, android.view.View.OnLongClickListener, android.support.v7.widget.ActionMenuView.ActionMenuChildView
{
    private class ActionMenuItemForwardingListener extends ForwardingListener
    {

        public ShowableListMenu getPopup()
        {
            if(mPopupCallback != null)
                return mPopupCallback.getPopup();
            else
                return null;
        }

        protected boolean onForwardingStarted()
        {
            boolean flag1 = false;
            boolean flag = flag1;
            if(mItemInvoker != null)
            {
                flag = flag1;
                if(mItemInvoker.invokeItem(mItemData))
                {
                    ShowableListMenu showablelistmenu = getPopup();
                    flag = flag1;
                    if(showablelistmenu != null)
                    {
                        flag = flag1;
                        if(showablelistmenu.isShowing())
                            flag = true;
                    }
                }
            }
            return flag;
        }

        final ActionMenuItemView this$0;

        public ActionMenuItemForwardingListener()
        {
            this$0 = ActionMenuItemView.this;
            super(ActionMenuItemView.this);
        }
    }

    public static abstract class PopupCallback
    {

        public abstract ShowableListMenu getPopup();

        public PopupCallback()
        {
        }
    }


    public ActionMenuItemView(Context context)
    {
        this(context, null);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public ActionMenuItemView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        Resources resources = context.getResources();
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        context = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.ActionMenuItemView, i, 0);
        mMinWidth = context.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.ActionMenuItemView_android_minWidth, 0);
        context.recycle();
        mMaxIconSize = (int)(32F * resources.getDisplayMetrics().density + 0.5F);
        setOnClickListener(this);
        setOnLongClickListener(this);
        mSavedPaddingLeft = -1;
        setSaveEnabled(false);
    }

    private boolean shouldAllowTextWithIcon()
    {
        Configuration configuration = getContext().getResources().getConfiguration();
        int i = ConfigurationHelper.getScreenWidthDp(getResources());
        int j = ConfigurationHelper.getScreenHeightDp(getResources());
        return i >= 480 || i >= 640 && j >= 480 || configuration.orientation == 2;
    }

    private void updateTextButtonVisibility()
    {
        CharSequence charsequence;
label0:
        {
            boolean flag2 = false;
            boolean flag;
            boolean flag1;
            if(!TextUtils.isEmpty(mTitle))
                flag = true;
            else
                flag = false;
            if(mIcon != null)
            {
                flag1 = flag2;
                if(!mItemData.showsTextAsAction())
                    break label0;
                if(!mAllowTextWithIcon)
                {
                    flag1 = flag2;
                    if(!mExpandedFormat)
                        break label0;
                }
            }
            flag1 = true;
        }
        if(flag & flag1)
            charsequence = mTitle;
        else
            charsequence = null;
        setText(charsequence);
    }

    public MenuItemImpl getItemData()
    {
        return mItemData;
    }

    public boolean hasText()
    {
        return !TextUtils.isEmpty(getText());
    }

    public void initialize(MenuItemImpl menuitemimpl, int i)
    {
        mItemData = menuitemimpl;
        setIcon(menuitemimpl.getIcon());
        setTitle(menuitemimpl.getTitleForItemView(this));
        setId(menuitemimpl.getItemId());
        if(menuitemimpl.isVisible())
            i = 0;
        else
            i = 8;
        setVisibility(i);
        setEnabled(menuitemimpl.isEnabled());
        if(menuitemimpl.hasSubMenu() && mForwardingListener == null)
            mForwardingListener = new ActionMenuItemForwardingListener();
    }

    public boolean needsDividerAfter()
    {
        return hasText();
    }

    public boolean needsDividerBefore()
    {
        return hasText() && mItemData.getIcon() == null;
    }

    public void onClick(View view)
    {
        if(mItemInvoker != null)
            mItemInvoker.invokeItem(mItemData);
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mAllowTextWithIcon = shouldAllowTextWithIcon();
        updateTextButtonVisibility();
    }

    public boolean onLongClick(View view)
    {
        if(hasText())
            return false;
        int ai[] = new int[2];
        Rect rect = new Rect();
        getLocationOnScreen(ai);
        getWindowVisibleDisplayFrame(rect);
        Context context = getContext();
        int i = getWidth();
        int k = getHeight();
        int l = ai[1];
        int i1 = k / 2;
        int j = ai[0] + i / 2;
        i = j;
        if(ViewCompat.getLayoutDirection(view) == 0)
            i = context.getResources().getDisplayMetrics().widthPixels - j;
        view = Toast.makeText(context, mItemData.getTitle(), 0);
        if(l + i1 < rect.height())
            view.setGravity(0x800035, i, (ai[1] + k) - rect.top);
        else
            view.setGravity(81, 0, k);
        view.show();
        return true;
    }

    protected void onMeasure(int i, int j)
    {
        boolean flag = hasText();
        if(flag && mSavedPaddingLeft >= 0)
            super.setPadding(mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom());
        super.onMeasure(i, j);
        int k = android.view.View.MeasureSpec.getMode(i);
        i = android.view.View.MeasureSpec.getSize(i);
        int l = getMeasuredWidth();
        if(k == 0x80000000)
            i = Math.min(i, mMinWidth);
        else
            i = mMinWidth;
        if(k != 0x40000000 && mMinWidth > 0 && l < i)
            super.onMeasure(android.view.View.MeasureSpec.makeMeasureSpec(i, 0x40000000), j);
        if(!flag && mIcon != null)
            super.setPadding((getMeasuredWidth() - mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom());
    }

    public void onRestoreInstanceState(Parcelable parcelable)
    {
        super.onRestoreInstanceState(null);
    }

    public boolean onTouchEvent(MotionEvent motionevent)
    {
        if(mItemData.hasSubMenu() && mForwardingListener != null && mForwardingListener.onTouch(this, motionevent))
            return true;
        else
            return super.onTouchEvent(motionevent);
    }

    public boolean prefersCondensedTitle()
    {
        return true;
    }

    public void setCheckable(boolean flag)
    {
    }

    public void setChecked(boolean flag)
    {
    }

    public void setExpandedFormat(boolean flag)
    {
        if(mExpandedFormat != flag)
        {
            mExpandedFormat = flag;
            if(mItemData != null)
                mItemData.actionFormatChanged();
        }
    }

    public void setIcon(Drawable drawable)
    {
        mIcon = drawable;
        if(drawable != null)
        {
            int l = drawable.getIntrinsicWidth();
            int k = drawable.getIntrinsicHeight();
            int j = k;
            int i = l;
            if(l > mMaxIconSize)
            {
                float f = (float)mMaxIconSize / (float)l;
                i = mMaxIconSize;
                j = (int)((float)k * f);
            }
            l = j;
            k = i;
            if(j > mMaxIconSize)
            {
                float f1 = (float)mMaxIconSize / (float)j;
                l = mMaxIconSize;
                k = (int)((float)i * f1);
            }
            drawable.setBounds(0, 0, k, l);
        }
        setCompoundDrawables(drawable, null, null, null);
        updateTextButtonVisibility();
    }

    public void setItemInvoker(MenuBuilder.ItemInvoker iteminvoker)
    {
        mItemInvoker = iteminvoker;
    }

    public void setPadding(int i, int j, int k, int l)
    {
        mSavedPaddingLeft = i;
        super.setPadding(i, j, k, l);
    }

    public void setPopupCallback(PopupCallback popupcallback)
    {
        mPopupCallback = popupcallback;
    }

    public void setShortcut(boolean flag, char c)
    {
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        setContentDescription(mTitle);
        updateTextButtonVisibility();
    }

    public boolean showsIcon()
    {
        return true;
    }

    private static final int MAX_ICON_SIZE = 32;
    private static final String TAG = "ActionMenuItemView";
    private boolean mAllowTextWithIcon;
    private boolean mExpandedFormat;
    private ForwardingListener mForwardingListener;
    private Drawable mIcon;
    MenuItemImpl mItemData;
    MenuBuilder.ItemInvoker mItemInvoker;
    private int mMaxIconSize;
    private int mMinWidth;
    PopupCallback mPopupCallback;
    private int mSavedPaddingLeft;
    private CharSequence mTitle;
}
