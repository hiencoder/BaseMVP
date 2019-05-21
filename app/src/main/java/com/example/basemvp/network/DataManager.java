package com.example.basemvp.network;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Response;


public class DataManager extends ApiClient {
    private static DataManager mDataManager;
    public static DataManager getInstall() {
        if (mDataManager == null)
            mDataManager = new DataManager();
        return mDataManager;
    }

    //Handle login
    public Observable<Response<ResponseBody>> loginAccount(String mail, String pass) {
        return getClient().loginAccount(mail, pass);
    }


}
