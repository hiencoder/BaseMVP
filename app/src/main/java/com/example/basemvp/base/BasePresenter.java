package com.example.basemvp.base;

import com.example.basemvp.common.utils.NetworkUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Response;

public class BasePresenter<V> {
    protected V mvpView;
    private BaseFragment baseFragment;
    private BaseActivity baseActivity;
    private CompositeDisposable mCompositeDisposable;

    public void attachView(V mvpView){
        this.mvpView = mvpView;
        if (mvpView instanceof BaseFragment){
            baseFragment = (BaseFragment) mvpView;
        }
        if (mvpView instanceof BaseActivity){
            baseActivity = (BaseActivity) mvpView;
        }
    }

    public V getMvpView() {
        return mvpView;
    }

    public void detachView(){
        onUnSubscribe();
    }

    private void onUnSubscribe() {
        if (mCompositeDisposable != null && mCompositeDisposable.size() > 0) {
            mCompositeDisposable.clear();
            mCompositeDisposable = null;
        }
    }

    public void addSubscription(Observable observable, Consumer success, Consumer<? super Throwable> throwable) {
        if (isNetworkNotAvailable()) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable));
    }

    public void addSubscriptionWithDefaultResponse(Observable observable, Consumer<? super Response> success, Consumer<? super Throwable> throwable) {
        if (isNetworkNotAvailable()) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable));
    }


    protected void addSubscriptionResponseBody(Observable observable, Consumer<? super Response<ResponseBody>> success, Consumer<? super Throwable> throwable) {
        if (isNetworkNotAvailable()) {
            return;
        }
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(success, throwable));

    }


    private boolean isNetworkNotAvailable() {
        if (baseFragment != null) {
            if (baseFragment.getContext() != null && NetworkUtils.checkNetworkAvailable(baseFragment.getContext())) {
                baseFragment.internetRequest();
                baseFragment.hideLoading();
                return true;
            } else {
                return false;
            }
        } else if (baseActivity != null) {
            if (NetworkUtils.checkNetworkAvailable(baseActivity)) {
                baseActivity.internetRequest();
                baseActivity.hideLoading();
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
