package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

public class LoginActivity extends Activity {

    EditText edtEmail,edtPassword;
    LinearLayout llEmail,llPassword;
    ImageView btnBack;
    TextView btnRegisterNow;
    CardView btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViews();

        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    llEmail.setBackgroundResource(R.color.colorPrimary);
                    llPassword.setBackgroundResource(R.color.gray);
                }
            }
        });

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.colorPrimary);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegisterNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
            }
        });
    }

    private void findViews() {

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        llEmail = findViewById(R.id.llEmail);
        llPassword =  findViewById(R.id.llPassword);

        btnBack = findViewById(R.id.btnBack);
        btnRegisterNow = findViewById(R.id.btnRegister);

        btnLogin = findViewById(R.id.btnLogin);

    }
}