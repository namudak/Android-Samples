package com.sb.android.myapplication.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sb.android.myapplication.R;

public class Mission031Activity extends AppCompatActivity implements View.OnClickListener{

    private final int REQUEST_CODE_STRING= 0;

    private EditText mNameEditText;
    private EditText mPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission031);


        mNameEditText = (EditText)findViewById(R.id.name_edit_text);
        mPasswordEditText = (EditText)findViewById(R.id.password_edit_text);

        findViewById(R.id.login_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Check if user name or password is null
        if(mNameEditText.getText().toString().equals("") ||
                mPasswordEditText.getText().toString().equals("") ) {
            Toast.makeText(Mission031Activity.this, "Error!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent= new Intent(getApplicationContext(), Mission032Activity.class);

        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("password", mPasswordEditText.getText().toString());

        startActivityForResult(intent, REQUEST_CODE_STRING);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK) {
            if(requestCode== REQUEST_CODE_STRING) {
                if(data!= null) {
                    String result= data.getStringExtra(("result"));
                    Toast.makeText(Mission031Activity.this, "Result: " + result, Toast.LENGTH_SHORT).show();
                }
            }
        } else {
            Toast.makeText(Mission031Activity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission031, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
