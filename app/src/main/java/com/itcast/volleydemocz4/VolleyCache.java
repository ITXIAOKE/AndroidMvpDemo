package com.itcast.volleydemocz4;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by lxj on 2016/11/11.
 */

public class VolleyCache implements ImageLoader.ImageCache {
    LruCache<String,Bitmap> lruCache;

    //内存缓存的上限
    int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
    public VolleyCache(){
        lruCache = new LruCache<String,Bitmap>(maxSize){
            /**
             *   返回图片的大小,返回LinkedHashMap中元素的大小
             */
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight()*value.getRowBytes();
            }
        };

    }
    @Override
    public Bitmap getBitmap(String url) {
        return lruCache.get(url);
    }
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        lruCache.put(url,bitmap);
    }
}
