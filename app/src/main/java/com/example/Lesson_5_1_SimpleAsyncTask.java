package com.example.aphomework;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class Lesson_5_1_SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private static final String TAG = "[HomeWork][Lesson5][1][Lesson_5_1_SimpleAsyncTask]";

    // The TextView where we will show results
    private TextView mTextView;
    private ProgressBar progressBar;

    // Constructor that provides a reference to the TextView from the MainActivity
    public Lesson_5_1_SimpleAsyncTask(TextView tv, ProgressBar pb) {
        Log.d(TAG, "Start Lesson_5_1_SimpleAsyncTask, mTextView = " + tv);
        mTextView = tv;
        progressBar = pb;
    }

    // Enable this method for check the workflow of code, it run in UI Thread
    @Override
    protected void onPreExecute(){
        Log.d(TAG, "*onPreExecute, it will be run in UI thread");
    }


    /**
     * Runs on the background thread.
     *
     * @param voids No parameters in this use case.
     * @return Returns the string including the amount of time that
     * the background thread slept.
     */
    // Run in worker Thread and return result to onPostExecute()
    @Override
    protected String doInBackground(Void... params) {
        Log.d(TAG, "*doInBackground");
        // Generate a random number between 0 and 10
        Random r = new Random();
        int n = r.nextInt(11) + 1;
        Log.d(TAG, "*doInBackground, n = " + n);

        // Make the task take long enough that we have time to rotate the phone while it is running
        int s = n * 200;

        // Sleep for the random amount of time
        int progress_period = (int) (100/(n-1));
        int progress = 0;
        Log.d(TAG, "*doInBackground, progress_period = " + progress_period);
        Log.d(TAG, "*doInBackground, progress = " + progress);
        int i = 0;
        while(i<n){
            i++;
            Log.d(TAG, "*doInBackground, i = " + i);
            Log.d(TAG, "*doInBackground, while progress = " + progress);
            progress = progress + progress_period;
            publishProgress(progress);
            try {
                Thread.sleep(1000);
                Log.d(TAG, "*doInBackground, sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Return a String result
        return "Awake at last after sleeping for " + (n*1000) + " milliseconds!";
    }



    // Run in UI Thread, need to publishProgress() in doInBackground method
    @Override
    protected void onProgressUpdate(Integer... progress) {
        // TODO Auto-generated method stub
        Log.d(TAG, "*onProgressUpdate, progress[0] = " + progress[0]);
        progressBar.setProgress(progress[0]);
    }

    /**
     * Does something with the result on the UI thread; in this case
     * updates the TextView.
     */
    protected void onPostExecute(String result) {
        Log.d(TAG, "*onPostExecute, result = " + result);
        mTextView.setText(result);
    }
}
