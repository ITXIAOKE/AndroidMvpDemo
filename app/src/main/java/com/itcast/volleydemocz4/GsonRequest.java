package com.itcast.volleydemocz4;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;

/**
 * Created by lxj on 2016/11/11.
 * 自动将请求回来的json数据解析为java bean
 *
 */

public class GsonRequest<T> extends Request<T>{
    Response.Listener<T> mListener;
    Class<T> clazz;
    public GsonRequest(String url, Class<T> clazz,Response.Listener<T> mListener,Response.ErrorListener listener) {
        super(url, listener);
        this.clazz = clazz;
        this.mListener  =mListener;
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        String parsed;
        try {
            parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }

        //将json数据解析为java bean
        T t = new Gson().fromJson(parsed, clazz);

        return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(T response) {
        //将response传递给listener
        mListener.onResponse(response);
    }


}
