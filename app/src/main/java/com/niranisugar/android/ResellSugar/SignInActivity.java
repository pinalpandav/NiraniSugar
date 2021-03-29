package com.niranisugar.android.ResellSugar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.niranisugar.android.R;

public class SignInActivity extends Activity {

    EditText edtPhoneNo;
    LinearLayout llPhoneNo;

    CardView btnSendOTP, btnSignIn;

    TextView btnSignUp;

    ImageView btnBack;

    EditText editText1, editText2, editText3, editText4, editText5, editText6;
    LinearLayout FirstLayout, SecondLayout;

    TextView ResendOTP;
    boolean Resend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_resell_sugar);

        findViews();

        edtPhoneNo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    llPhoneNo.setBackgroundResource(R.color.colorPrimary);
                }
            }
        });


        btnBack.setOnClickListener(view -> {
            if (SecondLayout.getVisibility() == View.VISIBLE) {
                Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
                SecondLayout.startAnimation(animSlide);
                animSlide.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        FirstLayout.setVisibility(View.VISIBLE);
                        SecondLayout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            } else {
                onBackPressed();
            }
        });


        btnSendOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtPhoneNo.getText().toString().trim().isEmpty()) {
                    edtPhoneNo.setError("Please enter Mobile No.");
                    edtPhoneNo.requestFocus();
                } else if (edtPhoneNo.getText().toString().trim().length() != 10) {
                    edtPhoneNo.setError("Please enter valid Mobile No.");
                    edtPhoneNo.requestFocus();
                } else {
                    callAPI();
                }
            }
        });

        btnSignIn.setOnClickListener(view -> {
            hideKeyboard(SignInActivity.this);
            if (ValidateThree()) {
                String Code = editText1.getText().toString().trim() + editText2.getText().toString().trim()
                        + editText3.getText().toString().trim() + editText4.getText().toString().trim() +
                        editText5.getText().toString().trim() + editText6.getText().toString().trim();
//                callOTPVerificationAPI(Integer.parseInt(Code));
            }
        });

        btnSignUp.setOnClickListener(view -> {
            Intent i = new Intent(SignInActivity.this, SignUpActivity.class);
            startActivity(i);
            finish();
        });

    }

    public boolean ValidateThree() {
        String Code = editText1.getText().toString().trim() + editText2.getText().toString().trim()
                + editText3.getText().toString().trim() + editText4.getText().toString().trim() +
                editText5.getText().toString().trim() + editText6.getText().toString().trim();

        if (Code.length() != 6) {
            Toast.makeText(this, "Opps! OTP must be 6 character.", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return true;
        }
    }

    public void callAPI() {
//        hud.show();
//        final JsonObject jobjdata = new JsonObject();
//        jobjdata.addProperty("mobileNumber", edtMobileNo.getText().toString().trim());
//
//        apiService = ApiClient.getClient().create(ApiInterface.class);
//        Call<String> callCard = apiService.Login(jobjdata.toString());
//        callCard.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                hud.dismiss();
//                // Toast.makeText(ds, String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
//                if (response.code() == 200) {
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(response.body());
//                        if (jsonObject.getString("status").equals("1")) {
        Animation animSlide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_left);
        FirstLayout.startAnimation(animSlide);
        animSlide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                SecondLayout.setVisibility(View.VISIBLE);
                FirstLayout.setVisibility(View.GONE);
                editText1.requestFocus();
                showKeyBoard(SignInActivity.this, editText1);
                new CountDownTimer(180000, 1000) {

                    public void onTick(long millisUntilFinished) {

                        int seconds = (int) (millisUntilFinished / 1000);
                        int minutes = seconds / 60;
                        seconds = seconds % 60;
                        ResendOTP.setText("Resend OTP in " + String.format("%02d", minutes)
                                + ":" + String.format("%02d", seconds));

                        //here you can have your logic to set text to edittext
                    }

                    public void onFinish() {
                        Resend = true;
                        ResendOTP.setText("Resend OTP");
                    }

                }.start();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
//                            Toast.makeText(LoginWithMobileNoActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(LoginWithMobileNoActivity.this, jsonObject.getString("msg"), Toast.LENGTH_SHORT).show();
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//
//            }
//        });
    }

    public class GenericTextWatcher implements TextWatcher {
        private View view;

        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void afterTextChanged(Editable editable) {
            // TODO Auto-generated method stub
            String text = editable.toString();
            switch (view.getId()) {

                case R.id.edittext1:
                    if (text.length() == 1)
                        editText2.requestFocus();
                    break;
                case R.id.edittext2:
                    if (text.length() == 1)
                        editText3.requestFocus();

                    break;
                case R.id.edittext3:
                    if (text.length() == 1)
                        editText4.requestFocus();

                    break;
                case R.id.edittext4:
                    if (text.length() == 1)
                        editText5.requestFocus();

                    break;
                case R.id.edittext5:
                    if (text.length() == 1)
                        editText6.requestFocus();
                    break;
                case R.id.edittext6:
                    break;
            }
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            // TODO Auto-generated method stub
        }
    }

    public void showKeyBoard(Activity activity, EditText editText) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        // check if no view has focus:
        View currentFocusedView = activity.getCurrentFocus();
        if (currentFocusedView != null) {
            inputManager.hideSoftInputFromWindow(currentFocusedView.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    private void findViews() {

        edtPhoneNo = findViewById(R.id.edtPhoneNo);
        llPhoneNo = findViewById(R.id.llPhoneNo);

        btnBack = findViewById(R.id.btnBack);

        btnSendOTP = findViewById(R.id.btnSendOTP);

        editText1 = findViewById(R.id.edittext1);
        editText2 = findViewById(R.id.edittext2);
        editText3 = findViewById(R.id.edittext3);
        editText4 = findViewById(R.id.edittext4);
        editText5 = findViewById(R.id.edittext5);
        editText6 = findViewById(R.id.edittext6);

        editText1.addTextChangedListener(new GenericTextWatcher(editText1));
        editText2.addTextChangedListener(new GenericTextWatcher(editText2));
        editText3.addTextChangedListener(new GenericTextWatcher(editText3));
        editText4.addTextChangedListener(new GenericTextWatcher(editText4));
        editText5.addTextChangedListener(new GenericTextWatcher(editText5));
        editText6.addTextChangedListener(new GenericTextWatcher(editText6));

        FirstLayout = findViewById(R.id.FirstPart);
        SecondLayout = findViewById(R.id.SecondPart);
        ResendOTP = findViewById(R.id.ResendOTP);

        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignUp = findViewById(R.id.btnSignUp);

    }
}