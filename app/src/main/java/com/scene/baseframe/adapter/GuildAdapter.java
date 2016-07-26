package com.scene.baseframe.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @FileName:com.scene.baseframe.adapter.GuildAddpter.java
 * @功能描述：GuildActivity 的viewpager适配器
 * @author: scene
 * @date: 2016-07-26 15:26
 */
public class GuildAdapter extends PagerAdapter {
    private List<View> lists;

    public GuildAdapter(List<View> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists != null ? lists.size() : 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        container.removeView(lists.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = lists.get(position);
        container.addView(view);
        return view;
    }
}
