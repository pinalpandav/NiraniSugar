package com.niranisugar.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niranisugar.android.AddressActivity;
import com.niranisugar.android.Models.AddOrder;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.OrderModel;
import com.niranisugar.android.R;
import com.niranisugar.android.SqliteDatabse.Cart;

import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderModel> data_list;
    public int selectedPosition = -1;
    String categories_title;
    private ClickListener clickListener;

    public MyOrderAdapter(Context context, String str, List<OrderModel> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_myorders, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        OrderModel cart = data_list.get(position);
        holder.btnOrderAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AddOrder addOrder = new AddOrder();
                addOrder.setProductIDs(String.valueOf(cart.getProduct_id()));
                addOrder.setProductPrice(String.valueOf(cart.getProduct_price()));
                addOrder.setProductQtys("1");
                addOrder.setTotalAmount(String.valueOf(cart.getProduct_price()));

                Intent i = new Intent(context, AddressActivity.class);
                i.putExtra("array",addOrder);
                context.startActivity(i);
            }
        });

        holder.tvItemName.setText(cart.getProduct_name());
        holder.tvPrice.setText("\u20B9 " + cart.getProduct_price());
        holder.tvQty.setText(cart.getProduct_name());
        Glide.with(context).load(cart.getProduct_image()).into(holder.img);


    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

        LinearLayout btnOrderAgain;
        ImageView img;
        TextView tvItemName,tvQty,tvPrice;

        public ViewHolder(View itemView) {
            super(itemView);
            btnOrderAgain = itemView.findViewById(R.id.btnOrderAgain);
            img = itemView.findViewById(R.id.img);
            tvItemName = itemView.findViewById(R.id.tvItemName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvQty = itemView.findViewById(R.id.tvQty);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v);
        }

        @Override
        public boolean onLongClick(View v) {
            clickListener.onItemLongClick(getAdapterPosition(), v);
            return false;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}