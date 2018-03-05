package com.example.aphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class Lesson4_3_Main_Activity extends AppCompatActivity {

    private static final String TAG = "[HomeWork][Lesson4][3]";
    private ImageButton ibtn_1;
    private ImageButton ibtn_2;
    private ImageButton ibtn_3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_3);
        Log.d(TAG,"Start");

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson4-3");

    }

    // show toast once anyone checkbox is selected
    public void onImageButtonClicked(View view) {
        Log.d(TAG,"onImageButtonClicked");
        String click_id = "";

       Intent intent_launch_l4_3_Nav_Up = new Intent(Lesson4_3_Main_Activity.this, Lesson4_3_Nav_Up_Activity.class);
        //intent_launch_l4_3_Nav_Up.putExtra("title",click_id);
        startActivity(intent_launch_l4_3_Nav_Up);

        //Disable below code due to use simple workflow
        //switch (view.getId()) {
        //    case R.id.l4_3_ibtn_1:
        //        Log.d(TAG,"[onImageButtonClicked]Selected the first image button");
        //        Intent intent_launch_l4_3_1 = new Intent(Lesson4_3_Main_Activity.this, Lesson4_3_1_Activity.class);
        //        startActivity(intent_launch_l4_3_1);
        //        break;
        //    case R.id.l4_3_ibtn_2:
        //        Log.d(TAG,"[onImageButtonClicked]Selected the second image button");
        //        Intent intent_launch_l4_3_2 = new Intent(Lesson4_3_Main_Activity.this, Lesson4_3_2_Activity.class);
        //        startActivity(intent_launch_l4_3_2);
        //        Log.d(TAG,"[onImageButtonClicked]second image button startActivity finished");
        //        break;
        //    case R.id.l4_3_ibtn_3:
        //        Log.d(TAG,"[onImageButtonClicked]Selected the third image button");
        //        Intent intent_launch_l4_3_3 = new Intent(Lesson4_3_Main_Activity.this, Lesson4_3_3_Activity.class);
        //        startActivity(intent_launch_l4_3_3);
        //        Log.d(TAG,"[onImageButtonClicked]third image button startActivity finished");
        //        break;
        //}

    }
}
