package com.scene.baseframe.viewholder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.scene.baseframe.R;
import com.scene.baseframe.util.GlideUtils;
import com.scene.baselib.convenientbanner.holder.Holder;

/**
 * @FileName:com.scene.baseframe.adapter.GuildHolder.java
 * @功能描述：GuildActivity holder
 * @author: scene
 * @date: 2016-07-26 10:57
 */
public class GuildHolder implements Holder {
    private ImageView image;

    @Override
    public View createView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_guide1, null, false);
        image = (ImageView) view.findViewById(R.id.image);
        return view;
    }

    @Override
    public void UpdateUI(Context context, int position, Object data) {
        GlideUtils.loadImage(context, data, image);
    }
}
