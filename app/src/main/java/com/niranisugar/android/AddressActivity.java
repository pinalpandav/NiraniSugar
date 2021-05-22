package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.AddressAdapter;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.Models.AddressModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressActivity extends Activity {

    RecyclerView rvAddress;
    AddressAdapter addressAdapter;
    ArrayList<AddressModel> arrAddress = new ArrayList<>();

    TextView btnAddAddress, btnContinueToPayment;

    ImageView btnBack;
    ImageView imgNotification;
    KProgressHUD hud;
    ApiInterface apiService;
    String access_token;
    SharedPreferences prefUserData;

    AddOrder addOrder;
    TextView tvNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        access_token = prefUserData.getString("token", "");

        findViews();

        rvAddress.setHasFixedSize(true);
        rvAddress.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvAddress.setLayoutManager(llmF);

        addOrder = (AddOrder) getIntent().getParcelableExtra("array");


        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddressActivity.this, AddAddressActivity.class);
                startActivity(i);
            }
        });

        btnContinueToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addOrder.setAddressID(addressAdapter.data_list.get(addressAdapter.lastCheckedPosition).getId());
                addOrder.setAddress(addressAdapter.data_list.get(addressAdapter.lastCheckedPosition).getAddress());
                Intent i = new Intent(AddressActivity.this, PaymentActivity.class);
                i.putExtra("array", addOrder);
                startActivity(i);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(AddressActivity.this, NotificationActivity.class);
            startActivity(i);
        });

        if(access_token.equals("")){
            Toast.makeText(this, "Please login to the application", Toast.LENGTH_SHORT).show();
        }else{
            GetAddress();
        }

    }

    private void findViews() {
        rvAddress = findViewById(R.id.rvAddress);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnContinueToPayment = findViewById(R.id.btnContinueToPayment);
        imgNotification = findViewById(R.id.imgNotification);
        btnBack = findViewById(R.id.btnBack);
        tvNoDataFound = findViewById(R.id.tvNoDataFound);
    }

    private void GetAddress() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetAddress(access_token);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                arrAddress.clear();
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    AddressModel addressModel = new AddressModel();
                                    addressModel.setId(jsonObject1.getInt("id"));
                                    addressModel.setName(jsonObject1.getString("name"));
                                    addressModel.setAddress(jsonObject1.getString("address"));
                                    addressModel.setCity(jsonObject1.getString("city"));
                                    addressModel.setCountry(jsonObject1.getString("country"));
                                    addressModel.setLandmark(jsonObject1.getString("landmark"));
                                    addressModel.setPhoneno(jsonObject1.getString("phone_no"));
                                    addressModel.setState(jsonObject1.getString("state"));
                                    addressModel.setPincode(jsonObject1.getString("postal_code"));
                                    arrAddress.add(addressModel);
                                }

                                if(arrAddress.size() == 0){
                                    tvNoDataFound.setVisibility(View.VISIBLE);
                                    btnContinueToPayment.setBackgroundColor(getResources().getColor(R.color.lightgray));
                                    btnContinueToPayment.setTextColor(getResources().getColor(R.color.black));
                                    btnContinueToPayment.setEnabled(false);
                                }else{
                                    tvNoDataFound.setVisibility(View.GONE);
                                    btnContinueToPayment.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                                    btnContinueToPayment.setTextColor(getResources().getColor(R.color.white));
                                    btnContinueToPayment.setEnabled(true);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                addressAdapter = new AddressAdapter(AddressActivity.this, "Fragment", arrAddress, "");
                                rvAddress.setAdapter(addressAdapter);

                                addressAdapter.setOnItemClickListener(new AddressAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int position, View v) {

                                    }

                                    @Override
                                    public void onItemLongClick(int position, View v) {

                                    }
                                });

                            }
                        } else {
                            if (jsonObject.has("errors")) {
                                Toast.makeText(AddressActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                } else {
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(access_token.equals("")){
            Toast.makeText(this, "Please login to the application", Toast.LENGTH_SHORT).show();
        }else{
            GetAddress();
        }
    }
}
