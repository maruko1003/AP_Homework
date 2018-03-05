package com.example.aphomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;



import android.app.ListActivity;
import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Struct;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;


public class Lesson6MainActivity extends ListActivity implements android.view.View.OnClickListener {
    private static final String TAG = "[HomeWork][Lesson6]";
    private Button btnAdd,btnGetAll,btnGetInProgress,btnGetCompleted;
    private TextView task_Id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson6_main);
        Log.d(TAG,"onCreate");

        btnAdd = (Button) findViewById(R.id.btn_l6_add_task);
        btnAdd.setOnClickListener(this);

        btnGetAll = (Button) findViewById(R.id.btn_l6_get_all);
        btnGetAll.setOnClickListener(this);

        btnGetInProgress = (Button) findViewById(R.id.btn_l6_get_inprogress);
        btnGetInProgress.setOnClickListener(this);

        btnGetCompleted = (Button) findViewById(R.id.btn_l6_get_completed);
        btnGetCompleted.setOnClickListener(this);

        // By Spec Request
        Log.d(TAG,"*onCreate, Show In Progress Task on lesson6 Main Activity");
        try {
            Lesson6_TaskRepo repo = new Lesson6_TaskRepo(this);
            //ArrayList<HashMap<String, String>> taskList;
            ArrayList<HashMap<String, String>> taskList = repo.getTaskList("inprogress");

            if(taskList.size()!=0) {
                ListView lv = getListView();

                // Click item to modify task data
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                        task_Id = (TextView) view.findViewById(R.id.tv_l6_db_task_id);
                        String taskId = task_Id.getText().toString();
                        Log.d(TAG, "*onCreate, onItemClick taskId = " + taskId);
                        Intent objIndent = new Intent(getApplicationContext(),Lesson6_Task_Detail.class);
                        objIndent.putExtra("task_Id", Integer.parseInt( taskId));
                        startActivity(objIndent);
                    }
                });

                // set and show  which items will be shown on list
                ListAdapter adapter = new SimpleAdapter( Lesson6MainActivity.this,taskList, R.layout.activity_lesson6_view_task_entry, new String[] { "id","description","create_date","complete_date", "status"}, new int[] {R.id.tv_l6_db_task_id, R.id.tv_l6_db_description, R.id.tv_l6_db_create, R.id.tv_l6_db_complete, R.id.bt_l6_db_status});
                setListAdapter(adapter);
                Log.d(TAG, "*onCreate, Show tasklist on user interface completed");

            }else{
                Toast.makeText(this, "No Task!", Toast.LENGTH_SHORT).show();
            }

        }catch (ParseException e) {
            Log.d(TAG, "*onCreate, DB exist incorrect Date Format");
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG,"onClick");

        if (v== findViewById(R.id.btn_l6_add_task)){
            Log.d(TAG,"Add New Task");
            Intent intent = new Intent(this,Lesson6_Task_Detail.class);
            intent.putExtra("task_Id",0);
            intent.putExtra("disable_focus","true");
            startActivity(intent);

        }else {
            Lesson6_TaskRepo repo = new Lesson6_TaskRepo(this);

            //ArrayList<HashMap<String, String>> taskList =  repo.getTaskListWithoutUpdateStatus();
            try {
                //ArrayList<HashMap<String, String>> taskList =  repo.getTaskList();
                ArrayList<HashMap<String, String>> taskList;
                if (v== findViewById(R.id.btn_l6_get_inprogress)) {
                    Log.d(TAG,"*onClick, btn_l6_get_inprogress");
                    taskList = repo.getTaskList("inprogress");
                }else if (v== findViewById(R.id.btn_l6_get_completed)) {
                    Log.d(TAG, "*onClick, btn_l6_get_completed");
                    taskList = repo.getTaskList("completed");
                }else if (v== findViewById(R.id.btn_l6_get_all)) {
                    Log.d(TAG, "*onClick, btn_l6_get_all");
                    taskList = repo.getTaskList("all");
                }else{
                    Log.d(TAG, "*onClick, NOT FOUND BUTTON!!");
                    return;
                }

                if(taskList.size()!=0) {
                    ListView lv = getListView();

                    // Enable click item to modify task data
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                            task_Id = (TextView) view.findViewById(R.id.tv_l6_db_task_id);
                            String taskId = task_Id.getText().toString();
                            Log.d(TAG, "*onClick, onItemClick taskId = " + taskId);
                            Intent objIndent = new Intent(getApplicationContext(),Lesson6_Task_Detail.class);
                            objIndent.putExtra("task_Id", Integer.parseInt( taskId));
                            startActivity(objIndent);
                        }
                    });

                    // set and show  which items will be shown on list
                    ListAdapter adapter = new SimpleAdapter( Lesson6MainActivity.this,taskList, R.layout.activity_lesson6_view_task_entry, new String[] { "id","description","create_date","complete_date", "status"}, new int[] {R.id.tv_l6_db_task_id, R.id.tv_l6_db_description, R.id.tv_l6_db_create, R.id.tv_l6_db_complete, R.id.bt_l6_db_status});
                    setListAdapter(adapter);
                    Log.d(TAG, "*onClick, Show tasklist on user interface completed");
                    
                }else{

                    setListAdapter(null);
                    Toast.makeText(this, "No Task!", Toast.LENGTH_SHORT).show();
                }

            }catch (ParseException e) {
                Log.d(TAG, "*onClick, DB exist incorrect Date Format");
                e.printStackTrace();
            }

        }

    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}






//https://www.jianshu.com/p/ee08f75b407c
//https://www.jianshu.com/p/5c33be6ce89d
//https://www.jianshu.com/p/c966e4a205cc


/*
   <HorizontalScrollView
        android:id="@+id/horizontalScrollView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:scrollbars="horizontal"
        android:layout_above="@+id/btn_l6_get_completed"
        android:layout_alignParentTop="true"
        android:layout_below="@+id/rl_l6_task_title">


        <ListView
            android:id="@android:id/list"
            android:layout_width="wrap_content"
            android:layout_height="match_parent"
            android:layout_toEndOf="@+id/btn_l6_add_task" />

    </HorizontalScrollView>
 */