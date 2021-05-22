package com.niranisugar.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class TermsAndConditionActivity extends Activity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_of_use);

       btnBack = findViewById(R.id.btnBack);
       btnBack.setOnClickListener(view -> {
           onBackPressed();
       });

    }
}