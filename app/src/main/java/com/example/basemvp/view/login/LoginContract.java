package com.example.basemvp.view.login;

import com.example.basemvp.base.BaseView;

public interface LoginContract {
    interface LoginView extends BaseView {
        void loginSuccess();

        void loginError();
    }

    interface LoginPresenter{
        void doLogin(String email, String password);
    }
}
