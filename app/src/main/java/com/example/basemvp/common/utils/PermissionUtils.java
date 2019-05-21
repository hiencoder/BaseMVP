package com.example.basemvp.common.utils;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

import androidx.core.app.ActivityCompat;

public class PermissionUtils {
    private static PermissionUtils mInstance;
    private static String TAG = PermissionUtils.class.getSimpleName();

    public static synchronized PermissionUtils getInstance() {
        if (mInstance == null) {
            mInstance = new PermissionUtils();
        }
        return mInstance;
    }

    //Camera permission
    public boolean checkCameraPermissionGranted(Context context) {
        return (Build.VERSION.SDK_INT >= 23
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED);
    }

    //Storage permission
    public boolean checkStoragePermission(Context context) {
        return (Build.VERSION.SDK_INT >= 23
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED);

    }

    //Location permission
    public boolean checkLocationPermission(Context context) {
        return (Build.VERSION.SDK_INT >= 23
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED);
    }

    /**
     * @param activity
     */
    public void requestAllPermission(Activity activity) {
        if (!checkCameraPermissionGranted(activity)
                && !checkLocationPermission(activity)
                && !checkStoragePermission(activity)) {
            Dexter.withActivity(activity)
                    .withPermissions(Manifest.permission.CAMERA,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new MultiplePermissionsListener() {
                        @Override
                        public void onPermissionsChecked(MultiplePermissionsReport report) {
                            if (report.areAllPermissionsGranted()) {
                                LogUtils.log(TAG, "Permissions are granted!");
                            }
                            if (report.isAnyPermissionPermanentlyDenied()) {
                                //If permission denied
                                //showSettingDialog();
                            }
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                @Override
                public void onError(DexterError error) {
                    LogUtils.log_error(error.name());
                }
            }).onSameThread().check();
        }
    }

    /**
     * Request camera permission
     *
     * @param activity
     */
    public void requestCameraPermission(Activity activity) {
        if (!checkCameraPermissionGranted(activity)) {
            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            LogUtils.log(TAG, "Permission is granted!");
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            LogUtils.log(TAG, "Permission is denied!");
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                @Override
                public void onError(DexterError error) {

                }
            }).onSameThread().check();
        }
    }

    /**
     * Request storage
     *
     * @param activity
     */
    public void requestStoragePermission(Activity activity) {
        if (!checkStoragePermission(activity)) {
            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            LogUtils.log(TAG, "Permission is granted!");
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            LogUtils.log(TAG, "Permission is denied!");
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                @Override
                public void onError(DexterError error) {
                    LogUtils.log_error(error.name());
                }
            }).onSameThread().check();
        }
    }

    /**
     * Request storage
     *
     * @param activity
     */
    public void requestLocationPermission(Activity activity) {
        if (!checkStoragePermission(activity)) {
            Dexter.withActivity(activity)
                    .withPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            LogUtils.log(TAG, "Permission is granted!");
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse response) {
                            LogUtils.log(TAG, "Permission is denied!");
                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                            token.continuePermissionRequest();
                        }
                    }).withErrorListener(new PermissionRequestErrorListener() {
                @Override
                public void onError(DexterError error) {
                    LogUtils.log_error(error.name());
                }
            }).onSameThread().check();
        }
    }
    /*Show setting dialog*/
    private void showSettingDialog(Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle("Thông báo");
        builder.setMessage("Đến màn hình setting app!");
        builder.setPositiveButton("GoTo Setting", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSetting(activity);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }

    /*Open setting app*/
    private void openSetting(Activity activity) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
        intent.setData(uri);
        activity.startActivityForResult(intent, 113);
    }
}
