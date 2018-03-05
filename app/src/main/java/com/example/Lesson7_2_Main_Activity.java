package com.example.aphomework;

import android.Manifest;
import android.app.ListActivity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.WRITE_CONTACTS;

public class Lesson7_2_Main_Activity extends ListActivity {

    private static final String TAG = "[HomeWork][" + Lesson7_2_Main_Activity.class.getSimpleName() + "]";
    private static final int RequestPermissionCode = 1;

    ListView listView ;
    Cursor cursor ;
    String name, phonenumber ;
    //public  static final int RequestPermissionCode  = 1 ;
    Button button;
    ArrayList<HashMap<String, String>> StoreContacts=new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7_2);

        listView = getListView();
        button = (Button)findViewById(R.id.bt_l7_2_get_contacts_list);

        //EnableRuntimePermission();
        enableContactsPermissionStatus();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetContactsIntoArrayList();

                Log.d(TAG, "StoreContacts length = " + StoreContacts.size());
                if (StoreContacts.size() == 0) {
                    Toast.makeText(Lesson7_2_Main_Activity.this,"No Contacts on this Device.", Toast.LENGTH_LONG).show();
                }else{
                    ListAdapter adapter = new SimpleAdapter( Lesson7_2_Main_Activity.this, StoreContacts, R.layout.activity_lesson7_2_contact_item, new String[] { "name", "phone_number"}, new int[] {R.id.tv_l7_2_name, R.id.tv_l7_2_phone_number});
                    setListAdapter(adapter);  //***Jane Comment : Must extend ListActivity, ListView id must be "@android:id/list"
                }
            }
        });

    }


    public Boolean enableContactsPermissionStatus(){
        // check if app has permission to read/write contacts permission
        int permission = ActivityCompat.checkSelfPermission(this,
                READ_CONTACTS);
        Log.d(TAG,"*enableContactsPermissionStatus, Current READ_CONTACTS permission = " + permission);
        Log.d(TAG,"*enableContactsPermissionStatus, Current PackageManager.PERMISSION_GRANTED = " + PackageManager.PERMISSION_GRANTED);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG,"*enableContactsPermissionStatus, Start to pop up Contacts Permission menu");
            //未取得權限，向使用者要求允許權限
            ActivityCompat.requestPermissions( this,
                    new String[]{READ_CONTACTS, WRITE_CONTACTS},
                    RequestPermissionCode ); // Jane Comment: if you call ActivityCompat.requestPermissions in a fragment, the onRequestPermissionsResult callback is called on the activity and not the fragment.
            return false;
        }else{
            //已有權限，可進行檔案存取
            Log.d(TAG,"*enableContactsPermissionStatus, Allowed Contacts Permission");
            Toast.makeText(Lesson7_2_Main_Activity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();
            //readContacts();
            return true;
        }
    }

    public void GetContactsIntoArrayList(){
        Log.d(TAG,"*GetContactsIntoArrayList");
        cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null, null, null);

        while (cursor.moveToNext()) {

            name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phonenumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            Log.d(TAG,"*GetContactsIntoArrayList, name = " + name);
            Log.d(TAG,"*GetContactsIntoArrayList, phonenumber = " + phonenumber);

            HashMap<String,String> contact_info = new HashMap<String,String>();
            contact_info.put("name",name);
            contact_info.put("phone_number",phonenumber);
            //contact_info.put("name",name + " : " + phonenumber);
            StoreContacts.add(contact_info);
        }
        if(!cursor.isClosed()){
            cursor.close();
            cursor = null;
        }
       // cursor.close();
    }


    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                Lesson7_2_Main_Activity.this,
                Manifest.permission.READ_CONTACTS))
        {

            Toast.makeText(Lesson7_2_Main_Activity.this,"CONTACTS permission allows us to Access CONTACTS app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(Lesson7_2_Main_Activity.this,new String[]{
                    Manifest.permission.READ_CONTACTS}, RequestPermissionCode);

        }
    }


    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:
                Log.d(TAG,"*onRequestPermissionsResult, RequestPermissionCode = " + RequestPermissionCode);
                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(Lesson7_2_Main_Activity.this,"Permission Granted, Now your application can access CONTACTS.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(Lesson7_2_Main_Activity.this,"Permission Canceled, Now your application cannot access CONTACTS.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }
}



// UI will be "Contact_Name" + "Phone_Number"
/*
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="fill_parent"
    android:layout_height="wrap_content"
    android:padding="6dip">
    <TextView
        android:id="@+id/tv_l7_2_name"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_weight="1"
        android:gravity="center_vertical"
        android:textSize="18dip"/>
    <TextView
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:layout_weight="1"
        android:id="@+id/tv_l7_2_phone_number"
        android:singleLine="true"
        android:ellipsize="marquee"
        android:textSize="18dip"/>
</LinearLayout>
 */


