package com.example.aphomework;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class Lesson4_1_Main_Activity extends AppCompatActivity {

    private static final String TAG = "[HomeWork][Lesson4][1]";
    private CheckBox cb_chocolate;
    private CheckBox cb_sprinkles;
    private CheckBox cb_crashed;
    private CheckBox cb_cherries;
    private CheckBox cb_orio;
    private Button btn_toast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_1);

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson4-1");

        // when checkbox are found, trigger corresponding action
        cb_chocolate = (CheckBox)findViewById(R.id.cb_l4_1_chocolate);
        cb_sprinkles = (CheckBox)findViewById(R.id.cb_l4_2_sprinkles);
        cb_crashed = (CheckBox)findViewById(R.id.cb_l4_3_crashed);
        cb_cherries = (CheckBox)findViewById(R.id.cb_l4_4_cherries);
        cb_orio = (CheckBox)findViewById(R.id.cb_l4_5_orio);

        // when button is clicked, show toast
        btn_toast = (Button)findViewById(R.id.btn_l4_1_showtoast);

        // use onSubmit() in layout instead of setOnClickListener
        /*
        btn_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total_str = "Toppings: ";
                if(cb_chocolate.isChecked()){
                    total_str += "\t" + cb_chocolate.getText().toString();
                }
                if(cb_sprinkles.isChecked()){
                    total_str += "\t" + cb_sprinkles.getText().toString();
                }
                if(cb_crashed.isChecked()){
                    total_str += "\t" + cb_crashed.getText().toString();
                }
                if(cb_cherries.isChecked()){
                    total_str += "\t" + cb_cherries.getText().toString();
                }
                if(cb_orio.isChecked()){
                    total_str += "\t" + cb_orio.getText().toString();
                }
                Toast.makeText(getApplicationContext(), total_str, Toast.LENGTH_SHORT).show();
            }
        });
        */

    }

    // show toast once anyone checkbox is selected
    public void onCheckboxClicked(View view) {

        Log.d(TAG,"[onCheckboxClicked]Get checkbox string");

        String checkbox_str = new String();

        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cb_l4_1_chocolate:
                //if (checked)
                //// Put some meat on the sandwich
                    //checkbox_str = cb_chocolate.getText().toString();
                //else
                    //Log.d(TAG,"chocolate checkbox not selected");
                checkbox_str = checked?cb_chocolate.getText().toString():cb_chocolate.getText().toString()+" Deselected";
                break;
            case R.id.cb_l4_2_sprinkles:
                //if (checked)
                    //// Put some meat on the sandwich
                    //checkbox_str = cb_sprinkles.getText().toString();
                //else
                    //Log.d(TAG,"sprinkls checkbox not selected ");
                checkbox_str = checked?cb_sprinkles.getText().toString():cb_sprinkles.getText().toString()+" Deselected";
                break;
            case R.id.cb_l4_3_crashed:
                //if (checked)
                    //// Put some meat on the sandwich
                    //checkbox_str = cb_crashed.getText().toString();
                //else
                    //Log.d(TAG,"crashed checkbox not selected ");
                checkbox_str = checked?cb_crashed.getText().toString():cb_crashed.getText().toString()+" Deselected";
                break;
            case R.id.cb_l4_4_cherries:
                //if (checked)
                    //// Put some meat on the sandwich
                    //checkbox_str = cb_cherries.getText().toString();
                //else
                    //Log.d(TAG,"cherries checkbox not selected ");
                checkbox_str = checked?cb_cherries.getText().toString():cb_cherries.getText().toString()+" Deselected";
                break;
            case R.id.cb_l4_5_orio:
                //if (checked)
                    //// Put some meat on the sandwich
                    //checkbox_str =  cb_orio.getText().toString();
                //else
                    //Log.d(TAG,"orio checkbox not selected ");
                checkbox_str = checked?cb_orio.getText().toString():cb_orio.getText().toString()+" Deselected";
                break;
            // TODO: Veggie sandwich
        }
        Log.d(TAG,"Selected checkbox string : " + checkbox_str);
        Toast.makeText(getApplicationContext(), checkbox_str, Toast.LENGTH_SHORT).show();
    }

    // show total toast when clicked "SHOW TOAST" button
    public void onSubmit(View view) {

        Log.d(TAG, "[onSubmit] Start show total toast");
        String total_str = "Toppings: ";

        if(cb_chocolate.isChecked()){
            total_str += "\t" + cb_chocolate.getText().toString();
        }
        if(cb_sprinkles.isChecked()){
            total_str += "\t" + cb_sprinkles.getText().toString();
        }
        if(cb_crashed.isChecked()){
            total_str += "\t" + cb_crashed.getText().toString();
        }
        if(cb_cherries.isChecked()){
            total_str += "\t" + cb_cherries.getText().toString();
        }
        if(cb_orio.isChecked()){
            total_str += "\t" + cb_orio.getText().toString();
        }
        Toast.makeText(getApplicationContext(), total_str, Toast.LENGTH_SHORT).show();
    }
}

