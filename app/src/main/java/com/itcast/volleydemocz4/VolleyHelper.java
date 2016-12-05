package com.itcast.volleydemocz4;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

/**
 * Created by lxj on 2016/11/11.
 * Volley的封装类
 */

public class VolleyHelper {
    RequestQueue requestQueue;
    private static VolleyHelper mInstance = null;

    private VolleyHelper(Context context){
        //1.创建请求队列,请求队列是用来执行请求的
        requestQueue = Volley.newRequestQueue(context);
    }
    public static VolleyHelper create(Context context){
        if(mInstance==null){
            mInstance = new VolleyHelper(context);
        }
         return mInstance;
    }

    /**
     * 执行请求
     * @param url
     * @param clazz
     * @param listener
     * @param errListener
     * @param <T>
     */
    public <T> void execRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                                Response.ErrorListener errListener){
        GsonRequest<T> gsonRequest = new GsonRequest<>(url, clazz, listener, errListener);
        requestQueue.add(gsonRequest);
    }

    /**
     * 执行请求
     * @param url
     * @param clazz
     * @param callback
     * @param <T>
     */
    public <T> void execRequest(String url, Class<T> clazz, final VolleyCallbck<T> callback){
        GsonRequest<T> gsonRequest = new GsonRequest<>(url, clazz, new Response.Listener<T>() {
            @Override
            public void onResponse(T response) {
                callback.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onFial(error);
            }
        });
        requestQueue.add(gsonRequest);
    }



    public interface VolleyCallbck<T>{
        void onSuccess(T t);
        void onFial(Exception error);
    }

}
