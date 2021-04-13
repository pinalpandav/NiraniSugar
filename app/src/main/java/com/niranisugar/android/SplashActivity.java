package com.niranisugar.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

    SharedPreferences prefFromWhere;
    SharedPreferences.Editor editorfromWhere;

    SharedPreferences prefUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefFromWhere = getSharedPreferences("FROMWHERE", Context.MODE_PRIVATE);
        editorfromWhere = prefFromWhere.edit();

        editorfromWhere.putBoolean("isFromMain", true);
        editorfromWhere.apply();


        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (prefUserData.getString("token", "").isEmpty()) {
                    if (prefUserData.getBoolean("isSkip", false)) {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        finish();
                    } else {
                        Intent i = new Intent(SplashActivity.this, WelcomeActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                        finish();
                    }
                } else {
                    Intent i = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                    finish();
                }
            }
        }, 1500);



    }


}