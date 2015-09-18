package com.sb.android.myapplication.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.sb.android.myapplication.R;
import com.sb.android.myapplication.database.helper.DbHelper;

/**
 * Created by student on 2015-09-18.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        findViewById(R.id.login_button).setOnClickListener(this);
        findViewById(R.id.account_text_view).setOnClickListener(this);

        mDbHelper = new DbHelper(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                if(mDbHelper.insert("a", "b", "c")!= -1) {
                    Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "failure", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.account_text_view:
                Intent intent= new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
                break;

        }
    }
}
