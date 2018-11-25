// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

// Referenced classes of package android.support.v4.widget:
//            TextViewCompatApi23, TextViewCompatJbMr2, TextViewCompatJbMr1, TextViewCompatJb, 
//            TextViewCompatGingerbread

public final class TextViewCompat
{
    static class Api23TextViewCompatImpl extends JbMr2TextViewCompatImpl
    {

        public void setTextAppearance(TextView textview, int i)
        {
            TextViewCompatApi23.setTextAppearance(textview, i);
        }

        Api23TextViewCompatImpl()
        {
        }
    }

    static class BaseTextViewCompatImpl
        implements TextViewCompatImpl
    {

        public Drawable[] getCompoundDrawablesRelative(TextView textview)
        {
            return TextViewCompatGingerbread.getCompoundDrawablesRelative(textview);
        }

        public int getMaxLines(TextView textview)
        {
            return TextViewCompatGingerbread.getMaxLines(textview);
        }

        public int getMinLines(TextView textview)
        {
            return TextViewCompatGingerbread.getMinLines(textview);
        }

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawables(drawable, drawable1, drawable2, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(i, j, k, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            textview.setCompoundDrawablesWithIntrinsicBounds(drawable, drawable1, drawable2, drawable3);
        }

        public void setTextAppearance(TextView textview, int i)
        {
            TextViewCompatGingerbread.setTextAppearance(textview, i);
        }

        BaseTextViewCompatImpl()
        {
        }
    }

    static class JbMr1TextViewCompatImpl extends JbTextViewCompatImpl
    {

        public Drawable[] getCompoundDrawablesRelative(TextView textview)
        {
            return TextViewCompatJbMr1.getCompoundDrawablesRelative(textview);
        }

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            TextViewCompatJbMr1.setCompoundDrawablesRelative(textview, drawable, drawable1, drawable2, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, i, j, k, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            TextViewCompatJbMr1.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, drawable, drawable1, drawable2, drawable3);
        }

        JbMr1TextViewCompatImpl()
        {
        }
    }

    static class JbMr2TextViewCompatImpl extends JbMr1TextViewCompatImpl
    {

        public void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            TextViewCompatJbMr2.setCompoundDrawablesRelative(textview, drawable, drawable1, drawable2, drawable3);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
        {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, i, j, k, l);
        }

        public void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
        {
            TextViewCompatJbMr2.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, drawable, drawable1, drawable2, drawable3);
        }

        JbMr2TextViewCompatImpl()
        {
        }
    }

    static class JbTextViewCompatImpl extends BaseTextViewCompatImpl
    {

        public int getMaxLines(TextView textview)
        {
            return TextViewCompatJb.getMaxLines(textview);
        }

        public int getMinLines(TextView textview)
        {
            return TextViewCompatJb.getMinLines(textview);
        }

        JbTextViewCompatImpl()
        {
        }
    }

    static interface TextViewCompatImpl
    {

        public abstract Drawable[] getCompoundDrawablesRelative(TextView textview);

        public abstract int getMaxLines(TextView textview);

        public abstract int getMinLines(TextView textview);

        public abstract void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3);

        public abstract void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l);

        public abstract void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3);

        public abstract void setTextAppearance(TextView textview, int i);
    }


    private TextViewCompat()
    {
    }

    public static Drawable[] getCompoundDrawablesRelative(TextView textview)
    {
        return textview.getCompoundDrawables();
    }

    public static int getMaxLines(TextView textview)
    {
        return IMPL.getMaxLines(textview);
    }

    public static int getMinLines(TextView textview)
    {
        return IMPL.getMinLines(textview);
    }

    public static void setCompoundDrawablesRelative(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        IMPL.setCompoundDrawablesRelative(textview, drawable, drawable1, drawable2, drawable3);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, int i, int j, int k, int l)
    {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, i, j, k, l);
    }

    public static void setCompoundDrawablesRelativeWithIntrinsicBounds(TextView textview, Drawable drawable, Drawable drawable1, Drawable drawable2, Drawable drawable3)
    {
        IMPL.setCompoundDrawablesRelativeWithIntrinsicBounds(textview, drawable, drawable1, drawable2, drawable3);
    }

    public static void setTextAppearance(TextView textview, int i)
    {
        IMPL.setTextAppearance(textview, i);
    }

    static final TextViewCompatImpl IMPL;

    static 
    {
        int i = android.os.Build.VERSION.SDK_INT;
        if(i >= 23)
            IMPL = new Api23TextViewCompatImpl();
        else
        if(i >= 18)
            IMPL = new JbMr2TextViewCompatImpl();
        else
        if(i >= 17)
            IMPL = new JbMr1TextViewCompatImpl();
        else
        if(i >= 16)
            IMPL = new JbTextViewCompatImpl();
        else
            IMPL = new BaseTextViewCompatImpl();
    }
}
