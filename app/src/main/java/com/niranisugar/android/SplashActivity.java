package com.niranisugar.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class SplashActivity extends Activity{

    SharedPreferences prefFromWhere;
    SharedPreferences.Editor editorfromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefFromWhere = getSharedPreferences("FROMWHERE", Context.MODE_PRIVATE);
        editorfromWhere = prefFromWhere.edit();

        editorfromWhere.putBoolean("isFromMain",true);
        editorfromWhere.apply();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this,WelcomeActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
            }
        },1500);

    }




}