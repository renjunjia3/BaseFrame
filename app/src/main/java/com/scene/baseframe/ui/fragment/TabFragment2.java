package com.scene.baseframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scene.baseframe.R;
import com.scene.baseframe.base.BaseLazyMainFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.fragment.TabFragment1.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-14 16:25
 */
public class TabFragment2 extends BaseLazyMainFragment {
    @BindView(R.id.toolbar)
    public Toolbar mToolbar;


    public static TabFragment2 newInstance() {

        Bundle args = new Bundle();
        TabFragment2 fragment = new TabFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        ButterKnife.bind(this, view);
        initView(view);
        return view;
    }

    @Override
    protected void initLazyView(@Nullable Bundle savedInstanceState) {
        //在这里加载页面的数据
    }

    private void initView(View view) {
        mToolbar.setTitle("首页2");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}
