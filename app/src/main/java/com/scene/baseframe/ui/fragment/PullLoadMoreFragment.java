package com.scene.baseframe.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.scene.baseframe.R;
import com.scene.baseframe.adapter.PullLoadMoreAdapter;
import com.scene.baseframe.base.BaseBackFragment;
import com.scene.baselib.pull_loadmore.PtrClassicFrameLayout;
import com.scene.baselib.pull_loadmore.PtrDefaultHandler;
import com.scene.baselib.pull_loadmore.PtrFrameLayout;
import com.scene.baselib.pull_loadmore.loadmore.OnLoadMoreListener;
import com.scene.baselib.pull_loadmore.recyclerview.RecyclerAdapterWithHF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.fragment.PullLoadMoreFragment.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-18 10:20
 */
public class PullLoadMoreFragment extends BaseBackFragment {
    private static final String ARG_MSG = "arg_msg";
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.ptr_layout)
    PtrClassicFrameLayout ptrLayout;

    private List<String> mlists = new ArrayList<>();
    private PullLoadMoreAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;

    Handler handler = new Handler();

    int page = 0;
    private String msg;

    public static PullLoadMoreFragment newInstance(String msg) {
        Bundle args = new Bundle();
        args.putString(ARG_MSG, msg);
        PullLoadMoreFragment fragment = new PullLoadMoreFragment();
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
        View view = inflater.inflate(R.layout.fragment_pull_loadmore, container, false);
        ButterKnife.bind(this, view);
        initView();
        return attachToSwipeBack(view);
    }

    private void initView() {
        initToolbarNav(toolbar, msg);
        ptrLayout.setLastUpdateTimeRelateObject(this);
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {
        super.onEnterAnimationEnd(savedInstanceState);
        adapter = new PullLoadMoreAdapter(mlists, this);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerview.setAdapter(mAdapter);
        ptrLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                ptrLayout.autoRefresh(true);
            }
        }, 150);

        ptrLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        page = 0;
                        mlists.clear();
                        mlists.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4230438925,985959782&fm=21&gp=0.jpg");
                        mlists.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3986176469,46758905&fm=21&gp=0.jpg");
                        mlists.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3425851328,2681317699&fm=21&gp=0.jpg");
                        mlists.add("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1942600385,3845795931&fm=21&gp=0.jpg");
                        mlists.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1849768669,2102852722&fm=21&gp=0.jpg");
                        mlists.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4168762024,1922499492&fm=21&gp=0.jpg");
                        mlists.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=3943757407,3404348698&fm=21&gp=0.jpg");
                        mAdapter.notifyDataSetChanged();
                        ptrLayout.refreshComplete();
                        ptrLayout.setLoadMoreEnable(true);
                        ptrLayout.loadMoreComplete(page <= 5 ? true : false);
                    }
                }, 2000);
            }
        });

        ptrLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void loadMore() {
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mlists.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2666243017,432522262&fm=21&gp=0.jpg");
                        mAdapter.notifyDataSetChanged();
                        page++;
                        ptrLayout.loadMoreComplete(page <= 5 ? true : false);
                        ptrLayout.setLoadMoreEnable(page <= 5 ? true : false);
                    }
                }, 1000);
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
