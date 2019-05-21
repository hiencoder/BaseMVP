package com.example.basemvp.view.login;

import com.example.basemvp.base.BaseView;

public interface LoginView extends BaseView {
    void loginSuccess();

    void loginError();
}
