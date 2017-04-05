package com.example.anton.groceriesprocessor;

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EnsureAccountsPermission();
    }

    private void EnsureAccountsPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.GET_ACCOUNTS)) {

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.GET_ACCOUNTS},
                        42);

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

    }

    public void sendMessage(View view){
        AccountManager am = AccountManager.get(this);
        Account[] accounts = am.getAccountsByType("com.splitwise.datasync");
        Account splitwise = accounts[0];

        Bundle options = new Bundle();
        am.getAuthToken(
                splitwise,
                "v3.0",
                options,
                this,
                new OnTokenAcquired(),
                new Handler(new OnError()));
    }

    private class OnError implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    }

    private class OnTokenAcquired implements AccountManagerCallback<Bundle> {
        @Override
        public void run(AccountManagerFuture<Bundle> result) {
            // Get the result of the operation from the AccountManagerFuture.
            Bundle bundle = null;
            try {
                bundle = result.getResult();
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }

            // The token is a named value in the bundle. The name of the value
            // is stored in the constant AccountManager.KEY_AUTHTOKEN.
            String token = bundle.getString(AccountManager.KEY_AUTHTOKEN);

            try {
                Intent launch = (Intent) result.getResult().get(AccountManager.KEY_INTENT);
            } catch (OperationCanceledException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (AuthenticatorException e) {
                e.printStackTrace();
            }
        }
    }
}
