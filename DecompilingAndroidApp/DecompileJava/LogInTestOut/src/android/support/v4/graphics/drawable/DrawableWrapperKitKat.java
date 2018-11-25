// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperHoneycomb

class DrawableWrapperKitKat extends DrawableWrapperHoneycomb
{
    private static class DrawableWrapperStateKitKat extends DrawableWrapperGingerbread.DrawableWrapperState
    {

        public Drawable newDrawable(Resources resources)
        {
            return new DrawableWrapperKitKat(this, resources);
        }

        DrawableWrapperStateKitKat(DrawableWrapperGingerbread.DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate, resources);
        }
    }


    DrawableWrapperKitKat(Drawable drawable)
    {
        super(drawable);
    }

    DrawableWrapperKitKat(DrawableWrapperGingerbread.DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        super(drawablewrapperstate, resources);
    }

    public boolean isAutoMirrored()
    {
        return mDrawable.isAutoMirrored();
    }

    DrawableWrapperGingerbread.DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateKitKat(mState, null);
    }

    public void setAutoMirrored(boolean flag)
    {
        mDrawable.setAutoMirrored(flag);
    }
}
