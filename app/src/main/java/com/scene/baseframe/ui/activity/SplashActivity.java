package com.scene.baseframe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;

import com.scene.baseframe.MainActivity;
import com.scene.baseframe.R;
import com.scene.baseframe.config.AppConfig;
import com.scene.baselib.util.SharedPreferencesUtil;

/**
 * @FileName:com.scene.baseframe.ui.activity.SplashActivity.java
 * @功能描述：加载页
 * @author: scene
 * @date: 2016-07-25 16:09
 */
public class SplashActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        boolean isFrist = SharedPreferencesUtil.getBoolean(SplashActivity.this, AppConfig.ISFRIST, true);
        if (isFrist) {
            toGuideActivty();
        } else {
            toMainActivity();
        }
    }


    /**
     * 进入主页
     */
    private void toMainActivity() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);
    }

    /**
     * 进入引导页
     */
    private void toGuideActivty() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                finish();
            }
        }, 3000);
    }

    @Override
    public void onBackPressed() {

    }
}
