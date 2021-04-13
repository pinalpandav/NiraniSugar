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
import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Adapter.NotificationAdapter;
import com.niranisugar.android.Adapter.ProductByCategoryAdapter;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.ProductDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationActivity extends Activity {

    RecyclerView rvNotification;
    ImageView btnBack;
    TextView tvNoDataFound;
    NotificationAdapter notificationAdapter;
    KProgressHUD hud;
    ApiInterface apiService;
    SharedPreferences prefUserData;
    String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        access_token = prefUserData.getString("token", "");

        findViews();

        rvNotification.setHasFixedSize(true);
        rvNotification.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvNotification.setLayoutManager(llmF);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        GetNotification();

    }

    private void GetNotification() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetNotification(access_token);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                tvNoDataFound.setVisibility(View.GONE);
                                // TODO: 05-01-2021 Set data in adapter
                                notificationAdapter = new NotificationAdapter(NotificationActivity.this, "Fragment", null, "");
                                rvNotification.setAdapter(notificationAdapter);

                                notificationAdapter.setOnItemClickListener(new NotificationAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int position, View v) {

                                    }

                                    @Override
                                    public void onItemLongClick(int position, View v) {

                                    }
                                });
                            }else{
                                tvNoDataFound.setVisibility(View.VISIBLE);
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(NotificationActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void findViews() {
        rvNotification = findViewById(R.id.rvNotification);
        btnBack = findViewById(R.id.btnBack);
        tvNoDataFound = findViewById(R.id.tvNoDataFound);
    }
}
