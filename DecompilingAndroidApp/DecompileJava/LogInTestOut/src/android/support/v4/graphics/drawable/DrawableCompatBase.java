// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

// Referenced classes of package android.support.v4.graphics.drawable:
//            TintAwareDrawable, DrawableWrapperGingerbread

class DrawableCompatBase
{

    DrawableCompatBase()
    {
    }

    public static void inflate(Drawable drawable, Resources resources, XmlPullParser xmlpullparser, AttributeSet attributeset, android.content.res.Resources.Theme theme)
        throws IOException, XmlPullParserException
    {
        drawable.inflate(resources, xmlpullparser, attributeset);
    }

    public static void setTint(Drawable drawable, int i)
    {
        if(drawable instanceof TintAwareDrawable)
            ((TintAwareDrawable)drawable).setTint(i);
    }

    public static void setTintList(Drawable drawable, ColorStateList colorstatelist)
    {
        if(drawable instanceof TintAwareDrawable)
            ((TintAwareDrawable)drawable).setTintList(colorstatelist);
    }

    public static void setTintMode(Drawable drawable, android.graphics.PorterDuff.Mode mode)
    {
        if(drawable instanceof TintAwareDrawable)
            ((TintAwareDrawable)drawable).setTintMode(mode);
    }

    public static Drawable wrapForTinting(Drawable drawable)
    {
        Object obj = drawable;
        if(!(drawable instanceof TintAwareDrawable))
            obj = new DrawableWrapperGingerbread(drawable);
        return ((Drawable) (obj));
    }
}
