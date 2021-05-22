package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends Activity {

    EditText edtEmail,edtPassword;
    LinearLayout llEmail,llPassword;
    ImageView btnBack;
    TextView btnRegisterNow;
    CardView btnLogin;
    CardView btnLoginWithGoogle;
    public static final int RC_SIGN_IN = 100;
    KProgressHUD hud;
    ApiInterface apiService;
    SharedPreferences prefUserData;
    SharedPreferences.Editor editorUserData;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
                if(Validate()){
                    String email = edtEmail.getText().toString().trim();
                    String password = edtPassword.getText().toString().trim();
                    LoginAPI(email,password);
                }

            }
        });

        btnLoginWithGoogle.setOnClickListener(view -> {
            signInwithGoogle();
        });
    }

    GoogleSignInClient mGoogleSignInClient;

    protected void signInwithGoogle(){
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            Toast.makeText(this, "Login Success " + account.getEmail(), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else{
            Toast.makeText(this, "Login Fail ", Toast.LENGTH_SHORT).show();
        }
    }

    private void findViews() {
        prefUserData = getSharedPreferences("USER_DATA",MODE_PRIVATE);
        editorUserData = prefUserData.edit();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        llEmail = findViewById(R.id.llEmail);
        llPassword =  findViewById(R.id.llPassword);

        btnBack = findViewById(R.id.btnBack);
        btnRegisterNow = findViewById(R.id.btnRegister);

        btnLogin = findViewById(R.id.btnLogin);

        btnLoginWithGoogle = findViewById(R.id.btnLoginWithGoogle);

    }

    private void LoginAPI(String email, String password) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.Login(email,password);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                Toast.makeText(LoginActivity.this, jsonObject.getString("message").toString(), Toast.LENGTH_SHORT).show();

                                editorUserData.putString("user_data", jsonObject.toString());
                                editorUserData.putString("token", jsonObject.getString("access_token"));
                                editorUserData.apply();

                                Intent i = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(LoginActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
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
        if(edtEmail.getText().toString().trim().isEmpty()){
            edtEmail.setError("Please enter Email");
            return false;
        }else if(!edtEmail.getText().toString().trim().matches(emailPattern)){
            edtEmail.setError("Please enter valid Email");
            return false;
        }else if(edtPassword.getText().toString().trim().isEmpty()){
            edtPassword.setError("Please enter Password");
            return false;
        }else{
            return true;
        }
    }
}