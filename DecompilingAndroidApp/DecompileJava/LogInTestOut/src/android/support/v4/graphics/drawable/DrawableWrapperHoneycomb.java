// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapperGingerbread

class DrawableWrapperHoneycomb extends DrawableWrapperGingerbread
{
    private static class DrawableWrapperStateHoneycomb extends DrawableWrapperGingerbread.DrawableWrapperState
    {

        public Drawable newDrawable(Resources resources)
        {
            return new DrawableWrapperHoneycomb(this, resources);
        }

        DrawableWrapperStateHoneycomb(DrawableWrapperGingerbread.DrawableWrapperState drawablewrapperstate, Resources resources)
        {
            super(drawablewrapperstate, resources);
        }
    }


    DrawableWrapperHoneycomb(Drawable drawable)
    {
        super(drawable);
    }

    DrawableWrapperHoneycomb(DrawableWrapperGingerbread.DrawableWrapperState drawablewrapperstate, Resources resources)
    {
        super(drawablewrapperstate, resources);
    }

    public void jumpToCurrentState()
    {
        mDrawable.jumpToCurrentState();
    }

    DrawableWrapperGingerbread.DrawableWrapperState mutateConstantState()
    {
        return new DrawableWrapperStateHoneycomb(mState, null);
    }
}
