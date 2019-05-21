package com.example.basemvp;

import androidx.annotation.Nullable;

import android.os.Bundle;

import com.example.basemvp.base.BaseActivity;
import com.example.basemvp.view.login.LoginFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        container = R.id.fl_content;
        replaceFragmentFirst(new LoginFragment());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {

    }


}
