package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.Models.CartModel;

import java.util.ArrayList;

public class CartActivity extends Activity {

    RecyclerView rvCart;
    CartAdapter cartAdapter;
    ArrayList<CartModel> arrCart = new ArrayList<>();
    TextView btnContinue;

    ImageView btnBack;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

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

        rvCart.setHasFixedSize(true);
        rvCart.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCart.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        cartAdapter = new CartAdapter(this, "Fragment", arrCart, "");
        rvCart.setAdapter(cartAdapter);

        cartAdapter.setOnItemClickListener(new CartAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CartActivity.this,AddressActivity.class);
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
            Intent i = new Intent(CartActivity.this,NotificationActivity.class);
            startActivity(i);
        });

    }

    private void findViews() {
        rvCart = findViewById(R.id.rvCart);
        btnContinue = findViewById(R.id.btnContinue);
        btnBack = findViewById(R.id.btnBack);
        imgNotification = findViewById(R.id.imgNotification);
    }
}
