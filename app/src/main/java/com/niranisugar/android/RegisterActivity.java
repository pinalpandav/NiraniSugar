package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.API.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends Activity {

    EditText edtEmail,edtPassword,edtConfirmPassword,edtFullName,edtPhone,edtAddress;
    LinearLayout llEmail,llPassword,llConfirmPassword,llFullName,llPhone,llAddress;

    ImageView btnBack;
    TextView btnLoginNow;
    CardView btnRegister;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    KProgressHUD hud;
    ApiInterface apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

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

                if(Validate()){
                    String fullName = edtFullName.getText().toString().trim();
                    String email = edtEmail.getText().toString().trim();
                    String phoneNo = edtPhone.getText().toString().trim();
                    String address = edtAddress.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    String confirmpassword = edtConfirmPassword.getText().toString().trim();

                    RegisterAPI(fullName,email,phoneNo,address,password,confirmpassword);
                }

            }
        });
    }

    private void RegisterAPI(String fullName, String email, String phoneNo, String address, String password,String confirmpassword) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.Register(fullName,email,phoneNo,address,password,confirmpassword);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("0")) {
                                Toast.makeText(RegisterActivity.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(RegisterActivity.this, jsonObject.getJSONObject("errors").getJSONArray("email").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private boolean Validate() {
        if(edtFullName.getText().toString().trim().isEmpty()){
            edtFullName.setError("Please enter Full Name");
            return false;
        }else if(edtEmail.getText().toString().trim().isEmpty()){
            edtEmail.setError("Please enter Email");
            return false;
        }else if(!edtEmail.getText().toString().trim().matches(emailPattern)){
            edtEmail.setError("Please enter valid Email");
            return false;
        }else if(edtPhone.getText().toString().trim().isEmpty()){
            edtPhone.setError("Please enter Phone No");
            return false;
        }else if(edtPhone.getText().toString().trim().length() != 10){
            edtPhone.setError("Please enter valid Phone No");
            return false;
        }else if(edtAddress.getText().toString().trim().isEmpty()){
            edtAddress.setError("Please enter Address");
            return false;
        }else if(edtPassword.getText().toString().trim().isEmpty()){
            edtPassword.setError("Please enter Password");
            return false;
        }else if(edtPassword.getText().toString().trim().length() < 6){
            edtPassword.setError("Password must be at least 6 Characters");
            return false;
        }else if(edtConfirmPassword.getText().toString().trim().isEmpty()){
            edtConfirmPassword.setError("Please enter Confirm Password");
            return false;
        }else if(edtConfirmPassword.getText().toString().trim().length() < 6){
            edtConfirmPassword.setError("Confirm Password must be at least 6 Characters");
            return false;
        }else if(!edtPassword.getText().toString().trim().equals(edtConfirmPassword.getText().toString().trim())){
            Toast.makeText(this, "Password does not match!", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            return true;
        }
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