package com.itcast.mvpdemo.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itcast.mvpdemo.R;
import com.itcast.mvpdemo.model.http.HttpHelper;
import com.itcast.mvpdemo.presenter.BasePresenter;
import com.itcast.mvpdemo.presenter.LoginPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView{

    @Bind(R.id.et_username)
    EditText etUsername;
    @Bind(R.id.et_psw)
    EditText etPsw;
    @Bind(R.id.btn_login)
    Button btnLogin;


    @Override
    public BasePresenter getPresenter() {
        return new LoginPresenter(this);
    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        //1.获取用户输入的数据
        String username = etUsername.getText().toString().trim();
        String psw = etPsw.getText().toString();

        //2.进行登录
        ((LoginPresenter)basePresenter).login(username,psw);
    }

    //提示用户登录成功
    @Override
    public void showLoginSuccess() {
        Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
    }

    //提示用户登录失败
    @Override
    public void showLoginFail() {
        Toast.makeText(LoginActivity.this, "登录失败!", Toast.LENGTH_SHORT).show();
    }

    //显示用户名长度不正确
    @Override
    public void showUsernameLengthError() {
        Toast.makeText(LoginActivity.this, "用户名长度不正确!", Toast.LENGTH_SHORT).show();
    }

    //提示用户不能为空
    @Override
    public void showNotNull() {
         Toast.makeText(LoginActivity.this, "用户名或者密码不能为空!", Toast.LENGTH_SHORT).show();
    }


}
