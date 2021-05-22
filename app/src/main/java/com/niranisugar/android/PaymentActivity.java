package com.niranisugar.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.merhold.extensiblepageindicator.ExtensiblePageIndicator;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.AddressAdapter;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.Models.AddressModel;
import com.niranisugar.android.PaymentCard.CardFragmentPagerAdapter;
import com.niranisugar.android.PaymentCard.CardItem;
import com.niranisugar.android.PaymentCard.CardPagerAdapter;
import com.niranisugar.android.PaymentCard.ShadowTransformer;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private CardPagerAdapter mCardAdapter;
    private ShadowTransformer mCardShadowTransformer;
    private ViewPager mViewPager;

    TextView btnCheckOut;
    ImageView btnBack;
    ImageView imgNotification;

    TextView tvSubTotal,tvTotal;
    AddOrder addOrder;

    KProgressHUD hud;
    ApiInterface apiService;
    String access_token;
    String user_id;
    SharedPreferences prefUserData;
    JsonObject jobjUser;

    RadioButton onlinePayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Checkout.preload(getApplicationContext());

        hud = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        access_token = prefUserData.getString("token", "");
        String user_data = prefUserData.getString("user_data","");
        jobjUser = new Gson().fromJson(user_data, JsonObject.class);
        user_id = jobjUser.getAsJsonObject("data").get("id").getAsString();
        findViews();

        addOrder = (AddOrder) getIntent().getParcelableExtra("array");

        tvTotal.setText(addOrder.getTotalAmount());
        tvSubTotal.setText(addOrder.getTotalAmount());

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

            if(onlinePayment.isChecked()){
                startPayment();
            }else {
                AddOrderAPI();
            }
//            Intent i = new Intent(PaymentActivity.this,OrderConfirmationActivity.class);
//            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(i);
//            finish();

        });

        btnBack.setOnClickListener(view -> {
            finish();
        });

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(PaymentActivity.this,NotificationActivity.class);
            startActivity(i);
        });

    }

    private void AddOrderAPI() {

        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.AddOrder(access_token,addOrder.getProductIDs(),addOrder.getProductQtys(),addOrder.getProductPrice(),user_id,addOrder.getAddressID(),"");
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                Intent i = new Intent(PaymentActivity.this,OrderConfirmationActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            }
                        } else {
                            if (jsonObject.has("errors")) {
                                Toast.makeText(PaymentActivity.this, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
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

    private void findViews() {
        mViewPager = (ViewPager) findViewById(R.id.vpPaymentCard);
        btnCheckOut = findViewById(R.id.btnCheckOut);
        btnBack = findViewById(R.id.btnBack);
        imgNotification = findViewById(R.id.imgNotification);
        tvTotal = findViewById(R.id.tvTotal);
        tvSubTotal = findViewById(R.id.tvSubTotal);

        onlinePayment = findViewById(R.id.onlinePayment);
    }


    public void startPayment() {
        /*
          You need to pass current activity in order to let Razorpay create CheckoutActivity
         */
        final Activity activity = this;

        final Checkout co = new Checkout();

        try {
            JSONObject options = new JSONObject();
            options.put("name", "Nirani Sugar");
            options.put("description", "Buy Sugar");
            options.put("send_sms_hash",true);
            options.put("allow_rotation", true);
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", Math.round(Float.parseFloat(addOrder.getProductPrice())));

            JSONObject preFill = new JSONObject();
            preFill.put("email", jobjUser.getAsJsonObject("data").get("email").getAsString());
            preFill.put("contact", jobjUser.getAsJsonObject("data").get("phone").getAsString());

            options.put("prefill", preFill);

            co.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        try {
            Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentSuccess", e);
        }
    }

    /**
     * The name of the function has to be
     * onPaymentError
     * Wrap your code in try catch, as shown, to ensure that this method runs correctly
     */
    @Override
    public void onPaymentError(int code, String response) {
        try {
            Toast.makeText(this, "Payment failed: " + code + " " + response, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("TAG", "Exception in onPaymentError", e);
        }
    }

}