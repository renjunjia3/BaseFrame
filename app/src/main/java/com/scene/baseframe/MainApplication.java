package com.scene.baseframe;

import android.app.Application;

import com.scene.baseframe.config.AppConfig;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @FileName:com.scene.baseframe.MainApplication.java
 * @功能描述：application
 * @author: scene
 * @date: 2016-07-25 15:38
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //配置okhttp 更多配置：https://github.com/hongyangAndroid/okhttp-utils
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(AppConfig.OKHTTP_CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .readTimeout(AppConfig.OKHTTP_READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
