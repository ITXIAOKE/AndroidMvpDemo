package com.itcast.mvpdemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.itcast.mvpdemo.presenter.BasePresenter;

/**
 * Created by lxj on 2016/11/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    BasePresenter basePresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        basePresenter = getPresenter();//每个View层的presetner不一样，只能通过方法动画获取

    }

    public abstract BasePresenter getPresenter();

    //一下重复代码可以完全放入BaseActivity中进行执行
    @Override
    protected void onResume() {
        super.onResume();
        basePresenter.onResume();
    }

    @Override
    protected void onPause() {
        super.onResume();
        basePresenter.onPause();
    }

    @Override
    protected void onStart() {
        super.onResume();
        basePresenter.onStart();
    }
    @Override
    protected void onStop() {
        super.onResume();
        basePresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //说明View销毁了，那么应该通知Presneter层
        basePresenter.onDestory();
    }
}
