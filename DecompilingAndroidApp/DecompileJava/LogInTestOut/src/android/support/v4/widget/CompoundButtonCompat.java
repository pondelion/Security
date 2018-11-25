// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.widget.CompoundButton;

// Referenced classes of package android.support.v4.widget:
//            CompoundButtonCompatApi23, CompoundButtonCompatLollipop, CompoundButtonCompatGingerbread

public final class CompoundButtonCompat
{
    static class Api23CompoundButtonImpl extends LollipopCompoundButtonImpl
    {

        public Drawable getButtonDrawable(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatApi23.getButtonDrawable(compoundbutton);
        }

        Api23CompoundButtonImpl()
        {
        }
    }

    static class BaseCompoundButtonCompat
        implements CompoundButtonCompatImpl
    {

        public Drawable getButtonDrawable(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatGingerbread.getButtonDrawable(compoundbutton);
        }

        public ColorStateList getButtonTintList(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatGingerbread.getButtonTintList(compoundbutton);
        }

        public android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatGingerbread.getButtonTintMode(compoundbutton);
        }

        public void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
        {
            CompoundButtonCompatGingerbread.setButtonTintList(compoundbutton, colorstatelist);
        }

        public void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
        {
            CompoundButtonCompatGingerbread.setButtonTintMode(compoundbutton, mode);
        }

        BaseCompoundButtonCompat()
        {
        }
    }

    static interface CompoundButtonCompatImpl
    {

        public abstract Drawable getButtonDrawable(CompoundButton compoundbutton);

        public abstract ColorStateList getButtonTintList(CompoundButton compoundbutton);

        public abstract android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton);

        public abstract void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist);

        public abstract void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode);
    }

    static class LollipopCompoundButtonImpl extends BaseCompoundButtonCompat
    {

        public ColorStateList getButtonTintList(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatLollipop.getButtonTintList(compoundbutton);
        }

        public android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
        {
            return CompoundButtonCompatLollipop.getButtonTintMode(compoundbutton);
        }

        public void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
        {
            CompoundButtonCompatLollipop.setButtonTintList(compoundbutton, colorstatelist);
        }

        public void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
        {
            CompoundButtonCompatLollipop.setButtonTintMode(compoundbutton, mode);
        }

        LollipopCompoundButtonImpl()
        {
        }
    }


    private CompoundButtonCompat()
    {
    }

    public static Drawable getButtonDrawable(CompoundButton compoundbutton)
    {
        return IMPL.getButtonDrawable(compoundbutton);
    }

    public static ColorStateList getButtonTintList(CompoundButton compoundbutton)
    {
        return IMPL.getButtonTintList(compoundbutton);
    }

    public static android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
    {
        return IMPL.getButtonTintMode(compoundbutton);
    }

    public static void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
    {
        IMPL.setButtonTintList(compoundbutton, colorstatelist);
    }

    public static void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
    {
        IMPL.setButtonTintMode(compoundbutton, mode);
    }

    private static final CompoundButtonCompatImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 23)
            IMPL = new Api23CompoundButtonImpl();
        else
        if(i >= 21)
            IMPL = new LollipopCompoundButtonImpl();
        else
            IMPL = new BaseCompoundButtonCompat();
    }
}
