// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.app:
//            AppCompatDialog

class AlertController
{
    public static class AlertParams
    {

        private void createListView(AlertController alertcontroller)
        {
            final ListView listView = (ListView)mInflater.inflate(alertcontroller.mListLayout, null);
            Object obj;
            if(mIsMultiChoice)
            {
                if(mCursor == null)
                    obj = mContext. new ArrayAdapter(alertcontroller.mMultiChoiceItemLayout, 0x1020014, mItems, listView) {

                        public View getView(int i, View view, ViewGroup viewgroup)
                        {
                            view = super.getView(i, view, viewgroup);
                            if(mCheckedItems != null && mCheckedItems[i])
                                listView.setItemChecked(i, true);
                            return view;
                        }

                        final AlertParams this$0;
                        final ListView val$listView;

            
            {
                this$0 = final_alertparams;
                listView = listview;
                super(Context.this, i, j, acharsequence);
            }
                    }
;
                else
                    obj = mCursor. new CursorAdapter(false, listView, alertcontroller) {

                        public void bindView(View view, Context context, Cursor cursor)
                        {
                            boolean flag = true;
                            ((CheckedTextView)view.findViewById(0x1020014)).setText(cursor.getString(mLabelIndex));
                            view = listView;
                            int i = cursor.getPosition();
                            if(cursor.getInt(mIsCheckedIndex) != 1)
                                flag = false;
                            view.setItemChecked(i, flag);
                        }

                        public View newView(Context context, Cursor cursor, ViewGroup viewgroup)
                        {
                            return mInflater.inflate(dialog.mMultiChoiceItemLayout, viewgroup, false);
                        }

                        private final int mIsCheckedIndex;
                        private final int mLabelIndex;
                        final AlertParams this$0;
                        final AlertController val$dialog;
                        final ListView val$listView;

            
            {
                this$0 = final_alertparams;
                listView = listview;
                dialog = alertcontroller;
                super(final_context, Cursor.this, flag);
                final_alertparams = getCursor();
                mLabelIndex = final_alertparams.getColumnIndexOrThrow(mLabelColumn);
                mIsCheckedIndex = final_alertparams.getColumnIndexOrThrow(mIsCheckedColumn);
            }
                    }
;
            } else
            {
                int i;
                if(mIsSingleChoice)
                    i = alertcontroller.mSingleChoiceItemLayout;
                else
                    i = alertcontroller.mListItemLayout;
                if(mCursor != null)
                    obj = new SimpleCursorAdapter(mContext, i, mCursor, new String[] {
                        mLabelColumn
                    }, new int[] {
                        0x1020014
                    });
                else
                if(mAdapter != null)
                    obj = mAdapter;
                else
                    obj = new CheckedItemAdapter(mContext, i, 0x1020014, mItems);
            }
            if(mOnPrepareListViewListener != null)
                mOnPrepareListViewListener.onPrepareListView(listView);
            alertcontroller.mAdapter = ((ListAdapter) (obj));
            alertcontroller.mCheckedItem = mCheckedItem;
            if(mOnClickListener != null)
                listView.setOnItemClickListener(alertcontroller. new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        mOnClickListener.onClick(dialog.mDialog, i);
                        if(!mIsSingleChoice)
                            dialog.mDialog.dismiss();
                    }

                    final AlertParams this$0;
                    final AlertController val$dialog;

            
            {
                this$0 = final_alertparams;
                dialog = AlertController.this;
                super();
            }
                }
);
            else
            if(mOnCheckboxClickListener != null)
                listView.setOnItemClickListener(alertcontroller. new android.widget.AdapterView.OnItemClickListener() {

                    public void onItemClick(AdapterView adapterview, View view, int i, long l)
                    {
                        if(mCheckedItems != null)
                            mCheckedItems[i] = listView.isItemChecked(i);
                        mOnCheckboxClickListener.onClick(dialog.mDialog, i, listView.isItemChecked(i));
                    }

                    final AlertParams this$0;
                    final AlertController val$dialog;
                    final ListView val$listView;

            
            {
                this$0 = final_alertparams;
                listView = listview;
                dialog = AlertController.this;
                super();
            }
                }
);
            if(mOnItemSelectedListener != null)
                listView.setOnItemSelectedListener(mOnItemSelectedListener);
            if(mIsSingleChoice)
                listView.setChoiceMode(1);
            else
            if(mIsMultiChoice)
                listView.setChoiceMode(2);
            alertcontroller.mListView = listView;
        }

        public void apply(AlertController alertcontroller)
        {
            if(mCustomTitleView == null) goto _L2; else goto _L1
_L1:
            alertcontroller.setCustomTitle(mCustomTitleView);
_L10:
            if(mMessage != null)
                alertcontroller.setMessage(mMessage);
            if(mPositiveButtonText != null)
                alertcontroller.setButton(-1, mPositiveButtonText, mPositiveButtonListener, null);
            if(mNegativeButtonText != null)
                alertcontroller.setButton(-2, mNegativeButtonText, mNegativeButtonListener, null);
            if(mNeutralButtonText != null)
                alertcontroller.setButton(-3, mNeutralButtonText, mNeutralButtonListener, null);
            if(mItems != null || mCursor != null || mAdapter != null)
                createListView(alertcontroller);
            if(mView == null) goto _L4; else goto _L3
_L3:
            if(!mViewSpacingSpecified) goto _L6; else goto _L5
_L5:
            alertcontroller.setView(mView, mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
_L8:
            return;
_L2:
            if(mTitle != null)
                alertcontroller.setTitle(mTitle);
            if(mIcon != null)
                alertcontroller.setIcon(mIcon);
            if(mIconId != 0)
                alertcontroller.setIcon(mIconId);
            if(mIconAttrId != 0)
                alertcontroller.setIcon(alertcontroller.getIconAttributeResId(mIconAttrId));
            continue; /* Loop/switch isn't completed */
_L6:
            alertcontroller.setView(mView);
            return;
_L4:
            if(mViewLayoutResId == 0) goto _L8; else goto _L7
_L7:
            alertcontroller.setView(mViewLayoutResId);
            return;
            if(true) goto _L10; else goto _L9
_L9:
        }

        public ListAdapter mAdapter;
        public boolean mCancelable;
        public int mCheckedItem;
        public boolean mCheckedItems[];
        public final Context mContext;
        public Cursor mCursor;
        public View mCustomTitleView;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconAttrId;
        public int mIconId;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence mItems[];
        public String mLabelColumn;
        public CharSequence mMessage;
        public android.content.DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public android.content.DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public android.content.DialogInterface.OnCancelListener mOnCancelListener;
        public android.content.DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public android.content.DialogInterface.OnClickListener mOnClickListener;
        public android.content.DialogInterface.OnDismissListener mOnDismissListener;
        public android.widget.AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public android.content.DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public android.content.DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure;
        public CharSequence mTitle;
        public View mView;
        public int mViewLayoutResId;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified;
        public int mViewSpacingTop;

        public AlertParams(Context context)
        {
            mIconId = 0;
            mIconAttrId = 0;
            mViewSpacingSpecified = false;
            mCheckedItem = -1;
            mRecycleOnMeasure = true;
            mContext = context;
            mCancelable = true;
            mInflater = (LayoutInflater)context.getSystemService("layout_inflater");
        }
    }

    public static interface AlertParams.OnPrepareListViewListener
    {

        public abstract void onPrepareListView(ListView listview);
    }

    private static final class ButtonHandler extends Handler
    {

        public void handleMessage(Message message)
        {
            switch(message.what)
            {
            case 0: // '\0'
            default:
                return;

            case -3: 
            case -2: 
            case -1: 
                ((android.content.DialogInterface.OnClickListener)message.obj).onClick((DialogInterface)mDialog.get(), message.what);
                return;

            case 1: // '\001'
                ((DialogInterface)message.obj).dismiss();
                break;
            }
        }

        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference mDialog;

        public ButtonHandler(DialogInterface dialoginterface)
        {
            mDialog = new WeakReference(dialoginterface);
        }
    }

    private static class CheckedItemAdapter extends ArrayAdapter
    {

        public long getItemId(int i)
        {
            return (long)i;
        }

        public boolean hasStableIds()
        {
            return true;
        }

        public CheckedItemAdapter(Context context, int i, int j, CharSequence acharsequence[])
        {
            super(context, i, j, acharsequence);
        }
    }


    public AlertController(Context context, AppCompatDialog appcompatdialog, Window window)
    {
        mViewSpacingSpecified = false;
        mIconId = 0;
        mCheckedItem = -1;
        mButtonPanelLayoutHint = 0;
        mContext = context;
        mDialog = appcompatdialog;
        mWindow = window;
        mHandler = new ButtonHandler(appcompatdialog);
        context = context.obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.AlertDialog, android.support.v7.appcompat.R.attr.alertDialogStyle, 0);
        mAlertDialogLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_android_layout, 0);
        mButtonPanelSideLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_buttonPanelSideLayout, 0);
        mListLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_listLayout, 0);
        mMultiChoiceItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_multiChoiceItemLayout, 0);
        mSingleChoiceItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_singleChoiceItemLayout, 0);
        mListItemLayout = context.getResourceId(android.support.v7.appcompat.R.styleable.AlertDialog_listItemLayout, 0);
        context.recycle();
        appcompatdialog.supportRequestWindowFeature(1);
    }

    static boolean canTextInput(View view)
    {
        if(view.onCheckIsTextEditor())
            return true;
        if(!(view instanceof ViewGroup))
            return false;
        view = (ViewGroup)view;
        for(int i = view.getChildCount(); i > 0;)
        {
            int j = i - 1;
            i = j;
            if(canTextInput(view.getChildAt(j)))
                return true;
        }

        return false;
    }

    static void manageScrollIndicators(View view, View view1, View view2)
    {
        boolean flag = false;
        int i;
        if(view1 != null)
        {
            if(ViewCompat.canScrollVertically(view, -1))
                i = 0;
            else
                i = 4;
            view1.setVisibility(i);
        }
        if(view2 != null)
        {
            if(ViewCompat.canScrollVertically(view, 1))
                i = ((flag) ? 1 : 0);
            else
                i = 4;
            view2.setVisibility(i);
        }
    }

    private ViewGroup resolvePanel(View view, View view1)
    {
        if(view == null)
        {
            view = view1;
            if(view1 instanceof ViewStub)
                view = ((ViewStub)view1).inflate();
            return (ViewGroup)view;
        }
        if(view1 != null)
        {
            android.view.ViewParent viewparent = view1.getParent();
            if(viewparent instanceof ViewGroup)
                ((ViewGroup)viewparent).removeView(view1);
        }
        view1 = view;
        if(view instanceof ViewStub)
            view1 = ((ViewStub)view).inflate();
        return (ViewGroup)view1;
    }

    private int selectContentView()
    {
        if(mButtonPanelSideLayout == 0)
            return mAlertDialogLayout;
        if(mButtonPanelLayoutHint == 1)
            return mButtonPanelSideLayout;
        else
            return mAlertDialogLayout;
    }

    private void setScrollIndicators(ViewGroup viewgroup, final View top, int i, int j)
    {
        final View bottom = mWindow.findViewById(android.support.v7.appcompat.R.id.scrollIndicatorUp);
        View view = mWindow.findViewById(android.support.v7.appcompat.R.id.scrollIndicatorDown);
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            ViewCompat.setScrollIndicators(top, i, j);
            if(bottom != null)
                viewgroup.removeView(bottom);
            if(view != null)
                viewgroup.removeView(view);
        } else
        {
            top = bottom;
            if(bottom != null)
            {
                top = bottom;
                if((i & 1) == 0)
                {
                    viewgroup.removeView(bottom);
                    top = null;
                }
            }
            bottom = view;
            if(view != null)
            {
                bottom = view;
                if((i & 2) == 0)
                {
                    viewgroup.removeView(view);
                    bottom = null;
                }
            }
            if(top != null || bottom != null)
            {
                if(mMessage != null)
                {
                    mScrollView.setOnScrollChangeListener(new android.support.v4.widget.NestedScrollView.OnScrollChangeListener() {

                        public void onScrollChange(NestedScrollView nestedscrollview, int k, int l, int i1, int j1)
                        {
                            AlertController.manageScrollIndicators(nestedscrollview, top, bottom);
                        }

                        final AlertController this$0;
                        final View val$bottom;
                        final View val$top;

            
            {
                this$0 = AlertController.this;
                top = view;
                bottom = view1;
                super();
            }
                    }
);
                    mScrollView.post(new Runnable() {

                        public void run()
                        {
                            AlertController.manageScrollIndicators(mScrollView, top, bottom);
                        }

                        final AlertController this$0;
                        final View val$bottom;
                        final View val$top;

            
            {
                this$0 = AlertController.this;
                top = view;
                bottom = view1;
                super();
            }
                    }
);
                    return;
                }
                if(mListView != null)
                {
                    mListView.setOnScrollListener(new android.widget.AbsListView.OnScrollListener() {

                        public void onScroll(AbsListView abslistview, int k, int l, int i1)
                        {
                            AlertController.manageScrollIndicators(abslistview, top, bottom);
                        }

                        public void onScrollStateChanged(AbsListView abslistview, int k)
                        {
                        }

                        final AlertController this$0;
                        final View val$bottom;
                        final View val$top;

            
            {
                this$0 = AlertController.this;
                top = view;
                bottom = view1;
                super();
            }
                    }
);
                    mListView.post(new Runnable() {

                        public void run()
                        {
                            AlertController.manageScrollIndicators(mListView, top, bottom);
                        }

                        final AlertController this$0;
                        final View val$bottom;
                        final View val$top;

            
            {
                this$0 = AlertController.this;
                top = view;
                bottom = view1;
                super();
            }
                    }
);
                    return;
                }
                if(top != null)
                    viewgroup.removeView(top);
                if(bottom != null)
                {
                    viewgroup.removeView(bottom);
                    return;
                }
            }
        }
    }

    private void setupButtons(ViewGroup viewgroup)
    {
        boolean flag = false;
        int i = 0;
        mButtonPositive = (Button)viewgroup.findViewById(0x1020019);
        mButtonPositive.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonPositiveText))
        {
            mButtonPositive.setVisibility(8);
        } else
        {
            mButtonPositive.setText(mButtonPositiveText);
            mButtonPositive.setVisibility(0);
            i = false | true;
        }
        mButtonNegative = (Button)viewgroup.findViewById(0x102001a);
        mButtonNegative.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonNegativeText))
        {
            mButtonNegative.setVisibility(8);
        } else
        {
            mButtonNegative.setText(mButtonNegativeText);
            mButtonNegative.setVisibility(0);
            i |= 2;
        }
        mButtonNeutral = (Button)viewgroup.findViewById(0x102001b);
        mButtonNeutral.setOnClickListener(mButtonHandler);
        if(TextUtils.isEmpty(mButtonNeutralText))
        {
            mButtonNeutral.setVisibility(8);
        } else
        {
            mButtonNeutral.setText(mButtonNeutralText);
            mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        if(i != 0)
            flag = true;
        if(!flag)
            viewgroup.setVisibility(8);
    }

    private void setupContent(ViewGroup viewgroup)
    {
        mScrollView = (NestedScrollView)mWindow.findViewById(android.support.v7.appcompat.R.id.scrollView);
        mScrollView.setFocusable(false);
        mScrollView.setNestedScrollingEnabled(false);
        mMessageView = (TextView)viewgroup.findViewById(0x102000b);
        if(mMessageView == null)
            return;
        if(mMessage != null)
        {
            mMessageView.setText(mMessage);
            return;
        }
        mMessageView.setVisibility(8);
        mScrollView.removeView(mMessageView);
        if(mListView != null)
        {
            viewgroup = (ViewGroup)mScrollView.getParent();
            int i = viewgroup.indexOfChild(mScrollView);
            viewgroup.removeViewAt(i);
            viewgroup.addView(mListView, i, new android.view.ViewGroup.LayoutParams(-1, -1));
            return;
        } else
        {
            viewgroup.setVisibility(8);
            return;
        }
    }

    private void setupCustomContent(ViewGroup viewgroup)
    {
        boolean flag = false;
        View view;
        if(mView != null)
            view = mView;
        else
        if(mViewLayoutResId != 0)
            view = LayoutInflater.from(mContext).inflate(mViewLayoutResId, viewgroup, false);
        else
            view = null;
        if(view != null)
            flag = true;
        if(!flag || !canTextInput(view))
            mWindow.setFlags(0x20000, 0x20000);
        if(flag)
        {
            FrameLayout framelayout = (FrameLayout)mWindow.findViewById(android.support.v7.appcompat.R.id.custom);
            framelayout.addView(view, new android.view.ViewGroup.LayoutParams(-1, -1));
            if(mViewSpacingSpecified)
                framelayout.setPadding(mViewSpacingLeft, mViewSpacingTop, mViewSpacingRight, mViewSpacingBottom);
            if(mListView != null)
                ((android.widget.LinearLayout.LayoutParams)viewgroup.getLayoutParams()).weight = 0.0F;
            return;
        } else
        {
            viewgroup.setVisibility(8);
            return;
        }
    }

    private void setupTitle(ViewGroup viewgroup)
    {
        boolean flag = false;
        if(mCustomTitleView != null)
        {
            android.view.ViewGroup.LayoutParams layoutparams = new android.view.ViewGroup.LayoutParams(-1, -2);
            viewgroup.addView(mCustomTitleView, 0, layoutparams);
            mWindow.findViewById(android.support.v7.appcompat.R.id.title_template).setVisibility(8);
            return;
        }
        mIconView = (ImageView)mWindow.findViewById(0x1020006);
        if(!TextUtils.isEmpty(mTitle))
            flag = true;
        if(flag)
        {
            mTitleView = (TextView)mWindow.findViewById(android.support.v7.appcompat.R.id.alertTitle);
            mTitleView.setText(mTitle);
            if(mIconId != 0)
            {
                mIconView.setImageResource(mIconId);
                return;
            }
            if(mIcon != null)
            {
                mIconView.setImageDrawable(mIcon);
                return;
            } else
            {
                mTitleView.setPadding(mIconView.getPaddingLeft(), mIconView.getPaddingTop(), mIconView.getPaddingRight(), mIconView.getPaddingBottom());
                mIconView.setVisibility(8);
                return;
            }
        } else
        {
            mWindow.findViewById(android.support.v7.appcompat.R.id.title_template).setVisibility(8);
            mIconView.setVisibility(8);
            viewgroup.setVisibility(8);
            return;
        }
    }

    private void setupView()
    {
        Object obj2 = mWindow.findViewById(android.support.v7.appcompat.R.id.parentPanel);
        Object obj4 = ((View) (obj2)).findViewById(android.support.v7.appcompat.R.id.topPanel);
        Object obj3 = ((View) (obj2)).findViewById(android.support.v7.appcompat.R.id.contentPanel);
        Object obj = ((View) (obj2)).findViewById(android.support.v7.appcompat.R.id.buttonPanel);
        obj2 = (ViewGroup)((View) (obj2)).findViewById(android.support.v7.appcompat.R.id.customPanel);
        setupCustomContent(((ViewGroup) (obj2)));
        View view3 = ((ViewGroup) (obj2)).findViewById(android.support.v7.appcompat.R.id.topPanel);
        View view2 = ((ViewGroup) (obj2)).findViewById(android.support.v7.appcompat.R.id.contentPanel);
        View view1 = ((ViewGroup) (obj2)).findViewById(android.support.v7.appcompat.R.id.buttonPanel);
        obj4 = resolvePanel(view3, ((View) (obj4)));
        obj3 = resolvePanel(view2, ((View) (obj3)));
        obj = resolvePanel(view1, ((View) (obj)));
        setupContent(((ViewGroup) (obj3)));
        setupButtons(((ViewGroup) (obj)));
        setupTitle(((ViewGroup) (obj4)));
        int i;
        byte byte0;
        boolean flag;
        if(obj2 != null && ((ViewGroup) (obj2)).getVisibility() != 8)
            i = 1;
        else
            i = 0;
        if(obj4 != null && ((ViewGroup) (obj4)).getVisibility() != 8)
            flag = true;
        else
            flag = false;
        if(obj != null && ((ViewGroup) (obj)).getVisibility() != 8)
            byte0 = 1;
        else
            byte0 = 0;
        if(byte0 == 0 && obj3 != null)
        {
            View view = ((ViewGroup) (obj3)).findViewById(android.support.v7.appcompat.R.id.textSpacerNoButtons);
            if(view != null)
                view.setVisibility(0);
        }
        if(flag && mScrollView != null)
            mScrollView.setClipToPadding(true);
        if(i == 0)
        {
            Object obj1;
            if(mListView != null)
                obj1 = mListView;
            else
                obj1 = mScrollView;
            if(obj1 != null)
            {
                if(flag)
                    i = 1;
                else
                    i = 0;
                if(byte0 != 0)
                    byte0 = 2;
                else
                    byte0 = 0;
                setScrollIndicators(((ViewGroup) (obj3)), ((View) (obj1)), i | byte0, 3);
            }
        }
        obj1 = mListView;
        if(obj1 != null && mAdapter != null)
        {
            ((ListView) (obj1)).setAdapter(mAdapter);
            i = mCheckedItem;
            if(i > -1)
            {
                ((ListView) (obj1)).setItemChecked(i, true);
                ((ListView) (obj1)).setSelection(i);
            }
        }
    }

    public Button getButton(int i)
    {
        switch(i)
        {
        default:
            return null;

        case -1: 
            return mButtonPositive;

        case -2: 
            return mButtonNegative;

        case -3: 
            return mButtonNeutral;
        }
    }

    public int getIconAttributeResId(int i)
    {
        TypedValue typedvalue = new TypedValue();
        mContext.getTheme().resolveAttribute(i, typedvalue, true);
        return typedvalue.resourceId;
    }

    public ListView getListView()
    {
        return mListView;
    }

    public void installContent()
    {
        int i = selectContentView();
        mDialog.setContentView(i);
        setupView();
    }

    public boolean onKeyDown(int i, KeyEvent keyevent)
    {
        return mScrollView != null && mScrollView.executeKeyEvent(keyevent);
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        return mScrollView != null && mScrollView.executeKeyEvent(keyevent);
    }

    public void setButton(int i, CharSequence charsequence, android.content.DialogInterface.OnClickListener onclicklistener, Message message)
    {
        Message message1 = message;
        if(message == null)
        {
            message1 = message;
            if(onclicklistener != null)
                message1 = mHandler.obtainMessage(i, onclicklistener);
        }
        switch(i)
        {
        default:
            throw new IllegalArgumentException("Button does not exist");

        case -1: 
            mButtonPositiveText = charsequence;
            mButtonPositiveMessage = message1;
            return;

        case -2: 
            mButtonNegativeText = charsequence;
            mButtonNegativeMessage = message1;
            return;

        case -3: 
            mButtonNeutralText = charsequence;
            mButtonNeutralMessage = message1;
            return;
        }
    }

    public void setButtonPanelLayoutHint(int i)
    {
        mButtonPanelLayoutHint = i;
    }

    public void setCustomTitle(View view)
    {
        mCustomTitleView = view;
    }

    public void setIcon(int i)
    {
label0:
        {
            mIcon = null;
            mIconId = i;
            if(mIconView != null)
            {
                if(i == 0)
                    break label0;
                mIconView.setVisibility(0);
                mIconView.setImageResource(mIconId);
            }
            return;
        }
        mIconView.setVisibility(8);
    }

    public void setIcon(Drawable drawable)
    {
label0:
        {
            mIcon = drawable;
            mIconId = 0;
            if(mIconView != null)
            {
                if(drawable == null)
                    break label0;
                mIconView.setVisibility(0);
                mIconView.setImageDrawable(drawable);
            }
            return;
        }
        mIconView.setVisibility(8);
    }

    public void setMessage(CharSequence charsequence)
    {
        mMessage = charsequence;
        if(mMessageView != null)
            mMessageView.setText(charsequence);
    }

    public void setTitle(CharSequence charsequence)
    {
        mTitle = charsequence;
        if(mTitleView != null)
            mTitleView.setText(charsequence);
    }

    public void setView(int i)
    {
        mView = null;
        mViewLayoutResId = i;
        mViewSpacingSpecified = false;
    }

    public void setView(View view)
    {
        mView = view;
        mViewLayoutResId = 0;
        mViewSpacingSpecified = false;
    }

    public void setView(View view, int i, int j, int k, int l)
    {
        mView = view;
        mViewLayoutResId = 0;
        mViewSpacingSpecified = true;
        mViewSpacingLeft = i;
        mViewSpacingTop = j;
        mViewSpacingRight = k;
        mViewSpacingBottom = l;
    }

    ListAdapter mAdapter;
    private int mAlertDialogLayout;
    private final android.view.View.OnClickListener mButtonHandler = new android.view.View.OnClickListener() {

        public void onClick(View view)
        {
            if(view == mButtonPositive && mButtonPositiveMessage != null)
                view = Message.obtain(mButtonPositiveMessage);
            else
            if(view == mButtonNegative && mButtonNegativeMessage != null)
                view = Message.obtain(mButtonNegativeMessage);
            else
            if(view == mButtonNeutral && mButtonNeutralMessage != null)
                view = Message.obtain(mButtonNeutralMessage);
            else
                view = null;
            if(view != null)
                view.sendToTarget();
            mHandler.obtainMessage(1, mDialog).sendToTarget();
        }

        final AlertController this$0;

            
            {
                this$0 = AlertController.this;
                super();
            }
    }
;
    Button mButtonNegative;
    Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    Button mButtonNeutral;
    Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    private int mButtonPanelLayoutHint;
    private int mButtonPanelSideLayout;
    Button mButtonPositive;
    Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    int mCheckedItem;
    private final Context mContext;
    private View mCustomTitleView;
    final AppCompatDialog mDialog;
    Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    int mListItemLayout;
    int mListLayout;
    ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    int mMultiChoiceItemLayout;
    NestedScrollView mScrollView;
    int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private int mViewLayoutResId;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;
}
