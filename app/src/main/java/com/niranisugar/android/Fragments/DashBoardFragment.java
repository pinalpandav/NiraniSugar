package com.niranisugar.android.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.BestSellAdapter;
import com.niranisugar.android.Adapter.CategoriesAdapter;
import com.niranisugar.android.Adapter.FeaturedAdapter;
import com.niranisugar.android.Adapter.FeaturedAllAdapter;
import com.niranisugar.android.Adapter.ImageSliderAdapter;
import com.niranisugar.android.CategoryListActivity;
import com.niranisugar.android.FeaturedActivity;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.Models.ImageSliderModel;
import com.niranisugar.android.ProductDescriptionActivity;
import com.niranisugar.android.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;


public class DashBoardFragment extends Fragment {

    RecyclerView rvCategories,rvFeatured,rvBestSell;
    public ArrayList<CategoriesModel> arrCategories = new ArrayList<>();
    public ArrayList<ImageSliderModel> arrSliderImage = new ArrayList<>();

    public SliderView svImage;

    TextView btnSeeAllFeatured,btnSeeAllBestSell,btnSeeAllCategories;

    FeaturedAdapter featuredAdapter;
    BestSellAdapter bestSellAdapter;

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

        CategoriesModel obj1 = new CategoriesModel();
        obj1.setCategories_title("Sugar");
        obj1.setCategories_image("Sugar");
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);
        arrCategories.add(obj1);

        findViews(view);

        rvCategories.setHasFixedSize(true);
        rvCategories.setLayoutFrozen(true);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvCategories.setLayoutManager(llm);

        // TODO: 05-01-2021 Set data in adapter
        CategoriesAdapter categoriesAdapter = new CategoriesAdapter(getActivity(), "Fragment", arrCategories, "");
        rvCategories.setAdapter(categoriesAdapter);

        rvFeatured.setHasFixedSize(true);
        rvFeatured.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvFeatured.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        featuredAdapter = new FeaturedAdapter(getActivity(), "Fragment", arrCategories, "");
        rvFeatured.setAdapter(featuredAdapter);

        rvBestSell.setHasFixedSize(true);
        rvBestSell.setLayoutFrozen(true);
        LinearLayoutManager llmBS = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rvBestSell.setLayoutManager(llmBS);

        // TODO: 05-01-2021 Set data in adapter
        bestSellAdapter = new BestSellAdapter(getActivity(), "Fragment", arrCategories, "");
        rvBestSell.setAdapter(bestSellAdapter);


        ImageSliderModel modelSliderHometop1 = new ImageSliderModel();
        modelSliderHometop1.setImageUrl("https://3.bp.blogspot.com/-Wxr9iElgMHc/XvEleXaZOTI/AAAAAAAAAQY/WcZotaCBqdYQzBX5HBjTr1R3NkiKtS1sgCLcBGAsYHQ/s1600/lion.png");
        arrSliderImage.add(modelSliderHometop1);

        ImageSliderModel modelSliderHometop2 = new ImageSliderModel();
        modelSliderHometop2.setImageUrl("https://3.bp.blogspot.com/-B9_SO1pZJTw/XvElYpggK0I/AAAAAAAAAQQ/ZKP7yjF-IOAnukbbt-4LfvXk99ekuGGbQCLcBGAsYHQ/s1600/elephant.jpg");
        arrSliderImage.add(modelSliderHometop2);

        ImageSliderModel modelSliderHometop3 = new ImageSliderModel();
        modelSliderHometop3.setImageUrl("https://4.bp.blogspot.com/-8U-yeWQyT-g/XvEld2GJKQI/AAAAAAAAAQU/EaqkAB8h8g0kDDDowQ3-2MxvHU1R7S6_QCLcBGAsYHQ/s1600/hands.jpg");
        arrSliderImage.add(modelSliderHometop3);

        ImageSliderAdapter imageSliderAdapter = new ImageSliderAdapter(getActivity(), arrSliderImage);
        svImage.setSliderAdapter(imageSliderAdapter);
        svImage.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        svImage.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        svImage.setScrollTimeInSec(2); //set scroll delay in seconds :
        svImage.startAutoCycle();

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

        featuredAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getActivity(), ProductDescriptionActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        bestSellAdapter.setOnItemClickListener(new FeaturedAllAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Intent i = new Intent(getActivity(), ProductDescriptionActivity.class);
                startActivity(i);
            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        return view;
    }

    private void findViews(View view) {
        rvCategories = view.findViewById(R.id.rvCategories);
        rvFeatured = view.findViewById(R.id.rvFeatured);
        rvBestSell = view.findViewById(R.id.rvBestSell);
        svImage = view.findViewById(R.id.imageSlider_top);

        btnSeeAllBestSell = view.findViewById(R.id.btnSeeAllBestSell);
        btnSeeAllFeatured = view.findViewById(R.id.btnSeeAllFeatured);
        btnSeeAllCategories = view.findViewById(R.id.btnSeeAllCategories);

    }
}
