package com.example.aphomework;

/**
 * Created by Jane_Chiang on 2018/2/26.
 */

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.util.Log;

public class Lesson6_DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "[HomeWork][Lesson6][Lesson6_DBHelper]";
    //数据库版本号
    private static final int DATABASE_VERSION=4;

    //数据库名称
    private static final String DATABASE_NAME="crud.db";

    public Lesson6_DBHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "*Start onCreate");
        //创建数据表
        //String CREATE_TABLE_STUDENT="CREATE TABLE "+ Lesson6_Task.TABLE+"("
        //        +Lesson6_Task.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
        //        +Lesson6_Task.KEY_name+" TEXT, "
        //        +Lesson6_Task.KEY_age+" INTEGER, "
        //        +Lesson6_Task.KEY_email+" TEXT)";
        //db.execSQL(CREATE_TABLE_STUDENT);
        String CREATE_TABLE_TASK="CREATE TABLE "+ Lesson6_Task.TABLE+"("
                +Lesson6_Task.KEY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT ,"
                +Lesson6_Task.KEY_description+" TEXT, "
                +Lesson6_Task.KEY_create_date+" INTEGER, "
                +Lesson6_Task.KEY_complete_date+" INTEGER, "
                +Lesson6_Task.KEY_status+" BOOLEAN)";
        db.execSQL(CREATE_TABLE_TASK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "*Start onUpgrade");
        //如果旧表存在，删除，所以数据将会消失
        db.execSQL("DROP TABLE IF EXISTS "+ Lesson6_Task.TABLE);

        //再次创建表
        onCreate(db);
    }

}
