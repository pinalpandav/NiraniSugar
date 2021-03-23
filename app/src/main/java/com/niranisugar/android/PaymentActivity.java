package com.niranisugar.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;
import com.niranisugar.android.PaymentCard.CardFragmentPagerAdapter;
import com.niranisugar.android.PaymentCard.CardItem;
import com.niranisugar.android.PaymentCard.CardPagerAdapter;
import com.niranisugar.android.PaymentCard.ShadowTransformer;

public class PaymentActivity extends AppCompatActivity {

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private ViewPager mViewPager;

    TextView btnCheckOut;
    ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        findViews();

        mCardAdapter = new CardPagerAdapter();
        mCardAdapter.addCardItem(new CardItem(R.string.app_name, R.string.app_name));
        mCardAdapter.addCardItem(new CardItem(R.string.app_name, R.string.app_name));
        mCardAdapter.addCardItem(new CardItem(R.string.app_name, R.string.app_name));
        mCardAdapter.addCardItem(new CardItem(R.string.app_name, R.string.app_name));

        mCardShadowTransformer = new ShadowTransformer(mViewPager, mCardAdapter);

        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setPageTransformer(false, mCardShadowTransformer);
        mViewPager.setOffscreenPageLimit(3);

        ExtensiblePageIndicator extensiblePageIndicator = (ExtensiblePageIndicator) findViewById(R.id.vpIndicator);
        extensiblePageIndicator.initViewPager(mViewPager);

        btnCheckOut.setOnClickListener(view -> {
            Intent i = new Intent(PaymentActivity.this,CheckOutActivity.class);
            startActivity(i);
        });

        btnBack.setOnClickListener(view -> {
            finish();
        });

    }

    private void findViews() {
        mViewPager = (ViewPager) findViewById(R.id.vpPaymentCard);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnBack = findViewById(R.id.btnBack);
    }


}