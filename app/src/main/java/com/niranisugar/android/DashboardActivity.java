package com.niranisugar.android;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CategoriesAdapter;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.Models.CategoriesModel;

import java.util.ArrayList;

public class DashboardActivity extends Activity {

   RecyclerView rvCategories,rvFeatured,rvBestSell;
   public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

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

        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutFrozen(true);
        LinearLayoutManager llm = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvCategories.setLayoutManager(llm);

        // TODO: 05-01-2021 Set data in adapter
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(this, "Fragment", arrCategories, "");
        rvCategories.setAdapter(categoriesAdapter);

        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvFeatured.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(this, "Fragment", arrCategories, "");
        rvFeatured.setAdapter(featuredAdapter);

        rvBestSell.setHasFixedSize(true);
        rvBestSell.setLayoutFrozen(true);
        LinearLayoutManager llmBS = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        rvBestSell.setLayoutManager(llmBS);

        // TODO: 05-01-2021 Set data in adapter
        FeaturedAdapter bestSellAdapter = new FeaturedAdapter(this, "Fragment", arrCategories, "");
        rvBestSell.setAdapter(bestSellAdapter);


    }

    private void findViews() {

        rvCategories = findViewById(R.id.rvCategories);
        rvFeatured = findViewById(R.id.rvFeatured);
        rvBestSell = findViewById(R.id.rvBestSell);
    }
}