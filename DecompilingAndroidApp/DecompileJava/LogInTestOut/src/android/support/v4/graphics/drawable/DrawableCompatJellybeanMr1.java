// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.graphics.drawable.Drawable;
import android.util.Log;
import java.lang.reflect.Method;

class DrawableCompatJellybeanMr1
{

    DrawableCompatJellybeanMr1()
    {
    }

    public static int getLayoutDirection(Drawable drawable)
    {
        if(!sGetLayoutDirectionMethodFetched)
        {
            int i;
            try
            {
                sGetLayoutDirectionMethod = android/graphics/drawable/Drawable.getDeclaredMethod("getLayoutDirection", new Class[0]);
                sGetLayoutDirectionMethod.setAccessible(true);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve getLayoutDirection() method", nosuchmethodexception);
            }
            sGetLayoutDirectionMethodFetched = true;
        }
        if(sGetLayoutDirectionMethod == null)
            break MISSING_BLOCK_LABEL_84;
        i = ((Integer)sGetLayoutDirectionMethod.invoke(drawable, new Object[0])).intValue();
        return i;
        drawable;
        Log.i("DrawableCompatJellybeanMr1", "Failed to invoke getLayoutDirection() via reflection", drawable);
        sGetLayoutDirectionMethod = null;
        return -1;
    }

    public static boolean setLayoutDirection(Drawable drawable, int i)
    {
        if(!sSetLayoutDirectionMethodFetched)
        {
            try
            {
                sSetLayoutDirectionMethod = android/graphics/drawable/Drawable.getDeclaredMethod("setLayoutDirection", new Class[] {
                    Integer.TYPE
                });
                sSetLayoutDirectionMethod.setAccessible(true);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                Log.i("DrawableCompatJellybeanMr1", "Failed to retrieve setLayoutDirection(int) method", nosuchmethodexception);
            }
            sSetLayoutDirectionMethodFetched = true;
        }
        if(sSetLayoutDirectionMethod == null)
            break MISSING_BLOCK_LABEL_91;
        sSetLayoutDirectionMethod.invoke(drawable, new Object[] {
            Integer.valueOf(i)
        });
        return true;
        drawable;
        Log.i("DrawableCompatJellybeanMr1", "Failed to invoke setLayoutDirection(int) via reflection", drawable);
        sSetLayoutDirectionMethod = null;
        return false;
    }

    private static final String TAG = "DrawableCompatJellybeanMr1";
    private static Method sGetLayoutDirectionMethod;
    private static boolean sGetLayoutDirectionMethodFetched;
    private static Method sSetLayoutDirectionMethod;
    private static boolean sSetLayoutDirectionMethodFetched;
}
