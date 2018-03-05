package com.example.aphomework;

/**
 * Created by Jane_Chiang on 2018/2/26.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by ifanr on 2015/3/29.
 */
public class Lesson6_TaskRepo {
    private static final String TAG = "[HomeWork][Lesson6][Lesson6_TaskRepo]";
    private Lesson6_DBHelper dbHelper;

    public Lesson6_TaskRepo(Context context){
        Log.d(TAG, "*Start Lesson6_TaskRepo");
        dbHelper=new Lesson6_DBHelper(context);
    }

    public int insert(Lesson6_Task task){
        Log.d(TAG, "*Start insert");
        //打开连接，写入数据
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(Lesson6_Task.KEY_age,student.age);
        //values.put(Lesson6_Task.KEY_email,student.email);
        //values.put(Lesson6_Task.KEY_name,student.name);

        values.put(Lesson6_Task.KEY_create_date,task.create_date);
        values.put(Lesson6_Task.KEY_complete_date,task.complete_date);
        values.put(Lesson6_Task.KEY_status,task.status);
        values.put(Lesson6_Task.KEY_description,task.description);

        long student_Id=db.insert(Lesson6_Task.TABLE,null,values);
        db.close();
        return (int)student_Id;
    }

    public void delete(int task_ID){
        Log.d(TAG, "*Start delete");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        db.delete(Lesson6_Task.TABLE,Lesson6_Task.KEY_ID+"=?", new String[]{String.valueOf(task_ID)});
        db.close();
    }
    public void update(Lesson6_Task task){
        Log.d(TAG, "*Start update");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        //values.put(Lesson6_Task.KEY_age,student.age);
        //values.put(Lesson6_Task.KEY_email,student.email);
        //values.put(Lesson6_Task.KEY_name,student.name);

        values.put(Lesson6_Task.KEY_create_date,task.create_date);
        values.put(Lesson6_Task.KEY_complete_date,task.complete_date);

        // Modify Create Date after user modified Status to Completed(the creation date is replaced with the completion date)
        String tmp_status = task.complete_date.toString();
        //if (tmp_status.equals("Completed")){
        //    values.put(Lesson6_Task.KEY_create_date,task.complete_date);
        //}

        values.put(Lesson6_Task.KEY_status,task.status);
        values.put(Lesson6_Task.KEY_description,task.description);

        Log.d(TAG, "*update, task_ID = " + task.task_ID);
        Log.d(TAG, "*update, description = " + task.description);
        Log.d(TAG, "*update, create_date = " + task.create_date);
        Log.d(TAG, "*update, complete_date = " + task.complete_date);
        Log.d(TAG, "*update, status = " + task.status);
        Log.d(TAG, "*update, =====================================================");


        db.update(Lesson6_Task.TABLE,values,Lesson6_Task.KEY_ID+"=?",new String[] { String.valueOf(task.task_ID) });
        db.close();
    }

    public ArrayList<HashMap<String, String>> getTaskList(String condition) throws ParseException {
        Log.d(TAG, "*Start getAllData");
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        String selectQuery="SELECT "+
                Lesson6_Task.KEY_ID+","+
                Lesson6_Task.KEY_description+","+
                Lesson6_Task.KEY_create_date+","+
                Lesson6_Task.KEY_complete_date+","+
                Lesson6_Task.KEY_status+" FROM "+Lesson6_Task.TABLE;
        ArrayList<HashMap<String,String>> taskList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            boolean task_status = true;
            do{
                // get information for showing in UI
                HashMap<String,String> task=new HashMap<String,String>();
                task.put("id",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_ID)));
                task.put("description",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description)));
                task.put("create_date",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                task.put("complete_date",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                task.put("status",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status)));
                Log.d(TAG, "getTaskList, id = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_ID)));

                // According to spec, skip check "status = Complete" for update status
                String tmp_status = cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status));
                if (tmp_status.equals("Completed")) {
                    Log.d(TAG, "*getTaskList, Skip update tasklist due to user has modify the status to Completed");
                    //taskList.add(task);
                }else{
                    // Compare create and complete Date for update status
                    try {
                        // Update Status
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        //取的兩個時間
                        Log.d(TAG, "*Start getTaskList, create = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                        Log.d(TAG, "*Start getTaskList, complete = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                        Date create_dt = sdf.parse(cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                        Date complete_dt = sdf.parse(cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                        Date current_dt = sdf.parse(getCurrentDateTime());

                        //取得兩個時間的Unix時間
                        Long create_date = create_dt.getTime();
                        Long complete_date = complete_dt.getTime() + (1000*60*60*24); // system will set to current Date with 00:00:00 so add one date
                        Long current_date = current_dt.getTime() + (1000*60*60*24); // system will set to current Date with 00:00:00 so add one date

                        Log.d(TAG, "*Start getTaskList, create_date = " + create_date);
                        Log.d(TAG, "*Start getTaskList, complete_date = " + complete_date);
                        Log.d(TAG, "*Start getTaskList, current_date = " + current_date);

                        //Long timeP = complete_date - create_date;//毫秒差
                        Long timeP = complete_date - current_date;//毫秒差
                        Log.d(TAG, "*Start getTaskList, timeP = " + timeP); // add this log can increase success rate
                        if (timeP > 0) {
                            values.put(Lesson6_Task.KEY_status, "In Progress");
                            task_status = false;
                        } else {
                            values.put(Lesson6_Task.KEY_status, "Completed");
                            task_status = true;
                        }
                        db.update(Lesson6_Task.TABLE, values, Lesson6_Task.KEY_ID + "=?", new String[]{String.valueOf(cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_ID)))});

                        task.put("status", cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status)));

                        Log.d(TAG, "getTaskList, description = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description)));
                        Log.d(TAG, "getTaskList, create_date = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                        Log.d(TAG, "getTaskList, complete_date = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                        Log.d(TAG, "getTaskList, status = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status)));

                    }catch (ParseException e) {
                        Log.d(TAG, "*getTaskList, DB exist incorrect Date Format");
                        e.printStackTrace();
                    }
                }

                // only add status equals to "In progress" to list for showing in UI
                String current_status = cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status));
                if (condition.equals("completed") && (task_status)) {
                    taskList.add(task);
                }else if (condition.equals("inprogress") && (!task_status)) {
                    taskList.add(task);
                }else if (condition.equals("all")){
                    taskList.add(task);
                }else{
                    Log.d(TAG, "*getTaskList, " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description)) + " status and condition not equal");
                }

            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }



    public ArrayList<HashMap<String, String>> getTaskListWithoutUpdateStatus(){
        Log.d(TAG, "*Start ArrayList");
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //String selectQuery="SELECT "+
        //        Lesson6_Task.KEY_ID+","+
        //        Lesson6_Task.KEY_name+","+
        //        Lesson6_Task.KEY_email+","+
        //        Lesson6_Task.KEY_age+" FROM "+Lesson6_Task.TABLE;

        String selectQuery="SELECT "+
                Lesson6_Task.KEY_ID+","+
                Lesson6_Task.KEY_description+","+
                Lesson6_Task.KEY_create_date+","+
                Lesson6_Task.KEY_complete_date+","+
                Lesson6_Task.KEY_status+" FROM "+Lesson6_Task.TABLE;
        ArrayList<HashMap<String,String>> taskList=new ArrayList<HashMap<String, String>>();
        Cursor cursor=db.rawQuery(selectQuery,null);

        if(cursor.moveToFirst()){
            do{
                // get information for showing in UI
                HashMap<String,String> task=new HashMap<String,String>();
                task.put("id",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_ID)));
                task.put("description",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description)));
                task.put("create_date",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                task.put("complete_date",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                task.put("status",cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status)));
                Log.d(TAG, "id = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_ID)));
                Log.d(TAG, "description = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description)));
                Log.d(TAG, "create_date = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date)));
                Log.d(TAG, "complete_date = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date)));
                Log.d(TAG, "status = " + cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status)));
                taskList.add(task);
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    public Lesson6_Task getTaskById(int Id){
        Log.d(TAG, "*Start getTaskById");
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        //String selectQuery="SELECT "+
        //        Lesson6_Task.KEY_ID + "," +
        //        Lesson6_Task.KEY_name + "," +
        //        Lesson6_Task.KEY_email + "," +
        //        Lesson6_Task.KEY_age +
        //        " FROM " + Lesson6_Task.TABLE
        //        + " WHERE " +
        //        Lesson6_Task.KEY_ID + "=?";

        String selectQuery="SELECT "+
                Lesson6_Task.KEY_ID + "," +
                Lesson6_Task.KEY_description + "," +
                Lesson6_Task.KEY_create_date + "," +
                Lesson6_Task.KEY_complete_date + "," +
                Lesson6_Task.KEY_status +
                " FROM " + Lesson6_Task.TABLE
                + " WHERE " +
                Lesson6_Task.KEY_ID + "=?";

        int iCount=0;
        Lesson6_Task task=new Lesson6_Task();
        Cursor cursor=db.rawQuery(selectQuery,new String[]{String.valueOf(Id)});
        if(cursor.moveToFirst()){
            do{
                //task.student_ID =cursor.getInt(cursor.getColumnIndex(Lesson6_Task.KEY_ID));
                //task.name =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_name));
                //task.email  =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_email));
                //task.age =cursor.getInt(cursor.getColumnIndex(Lesson6_Task.KEY_age));

                task.task_ID =cursor.getInt(cursor.getColumnIndex(Lesson6_Task.KEY_ID));
                task.description =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_description));
                task.create_date  =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_create_date));
                task.complete_date  =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date));
                //task.create_date  = cursor.getInt(cursor.getColumnIndex(Lesson6_Task.KEY_create_date));
                //task.complete_date  = cursor.getInt(cursor.getColumnIndex(Lesson6_Task.KEY_complete_date));
                task.status =cursor.getString(cursor.getColumnIndex(Lesson6_Task.KEY_status));
            }while(cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return task;
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
}

