package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class WelcomeActivity extends Activity implements View.OnClickListener {

    TextView btnSkip;
    CardView cvLogin, cvRegister;
    SharedPreferences prefUserData;
    SharedPreferences.Editor editorUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        findViews();

        cvLogin.setOnClickListener(this);
        cvRegister.setOnClickListener(this);
        btnSkip.setOnClickListener(this);
    }

    private void findViews() {

        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        editorUserData = prefUserData.edit();

        cvLogin = findViewById(R.id.btnLogin);
        cvRegister = findViewById(R.id.btnRegister);
        btnSkip = findViewById(R.id.btnSkip);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnLogin) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (view.getId() == R.id.btnRegister) {
            startActivity(new Intent(this, RegisterActivity.class));
        } else if (view.getId() == R.id.btnSkip) {
            editorUserData.putBoolean("isSkip",true);
            editorUserData.apply();
            startActivity(new Intent(this, MainActivity.class));
        }
    }
}