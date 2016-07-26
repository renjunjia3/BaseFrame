package com.scene.baseframe.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import com.scene.baseframe.MainActivity;
import com.scene.baseframe.R;
import com.scene.baseframe.adapter.GuildAdapter;
import com.scene.baseframe.config.AppConfig;
import com.scene.baselib.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.activity.GuideActivity.java
 * @功能描述：引导页
 * @author: scene
 * @date: 2016-07-25 17:07
 */
public class GuideActivity extends Activity {

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private LayoutInflater inflater;
    private List<View> viewList;//view数组

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);
        initGuild();
    }

    private void initGuild() {
        inflater = LayoutInflater.from(this);
        View page1 = inflater.inflate(R.layout.item_guide1, null);
        View page2 = inflater.inflate(R.layout.item_guide2, null);
        View page3 = inflater.inflate(R.layout.item_guide3, null);
        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(page1);
        viewList.add(page2);
        viewList.add(page3);
        viewPager.setAdapter(new GuildAdapter(viewList));
        page3.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferencesUtil.putBoolean(GuideActivity.this, AppConfig.ISFRIST, false);
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}
