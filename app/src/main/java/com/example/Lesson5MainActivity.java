package com.example.aphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

public class Lesson5MainActivity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson5]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5_main);
        Log.d(TAG,"onCreate");

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson5");
    }

    public void onButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_l5_1_async_task:
                Log.d(TAG,"Start Async Task");
                Intent intent_launch_l5_1 = new Intent(Lesson5MainActivity.this, Lesson5_1_Main_Activity.class);
                startActivity(intent_launch_l5_1);
                break;
            case R.id.btn_l5_2_connect_internet:
                Log.d(TAG,"Start Async Task");
                Intent intent_launch_l5_2 = new Intent(Lesson5MainActivity.this, Lesson5_2_Main_Activity.class);
                startActivity(intent_launch_l5_2);
                break;
        }
    }

}


