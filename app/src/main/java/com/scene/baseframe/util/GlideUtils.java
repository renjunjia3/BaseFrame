package com.scene.baseframe.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.scene.baseframe.R;

/**
 * @FileName:com.scene.baseframe.util.GlideUtils.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-18 13:01
 */
public class GlideUtils {

    public static void loadImage(Context context, Object url, ImageView imageView) {
        Glide.with(context).load(url).placeholder(R.drawable.bg_img).error(R.drawable.bg_img_error).crossFade().into(imageView);
    }

    public static void loadImage(Activity activity, Object url, ImageView imageView) {
        Glide.with(activity).load(url).placeholder(R.drawable.bg_img).error(R.drawable.bg_img_error).crossFade().into(imageView);
    }

    public static void loadImage(Fragment fragment, Object url, ImageView imageView) {
        Glide.with(fragment).load(url).placeholder(R.drawable.bg_img).error(R.drawable.bg_img_error).crossFade().into(imageView);
    }

    public static void loadImage(android.app.Fragment fragment, Object url, ImageView imageView) {
        Glide.with(fragment).load(url).placeholder(R.drawable.bg_img).error(R.drawable.bg_img_error).crossFade().into(imageView);
    }

    public static void loadImage(FragmentActivity activity, Object url, ImageView imageView) {
        Glide.with(activity).load(url).placeholder(R.drawable.bg_img).error(R.drawable.bg_img_error).crossFade().into(imageView);
    }
}
