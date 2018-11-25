// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.FrameLayout;

// Referenced classes of package android.support.v7.widget:
//            FitWindowsViewGroup

public class FitWindowsFrameLayout extends FrameLayout
    implements FitWindowsViewGroup
{

    public FitWindowsFrameLayout(Context context)
    {
        super(context);
    }

    public FitWindowsFrameLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected boolean fitSystemWindows(Rect rect)
    {
        if(mListener != null)
            mListener.onFitSystemWindows(rect);
        return super.fitSystemWindows(rect);
    }

    public void setOnFitSystemWindowsListener(FitWindowsViewGroup.OnFitSystemWindowsListener onfitsystemwindowslistener)
    {
        mListener = onfitsystemwindowslistener;
    }

    private FitWindowsViewGroup.OnFitSystemWindowsListener mListener;
}
