// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

class DrawableCompatApi23
{

    DrawableCompatApi23()
    {
    }

    public static int getLayoutDirection(Drawable drawable)
    {
        return drawable.getLayoutDirection();
    }

    public static boolean setLayoutDirection(Drawable drawable, int i)
    {
        return drawable.setLayoutDirection(i);
    }
}
