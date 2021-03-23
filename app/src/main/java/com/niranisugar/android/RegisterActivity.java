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

public class RegisterActivity extends Activity {

    EditText edtEmail,edtPassword,edtConfirmPassword,edtFullName,edtPhone,edtAddress;
    LinearLayout llEmail,llPassword,llConfirmPassword,llFullName,llPhone,llAddress;

    ImageView btnBack;
    TextView btnLoginNow;
    CardView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        findViews();

        edtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){
                    llEmail.setBackgroundResource(R.color.colorPrimary);
                    llPassword.setBackgroundResource(R.color.gray);
                    llConfirmPassword.setBackgroundResource(R.color.gray);
                    llPhone.setBackgroundResource(R.color.gray);
                    llAddress.setBackgroundResource(R.color.gray);
                    llFullName.setBackgroundResource(R.color.gray);
                }
            }
        });

        edtPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.colorPrimary);
                    llConfirmPassword.setBackgroundResource(R.color.gray);
                    llPhone.setBackgroundResource(R.color.gray);
                    llAddress.setBackgroundResource(R.color.gray);
                    llFullName.setBackgroundResource(R.color.gray);
                }
            }
        });

        edtConfirmPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.gray);
                    llConfirmPassword.setBackgroundResource(R.color.colorPrimary);
                    llPhone.setBackgroundResource(R.color.gray);
                    llAddress.setBackgroundResource(R.color.gray);
                    llFullName.setBackgroundResource(R.color.gray);
                }
            }
        });

        edtPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.gray);
                    llConfirmPassword.setBackgroundResource(R.color.gray);
                    llPhone.setBackgroundResource(R.color.colorPrimary);
                    llAddress.setBackgroundResource(R.color.gray);
                    llFullName.setBackgroundResource(R.color.gray);
                }
            }
        });

        edtFullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.gray);
                    llConfirmPassword.setBackgroundResource(R.color.gray);
                    llPhone.setBackgroundResource(R.color.gray);
                    llAddress.setBackgroundResource(R.color.gray);
                    llFullName.setBackgroundResource(R.color.colorPrimary);
                }
            }
        });

        edtAddress.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b){

                    llEmail.setBackgroundResource(R.color.gray);
                    llPassword.setBackgroundResource(R.color.gray);
                    llConfirmPassword.setBackgroundResource(R.color.gray);
                    llPhone.setBackgroundResource(R.color.gray);
                    llAddress.setBackgroundResource(R.color.colorPrimary);
                    llFullName.setBackgroundResource(R.color.gray);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnLoginNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this,MainActivity.class));
            }
        });
    }

    private void findViews() {

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        edtAddress = findViewById(R.id.edtAddress);
        edtFullName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhoneNo);

        llEmail = findViewById(R.id.llEmail);
        llPassword =  findViewById(R.id.llPassword);
        llConfirmPassword = findViewById(R.id.llConfirmPassword);
        llAddress = findViewById(R.id.llAddress);
        llFullName = findViewById(R.id.llName);
        llPhone = findViewById(R.id.llPhoneNo);

        btnBack = findViewById(R.id.btnBack);

        btnLoginNow = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

    }
}