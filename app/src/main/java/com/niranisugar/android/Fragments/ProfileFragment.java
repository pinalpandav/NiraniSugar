package com.niranisugar.android.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.MainActivity;
import com.niranisugar.android.Models.ProductGridModel;
import com.niranisugar.android.Models.Profile;
import com.niranisugar.android.R;
import com.niranisugar.android.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    KProgressHUD hud;
    ApiInterface apiService;
    MainActivity thisAct;
    EditText edtName,edtAddress,edtCity,edtEmail,edtPhoneno;
    CardView btnSave;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    RadioGroup rgGender;
    String strGender;
    RadioButton rbFemale,rbMale;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        thisAct = (MainActivity) getActivity();
        hud = KProgressHUD.create(thisAct)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        findViews(v);

        ProfileAPI(thisAct.access_token);

        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int Gender = radioGroup.getCheckedRadioButtonId();
                if(Gender == R.id.rbFemale){
                    strGender = "female";
                }else{
                    strGender = "male";
                }
            }
        });

        btnSave.setOnClickListener(view -> {
            if(Validate()){
                String name = edtName.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String address = edtAddress.getText().toString().trim();
                String city = edtCity.getText().toString().trim();
                String phoneNo = edtPhoneno.getText().toString().trim();
                String gender = strGender;

                UpdateProfileAPI(thisAct.access_token,name,email,address,city,phoneNo,gender);
            }
        });

        return v;
    }

    private boolean Validate() {
        if(edtName.getText().toString().trim().isEmpty()){
            edtName.setError("Please enter Name");
            return false;
        }else if(edtAddress.getText().toString().trim().isEmpty()){
            edtName.setError("Please enter Address");
            return false;
        }else if(edtCity.getText().toString().trim().isEmpty()){
            edtCity.setError("Please enter City");
            return false;
        }else if(edtEmail.getText().toString().trim().isEmpty()){
            edtEmail.setError("Please enter Email");
            return false;
        }else if(!edtEmail.getText().toString().trim().matches(emailPattern)){
            edtEmail.setError("Please enter valid Email");
            return false;
        }else if(edtPhoneno.getText().toString().trim().isEmpty()){
            edtPhoneno.setError("Please enter Phone No.");
            return false;
        }else if(edtPhoneno.getText().toString().trim().length() != 10){
            edtPhoneno.setError("Please enter valid Phone No.");
            return false;
        }else{
            return true;
        }
    }

    private void findViews(View v) {
        edtName = v.findViewById(R.id.edtName);
        edtAddress = v.findViewById(R.id.edtAddress);
        edtCity = v.findViewById(R.id.edtCity);
        edtEmail = v.findViewById(R.id.edtEmail);
        edtPhoneno = v.findViewById(R.id.edtPhoneNo);
        btnSave = v.findViewById(R.id.btnSave);
        rgGender = v.findViewById(R.id.rgGender);
        rbMale = v.findViewById(R.id.rbMale);
        rbFemale = v.findViewById(R.id.rbFemale);
    }

    private void ProfileAPI(String token) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.Profile(token);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                                Profile profile = new Profile();
                                profile.setName(jsonObject1.getString("name"));
                                profile.setEmail(jsonObject1.getString("email"));
                                profile.setCity(jsonObject1.getString("city"));
                                profile.setAddress(jsonObject1.getString("address"));
                                profile.setPhoneno(jsonObject1.getString("phone"));
                                profile.setGender(jsonObject1.getString("gender"));
                                setData(profile);
                            }
                        }
                        //Toast.makeText(thisAct, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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

    private void setData(Profile profile) {
        edtName.setText(profile.getName());
        edtAddress.setText(profile.getAddress());
        edtCity.setText(profile.getCity());
        edtPhoneno.setText(profile.getPhoneno());
        edtEmail.setText(profile.getEmail());
        if(!profile.getGender().equals("null")){
            if(profile.getGender().equalsIgnoreCase("male")){
                rbMale.setChecked(true);
            }else{
                rbFemale.setChecked(true);
            }
        }
    }


    private void UpdateProfileAPI(String access_token, String name, String email, String address, String city, String phoneNo, String gender) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.UpdateProfile(access_token,name,email,city,address,phoneNo,gender);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        /*if (jsonObject.getString("status").equals("1")) {
                            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                            Profile profile = new Profile();
                            profile.setName(jsonObject1.getString("name"));
                            profile.setEmail(jsonObject1.getString("email"));
                            profile.setCity(jsonObject1.getString("city"));
                            profile.setAddress(jsonObject1.getString("address"));
                            profile.setPhoneno(jsonObject1.getString("phone"));
                            setData(profile);
                        }*/
                        Toast.makeText(thisAct, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
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
