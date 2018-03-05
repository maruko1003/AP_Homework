package com.example.aphomework;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.widget.EditText;


/**
 * 这是一个基础的数据加载Loader
 *
 * @author Gavinliu
 * @param <T>
 *
 */

public abstract class Lesson5_2_BaseDataLoade<T> extends AsyncTaskLoader<T> {
    private String TAG = "[HomeWork][Lesson5][2][Lesson5_2_BaseDataLoade]";
    private T mData;
    public String getInputURL = null;

    //public BaseDataLoader(Context context) {
    public Lesson5_2_BaseDataLoade(Context context) {
        super(context);
        Log.d(TAG, "*Start Lesson5_2_BaseDataLoade, context = " + context);

        //getInputURL = getContext().getApplicationInfo().toString();
        //Log.d(TAG, "*Start Lesson5_2_BaseDataLoade, getInputURL = " + getInputURL);
        //getInputURL = Resources.getSystem().getString(R.id.et_l5_2_input_url);
        //Log.d(TAG, "*Start Lesson5_2_BaseDataLoade, type = " + getContext().getApplicationContext().getText(R.id.et_l5_2_input_url).getClass().getName());
        //getInputURL = (getContext().getApplicationContext().getText(R.id.et_l5_2_input_url)).toString();
        //Log.d(TAG, "*Start Lesson5_2_BaseDataLoade, getInputURL = " + getInputURL);
    }
    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // If we currently have a result available, deliver it
            // immediately.
            Log.d(TAG, "onStartLoading, mData!=null");
            deliverResult(mData);
        }else{
            Log.d(TAG, "onStartLoading, mData==null");
        }
        registerObserver();
        if (takeContentChanged() || mData == null || isConfigChanged()) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            Log.d(TAG, "onStartLoading, takeContentChanged & mData==null & isConfigChanged");
            forceLoad();
        }else{
            Log.d(TAG, "onStartLoading, takeContentChanged & mData!=null & isConfigChanged");
        }
    }
    /**
     * Called when there is new data to deliver to the client. The super class
     * will take care of delivering it; the implementation here just adds a
     * little more logic.
     */
    @Override
    public void deliverResult(T data) {
        Log.d(TAG, "*deliverResult");
        if (isReset()) {
            // An async query came in while the loader is stopped. We
            // don't need the result.
            if (mData != null) {
                onReleaseResources(mData);
            }
        }
        T oldApps = mData;
        mData = data;
        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(data);
        }
        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (oldApps != null) {
            onReleaseResources(oldApps);
        }
    }
    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        Log.d(TAG, "*onStopLoading");
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }
    /**
     * Handles a request to cancel a load.
     */
    @Override
    public void onCanceled(T data) {
        super.onCanceled(data);
        Log.d(TAG, "*onCanceled");
        // At this point we can release the resources associated with 'apps'
        // if needed.
        onReleaseResources(data);
    }
    /**
     * Handles a request to completely reset the Loader.
     */
    @Override
    protected void onReset() {
        super.onReset();
        Log.d(TAG, "*onReset");
        // Ensure the loader is stopped
        onStopLoading();
        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (mData != null) {
            onReleaseResources(mData);
            mData = null;
        }
        unregisterObserver();
    }
    protected boolean isConfigChanged() {
        Log.d(TAG, "*isConfigChanged");
        return false;
    }
    protected void registerObserver() {
        Log.d(TAG, "*registerObserver");
    }
    protected void unregisterObserver() {
        Log.d(TAG, "*unregisterObserver");
    }
    protected void onReleaseResources(T mData) {
        Log.d(TAG, "*onReleaseResources");
    }
}


/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lesson5_2_BaseDataLoade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson5_2_base_data_loade);
    }
}
*/
