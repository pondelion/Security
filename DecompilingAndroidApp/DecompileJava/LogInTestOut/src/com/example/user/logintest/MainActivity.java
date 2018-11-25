// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.example.user.logintest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity
{

    public MainActivity()
    {
    }

    private boolean passwordCheck(String s)
    {
        return "possword".equals(s);
    }

    private void processLogInFail()
    {
        mStatusTextView.setText("\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u7570\u306A\u308A\u307E\u3059");
        Toast.makeText(this, "\u30D1\u30B9\u30EF\u30FC\u30C9\u304C\u7570\u306A\u308A\u307E\u3059", 1).show();
    }

    private void processLogInSuccess()
    {
        mStatusTextView.setText("\u30ED\u30B0\u30A4\u30F3\u3057\u307E\u3057\u305F");
        Toast.makeText(this, "\u30ED\u30B0\u30A4\u30F3\u3057\u307E\u3057\u305F", 1).show();
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        setContentView(0x7f04001a);
        mPasswordEditText = (EditText)findViewById(0x7f0b0055);
        mStatusTextView = (TextView)findViewById(0x7f0b0057);
        mLogInButton = (Button)findViewById(0x7f0b0056);
        mLogInButton.setOnClickListener(new android.view.View.OnClickListener() {

            public void onClick(View view)
            {
                if(passwordCheck(mPasswordEditText.getText().toString()))
                {
                    processLogInSuccess();
                    return;
                } else
                {
                    processLogInFail();
                    return;
                }
            }

            final MainActivity this$0;

            
            {
                this$0 = MainActivity.this;
                super();
            }
        }
);
    }

    private Button mLogInButton;
    private EditText mPasswordEditText;
    private TextView mStatusTextView;




}
