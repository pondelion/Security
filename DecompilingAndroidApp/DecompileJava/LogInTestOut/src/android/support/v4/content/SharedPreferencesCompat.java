// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.content;


public final class SharedPreferencesCompat
{
    public static final class EditorCompat
    {

        public static EditorCompat getInstance()
        {
            if(sInstance == null)
                sInstance = new EditorCompat();
            return sInstance;
        }

        public void apply(android.content.SharedPreferences.Editor editor)
        {
            mHelper.apply(editor);
        }

        private static EditorCompat sInstance;
        private final Helper mHelper = new Helper();

        private EditorCompat()
        {
        }
    }

    private static class EditorCompat.Helper
    {

        public void apply(android.content.SharedPreferences.Editor editor)
        {
            try
            {
                editor.apply();
                return;
            }
            catch(AbstractMethodError abstractmethoderror)
            {
                editor.commit();
            }
        }

        EditorCompat.Helper()
        {
        }
    }


    private SharedPreferencesCompat()
    {
    }
}
