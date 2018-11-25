// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.content.res;

import android.content.Context;
import android.content.res.*;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.*;
import java.util.WeakHashMap;

// Referenced classes of package android.support.v7.content.res:
//            AppCompatColorStateListInflater

public final class AppCompatResources
{
    private static class ColorStateListCacheEntry
    {

        final Configuration configuration;
        final ColorStateList value;

        ColorStateListCacheEntry(ColorStateList colorstatelist, Configuration configuration1)
        {
            value = colorstatelist;
            configuration = configuration1;
        }
    }


    private AppCompatResources()
    {
    }

    private static void addColorStateListToCache(Context context, int i, ColorStateList colorstatelist)
    {
        Object obj = sColorStateCacheLock;
        obj;
        JVM INSTR monitorenter ;
        SparseArray sparsearray1 = (SparseArray)sColorStateCaches.get(context);
        SparseArray sparsearray;
        sparsearray = sparsearray1;
        if(sparsearray1 != null)
            break MISSING_BLOCK_LABEL_45;
        sparsearray = new SparseArray();
        sColorStateCaches.put(context, sparsearray);
        sparsearray.append(i, new ColorStateListCacheEntry(colorstatelist, context.getResources().getConfiguration()));
        obj;
        JVM INSTR monitorexit ;
        return;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    private static ColorStateList getCachedColorStateList(Context context, int i)
    {
        Object obj = sColorStateCacheLock;
        obj;
        JVM INSTR monitorenter ;
        SparseArray sparsearray = (SparseArray)sColorStateCaches.get(context);
        if(sparsearray == null)
            break MISSING_BLOCK_LABEL_76;
        ColorStateListCacheEntry colorstatelistcacheentry;
        if(sparsearray.size() <= 0)
            break MISSING_BLOCK_LABEL_76;
        colorstatelistcacheentry = (ColorStateListCacheEntry)sparsearray.get(i);
        if(colorstatelistcacheentry == null)
            break MISSING_BLOCK_LABEL_76;
        if(!colorstatelistcacheentry.configuration.equals(context.getResources().getConfiguration()))
            break MISSING_BLOCK_LABEL_71;
        context = colorstatelistcacheentry.value;
        obj;
        JVM INSTR monitorexit ;
        return context;
        sparsearray.remove(i);
        obj;
        JVM INSTR monitorexit ;
        return null;
        context;
        obj;
        JVM INSTR monitorexit ;
        throw context;
    }

    public static ColorStateList getColorStateList(Context context, int i)
    {
        ColorStateList colorstatelist;
        if(android.os.Build.VERSION.SDK_INT >= 23)
        {
            colorstatelist = context.getColorStateList(i);
        } else
        {
            ColorStateList colorstatelist2 = getCachedColorStateList(context, i);
            colorstatelist = colorstatelist2;
            if(colorstatelist2 == null)
            {
                ColorStateList colorstatelist1 = inflateColorStateList(context, i);
                if(colorstatelist1 != null)
                {
                    addColorStateListToCache(context, i, colorstatelist1);
                    return colorstatelist1;
                } else
                {
                    return ContextCompat.getColorStateList(context, i);
                }
            }
        }
        return colorstatelist;
    }

    public static Drawable getDrawable(Context context, int i)
    {
        return AppCompatDrawableManager.get().getDrawable(context, i);
    }

    private static TypedValue getTypedValue()
    {
        TypedValue typedvalue1 = (TypedValue)TL_TYPED_VALUE.get();
        TypedValue typedvalue = typedvalue1;
        if(typedvalue1 == null)
        {
            typedvalue = new TypedValue();
            TL_TYPED_VALUE.set(typedvalue);
        }
        return typedvalue;
    }

    private static ColorStateList inflateColorStateList(Context context, int i)
    {
        if(isColorInt(context, i))
            return null;
        Resources resources = context.getResources();
        android.content.res.XmlResourceParser xmlresourceparser = resources.getXml(i);
        try
        {
            context = AppCompatColorStateListInflater.createFromXml(resources, xmlresourceparser, context.getTheme());
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            Log.e("AppCompatResources", "Failed to inflate ColorStateList, leaving it to the framework", context);
            return null;
        }
        return context;
    }

    private static boolean isColorInt(Context context, int i)
    {
        context = context.getResources();
        TypedValue typedvalue = getTypedValue();
        context.getValue(i, typedvalue, true);
        return typedvalue.type >= 28 && typedvalue.type <= 31;
    }

    private static final String LOG_TAG = "AppCompatResources";
    private static final ThreadLocal TL_TYPED_VALUE = new ThreadLocal();
    private static final Object sColorStateCacheLock = new Object();
    private static final WeakHashMap sColorStateCaches = new WeakHashMap(0);

}
