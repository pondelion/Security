// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.lang.ref.WeakReference;

// Referenced classes of package android.support.v7.widget:
//            ResourcesWrapper, AppCompatDrawableManager

class TintResources extends ResourcesWrapper
{

    public TintResources(Context context, Resources resources)
    {
        super(resources);
        mContextRef = new WeakReference(context);
    }

    public Drawable getDrawable(int i)
        throws android.content.res.Resources.NotFoundException
    {
        Drawable drawable = super.getDrawable(i);
        Context context = (Context)mContextRef.get();
        if(drawable != null && context != null)
        {
            AppCompatDrawableManager.get();
            AppCompatDrawableManager.tintDrawableUsingColorFilter(context, i, drawable);
        }
        return drawable;
    }

    private final WeakReference mContextRef;
}
