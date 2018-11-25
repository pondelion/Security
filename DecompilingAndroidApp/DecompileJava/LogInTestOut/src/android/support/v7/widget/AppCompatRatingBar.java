// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.RatingBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatProgressBarHelper

public class AppCompatRatingBar extends RatingBar
{

    public AppCompatRatingBar(Context context)
    {
        AppCompatRatingBar(context, null);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeset)
    {
        AppCompatRatingBar(context, attributeset, android.support.v7.appcompat.R.attr.ratingBarStyle);
    }

    public AppCompatRatingBar(Context context, AttributeSet attributeset, int i)
    {
        RatingBar(context, attributeset, i);
        mAppCompatProgressBarHelper = new AppCompatProgressBarHelper(this);
        mAppCompatProgressBarHelper.loadFromAttributes(attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        Bitmap bitmap;
        onMeasure(i, j);
        bitmap = mAppCompatProgressBarHelper.getSampleTime();
        if(bitmap == null)
            break MISSING_BLOCK_LABEL_42;
        setMeasuredDimension(ViewCompat.resolveSizeAndState(bitmap.getWidth() * getNumStars(), i, 0), getMeasuredHeight());
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    private AppCompatProgressBarHelper mAppCompatProgressBarHelper;
}
