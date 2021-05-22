package com.niranisugar.android.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Adapter.CartAdapter;
import com.niranisugar.android.AddressActivity;
import com.niranisugar.android.CartActivity;
import com.niranisugar.android.MainActivity;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.ProductDetails;
import com.niranisugar.android.R;
import com.niranisugar.android.SqliteDatabse.Cart;
import com.niranisugar.android.SqliteDatabse.DatabaseHelper;

import java.util.ArrayList;


public class CartFragment extends Fragment {

    RecyclerView rvCart;
    CartAdapter cartAdapter;
    ArrayList<Cart> arrCart = new ArrayList<>();
    CardView btnContinue;
    private DatabaseHelper dbCart;
    public TextView tvNoDataFound;
    MainActivity mainActivity;

    @Override
    public void onStart() {
        super.onStart();
        arrCart = dbCart.GetAllCartProduct();
        if(arrCart.size() == 0){
            tvNoDataFound.setVisibility(View.VISIBLE);
            btnContinue.setEnabled(false);
        }else{
            tvNoDataFound.setVisibility(View.GONE);
            btnContinue.setEnabled(true);
        }
        cartAdapter = new CartAdapter(getActivity(),mainActivity, "Fragment", arrCart, "");
        rvCart.setAdapter(cartAdapter);
    }

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
        dbCart = new DatabaseHelper(getActivity());
        arrCart = dbCart.GetAllCartProduct();

        findViews(view);

        mainActivity = (MainActivity) getActivity();

        tvNoDataFound = view.findViewById(R.id.tvNoDataFound);


        rvCart.setHasFixedSize(true);
        rvCart.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvCart.setLayoutManager(llmF);

        // TODO: 05-01-2021 Set data in adapter
        cartAdapter = new CartAdapter(getActivity(),mainActivity, "Fragment", arrCart, "");
        rvCart.setAdapter(cartAdapter);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartAdapter.data_list.size() != 0) {
                    String Product_IDS = "";
                    String Product_Prices = "";
                    String Product_Qtys = "";
                    float Product_Total_Price = 0.0f;
                    AddOrder addOrder = new AddOrder();
                    for (int i = 0; i < cartAdapter.data_list.size(); i++) {
                        if (cartAdapter.data_list.size() == 1) {
                            Product_IDS = String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                            Product_Prices = String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                            Product_Qtys = String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                            Product_Total_Price = Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                        } else {
                            if(i == 0){
                                Product_IDS = String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                                Product_Prices =String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                                Product_Qtys = String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                                Product_Total_Price = Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                            }else{
                                Product_IDS = Product_IDS + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_id());
                                Product_Prices =Product_Prices + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_price());
                                Product_Qtys = Product_Qtys + "," + String.valueOf(cartAdapter.data_list.get(i).getProduct_count());
                                Product_Total_Price =Product_Total_Price +  Float.parseFloat(cartAdapter.data_list.get(i).getProduct_price());
                            }



                        }
                    }
                    addOrder.setProductIDs(Product_IDS);
                    addOrder.setProductPrice(Product_Prices);
                    addOrder.setProductQtys(Product_Qtys);
                    addOrder.setTotalAmount(String.format("%.2f",Product_Total_Price));
                    Intent i = new Intent(getActivity(), AddressActivity.class);
                    i.putExtra("array",addOrder);
                    startActivity(i);
                }else{
                    Toast.makeText(getActivity(), "Cart is Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void findViews(View view) {
        rvCart = view.findViewById(R.id.rvCart);
        btnContinue = view.findViewById(R.id.btnContinue);
    }


}
