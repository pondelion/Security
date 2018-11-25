// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.content.Context;

// Referenced classes of package android.support.v4.os:
//            BuildCompat, UserManagerCompatApi24

public class UserManagerCompat
{

    private UserManagerCompat()
    {
    }

    public static boolean isUserRunningAndLocked(Context context)
    {
        return !isUserUnlocked(context);
    }

    public static boolean isUserRunningAndUnlocked(Context context)
    {
        return isUserUnlocked(context);
    }

    public static boolean isUserUnlocked(Context context)
    {
        if(BuildCompat.isAtLeastN())
            return UserManagerCompatApi24.isUserUnlocked(context);
        else
            return true;
    }
}
