// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.text;

import java.util.Locale;

// Referenced classes of package android.support.v4.text:
//            ICUCompatIcs, ICUCompatApi23

public final class ICUCompat
{
    static interface ICUCompatImpl
    {

        public abstract String maximizeAndGetScript(Locale locale);
    }

    static class ICUCompatImplBase
        implements ICUCompatImpl
    {

        public String maximizeAndGetScript(Locale locale)
        {
            return null;
        }

        ICUCompatImplBase()
        {
        }
    }

    static class ICUCompatImplIcs
        implements ICUCompatImpl
    {

        public String maximizeAndGetScript(Locale locale)
        {
            return ICUCompatIcs.maximizeAndGetScript(locale);
        }

        ICUCompatImplIcs()
        {
        }
    }

    static class ICUCompatImplLollipop
        implements ICUCompatImpl
    {

        public String maximizeAndGetScript(Locale locale)
        {
            return ICUCompatApi23.maximizeAndGetScript(locale);
        }

        ICUCompatImplLollipop()
        {
        }
    }


    private ICUCompat()
    {
    }

    public static String maximizeAndGetScript(Locale locale)
    {
        return IMPL.maximizeAndGetScript(locale);
    }

    private static final ICUCompatImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 21)
            IMPL = new ICUCompatImplLollipop();
        else
        if(i >= 14)
            IMPL = new ICUCompatImplIcs();
        else
            IMPL = new ICUCompatImplBase();
    }
}
