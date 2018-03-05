package com.example.aphomework;


import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


//public class activity_lesson6_task_detail extends ActionBarActivity implements View.OnClickListener {
//public class Lesson6_Task_Detail implements View.OnClickListener {
public class Lesson6_Task_Detail extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson6][Lesson6_Task_Detail]";
    private Button btnSave,btnClose;
    private EditText etTssk;
    private EditText etCreateDate;
    private EditText etCompleteDate;
    private EditText etStatus;
    private int _task_id=0;
    private String _disable_focus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6_task_detail);
        Log.d(TAG, "*Start onCreate");
        btnSave = (Button) findViewById(R.id.btn_l6_Save);
        //btnDelete = (Button) findViewById(R.id.btnDelete);
        btnClose = (Button) findViewById(R.id.btn_l6_close);

        etTssk = (EditText) findViewById(R.id.et_l6_task_content);
        etCreateDate = (EditText) findViewById(R.id.et_l6_create_date_content);
        etCompleteDate = (EditText) findViewById(R.id.et_l6_complete_date_content);
        etStatus = (EditText) findViewById(R.id.et_l6_status);

        // disable status textview focus to avoid modify
        Intent intent = getIntent();
        _disable_focus =intent.getStringExtra("disable_focus");
        //if (_disable_focus != null && _disable_focus.equals("true")) {
        //    Log.d(TAG, "*Start onCreate, disable focus function for status");
        //    etStatus.setFocusable(false);
        //}

        //btnSave.setOnClickListener(this);
        //btnClose.setOnClickListener(this);
        _task_id =0;
        //Intent intent = getIntent();
        _task_id =intent.getIntExtra("task_Id", 0);
        Lesson6_TaskRepo repo = new Lesson6_TaskRepo(this);
        Lesson6_Task task = new Lesson6_Task();
        task = repo.getTaskById(_task_id);

        // set task description with default ""
        etTssk.setText(String.valueOf(task.description));

        // set create time and disable modification in xml(focusable = false)
        if (task.create_date.equals("")) { // only add task need to set current date as default value
            Log.d(TAG, "*Start onCreate, Set current date as the default to show in detail page");
            etCreateDate.setText(getCurrentDateTime());
        }else{
            Log.d(TAG, "*Start onCreate, , Get create date from DB to show in detail page");
            etCreateDate.setText(String.valueOf(task.create_date));
        }

        // set task complete date with default ""
        etCompleteDate.setText(String.valueOf(task.complete_date));

        // set status with default "In Progress"
        etStatus.setText(task.status);



    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_detail, menu);
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
*/

    //@Override
    //public void onClick(View v) {
    public void SelectNewTask(View v) {
        Log.d(TAG, "*SelectNewTask");
        if(v==findViewById(R.id.btn_l6_Save)){
            Log.d(TAG, "*SelectNewTask Save");
            Lesson6_TaskRepo repo=new Lesson6_TaskRepo(this);
            Lesson6_Task task=new Lesson6_Task();

            //task.status=Integer.parseInt(etStatus.getText().toString());
            task.status = etStatus.getText().toString();

            //task.complete_date=Integer.parseInt(etCompleteDate.getText().toString());
            //task.complete_date=etCompleteDate.getText().toString();
            //task.create_date=etCreateDate.getText().toString();

            // Modify Create Date after user modified Status to Completed(the creation date is replaced with the completion date)
            //if ((task.status).equals("Completed")) {
            //    task.create_date = etCompleteDate.getText().toString();
            //}else{
                task.create_date=etCreateDate.getText().toString();
            //}


            task.complete_date=etCompleteDate.getText().toString();
            try {
                convertDateTimeFormat(task.complete_date);
            }catch (ParseException e) {
                Log.d(TAG, "*SelectNewTask, User Input incorrect Date Format");
                Toast.makeText(this,"User Input incorrect Date Format(yyyy/MM/dd)",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
                // use return to avoid data to be added into DB
                return;
            }

            task.description=etTssk.getText().toString();

            task.task_ID =_task_id;

            if(_task_id==0){
                _task_id=repo.insert(task);
                Toast.makeText(this,"New Task Insert",Toast.LENGTH_SHORT).show();
            }else{

                // Modify Create Date after user modified Status to Completed(the creation date is replaced with the completion date)
                if ((task.status).equals("Completed")) {
                    task.create_date = etCompleteDate.getText().toString();
                }
                repo.update(task);
                Toast.makeText(this,"Task Record updated",Toast.LENGTH_SHORT).show();
            }
            finish();
        ///}else if (v== findViewById(R.id.btnDelete)){
        //    StudentRepo repo = new StudentRepo(this);
        //    repo.delete(_student_id);
        //    Toast.makeText(this, "Student Record Deleted", Toast.LENGTH_SHORT);
        //    finish();
        }else if (v== findViewById(R.id.btn_l6_close)){
            Log.d(TAG, "*SelectNewTask Close");
            finish();
        }
    }

    public String getCurrentDateTime(){
        Log.d(TAG, "*getCurrentDateTime");
        //先行定義時間格式
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //取得現在時間
        Date dt=new Date();

        //透過SimpleDateFormat的format方法將Date轉為字串
        String dts=sdf.format(dt);
        Log.d(TAG, "*getCurrentDateTime, create Date Time = " + dts);
        return  dts;
    }

    public String convertDateTimeFormat(String dataTime) throws ParseException {
        Date convert_date;

        //定義好時間字串的格式
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

        //將字串轉成Date型
        convert_date = sdf.parse(dataTime);
        Log.d(TAG, "*convertDateTimeFormat, User input correct format");

        //透過SimpleDateFormat的format方法將Date轉為字串
        String dts=sdf.format(convert_date);
        Log.d(TAG, "*convertDateTimeFormat, convert date format result = " + dts);

        //新增一個Calendar,並且指定時間
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(convert_date);
        //calendar.add(Calendar.MONTH, 2);//月份+2
        //calendar.add(Calendar.HOUR, -1);//小時-1
        //Date tdt=calendar.getTime();//取得加減過後的Date

        //依照設定格式取得字串
        //String time=sdf.format(tdt);
        return dts;
    }

    // pop up menu for user to select
    public void getTips(View view) {
        Log.d(TAG, "*getTips");
        // only pop up menu list when modify status
        if (_disable_focus != null && _disable_focus.equals("true")) {
            Log.d(TAG, "*Start onCreate, disable focus function for status");
            etStatus.setFocusable(false);
        }else{
            //Creating the instance of PopupMenu
            PopupMenu popup = new PopupMenu(Lesson6_Task_Detail.this, etStatus);
            //Inflating the Popup using xml file
            popup.getMenuInflater()
                    .inflate(R.menu.menu_lesson6, popup.getMenu());

            //registering popup with OnMenuItemClickListener
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                public boolean onMenuItemClick(MenuItem item) {
                    //set selected string of item in EditText
                    etStatus.setText(item.getTitle());
                    Toast.makeText(
                            Lesson6_Task_Detail.this,
                            "You Clicked : " + item.getTitle(),
                            Toast.LENGTH_SHORT
                    ).show();
                    return true;
                }
            });

            popup.show(); //showing popup menu

        }

    }

}

