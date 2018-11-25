// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.graphics.drawable.DrawableWrapper;
import android.util.Log;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package android.support.v7.widget:
//            ThemeUtils

public class DrawableUtils
{

    private DrawableUtils()
    {
    }

    public static boolean canSafelyMutateDrawable(Drawable drawable)
    {
_L2:
        return false;
        if(android.os.Build.VERSION.SDK_INT < 15 && (drawable instanceof InsetDrawable) || android.os.Build.VERSION.SDK_INT < 15 && (drawable instanceof GradientDrawable) || android.os.Build.VERSION.SDK_INT < 17 && (drawable instanceof LayerDrawable)) goto _L2; else goto _L1
_L1:
        if(!(drawable instanceof DrawableContainer))
            break; /* Loop/switch isn't completed */
        drawable = drawable.getConstantState();
        if(!(drawable instanceof android.graphics.drawable.DrawableContainer.DrawableContainerState))
            break MISSING_BLOCK_LABEL_156;
        drawable = ((android.graphics.drawable.DrawableContainer.DrawableContainerState)drawable).getChildren();
        int j = drawable.length;
        int i = 0;
        do
        {
            if(i >= j)
                break MISSING_BLOCK_LABEL_156;
            if(!canSafelyMutateDrawable(drawable[i]))
                break;
            i++;
        } while(true);
        if(true) goto _L2; else goto _L3
_L3:
        if(drawable instanceof DrawableWrapper)
            return canSafelyMutateDrawable(((DrawableWrapper)drawable).getWrappedDrawable());
        if(drawable instanceof android.support.v7.graphics.drawable.DrawableWrapper)
            return canSafelyMutateDrawable(((android.support.v7.graphics.drawable.DrawableWrapper)drawable).getWrappedDrawable());
        if(drawable instanceof ScaleDrawable)
            return canSafelyMutateDrawable(((ScaleDrawable)drawable).getDrawable());
        return true;
    }

    static void fixDrawable(Drawable drawable)
    {
        if(android.os.Build.VERSION.SDK_INT == 21 && "android.graphics.drawable.VectorDrawable".equals(drawable.getClass().getName()))
            fixVectorDrawableTinting(drawable);
    }

    private static void fixVectorDrawableTinting(Drawable drawable)
    {
        int ai[] = drawable.getState();
        if(ai == null || ai.length == 0)
            drawable.setState(ThemeUtils.CHECKED_STATE_SET);
        else
            drawable.setState(ThemeUtils.EMPTY_STATE_SET);
        drawable.setState(ai);
    }

    public static Rect getOpticalBounds(Drawable drawable)
    {
        if(sInsetsClazz == null) goto _L2; else goto _L1
_L1:
        Object obj;
        drawable = DrawableCompat.unwrap(drawable);
        obj = drawable.getClass().getMethod("getOpticalInsets", new Class[0]).invoke(drawable, new Object[0]);
        if(obj == null) goto _L2; else goto _L3
_L3:
        int j;
        Rect rect;
        Field afield[];
        rect = new Rect();
        afield = sInsetsClazz.getFields();
        j = afield.length;
        int i = 0;
_L17:
        drawable = rect;
        if(i >= j) goto _L5; else goto _L4
_L4:
        drawable = afield[i];
        String s = drawable.getName();
        byte byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 4: default 263
    //                   -1383228885: 177
    //                   115029: 147
    //                   3317767: 132
    //                   108511772: 162;
           goto _L6 _L7 _L8 _L9 _L10
_L9:
        if(s.equals("left"))
            byte0 = 0;
          goto _L6
_L8:
        if(s.equals("top"))
            byte0 = 1;
          goto _L6
_L10:
        if(s.equals("right"))
            byte0 = 2;
          goto _L6
_L7:
        if(s.equals("bottom"))
            byte0 = 3;
          goto _L6
_L12:
        rect.left = drawable.getInt(obj);
          goto _L11
        drawable;
        Log.e("DrawableUtils", "Couldn't obtain the optical insets. Ignoring.");
_L2:
        drawable = INSETS_NONE;
_L5:
        return drawable;
_L13:
        rect.top = drawable.getInt(obj);
          goto _L11
_L14:
        rect.right = drawable.getInt(obj);
          goto _L11
_L15:
        rect.bottom = drawable.getInt(obj);
          goto _L11
_L6:
        byte0;
        JVM INSTR tableswitch 0 3: default 296
    //                   0 192
    //                   1 221
    //                   2 235
    //                   3 249;
           goto _L11 _L12 _L13 _L14 _L15
_L11:
        i++;
        if(true) goto _L17; else goto _L16
_L16:
    }

    static android.graphics.PorterDuff.Mode parseTintMode(int i, android.graphics.PorterDuff.Mode mode)
    {
        i;
        JVM INSTR tableswitch 3 16: default 72
    //                   3 74
    //                   4 72
    //                   5 78
    //                   6 72
    //                   7 72
    //                   8 72
    //                   9 82
    //                   10 72
    //                   11 72
    //                   12 72
    //                   13 72
    //                   14 86
    //                   15 90
    //                   16 94;
           goto _L1 _L2 _L1 _L3 _L1 _L1 _L1 _L4 _L1 _L1 _L1 _L1 _L5 _L6 _L7
_L1:
        return mode;
_L2:
        return android.graphics.PorterDuff.Mode.SRC_OVER;
_L3:
        return android.graphics.PorterDuff.Mode.SRC_IN;
_L4:
        return android.graphics.PorterDuff.Mode.SRC_ATOP;
_L5:
        return android.graphics.PorterDuff.Mode.MULTIPLY;
_L6:
        return android.graphics.PorterDuff.Mode.SCREEN;
_L7:
        if(android.os.Build.VERSION.SDK_INT >= 11)
            return android.graphics.PorterDuff.Mode.valueOf("ADD");
        if(true) goto _L1; else goto _L8
_L8:
    }

    public static final Rect INSETS_NONE;
    private static final String TAG = "DrawableUtils";
    private static final String VECTOR_DRAWABLE_CLAZZ_NAME = "android.graphics.drawable.VectorDrawable";
    private static Class sInsetsClazz = Class.forName("android.graphics.Insets");

    static 
    {
        INSETS_NONE = new Rect();
        if(android.os.Build.VERSION.SDK_INT < 18)
            break MISSING_BLOCK_LABEL_26;
        return;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
    }
}
