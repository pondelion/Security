// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.view;

import android.view.LayoutInflater;

// Referenced classes of package android.support.v4.view:
//            LayoutInflaterFactory, LayoutInflaterCompatBase, LayoutInflaterCompatHC, LayoutInflaterCompatLollipop

public final class LayoutInflaterCompat
{
    static interface LayoutInflaterCompatImpl
    {

        public abstract LayoutInflaterFactory getFactory(LayoutInflater layoutinflater);

        public abstract void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory);
    }

    static class LayoutInflaterCompatImplBase
        implements LayoutInflaterCompatImpl
    {

        public LayoutInflaterFactory getFactory(LayoutInflater layoutinflater)
        {
            return LayoutInflaterCompatBase.getFactory(layoutinflater);
        }

        public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
        {
            LayoutInflaterCompatBase.setFactory(layoutinflater, layoutinflaterfactory);
        }

        LayoutInflaterCompatImplBase()
        {
        }
    }

    static class LayoutInflaterCompatImplV11 extends LayoutInflaterCompatImplBase
    {

        public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
        {
            LayoutInflaterCompatHC.setFactory(layoutinflater, layoutinflaterfactory);
        }

        LayoutInflaterCompatImplV11()
        {
        }
    }

    static class LayoutInflaterCompatImplV21 extends LayoutInflaterCompatImplV11
    {

        public void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
        {
            LayoutInflaterCompatLollipop.setFactory(layoutinflater, layoutinflaterfactory);
        }

        LayoutInflaterCompatImplV21()
        {
        }
    }


    private LayoutInflaterCompat()
    {
    }

    public static LayoutInflaterFactory getFactory(LayoutInflater layoutinflater)
    {
        return IMPL.getFactory(layoutinflater);
    }

    public static void setFactory(LayoutInflater layoutinflater, LayoutInflaterFactory layoutinflaterfactory)
    {
        IMPL.setFactory(layoutinflater, layoutinflaterfactory);
    }

    static final LayoutInflaterCompatImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 21)
            IMPL = new LayoutInflaterCompatImplV21();
        else
        if(i >= 11)
            IMPL = new LayoutInflaterCompatImplV11();
        else
            IMPL = new LayoutInflaterCompatImplBase();
    }
}
