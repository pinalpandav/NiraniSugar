package com.niranisugar.android.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.Adapter.MyOrderAdapter;
import com.niranisugar.android.AddressActivity;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.R;

import java.util.ArrayList;


public class MyOrderFragment extends Fragment {

    RecyclerView rvCart;
    MyOrderAdapter myOrderAdapter;
    ArrayList<CartModel> arrCart = new ArrayList<>();

    public MyOrderFragment() {
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
        View view = inflater.inflate(R.layout.fragment_myorder, container, false);

        findViews(view);

        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","8","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","3","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","9","https://bit.ly/37Rn50u"));
        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));

        rvCart.setHasFixedSize(true);
        rvCart.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvCart.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        myOrderAdapter = new MyOrderAdapter(getActivity(), "Fragment", arrCart, "");
        rvCart.setAdapter(myOrderAdapter);

        myOrderAdapter.setOnItemClickListener(new MyOrderAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });



        return view;
    }

    private void findViews(View view) {
        rvCart = view.findViewById(R.id.rvCart);
    }


}
