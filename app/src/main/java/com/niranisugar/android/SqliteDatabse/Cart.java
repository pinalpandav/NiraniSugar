package com.niranisugar.android.SqliteDatabse;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ravi on 20/02/18.
 */

public class Cart implements Parcelable, Serializable {
    public static final String TABLE_NAME = "cart";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_PRODUCT_ID = "product_id";
    public static final String COLUMN_PRODUCT_NAME = "product_name";
    public static final String COLUMN_PRODUCT_CATEGORY = "product_category";
    public static final String COLUMN_PRODUCT_PRICE = "product_price";
    public static final String COLUMN_PRODUCT_COUNT = "product_count";
    public static final String COLUMN_PRODUCT_IMAGE = "product_image";


    private int id,product_id,product_count;
    private String product_name,product_category,product_price,product_image;


    // Create table SQL query
    public static final String CREATE_TABLE_CART =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_PRODUCT_ID + " INTEGER,"
                    + COLUMN_PRODUCT_NAME + " TEXT,"
                    + COLUMN_PRODUCT_CATEGORY + " TEXT,"
                    + COLUMN_PRODUCT_PRICE + " TEXT,"
                    + COLUMN_PRODUCT_COUNT + " INTEGER,"
                    + COLUMN_PRODUCT_IMAGE + " TEXT"
                    + ")";

    public Cart() {
    }

    protected Cart(Parcel in) {
        id = in.readInt();
        product_id = in.readInt();
        product_count = in.readInt();
        product_name = in.readString();
        product_category = in.readString();
        product_price = in.readString();
        product_image = in.readString();
    }

    public static final Creator<Cart> CREATOR = new Creator<Cart>() {
        @Override
        public Cart createFromParcel(Parcel in) {
            return new Cart(in);
        }

        @Override
        public Cart[] newArray(int size) {
            return new Cart[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getProduct_count() {
        return product_count;
    }

    public void setProduct_count(int product_count) {
        this.product_count = product_count;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_image() {
        return product_image;
    }

    public void setProduct_image(String product_image) {
        this.product_image = product_image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(product_id);
        parcel.writeInt(product_count);
        parcel.writeString(product_name);
        parcel.writeString(product_category);
        parcel.writeString(product_price);
        parcel.writeString(product_image);
    }
}
