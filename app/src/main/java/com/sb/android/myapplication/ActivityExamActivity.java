package com.sb.android.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class ActivityExamActivity extends Activity implements View.OnClickListener {

    private EditText mNameEditText;
    private EditText mPhooneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);
        findViewById(R.id.button1).setOnClickListener(this);

        mNameEditText= (EditText)findViewById(R.id.name_edit_text);
        mPhooneEditText= (EditText)findViewById(R.id.phone_edit_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_exam, menu);
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

    @Override
    public void onClick(View v) {
        //
        Intent intent= new Intent(getApplicationContext(), TargetActivity.class);

        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhooneEditText.getText().toString());

        startActivity(intent);
    }
}