// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.ComponentName;
import android.content.Context;
import android.view.View;

// Referenced classes of package android.support.v4.widget:
//            SearchViewCompatHoneycomb, SearchViewCompatIcs

public final class SearchViewCompat
{
    public static interface OnCloseListener
    {

        public abstract boolean onClose();
    }

    public static abstract class OnCloseListenerCompat
        implements OnCloseListener
    {

        public boolean onClose()
        {
            return false;
        }

        public OnCloseListenerCompat()
        {
        }
    }

    public static interface OnQueryTextListener
    {

        public abstract boolean onQueryTextChange(String s);

        public abstract boolean onQueryTextSubmit(String s);
    }

    public static abstract class OnQueryTextListenerCompat
        implements OnQueryTextListener
    {

        public boolean onQueryTextChange(String s)
        {
            return false;
        }

        public boolean onQueryTextSubmit(String s)
        {
            return false;
        }

        public OnQueryTextListenerCompat()
        {
        }
    }

    static class SearchViewCompatHoneycombImpl extends SearchViewCompatStubImpl
    {

        protected void checkIfLegalArg(View view)
        {
            SearchViewCompatHoneycomb.checkIfLegalArg(view);
        }

        public CharSequence getQuery(View view)
        {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.getQuery(view);
        }

        public boolean isIconified(View view)
        {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isIconified(view);
        }

        public boolean isQueryRefinementEnabled(View view)
        {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isQueryRefinementEnabled(view);
        }

        public boolean isSubmitButtonEnabled(View view)
        {
            checkIfLegalArg(view);
            return SearchViewCompatHoneycomb.isSubmitButtonEnabled(view);
        }

        public Object newOnCloseListener(OnCloseListener oncloselistener)
        {
            return SearchViewCompatHoneycomb.newOnCloseListener(oncloselistener. new SearchViewCompatHoneycomb.OnCloseListenerCompatBridge() {

                public boolean onClose()
                {
                    return listener.onClose();
                }

                final SearchViewCompatHoneycombImpl this$0;
                final OnCloseListener val$listener;

            
            {
                this$0 = final_searchviewcompathoneycombimpl;
                listener = OnCloseListener.this;
                super();
            }
            }
);
        }

        public Object newOnQueryTextListener(OnQueryTextListener onquerytextlistener)
        {
            return SearchViewCompatHoneycomb.newOnQueryTextListener(onquerytextlistener. new SearchViewCompatHoneycomb.OnQueryTextListenerCompatBridge() {

                public boolean onQueryTextChange(String s)
                {
                    return listener.onQueryTextChange(s);
                }

                public boolean onQueryTextSubmit(String s)
                {
                    return listener.onQueryTextSubmit(s);
                }

                final SearchViewCompatHoneycombImpl this$0;
                final OnQueryTextListener val$listener;

            
            {
                this$0 = final_searchviewcompathoneycombimpl;
                listener = OnQueryTextListener.this;
                super();
            }
            }
);
        }

        public View newSearchView(Context context)
        {
            return SearchViewCompatHoneycomb.newSearchView(context);
        }

        public void setIconified(View view, boolean flag)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setIconified(view, flag);
        }

        public void setMaxWidth(View view, int i)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setMaxWidth(view, i);
        }

        public void setOnCloseListener(View view, OnCloseListener oncloselistener)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnCloseListener(view, newOnCloseListener(oncloselistener));
        }

        public void setOnQueryTextListener(View view, OnQueryTextListener onquerytextlistener)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setOnQueryTextListener(view, newOnQueryTextListener(onquerytextlistener));
        }

        public void setQuery(View view, CharSequence charsequence, boolean flag)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQuery(view, charsequence, flag);
        }

        public void setQueryHint(View view, CharSequence charsequence)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryHint(view, charsequence);
        }

        public void setQueryRefinementEnabled(View view, boolean flag)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setQueryRefinementEnabled(view, flag);
        }

        public void setSearchableInfo(View view, ComponentName componentname)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSearchableInfo(view, componentname);
        }

        public void setSubmitButtonEnabled(View view, boolean flag)
        {
            checkIfLegalArg(view);
            SearchViewCompatHoneycomb.setSubmitButtonEnabled(view, flag);
        }

        SearchViewCompatHoneycombImpl()
        {
        }
    }

    static class SearchViewCompatIcsImpl extends SearchViewCompatHoneycombImpl
    {

        public View newSearchView(Context context)
        {
            return SearchViewCompatIcs.newSearchView(context);
        }

        public void setImeOptions(View view, int i)
        {
            checkIfLegalArg(view);
            SearchViewCompatIcs.setImeOptions(view, i);
        }

        public void setInputType(View view, int i)
        {
            checkIfLegalArg(view);
            SearchViewCompatIcs.setInputType(view, i);
        }

        SearchViewCompatIcsImpl()
        {
        }
    }

    static interface SearchViewCompatImpl
    {

        public abstract CharSequence getQuery(View view);

        public abstract boolean isIconified(View view);

        public abstract boolean isQueryRefinementEnabled(View view);

        public abstract boolean isSubmitButtonEnabled(View view);

        public abstract Object newOnCloseListener(OnCloseListener oncloselistener);

        public abstract Object newOnQueryTextListener(OnQueryTextListener onquerytextlistener);

        public abstract View newSearchView(Context context);

        public abstract void setIconified(View view, boolean flag);

        public abstract void setImeOptions(View view, int i);

        public abstract void setInputType(View view, int i);

        public abstract void setMaxWidth(View view, int i);

        public abstract void setOnCloseListener(View view, OnCloseListener oncloselistener);

        public abstract void setOnQueryTextListener(View view, OnQueryTextListener onquerytextlistener);

        public abstract void setQuery(View view, CharSequence charsequence, boolean flag);

        public abstract void setQueryHint(View view, CharSequence charsequence);

        public abstract void setQueryRefinementEnabled(View view, boolean flag);

        public abstract void setSearchableInfo(View view, ComponentName componentname);

        public abstract void setSubmitButtonEnabled(View view, boolean flag);
    }

    static class SearchViewCompatStubImpl
        implements SearchViewCompatImpl
    {

        public CharSequence getQuery(View view)
        {
            return null;
        }

        public boolean isIconified(View view)
        {
            return true;
        }

        public boolean isQueryRefinementEnabled(View view)
        {
            return false;
        }

        public boolean isSubmitButtonEnabled(View view)
        {
            return false;
        }

        public Object newOnCloseListener(OnCloseListener oncloselistener)
        {
            return null;
        }

        public Object newOnQueryTextListener(OnQueryTextListener onquerytextlistener)
        {
            return null;
        }

        public View newSearchView(Context context)
        {
            return null;
        }

        public void setIconified(View view, boolean flag)
        {
        }

        public void setImeOptions(View view, int i)
        {
        }

        public void setInputType(View view, int i)
        {
        }

        public void setMaxWidth(View view, int i)
        {
        }

        public void setOnCloseListener(View view, OnCloseListener oncloselistener)
        {
        }

        public void setOnQueryTextListener(View view, OnQueryTextListener onquerytextlistener)
        {
        }

        public void setQuery(View view, CharSequence charsequence, boolean flag)
        {
        }

        public void setQueryHint(View view, CharSequence charsequence)
        {
        }

        public void setQueryRefinementEnabled(View view, boolean flag)
        {
        }

        public void setSearchableInfo(View view, ComponentName componentname)
        {
        }

        public void setSubmitButtonEnabled(View view, boolean flag)
        {
        }

        SearchViewCompatStubImpl()
        {
        }
    }


    private SearchViewCompat(Context context)
    {
    }

    public static CharSequence getQuery(View view)
    {
        return IMPL.getQuery(view);
    }

    public static boolean isIconified(View view)
    {
        return IMPL.isIconified(view);
    }

    public static boolean isQueryRefinementEnabled(View view)
    {
        return IMPL.isQueryRefinementEnabled(view);
    }

    public static boolean isSubmitButtonEnabled(View view)
    {
        return IMPL.isSubmitButtonEnabled(view);
    }

    public static View newSearchView(Context context)
    {
        return IMPL.newSearchView(context);
    }

    public static void setIconified(View view, boolean flag)
    {
        IMPL.setIconified(view, flag);
    }

    public static void setImeOptions(View view, int i)
    {
        IMPL.setImeOptions(view, i);
    }

    public static void setInputType(View view, int i)
    {
        IMPL.setInputType(view, i);
    }

    public static void setMaxWidth(View view, int i)
    {
        IMPL.setMaxWidth(view, i);
    }

    public static void setOnCloseListener(View view, OnCloseListener oncloselistener)
    {
        IMPL.setOnCloseListener(view, oncloselistener);
    }

    public static void setOnQueryTextListener(View view, OnQueryTextListener onquerytextlistener)
    {
        IMPL.setOnQueryTextListener(view, onquerytextlistener);
    }

    public static void setQuery(View view, CharSequence charsequence, boolean flag)
    {
        IMPL.setQuery(view, charsequence, flag);
    }

    public static void setQueryHint(View view, CharSequence charsequence)
    {
        IMPL.setQueryHint(view, charsequence);
    }

    public static void setQueryRefinementEnabled(View view, boolean flag)
    {
        IMPL.setQueryRefinementEnabled(view, flag);
    }

    public static void setSearchableInfo(View view, ComponentName componentname)
    {
        IMPL.setSearchableInfo(view, componentname);
    }

    public static void setSubmitButtonEnabled(View view, boolean flag)
    {
        IMPL.setSubmitButtonEnabled(view, flag);
    }

    private static final SearchViewCompatImpl IMPL;

    static 
    {
        if(android.os.Build.VERSION.SDK_INT >= 14)
            IMPL = new SearchViewCompatIcsImpl();
        else
        if(android.os.Build.VERSION.SDK_INT >= 11)
            IMPL = new SearchViewCompatHoneycombImpl();
        else
            IMPL = new SearchViewCompatStubImpl();
    }
}
