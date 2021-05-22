package com.niranisugar.android;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddAddressActivity extends Activity {

    ImageView btnBack;
    TextView btnAddAddress;

    LinearLayout llName, llAddress, llCity, llState, llCountry, llPincode, llPhoneno, llLandMark;
    EditText edtName, edtAddress, edtCity, edtState, edtCountry, edtPincode, edtPhoneno, edtLandMark;
    KProgressHUD hud;
    ApiInterface apiService;
    String access_token;
    SharedPreferences prefUserData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        access_token = prefUserData.getString("token", "");

        findViews();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnAddAddress.setOnClickListener(view -> {
            onBackPressed();
        });

        edtName.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtAddress.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtCity.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtState.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtCountry.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtPincode.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.gray));
            }
        });

        edtLandMark.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.gray));
                llLandMark.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        edtPhoneno.setOnFocusChangeListener((view, b) -> {
            if (b) {
                llName.setBackgroundColor(getResources().getColor(R.color.gray));
                llAddress.setBackgroundColor(getResources().getColor(R.color.gray));
                llCountry.setBackgroundColor(getResources().getColor(R.color.gray));
                llCity.setBackgroundColor(getResources().getColor(R.color.gray));
                llState.setBackgroundColor(getResources().getColor(R.color.gray));
                llPincode.setBackgroundColor(getResources().getColor(R.color.gray));
                llPhoneno.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        btnAddAddress.setOnClickListener(view -> {
            if (Validate()) {
                String name = edtName.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String city = edtCity.getText().toString().trim();
                String state = edtState.getText().toString().trim();
                String country = edtCountry.getText().toString().trim();
                String pincode = edtPincode.getText().toString().trim();
                String phoneno = edtPhoneno.getText().toString().trim();
                String landmark = edtLandMark.getText().toString().trim();
                if (access_token.equals("")) {
                    Toast.makeText(this, "Please do login to the application", Toast.LENGTH_SHORT).show();
                } else {
                    AddAddressAPI(name, address, city, state, country, pincode, phoneno, landmark);
                }
            }
        });

    }

    private boolean Validate() {
        if (edtName.getText().toString().trim().isEmpty()) {
            edtName.setError("Please enter Name");
            return false;
        } else if (edtAddress.getText().toString().trim().isEmpty()) {
            edtAddress.setError("Please enter Address");
            return false;
        } else if (edtLandMark.getText().toString().trim().isEmpty()) {
            edtLandMark.setError("Please enter LandMark");
            return false;
        } else if (edtCity.getText().toString().trim().isEmpty()) {
            edtCity.setError("Please enter City");
            return false;
        } else if (edtState.getText().toString().trim().isEmpty()) {
            edtState.setError("Please enter State");
            return false;
        } else if (edtCountry.getText().toString().trim().isEmpty()) {
            edtCountry.setError("Please enter Country");
            return false;
        } else if (edtPincode.getText().toString().trim().isEmpty()) {
            edtPincode.setError("Please enter Pincode");
            return false;
        } else if (edtPhoneno.getText().toString().trim().isEmpty()) {
            edtPhoneno.setError("Please enter Phone no");
            return false;
        } else if (edtPhoneno.getText().toString().trim().length() != 10) {
            edtPhoneno.setError("Please enter valid Phone no");
            return false;
        } else {
            return true;
        }
    }

    private void findViews() {
        btnBack = findViewById(R.id.btnBack);
        btnAddAddress = findViewById(R.id.btnAddAddress);

        llName = findViewById(R.id.llName);
        llAddress = findViewById(R.id.llAddress);
        llCity = findViewById(R.id.llCity);
        llState = findViewById(R.id.llState);
        llCountry = findViewById(R.id.llCountry);
        llPincode = findViewById(R.id.llPincode);
        llPhoneno = findViewById(R.id.llPhoneNo);
        llLandMark = findViewById(R.id.llLandMark);

        edtName = findViewById(R.id.edtName);
        edtAddress = findViewById(R.id.edtAddress);
        edtCity = findViewById(R.id.edtCity);
        edtState = findViewById(R.id.edtState);
        edtCountry = findViewById(R.id.edtCountry);
        edtPincode = findViewById(R.id.edtPincode);
        edtPhoneno = findViewById(R.id.edtPhoneNo);
        edtLandMark = findViewById(R.id.edtLandmark);
    }

    private void AddAddressAPI(String name, String address, String city, String state, String country, String pincode, String phoneno, String landmark) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.AddAddress(access_token, name, address, city, state, country, pincode, phoneno, landmark);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                onBackPressed();
                            }
                        } else {
                            if (jsonObject.has("errors")) {
                                Toast.makeText(AddAddressActivity.this, jsonObject.getJSONObject("errors").getJSONArray("email").toString(), Toast.LENGTH_SHORT).show();
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

}
