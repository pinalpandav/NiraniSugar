package com.niranisugar.android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.Models.ProductGridModel;
import com.niranisugar.android.R;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.ViewHolder> {

    private Context context;
    private List<ProductGridModel> data_list;
    public int selectedPosition = -1;
    String categories_title;
    private ClickListener clickListener;


    public FeaturedAdapter(Context context, String str, List<ProductGridModel> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_featured, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ProductGridModel productGridModel = data_list.get(position);
        holder.tvProductName.setText(productGridModel.getProduct_name());
        holder.tvProductPrice.setText("\u20B9 " + String.format("%.2f",productGridModel.getProduct_price()));
        Glide.with(context).load(productGridModel.getProduct_thumbnail()).into(holder.ivThumbnail);

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