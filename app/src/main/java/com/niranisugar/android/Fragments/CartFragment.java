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
import com.niranisugar.android.AddressActivity;
import com.niranisugar.android.CartActivity;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.R;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    RecyclerView rvCart;
    CartAdapter cartAdapter;
    ArrayList<CartModel> arrCart = new ArrayList<>();
    CardView btnContinue;

    public CartFragment() {
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

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
        cartAdapter = new CartAdapter(getActivity(), "Fragment", arrCart, "");
        rvCart.setAdapter(cartAdapter);

        cartAdapter.setOnItemClickListener(new CartAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), AddressActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

    private void findViews(View view) {
        rvCart = view.findViewById(R.id.rvCart);
        btnContinue = view.findViewById(R.id.btnContinue);
    }


}
