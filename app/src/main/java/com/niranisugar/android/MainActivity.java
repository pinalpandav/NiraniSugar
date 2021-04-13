package com.niranisugar.android;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.niranisugar.android.Fragments.CartFragment;
import com.niranisugar.android.Fragments.DashBoardFragment;
import com.niranisugar.android.Fragments.MyOrderFragment;
import com.niranisugar.android.Fragments.ProfileFragment;
import com.niranisugar.android.ResellSugar.SignInActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    //viewPager
    private ViewPager viewPager;

    //Fragments
    DashBoardFragment dashBoardFragment;
    CartFragment cartFragment;
    MyOrderFragment myOrderFragment;
    ProfileFragment profileFragment;

    MenuItem prevMenuItem;

    ImageView imgMenu;

    private DrawerLayout mDrawer;
    private NavigationView nvDrawer;

    public RelativeLayout rlMenu;

    ImageView imgLoginLogout;
    SharedPreferences prefUserData;
    public String access_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sidemenu);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        rlMenu = findViewById(R.id.rlMenu);
        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        imgLoginLogout = findViewById(R.id.imgLoginLogout);

        nvDrawer.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.opHome:
                        mDrawer.closeDrawer(GravityCompat.START);
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.opProfile:
                        mDrawer.closeDrawer(GravityCompat.START);
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.opMyCart:
                    case R.id.opFavorite:
                        mDrawer.closeDrawer(GravityCompat.START);
//                        viewPager.setCurrentItem(1);
                        return true;
//                        viewPager.setCurrentItem(2);
                    //                    case R.id.opLogout:
//                        Intent i = new Intent(MainActivity.this,WelcomeActivity.class);
//                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                        startActivity(i);
//                        finish();
//                        return true;
//                    case R.id.opFavorite:
//                        mDrawer.closeDrawer(GravityCompat.START);
//                       Intent ifav = new Intent(MainActivity.this,FavoriteActivity.class);
//                        startActivity(ifav);
//                        return true;

                }
                return false;
            }
        });


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_mycart:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_myorder:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(3);
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "" + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

        imgMenu = findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawer.openDrawer(GravityCompat.START);
            }
        });

        access_token = prefUserData.getString("token", "");
        if (access_token.isEmpty()) {
            imgLoginLogout.setTag("Login");
            imgLoginLogout.setImageResource(R.drawable.login);
        } else {
            imgLoginLogout.setTag("Logout");
            imgLoginLogout.setImageResource(R.drawable.logout);
        }

        imgLoginLogout.setOnClickListener(view -> {
            if (imgLoginLogout.getTag().equals("Login")) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to Logout?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(MainActivity.this, WelcomeActivity.class);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        dashBoardFragment = new DashBoardFragment();
        cartFragment = new CartFragment();
        myOrderFragment = new MyOrderFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(dashBoardFragment);
        adapter.addFragment(cartFragment);
        adapter.addFragment(myOrderFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

    }
}