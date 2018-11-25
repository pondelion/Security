// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.app;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Window;

// Referenced classes of package android.support.v7.app:
//            AppCompatDialog

public class AppCompatDialogFragment extends DialogFragment
{

    public AppCompatDialogFragment()
    {
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        return new AppCompatDialog(getContext(), getTheme());
    }

    public void setupDialog(Dialog dialog, int i)
    {
        if(dialog instanceof AppCompatDialog)
        {
            AppCompatDialog appcompatdialog = (AppCompatDialog)dialog;
            switch(i)
            {
            default:
                return;

            case 3: // '\003'
                dialog.getWindow().addFlags(24);
                // fall through

            case 1: // '\001'
            case 2: // '\002'
                appcompatdialog.supportRequestWindowFeature(1);
                break;
            }
            return;
        } else
        {
            super.setupDialog(dialog, i);
            return;
        }
    }
}
