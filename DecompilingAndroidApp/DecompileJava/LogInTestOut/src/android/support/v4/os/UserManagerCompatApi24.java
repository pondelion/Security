// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.os;

import android.content.Context;
import android.os.UserManager;

public class UserManagerCompatApi24
{

    public UserManagerCompatApi24()
    {
    }

    public static boolean isUserUnlocked(Context context)
    {
        return ((UserManager)context.getSystemService(android/os/UserManager)).isUserUnlocked();
    }
}
