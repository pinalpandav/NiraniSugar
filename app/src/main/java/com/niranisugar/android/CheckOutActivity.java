package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Models.CartModel;

import java.util.ArrayList;

public class CheckOutActivity extends Activity {

    RecyclerView rvCheckOut;
    CartAdapter cartAdapter;
    ArrayList<CartModel> arrCart = new ArrayList<>();
    TextView btnBuy;

    ImageView btnBack;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        findViews();

        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","8","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","3","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","9","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));

        rvCheckOut.setHasFixedSize(true);
        rvCheckOut.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCheckOut.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        cartAdapter = new CartAdapter(this, "Fragment", arrCart, "");
        rvCheckOut.setAdapter(cartAdapter);

        cartAdapter.setOnItemClickListener(new CartAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CheckOutActivity.this,OrderConfirmationActivity.class);
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
            Intent i = new Intent(CheckOutActivity.this,NotificationActivity.class);
            startActivity(i);
        });

    }

    private void findViews() {
        rvCheckOut = findViewById(R.id.rvCheckOut);
        btnBuy = findViewById(R.id.btnBuy);
        btnBack = findViewById(R.id.btnBack);
        imgNotification = findViewById(R.id.imgNotification);
    }
}
