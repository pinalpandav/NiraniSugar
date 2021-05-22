package com.niranisugar.android;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.niranisugar.android.Fragments.CartFragment;
import com.niranisugar.android.Fragments.DashBoardFragment;
import com.niranisugar.android.Fragments.MyOrderFragment;
import com.niranisugar.android.Fragments.ProfileFragment;
import com.niranisugar.android.SqliteDatabse.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static BottomNavigationView bottomNavigationView;

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
    ImageView imgNotification;
    SharedPreferences.Editor editorUserData;

    TextView tvName, tvEmail;
    private static DatabaseHelper dbCart;

    @Override
    public void onBackPressed() {
        if (dashBoardFragment.llSearchLayout.getVisibility() == View.VISIBLE) {
            dashBoardFragment.llDashboardLayout.setVisibility(View.VISIBLE);
            dashBoardFragment.llSearchLayout.setVisibility(View.GONE);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        GetCartCount(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_sidemenu);

        dbCart = new DatabaseHelper(this);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        View headerView = nvDrawer.getHeaderView(0);
        tvName = (TextView) headerView.findViewById(R.id.tvName);
        tvEmail = (TextView) headerView.findViewById(R.id.tvEmail);
        rlMenu = findViewById(R.id.rlMenu);
        prefUserData = getSharedPreferences("USER_DATA", MODE_PRIVATE);
        editorUserData = prefUserData.edit();
        imgLoginLogout = findViewById(R.id.imgLoginLogout);
        imgNotification = findViewById(R.id.imgNotification);

        imgNotification.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this, NotificationActivity.class);
            startActivity(i);
        });

        nvDrawer.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.opHome:
                    mDrawer.closeDrawer(GravityCompat.START);
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.opProfile:
                    mDrawer.closeDrawer(GravityCompat.START);
                    if (imgLoginLogout.getTag().equals("Login")) {
                        doLogin();
                    } else {
                        viewPager.setCurrentItem(3);
                    }
                    return true;
                case R.id.opMyCart:
                    mDrawer.closeDrawer(GravityCompat.START);
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.opPrivacyPolicy:
                    mDrawer.closeDrawer(GravityCompat.START);
                    Intent i = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
                    startActivity(i);
                    return true;
                case R.id.opTermsAndCondition:
                    mDrawer.closeDrawer(GravityCompat.START);
                    Intent ij = new Intent(MainActivity.this, TermsAndConditionActivity.class);
                    startActivity(ij);
                    return true;
                case R.id.opAboutUs:
                    mDrawer.closeDrawer(GravityCompat.START);
                    Intent ijk = new Intent(MainActivity.this, AboutUsActivity.class);
                    startActivity(ijk);
                    return true;
                case R.id.opLogout:
                    if (item.getTitle().toString().equalsIgnoreCase("Login")) {
                        Login();
                    } else {
                        Logout();
                    }
                    mDrawer.closeDrawer(GravityCompat.START);
                    return true;
            }
            return false;
        });


        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    switch (item.getItemId()) {
                        case R.id.navigation_home:
                            viewPager.setCurrentItem(0);
                            break;
                        case R.id.navigation_mycart:
                            viewPager.setCurrentItem(1);
                            break;
                        case R.id.navigation_myorder:
                            if (imgLoginLogout.getTag().equals("Login")) {
                                doLogin();
                            } else {
                                viewPager.setCurrentItem(2);
                            }
                            break;
                        case R.id.navigation_profile:
                            if (imgLoginLogout.getTag().equals("Login")) {
                                doLogin();
                            } else {
                                viewPager.setCurrentItem(3);
                            }
                            break;
                    }
                    GetCartCount(MainActivity.this);
                    return false;
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

                GetCartCount(MainActivity.this);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);

        imgMenu = findViewById(R.id.imgMenu);
        imgMenu.setOnClickListener(view -> mDrawer.openDrawer(GravityCompat.START));

        Menu menu = nvDrawer.getMenu();
        MenuItem menuItem = menu.findItem(R.id.opLogout);

        access_token = prefUserData.getString("token", "");
        String user_data = prefUserData.getString("user_data", "");
        JsonObject jobjUser = new Gson().fromJson(user_data, JsonObject.class);

        if (access_token.isEmpty()) {
            imgLoginLogout.setTag("Login");
            imgLoginLogout.setImageResource(R.drawable.login);
            menuItem.setTitle("Login");
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.login));
            tvName.setVisibility(View.GONE);
            tvEmail.setVisibility(View.GONE);
        } else {
            imgLoginLogout.setTag("Logout");
            imgLoginLogout.setImageResource(R.drawable.logout);
            menuItem.setTitle("Logout");
            menuItem.setIcon(ContextCompat.getDrawable(this, R.drawable.logout));
            tvName.setVisibility(View.VISIBLE);
            tvEmail.setVisibility(View.VISIBLE);
            tvEmail.setText(jobjUser.getAsJsonObject("data").get("email").getAsString());
            tvName.setText(jobjUser.getAsJsonObject("data").get("name").getAsString());
        }

        imgLoginLogout.setOnClickListener(view -> {
            if (imgLoginLogout.getTag().equals("Login")) {
                Login();
            } else {
                Logout();
            }
        });

        GetCartCount(this);

    }

    public void Login() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();

    }

    public void doLogin() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }

    public static void showBadge(Context context, BottomNavigationView
            bottomNavigationView, @IdRes int itemId, String value) {
        removeBadge(bottomNavigationView, itemId);
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        View badge = LayoutInflater.from(context).inflate(R.layout.layout_budge, bottomNavigationView, false);

        TextView text = badge.findViewById(R.id.badge_text_view);
        text.setText(value);
        itemView.addView(badge);
    }

    public static void removeBadge(BottomNavigationView bottomNavigationView, @IdRes int itemId) {
        BottomNavigationItemView itemView = bottomNavigationView.findViewById(itemId);
        if (itemView.getChildCount() == 3) {
            itemView.removeViewAt(2);
        }
    }


    public void Logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to Logout?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        editorUserData.putString("user_data", "");
                        editorUserData.putString("token", "");
                        editorUserData.apply();

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
//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        MenuItem item = menu.findItem(R.id.opLogout);
//        access_token = prefUserData.getString("token", "");
//        if (access_token.isEmpty()) {
//           item.setTitle("Login");
//           item.setIcon(ContextCompat.getDrawable(this,R.drawable.login));
//        } else {
//            item.setTitle("Logout");
//            item.setIcon(ContextCompat.getDrawable(this,R.drawable.logout));
//        }
//
//        return super.onPrepareOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.side_menu, menu);
//        MenuItem item = menu.findItem(R.id.opLogout);
//        access_token = prefUserData.getString("token", "");
//        if (access_token.isEmpty()) {
//            item.setTitle("Login");
//            item.setIcon(ContextCompat.getDrawable(this,R.drawable.login));
//        } else {
//            item.setTitle("Logout");
//            item.setIcon(ContextCompat.getDrawable(this,R.drawable.logout));
//        }
//        return super.onCreateOptionsMenu(menu);
//    }

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


    public static void GetCartCount(Context context) {
        int CartCount = dbCart.GetCartCount();
        if (CartCount == 0) {
            removeBadge(bottomNavigationView, R.id.navigation_mycart);
        } else {
            showBadge(context, bottomNavigationView, R.id.navigation_mycart, String.valueOf(CartCount));
        }
    }
}