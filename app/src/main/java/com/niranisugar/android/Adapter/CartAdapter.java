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
import com.niranisugar.android.MainActivity;
import com.niranisugar.android.Models.CartModel;
import com.niranisugar.android.Models.CategoriesModel;
import com.niranisugar.android.R;
import com.niranisugar.android.SqliteDatabse.Cart;
import com.niranisugar.android.SqliteDatabse.DatabaseHelper;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private Context context;
    public List<Cart> data_list;
    public int selectedPosition = -1;
    String categories_title;
    private DatabaseHelper dbCart;
    MainActivity mainActivity;

    public CartAdapter(Context context,MainActivity mainActivity, String str, List<Cart> data_list, String categories_title) {
        this.context = context;
        this.data_list = data_list;
        this.categories_title = categories_title;
        dbCart = new DatabaseHelper(context);
        this.mainActivity = mainActivity;
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

        Cart cart = data_list.get(position);
        holder.tvQty.setText(String.valueOf(cart.getProduct_count()));
        holder.btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int qty = Integer.parseInt(holder.tvQty.getText().toString().trim()) + 1;
                cart.setProduct_count(qty);
                dbCart.UpdateCart(cart);
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
                cart.setProduct_count(qty);
                dbCart.UpdateCart(cart);
                holder.tvQty.setText(String.valueOf(qty));
            }
        });

        holder.btnClose.setOnClickListener(view -> {
            dbCart.DeleteCart(cart);
            data_list.remove(position);
            notifyDataSetChanged();
            if(mainActivity != null) {
                MainActivity.GetCartCount(context);
            }
        });


        holder.tvProductName.setText(cart.getProduct_name());
        holder.tvPrice.setText("\u20B9 " + cart.getProduct_price());
        holder.tvCategoryName.setText(cart.getProduct_name());
        Glide.with(context).load(cart.getProduct_image()).into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return data_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView btnMinus,btnPlus;
        TextView tvQty,tvPrice,tvProductName,tvCategoryName;
        ImageView btnClose,imgProduct;

        public ViewHolder(View itemView) {
            super(itemView);
            btnMinus = itemView.findViewById(R.id.btnMinus);
            btnPlus = itemView.findViewById(R.id.btnPlus);
            tvQty = itemView.findViewById(R.id.tvQty);
            btnClose = itemView.findViewById(R.id.btnClose);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
            imgProduct = itemView.findViewById(R.id.imgProduct);
        }

    }

}