package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.Models.ProductGridModel;

import java.util.ArrayList;

public class FavoriteActivity extends Activity {

    RecyclerView rvCategories, rvFeatured, rvBestSell;
    public ArrayList<ProductGridModel> arrCategories = new ArrayList<>();
    TextView tvTitle;

    ImageView btnBack;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        ProductGridModel obj1 = new ProductGridModel();
        obj1.setProduct_name("Sugar");
        obj1.setProduct_price(50.00);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);

        findViews();

        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutFrozen(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvFeatured.setLayoutManager(gridLayoutManager);

        // TODO: 05-01-2021 Set data in adapter
        FeaturedAllAdapter categoriesAdapter = new FeaturedAllAdapter(this, "Fragment", arrCategories, "");
        rvFeatured.setAdapter(categoriesAdapter);

        categoriesAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(FavoriteActivity.this, ProductDescriptionActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(FavoriteActivity.this,NotificationActivity.class);
            startActivity(i);
        });

    }

    private void findViews() {
        rvFeatured = findViewById(R.id.rvFeaturedAll);
        imgNotification = findViewById(R.id.imgNotification);
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.btnBack);
    }
}