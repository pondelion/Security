// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.support.v7.app.AppCompatDelegate;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

// Referenced classes of package android.support.v7.widget:
//            VectorEnabledTintResources, TintResources

public class TintContextWrapper extends ContextWrapper
{

    private TintContextWrapper(Context context)
    {
        super(context);
        if(VectorEnabledTintResources.shouldBeUsed())
        {
            mResources = new VectorEnabledTintResources(this, context.getResources());
            mTheme = mResources.newTheme();
            mTheme.setTo(context.getTheme());
            return;
        } else
        {
            mResources = new TintResources(this, context.getResources());
            mTheme = null;
            return;
        }
    }

    private static boolean shouldWrap(Context context)
    {
        while((context instanceof TintContextWrapper) || (context.getResources() instanceof TintResources) || (context.getResources() instanceof VectorEnabledTintResources) || AppCompatDelegate.isCompatVectorFromResourcesEnabled() && android.os.Build.VERSION.SDK_INT > 20) 
            return false;
        return true;
    }

    public static Context wrap(Context context)
    {
        if(shouldWrap(context))
        {
            int i = 0;
            Object obj;
            for(int j = sCache.size(); i < j; i++)
            {
                obj = (WeakReference)sCache.get(i);
                if(obj != null)
                    obj = (TintContextWrapper)((WeakReference) (obj)).get();
                else
                    obj = null;
                if(obj != null && ((TintContextWrapper) (obj)).getBaseContext() == context)
                    return ((Context) (obj));
            }

            context = new TintContextWrapper(context);
            sCache.add(new WeakReference(context));
            return context;
        } else
        {
            return context;
        }
    }

    public Resources getResources()
    {
        return mResources;
    }

    public android.content.res.Resources.Theme getTheme()
    {
        if(mTheme == null)
            return super.getTheme();
        else
            return mTheme;
    }

    public void setTheme(int i)
    {
        if(mTheme == null)
        {
            super.setTheme(i);
            return;
        } else
        {
            mTheme.applyStyle(i, true);
            return;
        }
    }

    private static final ArrayList sCache = new ArrayList();
    private final Resources mResources;
    private final android.content.res.Resources.Theme mTheme;

}
