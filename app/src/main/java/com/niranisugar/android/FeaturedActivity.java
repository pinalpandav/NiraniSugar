package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Adapter.ProductByCategoryAdapter;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.Models.ProductDetails;
import com.niranisugar.android.Models.ProductGridModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeaturedActivity extends Activity {

    RecyclerView rvFeatured;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    TextView tvTitle;

    ImageView btnBack;
    KProgressHUD hud;
    ApiInterface apiService;
    FeaturedAllAdapter featuredAllAdapter;
    ProductByCategoryAdapter productByCategoryAdapter;
    public ArrayList<ProductGridModel> arrFeatured = new ArrayList<>();
    public ArrayList<ProductDetails> arrProductDetails = new ArrayList<>();
    ImageView imgNotification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_featured);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        findViews();

        Intent i = getIntent();
        String strFromWhere = i.getStringExtra("fromWhere");
        tvTitle.setText(strFromWhere);

        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutFrozen(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvFeatured.setLayoutManager(gridLayoutManager);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        imgNotification.setOnClickListener(view -> {
            Intent ij = new Intent(FeaturedActivity.this,NotificationActivity.class);
            startActivity(ij);
        });

        if(strFromWhere.equals("Featured") || strFromWhere.equals("Best Sell")){
            GetFeatured();
        }else {
            int cat_id = i.getIntExtra("cat_id",0);
            GetProductByCategory(cat_id);
        }
    }

    private void findViews() {
        rvFeatured = findViewById(R.id.rvFeaturedAll);
        tvTitle = findViewById(R.id.tvTitle);
        btnBack = findViewById(R.id.btnBack);
        imgNotification = findViewById(R.id.imgNotification);
    }

    private void GetFeatured() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetFeatures();
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

                                for(int i = 0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ProductGridModel productGridModel = new ProductGridModel();
                                    productGridModel.setId(jsonObject1.getInt("id"));
                                    productGridModel.setProduct_attributes(jsonObject1.getString("attributes"));
                                    productGridModel.setProduct_discount_date(jsonObject1.getString("discount_date"));
                                    productGridModel.setProduct_name(jsonObject1.getString("name"));
                                    productGridModel.setProduct_prev_price(jsonObject1.getString("previous_price"));
                                    productGridModel.setProduct_size(jsonObject1.getString("size"));
                                    productGridModel.setProduct_size_price(jsonObject1.getString("size_price"));
                                    productGridModel.setProduct_slug(jsonObject1.getString("slug"));
                                    productGridModel.setProduct_thumbnail(jsonObject1.getString("thumbnail"));
                                    productGridModel.setProduct_price(jsonObject1.getDouble("price"));
                                    arrFeatured.add(productGridModel);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                featuredAllAdapter = new FeaturedAllAdapter(FeaturedActivity.this, "Fragment", arrFeatured, "");
                                rvFeatured.setAdapter(featuredAllAdapter);


                                setFeaturedAdapterClickEvent();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(FeaturedActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
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

    private void GetProductByCategory(int cat_id) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetProductByCategory(cat_id);
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

                                for(int i = 0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ProductDetails productDetails = new ProductDetails();
                                    productDetails.setCategory_id(jsonObject1.getString("category_id"));
                                    productDetails.setColor(jsonObject1.getString("color"));
                                    productDetails.setDetails(jsonObject1.getString("details"));
                                    productDetails.setGst(jsonObject1.getString("gst"));
                                    productDetails.setId(jsonObject1.getInt("id"));
                                    productDetails.setName(jsonObject1.getString("name"));
                                    productDetails.setOrignal_price(jsonObject1.getString("original_price"));
                                    productDetails.setPhoto(jsonObject1.getString("photo"));
                                    productDetails.setPolicy(jsonObject1.getString("policy"));
                                    productDetails.setPrice(jsonObject1.getDouble("price"));
                                    productDetails.setProduct_type(jsonObject1.getString("product_type"));
                                    productDetails.setPrv_price(jsonObject1.getString("previous_price"));
                                    productDetails.setSize(jsonObject1.getString("size"));
                                    productDetails.setSize_price(jsonObject1.getString("size_price"));
                                    productDetails.setSize_qty(jsonObject1.getString("size_qty"));
                                    productDetails.setSku(jsonObject1.getString("sku"));
                                    productDetails.setSlug(jsonObject1.getString("slug"));
                                    productDetails.setStock(jsonObject1.getString("stock"));
                                    productDetails.setSubcat_id(jsonObject1.getString("subcategory_id"));
                                    productDetails.setTcs(jsonObject1.getString("tcs"));
                                    productDetails.setThumbnail(jsonObject1.getString("thumbnail"));
                                    productDetails.setViews(jsonObject1.getString("views"));
                                    arrProductDetails.add(productDetails);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                productByCategoryAdapter = new ProductByCategoryAdapter(FeaturedActivity.this, "Fragment", arrProductDetails, "");
                                rvFeatured.setAdapter(productByCategoryAdapter);


                                setProductByCategoryAdapterClickEvent();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(FeaturedActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
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

    private void setFeaturedAdapterClickEvent() {
        featuredAllAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(FeaturedActivity.this, ProductDescriptionActivity.class);
                i.putExtra("id",arrFeatured.get(position).getId());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }

    private void setProductByCategoryAdapterClickEvent(){
        productByCategoryAdapter.setOnItemClickListener(new ProductByCategoryAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(FeaturedActivity.this, ProductDescriptionActivity.class);
                i.putExtra("id",arrProductDetails.get(position).getId());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }
}