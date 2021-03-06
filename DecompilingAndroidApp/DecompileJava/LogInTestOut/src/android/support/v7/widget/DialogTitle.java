// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Layout;
import android.util.AttributeSet;
import android.widget.TextView;

public class DialogTitle extends TextView
{

    public DialogTitle(Context context)
    {
        super(context);
    }

    public DialogTitle(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public DialogTitle(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        Layout layout = getLayout();
        if(layout != null)
        {
            int k = layout.getLineCount();
            if(k > 0 && layout.getEllipsisCount(k - 1) > 0)
            {
                setSingleLine(false);
                setMaxLines(2);
                TypedArray typedarray = getContext().obtainStyledAttributes(null, android.support.v7.appcompat.R.styleable.TextAppearance, 0x1010041, 0x1030044);
                int l = typedarray.getDimensionPixelSize(android.support.v7.appcompat.R.styleable.TextAppearance_android_textSize, 0);
                if(l != 0)
                    setTextSize(0, l);
                typedarray.recycle();
                super.onMeasure(i, j);
            }
        }
    }
}
