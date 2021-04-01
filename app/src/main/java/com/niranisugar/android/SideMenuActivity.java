package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SideMenuActivity extends Activity {

    ImageView btnClose;

    TextView opHome,opProfile,opMyCart,opFavorite,opMyOrder,opLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_menu);


        findViews();

        btnClose.setOnClickListener(view -> {
            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);

        });


    }

    private void findViews() {
//         btnClose = findViewById(R.id.btnClose);
//         opHome = findViewById(R.id.opHome);
//         opFavorite = findViewById(R.id.opFavorite);
//         opMyCart = findViewById(R.id.opMyCart);
//         opProfile = findViewById(R.id.opProfile);
////         opMyOrder = findViewById(R.id.opMyOrder);
////         opLogout = findViewById(R.id.opLogout);
    }


}