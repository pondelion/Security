// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            TintAwareDrawable, DrawableWrapperKitKat

class DrawableCompatKitKat
{

    DrawableCompatKitKat()
    {
    }

    public static int getAlpha(Drawable drawable)
    {
        return drawable.getAlpha();
    }

    public static boolean isAutoMirrored(Drawable drawable)
    {
        return drawable.isAutoMirrored();
    }

    public static void setAutoMirrored(Drawable drawable, boolean flag)
    {
        drawable.setAutoMirrored(flag);
    }

    public static Drawable wrapForTinting(Drawable drawable)
    {
        Object obj = drawable;
        if(!(drawable instanceof TintAwareDrawable))
            obj = new DrawableWrapperKitKat(drawable);
        return ((Drawable) (obj));
    }
}
