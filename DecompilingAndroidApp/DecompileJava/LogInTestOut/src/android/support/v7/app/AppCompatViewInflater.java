// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.*;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import java.lang.reflect.*;
import java.util.Map;

class AppCompatViewInflater
{
    private static class DeclaredOnClickListener
        implements android.view.View.OnClickListener
    {

        private void resolveMethod(Context context, String s)
        {
_L2:
            if(context == null)
                break; /* Loop/switch isn't completed */
            if(context.isRestricted())
                break MISSING_BLOCK_LABEL_48;
            s = context.getClass().getMethod(mMethodName, new Class[] {
                android/view/View
            });
            if(s != null)
                try
                {
                    mResolvedMethod = s;
                    mResolvedContext = context;
                    return;
                }
                // Misplaced declaration of an exception variable
                catch(String s) { }
            if(context instanceof ContextWrapper)
                context = ((ContextWrapper)context).getBaseContext();
            else
                context = null;
            if(true) goto _L2; else goto _L1
_L1:
            int i = mHostView.getId();
            if(i == -1)
                context = "";
            else
                context = (new StringBuilder()).append(" with id '").append(mHostView.getContext().getResources().getResourceEntryName(i)).append("'").toString();
            throw new IllegalStateException((new StringBuilder()).append("Could not find method ").append(mMethodName).append("(View) in a parent or ancestor Context for android:onClick ").append("attribute defined on view ").append(mHostView.getClass()).append(context).toString());
        }

        public void onClick(View view)
        {
            if(mResolvedMethod == null)
                resolveMethod(mHostView.getContext(), mMethodName);
            try
            {
                mResolvedMethod.invoke(mResolvedContext, new Object[] {
                    view
                });
                return;
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new IllegalStateException("Could not execute non-public method for android:onClick", view);
            }
            // Misplaced declaration of an exception variable
            catch(View view)
            {
                throw new IllegalStateException("Could not execute method for android:onClick", view);
            }
        }

        private final View mHostView;
        private final String mMethodName;
        private Context mResolvedContext;
        private Method mResolvedMethod;

        public DeclaredOnClickListener(View view, String s)
        {
            mHostView = view;
            mMethodName = s;
        }
    }


    AppCompatViewInflater()
    {
    }

    private void checkOnClickListener(View view, AttributeSet attributeset)
    {
        Object obj = view.getContext();
        if(!(obj instanceof ContextWrapper) || android.os.Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(view))
            return;
        attributeset = ((Context) (obj)).obtainStyledAttributes(attributeset, sOnClickAttrs);
        obj = attributeset.getString(0);
        if(obj != null)
            view.setOnClickListener(new DeclaredOnClickListener(view, ((String) (obj))));
        attributeset.recycle();
    }

    private View createView(Context context, String s, String s1)
        throws ClassNotFoundException, InflateException
    {
        Object obj;
        Constructor constructor;
        constructor = (Constructor)sConstructorMap.get(s);
        obj = constructor;
        if(constructor != null) goto _L2; else goto _L1
_L1:
        try
        {
            obj = context.getClassLoader();
        }
        // Misplaced declaration of an exception variable
        catch(Context context)
        {
            return null;
        }
        if(s1 == null)
            break MISSING_BLOCK_LABEL_104;
        context = (new StringBuilder()).append(s1).append(s).toString();
_L3:
        obj = ((ClassLoader) (obj)).loadClass(context).asSubclass(android/view/View).getConstructor(sConstructorSignature);
        sConstructorMap.put(s, obj);
_L2:
        ((Constructor) (obj)).setAccessible(true);
        context = (View)((Constructor) (obj)).newInstance(mConstructorArgs);
        return context;
        context = s;
          goto _L3
    }

    private View createViewFromTag(Context context, String s, AttributeSet attributeset)
    {
        String s1;
        s1 = s;
        if(s.equals("view"))
            s1 = attributeset.getAttributeValue(null, "class");
        mConstructorArgs[0] = context;
        mConstructorArgs[1] = attributeset;
        if(-1 != s1.indexOf('.'))
            break MISSING_BLOCK_LABEL_119;
        int i = 0;
        do
        {
            try
            {
                if(i >= sClassPrefixList.length)
                    break;
                s = createView(context, s1, sClassPrefixList[i]);
            }
            // Misplaced declaration of an exception variable
            catch(Context context)
            {
                mConstructorArgs[0] = null;
                mConstructorArgs[1] = null;
                return null;
            }
            if(s != null)
            {
                mConstructorArgs[0] = null;
                mConstructorArgs[1] = null;
                return s;
            }
            i++;
        } while(true);
        mConstructorArgs[0] = null;
        mConstructorArgs[1] = null;
        return null;
        context = createView(context, s1, null);
        mConstructorArgs[0] = null;
        mConstructorArgs[1] = null;
        return context;
        context;
        mConstructorArgs[0] = null;
        mConstructorArgs[1] = null;
        throw context;
    }

    private static Context themifyContext(Context context, AttributeSet attributeset, boolean flag, boolean flag1)
    {
label0:
        {
            attributeset = context.obtainStyledAttributes(attributeset, android.support.v7.appcompat.R.styleable.View, 0, 0);
            int i = 0;
            if(flag)
                i = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.View_android_theme, 0);
            int k = i;
            if(flag1)
            {
                k = i;
                if(i == 0)
                {
                    int j = attributeset.getResourceId(android.support.v7.appcompat.R.styleable.View_theme, 0);
                    k = j;
                    if(j != 0)
                    {
                        Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
                        k = j;
                    }
                }
            }
            attributeset.recycle();
            attributeset = context;
            if(k == 0)
                break label0;
            if(context instanceof ContextThemeWrapper)
            {
                attributeset = context;
                if(((ContextThemeWrapper)context).getThemeResId() == k)
                    break label0;
            }
            attributeset = new ContextThemeWrapper(context, k);
        }
        return attributeset;
    }

    public final View createView(View view, String s, Context context, AttributeSet attributeset, boolean flag, boolean flag1, boolean flag2, 
            boolean flag3)
    {
        byte byte0;
        Object obj;
label0:
        {
            Context context1 = context;
            if(flag)
            {
                context1 = context;
                if(view != null)
                    context1 = view.getContext();
            }
            if(!flag1)
            {
                view = context1;
                if(!flag2)
                    break label0;
            }
            view = themifyContext(context1, attributeset, flag1, flag2);
        }
        obj = view;
        if(flag3)
            obj = TintContextWrapper.wrap(view);
        view = null;
        byte0 = -1;
        s.hashCode();
        JVM INSTR lookupswitch 13: default 184
    //                   -1946472170: 465
    //                   -1455429095: 417
    //                   -1346021293: 449
    //                   -938935918: 295
    //                   -937446323: 370
    //                   -658531749: 481
    //                   -339785223: 355
    //                   776382189: 401
    //                   1125864064: 310
    //                   1413872058: 433
    //                   1601505219: 385
    //                   1666676343: 340
    //                   2001146706: 325;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L1:
        byte0;
        JVM INSTR tableswitch 0 12: default 252
    //                   0 498
    //                   1 513
    //                   2 528
    //                   3 543
    //                   4 558
    //                   5 573
    //                   6 588
    //                   7 603
    //                   8 618
    //                   9 633
    //                   10 648
    //                   11 663
    //                   12 678;
           goto _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28
_L15:
        View view1 = view;
        if(view == null)
        {
            view1 = view;
            if(context != obj)
                view1 = createViewFromTag(((Context) (obj)), s, attributeset);
        }
        if(view1 != null)
            checkOnClickListener(view1, attributeset);
        return view1;
_L5:
        if(s.equals("TextView"))
            byte0 = 0;
          goto _L1
_L10:
        if(s.equals("ImageView"))
            byte0 = 1;
          goto _L1
_L14:
        if(s.equals("Button"))
            byte0 = 2;
          goto _L1
_L13:
        if(s.equals("EditText"))
            byte0 = 3;
          goto _L1
_L8:
        if(s.equals("Spinner"))
            byte0 = 4;
          goto _L1
_L6:
        if(s.equals("ImageButton"))
            byte0 = 5;
          goto _L1
_L12:
        if(s.equals("CheckBox"))
            byte0 = 6;
          goto _L1
_L9:
        if(s.equals("RadioButton"))
            byte0 = 7;
          goto _L1
_L3:
        if(s.equals("CheckedTextView"))
            byte0 = 8;
          goto _L1
_L11:
        if(s.equals("AutoCompleteTextView"))
            byte0 = 9;
          goto _L1
_L4:
        if(s.equals("MultiAutoCompleteTextView"))
            byte0 = 10;
          goto _L1
_L2:
        if(s.equals("RatingBar"))
            byte0 = 11;
          goto _L1
_L7:
        if(s.equals("SeekBar"))
            byte0 = 12;
          goto _L1
_L16:
        view = new AppCompatTextView(((Context) (obj)), attributeset);
          goto _L15
_L17:
        view = new AppCompatImageView(((Context) (obj)), attributeset);
          goto _L15
_L18:
        view = new AppCompatButton(((Context) (obj)), attributeset);
          goto _L15
_L19:
        view = new AppCompatEditText(((Context) (obj)), attributeset);
          goto _L15
_L20:
        view = new AppCompatSpinner(((Context) (obj)), attributeset);
          goto _L15
_L21:
        view = new AppCompatImageButton(((Context) (obj)), attributeset);
          goto _L15
_L22:
        view = new AppCompatCheckBox(((Context) (obj)), attributeset);
          goto _L15
_L23:
        view = new AppCompatRadioButton(((Context) (obj)), attributeset);
          goto _L15
_L24:
        view = new AppCompatCheckedTextView(((Context) (obj)), attributeset);
          goto _L15
_L25:
        view = new AppCompatAutoCompleteTextView(((Context) (obj)), attributeset);
          goto _L15
_L26:
        view = new AppCompatMultiAutoCompleteTextView(((Context) (obj)), attributeset);
          goto _L15
_L27:
        view = new AppCompatRatingBar(((Context) (obj)), attributeset);
          goto _L15
_L28:
        view = new AppCompatSeekBar(((Context) (obj)), attributeset);
          goto _L15
    }

    private static final String LOG_TAG = "AppCompatViewInflater";
    private static final String sClassPrefixList[] = {
        "android.widget.", "android.view.", "android.webkit."
    };
    private static final Map sConstructorMap = new ArrayMap();
    private static final Class sConstructorSignature[] = {
        android/content/Context, android/util/AttributeSet
    };
    private static final int sOnClickAttrs[] = {
        0x101026f
    };
    private final Object mConstructorArgs[] = new Object[2];

}
