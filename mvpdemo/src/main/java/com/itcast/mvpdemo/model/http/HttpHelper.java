package com.itcast.mvpdemo.model.http;

/**
 * Created by lxj on 2016/11/11.
 * 从网络获取数据
 */

public class HttpHelper {
    private static HttpHelper mInstance = new HttpHelper();
    private HttpHelper(){

    }
    public static HttpHelper create(){
         return mInstance;
    }

    public void post(String url,HttpCallback callback){
        //执行真正的网络请求的代码了，哒哒哒....
    }

    public interface HttpCallback{
        void onSuccess(String result);
        void onFial(Exception e);
    }
}
