package com.sb.android.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by student on 2015-09-24.
 */
public class ParcelableActivity  extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = new Button(this);
        button.setText("parcl data send");

        setContentView(button);

        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent= new Intent(this, TargetActivity.class);

        TodoItem todoitem= new TodoItem(1, "2015-9-25", "3", "3", "todo","2");
        intent.putExtra("todo", todoitem);
        startActivity(intent);
    }
}
