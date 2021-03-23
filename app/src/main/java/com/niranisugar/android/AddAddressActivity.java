package com.niranisugar.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.AddressAdapter;
import com.niranisugar.android.Models.CartModel;

import java.util.ArrayList;

public class AddAddressActivity extends Activity {

    ImageView btnBack;
    TextView btnAddAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

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


    }

    private void findViews() {
        btnBack = findViewById(R.id.btnBack);
        btnAddAddress = findViewById(R.id.btnAddAddress);
    }
}
