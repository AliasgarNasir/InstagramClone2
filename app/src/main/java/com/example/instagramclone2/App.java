package com.example.instagramclone2;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("PSmYqGWn8HdfKOvhdzSlNVjzOjfL8Vr8z9boRMWQ")
                // if defined
                .clientKey("hPD06dhTC67kfLRshi8HPSQ3YyeGQod4LwmJY4IW")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

}
