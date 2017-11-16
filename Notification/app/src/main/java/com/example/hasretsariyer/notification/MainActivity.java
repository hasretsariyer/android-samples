package com.example.hasretsariyer.notification;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    OkHttpClient client = null;

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

    public void httpExample(View v) {
        final long startTime = System.currentTimeMillis();
        final int COUNT = 5000;
        String url = "https://randomuser.me/api/?results=" + COUNT;
        Request request = new Request.Builder().url(url).method("GET", null).build();

        if(client == null)
            client = new OkHttpClient.Builder().build();
        client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("@@onFailure", e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    int statusCode = response.code();
                    byte[] bytes = null;
                    if(statusCode != 304 && response.body() != null) {
                        bytes = response.body().bytes();
                    }

                    long endTime = System.currentTimeMillis();
                    Log.i("@@onResponse", "Time took for " + COUNT + "people: " + (endTime - startTime) + "ms");
                }
            });
    }
}
