package com.example.aphomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

    public class Lesson5_1_Main_Activity extends AppCompatActivity {

        private static final String TAG = "[HomeWork][Lesson5][1]";

        //Key for saving the state of the TextView
        private static final String TEXT_STATE = "currentText";

        // The TextView where we will show results
        private TextView mTextView;
        private ProgressBar progressBar;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lesson5_1);
            Log.d(TAG, "START");

            // customize toolbar title
            getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson5-1");

            //  Initialize mTextView
            mTextView = (TextView) findViewById(R.id.tv_l5_1_start_work);
            progressBar =(ProgressBar) findViewById(R.id.btn_l5_1_progressBar);

            // Restore TextView if there is a savedInstanceState(rotate device will enter savedInstanceState!= null)
            if (savedInstanceState!= null) { // read data saved in onSaveInstanceState()
                Log.d(TAG, "TEXT_STATE = " + savedInstanceState.getString(TEXT_STATE));
                mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            }else{
                Log.d(TAG, "savedInstanceState = null");
            }
        }

        //
        public void startTask (View view) {
            Log.d(TAG, "startTask");
            // Put a message in the text view
            mTextView.setText(R.string.tv_l5_1_napping);

            // Start the AsyncTask.
            // The AsyncTask has a callback that will update the text view.
            //new SimpleAsyncTask(mTextView).execute();
            Log.d(TAG, "*startTask, method_name.execute() to run ");
            new Lesson_5_1_SimpleAsyncTask(mTextView,progressBar).execute();
        }

        //Saves the contents of the TextView to restore on configuration change.
        @Override
        protected void onSaveInstanceState(Bundle outState) {
            Log.d(TAG, "onSaveInstanceState");
            super.onSaveInstanceState(outState);
            // Save the state of the TextView
            outState.putString(TEXT_STATE, mTextView.getText().toString());
        }

    }

