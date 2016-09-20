package com.scene.baseframe.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.commit451.nativestackblur.NativeStackBlur;
import com.scene.baseframe.R;
import com.scene.baseframe.base.BaseBackFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @FileName:com.scene.baseframe.ui.fragment.BlurFragment.java
 * @功能描述：模糊效果的界面
 * @author: scene
 * @date: 2016-09-19 11:09
 */
public class BlurFragment extends BaseBackFragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.image)
    ImageView image;

    public static BlurFragment newInstance() {
        Bundle args = new Bundle();
        BlurFragment fragment = new BlurFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blur, container, false);
        ButterKnife.bind(this, view);
        initView();
        return attachToSwipeBack(view);
    }

    private void initView() {
        initToolbarNav(toolbar, "模糊效果");
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState) {

        Glide.with(this)
                .load("http://abc.2008php.com/2011_Website_appreciate/2011-11-21/20111121023634.jpg")
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        Bitmap bm = NativeStackBlur.process(resource, 20);
                        image.setImageBitmap(bm);
                    }
                });
    }

}
