package com.niranisugar.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.niranisugar.android.Models.ProductDetails;
import com.niranisugar.android.Models.ProductGridModel;
import com.niranisugar.android.R;

import java.util.List;

public class ProductByCategoryAdapter extends RecyclerView.Adapter<ProductByCategoryAdapter.ViewHolder> {

    private Context context;
    private List<ProductDetails> data_list;
    public int selectedPosition = -1;
    String categories_title;
    private ClickListener clickListener;

    public ProductByCategoryAdapter(Context context, String str, List<ProductDetails> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured_all, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductDetails productGridModel = data_list.get(position);
        holder.tvProductName.setText(productGridModel.getName());
        holder.tvProductPrice.setText("\u20B9 " + String.format("%.2f",productGridModel.getPrice()));


    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener  {

        ImageView ivThumbnail;
        TextView tvProductPrice,tvProductName;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
            tvProductPrice = itemView.findViewById(R.id.tvPrice);
            tvProductName = itemView.findViewById(R.id.tvProductName);
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