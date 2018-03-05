package com.example.aphomework;

import java.util.Date;

/**
 * Created by Jane_Chiang on 2018/2/26.
 */

public class Lesson6_Task {

    //表名
    public static final String TABLE="Task";

    //表的各域名
    public static final String KEY_ID="id";
    public static final String KEY_name="name";
    public static final String KEY_email="email";
    public static final String KEY_age="age";

    public static final String KEY_description="description";
    public static final String KEY_create_date="create_date";
    public static final String KEY_complete_date="complete_date";
    public static final String KEY_status="status";


    //属性
    public int student_ID;
    public String name;
    public String email;
    public int age;

    public int task_ID;
    public String description = "";
    public String create_date = "";
    public String complete_date = "";
    public String status = "In Progress";


}
