// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.view.menu;

import android.content.Context;
import android.graphics.Rect;
import android.view.*;
import android.widget.*;

// Referenced classes of package android.support.v7.view.menu:
//            ShowableListMenu, MenuPresenter, MenuBuilder, MenuAdapter, 
//            MenuItemImpl, MenuView

abstract class MenuPopup
    implements ShowableListMenu, MenuPresenter, android.widget.AdapterView.OnItemClickListener
{

    MenuPopup()
    {
    }

    protected static int measureIndividualMenuWidth(ListAdapter listadapter, ViewGroup viewgroup, Context context, int i)
    {
        int j = 0;
        Object obj1 = null;
        int i1 = 0;
        int k1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int l1 = android.view.View.MeasureSpec.makeMeasureSpec(0, 0);
        int i2 = listadapter.getCount();
        int k = 0;
        Object obj = viewgroup;
        viewgroup = obj1;
        while(k < i2) 
        {
            int j1 = listadapter.getItemViewType(k);
            int l = i1;
            if(j1 != i1)
            {
                l = j1;
                viewgroup = null;
            }
            Object obj2 = obj;
            if(obj == null)
                obj2 = new FrameLayout(context);
            viewgroup = listadapter.getView(k, viewgroup, ((ViewGroup) (obj2)));
            viewgroup.measure(k1, l1);
            i1 = viewgroup.getMeasuredWidth();
            if(i1 >= i)
                return i;
            j1 = j;
            if(i1 > j)
                j1 = i1;
            k++;
            i1 = l;
            j = j1;
            obj = obj2;
        }
        return j;
    }

    protected static boolean shouldPreserveIconSpacing(MenuBuilder menubuilder)
    {
        boolean flag1 = false;
        int j = menubuilder.size();
        int i = 0;
        do
        {
label0:
            {
                boolean flag = flag1;
                if(i < j)
                {
                    MenuItem menuitem = menubuilder.getItem(i);
                    if(!menuitem.isVisible() || menuitem.getIcon() == null)
                        break label0;
                    flag = true;
                }
                return flag;
            }
            i++;
        } while(true);
    }

    protected static MenuAdapter toMenuAdapter(ListAdapter listadapter)
    {
        if(listadapter instanceof HeaderViewListAdapter)
            return (MenuAdapter)((HeaderViewListAdapter)listadapter).getWrappedAdapter();
        else
            return (MenuAdapter)listadapter;
    }

    public abstract void addMenu(MenuBuilder menubuilder);

    protected boolean closeMenuOnSubMenuOpened()
    {
        return true;
    }

    public boolean collapseItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public boolean expandItemActionView(MenuBuilder menubuilder, MenuItemImpl menuitemimpl)
    {
        return false;
    }

    public Rect getEpicenterBounds()
    {
        return mEpicenterBounds;
    }

    public int getId()
    {
        return 0;
    }

    public MenuView getMenuView(ViewGroup viewgroup)
    {
        throw new UnsupportedOperationException("MenuPopups manage their own views");
    }

    public void initForMenu(Context context, MenuBuilder menubuilder)
    {
    }

    public void onItemClick(AdapterView adapterview, View view, int i, long l)
    {
        view = (ListAdapter)adapterview.getAdapter();
        adapterview = toMenuAdapter(view).mAdapterMenu;
        view = (MenuItem)view.getItem(i);
        if(closeMenuOnSubMenuOpened())
            i = 0;
        else
            i = 4;
        adapterview.performItemAction(view, this, i);
    }

    public abstract void setAnchorView(View view);

    public void setEpicenterBounds(Rect rect)
    {
        mEpicenterBounds = rect;
    }

    public abstract void setForceShowIcon(boolean flag);

    public abstract void setGravity(int i);

    public abstract void setHorizontalOffset(int i);

    public abstract void setOnDismissListener(android.widget.PopupWindow.OnDismissListener ondismisslistener);

    public abstract void setShowTitle(boolean flag);

    public abstract void setVerticalOffset(int i);

    private Rect mEpicenterBounds;
}
