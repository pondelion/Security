// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content.res;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package android.support.v4.content.res:
//            ResourcesCompatApi23, ResourcesCompatApi21, ResourcesCompatIcsMr1

public final class ResourcesCompat
{

    private ResourcesCompat()
    {
    }

    public static int getColor(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return ResourcesCompatApi23.getColor(resources, i, theme);
        else
            return resources.getColor(i);
    }

    public static ColorStateList getColorStateList(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 23)
            return ResourcesCompatApi23.getColorStateList(resources, i, theme);
        else
            return resources.getColorStateList(i);
    }

    public static Drawable getDrawable(Resources resources, int i, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ResourcesCompatApi21.getDrawable(resources, i, theme);
        else
            return resources.getDrawable(i);
    }

    public static Drawable getDrawableForDensity(Resources resources, int i, int j, android.content.res.Resources.Theme theme)
        throws android.content.res.Resources.NotFoundException
    {
        if(android.os.Build.VERSION.SDK_INT >= 21)
            return ResourcesCompatApi21.getDrawableForDensity(resources, i, j, theme);
        if(android.os.Build.VERSION.SDK_INT >= 15)
            return ResourcesCompatIcsMr1.getDrawableForDensity(resources, i, j);
        else
            return resources.getDrawable(i);
    }
}
