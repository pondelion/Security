// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v4.media;

import android.os.Bundle;

public class MediaBrowserCompatUtils
{

    public MediaBrowserCompatUtils()
    {
    }

    public static boolean areSameOptions(Bundle bundle, Bundle bundle1)
    {
        if(bundle != bundle1) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if(bundle != null)
            break; /* Loop/switch isn't completed */
        if(bundle1.getInt("android.media.browse.extra.PAGE", -1) != -1 || bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1) != -1)
            return false;
        if(true) goto _L1; else goto _L3
_L3:
        if(bundle1 != null)
            continue; /* Loop/switch isn't completed */
        if(bundle.getInt("android.media.browse.extra.PAGE", -1) == -1 && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == -1) goto _L1; else goto _L4
_L4:
        return false;
        if(bundle.getInt("android.media.browse.extra.PAGE", -1) == bundle1.getInt("android.media.browse.extra.PAGE", -1) && bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1) == bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1)) goto _L1; else goto _L5
_L5:
        return false;
    }

    public static boolean hasDuplicatedItems(Bundle bundle, Bundle bundle1)
    {
        int i;
        int j;
        int k;
        int l;
        if(bundle == null)
            k = -1;
        else
            k = bundle.getInt("android.media.browse.extra.PAGE", -1);
        if(bundle1 == null)
            i = -1;
        else
            i = bundle1.getInt("android.media.browse.extra.PAGE", -1);
        if(bundle == null)
            l = -1;
        else
            l = bundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if(bundle1 == null)
            j = -1;
        else
            j = bundle1.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if(k == -1 || l == -1)
        {
            l = 0;
            k = 0x7fffffff;
        } else
        {
            int i1 = l * k;
            k = (i1 + l) - 1;
            l = i1;
        }
        if(i == -1 || j == -1)
        {
            j = 0;
            i = 0x7fffffff;
        } else
        {
            int j1 = j * i;
            i = (j1 + j) - 1;
            j = j1;
        }
        while(l <= j && j <= k || l <= i && i <= k) 
            return true;
        return false;
    }
}
