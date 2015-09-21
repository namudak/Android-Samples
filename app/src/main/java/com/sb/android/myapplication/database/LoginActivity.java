package com.sb.android.myapplication.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.database.contract.DbContract;
import com.sb.android.myapplication.database.helper.DbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper mDbHelper;
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmail = (EditText)findViewById(R.id.email_edit_text);
        mPassword = (EditText)findViewById(R.id.password_edit_text);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.account_text_view).setOnClickListener(this);

        mDbHelper = new DbHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
//                if(mDbHelper.insert("a", "b", "c")!= -1) {
//                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
//                }
//                Cursor cursor= mDbHelper.query();// last position
//
//                if(cursor!= null) {
//                    cursor.moveToFirst();
//                    long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(DbContract.UserEntry._ID));
//                    Toast.makeText(getApplicationContext(), "Success"+ itemId, Toast.LENGTH_SHORT).show();
//                } else {
//                    Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
//                }
//                int count= mDbHelper.update("a", "aaa");
//                if(count!= 0){
//                    Toast.makeText(getApplicationContext(), "Success" + count, Toast.LENGTH_SHORT).show();
//                }
                DbHelper helper= new DbHelper(this);
                ContentValues values = new ContentValues();
                values.put("Mode", "LoginIdCheck");
                values.put(DbContract.UserEntry.COLUMN_NAME_EMAIL, mEmail.getText().toString());
                values.put(DbContract.UserEntry.COLUMN_NAME_PASSWORD, mPassword.getText().toString());
                Cursor cursor= helper.query(values);
                break;
            case R.id.account_text_view:
                Intent intent= new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                break;

        }
    }
}
