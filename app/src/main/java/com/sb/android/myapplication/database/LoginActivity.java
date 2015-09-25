package com.sb.android.myapplication.database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.sb.android.myapplication.R;
import com.sb.android.myapplication.database.helper.DbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper mDbHelper;
    private EditText mEmail;
    private EditText mPassword;
    private CheckBox mRememberEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        mEmail = (EditText)findViewById(R.id.email_edit_text);
        mPassword = (EditText)findViewById(R.id.password_edit_text);
        mRememberEmail = (CheckBox)findViewById(R.id.remember_email);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.account_text_view).setOnClickListener(this);


        mDbHelper = new DbHelper(this);

        // Todo Get SharedPreference
        getSharedPreferencesValue();

    }

    private String getSharedPreferencesValue() {
        SharedPreferences sharedPref= getPreferences(Context.MODE_PRIVATE);
        String email= sharedPref.getString("pref_email", "");
        return email;
    }

    private void saveSharedPreferencesValue(String email) {
        SharedPreferences sharedPref= getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPref.edit();
        editor.putString("put_email", email);
        //editor.commit();//sync
        editor.apply();//async
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
//                DbHelper helper= new DbHelper(this);
//                ContentValues values = new ContentValues();
//                values.put("Mode", "LoginIdCheck");
//                values.put(DbContract.UserEntry.COLUMN_NAME_EMAIL, mEmail.getText().toString());
//                values.put(DbContract.UserEntry.COLUMN_NAME_PASSWORD, mPassword.getText().toString());
//                Cursor cursor= helper.query(values);
//
//                // TODO set SharedPreferences value
//                if(mRememberEmail.isChecked()) {
//                    saveSharedPreferencesValue(mEmail.getText().toString());
//                } else {
//                    saveSharedPreferencesValue("");
//                }


                ParseUser.logInInBackground(mEmail.getText().toString(),
                                            mPassword.getText().toString(), new LogInCallback() {
                    public void done(ParseUser user, ParseException e) {
                        if (user != null) {
                            // Hooray! The user is logged in.
                            //Toast.makeText("Login", "success", Toast.LENGTH_SHORT).show();
                        } else {
                            // Signup failed. Look at the ParseException to see what happened.
                        }
                    }
                });
                break;
            case R.id.account_text_view:
                Intent intent= new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                break;

        }
    }
}
