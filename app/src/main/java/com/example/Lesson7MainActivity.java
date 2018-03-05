package com.example.aphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Lesson7MainActivity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson7]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7_main);
        Log.d(TAG,"onCreate");

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson7");
    }

    public void onButtonClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_l7_1_notification:
                Log.d(TAG,"Start Notification");
                //Intent intent_launch_l7_1 = new Intent(Lesson7MainActivity.this, Lesson7_1_Main_Activity.class);
                Intent intent_launch_l7_1 = new Intent(Lesson7MainActivity.this, Lesson7_1_Main_Activity_Lite.class);
                startActivity(intent_launch_l7_1);
                break;
            case R.id.btn_l7_2_contact:
                Log.d(TAG,"Start Contacts List");
                Intent intent_launch_l7_2 = new Intent(Lesson7MainActivity.this, Lesson7_2_Main_Activity.class);
                startActivity(intent_launch_l7_2);
                break;
        }
    }

}
