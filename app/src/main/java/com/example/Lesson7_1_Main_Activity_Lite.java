package com.example.aphomework;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Lesson7_1_Main_Activity_Lite extends AppCompatActivity {
    private static final String TAG = "[HomeWork][" + Lesson7_1_Main_Activity.class.getSimpleName() + "]";

    /*
     * A view model for interacting with the UI elements.
     */
    private MainUi mUIModel;

    /*
     * A helper class for initializing notification channels and sending notifications.
     */
    private Lesson7_1_NotificationHelper_Lite mNotificationHelper;

    private static final int NOTIFICATION_FIRST_1 = 1100;
    private static final int NOTIFICATION_FIRST_2 = 1101;
    private static final int NOTIFICATION_SECOND_1 = 1200;
    private static final int NOTIFICATION_SECOND_2 = 1201;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson7_1_lite);
        Log.d(TAG,"*onCreate, Build Version = " + Build.VERSION.SDK_INT);

        // customize toolbar title
        getSupportActionBar().setTitle((String) getSupportActionBar().getTitle() + " : Lesson7-1");

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Toast.makeText(Lesson7_1_Main_Activity_Lite.this,"Please upgrade to Android Oreo.", Toast.LENGTH_LONG).show();
        }else{
            setContentView(R.layout.activity_lesson7_1_lite);
            mNotificationHelper = new Lesson7_1_NotificationHelper_Lite(this);
            mUIModel = new MainUi(findViewById(R.id.activity_lesson7_1_lite));
        }
    }



    /**
     * View model for interacting with Activity UI elements. (Keeps core logic for sample separate.)
     */
    class MainUi implements View.OnClickListener {

        private MainUi(View root) {

            // Setup the buttons
            (root.findViewById(R.id.bt_l7_1_send_first_1)).setOnClickListener(this);
            (root.findViewById(R.id.bt_l7_1_send_first_2)).setOnClickListener(this);
            (root.findViewById(R.id.bt_l7_1_send_second_1)).setOnClickListener(this);
            (root.findViewById(R.id.bt_l7_1_send_second_2)).setOnClickListener(this);
            (root.findViewById(R.id.bt_l7_1_go_to_settings)).setOnClickListener(this);
        }


    /**
         * Send activity notifications.
         *
         * @param id The ID of the notification to create
         */
        private void sendNotification(int id) {
            Notification.Builder notificationBuilder = null;
            switch (id) {
                case NOTIFICATION_FIRST_1:
                    notificationBuilder =
                            mNotificationHelper.getNotificationFirst(
                                    getString(R.string.l7_1_first_title_notification),
                                    getString(R.string.l7_1_first_notification_body_1,
                                            mNotificationHelper.getRandomName()));
                    break;

                case NOTIFICATION_FIRST_2:
                    notificationBuilder =
                            mNotificationHelper.getNotificationFirst(
                                    getString(R.string.l7_1_first_title_notification),
                                    getString(R.string.l7_1_first_notification_body_2,
                                            mNotificationHelper.getRandomName()));
                    break;

                case NOTIFICATION_SECOND_1:
                    notificationBuilder =
                            mNotificationHelper.getNotificationSecond(
                                    getString(R.string.l7_1_second_title_notification),
                                    getString(R.string.l7_1_second_notification_body_1,
                                            mNotificationHelper.getRandomName()));
                    break;

                case NOTIFICATION_SECOND_2:
                    notificationBuilder =
                            mNotificationHelper.getNotificationSecond(
                                    getString(R.string.l7_1_second_title_notification),
                                    getString(R.string.l7_1_second_notification_body_2,
                                            mNotificationHelper.getRandomName()));
                    break;
            }
            if (notificationBuilder != null) {
                mNotificationHelper.notify(id, notificationBuilder); //Post a notification to be shown in the status bar.
            }
        }


        /** Send Intent to load system Notification Settings for this app. */
        private void goToNotificationSettings() {
            Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
            startActivity(i);
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bt_l7_1_send_first_1:
                    sendNotification(NOTIFICATION_FIRST_1);
                    break;
                case R.id.bt_l7_1_send_first_2:
                    sendNotification(NOTIFICATION_FIRST_2);
                    break;
                //case R.id.follower_channel_settings_button:
                //    goToNotificationChannelSettings(Lesson7_1_NotificationHelper.FOLLOWERS_CHANNEL);
                //    break;
                case R.id.bt_l7_1_send_second_1:
                    sendNotification(NOTIFICATION_SECOND_1);
                    break;
                case R.id.bt_l7_1_send_second_2:
                    sendNotification(NOTIFICATION_SECOND_2);
                    break;
                //case R.id.dm_channel_settings_button:
                //    goToNotificationChannelSettings(Lesson7_1_NotificationHelper.MESSAGE_CHANNEL);
                //    break;
                case R.id.bt_l7_1_go_to_settings:
                    goToNotificationSettings();
                    break;
                default:
                    Log.e(TAG, getString(R.string.error_click));
                    break;
            }
        }
    }
}
