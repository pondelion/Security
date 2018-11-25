// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.support.v4.view.ActionProvider;
import android.support.v7.content.res.AppCompatResources;
import android.util.TypedValue;
import android.view.*;

// Referenced classes of package android.support.v7.widget:
//            ActivityChooserModel, ActivityChooserView

public class ShareActionProvider extends ActionProvider
{
    public static interface OnShareTargetSelectedListener
    {

        public abstract boolean onShareTargetSelected(ShareActionProvider shareactionprovider, Intent intent);
    }

    private class ShareActivityChooserModelPolicy
        implements ActivityChooserModel.OnChooseActivityListener
    {

        public boolean onChooseActivity(ActivityChooserModel activitychoosermodel, Intent intent)
        {
            if(mOnShareTargetSelectedListener != null)
                mOnShareTargetSelectedListener.onShareTargetSelected(ShareActionProvider.this, intent);
            return false;
        }

        final ShareActionProvider this$0;

        ShareActivityChooserModelPolicy()
        {
            this$0 = ShareActionProvider.this;
            super();
        }
    }

    private class ShareMenuItemOnMenuItemClickListener
        implements android.view.MenuItem.OnMenuItemClickListener
    {

        public boolean onMenuItemClick(MenuItem menuitem)
        {
            menuitem = ActivityChooserModel.get(mContext, mShareHistoryFileName).chooseActivity(menuitem.getItemId());
            if(menuitem != null)
            {
                String s = menuitem.getAction();
                if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
                    updateIntent(menuitem);
                mContext.startActivity(menuitem);
            }
            return true;
        }

        final ShareActionProvider this$0;

        ShareMenuItemOnMenuItemClickListener()
        {
            this$0 = ShareActionProvider.this;
            super();
        }
    }


    public ShareActionProvider(Context context)
    {
        super(context);
        mMaxShownActivityCount = 4;
        mShareHistoryFileName = "share_history.xml";
        mContext = context;
    }

    private void setActivityChooserPolicyIfNeeded()
    {
        if(mOnShareTargetSelectedListener == null)
            return;
        if(mOnChooseActivityListener == null)
            mOnChooseActivityListener = new ShareActivityChooserModelPolicy();
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setOnChooseActivityListener(mOnChooseActivityListener);
    }

    public boolean hasSubMenu()
    {
        return true;
    }

    public View onCreateActionView()
    {
        ActivityChooserView activitychooserview = new ActivityChooserView(mContext);
        if(!activitychooserview.isInEditMode())
            activitychooserview.setActivityChooserModel(ActivityChooserModel.get(mContext, mShareHistoryFileName));
        TypedValue typedvalue = new TypedValue();
        mContext.getTheme().resolveAttribute(android.support.v7.appcompat.R.attr.actionModeShareDrawable, typedvalue, true);
        activitychooserview.setExpandActivityOverflowButtonDrawable(AppCompatResources.getDrawable(mContext, typedvalue.resourceId));
        activitychooserview.setProvider(this);
        activitychooserview.setDefaultActionButtonContentDescription(android.support.v7.appcompat.R.string.abc_shareactionprovider_share_with_application);
        activitychooserview.setExpandActivityOverflowButtonContentDescription(android.support.v7.appcompat.R.string.abc_shareactionprovider_share_with);
        return activitychooserview;
    }

    public void onPrepareSubMenu(SubMenu submenu)
    {
        submenu.clear();
        ActivityChooserModel activitychoosermodel = ActivityChooserModel.get(mContext, mShareHistoryFileName);
        android.content.pm.PackageManager packagemanager = mContext.getPackageManager();
        int k = activitychoosermodel.getActivityCount();
        int l = Math.min(k, mMaxShownActivityCount);
        for(int i = 0; i < l; i++)
        {
            ResolveInfo resolveinfo = activitychoosermodel.getActivity(i);
            submenu.add(0, i, i, resolveinfo.loadLabel(packagemanager)).setIcon(resolveinfo.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
        }

        if(l < k)
        {
            submenu = submenu.addSubMenu(0, l, l, mContext.getString(android.support.v7.appcompat.R.string.abc_activity_chooser_view_see_all));
            for(int j = 0; j < k; j++)
            {
                ResolveInfo resolveinfo1 = activitychoosermodel.getActivity(j);
                submenu.add(0, j, j, resolveinfo1.loadLabel(packagemanager)).setIcon(resolveinfo1.loadIcon(packagemanager)).setOnMenuItemClickListener(mOnMenuItemClickListener);
            }

        }
    }

    public void setOnShareTargetSelectedListener(OnShareTargetSelectedListener onsharetargetselectedlistener)
    {
        mOnShareTargetSelectedListener = onsharetargetselectedlistener;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareHistoryFileName(String s)
    {
        mShareHistoryFileName = s;
        setActivityChooserPolicyIfNeeded();
    }

    public void setShareIntent(Intent intent)
    {
        if(intent != null)
        {
            String s = intent.getAction();
            if("android.intent.action.SEND".equals(s) || "android.intent.action.SEND_MULTIPLE".equals(s))
                updateIntent(intent);
        }
        ActivityChooserModel.get(mContext, mShareHistoryFileName).setIntent(intent);
    }

    void updateIntent(Intent intent)
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
        {
            intent.addFlags(0x8080000);
            return;
        } else
        {
            intent.addFlags(0x80000);
            return;
        }
    }

    private static final int DEFAULT_INITIAL_ACTIVITY_COUNT = 4;
    public static final String DEFAULT_SHARE_HISTORY_FILE_NAME = "share_history.xml";
    final Context mContext;
    private int mMaxShownActivityCount;
    private ActivityChooserModel.OnChooseActivityListener mOnChooseActivityListener;
    private final ShareMenuItemOnMenuItemClickListener mOnMenuItemClickListener = new ShareMenuItemOnMenuItemClickListener();
    OnShareTargetSelectedListener mOnShareTargetSelectedListener;
    String mShareHistoryFileName;
}
