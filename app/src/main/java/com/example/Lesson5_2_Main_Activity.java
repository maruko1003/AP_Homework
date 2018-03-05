//http://gavinliu.cn/2014/03/18/Android---%E4%BD%BF%E7%94%A8Loader%E8%BF%9B%E8%A1%8C%E6%95%B0%E6%8D%AE%E5%8A%A0%E8%BD%BD/
//https://my.oschina.net/chaosWL/blog/78465
//http://brianchen85.blogspot.tw/2015/04/android-httpurlconnection-get.html

package com.example.aphomework;


import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Lesson5_2_Main_Activity extends Activity implements LoaderCallbacks<List<String>> {

    private static final String TAG = "[HomeWork][Lesson5][2]";
    private Button btn_get_resource;
    private EditText et_input_url;
    private String str_get_url;
    private TextView tv_html_content;
    public String getInputURL;


    private TextView mAuthorText;
    private SimpleAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "*onCreate, START");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5_2);

        //Check if a Loader is running, if it is, reconnect to it
//        if(getLoaderManager().getLoader(0)!=null){
//            Log.d(TAG, "*onCreate, Enter getLoaderManager");
//            getLoaderManager().initLoader(0,null,this);
//        }

        et_input_url = (EditText) findViewById(R.id.et_l5_2_input_url);
        btn_get_resource = (Button)findViewById(R.id.btn_l5_2_get_resource);
        tv_html_content = (TextView) findViewById(R.id.tv_l5_2_html_content);
        Log.d(TAG, "*onCreate, Enter getLoaderManager---End");


        //getLoaderManager().initLoader(0, null, this); //getSupportLoaderManager
        //Check if a Loader is running, if it is, reconnect to it
        if(getLoaderManager().getLoader(0)!=null){
            Log.d(TAG, "*onCreate, Enter getLoaderManager");
            getLoaderManager().initLoader(0,null,this);
        }
    }


    public void getTips(View view) {
        Log.d(TAG, "*getTips");
        //Creating the instance of PopupMenu
        PopupMenu popup = new PopupMenu(Lesson5_2_Main_Activity.this, et_input_url);
        //Inflating the Popup using xml file
        popup.getMenuInflater()
                .inflate(R.menu.menu_lesson5_2, popup.getMenu());

        //registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                //set selected string of item in EditText
                et_input_url.setText(item.getTitle());
                Toast.makeText(
                        Lesson5_2_Main_Activity.this,
                        "You Clicked : " + item.getTitle(),
                        Toast.LENGTH_SHORT
                ).show();
                return true;
            }
        });

        popup.show(); //showing popup menu

    }

    // when click GET RESOURCE Button will call this method
    public void getHTMLResource(View view) {
        Log.d(TAG, "*getHTMLResource");
        // Get the search string from the input field.
        //String queryString = mBookInput.getText().toString();
        str_get_url = et_input_url.getText().toString();
        Log.d(TAG, "*getHTMLResource, str_get_url = " + str_get_url);

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is active and the search field is not empty,
        // add the search term to the arguments Bundle and start the loader.
        if (networkInfo != null && networkInfo.isConnected() && str_get_url.length()!=0) {
            Log.d(TAG, "*getHTMLResource, Enter if");
//            mAuthorText.setText("");
            //mTitleText.setText(R.string.loading);
//            tv_html_content.setText("No Input URL");
            Bundle queryBundle = new Bundle();
            //queryBundle.putString("queryString", queryString);
            queryBundle.putString("inputString",str_get_url);
            //getSupportLoaderManager().restartLoader(0, queryBundle, this);
            //getSupportLoaderManager().restartLoader(0, queryBundle, this);
            getLoaderManager().restartLoader(0, queryBundle, this);
            Log.d(TAG, "*getHTMLResource, , Enter if -----END");
        }
        // Otherwise update the TextView to tell the user there is no connection or no search term.
        else {
            Log.d(TAG, "*getHTMLResource, Enter else");
            if (str_get_url.length() == 0) {
                Log.d(TAG, "*getHTMLResource, Enter else + if");
                //mAuthorText.setText("");
                //mTitleText.setText(R.string.no_search_term);
                tv_html_content.setText("no input URL");
            } else {
                Log.d(TAG, "*getHTMLResource, Enter else + else");
                //mAuthorText.setText("");
                //mTitleText.setText(R.string.no_network);
                tv_html_content.setText("no network");
            }
        }
    }


    ////////////////////////////////////////////////////
    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "*onCreateLoader");
        //return null;
        return new StringListLoader(this, args.getString("inputString"));
    }

     @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
         Log.d(TAG, "*onLoadFinished");
         tv_html_content.setText(data.get(0));
    }

    @Override
    public void onLoaderReset(final Loader <List<String>> loader) {
        Log.d(TAG, "*onLoaderReset");
    }
}


