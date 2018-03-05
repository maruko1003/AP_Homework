package com.example.aphomework;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jane_Chiang on 2018/2/14.
 */

public class StringListLoader  extends AsyncTaskLoader<List<String>> {

    private String TAG = "[HomeWork][Lesson5][2][Lesson5_2_StringListLoader]";
    private String mQueryString;

    public StringListLoader(Context context, String queryString){
        super(context);
        Log.d(TAG, "*StringListLoader");
        mQueryString = queryString;
        Log.d(TAG, "*StringListLoader, mQueryString = " + mQueryString);
    }

    protected void onStartLoading(){
        Log.d(TAG, "*onStartLoading");
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        Log.d(TAG, "*loadInBackground");
        Log.d(TAG, "*loadInBackground, URL = " + mQueryString);
        List<String> url_list = new ArrayList<String>();

//        List<String> data = new ArrayList<String>;
        //TODO: Load the data from the network or from a database

        URL url = null;
        BufferedReader reader = null;
        StringBuilder stringBuilder;

        try
        {
            // create the HttpURLConnection
            //url = new URL("http://www.google.com");
            url = new URL(mQueryString);
            //url = new URL(str_get_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 使用甚麼方法做連線
            connection.setRequestMethod("GET");

            // 是否添加參數(ex : json...等)
            //connection.setDoOutput(true);

            // 設定TimeOut時間
            connection.setReadTimeout(15*1000);
            connection.connect();

            // 伺服器回來的參數
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            Log.d(TAG, "HTML Content = " + stringBuilder.toString());
            url_list.add(stringBuilder.toString());
            Log.d(TAG, "loadInBackground = " + url_list.get(0));

            //return stringBuilder.toString();
            return url_list;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // close the reader; this can throw an exception too, so
            // wrap it in another try/catch block.
            if (reader != null)
            {
                try
                {
                    reader.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }
}

