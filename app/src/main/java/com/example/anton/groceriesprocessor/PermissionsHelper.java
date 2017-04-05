package com.example.anton.groceriesprocessor;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by anton on 05-Apr-17.
 */

public class PermissionsHelper {
    public static void EnsureAccountsPermission(Activity activity) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.GET_ACCOUNTS)) {

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        42);

            }
        }
    }
}
