package com.sergio.imgtopdf;

import android.app.Application;

/**
 * Created by Sergio on 10/12/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FileUtils.folderApp();

    }
}
