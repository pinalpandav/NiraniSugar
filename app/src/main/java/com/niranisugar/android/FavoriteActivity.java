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

import java.util.ArrayList;

public class FavoriteActivity extends Activity {

    RecyclerView rvCategories, rvFeatured, rvBestSell;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    TextView tvTitle;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        CategoriesModel obj1 = new CategoriesModel();
        obj1.setCategories_title("Sugar");
        obj1.setCategories_image("Sugar");
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

//        rvFeatured.setHasFixedSize(true);
//        rvFeatured.setLayoutFrozen(true);
//        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        rvFeatured.setLayoutManager(llmF);
//
//        // TODO: 05-01-2021 Set data in adapter
//        FeaturedAdapter featuredAdapter = new FeaturedAdapter(this, "Fragment", arrCategories, "");
//        rvFeatured.setAdapter(featuredAdapter);
//
//        rvBestSell.setHasFixedSize(true);
//        rvBestSell.setLayoutFrozen(true);
//        LinearLayoutManager llmBS = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        rvBestSell.setLayoutManager(llmBS);
//
//        // TODO: 05-01-2021 Set data in adapter
//        FeaturedAdapter bestSellAdapter = new FeaturedAdapter(this, "Fragment", arrCategories, "");
//        rvBestSell.setAdapter(bestSellAdapter);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void findViews() {

//        rvCategories = findViewById(R.id.rvCategories);
        rvFeatured = findViewById(R.id.rvFeaturedAll);
//        rvBestSell = findViewById(R.id.rvBestSell);

        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.btnBack);
    }
}