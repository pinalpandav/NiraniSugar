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
import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.ProductGridModel;

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
    ArrayList<CartModel> arrAddress = new ArrayList<>();

    TextView btnAddAddress,btnContinueToPayment;

    ImageView btnBack;
    ImageView imgNotification;
    KProgressHUD hud;
    ApiInterface apiService;
    String access_token;
    SharedPreferences prefUserData;

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

        arrAddress.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrAddress.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));
        arrAddress.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));

        rvAddress.setHasFixedSize(true);
        rvAddress.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvAddress.setLayoutManager(llmF);


         btnAddAddress.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                    Intent i = new Intent(AddressActivity.this,AddAddressActivity.class);
                    startActivity(i);
             }
         });

         btnContinueToPayment.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(AddressActivity.this,PaymentActivity.class);
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
             Intent i = new Intent(AddressActivity.this,NotificationActivity.class);
             startActivity(i);
         });

         GetAddress();

    }

    private void findViews() {
        rvAddress = findViewById(R.id.rvAddress);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnContinueToPayment = findViewById(R.id.btnContinueToPayment);
        imgNotification = findViewById(R.id.imgNotification);
        btnBack = findViewById(R.id.btnBack);
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
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");

                                for(int i = 0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ProductGridModel productGridModel = new ProductGridModel();
//                                    arrFeatured.add(productGridModel);
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
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(AddressActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
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

}
