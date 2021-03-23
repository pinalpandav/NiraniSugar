package com.niranisugar.android;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SideMenuActivity extends Activity {

    ImageView btnClose;
    SharedPreferences prefSelectedOption;
    SharedPreferences.Editor editorSelectedOption;

    TextView opHome,opProfile,opMyCart,opFavorite,opMyOrder,opLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.side_menu);

        prefSelectedOption = getSharedPreferences("SELECTEDPAGE",MODE_PRIVATE);
        editorSelectedOption = prefSelectedOption.edit();

        findViews();

        btnClose.setOnClickListener(view -> {
            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);

        });

        int pos = prefSelectedOption.getInt("NO",0);
        if(pos == 0){
            opHome.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else if(pos == 1){
            opMyCart.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else if(pos == 2){
            opMyOrder.setTextColor(getResources().getColor(R.color.colorPrimary));
        }else if(pos == 3){
            opProfile.setTextColor(getResources().getColor(R.color.colorPrimary));
        }

        opHome.setOnClickListener(view -> {
            editorSelectedOption.putInt("NO",0);
            editorSelectedOption.apply();

            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
            finish();
        });

        opFavorite.setOnClickListener(view -> {
            Intent i = new Intent(SideMenuActivity.this,FavoriteActivity.class);
            startActivity(i);
            finish();
        });

        opMyCart.setOnClickListener(view -> {
            editorSelectedOption.putInt("NO",1);
            editorSelectedOption.apply();

            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
            finish();
        });

        opProfile.setOnClickListener(view -> {
            editorSelectedOption.putInt("NO",3);
            editorSelectedOption.apply();

            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
            finish();
        });

        opMyOrder.setOnClickListener(view -> {
            editorSelectedOption.putInt("NO",2);
            editorSelectedOption.apply();

            Intent i = new Intent(SideMenuActivity.this,MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_top);
            finish();
        });

        opLogout.setOnClickListener(view -> {

            Intent i = new Intent(SideMenuActivity.this,WelcomeActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
            finish();
        });
    }

    private void findViews() {
         btnClose = findViewById(R.id.btnClose);
         opHome = findViewById(R.id.opHome);
         opFavorite = findViewById(R.id.opFavorite);
         opMyCart = findViewById(R.id.opMyCart);
         opProfile = findViewById(R.id.opProfile);
         opMyOrder = findViewById(R.id.opMyOrder);
         opLogout = findViewById(R.id.opLogout);
    }


}