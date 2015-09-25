package com.sb.android.myapplication.database;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.database.contract.DbContract;
import com.sb.android.myapplication.database.helper.DbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class SignupActivity extends AppCompatActivity{

    private EditText mNickname;
    private EditText mEmail;
    private EditText mPassword;
    private EditText mPasswordVerify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.signup_activity);

        mNickname = (EditText)findViewById(R.id.nickname_edit_text);
        mEmail = (EditText)findViewById(R.id.email_edit_text);
        mPassword = (EditText)findViewById(R.id.password_edit_text);
        mPasswordVerify = (EditText)findViewById(R.id.passwordverify_edit_text);

        findViewById(R.id.signup_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password= mPassword.getText().toString();
                String password2= mPasswordVerify.getText().toString();
                if(password.equals(password2)) {
                    DbHelper dbHelper = new DbHelper(getApplicationContext());
                    ContentValues values= new ContentValues();
                    values.put(DbContract.UserEntry.COLUMN_NAME_NICKNAME, mNickname.getText().toString());
                    values.put(DbContract.UserEntry.COLUMN_NAME_EMAIL, mEmail.getText().toString());
                    values.put(DbContract.UserEntry.COLUMN_NAME_PASSWORD, mPassword.getText().toString());
                    long inserted= dbHelper.insert(values);
                    if(inserted!= 0) {
                        Toast.makeText(getApplicationContext(), "Welcome aboard!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "email duplicated!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    mPasswordVerify.requestFocus();
                    Toast.makeText(getApplicationContext(), "Please check password!", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }
}
