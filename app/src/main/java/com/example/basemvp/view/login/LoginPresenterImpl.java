package com.example.basemvp.view.login;

import com.example.basemvp.base.BasePresenter;
import com.example.basemvp.network.DataManager;

import okhttp3.Headers;
import okhttp3.ResponseBody;

public class LoginPresenterImpl extends BasePresenter<LoginContract.LoginView> implements LoginContract.LoginPresenter {
    private LoginContract.LoginView loginView;

    public LoginPresenterImpl(LoginContract.LoginView loginView) {
        this.loginView = loginView;
        attachView(loginView);
    }

    @Override
    public void doLogin(String email, String password) {
        addSubscriptionResponseBody(DataManager.getInstall().loginAccount(email, password), response -> {
            Headers headers = response.headers();
            String token = headers.get("Bearer");

            mvpView.hideLoading();
            loginView.loginSuccess();
        }, throwable -> {
            mvpView.hideLoading();
            loginView.loginError();
        });
    }
}
