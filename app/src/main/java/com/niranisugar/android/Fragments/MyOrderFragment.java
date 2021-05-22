package com.niranisugar.android.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.niranisugar.android.API.ApiClient;
import com.niranisugar.android.API.ApiInterface;
import com.niranisugar.android.Adapter.MyOrderAdapter;
import com.niranisugar.android.Models.OrderModel;
import com.niranisugar.android.OrderDetailsActivity;
import com.niranisugar.android.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;


public class MyOrderFragment extends Fragment {

    RecyclerView rvCart;
    MyOrderAdapter myOrderAdapter;
    ArrayList<OrderModel> arrCart = new ArrayList<>();
    KProgressHUD hud;
    ApiInterface apiService;
    String access_token;
    SharedPreferences prefUserData;
    TextView tvNoDataFound;
    
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

        prefUserData = getActivity().getSharedPreferences("USER_DATA", MODE_PRIVATE);
        access_token = prefUserData.getString("token", "");


        hud = KProgressHUD.create(getActivity())
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(false)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f);

        findViews(view);

//        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","2","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","8","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","3","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","1","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","9","https://bit.ly/37Rn50u"));
//        arrCart.add(new CartModel("T-Shirt","Women","5","https://bit.ly/37Rn50u"));

        tvNoDataFound.setVisibility(View.VISIBLE);

        rvCart.setHasFixedSize(true);
        rvCart.setLayoutFrozen(true);
        LinearLayoutManager llmF = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rvCart.setLayoutManager(llmF);



        GetOrders();


        return view;
    }

    private void findViews(View view) {
        rvCart = view.findViewById(R.id.rvCart);
        tvNoDataFound = view.findViewById(R.id.tvNoDataFound);
    }

    private void GetOrders() {
        hud.show();
        apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<String> callCard = apiService.GetOrders(access_token);
        callCard.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                hud.dismiss();
                if (response.code() == 200) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.body());
                        if (jsonObject.has("status")) {
                            if (jsonObject.getString("status").equals("1")) {
                                arrCart.clear();
                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                for(int i = 0;i<jsonArray.length();i++){
                                    OrderModel orderModel = new OrderModel();
                                    JSONArray jsonArrCart = jsonArray.getJSONObject(i).getJSONArray("cart");
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    orderModel.setId(jsonObject1.getInt("id"));
                                    orderModel.setOrder_id(jsonObject1.getString("order_number"));
                                    orderModel.setPayment_status(jsonObject1.getString("payment_status"));
                                    orderModel.setCustomer_email(jsonObject1.getString("customer_email"));
                                    orderModel.setCustomer_name(jsonObject1.getString("customer_name"));
                                    orderModel.setCustomer_mobileno(jsonObject1.getString("customer_phone"));
                                    orderModel.setShipping_address(jsonObject1.getString("shipping_address"));
                                    orderModel.setShipping_city(jsonObject1.getString("shipping_city"));
                                    orderModel.setShipping_country(jsonObject1.getString("shipping_country"));
                                    orderModel.setOrder_status(jsonObject1.getString("status"));

                                    for(int j = 0;j<jsonArrCart.length();j++) {
                                        JSONObject jsonObject2 = jsonArrCart.getJSONObject(j);
                                        orderModel.setProduct_id(jsonObject2.getInt("id"));
                                        orderModel.setProduct_category(jsonObject2.getString("quantity"));
                                        orderModel.setProduct_name(jsonObject2.getString("name"));
                                        orderModel.setProduct_count(1);
                                        orderModel.setProduct_price(String.format("%.2f", jsonObject2.getDouble("price")));
                                        orderModel.setProduct_image(jsonObject2.getString("photo"));
                                        arrCart.add(orderModel);
                                    }
                                }

                                if(arrCart.size() !=  0){
                                    tvNoDataFound.setVisibility(View.GONE);
                                }

                                // TODO: 05-01-2021 Set data in adapter
                                myOrderAdapter = new MyOrderAdapter(getActivity(), "Fragment", arrCart, "");
                                rvCart.setAdapter(myOrderAdapter);

                                myOrderAdapter.setOnItemClickListener(new MyOrderAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int position, View v) {
                                        Intent i = new Intent(getActivity(), OrderDetailsActivity.class);
                                        i.putExtra("Orders",arrCart.get(position));
                                        startActivity(i);
                                    }

                                    @Override
                                    public void onItemLongClick(int position, View v) {

                                    }
                                });

                            }
                        } else {
                            if (jsonObject.has("errors")) {
                                Toast.makeText(getActivity(), jsonObject.getJSONObject("errors").toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        hud.dismiss();
                        e.printStackTrace();
                    }
                } else {
                    hud.dismiss();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                hud.dismiss();
            }
        });
    }


}
