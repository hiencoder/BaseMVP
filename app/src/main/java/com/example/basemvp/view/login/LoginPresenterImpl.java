package com.example.basemvp.view.login;

import com.example.basemvp.base.BasePresenter;
import com.example.basemvp.network.DataManager;

public class LoginPresenterImpl extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {
    private LoginContract.LoginView loginView;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        attachView(loginView);
    }

    @Override
    public void doLogin(String email, String password) {
        addSubscriptionResponseBody(DataManager.getInstall().loginAccount(email, password), response -> {
            mvpView.hideLoading();
            loginView.loginSuccess();
        }, throwable -> {
            mvpView.hideLoading();
            loginView.loginError();
        });
    }
}
