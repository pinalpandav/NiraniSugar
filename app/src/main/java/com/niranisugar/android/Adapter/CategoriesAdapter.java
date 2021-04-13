package com.niranisugar.android.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.R;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    private Context context;
    private List<CategoriesModel> data_list;
    public int selectedPosition = -1;
    String categories_title;

    public CategoriesAdapter(Context context, String str, List<CategoriesModel> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = null;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categories, parent, false);
        return new ViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CategoriesModel categoriesModel = data_list.get(position);
        holder.tvCategoryName.setText(categoriesModel.getCategories_title());
        if(categoriesModel.getCategories_title().length() > 18){
            holder.tvCategoryName.setTextSize(12);
        }
        Glide.with(context).load(categoriesModel.getCategories_image()).into(holder.ivCategory);


    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCategory;
        TextView tvCategoryName;
        public ViewHolder(View itemView) {
            super(itemView);
            ivCategory = itemView.findViewById(R.id.ivCategory);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }
    }
}