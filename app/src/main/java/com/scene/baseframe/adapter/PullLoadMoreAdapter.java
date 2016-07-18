package com.scene.baseframe.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scene.baseframe.R;
import com.scene.baseframe.util.GlideUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.adapter.PullLoadMoreAdapter.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-18 10:41
 */
public class PullLoadMoreAdapter extends RecyclerView.Adapter {

    private List<String> list;
    private Context mContext;
    private LayoutInflater inflater;
    private Fragment fragment;

    public PullLoadMoreAdapter(List<String> list, Fragment fragment) {
        this.list = list;
        this.fragment = fragment;
        this.mContext = fragment.getContext();
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PullLoadMoreHolder(inflater.inflate(R.layout.item_pull_loadmore, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        PullLoadMoreHolder mHolder = (PullLoadMoreHolder) holder;
        GlideUtils.loadImage(fragment, list.get(position), mHolder.img);
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class PullLoadMoreHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img)
        ImageView img;

        public PullLoadMoreHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
