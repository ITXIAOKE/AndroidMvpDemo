package com.itcast.mvpdemo.presenter;

/**
 * Created by lxj on 2016/11/11.
 */

public class BasePresenter {

    public void onResume(){

    }
    public void onPause(){

    }
    public void onStart(){

    }
    public void onStop(){

    }
    //当View层销毁的时候，我们应该做一些释放资源，比如取消请求网络，关闭cursor等
    public void onDestory(){
        //取消网络
    }
}
