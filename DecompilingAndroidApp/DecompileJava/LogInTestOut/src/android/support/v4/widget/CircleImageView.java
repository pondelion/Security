// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.widget.ImageView;

class CircleImageView extends ImageView
{
    private class OvalShadow extends OvalShape
    {

        private void updateRadialGradient(int i)
        {
            float f = i / 2;
            float f1 = i / 2;
            float f2 = mShadowRadius;
            android.graphics.Shader.TileMode tilemode = android.graphics.Shader.TileMode.CLAMP;
            mRadialGradient = new RadialGradient(f, f1, f2, new int[] {
                0x3d000000, 0
            }, null, tilemode);
            mShadowPaint.setShader(mRadialGradient);
        }

        public void draw(Canvas canvas, Paint paint)
        {
            int i = getWidth();
            int j = getHeight();
            canvas.drawCircle(i / 2, j / 2, i / 2, mShadowPaint);
            canvas.drawCircle(i / 2, j / 2, i / 2 - mShadowRadius, paint);
        }

        protected void onResize(float f, float f1)
        {
            super.onResize(f, f1);
            updateRadialGradient((int)f);
        }

        private RadialGradient mRadialGradient;
        private Paint mShadowPaint;
        final CircleImageView this$0;

        OvalShadow(int i)
        {
            this$0 = CircleImageView.this;
            super();
            mShadowPaint = new Paint();
            mShadowRadius = i;
            updateRadialGradient((int)rect().width());
        }
    }


    CircleImageView(Context context, int i)
    {
        super(context);
        float f = getContext().getResources().getDisplayMetrics().density;
        int j = (int)(1.75F * f);
        int k = (int)(0.0F * f);
        mShadowRadius = (int)(3.5F * f);
        if(elevationSupported())
        {
            context = new ShapeDrawable(new OvalShape());
            ViewCompat.setElevation(this, 4F * f);
        } else
        {
            context = new ShapeDrawable(new OvalShadow(mShadowRadius));
            ViewCompat.setLayerType(this, 1, context.getPaint());
            context.getPaint().setShadowLayer(mShadowRadius, k, j, 0x1e000000);
            j = mShadowRadius;
            setPadding(j, j, j, j);
        }
        context.getPaint().setColor(i);
        setBackgroundDrawable(context);
    }

    private boolean elevationSupported()
    {
        return android.os.Build.VERSION.SDK_INT >= 21;
    }

    public void onAnimationEnd()
    {
        super.onAnimationEnd();
        if(mListener != null)
            mListener.onAnimationEnd(getAnimation());
    }

    public void onAnimationStart()
    {
        super.onAnimationStart();
        if(mListener != null)
            mListener.onAnimationStart(getAnimation());
    }

    protected void onMeasure(int i, int j)
    {
        super.onMeasure(i, j);
        if(!elevationSupported())
            setMeasuredDimension(getMeasuredWidth() + mShadowRadius * 2, getMeasuredHeight() + mShadowRadius * 2);
    }

    public void setAnimationListener(android.view.animation.Animation.AnimationListener animationlistener)
    {
        mListener = animationlistener;
    }

    public void setBackgroundColor(int i)
    {
        if(getBackground() instanceof ShapeDrawable)
            ((ShapeDrawable)getBackground()).getPaint().setColor(i);
    }

    public void setBackgroundColorRes(int i)
    {
        setBackgroundColor(getContext().getResources().getColor(i));
    }

    private static final int FILL_SHADOW_COLOR = 0x3d000000;
    private static final int KEY_SHADOW_COLOR = 0x1e000000;
    private static final int SHADOW_ELEVATION = 4;
    private static final float SHADOW_RADIUS = 3.5F;
    private static final float X_OFFSET = 0F;
    private static final float Y_OFFSET = 1.75F;
    private android.view.animation.Animation.AnimationListener mListener;
    int mShadowRadius;
}
