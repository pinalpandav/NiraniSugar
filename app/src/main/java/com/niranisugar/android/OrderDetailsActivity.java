package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.Models.OrderModel;

public class OrderDetailsActivity extends Activity {

    TextView tvOrderID,tvProductName,tvProductCategory,tvPrice;
    TextView tvName,tvEmail,tvPhoneNo,tvAddress,tvOrderStatus,tvPaymentStatus;
    ImageView img;
    TextView btnOrderAgain;
    CardView btnProductDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnOrderAgain = findViewById(R.id.btnOrderAgain);

        btnProductDetails = findViewById(R.id.btnProductDetails);

        tvOrderID = findViewById(R.id.tvOrderID);
        tvProductName = findViewById(R.id.tvItemName);
        tvProductCategory = findViewById(R.id.tvCategory);
        tvPrice = findViewById(R.id.tvPrice);

        tvName = findViewById(R.id.tvCustomerName);
        tvPhoneNo = findViewById(R.id.tvCustomerMobileNo);
        tvEmail = findViewById(R.id.tvCustomerEmail);
        tvAddress = findViewById(R.id.tvAddress);
        tvOrderStatus = findViewById(R.id.tvOrderStatus);
        tvPaymentStatus = findViewById(R.id.tvPaymentStatus);

        img = findViewById(R.id.img);

        OrderModel orderModel = getIntent().getParcelableExtra("Orders");

        tvOrderID.setText("Order ID : " + orderModel.getOrder_id());
        tvProductName.setText(orderModel.getProduct_name());
        tvProductCategory.setText(orderModel.getProduct_name());
        tvPrice.setText("\u20B9 " + orderModel.getProduct_price());

        tvName.setText(orderModel.getCustomer_name());
        tvEmail.setText(orderModel.getCustomer_email());
        tvPhoneNo.setText(orderModel.getCustomer_mobileno());
        tvAddress.setText(orderModel.getShipping_address() + ", " + orderModel.getShipping_city() + ", " + orderModel.getShipping_country());
        tvOrderStatus.setText(orderModel.getOrder_status());
        tvPaymentStatus.setText(orderModel.getPayment_status());

        Glide.with(this).load(orderModel.getProduct_image()).into(img);

        btnOrderAgain.setOnClickListener(view -> {

                AddOrder addOrder = new AddOrder();
                addOrder.setProductIDs(String.valueOf(orderModel.getProduct_id()));
                addOrder.setProductPrice(String.valueOf(orderModel.getProduct_price()));
                addOrder.setProductQtys("1");
                addOrder.setTotalAmount(String.valueOf(orderModel.getProduct_price()));

                Intent i = new Intent(this, AddressActivity.class);
                i.putExtra("array",addOrder);
                startActivity(i);
        });

        btnProductDetails.setOnClickListener(view -> {
            Intent i = new Intent(this, ProductDescriptionActivity.class);
            i.putExtra("id",orderModel.getProduct_id());
            startActivity(i);
        });



    }
}
