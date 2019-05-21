package com.example.basemvp.base;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.basemvp.R;
import com.example.basemvp.common.Constants;
import com.example.basemvp.common.MySharePref;
import com.example.basemvp.common.utils.DialogUtils;
import com.example.basemvp.common.utils.FragmentUtils;
import com.example.basemvp.customview.CustomTextView;
import com.example.basemvp.network.response.BaseResponse;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected String TAG = getClass().getSimpleName();
    private Class<?> mClass;
    //private static final int CAMERA_PERMISSION = 1;
    public CompositeDisposable disposable;
    protected FragmentManager fragmentManager;
    protected FragmentUtils fragmentUtils;
    public int container;
    public MySharePref mySharePref;

    protected abstract int getLayoutId();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        setContentView(getLayoutId());
        //Create instance of MySharePref
        mySharePref = MySharePref.getInstance(this);
        //Get access token from shared
        Constants.ACCESS_TOKEN = mySharePref.getUserToken();
        fragmentManager = getSupportFragmentManager();
        fragmentUtils = FragmentUtils.getInstance();
        overridePendingTransition(R.anim.slide_right_in, R.anim.slide_left_out);
        ButterKnife.bind(this);
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_right_out);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    public void showLoading() {
        showLoading(true);
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    public void onRequestError(BaseResponse response) {
        DialogUtils.showDialogError(this, response);
    }

    @Override
    public void onRequestFailure(Throwable throwable) {
        DialogUtils.showDialogFailureRequest(this, throwable);
    }

    public Dialog mProgressDialog;

    /**
     * @param isCancel
     */
    public void showLoading(boolean isCancel) {
        if (mProgressDialog == null) {
            mProgressDialog = new Dialog(this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setCancelable(isCancel);
            mProgressDialog.setContentView(R.layout.dialog_loading);
            if (mProgressDialog.getWindow() != null && !isFinishing()) {
                mProgressDialog.getWindow().setBackgroundDrawableResource(
                        R.color.transparent);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

                if (!mProgressDialog.isShowing())
                    mProgressDialog.show();
                mProgressDialog.getWindow().setAttributes(lp);
            }
        } else {
            if (!isFinishing() && !mProgressDialog.isShowing())
                mProgressDialog.show();
        }
    }

    public void hideLoadingDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    protected void setupToolbar(String title, boolean enableBackButton) {
        try {
            setSupportActionBar(findViewById(R.id.my_toolbar));
            if (getSupportActionBar() != null) {
                CustomTextView txtTitle = findViewById(R.id.tv_title_name);
                AppCompatImageButton btnBack = findViewById(R.id.btn_back);
                if (enableBackButton) {
                    btnBack.setVisibility(View.VISIBLE);
                    btnBack.setOnClickListener(view -> {
                        onBackPressed();
                    });
                } else {
                    btnBack.setVisibility(View.GONE);
                }
                txtTitle.setText(title);
            }
        } catch (Exception e) {
            Log.e(TAG + "setupToolbar", e.getMessage());
        }
    }

    public void internetRequest(){
        DialogUtils.showDialogMessage(this, getString(R.string.msg_request_no_internet));
    }
    private static int CAMERA_PERMISSION = 1;

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClass = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    /**
     * @param fragment
     */
    public void replaceFragment(BaseFragment fragment) {
        fragmentUtils.replaceFragment(fragmentManager, fragment, container);
    }

    //Add fragment first
    public void replaceFragmentFirst(BaseFragment fragment){
        fragmentUtils.replaceFragmentWithoutBackStack(fragmentManager,fragment,container);
    }

    public void addFragment(BaseFragment fragment) {
        fragmentUtils.addFragment(fragmentManager, fragment, container);
    }

    public void replaceFragmentWithoutBackStack(BaseFragment fragment) {
        fragmentUtils.replaceFragmentWithoutBackStack(fragmentManager, fragment, container);
    }

    public void addFragmentWithoutBackStack(BaseFragment fragment){
        fragmentUtils.addFragmentWithoutBackStack(fragmentManager,fragment,container);
    }
}
