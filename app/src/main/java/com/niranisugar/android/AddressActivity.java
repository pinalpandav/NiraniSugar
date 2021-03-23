package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.AddressAdapter;
import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Models.CartModel;

import java.util.ArrayList;

public class AddressActivity extends Activity {

    RecyclerView rvAddress;
    AddressAdapter addressAdapter;
    ArrayList<CartModel> arrAddress = new ArrayList<>();

    TextView btnAddAddress,btnContinueToPayment;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        findViews();

        arrAddress.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrAddress.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));
        arrAddress.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));

        rvAddress.setHasFixedSize(true);
        rvAddress.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvAddress.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        addressAdapter = new AddressAdapter(this, "Fragment", arrAddress, "");
        rvAddress.setAdapter(addressAdapter);

        addressAdapter.setOnItemClickListener(new AddressAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

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

    }

    private void findViews() {
        rvAddress = findViewById(R.id.rvAddress);
        btnAddAddress = findViewById(R.id.btnAddAddress);
        btnContinueToPayment = findViewById(R.id.btnContinueToPayment);

        btnBack = findViewById(R.id.btnBack);
    }
}
