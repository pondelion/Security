// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.ColorUtils;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.util.*;
import android.support.v7.content.res.AppCompatResources;
import android.util.*;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v7.widget:
//            ThemeUtils, DrawableUtils, TintInfo, VectorEnabledTintResources

public final class AppCompatDrawableManager
{
    private static class AvdcInflateDelegate
        implements InflateDelegate
    {

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            try
            {
                context = AnimatedVectorDrawableCompat.createFromXmlInner(context, context.getResources(), xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", context);
                return null;
            }
            return context;
        }

        AvdcInflateDelegate()
        {
        }
    }

    private static class ColorFilterLruCache extends LruCache
    {

        private static int generateCacheKey(int i, android.graphics.PorterDuff.Mode mode)
        {
            return (i + 31) * 31 + mode.hashCode();
        }

        PorterDuffColorFilter get(int i, android.graphics.PorterDuff.Mode mode)
        {
            return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(i, mode)));
        }

        PorterDuffColorFilter put(int i, android.graphics.PorterDuff.Mode mode, PorterDuffColorFilter porterduffcolorfilter)
        {
            return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(i, mode)), porterduffcolorfilter);
        }

        public ColorFilterLruCache(int i)
        {
            LruCache(i);
        }
    }

    private static interface InflateDelegate
    {

        public abstract Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme);
    }

    private static class VdcInflateDelegate
        implements InflateDelegate
    {

        public Drawable createFromXmlInner(Context context, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        {
            try
            {
                context = VectorDrawableCompat.createFromXmlInner(context.getResources(), xmlpullparser, attributeset, theme);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                Log.e("VdcInflateDelegate", "Exception while inflating <vector>", context);
                return null;
            }
            return context;
        }

        VdcInflateDelegate()
        {
        }
    }


    public AppCompatDrawableManager()
    {
    }

    private void addDelegate(String s, InflateDelegate inflatedelegate)
    {
        if(mDelegates == null)
            mDelegates = new ArrayMap();
        mDelegates.put(s, inflatedelegate);
    }

    private boolean addDrawableToCache(Context context, long l, Drawable drawable)
    {
        android.graphics.drawable.Drawable.ConstantState constantstate;
        constantstate = drawable.getConstantState();
        if(constantstate == null)
            break MISSING_BLOCK_LABEL_89;
        Object obj = mDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDrawableCaches.get(context);
        drawable = longsparsearray;
        if(longsparsearray != null)
            break MISSING_BLOCK_LABEL_63;
        drawable = new LongSparseArray();
        mDrawableCaches.put(context, drawable);
        drawable.put(l, new WeakReference(constantstate));
        obj;
        JVM INSTR monitorexit ;
        return true;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        return false;
    }

    private void addTintListToCache(Context context, int i, ColorStateList colorstatelist)
    {
        if(mTintLists == null)
            mTintLists = new WeakHashMap();
        SparseArray sparsearray1 = (SparseArray)mTintLists.get(context);
        SparseArray sparsearray = sparsearray1;
        if(sparsearray1 == null)
        {
            sparsearray = new SparseArray();
            mTintLists.put(context, sparsearray);
        }
        sparsearray.append(i, colorstatelist);
    }

    private static boolean arrayContains(int ai[], int i)
    {
        boolean flag1 = false;
        int k = ai.length;
        int j = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if(j < k)
                {
                    if(ai[j] != i)
                        break label0;
                    flag = true;
                }
                return flag;
            }
            j++;
        } while(true);
    }

    private void checkVectorDrawableSetup(Context context)
    {
        if(!mHasCheckedVectorDrawableSetup)
        {
            mHasCheckedVectorDrawableSetup = true;
            context = getDrawable(context, android.support.v7.appcompat.R.drawable.abc_vector_test);
            if(context == null || !isVectorDrawable(context))
            {
                mHasCheckedVectorDrawableSetup = false;
                throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
            }
        }
    }

    private ColorStateList createBorderlessButtonColorStateList(Context context, ColorStateList colorstatelist)
    {
        return createButtonColorStateList(context, 0, null);
    }

    private ColorStateList createButtonColorStateList(Context context, int i, ColorStateList colorstatelist)
    {
        int ai[][] = new int[4][];
        int ai1[] = new int[4];
        int k = ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlHighlight);
        int j = ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorButtonNormal);
        ai[0] = ThemeUtils.DISABLED_STATE_SET;
        int l;
        if(colorstatelist != null)
            j = colorstatelist.getColorForState(ai[0], 0);
        ai1[0] = j;
        l = 0 + 1;
        ai[l] = ThemeUtils.PRESSED_STATE_SET;
        if(colorstatelist == null)
            j = i;
        else
            j = colorstatelist.getColorForState(ai[l], 0);
        ai1[l] = ColorUtils.compositeColors(k, j);
        l++;
        ai[l] = ThemeUtils.FOCUSED_STATE_SET;
        if(colorstatelist == null)
            j = i;
        else
            j = colorstatelist.getColorForState(ai[l], 0);
        ai1[l] = ColorUtils.compositeColors(k, j);
        j = l + 1;
        ai[j] = ThemeUtils.EMPTY_STATE_SET;
        if(colorstatelist != null)
            i = colorstatelist.getColorForState(ai[j], 0);
        ai1[j] = i;
        return new ColorStateList(ai, ai1);
    }

    private static long createCacheKey(TypedValue typedvalue)
    {
        return (long)typedvalue.assetCookie << 32 | (long)typedvalue.data;
    }

    private ColorStateList createColoredButtonColorStateList(Context context, ColorStateList colorstatelist)
    {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorAccent), colorstatelist);
    }

    private ColorStateList createDefaultButtonColorStateList(Context context, ColorStateList colorstatelist)
    {
        return createButtonColorStateList(context, ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorButtonNormal), colorstatelist);
    }

    private Drawable createDrawableIfNeeded(Context context, int i)
    {
        if(mTypedValue == null)
            mTypedValue = new TypedValue();
        TypedValue typedvalue = mTypedValue;
        context.getResources().getValue(i, typedvalue, true);
        long l = createCacheKey(typedvalue);
        Object obj = getCachedDrawable(context, l);
        if(obj != null)
            return ((Drawable) (obj));
        if(i == android.support.v7.appcompat.R.drawable.abc_cab_background_top_material)
            obj = new LayerDrawable(new Drawable[] {
                getDrawable(context, android.support.v7.appcompat.R.drawable.abc_cab_background_internal_bg), getDrawable(context, android.support.v7.appcompat.R.drawable.abc_cab_background_top_mtrl_alpha)
            });
        if(obj != null)
        {
            ((Drawable) (obj)).setChangingConfigurations(typedvalue.changingConfigurations);
            addDrawableToCache(context, l, ((Drawable) (obj)));
        }
        return ((Drawable) (obj));
    }

    private static PorterDuffColorFilter createTintFilter(ColorStateList colorstatelist, android.graphics.PorterDuff.Mode mode, int ai[])
    {
        if(colorstatelist == null || mode == null)
            return null;
        else
            return getPorterDuffColorFilter(colorstatelist.getColorForState(ai, 0), mode);
    }

    public static AppCompatDrawableManager get()
    {
        if(INSTANCE == null)
        {
            INSTANCE = new AppCompatDrawableManager();
            installDefaultInflateDelegates(INSTANCE);
        }
        return INSTANCE;
    }

    private Drawable getCachedDrawable(Context context, long l)
    {
        Object obj = mDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        LongSparseArray longsparsearray = (LongSparseArray)mDrawableCaches.get(context);
        if(longsparsearray != null)
            break MISSING_BLOCK_LABEL_32;
        obj;
        JVM INSTR monitorexit ;
        return null;
        Object obj1 = (WeakReference)longsparsearray.get(l);
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_90;
        obj1 = (android.graphics.drawable.Drawable.ConstantState)((WeakReference) (obj1)).get();
        if(obj1 == null)
            break MISSING_BLOCK_LABEL_84;
        context = ((android.graphics.drawable.Drawable.ConstantState) (obj1)).newDrawable(context.getResources());
        obj;
        JVM INSTR monitorexit ;
        return context;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
        longsparsearray.delete(l);
        obj;
        JVM INSTR monitorexit ;
        return null;
    }

    public static PorterDuffColorFilter getPorterDuffColorFilter(int i, android.graphics.PorterDuff.Mode mode)
    {
        PorterDuffColorFilter porterduffcolorfilter1 = COLOR_FILTER_CACHE.get(i, mode);
        PorterDuffColorFilter porterduffcolorfilter = porterduffcolorfilter1;
        if(porterduffcolorfilter1 == null)
        {
            porterduffcolorfilter = new PorterDuffColorFilter(i, mode);
            COLOR_FILTER_CACHE.put(i, mode, porterduffcolorfilter);
        }
        return porterduffcolorfilter;
    }

    private ColorStateList getTintListFromCache(Context context, int i)
    {
        Object obj = null;
        ColorStateList colorstatelist = obj;
        if(mTintLists != null)
        {
            context = (SparseArray)mTintLists.get(context);
            colorstatelist = obj;
            if(context != null)
                colorstatelist = (ColorStateList)context.get(i);
        }
        return colorstatelist;
    }

    static android.graphics.PorterDuff.Mode getTintMode(int i)
    {
        android.graphics.PorterDuff.Mode mode = null;
        if(i == android.support.v7.appcompat.R.drawable.abc_switch_thumb_material)
            mode = android.graphics.PorterDuff.Mode.MULTIPLY;
        return mode;
    }

    private static void installDefaultInflateDelegates(AppCompatDrawableManager appcompatdrawablemanager)
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i < 23)
        {
            appcompatdrawablemanager.addDelegate("vector", new VdcInflateDelegate());
            if(i >= 11)
                appcompatdrawablemanager.addDelegate("animated-vector", new AvdcInflateDelegate());
        }
    }

    private static boolean isVectorDrawable(Drawable drawable)
    {
        return (drawable instanceof VectorDrawableCompat) || "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName());
    }

    private Drawable loadDrawableFromDelegates(Context context, int i)
    {
        if(mDelegates == null || mDelegates.isEmpty())
            break MISSING_BLOCK_LABEL_408;
        if(mKnownDrawableIdTags == null) goto _L2; else goto _L1
_L1:
        Object obj = (String)mKnownDrawableIdTags.get(i);
        if(!"appcompat_skip_skip".equals(obj) && (obj == null || mDelegates.get(obj) != null)) goto _L4; else goto _L3
_L3:
        Object obj1 = null;
_L8:
        return ((Drawable) (obj1));
_L2:
        mKnownDrawableIdTags = new SparseArray();
_L4:
        long l;
        Drawable drawable;
        TypedValue typedvalue;
        Object obj2;
        if(mTypedValue == null)
            mTypedValue = new TypedValue();
        typedvalue = mTypedValue;
        obj2 = context.getResources();
        ((Resources) (obj2)).getValue(i, typedvalue, true);
        l = createCacheKey(typedvalue);
        obj = getCachedDrawable(context, l);
        obj1 = obj;
        if(obj != null)
            continue; /* Loop/switch isn't completed */
        drawable = ((Drawable) (obj));
        if(typedvalue.string == null)
            break MISSING_BLOCK_LABEL_256;
        drawable = ((Drawable) (obj));
        if(!typedvalue.string.toString().endsWith(".xml"))
            break MISSING_BLOCK_LABEL_256;
        drawable = ((Drawable) (obj));
        obj2 = ((Resources) (obj2)).getXml(i);
        drawable = ((Drawable) (obj));
        AttributeSet attributeset = Xml.asAttributeSet(((XmlPullParser) (obj2)));
_L6:
        drawable = ((Drawable) (obj));
        int j = ((XmlPullParser) (obj2)).next();
        if(j != 2 && j != 1) goto _L6; else goto _L5
_L5:
        if(j == 2)
            break; /* Loop/switch isn't completed */
        drawable = ((Drawable) (obj));
        try
        {
            throw new XmlPullParserException("No start tag found");
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("AppCompatDrawableManager", "Exception while inflating drawable", context);
        }
_L10:
        obj1 = drawable;
        if(drawable == null)
        {
            mKnownDrawableIdTags.append(i, "appcompat_skip_skip");
            return drawable;
        }
        if(true) goto _L8; else goto _L7
_L7:
        drawable = ((Drawable) (obj));
        obj1 = ((XmlPullParser) (obj2)).getName();
        drawable = ((Drawable) (obj));
        mKnownDrawableIdTags.append(i, obj1);
        drawable = ((Drawable) (obj));
        InflateDelegate inflatedelegate = (InflateDelegate)mDelegates.get(obj1);
        obj1 = obj;
        if(inflatedelegate == null)
            break MISSING_BLOCK_LABEL_354;
        drawable = ((Drawable) (obj));
        obj1 = inflatedelegate.createFromXmlInner(context, ((XmlPullParser) (obj2)), attributeset, context.getTheme());
        drawable = ((Drawable) (obj1));
        if(obj1 == null) goto _L10; else goto _L9
_L9:
        drawable = ((Drawable) (obj1));
        ((Drawable) (obj1)).setChangingConfigurations(typedvalue.changingConfigurations);
        drawable = ((Drawable) (obj1));
        boolean flag = addDrawableToCache(context, l, ((Drawable) (obj1)));
        drawable = ((Drawable) (obj1));
        if(flag)
            drawable = ((Drawable) (obj1));
          goto _L10
        return null;
    }

    private void removeDelegate(String s, InflateDelegate inflatedelegate)
    {
        if(mDelegates != null && mDelegates.get(s) == inflatedelegate)
            mDelegates.remove(s);
    }

    private static void setPorterDuffColorFilter(Drawable drawable, int i, android.graphics.PorterDuff.Mode mode)
    {
        Drawable drawable1 = drawable;
        if(DrawableUtils.canSafelyMutateDrawable(drawable))
            drawable1 = drawable.mutate();
        drawable = mode;
        if(mode == null)
            drawable = DEFAULT_MODE;
        drawable1.setColorFilter(getPorterDuffColorFilter(i, drawable));
    }

    private Drawable tintDrawable(Context context, int i, boolean flag, Drawable drawable)
    {
        Object obj = getTintList(context, i);
        if(obj != null)
        {
            context = drawable;
            if(DrawableUtils.canSafelyMutateDrawable(drawable))
                context = drawable.mutate();
            context = DrawableCompat.wrap(context);
            DrawableCompat.setTintList(context, ((ColorStateList) (obj)));
            drawable = getTintMode(i);
            obj = context;
            if(drawable != null)
            {
                DrawableCompat.setTintMode(context, drawable);
                obj = context;
            }
        } else
        {
            if(i == android.support.v7.appcompat.R.drawable.abc_seekbar_track_material)
            {
                obj = (LayerDrawable)drawable;
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x1020000), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000f), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000d), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
                return drawable;
            }
            if(i == android.support.v7.appcompat.R.drawable.abc_ratingbar_material || i == android.support.v7.appcompat.R.drawable.abc_ratingbar_indicator_material || i == android.support.v7.appcompat.R.drawable.abc_ratingbar_small_material)
            {
                obj = (LayerDrawable)drawable;
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x1020000), ThemeUtils.getDisabledThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlNormal), DEFAULT_MODE);
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000f), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
                setPorterDuffColorFilter(((LayerDrawable) (obj)).findDrawableByLayerId(0x102000d), ThemeUtils.getThemeAttrColor(context, android.support.v7.appcompat.R.attr.colorControlActivated), DEFAULT_MODE);
                return drawable;
            }
            obj = drawable;
            if(!tintDrawableUsingColorFilter(context, i, drawable))
            {
                obj = drawable;
                if(flag)
                    return null;
            }
        }
        return ((Drawable) (obj));
    }

    static void tintDrawable(Drawable drawable, TintInfo tintinfo, int ai[])
    {
        if(DrawableUtils.canSafelyMutateDrawable(drawable) && drawable.mutate() != drawable)
        {
            Log.d("AppCompatDrawableManager", "Mutated drawable is not the same instance as the input.");
        } else
        {
            if(tintinfo.mHasTintList || tintinfo.mHasTintMode)
            {
                ColorStateList colorstatelist;
                if(tintinfo.mHasTintList)
                    colorstatelist = tintinfo.mTintList;
                else
                    colorstatelist = null;
                if(tintinfo.mHasTintMode)
                    tintinfo = tintinfo.mTintMode;
                else
                    tintinfo = DEFAULT_MODE;
                drawable.setColorFilter(createTintFilter(colorstatelist, tintinfo, ai));
            } else
            {
                drawable.clearColorFilter();
            }
            if(android.os.Build.VERSION.SDK_INT <= 23)
            {
                drawable.invalidateSelf();
                return;
            }
        }
    }

    static boolean tintDrawableUsingColorFilter(Context context, int i, Drawable drawable)
    {
        Object obj1 = DEFAULT_MODE;
        boolean flag = false;
        int j = 0;
        byte byte0 = -1;
        int k;
        Object obj;
        if(arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, i))
        {
            j = android.support.v7.appcompat.R.attr.colorControlNormal;
            flag = true;
            obj = obj1;
            k = byte0;
        } else
        if(arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, i))
        {
            j = android.support.v7.appcompat.R.attr.colorControlActivated;
            flag = true;
            k = byte0;
            obj = obj1;
        } else
        if(arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, i))
        {
            j = 0x1010031;
            flag = true;
            obj = android.graphics.PorterDuff.Mode.MULTIPLY;
            k = byte0;
        } else
        if(i == android.support.v7.appcompat.R.drawable.abc_list_divider_mtrl_alpha)
        {
            j = 0x1010030;
            flag = true;
            k = Math.round(40.8F);
            obj = obj1;
        } else
        {
            k = byte0;
            obj = obj1;
            if(i == android.support.v7.appcompat.R.drawable.abc_dialog_material_background)
            {
                j = 0x1010031;
                flag = true;
                k = byte0;
                obj = obj1;
            }
        }
        if(flag)
        {
            obj1 = drawable;
            if(DrawableUtils.canSafelyMutateDrawable(drawable))
                obj1 = drawable.mutate();
            ((Drawable) (obj1)).setColorFilter(getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(context, j), ((android.graphics.PorterDuff.Mode) (obj))));
            if(k != -1)
                ((Drawable) (obj1)).setAlpha(k);
            return true;
        } else
        {
            return false;
        }
    }

    public Drawable getDrawable(Context context, int i)
    {
        return getDrawable(context, i, false);
    }

    Drawable getDrawable(Context context, int i, boolean flag)
    {
        checkVectorDrawableSetup(context);
        Drawable drawable1 = loadDrawableFromDelegates(context, i);
        Drawable drawable = drawable1;
        if(drawable1 == null)
            drawable = createDrawableIfNeeded(context, i);
        drawable1 = drawable;
        if(drawable == null)
            drawable1 = ContextCompat.getDrawable(context, i);
        drawable = drawable1;
        if(drawable1 != null)
            drawable = tintDrawable(context, i, flag, drawable1);
        if(drawable != null)
            DrawableUtils.fixDrawable(drawable);
        return drawable;
    }

    ColorStateList getTintList(Context context, int i)
    {
        return getTintList(context, i, null);
    }

    ColorStateList getTintList(Context context, int i, ColorStateList colorstatelist)
    {
        ColorStateList colorstatelist1;
        boolean flag;
        ColorStateList colorstatelist2;
        if(colorstatelist == null)
            flag = true;
        else
            flag = false;
        if(flag)
            colorstatelist1 = getTintListFromCache(context, i);
        else
            colorstatelist1 = null;
        colorstatelist2 = colorstatelist1;
        if(colorstatelist1 != null) goto _L2; else goto _L1
_L1:
        if(i != android.support.v7.appcompat.R.drawable.abc_edit_text_material) goto _L4; else goto _L3
_L3:
        colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_edittext);
_L6:
        colorstatelist2 = colorstatelist;
        if(flag)
        {
            colorstatelist2 = colorstatelist;
            if(colorstatelist != null)
            {
                addTintListToCache(context, i, colorstatelist);
                colorstatelist2 = colorstatelist;
            }
        }
_L2:
        return colorstatelist2;
_L4:
        if(i == android.support.v7.appcompat.R.drawable.abc_switch_track_mtrl_alpha)
            colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_switch_track);
        else
        if(i == android.support.v7.appcompat.R.drawable.abc_switch_thumb_material)
            colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_switch_thumb);
        else
        if(i == android.support.v7.appcompat.R.drawable.abc_btn_default_mtrl_shape)
            colorstatelist = createDefaultButtonColorStateList(context, colorstatelist);
        else
        if(i == android.support.v7.appcompat.R.drawable.abc_btn_borderless_material)
            colorstatelist = createBorderlessButtonColorStateList(context, colorstatelist);
        else
        if(i == android.support.v7.appcompat.R.drawable.abc_btn_colored_material)
            colorstatelist = createColoredButtonColorStateList(context, colorstatelist);
        else
        if(i == android.support.v7.appcompat.R.drawable.abc_spinner_mtrl_am_alpha || i == android.support.v7.appcompat.R.drawable.abc_spinner_textfield_background_material)
            colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_spinner);
        else
        if(arrayContains(TINT_COLOR_CONTROL_NORMAL, i))
            colorstatelist = ThemeUtils.getThemeAttrColorStateList(context, android.support.v7.appcompat.R.attr.colorControlNormal);
        else
        if(arrayContains(TINT_COLOR_CONTROL_STATE_LIST, i))
            colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_default);
        else
        if(arrayContains(TINT_CHECKABLE_BUTTON_LIST, i))
        {
            colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_btn_checkable);
        } else
        {
            colorstatelist = colorstatelist1;
            if(i == android.support.v7.appcompat.R.drawable.abc_seekbar_thumb_material)
                colorstatelist = AppCompatResources.getColorStateList(context, android.support.v7.appcompat.R.color.abc_tint_seek_thumb);
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void onConfigurationChanged(Context context)
    {
        Object obj = mDrawableCacheLock;
        obj;
        JVM INSTR monitorenter ;
        context = (LongSparseArray)mDrawableCaches.get(context);
        if(context == null)
            break MISSING_BLOCK_LABEL_27;
        context.clear();
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    Drawable onDrawableLoadedFromResources(Context context, VectorEnabledTintResources vectorenabledtintresources, int i)
    {
        Drawable drawable1 = loadDrawableFromDelegates(context, i);
        Drawable drawable = drawable1;
        if(drawable1 == null)
            drawable = vectorenabledtintresources.superGetDrawable(i);
        if(drawable != null)
            return tintDrawable(context, i, false, drawable);
        else
            return null;
    }

    private static final int COLORFILTER_COLOR_BACKGROUND_MULTIPLY[];
    private static final int COLORFILTER_COLOR_CONTROL_ACTIVATED[];
    private static final int COLORFILTER_TINT_COLOR_CONTROL_NORMAL[];
    private static final ColorFilterLruCache COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    private static final boolean DEBUG = false;
    private static final android.graphics.PorterDuff.Mode DEFAULT_MODE;
    private static AppCompatDrawableManager INSTANCE;
    private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
    private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
    private static final String TAG = "AppCompatDrawableManager";
    private static final int TINT_CHECKABLE_BUTTON_LIST[];
    private static final int TINT_COLOR_CONTROL_NORMAL[];
    private static final int TINT_COLOR_CONTROL_STATE_LIST[];
    private ArrayMap mDelegates;
    private final Object mDrawableCacheLock = new Object();
    private final WeakHashMap mDrawableCaches = new WeakHashMap(0);
    private boolean mHasCheckedVectorDrawableSetup;
    private SparseArray mKnownDrawableIdTags;
    private WeakHashMap mTintLists;
    private TypedValue mTypedValue;

    static 
    {
        DEFAULT_MODE = android.graphics.PorterDuff.Mode.SRC_IN;
        COLORFILTER_TINT_COLOR_CONTROL_NORMAL = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_textfield_search_default_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_textfield_default_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ab_share_pack_mtrl_alpha
        });
        TINT_COLOR_CONTROL_NORMAL = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_ic_commit_search_api_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_seekbar_tick_mark_material, android.support.v7.appcompat.R.drawable.abc_ic_menu_share_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_copy_mtrl_am_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_cut_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_selectall_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_ic_menu_paste_mtrl_am_alpha
        });
        COLORFILTER_COLOR_CONTROL_ACTIVATED = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_textfield_activated_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_textfield_search_activated_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_cab_background_top_mtrl_alpha, android.support.v7.appcompat.R.drawable.abc_text_cursor_material, android.support.v7.appcompat.R.drawable.abc_text_select_handle_left_mtrl_dark, android.support.v7.appcompat.R.drawable.abc_text_select_handle_middle_mtrl_dark, android.support.v7.appcompat.R.drawable.abc_text_select_handle_right_mtrl_dark, android.support.v7.appcompat.R.drawable.abc_text_select_handle_left_mtrl_light, android.support.v7.appcompat.R.drawable.abc_text_select_handle_middle_mtrl_light, android.support.v7.appcompat.R.drawable.abc_text_select_handle_right_mtrl_light
        });
        COLORFILTER_COLOR_BACKGROUND_MULTIPLY = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_popup_background_mtrl_mult, android.support.v7.appcompat.R.drawable.abc_cab_background_internal_bg, android.support.v7.appcompat.R.drawable.abc_menu_hardkey_panel_mtrl_mult
        });
        TINT_COLOR_CONTROL_STATE_LIST = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_tab_indicator_material, android.support.v7.appcompat.R.drawable.abc_textfield_search_material
        });
        TINT_CHECKABLE_BUTTON_LIST = (new int[] {
            android.support.v7.appcompat.R.drawable.abc_btn_check_material, android.support.v7.appcompat.R.drawable.abc_btn_radio_material
        });
    }
}
