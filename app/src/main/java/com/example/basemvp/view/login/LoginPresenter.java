package com.example.basemvp.view.login;

import com.example.basemvp.base.BasePresenter;

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
        attachView(loginView);
    }

    /**
     * @param email
     * @param password
     */
    public void doLogin(String email, String password) {

    }
}
