// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.print;

import android.content.Context;

// Referenced classes of package android.support.v4.print:
//            PrintHelperApi23

class PrintHelperApi24 extends PrintHelperApi23
{

    PrintHelperApi24(Context context)
    {
        super(context);
        mIsMinMarginsHandlingCorrect = true;
        mPrintActivityRespectsOrientation = true;
    }
}
