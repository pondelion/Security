// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.widget.ListView;

// Referenced classes of package android.support.v4.widget:
//            ListViewCompatKitKat, ListViewCompatGingerbread

public final class ListViewCompat
{

    private ListViewCompat()
    {
    }

    public static void scrollListBy(ListView listview, int i)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19)
        {
            ListViewCompatKitKat.scrollListBy(listview, i);
            return;
        } else
        {
            ListViewCompatGingerbread.scrollListBy(listview, i);
            return;
        }
    }
}
