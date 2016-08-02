package com.scene.baseframe.config;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.scene.baseframe.R;

/**
 * @FileName:com.scene.baseframe.config.GlideConfiguration.java
 * @功能描述：
 * @author: scene
 * @date: 2016-07-18 12:26
 */
public class GlideConfiguration implements GlideModule {

    private final int DISK_CACHE_SIZE = 50 * 1024 * 1024;
    private final String DISK_CACHE_NAME = Environment.getExternalStorageDirectory().getPath() + AppConfig.CACHE_URL;

    @Override
    public void applyOptions(Context context, GlideBuilder glideBuilder) {
        //设置tag
        ViewTarget.setTagId(R.id.glide_tag_id);
        //设置图片的质量
        glideBuilder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //自定义内存缓存
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        glideBuilder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        glideBuilder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));
        //自定义磁盘缓存
        glideBuilder.setDiskCache(new InternalCacheDiskCacheFactory(context, DISK_CACHE_SIZE));
        glideBuilder.setDiskCache(new DiskLruCacheFactory(DISK_CACHE_NAME, DISK_CACHE_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
    }
}
