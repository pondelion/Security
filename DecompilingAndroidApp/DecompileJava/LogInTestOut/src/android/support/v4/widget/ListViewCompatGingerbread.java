// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.view.View;
import android.widget.ListView;

class ListViewCompatGingerbread
{

    ListViewCompatGingerbread()
    {
    }

    static void scrollListBy(ListView listview, int i)
    {
        int j = listview.getFirstVisiblePosition();
        View view;
        if(j != -1)
            if((view = listview.getChildAt(0)) != null)
            {
                listview.setSelectionFromTop(j, view.getTop() - i);
                return;
            }
    }
}
