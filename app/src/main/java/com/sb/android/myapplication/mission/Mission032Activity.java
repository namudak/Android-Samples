package com.sb.android.myapplication.mission;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sb.android.myapplication.R;

public class Mission032Activity extends AppCompatActivity implements View.OnClickListener{

    private String mName;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mission032);

        // Get intent's data
        mName = getIntent().getStringExtra("name");
        mPassword= getIntent().getStringExtra("password");

        // Display result
        Toast.makeText(Mission032Activity.this, "name: " + mName + " password: " + mPassword,
                Toast.LENGTH_SHORT).show();

        findViewById(R.id.customer_button).setOnClickListener(this);
        findViewById(R.id.sales_button).setOnClickListener(this);
        findViewById(R.id.product_button).setOnClickListener(this);
        findViewById(R.id.close_menu_button).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        String result= null;
//
//        switch (v.getId()){
//            case R.id.customer_button:
//                result= mCustomerButton.getText().toString();
//
//                break;
//            case R.id.sales_button:
//                result= mSalesButton.getText().toString();
//                break;
//            case R.id.product_button:
//                result= mProductButton.getText().toString();
//                break;
//        }
//        // Make return data
//        Intent intent= new Intent();
//        intent.putExtra("result", result);
//
//        setResult(RESULT_OK, intent);
//
//        // This activity ends
//        finish();

        Intent intent= null;

        switch (v.getId()){
            case R.id.customer_button:
                intent= new Intent(getApplicationContext(), Mission033Activity.class);
                break;
            case R.id.sales_button:
                intent= new Intent(getApplicationContext(), Mission034Activity.class);
                break;
            case R.id.product_button:
                intent= new Intent(getApplicationContext(), Mission035Activity.class);
                break;
            case R.id.close_menu_button:
                setResult(RESULT_OK, intent);;
                finish();
                break;
        }
        
        if(intent!= null) startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mission032, menu);
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
