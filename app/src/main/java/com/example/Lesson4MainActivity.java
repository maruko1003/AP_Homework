package com.example.aphomework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class Lesson4MainActivity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson4]";
    private Button btn_lesson4_1;
    private Button btn_lesson4_2;
    private Button btn_lesson4_3;
    private Button btn_lesson4_4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_main);
        Log.d(TAG,"*onCreate");

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson4");

        // when button are found, trigger corresponding action
        btn_lesson4_1 = (Button)findViewById(R.id.btn_l4_1_input);
        btn_lesson4_2 = (Button)findViewById(R.id.btn_l4_2_menus);
        btn_lesson4_3 = (Button)findViewById(R.id.btn_l4_3_navigation);
        btn_lesson4_4 = (Button)findViewById(R.id.btn_l4_4_recycler);

        // 4.1: set OnClickListener for send intent via click button
        btn_lesson4_1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            Log.d(TAG,"Start User Input");
            Intent intent_goto_l4_1 = new Intent(Lesson4MainActivity.this, Lesson4_1_Main_Activity.class);
            startActivity(intent_goto_l4_1);
            }
        });

        // 4.2: set OnClickListener for send intent via click button
        btn_lesson4_2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Start Menus");
                Intent intent_goto_l4_2 = new Intent(Lesson4MainActivity.this, Lesson4_2_Main_Activity.class);
                startActivity(intent_goto_l4_2);
            }
        });

        // 4.3: set OnClickListener for send intent via click button
        btn_lesson4_3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Start Navigation");
                Intent intent_goto_l4_3 = new Intent(Lesson4MainActivity.this, Lesson4_3_Main_Activity.class);
                startActivity(intent_goto_l4_3);
            }
        });

        // 4.4: set OnClickListener for send intent via click button
        btn_lesson4_4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Start RecyclerView");
                Intent intent_goto_l4_4 = new Intent(Lesson4MainActivity.this, Lesson4_4_Main_Activity.class);
                startActivity(intent_goto_l4_4);
            }
        });

    }
}
