package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.CategoriesAdapter;
import com.niranisugar.android.Adapter.CategoryListAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Models.CategoriesModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryListActivity extends Activity {

    RecyclerView rvCategories;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    TextView tvTitle;

    ImageView btnBack;
    KProgressHUD hud;
    ApiInterface apiService;
    CategoryListAdapter categoriesAdapter;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        findViews();

        String strFromWhere = "Categories";
        tvTitle.setText(strFromWhere);

        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutFrozen(true);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        rvCategories.setLayoutManager(gridLayoutManager);



        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(CategoryListActivity.this,NotificationActivity.class);
            startActivity(i);
        });

        GetCategory();
    }

    private void findViews() {
        rvCategories = findViewById(R.id.rvCategoryAll);
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.btnBack);
        imgNotification = findViewById(R.id.imgNotification);
    }

    private void GetCategory() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetCategory();
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                int length = 10;
                                if(jsonArray.length() < 10){
                                    length = jsonArray.length();
                                }
                                arrCategories.clear();
                                for(int i = 0;i<length;i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    CategoriesModel categoriesModel = new CategoriesModel();
                                    categoriesModel.setCategories_id(jsonObject1.getInt("id"));
                                    categoriesModel.setCategories_image(jsonObject1.getString("image"));
                                    categoriesModel.setCategories_photo(jsonObject1.getString("photo"));
                                    categoriesModel.setCategories_title(jsonObject1.getString("name"));
                                    categoriesModel.setIsFeatured(jsonObject1.getInt("is_featured"));
                                    categoriesModel.setCategories_slug(jsonObject1.getString("slug"));
                                    arrCategories.add(categoriesModel);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                categoriesAdapter = new CategoryListAdapter(CategoryListActivity.this, "Fragment", arrCategories, "");
                                rvCategories.setAdapter(categoriesAdapter);

                                setAdapterClickEvent();

                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(CategoryListActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void setAdapterClickEvent() {
        categoriesAdapter.setOnItemClickListener(new CategoryListAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(CategoryListActivity.this, FeaturedActivity.class);
                i.putExtra("fromWhere",arrCategories.get(position).getCategories_title());
                i.putExtra("cat_id",arrCategories.get(position).getCategories_id());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

}