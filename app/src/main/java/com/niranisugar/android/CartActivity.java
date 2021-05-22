package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.SqliteDatabse.Cart;
import com.niranisugar.android.SqliteDatabse.DatabaseHelper;

import java.util.ArrayList;

public class CartActivity extends Activity {

    RecyclerView rvCart;
    CartAdapter cartAdapter;
    ArrayList<Cart> arrCart = new ArrayList<>();
    TextView btnContinue;

    ImageView btnBack;
    ImageView imgNotification;

    private DatabaseHelper dbCart;
    TextView tvNoDataFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        dbCart = new DatabaseHelper(this);

        findViews();

        arrCart = dbCart.GetAllCartProduct();

        tvNoDataFound = findViewById(R.id.tvNoDataFound);

        rvCart.setHasFixedSize(true);
        rvCart.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rvCart.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        cartAdapter = new CartAdapter(this,null, "Fragment", arrCart, "");
        rvCart.setAdapter(cartAdapter);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cartAdapter.data_list.size() != 0) {
                    String Product_IDS = "";
                    String Product_Prices = "";
                    String Product_Qtys = "";
                    float Product_Total_Price = 0.0f;
                    AddOrder addOrder = new AddOrder();
                    for (int i = 0; i < cartAdapter.data_list.size(); i++) {
                        if (cartAdapter.data_list.size() == 1) {
                            Product_IDS = String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                            Product_Prices = String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                            Product_Qtys = String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                            Product_Total_Price = Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                        } else {
                            if(i == 0){
                                Product_IDS = String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                                Product_Prices =String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                                Product_Qtys = String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                                Product_Total_Price = Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                            }else{
                                Product_IDS = Product_IDS + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                                Product_Prices =Product_Prices + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                                Product_Qtys = Product_Qtys + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                                Product_Total_Price =Product_Total_Price +  Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                            }
                        }
                    }
                    addOrder.setProductIDs(Product_IDS);
                    addOrder.setProductPrice(Product_Prices);
                    addOrder.setProductQtys(Product_Qtys);
                    addOrder.setTotalAmount(String.valueOf(Product_Total_Price));

                    Intent i = new Intent(CartActivity.this, AddressActivity.class);
                    i.putExtra("array", addOrder);
                    startActivity(i);
                } else {
                    Toast.makeText(CartActivity.this, "Cart is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(CartActivity.this, NotificationActivity.class);
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
