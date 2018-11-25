// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.graphics.drawable;

import android.animation.*;
import android.content.Context;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.ArrayMap;
import android.util.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.graphics.drawable:
//            VectorDrawableCommon, VectorDrawableCompat, AndroidResources

public class AnimatedVectorDrawableCompat extends VectorDrawableCommon
    implements Animatable
{
    private static class AnimatedVectorDrawableCompatState extends android.graphics.drawable.Drawable.ConstantState
    {

        public int getChangingConfigurations()
        {
            return mChangingConfigurations;
        }

        public Drawable newDrawable()
        {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }

        public Drawable newDrawable(Resources resources)
        {
            throw new IllegalStateException("No constant state support for SDK < 23.");
        }

        ArrayList mAnimators;
        int mChangingConfigurations;
        ArrayMap mTargetNameMap;
        VectorDrawableCompat mVectorDrawable;

        public AnimatedVectorDrawableCompatState(Context context, AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, android.graphics.drawable.Drawable.Callback callback, Resources resources)
        {
            if(animatedvectordrawablecompatstate != null)
            {
                mChangingConfigurations = animatedvectordrawablecompatstate.mChangingConfigurations;
                if(animatedvectordrawablecompatstate.mVectorDrawable != null)
                {
                    context = animatedvectordrawablecompatstate.mVectorDrawable.getConstantState();
                    int i;
                    int j;
                    if(resources != null)
                        mVectorDrawable = (VectorDrawableCompat)context.newDrawable(resources);
                    else
                        mVectorDrawable = (VectorDrawableCompat)context.newDrawable();
                    mVectorDrawable = (VectorDrawableCompat)mVectorDrawable.mutate();
                    mVectorDrawable.setCallback(callback);
                    mVectorDrawable.setBounds(animatedvectordrawablecompatstate.mVectorDrawable.getBounds());
                    mVectorDrawable.setAllowCaching(false);
                }
                if(animatedvectordrawablecompatstate.mAnimators != null)
                {
                    j = animatedvectordrawablecompatstate.mAnimators.size();
                    mAnimators = new ArrayList(j);
                    mTargetNameMap = new ArrayMap(j);
                    for(i = 0; i < j; i++)
                    {
                        callback = (Animator)animatedvectordrawablecompatstate.mAnimators.get(i);
                        context = callback.clone();
                        callback = (String)animatedvectordrawablecompatstate.mTargetNameMap.get(callback);
                        context.setTarget(mVectorDrawable.getTargetByName(callback));
                        mAnimators.add(context);
                        mTargetNameMap.put(context, callback);
                    }

                }
            }
        }
    }

    private static class AnimatedVectorDrawableDelegateState extends android.graphics.drawable.Drawable.ConstantState
    {

        public boolean canApplyTheme()
        {
            return mDelegateState.canApplyTheme();
        }

        public int getChangingConfigurations()
        {
            return mDelegateState.getChangingConfigurations();
        }

        public Drawable newDrawable()
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable();
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        public Drawable newDrawable(Resources resources, android.content.res.Resources.Theme theme)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat();
            animatedvectordrawablecompat.mDelegateDrawable = mDelegateState.newDrawable(resources, theme);
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            return animatedvectordrawablecompat;
        }

        private final android.graphics.drawable.Drawable.ConstantState mDelegateState;

        public AnimatedVectorDrawableDelegateState(android.graphics.drawable.Drawable.ConstantState constantstate)
        {
            mDelegateState = constantstate;
        }
    }


    AnimatedVectorDrawableCompat()
    {
        this(null, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context)
    {
        this(context, null, null);
    }

    private AnimatedVectorDrawableCompat(Context context, AnimatedVectorDrawableCompatState animatedvectordrawablecompatstate, Resources resources)
    {
        mArgbEvaluator = null;
        mCallback = new android.graphics.drawable.Drawable.Callback() {

            public void invalidateDrawable(Drawable drawable)
            {
                invalidateSelf();
            }

            public void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
            {
                scheduleSelf(runnable, l);
            }

            public void unscheduleDrawable(Drawable drawable, Runnable runnable)
            {
                unscheduleSelf(runnable);
            }

            final AnimatedVectorDrawableCompat this$0;

            
            {
                this$0 = AnimatedVectorDrawableCompat.this;
                super();
            }
        }
;
        mContext = context;
        if(animatedvectordrawablecompatstate != null)
        {
            mAnimatedVectorState = animatedvectordrawablecompatstate;
            return;
        } else
        {
            mAnimatedVectorState = new AnimatedVectorDrawableCompatState(context, animatedvectordrawablecompatstate, mCallback, resources);
            return;
        }
    }

    public static AnimatedVectorDrawableCompat create(Context context, int i)
    {
        Object obj;
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            AnimatedVectorDrawableCompat animatedvectordrawablecompat = new AnimatedVectorDrawableCompat(context);
            animatedvectordrawablecompat.mDelegateDrawable = ResourcesCompat.getDrawable(context.getResources(), i, context.getTheme());
            animatedvectordrawablecompat.mDelegateDrawable.setCallback(animatedvectordrawablecompat.mCallback);
            animatedvectordrawablecompat.mCachedConstantStateDelegate = new AnimatedVectorDrawableDelegateState(animatedvectordrawablecompat.mDelegateDrawable.getConstantState());
            return animatedvectordrawablecompat;
        }
        obj = context.getResources();
        AttributeSet attributeset;
        obj = ((Resources) (obj)).getXml(i);
        attributeset = Xml.asAttributeSet(((XmlPullParser) (obj)));
        do
            i = ((XmlPullParser) (obj)).next();
        while(i != 2 && i != 1);
        if(i != 2)
        {
            try
            {
                throw new XmlPullParserException("No start tag found");
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("AnimatedVDCompat", "parser error", context);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("AnimatedVDCompat", "parser error", context);
            }
            return null;
        }
        context = createFromXmlInner(context, context.getResources(), ((XmlPullParser) (obj)), attributeset, context.getTheme());
        return context;
    }

    public static AnimatedVectorDrawableCompat createFromXmlInner(Context context, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        context = new AnimatedVectorDrawableCompat(context);
        context.inflate(resources, xmlpullparser, attributeset, theme);
        return context;
    }

    private boolean isStarted()
    {
        ArrayList arraylist = mAnimatedVectorState.mAnimators;
        if(arraylist != null)
        {
            int j = arraylist.size();
            int i = 0;
            while(i < j) 
            {
                if(((Animator)arraylist.get(i)).isRunning())
                    return true;
                i++;
            }
        }
        return false;
    }

    static TypedArray obtainAttributes(Resources resources, android.content.res.Resources.Theme theme, AttributeSet attributeset, int ai[])
    {
        if(theme == null)
            return resources.obtainAttributes(attributeset, ai);
        else
            return theme.obtainStyledAttributes(attributeset, ai, 0, 0);
    }

    private void setupAnimatorsForTarget(String s, Animator animator)
    {
        animator.setTarget(mAnimatedVectorState.mVectorDrawable.getTargetByName(s));
        if(android.os.Build.VERSION.SDK_INT < 21)
            setupColorAnimator(animator);
        if(mAnimatedVectorState.mAnimators == null)
        {
            mAnimatedVectorState.mAnimators = new ArrayList();
            mAnimatedVectorState.mTargetNameMap = new ArrayMap();
        }
        mAnimatedVectorState.mAnimators.add(animator);
        mAnimatedVectorState.mTargetNameMap.put(animator, s);
    }

    private void setupColorAnimator(Animator animator)
    {
        if(animator instanceof AnimatorSet)
        {
            ArrayList arraylist = ((AnimatorSet)animator).getChildAnimations();
            if(arraylist != null)
            {
                for(int i = 0; i < arraylist.size(); i++)
                    setupColorAnimator((Animator)arraylist.get(i));

            }
        }
        if(animator instanceof ObjectAnimator)
        {
            animator = (ObjectAnimator)animator;
            String s = animator.getPropertyName();
            if("fillColor".equals(s) || "strokeColor".equals(s))
            {
                if(mArgbEvaluator == null)
                    mArgbEvaluator = new ArgbEvaluator();
                animator.setEvaluator(mArgbEvaluator);
            }
        }
    }

    public void applyTheme(android.content.res.Resources.Theme theme)
    {
        if(mDelegateDrawable != null)
            DrawableCompat.applyTheme(mDelegateDrawable, theme);
    }

    public boolean canApplyTheme()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.canApplyTheme(mDelegateDrawable);
        else
            return false;
    }

    public volatile void clearColorFilter()
    {
        super.clearColorFilter();
    }

    public void draw(Canvas canvas)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.draw(canvas);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.draw(canvas);
            if(isStarted())
            {
                invalidateSelf();
                return;
            }
        }
    }

    public int getAlpha()
    {
        if(mDelegateDrawable != null)
            return DrawableCompat.getAlpha(mDelegateDrawable);
        else
            return mAnimatedVectorState.mVectorDrawable.getAlpha();
    }

    public int getChangingConfigurations()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getChangingConfigurations();
        else
            return super.getChangingConfigurations() | mAnimatedVectorState.mChangingConfigurations;
    }

    public volatile ColorFilter getColorFilter()
    {
        return super.getColorFilter();
    }

    public android.graphics.drawable.Drawable.ConstantState getConstantState()
    {
        if(mDelegateDrawable != null)
            return new AnimatedVectorDrawableDelegateState(mDelegateDrawable.getConstantState());
        else
            return null;
    }

    public volatile Drawable getCurrent()
    {
        return super.getCurrent();
    }

    public int getIntrinsicHeight()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicHeight();
        else
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicHeight();
    }

    public int getIntrinsicWidth()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getIntrinsicWidth();
        else
            return mAnimatedVectorState.mVectorDrawable.getIntrinsicWidth();
    }

    public volatile int getLayoutDirection()
    {
        return super.getLayoutDirection();
    }

    public volatile int getMinimumHeight()
    {
        return super.getMinimumHeight();
    }

    public volatile int getMinimumWidth()
    {
        return super.getMinimumWidth();
    }

    public int getOpacity()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.getOpacity();
        else
            return mAnimatedVectorState.mVectorDrawable.getOpacity();
    }

    public volatile boolean getPadding(Rect rect)
    {
        return super.getPadding(rect);
    }

    public volatile int[] getState()
    {
        return super.getState();
    }

    public volatile Region getTransparentRegion()
    {
        return super.getTransparentRegion();
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset)
        throws XmlPullParserException, IOException
    {
        inflate(resources, xmlpullparser, attributeset, null);
    }

    public void inflate(Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws XmlPullParserException, IOException
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.inflate(mDelegateDrawable, resources, xmlpullparser, attributeset, theme);
        } else
        {
            int i = xmlpullparser.getEventType();
            while(i != 1) 
            {
                if(i == 2)
                {
                    Object obj = xmlpullparser.getName();
                    if("animated-vector".equals(obj))
                    {
                        obj = obtainAttributes(resources, theme, attributeset, AndroidResources.styleable_AnimatedVectorDrawable);
                        i = ((TypedArray) (obj)).getResourceId(0, 0);
                        if(i != 0)
                        {
                            VectorDrawableCompat vectordrawablecompat = VectorDrawableCompat.create(resources, i, theme);
                            vectordrawablecompat.setAllowCaching(false);
                            vectordrawablecompat.setCallback(mCallback);
                            if(mAnimatedVectorState.mVectorDrawable != null)
                                mAnimatedVectorState.mVectorDrawable.setCallback(null);
                            mAnimatedVectorState.mVectorDrawable = vectordrawablecompat;
                        }
                        ((TypedArray) (obj)).recycle();
                    } else
                    if("target".equals(obj))
                    {
label0:
                        {
                            TypedArray typedarray = resources.obtainAttributes(attributeset, AndroidResources.styleable_AnimatedVectorDrawableTarget);
                            String s = typedarray.getString(0);
                            int j = typedarray.getResourceId(1, 0);
                            if(j != 0)
                            {
                                if(mContext == null)
                                    break label0;
                                setupAnimatorsForTarget(s, AnimatorInflater.loadAnimator(mContext, j));
                            }
                            typedarray.recycle();
                        }
                    }
                }
                i = xmlpullparser.next();
            }
        }
        return;
        throw new IllegalStateException("Context can't be null when inflating animators");
    }

    public volatile boolean isAutoMirrored()
    {
        return super.isAutoMirrored();
    }

    public boolean isRunning()
    {
        if(mDelegateDrawable != null)
            return ((AnimatedVectorDrawable)mDelegateDrawable).isRunning();
        ArrayList arraylist = mAnimatedVectorState.mAnimators;
        int j = arraylist.size();
        for(int i = 0; i < j; i++)
            if(((Animator)arraylist.get(i)).isRunning())
                return true;

        return false;
    }

    public boolean isStateful()
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.isStateful();
        else
            return mAnimatedVectorState.mVectorDrawable.isStateful();
    }

    public volatile void jumpToCurrentState()
    {
        super.jumpToCurrentState();
    }

    public Drawable mutate()
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.mutate();
            return this;
        } else
        {
            throw new IllegalStateException("Mutate() is not supported for older platform");
        }
    }

    protected void onBoundsChange(Rect rect)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setBounds(rect);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setBounds(rect);
            return;
        }
    }

    protected boolean onLevelChange(int i)
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setLevel(i);
        else
            return mAnimatedVectorState.mVectorDrawable.setLevel(i);
    }

    protected boolean onStateChange(int ai[])
    {
        if(mDelegateDrawable != null)
            return mDelegateDrawable.setState(ai);
        else
            return mAnimatedVectorState.mVectorDrawable.setState(ai);
    }

    public void setAlpha(int i)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setAlpha(i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setAlpha(i);
            return;
        }
    }

    public volatile void setAutoMirrored(boolean flag)
    {
        super.setAutoMirrored(flag);
    }

    public volatile void setChangingConfigurations(int i)
    {
        super.setChangingConfigurations(i);
    }

    public volatile void setColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        super.setColorFilter(i, mode);
    }

    public void setColorFilter(ColorFilter colorfilter)
    {
        if(mDelegateDrawable != null)
        {
            mDelegateDrawable.setColorFilter(colorfilter);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setColorFilter(colorfilter);
            return;
        }
    }

    public volatile void setFilterBitmap(boolean flag)
    {
        super.setFilterBitmap(flag);
    }

    public volatile void setHotspot(float f, float f1)
    {
        super.setHotspot(f, f1);
    }

    public volatile void setHotspotBounds(int i, int j, int k, int l)
    {
        super.setHotspotBounds(i, j, k, l);
    }

    public volatile boolean setState(int ai[])
    {
        return super.setState(ai);
    }

    public void setTint(int i)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTint(mDelegateDrawable, i);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTint(i);
            return;
        }
    }

    public void setTintList(ColorStateList colorstatelist)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintList(mDelegateDrawable, colorstatelist);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintList(colorstatelist);
            return;
        }
    }

    public void setTintMode(android.graphics.PorterDuff.Mode mode)
    {
        if(mDelegateDrawable != null)
        {
            DrawableCompat.setTintMode(mDelegateDrawable, mode);
            return;
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setTintMode(mode);
            return;
        }
    }

    public boolean setVisible(boolean flag, boolean flag1)
    {
        if(mDelegateDrawable != null)
        {
            return mDelegateDrawable.setVisible(flag, flag1);
        } else
        {
            mAnimatedVectorState.mVectorDrawable.setVisible(flag, flag1);
            return super.setVisible(flag, flag1);
        }
    }

    public void start()
    {
        if(mDelegateDrawable != null)
            ((AnimatedVectorDrawable)mDelegateDrawable).start();
        else
        if(!isStarted())
        {
            ArrayList arraylist = mAnimatedVectorState.mAnimators;
            int j = arraylist.size();
            for(int i = 0; i < j; i++)
                ((Animator)arraylist.get(i)).start();

            invalidateSelf();
            return;
        }
    }

    public void stop()
    {
        if(mDelegateDrawable != null)
        {
            ((AnimatedVectorDrawable)mDelegateDrawable).stop();
        } else
        {
            ArrayList arraylist = mAnimatedVectorState.mAnimators;
            int j = arraylist.size();
            int i = 0;
            while(i < j) 
            {
                ((Animator)arraylist.get(i)).end();
                i++;
            }
        }
    }

    private static final String ANIMATED_VECTOR = "animated-vector";
    private static final boolean DBG_ANIMATION_VECTOR_DRAWABLE = false;
    private static final String LOGTAG = "AnimatedVDCompat";
    private static final String TARGET = "target";
    private AnimatedVectorDrawableCompatState mAnimatedVectorState;
    private ArgbEvaluator mArgbEvaluator;
    AnimatedVectorDrawableDelegateState mCachedConstantStateDelegate;
    final android.graphics.drawable.Drawable.Callback mCallback;
    private Context mContext;
}
