// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.widget;

import android.content.res.ColorStateList;

class TintInfo
{

    TintInfo()
    {
    }

    void clear()
    {
        mTintList = null;
        mHasTintList = false;
        mTintMode = null;
        mHasTintMode = false;
    }

    public boolean mHasTintList;
    public boolean mHasTintMode;
    public ColorStateList mTintList;
    public android.graphics.PorterDuff.Mode mTintMode;
}
