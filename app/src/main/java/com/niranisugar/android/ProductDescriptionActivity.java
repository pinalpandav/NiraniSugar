package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Models.ProductDetails;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDescriptionActivity extends Activity {

    ImageSlider imageSlider;
    ArrayList<SlideModel> arrImageSlider = new ArrayList<>();
    TextView tvSize1, tvSize2, tvSize3, tvSize4;
    CardView cvSize1, cvSize2, cvSize3, cvSize4;

    TextView btnSelectSize, btnSelectColor;
    LinearLayout llColor, llSize;
    ImageView ivColor1, ivColor2, ivColor3, ivColor4;
    CardView cvColor1, cvColor2, cvColor3, cvColor4;

    TextView btnAddToCart, btnBuyNow;

    ImageView btnBack;

    TextView tvProductName, tvPrice, tvOrignalPrice, tvReviewCount, tvReview, tvRating, tvDescription;
    KProgressHUD hud;
    ApiInterface apiService;
    ImageView imgNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        findViews();

        arrImageSlider.add(new SlideModel("https://bit.ly/37Rn50u", "", ScaleTypes.CENTER_CROP));
        arrImageSlider.add(new SlideModel("https://bit.ly/37Rn50u", "", ScaleTypes.CENTER_CROP));
        arrImageSlider.add(new SlideModel("https://bit.ly/37Rn50u", "", ScaleTypes.CENTER_CROP));
        arrImageSlider.add(new SlideModel("https://bit.ly/37Rn50u", "", ScaleTypes.CENTER_CROP));
        arrImageSlider.add(new SlideModel("https://bit.ly/37Rn50u", "", ScaleTypes.CENTER_CROP));
        imageSlider.setImageList(arrImageSlider);

        cvSize1.setOnClickListener(view -> {
            cvSize1.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvSize1.setTextColor(getResources().getColor(R.color.white));
            cvSize2.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize2.setTextColor(getResources().getColor(R.color.black));
            cvSize3.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize3.setTextColor(getResources().getColor(R.color.black));
            cvSize4.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize4.setTextColor(getResources().getColor(R.color.black));
        });

        cvSize2.setOnClickListener(view -> {
            cvSize1.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize1.setTextColor(getResources().getColor(R.color.black));
            cvSize2.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvSize2.setTextColor(getResources().getColor(R.color.white));
            cvSize3.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize3.setTextColor(getResources().getColor(R.color.black));
            cvSize4.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize4.setTextColor(getResources().getColor(R.color.black));
        });

        cvSize3.setOnClickListener(view -> {
            cvSize1.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize1.setTextColor(getResources().getColor(R.color.black));
            cvSize2.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize2.setTextColor(getResources().getColor(R.color.black));
            cvSize3.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvSize3.setTextColor(getResources().getColor(R.color.white));
            cvSize4.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize4.setTextColor(getResources().getColor(R.color.black));
        });

        cvSize4.setOnClickListener(view -> {
            cvSize1.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize1.setTextColor(getResources().getColor(R.color.black));
            cvSize2.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize2.setTextColor(getResources().getColor(R.color.black));
            cvSize3.setCardBackgroundColor(getResources().getColor(R.color.white));
            tvSize3.setTextColor(getResources().getColor(R.color.black));
            cvSize4.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));
            tvSize4.setTextColor(getResources().getColor(R.color.white));
        });

        btnSelectColor.setOnClickListener(view -> {
            llColor.setVisibility(View.VISIBLE);
            llSize.setVisibility(View.GONE);
            btnSelectColor.setTextColor(getResources().getColor(R.color.colorPrimary));
            btnSelectSize.setTextColor(getResources().getColor(R.color.black));
        });

        btnSelectSize.setOnClickListener(view -> {
            llColor.setVisibility(View.GONE);
            llSize.setVisibility(View.VISIBLE);
            btnSelectColor.setTextColor(getResources().getColor(R.color.black));
            btnSelectSize.setTextColor(getResources().getColor(R.color.colorPrimary));
        });

        cvColor1.setOnClickListener(view -> {
            ivColor1.setVisibility(View.VISIBLE);
            ivColor2.setVisibility(View.INVISIBLE);
            ivColor3.setVisibility(View.INVISIBLE);
            ivColor4.setVisibility(View.INVISIBLE);
        });

        cvColor2.setOnClickListener(view -> {
            ivColor1.setVisibility(View.INVISIBLE);
            ivColor2.setVisibility(View.VISIBLE);
            ivColor3.setVisibility(View.INVISIBLE);
            ivColor4.setVisibility(View.INVISIBLE);
        });

        cvColor3.setOnClickListener(view -> {
            ivColor1.setVisibility(View.INVISIBLE);
            ivColor2.setVisibility(View.INVISIBLE);
            ivColor3.setVisibility(View.VISIBLE);
            ivColor4.setVisibility(View.INVISIBLE);
        });

        cvColor4.setOnClickListener(view -> {
            ivColor1.setVisibility(View.INVISIBLE);
            ivColor2.setVisibility(View.INVISIBLE);
            ivColor3.setVisibility(View.INVISIBLE);
            ivColor4.setVisibility(View.VISIBLE);
        });

        btnBuyNow.setOnClickListener(view -> {
            Intent i = new Intent(ProductDescriptionActivity.this, CartActivity.class);
            startActivity(i);
        });

        btnAddToCart.setOnClickListener(view -> {
            Intent i = new Intent(ProductDescriptionActivity.this, CartActivity.class);
            startActivity(i);
        });

        btnBack.setOnClickListener(view -> onBackPressed());

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(ProductDescriptionActivity.this,NotificationActivity.class);
            startActivity(i);
        });

        GetProductDetailByID(getIntent().getIntExtra("id",0));

    }

    private void findViews() {
        imageSlider = findViewById(R.id.image_slider);
        tvSize1 = findViewById(R.id.tvSize1);
        tvSize2 = findViewById(R.id.tvSize2);
        tvSize3 = findViewById(R.id.tvSize3);
        tvSize4 = findViewById(R.id.tvSize4);

        cvSize1 = findViewById(R.id.cvSize1);
        cvSize2 = findViewById(R.id.cvSize2);
        cvSize3 = findViewById(R.id.cvSize3);
        cvSize4 = findViewById(R.id.cvSize4);

        btnSelectColor = findViewById(R.id.btnSelectColor);
        btnSelectSize = findViewById(R.id.btnSelectSize);

        llColor = findViewById(R.id.llColor);
        llSize = findViewById(R.id.llSize);

        ivColor1 = findViewById(R.id.ivColor1);
        ivColor2 = findViewById(R.id.ivColor2);
        ivColor3 = findViewById(R.id.ivColor3);
        ivColor4 = findViewById(R.id.ivColor4);

        cvColor1 = findViewById(R.id.cvColor1);
        cvColor2 = findViewById(R.id.cvColor2);
        cvColor3 = findViewById(R.id.cvColor3);
        cvColor4 = findViewById(R.id.cvColor4);

        btnAddToCart = findViewById(R.id.btnAddToCart);
        btnBuyNow = findViewById(R.id.btnBuyNow);

        btnBack = findViewById(R.id.btnBack);

        tvProductName = findViewById(R.id.tvProductName);
        tvPrice = findViewById(R.id.tvPrice);
        tvOrignalPrice = findViewById(R.id.tvOrignalPrice);
        tvReviewCount = findViewById(R.id.tvReviewCount);
        tvReview = findViewById(R.id.tvReview);
        tvRating = findViewById(R.id.tvRate);
        tvDescription = findViewById(R.id.tvDescription);
        imgNotification = findViewById(R.id.imgNotification);

    }

    private void GetProductDetailByID(int id) {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetProductDetails(id);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
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
                                setData(productDetails);
                            }
                        } else {
                            if (jsonObject.has("errors")) {
                                Toast.makeText(ProductDescriptionActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                } else {
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void setData(ProductDetails productDetails) {
        tvProductName.setText(productDetails.getName());
        tvPrice.setText("\u20B9 " + String.format("%.2f",productDetails.getPrice()));
        tvDescription.setText(Html.fromHtml(productDetails.getDetails()));
    }
}
