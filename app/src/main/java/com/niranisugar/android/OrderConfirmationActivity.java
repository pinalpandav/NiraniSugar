package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.IntentCompat;

import com.niranisugar.android.Fragments.DashBoardFragment;

public class OrderConfirmationActivity extends Activity {

    TextView btnBackToHome;
    SharedPreferences prefSelectedOption;
    SharedPreferences.Editor editorSelectedOption;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_done);

        findViews();

        prefSelectedOption = getSharedPreferences("SELECTEDPAGE",MODE_PRIVATE);
        editorSelectedOption = prefSelectedOption.edit();
        editorSelectedOption.putInt("NO",0);
        editorSelectedOption.apply();

        btnBackToHome.setOnClickListener(view -> {
            Intent i = new Intent(OrderConfirmationActivity.this, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();

        });


    }

    private void findViews() {

        btnBackToHome = findViewById(R.id.btnBackToHome);

    }


}