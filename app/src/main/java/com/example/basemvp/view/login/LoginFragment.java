package com.example.basemvp.view.login;

import com.example.basemvp.R;
import com.example.basemvp.base.BaseMvpFragment;
import com.example.basemvp.common.utils.LogUtils;

public class LoginFragment extends BaseMvpFragment<LoginPresenterImpl> implements LoginContract.LoginView {
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
        LogUtils.log(TAG,"Login success");
    }

    @Override
    public void loginError() {
        LogUtils.log(TAG,"Login error");
    }
}
