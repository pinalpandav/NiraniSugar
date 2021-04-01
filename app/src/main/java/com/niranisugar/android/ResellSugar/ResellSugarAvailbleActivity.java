package com.niranisugar.android.ResellSugar;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.baoyz.widget.PullRefreshLayout;
import com.niranisugar.android.Adapter.AdapterResellSugarAvailble;
import com.niranisugar.android.Models.ResellSugarAvailable;
import com.niranisugar.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ResellSugarAvailbleActivity extends Activity {

    ListView listView;
    AdapterResellSugarAvailble adapterNewData;
    ImageView btnBack;
    //ApiInterface apiService;
    // SharedPreferences prefUserData;

    ArrayList<ResellSugarAvailable> arrResellSugarAvailable = new ArrayList<>();
    ArrayList<ResellSugarAvailable> arrResellSugarAvailableMainArray = new ArrayList<>();

    String USERID = "";
    //KProgressHUD hud;

//    EditText edtGrade;
    EditText edtSearch;
    TextView tvNoRecordFound;

    CardView btnSeeMore;

    PullRefreshLayout layout;
    // DashboardActivity thisAct;

    // RelativeLayout layoutStartTiming,layoutTimeUp;
    //TextView tvGoodNight,tvMessage;


//    Spinner spinCountry;
    boolean isGrade = true;
        CardView btnStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_resell_suger_availble);


        tvNoRecordFound = findViewById(R.id.NoRecordFound);

        btnStore = findViewById(R.id.btnStore);

        btnBack = findViewById(R.id.btnBack);
        listView = findViewById(R.id.listViewResellSugar);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        edtSearch = findViewById(R.id.edtSearch);
//        edtGrade = findViewById(R.id.edtGradeSearch);
//
//
//        spinCountry = (Spinner) findViewById(R.id.spSelect);//fetch the spinner from layout file
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.spinner_item, getResources()
//                .getStringArray(R.array.country_array));//setting the country_array to spinner
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinCountry.setAdapter(adapter);
//
//        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int position, long id) {
//                if (position == 1) {
//                    edtGrade.setHint("Zone");
//                    isGrade = false;
//                } else {
//                    edtGrade.setHint("Grade");
//                    isGrade = true;
//                }
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> arg0) {
//            }
//        });


        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<ResellSugarAvailable> arrFilter = new ArrayList<>();
                for (int i = 0; i < arrResellSugarAvailableMainArray.size(); i++) {
                    if (arrResellSugarAvailableMainArray.get(i).getMillShortName().toLowerCase().contains(edtSearch.getText().toString().trim().toLowerCase()) ||
                            arrResellSugarAvailableMainArray.get(i).getMillFullName().toLowerCase().contains(edtSearch.getText().toString().trim().toLowerCase())) {
                        arrFilter.add(arrResellSugarAvailableMainArray.get(i));
                    }
                }
                if (arrFilter.size() == 0) {
                    tvNoRecordFound.setVisibility(View.VISIBLE);
                } else {
                    tvNoRecordFound.setVisibility(View.GONE);
                }
                arrResellSugarAvailable.clear();
                arrResellSugarAvailable.addAll(arrFilter);
                adapterNewData = new AdapterResellSugarAvailble(ResellSugarAvailbleActivity.this, arrResellSugarAvailable, USERID, true);
                listView.setAdapter(adapterNewData);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        edtGrade.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (isGrade) {
//                    ArrayList<ResellSugarAvailable> arrFilter = new ArrayList<>();
//                    for (int i = 0; i < arrResellSugarAvailableMainArray.size(); i++) {
//                        if (arrResellSugarAvailableMainArray.get(i).getGrade().toLowerCase().contains(edtGrade.getText().toString().trim().toLowerCase())) {
//                            arrFilter.add(arrResellSugarAvailableMainArray.get(i));
//                        }
//                    }
//                    if (arrFilter.size() == 0) {
//                        tvNoRecordFound.setVisibility(View.VISIBLE);
//                    } else {
//                        tvNoRecordFound.setVisibility(View.GONE);
//                    }
//                    arrResellSugarAvailable.clear();
//                    arrResellSugarAvailable.addAll(arrFilter);
//                    adapterNewData = new AdapterResellSugarAvailble(ResellSugarAvailbleActivity.this, arrResellSugarAvailable, USERID, true);
//                    listView.setAdapter(adapterNewData);
//                } else {
//                    ArrayList<ResellSugarAvailable> arrFilter = new ArrayList<>();
//                    for (int i = 0; i < arrResellSugarAvailableMainArray.size(); i++) {
//                        if (arrResellSugarAvailableMainArray.get(i).getZone().toLowerCase().contains(edtGrade.getText().toString().trim().toLowerCase())) {
//                            arrFilter.add(arrResellSugarAvailableMainArray.get(i));
//                        }
//                    }
//                    if (arrFilter.size() == 0) {
//                        tvNoRecordFound.setVisibility(View.VISIBLE);
//                    } else {
//                        tvNoRecordFound.setVisibility(View.GONE);
//                    }
//                    arrResellSugarAvailable.clear();
//                    arrResellSugarAvailable.addAll(arrFilter);
//                    adapterNewData = new AdapterResellSugarAvailble(ResellSugarAvailbleActivity.this, arrResellSugarAvailable, USERID, true);
//                    listView.setAdapter(adapterNewData);
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

        layout = (PullRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        layout.setOnRefreshListener(new PullRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                FromStartIn = 0;
//                LastPosition = 20;
//                callAPI(false, FromStartIn);
            }
        });

        adapterNewData = new AdapterResellSugarAvailble(ResellSugarAvailbleActivity.this, arrResellSugarAvailable, USERID, true);
        listView.setAdapter(adapterNewData);
        listView.setDividerHeight(0);

        btnStore.setOnClickListener(view -> {
            onBackPressed();
        });

//        CallAPItoGetServerTime(true);

        /*listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {

                    view.setOnTouchListener(new View.OnTouchListener() {
                        private float mInitialX;
                        private float mInitialY;

                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            switch (event.getAction()) {
                                case MotionEvent.ACTION_DOWN:
                                    mInitialX = event.getX();
                                    mInitialY = event.getY();
                                    return false;
                                case MotionEvent.ACTION_MOVE:
                                    final float x = event.getX();
                                    final float y = event.getY();
                                    final float yDiff = y - mInitialY;
                                    if (yDiff > 0.0) {
                                        if (!APICALLING) {
                                            callAPI(false, FromStartIn);
                                        }
                                        break;

                                    }
                                    break;
                            }
                            return false;
                        }
                    });
                }
            }
        });*/

    }

}