// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.CompoundButton;
import java.lang.reflect.Field;

// Referenced classes of package android.support.v4.widget:
//            TintableCompoundButton

class CompoundButtonCompatGingerbread
{

    CompoundButtonCompatGingerbread()
    {
    }

    static Drawable getButtonDrawable(CompoundButton compoundbutton)
    {
        if(!sButtonDrawableFieldFetched)
        {
            try
            {
                sButtonDrawableField = android/widget/CompoundButton.getDeclaredField("mButtonDrawable");
                sButtonDrawableField.setAccessible(true);
            }
            catch(NoSuchFieldException nosuchfieldexception)
            {
                Log.i("CompoundButtonCompatGingerbread", "Failed to retrieve mButtonDrawable field", nosuchfieldexception);
            }
            sButtonDrawableFieldFetched = true;
        }
        if(sButtonDrawableField == null)
            break MISSING_BLOCK_LABEL_73;
        compoundbutton = (Drawable)sButtonDrawableField.get(compoundbutton);
        return compoundbutton;
        compoundbutton;
        Log.i("CompoundButtonCompatGingerbread", "Failed to get button drawable via reflection", compoundbutton);
        sButtonDrawableField = null;
        return null;
    }

    static ColorStateList getButtonTintList(CompoundButton compoundbutton)
    {
        if(compoundbutton instanceof TintableCompoundButton)
            return ((TintableCompoundButton)compoundbutton).getSupportButtonTintList();
        else
            return null;
    }

    static android.graphics.PorterDuff.Mode getButtonTintMode(CompoundButton compoundbutton)
    {
        if(compoundbutton instanceof TintableCompoundButton)
            return ((TintableCompoundButton)compoundbutton).getSupportButtonTintMode();
        else
            return null;
    }

    static void setButtonTintList(CompoundButton compoundbutton, ColorStateList colorstatelist)
    {
        if(compoundbutton instanceof TintableCompoundButton)
            ((TintableCompoundButton)compoundbutton).setSupportButtonTintList(colorstatelist);
    }

    static void setButtonTintMode(CompoundButton compoundbutton, android.graphics.PorterDuff.Mode mode)
    {
        if(compoundbutton instanceof TintableCompoundButton)
            ((TintableCompoundButton)compoundbutton).setSupportButtonTintMode(mode);
    }

    private static final String TAG = "CompoundButtonCompatGingerbread";
    private static Field sButtonDrawableField;
    private static boolean sButtonDrawableFieldFetched;
}
