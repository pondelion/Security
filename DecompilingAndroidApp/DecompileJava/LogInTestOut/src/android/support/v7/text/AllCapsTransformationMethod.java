// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.text;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.text.method.TransformationMethod;
import android.view.View;
import java.util.Locale;

public class AllCapsTransformationMethod
    implements TransformationMethod
{

    public AllCapsTransformationMethod(Context context)
    {
        mLocale = context.getResources().getConfiguration().locale;
    }

    public CharSequence getTransformation(CharSequence charsequence, View view)
    {
        if(charsequence != null)
            return charsequence.toString().toUpperCase(mLocale);
        else
            return null;
    }

    public void onFocusChanged(View view, CharSequence charsequence, boolean flag, int i, Rect rect)
    {
    }

    private Locale mLocale;
}
