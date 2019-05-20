package com.example.basemvp.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basemvp.R;
import com.example.basemvp.common.utils.LogUtils;
import com.example.basemvp.network.response.BaseResponse;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseFragment extends Fragment implements BaseView {
    protected BaseActivity baseActivity;

    protected abstract int getLayoutId();

    protected abstract void setupData();

    protected Boolean mIsBackPress = true;

    CompositeDisposable disposable;

    private ProgressDialog progressDialog;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            baseActivity = (BaseActivity) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupData();
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtils.log(getClass().getSimpleName(), "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.log(getClass().getSimpleName(), "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.log(getClass().getSimpleName(), "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtils.log(getClass().getSimpleName(), "onStop");
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtils.log(getClass().getSimpleName(), "onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtils.log(getClass().getSimpleName(), "onDestroyView");
    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void onRequestError(BaseResponse response) {

    }

    @Override
    public void onRequestFailure(Throwable throwable) {

    }

    /**
     * Show dialog loading
     * @param hasCancel
     */
    public void showLoadingDialog(boolean hasCancel) {
        if (isVisible()) {
            //If fragment visible
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(getActivity());
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(hasCancel);
                progressDialog.setMessage(getString(R.string.loading));
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            }
            if (!progressDialog.isShowing()) {
                LogUtils.log(getClass().getSimpleName(), "show Progress");
                try {
                    progressDialog.show();
                } catch (Exception ex) {
                    LogUtils.log_error(ex.getMessage());
                }
            }
        }
    }

    /**
     * Hide loading dialog
     */
    public void hideLoadingDialog() {
        if (isVisible()) {
            if (progressDialog != null && progressDialog.isShowing()) {
                LogUtils.log(getClass().getSimpleName(), "dismiss Progress");
                progressDialog.dismiss();
            }
        }
    }

    public Boolean onBackPressed() {
        return mIsBackPress;
    }
}
