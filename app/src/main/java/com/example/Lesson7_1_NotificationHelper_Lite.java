package com.example.aphomework;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by Jane_Chiang on 2018/3/2.
 */

public class Lesson7_1_NotificationHelper_Lite extends ContextWrapper {
    private static final String TAG = "[HomeWork][" + Lesson7_1_Main_Activity.class.getSimpleName() + "]";
    public static final String FIRST_CHANNEL = "first";
    public static final String SECOND_CHANNEL = "second";

    private NotificationManager mNotificationManager;

    /**
     * Registers notification channels, which can be used later by individual notifications.
     *
     * @param context The application context
     */

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Lesson7_1_NotificationHelper_Lite(Context context) {
        super(context);

        // Create the channel object with the unique ID FIRST_CHANNEL
        NotificationChannel firstChannel =
                new NotificationChannel(
                        FIRST_CHANNEL,
                        getString(R.string.tv_l7_1_first_channel_title),
                        NotificationManager.IMPORTANCE_DEFAULT);

        // Create the channel object with the unique ID SECOND_CHANNEL
        NotificationChannel secondChannel =
                new NotificationChannel(
                        SECOND_CHANNEL,
                        getString(R.string.tv_l7_1_second_channel_title),
                        NotificationManager.IMPORTANCE_DEFAULT);

        // Configure the channel's initial settings
        firstChannel.setLightColor(Color.GREEN);
        // 添加自定义震动提醒
        // 延迟100ms后震动200ms，
        // 再延迟300ms后震动400ms
        // 再延500ms后震动400ms
        // 再延500ms后震动200ms
        // 再延500ms
        firstChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 500, 200, 500});
        // 设置是否显示角标
        firstChannel.setShowBadge(true);

        secondChannel.setLightColor(Color.BLUE);
        secondChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 500, 200, 500});
        secondChannel.setShowBadge(true);

        // Submit the notification channel object to the notification manager
        getNotificationManager().createNotificationChannel(firstChannel);
        getNotificationManager().createNotificationChannel(secondChannel);
    }


    /**
     * Get the notification mNotificationManager.
     * <p>
     * <p>Utility method as this helper works with it a lot.
     *
     * @return The system service NotificationManager
     */
    private NotificationManager getNotificationManager() {
        if (mNotificationManager == null) {
            mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mNotificationManager;
    }

    /**
     * Get a random name string from resources to add personalization to the notification
     *
     * @return A random name
     */
    public String getRandomName() {

        //String[] names = getApplicationContext().getResources().getStringArray(R.array.names_array);
        //return names[new Random().nextInt(names.length)];

        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd ");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        //取得現在時間
        Date dt=new Date();
        //透過SimpleDateFormat的format方法將Date轉為字串
        String dts=sdf.format(dt);
        Log.d(TAG, "*getRandomName, Date Time = " + dts);

        return dts;

    }

    /**
     * Get the small icon for this app
     *
     * @return The small icon resource id
     */
    private int getSmallIcon() {
        return android.R.drawable.stat_notify_chat;
    }



    /**
     * Send a notification.
     *
     * @param id           The ID of the notification
     * @param notification The notification object
     */
    public void notify(int id, Notification.Builder notification) {
        getNotificationManager().notify(id, notification.build());
    }


    /**
     * Create a PendingIntent for opening up the MainActivity when the notification is pressed
     *
     * @return A PendingIntent that opens the MainActivity
     */
    private PendingIntent getPendingIntent() {
        // Reference Document :http://www.bijishequ.com/detail/326073?p=
        Intent openMainIntent = new Intent(this, MainActivity.class);//Jane Comment: 點擊notification後,會回到哪個畫面

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        //创建返回栈
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        // Adds the back stack for the Intent (but not the Intent itself)
        //添加Activity到返回栈
        stackBuilder.addParentStack(MainActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        //添加Intent到栈顶
        stackBuilder.addNextIntent(openMainIntent);
        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_ONE_SHOT);
    }




    /**
     * Get a follow/un-follow notification
     * <p>
     * <p>Provide the builder rather than the notification it's self as useful for making
     * notification changes.
     *
     * @param title the title of the notification
     * @param body  the body text for the notification
     * @return A Notification.Builder configured with the selected channel and details
     */
    public Notification.Builder getNotificationFirst(String title, String body) {
//        return new Notification.Builder(getApplicationContext())
        // Reference document : http://yannischeng.com/Android%208.0%208.1%20Notification%E7%9A%84%E4%BD%BF%E7%94%A8/
        return new Notification.Builder(getApplicationContext(),FIRST_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true) // 仅当 配合"setContentIntent(pendingIntent)"使用时，点击通知会自动消失
                .setContentIntent(getPendingIntent());
    }


    /**
     * Get a follow/un-follow notification
     * <p>
     * <p>Provide the builder rather than the notification it's self as useful for making
     * notification changes.
     *
     * @param title the title of the notification
     * @param body  the body text for the notification
     * @return A Notification.Builder configured with the selected channel and details
     */
    public Notification.Builder getNotificationSecond(String title, String body) {
//        return new Notification.Builder(getApplicationContext())
        // Reference document : http://yannischeng.com/Android%208.0%208.1%20Notification%E7%9A%84%E4%BD%BF%E7%94%A8/
        return new Notification.Builder(getApplicationContext(),SECOND_CHANNEL)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(getSmallIcon())
                .setAutoCancel(true) // 仅当 配合"setContentIntent(pendingIntent)"使用时，点击通知会自动消失
                .setContentIntent(getPendingIntent());
    }

}
