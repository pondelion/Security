// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.drawable.*;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.graphics.drawable:
//            DrawableWrapper, TintAwareDrawable, DrawableWrapperLollipop

class DrawableCompatLollipop
{

    DrawableCompatLollipop()
    {
    }

    public static void applyTheme(Drawable drawable, android.content.res.Resources.Theme theme)
    {
        drawable.applyTheme(theme);
    }

    public static boolean canApplyTheme(Drawable drawable)
    {
        return drawable.canApplyTheme();
    }

    public static void clearColorFilter(Drawable drawable)
    {
        drawable.clearColorFilter();
        if(!(drawable instanceof InsetDrawable)) goto _L2; else goto _L1
_L1:
        clearColorFilter(((InsetDrawable)drawable).getDrawable());
_L4:
        return;
_L2:
        if(drawable instanceof DrawableWrapper)
        {
            clearColorFilter(((DrawableWrapper)drawable).getWrappedDrawable());
            return;
        }
        if(drawable instanceof DrawableContainer)
        {
            drawable = (android.graphics.drawable.DrawableContainer.DrawableContainerState)((DrawableContainer)drawable).getConstantState();
            if(drawable != null)
            {
                int i = 0;
                int j = drawable.getChildCount();
                while(i < j) 
                {
                    Drawable drawable1 = drawable.getChild(i);
                    if(drawable1 != null)
                        clearColorFilter(drawable1);
                    i++;
                }
            }
        }
        if(true) goto _L4; else goto _L3
_L3:
    }

    public static ColorFilter getColorFilter(Drawable drawable)
    {
        return drawable.getColorFilter();
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws IOException, XmlPullParserException
    {
        drawable.inflate(resources, xmlpullparser, attributeset, theme);
    }

    public static void setHotspot(Drawable drawable, float f, float f1)
    {
        drawable.setHotspot(f, f1);
    }

    public static void setHotspotBounds(Drawable drawable, int i, int j, int k, int l)
    {
        drawable.setHotspotBounds(i, j, k, l);
    }

    public static void setTint(Drawable drawable, int i)
    {
        drawable.setTint(i);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorstatelist)
    {
        drawable.setTintList(colorstatelist);
    }

    public static void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
    {
        drawable.setTintMode(mode);
    }

    public static Drawable wrapForTinting(Drawable drawable)
    {
        Object obj = drawable;
        if(!(drawable instanceof TintAwareDrawable))
            obj = new DrawableWrapperLollipop(drawable);
        return ((Drawable) (obj));
    }
}
