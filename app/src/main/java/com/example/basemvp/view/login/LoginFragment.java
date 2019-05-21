package com.example.basemvp.view.login;

import android.view.View;

import com.example.basemvp.R;
import com.example.basemvp.base.BaseMvpFragment;
import com.example.basemvp.common.utils.LogUtils;
import com.example.basemvp.customview.CustomButton;
import com.example.basemvp.customview.CustomEditText;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginFragment extends BaseMvpFragment<LoginPresenterImpl> implements LoginContract.LoginView {
    @BindView(R.id.cet_email)
    CustomEditText cetEmail;
    @BindView(R.id.cet_password)
    CustomEditText cetPassword;
    @BindView(R.id.btn_login)
    CustomButton btnLogin;
    @BindView(R.id.btn_cancel)
    CustomButton btnCancel;
    private String TAG = LoginFragment.class.getSimpleName();

    @Override
    protected LoginPresenterImpl createPresenter() {
        return new LoginPresenterImpl(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void loginSuccess() {
        LogUtils.log(TAG, "Login success");
    }

    @Override
    public void loginError() {
        LogUtils.log(TAG, "Login error");
    }

    @OnClick({R.id.btn_login, R.id.btn_cancel})
    void doClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String email = cetEmail.getText().toString();
                String password = cetPassword.getText().toString();
                mvpPresenter.doLogin(email,password);
                break;
            case R.id.btn_cancel:
                break;
        }
    }
}
