// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.app;

import android.app.Activity;
import android.content.*;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

abstract class BaseFragmentActivityGingerbread extends Activity
{

    BaseFragmentActivityGingerbread()
    {
    }

    static void checkForValidRequestCode(int i)
    {
        if((0xffff0000 & i) != 0)
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        else
            return;
    }

    abstract View dispatchFragmentsOnCreateView(View view, String s, Context context, AttributeSet attributeset);

    protected void onCreate(Bundle bundle)
    {
        if(android.os.Build.VERSION.SDK_INT < 11 && getLayoutInflater().getFactory() == null)
            getLayoutInflater().setFactory(this);
        super.onCreate(bundle);
    }

    public View onCreateView(String s, Context context, AttributeSet attributeset)
    {
        View view1 = dispatchFragmentsOnCreateView(null, s, context, attributeset);
        View view = view1;
        if(view1 == null)
            view = super.onCreateView(s, context, attributeset);
        return view;
    }

    public void startIntentSenderForResult(IntentSender intentsender, int i, Intent intent, int j, int k, int l)
        throws android.content.IntentSender.SendIntentException
    {
        if(!mStartedIntentSenderFromFragment && i != -1)
            checkForValidRequestCode(i);
        super.startIntentSenderForResult(intentsender, i, intent, j, k, l);
    }

    boolean mStartedIntentSenderFromFragment;
}
