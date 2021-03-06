package com.sb.android.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sb.android.myapplication.R;

public class TargetActivity extends Activity implements View.OnClickListener {

    private String mName;
    private String mPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target);

        // Get intent's data
         mName = getIntent().getStringExtra("name");
         mPhone= getIntent().getStringExtra("phone");

        // Display result
        Toast.makeText(TargetActivity.this, "name: " + mName + "phone: " + mPhone,
                Toast.LENGTH_SHORT).show();

        Intent intent= getIntent();
        TodoItem toditem= intent.getParcelableExtra("todo");
        Toast.makeText(getApplicationContext(), toditem.toString(), Toast.LENGTH_SHORT).show();

        findViewById(R.id.finish_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Make return data
        Intent intent= new Intent();
        intent.putExtra("result= ", mName+ ", "+ mPhone );

        setResult(RESULT_OK, intent);

        // This activity ends
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_target, menu);
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
