package com.example.hasretsariyer.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goLocalNotificationExample(View v) {
        Intent intent = new Intent(this, LocalNotificationActivity.class);
        startActivity(intent);
    }

    public void goViewPagerExample(View v) {
        Intent intent = new Intent(this, ViewPagerActivity.class);
        startActivity(intent);
    }
}
