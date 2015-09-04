package com.sb.android.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityExamActivity extends Activity implements View.OnClickListener {

    public static final int REQUEST_CODE_STRING = 0;

    private EditText mNameEditText;
    private EditText mPhooneEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_exam);
        findViewById(R.id.button1).setOnClickListener(this);

        mNameEditText= (EditText)findViewById(R.id.name_edit_text);
        mPhooneEditText= (EditText)findViewById(R.id.phone_edit_text);

        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.dialog_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button1:
                button1Click();
                break;

            case R.id.button2:
                button2Click();
                break;
            case R.id.dialog_button:
                openDialog();
                break;
        }

    }

    private void openDialog() {

        AlertDialog.Builder builder= new AlertDialog.Builder(ActivityExamActivity.this);

        builder.setTitle("Title");
        builder.setMessage("Message");
        builder.setPositiveButton("Confirm", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(ActivityExamActivity.this, "Pressed Confirm", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Close", null);

        builder.setIcon(R.mipmap.ic_launcher);

        builder.show();

    }

    private void button1Click() {
        //
        Intent intent= new Intent(getApplicationContext(), TargetActivity.class);

        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhooneEditText.getText().toString());

        startActivity(intent);
    }

    private void button2Click() {
        //
        Intent intent= new Intent(getApplicationContext(), TargetActivity.class);

        intent.putExtra("name", mNameEditText.getText().toString());
        intent.putExtra("phone", mPhooneEditText.getText().toString());

        // Call and get return value
        startActivityForResult(intent, REQUEST_CODE_STRING);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode== RESULT_OK) {
            if(requestCode== REQUEST_CODE_STRING) {
                if(data!= null) {
                    String result= data.getStringExtra(("result"));
                    Toast.makeText(ActivityExamActivity.this, "Result: "+ result, Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            Toast.makeText(ActivityExamActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
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
}
