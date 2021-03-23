package com.niranisugar.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    private List<CartModel> data_list;
    public int selectedPosition = -1;
    String categories_title;
    private ClickListener clickListener;

    public CartAdapter(Context context, String str, List<CartModel> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CartModel CategoriesModel = data_list.get(position);
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.tvQty.getText().toString().trim()) + 1;
                holder.tvQty.setText(String.valueOf(qty));
            }
        });

        holder.btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int qty = Integer.parseInt(holder.tvQty.getText().toString().trim());
                if(qty > 1){
                    qty = qty - 1;
                }
                holder.tvQty.setText(String.valueOf(qty));
            }
        });


    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

        ImageView btnMinus,btnPlus;
        TextView tvQty;

        public ViewHolder(View itemView) {
            super(itemView);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
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