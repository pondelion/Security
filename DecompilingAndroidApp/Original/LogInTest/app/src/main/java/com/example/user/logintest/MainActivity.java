package com.example.user.logintest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button mLogInButton;
    private EditText mPasswordEditText;
    private TextView mStatusTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPasswordEditText = (EditText)findViewById(R.id.editText);
        mStatusTextView = (TextView)findViewById(R.id.textView3);

        mLogInButton = (Button)findViewById(R.id.button);
        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (passwordCheck(mPasswordEditText.getText().toString())) {
                    processLogInSuccess();
                } else {
                    processLogInFail();
                }
            }
        });
    }

    private boolean passwordCheck(String password) {
        if (("posswo" +
                "rd").equals(password)) {
            return true;
        } else {
            return false;
        }
    }

    private void processLogInSuccess() {
        mStatusTextView.setText("ログインしました");
        Toast.makeText(MainActivity.this, "ログインしました", Toast.LENGTH_LONG).show();
    }

    private void processLogInFail() {
        mStatusTextView.setText("パスワードが異なります");
        Toast.makeText(MainActivity.this, "パスワードが異なります", Toast.LENGTH_LONG).show();
    }
}
