package com.example.hasretsariyer.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class LocalNotificationActivity extends AppCompatActivity {
    int NOTIFICATION_ID = 100;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_notification_example);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(true);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Local Notification Example");

        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    public void onResume(){
        super.onResume();
        notificationHandler(getIntent());
        Log.i("@@TAG", "onResume callback");
    }

    public void notificationHandler(Intent intent) {
        if(intent != null && intent.getExtras() != null && intent.getExtras().containsKey("RESPONSE_TYPE")) {
            String responseType = intent.getExtras().get("RESPONSE_TYPE").toString();
            if(responseType.equals("YES")) {
                cancelNotification();
            }
        }
    }

    public void createNotification(View v) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.globe_emea)
                        .setContentTitle("Notification example")
                        .setContentText("Do you want to remove this notification?");

        int YES_REQUEST_CODE = 1234;
        int NO_REQUEST_CODE = 1235;

        Intent yesReceive = new Intent(this, this.getClass());
        yesReceive.putExtra("RESPONSE_TYPE", "YES");
        PendingIntent pendingIntentYes = PendingIntent.getActivity(this, YES_REQUEST_CODE, yesReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(R.drawable.yes, "Yes", pendingIntentYes);


        Intent noReceive = new Intent(this, LocalNotificationActivity.class);
        yesReceive.putExtra("RESPONSE_TYPE", "NO");
        PendingIntent pendingIntentNo = PendingIntent.getActivity(this, NO_REQUEST_CODE, noReceive, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.addAction(R.drawable.no, "No", pendingIntentNo);
        notificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }

    public void cancelNotification() {
        notificationManager.cancel(NOTIFICATION_ID);
    }
}
