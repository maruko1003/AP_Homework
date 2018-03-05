package com.example.aphomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Lesson4_4_Main_Activity extends AppCompatActivity {
    private static final String TAG = "[HomeWork][Lesson4][4]";
    private RecyclerView recyclerView;
    private List<News> newsList;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson4_4);
        Log.d(TAG,"*onCreate");

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson4-4");

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView);

        // insert data to each item and generate the list
        initPersonData();

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter
        adapter=new RecyclerViewAdapter(newsList,Lesson4_4_Main_Activity.this);
        recyclerView.setAdapter(adapter);

    }

    private void initPersonData() {
        newsList =new ArrayList<>();
        //添加新闻
        newsList.add(new News(getString(R.string.news_one_title),getString(R.string.news_one_desc),R.drawable.news_1));
        newsList.add(new News(getString(R.string.news_two_title),getString(R.string.news_two_desc),R.drawable.news_2));
        newsList.add(new News(getString(R.string.news_three_title),getString(R.string.news_three_desc),R.drawable.news_3));
        newsList.add(new News(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.drawable.news_4));
        newsList.add(new News(getString(R.string.news_five_title),getString(R.string.news_five_desc),R.drawable.news_5));


    }
}
