// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.print;

import android.content.Context;
import android.print.PrintAttributes;

// Referenced classes of package android.support.v4.print:
//            PrintHelperApi20

class PrintHelperApi23 extends PrintHelperApi20
{

    PrintHelperApi23(Context context)
    {
        super(context);
        mIsMinMarginsHandlingCorrect = false;
    }

    protected android.print.PrintAttributes.Builder copyAttributes(PrintAttributes printattributes)
    {
        android.print.PrintAttributes.Builder builder = super.copyAttributes(printattributes);
        if(printattributes.getDuplexMode() != 0)
            builder.setDuplexMode(printattributes.getDuplexMode());
        return builder;
    }
}
