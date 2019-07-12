package com.mvpdemo.mvpdemo.module.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mvpdemo.mvpdemo.R;
import com.mvpdemo.mvpdemo.base.BaseActivity;
import com.mvpdemo.mvpdemo.base.BasePresenter;
import com.mvpdemo.mvpdemo.base.BaseResponse;
import com.mvpdemo.mvpdemo.base.IBasePresenter;
import com.mvpdemo.mvpdemo.module.main.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class LoginActivity extends BaseActivity<LoginPresenter> implements ILoginView {
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.bt_login)
    Button bt_login;

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initViews() {


        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.login();
            }
        });

    }

    @Override
    public String getUserName() {
        return et_username.getText().toString();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString();
    }

    @Override
    public void setData(BaseResponse loginResponse) {
        Map<String, String> map = new HashMap<>();
        map = (Map<String, String>) loginResponse.getInfo();
        map.get("");
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void loadData() {

    }

}
