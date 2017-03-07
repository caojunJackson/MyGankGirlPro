package caojun.com.myapplication.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import caojun.com.myapplication.controller.ActivityController;

/**
 * Created by tiger on 2017/3/7.
 */

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityController.addActivity(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityController.removeActivity(this);
    }

    private static  onRequestPermission mListener;

    public static void requestPermission(String[] permissions , onRequestPermission listener){
        if(listener == null){
            return;
        }

        mListener = listener;
        Activity context = ActivityController.getTopActivity();
        List<String> permissionDeny = new ArrayList<>();
        for (String permission  : permissions) {
            if(ActivityCompat.checkSelfPermission(context , permission) != PackageManager.PERMISSION_GRANTED){
                permissionDeny.add(permission);
            }
        }

        if(permissionDeny.isEmpty()){
            mListener.onGrant();
        }else{
            ActivityCompat.requestPermissions(context , permissionDeny.toArray(new String[permissionDeny.size()]) , 1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 1:
                if(grantResults.length > 0){
                    List<String> permissionDeny = new ArrayList<>();
                    String permission;
                    int grantResult;
                    for (int i = 0; i < grantResults.length; i++) {
                        permission = permissions[i];
                        grantResult = grantResults[i];

                        if(grantResult != PackageManager.PERMISSION_GRANTED){
                            permissionDeny.add(permission);
                        }
                    }

                    if(permissionDeny.isEmpty()){
                        mListener.onGrant();
                    }else{
                        mListener.onDeny(permissionDeny);
                    }

                }

                break;

            default:
                break;
        }


    }

    public interface onRequestPermission{
        void onGrant();
        void onDeny(List<String> denyedPermissions);

    }


    public static void getAppDetailSettingIntent() {
        Activity topActivity = ActivityController.getTopActivity();
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", topActivity.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings","com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", topActivity.getPackageName());
        }
        topActivity.startActivity(localIntent);
    }

}
