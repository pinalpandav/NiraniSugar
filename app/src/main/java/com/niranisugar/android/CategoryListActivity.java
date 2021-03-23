package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CategoryListAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Models.CategoriesModel;

import java.util.ArrayList;

public class CategoryListActivity extends Activity {

    RecyclerView rvCategories;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    TextView tvTitle;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

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

        Intent i = getIntent();
        String strFromWhere = "Categories";
        tvTitle.setText(strFromWhere);

        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutFrozen(true);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rvCategories.setLayoutManager(gridLayoutManager);

        // TODO: 05-01-2021 Set data in adapter
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this, "Fragment", arrCategories, "");
        rvCategories.setAdapter(categoryListAdapter);

        categoryListAdapter.setOnItemClickListener(new CategoryListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(CategoryListActivity.this, FeaturedActivity.class);
                i.putExtra("fromWhere","Men");
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

    }

    private void findViews() {
        rvCategories = findViewById(R.id.rvCategoryAll);
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.btnBack);
    }
}