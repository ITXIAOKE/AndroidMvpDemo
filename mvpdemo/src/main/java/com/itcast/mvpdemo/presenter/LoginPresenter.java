package com.itcast.mvpdemo.presenter;

import android.text.TextUtils;

import com.itcast.mvpdemo.model.http.HttpHelper;
import com.itcast.mvpdemo.view.ILoginView;

/**
 * Created by lxj on 2016/11/11.
 * 登录的业务逻辑层
 */

public class LoginPresenter extends BasePresenter{

    private ILoginView loginView;

    public LoginPresenter(ILoginView loginView){
        this.loginView = loginView;
    }

    /**
     * 执行登录的方法
     * @param username
     * @param psw
     */
    public void login(String username,String psw){
        //2.检查输入是否合法
        if(checkInput(username,psw)){
            //3.进行登录请求
            HttpHelper.create()
                    .post("url", new HttpHelper.HttpCallback() {
                        @Override
                        public void onSuccess(String result) {
                            //提示登录成功
                            loginView.showLoginSuccess();
                            //保存用户登录的数据
                            saveUserData();
                            //跳转界面
                            jumpActivity();
                        }
                        @Override
                        public void onFial(Exception e) {
                            //登录请求失败
                            loginView.showLoginFail();
                        }
                    });
        }
    }
    //登录成功，跳转界面
    private void jumpActivity() {
        //开始一个Activity...
    }
    //保存用户登录的数据
    private void saveUserData() {
        //保存到SharedPreference中
    }
    //检查输入的合法性
    private boolean checkInput(String username, String psw) {
        //1.检查是否为空
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(psw)){
            loginView.showNotNull();
            return false;
        }
        //2.检查用户名长度
        if(username.length()!=11){
            loginView.showUsernameLengthError();
            return false;
        }

        return true;
    }


    //当View层销毁的时候，我们应该做一些释放资源，比如取消请求网络，关闭cursor等
    public void onDestory(){
        //取消网络
    }


}
