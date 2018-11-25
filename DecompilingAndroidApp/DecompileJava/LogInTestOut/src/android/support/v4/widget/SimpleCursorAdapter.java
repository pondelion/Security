// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.widget;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

// Referenced classes of package android.support.v4.widget:
//            ResourceCursorAdapter

public class SimpleCursorAdapter extends ResourceCursorAdapter
{
    public static interface CursorToStringConverter
    {

        public abstract CharSequence convertToString(Cursor cursor);
    }

    public static interface ViewBinder
    {

        public abstract boolean setViewValue(View view, Cursor cursor, int i);
    }


    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String as[], int ai[])
    {
        super(context, i, cursor);
        mStringConversionColumn = -1;
        mTo = ai;
        mOriginalFrom = as;
        findColumns(cursor, as);
    }

    public SimpleCursorAdapter(Context context, int i, Cursor cursor, String as[], int ai[], int j)
    {
        super(context, i, cursor, j);
        mStringConversionColumn = -1;
        mTo = ai;
        mOriginalFrom = as;
        findColumns(cursor, as);
    }

    private void findColumns(Cursor cursor, String as[])
    {
        if(cursor != null)
        {
            int j = as.length;
            if(mFrom == null || mFrom.length != j)
                mFrom = new int[j];
            for(int i = 0; i < j; i++)
                mFrom[i] = cursor.getColumnIndexOrThrow(as[i]);

        } else
        {
            mFrom = null;
        }
    }

    public void bindView(View view, Context context, Cursor cursor)
    {
        ViewBinder viewbinder = mViewBinder;
        int j = mTo.length;
        int ai[] = mFrom;
        int ai1[] = mTo;
        int i = 0;
        while(i < j) 
        {
            View view1 = view.findViewById(ai1[i]);
            if(view1 == null)
                continue;
            boolean flag = false;
            if(viewbinder != null)
                flag = viewbinder.setViewValue(view1, cursor, ai[i]);
            if(!flag)
            {
                String s = cursor.getString(ai[i]);
                context = s;
                if(s == null)
                    context = "";
                if(view1 instanceof TextView)
                    setViewText((TextView)view1, context);
                else
                if(view1 instanceof ImageView)
                    setViewImage((ImageView)view1, context);
                else
                    throw new IllegalStateException((new StringBuilder()).append(view1.getClass().getName()).append(" is not a ").append(" view that can be bounds by this SimpleCursorAdapter").toString());
            }
            i++;
        }
    }

    public void changeCursorAndColumns(Cursor cursor, String as[], int ai[])
    {
        mOriginalFrom = as;
        mTo = ai;
        findColumns(cursor, mOriginalFrom);
        super.changeCursor(cursor);
    }

    public CharSequence convertToString(Cursor cursor)
    {
        if(mCursorToStringConverter != null)
            return mCursorToStringConverter.convertToString(cursor);
        if(mStringConversionColumn > -1)
            return cursor.getString(mStringConversionColumn);
        else
            return super.convertToString(cursor);
    }

    public CursorToStringConverter getCursorToStringConverter()
    {
        return mCursorToStringConverter;
    }

    public int getStringConversionColumn()
    {
        return mStringConversionColumn;
    }

    public ViewBinder getViewBinder()
    {
        return mViewBinder;
    }

    public void setCursorToStringConverter(CursorToStringConverter cursortostringconverter)
    {
        mCursorToStringConverter = cursortostringconverter;
    }

    public void setStringConversionColumn(int i)
    {
        mStringConversionColumn = i;
    }

    public void setViewBinder(ViewBinder viewbinder)
    {
        mViewBinder = viewbinder;
    }

    public void setViewImage(ImageView imageview, String s)
    {
        try
        {
            imageview.setImageResource(Integer.parseInt(s));
            return;
        }
        catch(NumberFormatException numberformatexception)
        {
            imageview.setImageURI(Uri.parse(s));
        }
    }

    public void setViewText(TextView textview, String s)
    {
        textview.setText(s);
    }

    public Cursor swapCursor(Cursor cursor)
    {
        findColumns(cursor, mOriginalFrom);
        return super.swapCursor(cursor);
    }

    private CursorToStringConverter mCursorToStringConverter;
    protected int mFrom[];
    String mOriginalFrom[];
    private int mStringConversionColumn;
    protected int mTo[];
    private ViewBinder mViewBinder;
}
