package com.scene.baseframe.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.scene.baseframe.R;
import com.scene.baseframe.base.BaseBackFragment;
import com.scene.baselib.frame.view.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.fragment.WebViewActivity.java
 * @功能描述：
 * @author: scene
 * @date: 2016-08-02 10:30
 */
public class WebViewFragment extends BaseBackFragment {
    private static final String ARG_MSG = "arg_msg";
    private String msg;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.webView)
    ProgressWebView webView;

    public static WebViewFragment newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString(ARG_MSG, msg);
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        msg = getArguments().getString(ARG_MSG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, view);
        initView();
        return attachToSwipeBack(view);
    }

    private void initView() {
        initToolbarNav(toolbar, msg);
    }


    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        webView.getWebView().loadUrl("http://www.scene.wiki");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
