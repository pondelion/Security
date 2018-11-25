// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

class ICUCompatApi23
{

    ICUCompatApi23()
    {
    }

    public static String maximizeAndGetScript(Locale locale)
    {
        String s = ((Locale)sAddLikelySubtagsMethod.invoke(null, new Object[] {
            locale
        })).getScript();
        return s;
        Object obj;
        obj;
        Log.w("ICUCompatIcs", ((Throwable) (obj)));
_L2:
        return locale.getScript();
        obj;
        Log.w("ICUCompatIcs", ((Throwable) (obj)));
        if(true) goto _L2; else goto _L1
_L1:
    }

    private static final String TAG = "ICUCompatIcs";
    private static Method sAddLikelySubtagsMethod;

    static 
    {
        try
        {
            sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[] {
                java/util/Locale
            });
        }
        catch(Exception exception)
        {
            throw new IllegalStateException(exception);
        }
    }
}
