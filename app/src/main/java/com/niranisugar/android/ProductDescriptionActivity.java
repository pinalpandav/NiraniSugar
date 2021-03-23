package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class ProductDescriptionActivity extends Activity {

    ImageSlider imageSlider;
    ArrayList<SlideModel> arrImageSlider = new ArrayList<>();
    TextView tvSize1,tvSize2,tvSize3,tvSize4;
    CardView cvSize1,cvSize2,cvSize3,cvSize4;

    TextView btnSelectSize,btnSelectColor;
    LinearLayout llColor,llSize;
    ImageView ivColor1,ivColor2,ivColor3,ivColor4;
    CardView cvColor1,cvColor2,cvColor3,cvColor4;

    TextView btnAddToCart,btnBuyNow;

    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_description);

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
            Intent i =  new Intent(ProductDescriptionActivity.this,CartActivity.class);
            startActivity(i);
        });

        btnAddToCart.setOnClickListener(view -> {
            Intent i =  new Intent(ProductDescriptionActivity.this,CartActivity.class);
            startActivity(i);
        });

        btnBack.setOnClickListener(view -> onBackPressed());

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

    }
}
