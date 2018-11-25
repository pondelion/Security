// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.SeekBar;

// Referenced classes of package android.support.v7.widget:
//            AppCompatSeekBarHelper

public class AppCompatSeekBar extends SeekBar
{

    public AppCompatSeekBar(Context context)
    {
        AppCompatSeekBar(context, null);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeset)
    {
        AppCompatSeekBar(context, attributeset, android.support.v7.appcompat.R.attr.seekBarStyle);
    }

    public AppCompatSeekBar(Context context, AttributeSet attributeset, int i)
    {
        SeekBar(context, attributeset, i);
        mAppCompatSeekBarHelper = new AppCompatSeekBarHelper(this);
        mAppCompatSeekBarHelper.loadFromAttributes(attributeset, i);
    }

    protected void drawableStateChanged()
    {
        drawableStateChanged();
        mAppCompatSeekBarHelper.drawableStateChanged();
    }

    public void jumpDrawablesToCurrentState()
    {
        jumpDrawablesToCurrentState();
        mAppCompatSeekBarHelper.jumpDrawablesToCurrentState();
    }

    protected void onDraw(Canvas canvas)
    {
        onDraw(canvas);
        mAppCompatSeekBarHelper.drawTickMarks(canvas);
    }

    private AppCompatSeekBarHelper mAppCompatSeekBarHelper;
}
