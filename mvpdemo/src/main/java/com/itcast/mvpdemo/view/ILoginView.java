package com.itcast.mvpdemo.view;

/**
 * Created by lxj on 2016/11/11.
 * LoginPresenter的View层接口
 */

public interface ILoginView {
    void showLoginSuccess();
    void showLoginFail();
    void showNotNull();
    void showUsernameLengthError();
}
