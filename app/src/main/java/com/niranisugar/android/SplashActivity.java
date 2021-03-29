package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class SplashActivity extends Activity{


    SharedPreferences prefSelectedOption;
    SharedPreferences.Editor editorSelectedOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        prefSelectedOption = getSharedPreferences("SELECTEDPAGE",MODE_PRIVATE);
        editorSelectedOption = prefSelectedOption.edit();
        editorSelectedOption.putInt("NO",0);
        editorSelectedOption.apply();

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