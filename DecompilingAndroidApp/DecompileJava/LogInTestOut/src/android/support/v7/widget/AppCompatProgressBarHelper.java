// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.*;
import android.graphics.drawable.*;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.AttributeSet;
import android.widget.ProgressBar;

// Referenced classes of package android.support.v7.widget:
//            TintTypedArray

class AppCompatProgressBarHelper
{

    AppCompatProgressBarHelper(ProgressBar progressbar)
    {
        mView = progressbar;
    }

    private Shape getDrawableShape()
    {
        return new RoundRectShape(new float[] {
            5F, 5F, 5F, 5F, 5F, 5F, 5F, 5F
        }, null, null);
    }

    private Drawable tileify(Drawable drawable, boolean flag)
    {
        if(!(drawable instanceof DrawableWrapper)) goto _L2; else goto _L1
_L1:
        Drawable drawable1 = ((DrawableWrapper)drawable).getWrappedDrawable();
        if(drawable1 != null)
        {
            drawable1 = tileify(drawable1, flag);
            ((DrawableWrapper)drawable).setWrappedDrawable(drawable1);
        }
_L4:
        return drawable;
_L2:
        int i;
        int j;
        LayerDrawable layerdrawable;
        LayerDrawable layerdrawable1;
        if(!(drawable instanceof LayerDrawable))
            continue; /* Loop/switch isn't completed */
        layerdrawable1 = (LayerDrawable)drawable;
        j = layerdrawable1.getNumberOfLayers();
        drawable = new Drawable[j];
        i = 0;
        while(i < j) 
        {
            int k = layerdrawable1.getId(i);
            Drawable drawable2 = layerdrawable1.getDrawable(i);
            if(k == 0x102000d || k == 0x102000f)
                flag = true;
            else
                flag = false;
            drawable[i] = tileify(drawable2, flag);
            i++;
        }
        layerdrawable = new LayerDrawable(drawable);
        i = 0;
_L5:
        drawable = layerdrawable;
        if(i >= j) goto _L4; else goto _L3
_L3:
        layerdrawable.setId(i, layerdrawable1.getId(i));
        i++;
          goto _L5
        if(!(drawable instanceof BitmapDrawable)) goto _L4; else goto _L6
_L6:
        drawable = (BitmapDrawable)drawable;
        Object obj = drawable.getBitmap();
        if(mSampleTile == null)
            mSampleTile = ((Bitmap) (obj));
        ShapeDrawable shapedrawable = new ShapeDrawable(getDrawableShape());
        obj = new BitmapShader(((Bitmap) (obj)), android.graphics.Shader.TileMode.REPEAT, android.graphics.Shader.TileMode.CLAMP);
        shapedrawable.getPaint().setShader(((android.graphics.Shader) (obj)));
        shapedrawable.getPaint().setColorFilter(drawable.getPaint().getColorFilter());
        drawable = shapedrawable;
        if(flag)
            drawable = new ClipDrawable(shapedrawable, 3, 1);
        return drawable;
    }

    private Drawable tileifyIndeterminate(Drawable drawable)
    {
        Object obj = drawable;
        if(drawable instanceof AnimationDrawable)
        {
            drawable = (AnimationDrawable)drawable;
            int j = drawable.getNumberOfFrames();
            obj = new AnimationDrawable();
            ((AnimationDrawable) (obj)).setOneShot(drawable.isOneShot());
            for(int i = 0; i < j; i++)
            {
                Drawable drawable1 = tileify(drawable.getFrame(i), true);
                drawable1.setLevel(10000);
                ((AnimationDrawable) (obj)).addFrame(drawable1, drawable.getDuration(i));
            }

            ((AnimationDrawable) (obj)).setLevel(10000);
        }
        return ((Drawable) (obj));
    }

    Bitmap getSampleTime()
    {
        return mSampleTile;
    }

    void loadFromAttributes(AttributeSet attributeset, int i)
    {
        attributeset = TintTypedArray.obtainStyledAttributes(mView.getContext(), attributeset, TINT_ATTRS, i, 0);
        Drawable drawable = attributeset.getDrawableIfKnown(0);
        if(drawable != null)
            mView.setIndeterminateDrawable(tileifyIndeterminate(drawable));
        drawable = attributeset.getDrawableIfKnown(1);
        if(drawable != null)
            mView.setProgressDrawable(tileify(drawable, false));
        attributeset.recycle();
    }

    private static final int TINT_ATTRS[] = {
        0x101013b, 0x101013c
    };
    private Bitmap mSampleTile;
    private final ProgressBar mView;

}
