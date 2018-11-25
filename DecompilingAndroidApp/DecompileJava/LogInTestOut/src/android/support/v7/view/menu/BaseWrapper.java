// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;


class BaseWrapper
{

    BaseWrapper(Object obj)
    {
        if(obj == null)
        {
            throw new IllegalArgumentException("Wrapped Object can not be null.");
        } else
        {
            mWrappedObject = obj;
            return;
        }
    }

    public Object getWrappedObject()
    {
        return mWrappedObject;
    }

    final Object mWrappedObject;
}
