package com.niranisugar.android.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.baoyz.widget.PullRefreshLayout;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.AdapterResellSugarAvailble;
import com.niranisugar.android.Adapter.BestSellAdapter;
import com.niranisugar.android.Adapter.CategoriesAdapter;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Adapter.ImageSliderAdapter;
import com.niranisugar.android.CategoryListActivity;
import com.niranisugar.android.FeaturedActivity;
import com.niranisugar.android.LoginActivity;
import com.niranisugar.android.MainActivity;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.Models.ImageSliderModel;
import com.niranisugar.android.Models.ProductGridModel;
import com.niranisugar.android.Models.ResellSugarAvailable;
import com.niranisugar.android.ProductDescriptionActivity;
import com.niranisugar.android.R;
import com.niranisugar.android.ResellSugar.ResellSugarAvailbleActivity;
import com.niranisugar.android.ResellSugar.SignInActivity;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashBoardFragment extends Fragment {

    RecyclerView rvCategories,rvFeatured,rvBestSell;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    public ArrayList<ProductGridModel> arrFeatured = new ArrayList<>();
    public ArrayList<ImageSliderModel> arrSliderImage = new ArrayList<>();

    public SliderView svImage;

    TextView btnSeeAllFeatured,btnSeeAllBestSell,btnSeeAllCategories;

    FeaturedAdapter featuredAdapter;
    BestSellAdapter bestSellAdapter;

    CardView btnResellSugar;

    RelativeLayout lMainDashboard;
    View lResellSugarAvailable;

    SharedPreferences prefFromWhere;
    SharedPreferences.Editor editorfromWhere;

    MainActivity mainActivity;
    KProgressHUD hud;
    ApiInterface apiService;

    //=-----------------------------ResellSugarAvaible--------------------------------

    ListView listView;
    AdapterResellSugarAvailble adapterNewData;
    ImageView btnBack;
    ArrayList<ResellSugarAvailable> arrResellSugarAvailable = new ArrayList<>();
    ArrayList<ResellSugarAvailable> arrResellSugarAvailableMainArray = new ArrayList<>();
    EditText edtSearch;
    TextView tvNoRecordFound;
    PullRefreshLayout layout;
    CardView btnStore;

    public DashBoardFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_dashboard, container, false);

        prefFromWhere = getActivity().getSharedPreferences("FROMWHERE", Context.MODE_PRIVATE);
        editorfromWhere = prefFromWhere.edit();

        mainActivity = (MainActivity) getActivity();

        hud = KProgressHUD.create(mainActivity)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        GetCategory();


        findViews(view);

        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutFrozen(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvCategories.setLayoutManager(llm);



        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvFeatured.setLayoutManager(llmF);


        rvBestSell.setHasFixedSize(true);
        rvBestSell.setLayoutFrozen(true);
        LinearLayoutManager llmBS = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvBestSell.setLayoutManager(llmBS);

        // TODO: 05-01-2021 Set data in adapter
        bestSellAdapter = new BestSellAdapter(getActivity(), "Fragment", arrCategories, "");
        rvBestSell.setAdapter(bestSellAdapter);





        btnSeeAllCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CategoryListActivity.class);
                startActivity(i);
            }
        });

        btnSeeAllFeatured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FeaturedActivity.class);
                i.putExtra("fromWhere","Featured");
                startActivity(i);
            }
        });

        btnSeeAllBestSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), FeaturedActivity.class);
                i.putExtra("fromWhere","Best Sell");
                startActivity(i);
            }
        });



        bestSellAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getActivity(), ProductDescriptionActivity.class);
                i.putExtra("id",22);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        btnResellSugar.setOnClickListener(view1 -> {

            Intent i = new Intent(getActivity(), SignInActivity.class);
            startActivity(i);
        });

        if(prefFromWhere.getBoolean("isFromMain",true)){
            btnResellSugar.setVisibility(View.VISIBLE);
            mainActivity.rlMenu.setVisibility(View.VISIBLE);
            lResellSugarAvailable.setVisibility(View.GONE);
            lMainDashboard.setVisibility(View.VISIBLE);
        }else{
            btnResellSugar.setVisibility(View.GONE);
            mainActivity.rlMenu.setVisibility(View.GONE);
            lResellSugarAvailable.setVisibility(View.VISIBLE);
            lMainDashboard.setVisibility(View.GONE);
        }

        //-----------------------Resell Sugar Avaiable ----------------------------------------

        btnBack.setOnClickListener(view1 ->  {

            editorfromWhere.putBoolean("isFromMain",true);
            editorfromWhere.apply();

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();
        });

        adapterNewData = new AdapterResellSugarAvailble(getActivity(), arrResellSugarAvailable, "USERID", true);
        listView.setAdapter(adapterNewData);
        listView.setDividerHeight(0);

        btnStore.setOnClickListener(view1 -> {
            editorfromWhere.putBoolean("isFromMain",true);
            editorfromWhere.apply();

            Intent i = new Intent(getActivity(), MainActivity.class);
            startActivity(i);
            getActivity().finish();

        });

        return view;
    }

    private void GetSliderImages() {

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetSliderImages();
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                arrSliderImage.clear();
                                for(int i = 0;i<jsonArray.length();i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ImageSliderModel modelSliderHometop1 = new ImageSliderModel();
                                    modelSliderHometop1.setImageUrl(jsonObject1.getString("photo"));
                                    arrSliderImage.add(modelSliderHometop1);

                                }

                                ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(getActivity(), arrSliderImage);
                                svImage.setSliderAdapter(imageSliderAdapter);
                                svImage.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                                svImage.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                                svImage.setScrollTimeInSec(2); //set scroll delay in seconds :
                                svImage.startAutoCycle();

                                // TODO: 05-01-2021 Set data in adapter
                                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), "Fragment", arrCategories, "");
                                rvCategories.setAdapter(categoriesAdapter);

                                GetFeatured();
                            }
                        }else{
                            hud.dismiss();
                            if(jsonObject.has("errors")){
                                Toast.makeText(mainActivity, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void GetCategory() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetCategory();
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                int length = 10;
                                if(jsonArray.length() < 10){
                                    length = jsonArray.length();
                                }
                                arrCategories.clear();
                                for(int i = 0;i<length;i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    CategoriesModel categoriesModel = new CategoriesModel();
                                    categoriesModel.setCategories_id(jsonObject1.getInt("id"));
                                    categoriesModel.setCategories_image(jsonObject1.getString("image"));
                                    categoriesModel.setCategories_photo(jsonObject1.getString("photo"));
                                    categoriesModel.setCategories_title(jsonObject1.getString("name"));
                                    categoriesModel.setIsFeatured(jsonObject1.getInt("is_featured"));
                                    categoriesModel.setCategories_slug(jsonObject1.getString("slug"));
                                    arrCategories.add(categoriesModel);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), "Fragment", arrCategories, "");
                                rvCategories.setAdapter(categoriesAdapter);

                                GetSliderImages();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(mainActivity, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void GetFeatured() {

        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetFeatures();
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if(jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                int length = 10;
                                if(jsonArray.length() < 10){
                                    length = jsonArray.length();
                                }
                                for(int i = 0;i<length;i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ProductGridModel productGridModel = new ProductGridModel();
                                    productGridModel.setId(jsonObject1.getInt("id"));
                                    productGridModel.setProduct_attributes(jsonObject1.getString("attributes"));
                                    productGridModel.setProduct_discount_date(jsonObject1.getString("discount_date"));
                                    productGridModel.setProduct_name(jsonObject1.getString("name"));
                                    productGridModel.setProduct_prev_price(jsonObject1.getString("previous_price"));
                                    productGridModel.setProduct_size(jsonObject1.getString("size"));
                                    productGridModel.setProduct_size_price(jsonObject1.getString("size_price"));
                                    productGridModel.setProduct_slug(jsonObject1.getString("slug"));
                                    productGridModel.setProduct_thumbnail(jsonObject1.getString("thumbnail"));
                                    productGridModel.setProduct_price(jsonObject1.getDouble("price"));
                                    arrFeatured.add(productGridModel);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                featuredAdapter = new FeaturedAdapter(getActivity(), "Fragment", arrFeatured, "");
                                rvFeatured.setAdapter(featuredAdapter);

                                setFeaturedAdapterClickEvent();
                            }
                        }else{
                            if(jsonObject.has("errors")){
                                Toast.makeText(mainActivity, jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                }else{
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }

    private void setFeaturedAdapterClickEvent() {
        featuredAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getActivity(), ProductDescriptionActivity.class);
                i.putExtra("id",arrFeatured.get(position).getId());
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });
    }


    private void findViews(View view) {
        rvCategories = view.findViewById(R.id.rvCategories);
        rvFeatured = view.findViewById(R.id.rvFeatured);
        rvBestSell = view.findViewById(R.id.rvBestSell);
        svImage = view.findViewById(R.id.imageSlider_top);

        btnSeeAllBestSell = view.findViewById(R.id.btnSeeAllBestSell);
        btnSeeAllFeatured = view.findViewById(R.id.btnSeeAllFeatured);
        btnSeeAllCategories = view.findViewById(R.id.btnSeeAllCategories);

        btnResellSugar = view.findViewById(R.id.btnResellSugar);

        lMainDashboard = view.findViewById(R.id.lMainDashboard);
        lResellSugarAvailable = view.findViewById(R.id.lResellSugarAvailble);


        //--------------------------ResellSugarAvailable-------------------

        tvNoRecordFound = view.findViewById(R.id.NoRecordFound);
        btnStore = view.findViewById(R.id.btnStore);
        btnBack = view.findViewById(R.id.btnBack);
        listView = view.findViewById(R.id.listViewResellSugar);
        edtSearch = view.findViewById(R.id.edtSearch);
        layout = (PullRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);

    }
}
