package com.example.aphomework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Lesson4_2_Main_Activity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson4][2]";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_2);

        TextView article_text = (TextView)findViewById(R.id.article);
        registerForContextMenu(article_text);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lesson4_2, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        String select_str;
        switch (item.getItemId()){
            case R.id.item_l4_2_content_edit:
                select_str = "Select EDIT";
                Log.d(TAG, select_str);
                break;
            case R.id.item_l4_2_content_share:
                select_str = "Select SHARE";
                Log.d(TAG, select_str);
                break;
            case R.id.item_l4_2_content_delete:
                select_str = "Select DELETE";
                Log.d(TAG, select_str);
                break;
            default:
                select_str = "Unknow ERROR";
                Toast.makeText(getApplicationContext(), select_str, Toast.LENGTH_SHORT).show();
                return super.onContextItemSelected(item);
        }
        Toast.makeText(getApplicationContext(), select_str, Toast.LENGTH_SHORT).show();
        return true;
    }


}
