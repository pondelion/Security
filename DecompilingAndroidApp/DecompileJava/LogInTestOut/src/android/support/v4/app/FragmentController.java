// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Parcelable;
import android.support.v4.util.SimpleArrayMap;
import android.util.AttributeSet;
import android.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package android.support.v4.app:
//            FragmentHostCallback, FragmentManagerImpl, FragmentManagerNonConfig, Fragment, 
//            FragmentManager, LoaderManager

public class FragmentController
{

    private FragmentController(FragmentHostCallback fragmenthostcallback)
    {
        mHost = fragmenthostcallback;
    }

    public static final FragmentController createController(FragmentHostCallback fragmenthostcallback)
    {
        return new FragmentController(fragmenthostcallback);
    }

    public void attachHost(Fragment fragment)
    {
        mHost.mFragmentManager.attachController(mHost, mHost, fragment);
    }

    public void dispatchActivityCreated()
    {
        mHost.mFragmentManager.dispatchActivityCreated();
    }

    public void dispatchConfigurationChanged(Configuration configuration)
    {
        mHost.mFragmentManager.dispatchConfigurationChanged(configuration);
    }

    public boolean dispatchContextItemSelected(MenuItem menuitem)
    {
        return mHost.mFragmentManager.dispatchContextItemSelected(menuitem);
    }

    public void dispatchCreate()
    {
        mHost.mFragmentManager.dispatchCreate();
    }

    public boolean dispatchCreateOptionsMenu(Menu menu, MenuInflater menuinflater)
    {
        return mHost.mFragmentManager.dispatchCreateOptionsMenu(menu, menuinflater);
    }

    public void dispatchDestroy()
    {
        mHost.mFragmentManager.dispatchDestroy();
    }

    public void dispatchDestroyView()
    {
        mHost.mFragmentManager.dispatchDestroyView();
    }

    public void dispatchLowMemory()
    {
        mHost.mFragmentManager.dispatchLowMemory();
    }

    public void dispatchMultiWindowModeChanged(boolean flag)
    {
        mHost.mFragmentManager.dispatchMultiWindowModeChanged(flag);
    }

    public boolean dispatchOptionsItemSelected(MenuItem menuitem)
    {
        return mHost.mFragmentManager.dispatchOptionsItemSelected(menuitem);
    }

    public void dispatchOptionsMenuClosed(Menu menu)
    {
        mHost.mFragmentManager.dispatchOptionsMenuClosed(menu);
    }

    public void dispatchPause()
    {
        mHost.mFragmentManager.dispatchPause();
    }

    public void dispatchPictureInPictureModeChanged(boolean flag)
    {
        mHost.mFragmentManager.dispatchPictureInPictureModeChanged(flag);
    }

    public boolean dispatchPrepareOptionsMenu(Menu menu)
    {
        return mHost.mFragmentManager.dispatchPrepareOptionsMenu(menu);
    }

    public void dispatchReallyStop()
    {
        mHost.mFragmentManager.dispatchReallyStop();
    }

    public void dispatchResume()
    {
        mHost.mFragmentManager.dispatchResume();
    }

    public void dispatchStart()
    {
        mHost.mFragmentManager.dispatchStart();
    }

    public void dispatchStop()
    {
        mHost.mFragmentManager.dispatchStop();
    }

    public void doLoaderDestroy()
    {
        mHost.doLoaderDestroy();
    }

    public void doLoaderRetain()
    {
        mHost.doLoaderRetain();
    }

    public void doLoaderStart()
    {
        mHost.doLoaderStart();
    }

    public void doLoaderStop(boolean flag)
    {
        mHost.doLoaderStop(flag);
    }

    public void dumpLoaders(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        mHost.dumpLoaders(s, filedescriptor, printwriter, as);
    }

    public boolean execPendingActions()
    {
        return mHost.mFragmentManager.execPendingActions();
    }

    public Fragment findFragmentByWho(String s)
    {
        return mHost.mFragmentManager.findFragmentByWho(s);
    }

    public List getActiveFragments(List list)
    {
        if(mHost.mFragmentManager.mActive == null)
            return null;
        Object obj = list;
        if(list == null)
            obj = new ArrayList(getActiveFragmentsCount());
        ((List) (obj)).addAll(mHost.mFragmentManager.mActive);
        return ((List) (obj));
    }

    public int getActiveFragmentsCount()
    {
        ArrayList arraylist = mHost.mFragmentManager.mActive;
        if(arraylist == null)
            return 0;
        else
            return arraylist.size();
    }

    public FragmentManager getSupportFragmentManager()
    {
        return mHost.getFragmentManagerImpl();
    }

    public LoaderManager getSupportLoaderManager()
    {
        return mHost.getLoaderManagerImpl();
    }

    public void noteStateNotSaved()
    {
        mHost.mFragmentManager.noteStateNotSaved();
    }

    public View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        return mHost.mFragmentManager.onCreateView(view, s, context, attributeset);
    }

    public void reportLoaderStart()
    {
        mHost.reportLoaderStart();
    }

    public void restoreAllState(Parcelable parcelable, FragmentManagerNonConfig fragmentmanagernonconfig)
    {
        mHost.mFragmentManager.restoreAllState(parcelable, fragmentmanagernonconfig);
    }

    public void restoreAllState(Parcelable parcelable, List list)
    {
        mHost.mFragmentManager.restoreAllState(parcelable, new FragmentManagerNonConfig(list, null));
    }

    public void restoreLoaderNonConfig(SimpleArrayMap simplearraymap)
    {
        mHost.restoreLoaderNonConfig(simplearraymap);
    }

    public SimpleArrayMap retainLoaderNonConfig()
    {
        return mHost.retainLoaderNonConfig();
    }

    public FragmentManagerNonConfig retainNestedNonConfig()
    {
        return mHost.mFragmentManager.retainNonConfig();
    }

    public List retainNonConfig()
    {
        FragmentManagerNonConfig fragmentmanagernonconfig = mHost.mFragmentManager.retainNonConfig();
        if(fragmentmanagernonconfig != null)
            return fragmentmanagernonconfig.getFragments();
        else
            return null;
    }

    public Parcelable saveAllState()
    {
        return mHost.mFragmentManager.saveAllState();
    }

    private final FragmentHostCallback mHost;
}
