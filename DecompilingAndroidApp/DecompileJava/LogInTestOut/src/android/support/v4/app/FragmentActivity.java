// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.content.*;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.*;
import android.support.v4.media.session.MediaControllerCompat;
import android.support.v4.util.SimpleArrayMap;
import android.support.v4.util.SparseArrayCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.*;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            BaseFragmentActivityJB, FragmentController, Fragment, FragmentManager, 
//            ActivityCompat, ActivityCompat21, ActivityCompatHoneycomb, LoaderManager, 
//            SharedElementCallback, FragmentHostCallback, FragmentManagerNonConfig

public class FragmentActivity extends BaseFragmentActivityJB
    implements ActivityCompat.OnRequestPermissionsResultCallback, ActivityCompatApi23.RequestPermissionsRequestCodeValidator
{
    class HostCallbacks extends FragmentHostCallback
    {

        public void onAttachFragment(Fragment fragment)
        {
            FragmentActivity.this.onAttachFragment(fragment);
        }

        public void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
        {
            dump(s, filedescriptor, printwriter, as);
        }

        public View onFindViewById(int i)
        {
            return findViewById(i);
        }

        public FragmentActivity onGetHost()
        {
            return FragmentActivity.this;
        }

        public volatile Object onGetHost()
        {
            return onGetHost();
        }

        public LayoutInflater onGetLayoutInflater()
        {
            return getLayoutInflater().cloneInContext(FragmentActivity.this);
        }

        public int onGetWindowAnimations()
        {
            Window window = getWindow();
            if(window == null)
                return 0;
            else
                return window.getAttributes().windowAnimations;
        }

        public boolean onHasView()
        {
            Window window = getWindow();
            return window != null && window.peekDecorView() != null;
        }

        public boolean onHasWindowAnimations()
        {
            return getWindow() != null;
        }

        public void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
        {
            requestPermissionsFromFragment(fragment, as, i);
        }

        public boolean onShouldSaveFragmentState(Fragment fragment)
        {
            return !isFinishing();
        }

        public boolean onShouldShowRequestPermissionRationale(String s)
        {
            return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
        }

        public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i)
        {
            startActivityFromFragment(fragment, intent, i);
        }

        public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
        {
            startActivityFromFragment(fragment, intent, i, bundle);
        }

        public void onStartIntentSenderFromFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
                Bundle bundle)
            throws android.content.IntentSender.SendIntentException
        {
            startIntentSenderFromFragment(fragment, intentsender, i, intent, j, k, l, bundle);
        }

        public void onSupportInvalidateOptionsMenu()
        {
            supportInvalidateOptionsMenu();
        }

        final FragmentActivity this$0;

        public HostCallbacks()
        {
            this$0 = FragmentActivity.this;
            super(FragmentActivity.this);
        }
    }

    static final class NonConfigurationInstances
    {

        Object custom;
        FragmentManagerNonConfig fragments;
        SimpleArrayMap loaders;

        NonConfigurationInstances()
        {
        }
    }


    public FragmentActivity()
    {
    }

    private int allocateRequestIndex(Fragment fragment)
    {
        if(mPendingFragmentActivityResults.size() >= 65534)
            throw new IllegalStateException("Too many pending Fragment activity results.");
        for(; mPendingFragmentActivityResults.indexOfKey(mNextCandidateRequestIndex) >= 0; mNextCandidateRequestIndex = (mNextCandidateRequestIndex + 1) % 65534);
        int i = mNextCandidateRequestIndex;
        mPendingFragmentActivityResults.put(i, fragment.mWho);
        mNextCandidateRequestIndex = (mNextCandidateRequestIndex + 1) % 65534;
        return i;
    }

    private void dumpViewHierarchy(String s, PrintWriter printwriter, View view)
    {
        printwriter.print(s);
        if(view != null) goto _L2; else goto _L1
_L1:
        printwriter.println("null");
_L4:
        return;
_L2:
        printwriter.println(viewToString(view));
        if(view instanceof ViewGroup)
        {
            view = (ViewGroup)view;
            int j = view.getChildCount();
            if(j > 0)
            {
                s = (new StringBuilder()).append(s).append("  ").toString();
                int i = 0;
                while(i < j) 
                {
                    dumpViewHierarchy(s, printwriter, view.getChildAt(i));
                    i++;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    private static String viewToString(View view)
    {
        byte byte0;
        byte byte1;
        StringBuilder stringbuilder;
        byte1 = 70;
        byte0 = 46;
        stringbuilder = new StringBuilder(128);
        stringbuilder.append(view.getClass().getName());
        stringbuilder.append('{');
        stringbuilder.append(Integer.toHexString(System.identityHashCode(view)));
        stringbuilder.append(' ');
        view.getVisibility();
        JVM INSTR lookupswitch 3: default 100
    //                   0: 526
    //                   4: 537
    //                   8: 548;
           goto _L1 _L2 _L3 _L4
_L1:
        stringbuilder.append('.');
_L11:
        char c;
        int i;
        Object obj;
        String s;
        if(view.isFocusable())
            c = 'F';
        else
            c = '.';
        stringbuilder.append(c);
        if(view.isEnabled())
            c = 'E';
        else
            c = '.';
        stringbuilder.append(c);
        if(view.willNotDraw())
            c = '.';
        else
            c = 'D';
        stringbuilder.append(c);
        if(view.isHorizontalScrollBarEnabled())
            c = 'H';
        else
            c = '.';
        stringbuilder.append(c);
        if(view.isVerticalScrollBarEnabled())
            c = 'V';
        else
            c = '.';
        stringbuilder.append(c);
        if(view.isClickable())
            c = 'C';
        else
            c = '.';
        stringbuilder.append(c);
        if(view.isLongClickable())
            c = 'L';
        else
            c = '.';
        stringbuilder.append(c);
        stringbuilder.append(' ');
        if(view.isFocused())
            c = byte1;
        else
            c = '.';
        stringbuilder.append(c);
        if(view.isSelected())
            c = 'S';
        else
            c = '.';
        stringbuilder.append(c);
        c = byte0;
        if(view.isPressed())
            c = 'P';
        stringbuilder.append(c);
        stringbuilder.append(' ');
        stringbuilder.append(view.getLeft());
        stringbuilder.append(',');
        stringbuilder.append(view.getTop());
        stringbuilder.append('-');
        stringbuilder.append(view.getRight());
        stringbuilder.append(',');
        stringbuilder.append(view.getBottom());
        i = view.getId();
        if(i == -1) goto _L6; else goto _L5
_L5:
        stringbuilder.append(" #");
        stringbuilder.append(Integer.toHexString(i));
        obj = view.getResources();
        if(i == 0 || obj == null) goto _L6; else goto _L7
_L7:
        0xff000000 & i;
        JVM INSTR lookupswitch 2: default 436
    //                   16777216: 620
    //                   2130706432: 613;
           goto _L8 _L9 _L10
_L8:
        view = ((Resources) (obj)).getResourcePackageName(i);
_L12:
        s = ((Resources) (obj)).getResourceTypeName(i);
        obj = ((Resources) (obj)).getResourceEntryName(i);
        stringbuilder.append(" ");
        stringbuilder.append(view);
        stringbuilder.append(":");
        stringbuilder.append(s);
        stringbuilder.append("/");
        stringbuilder.append(((String) (obj)));
_L6:
        stringbuilder.append("}");
        return stringbuilder.toString();
_L2:
        stringbuilder.append('V');
          goto _L11
_L3:
        stringbuilder.append('I');
          goto _L11
_L4:
        stringbuilder.append('G');
          goto _L11
_L10:
        view = "app";
          goto _L12
_L9:
        view = "android";
          goto _L12
        view;
          goto _L6
    }

    final View dispatchFragmentsOnCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        return mFragments.onCreateView(view, s, context, attributeset);
    }

    void doReallyStop(boolean flag)
    {
        if(!mReallyStopped)
        {
            mReallyStopped = true;
            mRetaining = flag;
            mHandler.removeMessages(1);
            onReallyStop();
        } else
        if(flag)
        {
            mFragments.doLoaderStart();
            mFragments.doLoaderStop(true);
            return;
        }
    }

    public void dump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        if(android.os.Build.VERSION.SDK_INT < 11);
        printwriter.print(s);
        printwriter.print("Local FragmentActivity ");
        printwriter.print(Integer.toHexString(System.identityHashCode(this)));
        printwriter.println(" State:");
        String s1 = (new StringBuilder()).append(s).append("  ").toString();
        printwriter.print(s1);
        printwriter.print("mCreated=");
        printwriter.print(mCreated);
        printwriter.print("mResumed=");
        printwriter.print(mResumed);
        printwriter.print(" mStopped=");
        printwriter.print(mStopped);
        printwriter.print(" mReallyStopped=");
        printwriter.println(mReallyStopped);
        mFragments.dumpLoaders(s1, filedescriptor, printwriter, as);
        mFragments.getSupportFragmentManager().dump(s, filedescriptor, printwriter, as);
        printwriter.print(s);
        printwriter.println("View Hierarchy:");
        dumpViewHierarchy((new StringBuilder()).append(s).append("  ").toString(), printwriter, getWindow().getDecorView());
    }

    public Object getLastCustomNonConfigurationInstance()
    {
        NonConfigurationInstances nonconfigurationinstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
        if(nonconfigurationinstances != null)
            return nonconfigurationinstances.custom;
        else
            return null;
    }

    public FragmentManager getSupportFragmentManager()
    {
        return mFragments.getSupportFragmentManager();
    }

    public LoaderManager getSupportLoaderManager()
    {
        return mFragments.getSupportLoaderManager();
    }

    public final MediaControllerCompat getSupportMediaController()
    {
        return mMediaController;
    }

    protected void onActivityResult(int i, int j, Intent intent)
    {
        mFragments.noteStateNotSaved();
        int k = i >> 16;
        if(k != 0)
        {
            k--;
            String s = (String)mPendingFragmentActivityResults.get(k);
            mPendingFragmentActivityResults.remove(k);
            if(s == null)
            {
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
                return;
            }
            Fragment fragment = mFragments.findFragmentByWho(s);
            if(fragment == null)
            {
                Log.w("FragmentActivity", (new StringBuilder()).append("Activity result no fragment exists for who: ").append(s).toString());
                return;
            } else
            {
                fragment.onActivityResult(0xffff & i, j, intent);
                return;
            }
        } else
        {
            super.onActivityResult(i, j, intent);
            return;
        }
    }

    public void onAttachFragment(Fragment fragment)
    {
    }

    public void onBackPressed()
    {
        if(!mFragments.getSupportFragmentManager().popBackStackImmediate())
            super.onBackPressed();
    }

    public void onConfigurationChanged(Configuration configuration)
    {
        super.onConfigurationChanged(configuration);
        mFragments.dispatchConfigurationChanged(configuration);
    }

    protected void onCreate(Bundle bundle)
    {
        FragmentManagerNonConfig fragmentmanagernonconfig = null;
        mFragments.attachHost(null);
        super.onCreate(bundle);
        NonConfigurationInstances nonconfigurationinstances = (NonConfigurationInstances)getLastNonConfigurationInstance();
        if(nonconfigurationinstances != null)
            mFragments.restoreLoaderNonConfig(nonconfigurationinstances.loaders);
        if(bundle != null)
        {
            android.os.Parcelable parcelable = bundle.getParcelable("android:support:fragments");
            FragmentController fragmentcontroller = mFragments;
            if(nonconfigurationinstances != null)
                fragmentmanagernonconfig = nonconfigurationinstances.fragments;
            fragmentcontroller.restoreAllState(parcelable, fragmentmanagernonconfig);
            if(bundle.containsKey("android:support:next_request_index"))
            {
                mNextCandidateRequestIndex = bundle.getInt("android:support:next_request_index");
                int ai[] = bundle.getIntArray("android:support:request_indicies");
                bundle = bundle.getStringArray("android:support:request_fragment_who");
                if(ai == null || bundle == null || ai.length != bundle.length)
                {
                    Log.w("FragmentActivity", "Invalid requestCode mapping in savedInstanceState.");
                } else
                {
                    mPendingFragmentActivityResults = new SparseArrayCompat(ai.length);
                    int i = 0;
                    while(i < ai.length) 
                    {
                        mPendingFragmentActivityResults.put(ai[i], bundle[i]);
                        i++;
                    }
                }
            }
        }
        if(mPendingFragmentActivityResults == null)
        {
            mPendingFragmentActivityResults = new SparseArrayCompat();
            mNextCandidateRequestIndex = 0;
        }
        mFragments.dispatchCreate();
    }

    public boolean onCreatePanelMenu(int i, Menu menu)
    {
        if(i == 0)
        {
            boolean flag = super.onCreatePanelMenu(i, menu);
            boolean flag1 = mFragments.dispatchCreateOptionsMenu(menu, getMenuInflater());
            if(android.os.Build.VERSION.SDK_INT >= 11)
                return flag | flag1;
            else
                return true;
        } else
        {
            return super.onCreatePanelMenu(i, menu);
        }
    }

    public volatile View onCreateView(View view, String s, Context context, AttributeSet attributeset)
    {
        return super.onCreateView(view, s, context, attributeset);
    }

    public volatile View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        return super.onCreateView(s, context, attributeset);
    }

    protected void onDestroy()
    {
        super.onDestroy();
        doReallyStop(false);
        mFragments.dispatchDestroy();
        mFragments.doLoaderDestroy();
    }

    public void onLowMemory()
    {
        super.onLowMemory();
        mFragments.dispatchLowMemory();
    }

    public boolean onMenuItemSelected(int i, MenuItem menuitem)
    {
        if(super.onMenuItemSelected(i, menuitem))
            return true;
        switch(i)
        {
        default:
            return false;

        case 0: // '\0'
            return mFragments.dispatchOptionsItemSelected(menuitem);

        case 6: // '\006'
            return mFragments.dispatchContextItemSelected(menuitem);
        }
    }

    public void onMultiWindowModeChanged(boolean flag)
    {
        mFragments.dispatchMultiWindowModeChanged(flag);
    }

    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        mFragments.noteStateNotSaved();
    }

    public void onPanelClosed(int i, Menu menu)
    {
        i;
        JVM INSTR tableswitch 0 0: default 20
    //                   0 27;
           goto _L1 _L2
_L1:
        super.onPanelClosed(i, menu);
        return;
_L2:
        mFragments.dispatchOptionsMenuClosed(menu);
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void onPause()
    {
        super.onPause();
        mResumed = false;
        if(mHandler.hasMessages(2))
        {
            mHandler.removeMessages(2);
            onResumeFragments();
        }
        mFragments.dispatchPause();
    }

    public void onPictureInPictureModeChanged(boolean flag)
    {
        mFragments.dispatchPictureInPictureModeChanged(flag);
    }

    protected void onPostResume()
    {
        super.onPostResume();
        mHandler.removeMessages(2);
        onResumeFragments();
        mFragments.execPendingActions();
    }

    protected boolean onPrepareOptionsPanel(View view, Menu menu)
    {
        return super.onPreparePanel(0, view, menu);
    }

    public boolean onPreparePanel(int i, View view, Menu menu)
    {
        if(i == 0 && menu != null)
        {
            if(mOptionsMenuInvalidated)
            {
                mOptionsMenuInvalidated = false;
                menu.clear();
                onCreatePanelMenu(i, menu);
            }
            return onPrepareOptionsPanel(view, menu) | mFragments.dispatchPrepareOptionsMenu(menu);
        } else
        {
            return super.onPreparePanel(i, view, menu);
        }
    }

    void onReallyStop()
    {
        mFragments.doLoaderStop(mRetaining);
        mFragments.dispatchReallyStop();
    }

    public void onRequestPermissionsResult(int i, String as[], int ai[])
    {
        String s;
label0:
        {
            int j = i >> 16 & 0xffff;
            if(j != 0)
            {
                j--;
                s = (String)mPendingFragmentActivityResults.get(j);
                mPendingFragmentActivityResults.remove(j);
                if(s != null)
                    break label0;
                Log.w("FragmentActivity", "Activity result delivered for unknown Fragment.");
            }
            return;
        }
        Fragment fragment = mFragments.findFragmentByWho(s);
        if(fragment == null)
        {
            Log.w("FragmentActivity", (new StringBuilder()).append("Activity result no fragment exists for who: ").append(s).toString());
            return;
        } else
        {
            fragment.onRequestPermissionsResult(i & 0xffff, as, ai);
            return;
        }
    }

    protected void onResume()
    {
        super.onResume();
        mHandler.sendEmptyMessage(2);
        mResumed = true;
        mFragments.execPendingActions();
    }

    protected void onResumeFragments()
    {
        mFragments.dispatchResume();
    }

    public Object onRetainCustomNonConfigurationInstance()
    {
        return null;
    }

    public final Object onRetainNonConfigurationInstance()
    {
        if(mStopped)
            doReallyStop(true);
        Object obj = onRetainCustomNonConfigurationInstance();
        FragmentManagerNonConfig fragmentmanagernonconfig = mFragments.retainNestedNonConfig();
        SimpleArrayMap simplearraymap = mFragments.retainLoaderNonConfig();
        if(fragmentmanagernonconfig == null && simplearraymap == null && obj == null)
        {
            return null;
        } else
        {
            NonConfigurationInstances nonconfigurationinstances = new NonConfigurationInstances();
            nonconfigurationinstances.custom = obj;
            nonconfigurationinstances.fragments = fragmentmanagernonconfig;
            nonconfigurationinstances.loaders = simplearraymap;
            return nonconfigurationinstances;
        }
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        android.os.Parcelable parcelable = mFragments.saveAllState();
        if(parcelable != null)
            bundle.putParcelable("android:support:fragments", parcelable);
        if(mPendingFragmentActivityResults.size() > 0)
        {
            bundle.putInt("android:support:next_request_index", mNextCandidateRequestIndex);
            int ai[] = new int[mPendingFragmentActivityResults.size()];
            String as[] = new String[mPendingFragmentActivityResults.size()];
            for(int i = 0; i < mPendingFragmentActivityResults.size(); i++)
            {
                ai[i] = mPendingFragmentActivityResults.keyAt(i);
                as[i] = (String)mPendingFragmentActivityResults.valueAt(i);
            }

            bundle.putIntArray("android:support:request_indicies", ai);
            bundle.putStringArray("android:support:request_fragment_who", as);
        }
    }

    protected void onStart()
    {
        super.onStart();
        mStopped = false;
        mReallyStopped = false;
        mHandler.removeMessages(1);
        if(!mCreated)
        {
            mCreated = true;
            mFragments.dispatchActivityCreated();
        }
        mFragments.noteStateNotSaved();
        mFragments.execPendingActions();
        mFragments.doLoaderStart();
        mFragments.dispatchStart();
        mFragments.reportLoaderStart();
    }

    public void onStateNotSaved()
    {
        mFragments.noteStateNotSaved();
    }

    protected void onStop()
    {
        super.onStop();
        mStopped = true;
        mHandler.sendEmptyMessage(1);
        mFragments.dispatchStop();
    }

    void requestPermissionsFromFragment(Fragment fragment, String as[], int i)
    {
        if(i == -1)
        {
            ActivityCompat.requestPermissions(this, as, i);
            return;
        }
        checkForValidRequestCode(i);
        mRequestedPermissionsFromFragment = true;
        ActivityCompat.requestPermissions(this, as, (allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i));
        mRequestedPermissionsFromFragment = false;
        return;
        fragment;
        mRequestedPermissionsFromFragment = false;
        throw fragment;
    }

    public void setEnterSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        ActivityCompat.setEnterSharedElementCallback(this, sharedelementcallback);
    }

    public void setExitSharedElementCallback(SharedElementCallback sharedelementcallback)
    {
        ActivityCompat.setExitSharedElementCallback(this, sharedelementcallback);
    }

    public final void setSupportMediaController(MediaControllerCompat mediacontrollercompat)
    {
        mMediaController = mediacontrollercompat;
        if(android.os.Build.VERSION.SDK_INT >= 21)
            ActivityCompat21.setMediaController(this, mediacontrollercompat.getMediaController());
    }

    public void startActivityForResult(Intent intent, int i)
    {
        if(!mStartedActivityFromFragment && i != -1)
            checkForValidRequestCode(i);
        super.startActivityForResult(intent, i);
    }

    public volatile void startActivityForResult(Intent intent, int i, Bundle bundle)
    {
        super.startActivityForResult(intent, i, bundle);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i)
    {
        startActivityFromFragment(fragment, intent, i, null);
    }

    public void startActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        mStartedActivityFromFragment = true;
        if(i != -1)
            break MISSING_BLOCK_LABEL_24;
        ActivityCompat.startActivityForResult(this, intent, -1, bundle);
        mStartedActivityFromFragment = false;
        return;
        checkForValidRequestCode(i);
        ActivityCompat.startActivityForResult(this, intent, (allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i), bundle);
        mStartedActivityFromFragment = false;
        return;
        fragment;
        mStartedActivityFromFragment = false;
        throw fragment;
    }

    public volatile void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l)
        throws android.content.IntentSender.SendIntentException
    {
        super.startIntentSenderForResult(intentsender, i, intent, j, k, l);
    }

    public volatile void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l, Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        super.startIntentSenderForResult(intentsender, i, intent, j, k, l, bundle);
    }

    public void startIntentSenderFromFragment(Fragment fragment, IntentSender intentsender, int i, Intent intent, int j, int k, int l, 
            Bundle bundle)
        throws android.content.IntentSender.SendIntentException
    {
        mStartedIntentSenderFromFragment = true;
        if(i != -1)
            break MISSING_BLOCK_LABEL_32;
        ActivityCompat.startIntentSenderForResult(this, intentsender, i, intent, j, k, l, bundle);
        mStartedIntentSenderFromFragment = false;
        return;
        checkForValidRequestCode(i);
        ActivityCompat.startIntentSenderForResult(this, intentsender, (allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i), intent, j, k, l, bundle);
        mStartedIntentSenderFromFragment = false;
        return;
        fragment;
        mStartedIntentSenderFromFragment = false;
        throw fragment;
    }

    public void supportFinishAfterTransition()
    {
        ActivityCompat.finishAfterTransition(this);
    }

    public void supportInvalidateOptionsMenu()
    {
        if(android.os.Build.VERSION.SDK_INT >= 11)
        {
            ActivityCompatHoneycomb.invalidateOptionsMenu(this);
            return;
        } else
        {
            mOptionsMenuInvalidated = true;
            return;
        }
    }

    public void supportPostponeEnterTransition()
    {
        ActivityCompat.postponeEnterTransition(this);
    }

    public void supportStartPostponedEnterTransition()
    {
        ActivityCompat.startPostponedEnterTransition(this);
    }

    public final void validateRequestPermissionsRequestCode(int i)
    {
        if(!mRequestedPermissionsFromFragment && i != -1)
            checkForValidRequestCode(i);
    }

    static final String ALLOCATED_REQUEST_INDICIES_TAG = "android:support:request_indicies";
    static final String FRAGMENTS_TAG = "android:support:fragments";
    private static final int HONEYCOMB = 11;
    static final int MAX_NUM_PENDING_FRAGMENT_ACTIVITY_RESULTS = 65534;
    static final int MSG_REALLY_STOPPED = 1;
    static final int MSG_RESUME_PENDING = 2;
    static final String NEXT_CANDIDATE_REQUEST_INDEX_TAG = "android:support:next_request_index";
    static final String REQUEST_FRAGMENT_WHO_TAG = "android:support:request_fragment_who";
    private static final String TAG = "FragmentActivity";
    boolean mCreated;
    final FragmentController mFragments = FragmentController.createController(new HostCallbacks());
    final Handler mHandler = new Handler() {

        public void handleMessage(Message message)
        {
            message.what;
            JVM INSTR tableswitch 1 2: default 28
        //                       1 34
        //                       2 53;
               goto _L1 _L2 _L3
_L1:
            super.handleMessage(message);
_L5:
            return;
_L2:
            if(!mStopped) goto _L5; else goto _L4
_L4:
            doReallyStop(false);
            return;
_L3:
            onResumeFragments();
            mFragments.execPendingActions();
            return;
        }

        final FragmentActivity this$0;

            
            {
                this$0 = FragmentActivity.this;
                super();
            }
    }
;
    MediaControllerCompat mMediaController;
    int mNextCandidateRequestIndex;
    boolean mOptionsMenuInvalidated;
    SparseArrayCompat mPendingFragmentActivityResults;
    boolean mReallyStopped;
    boolean mRequestedPermissionsFromFragment;
    boolean mResumed;
    boolean mRetaining;
    boolean mStopped;
}
