package com.example.basemvp.base;

import com.example.basemvp.network.response.BaseResponse;

public interface BaseView {
    void showLoading();

    void hideLoading();

    void onRequestError(BaseResponse response);

    void onRequestFailure(Throwable throwable);
}
