package com.niranisugar.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

public class PrivacyPolicyActivity extends Activity {

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);

       btnBack = findViewById(R.id.btnBack);
       btnBack.setOnClickListener(view -> {
           onBackPressed();
       });

    }
}