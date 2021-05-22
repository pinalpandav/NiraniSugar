package com.niranisugar.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderModel implements Parcelable {

    private int id,product_id,product_count;
    private String product_name,product_category,product_price,product_image;
    private String order_id,payment_status,customer_email,customer_name,customer_mobileno,shipping_address,shipping_city,shipping_country,order_status;

    public OrderModel() {
    }

    protected OrderModel(Parcel in) {
        id = in.readInt();
        product_id = in.readInt();
        product_count = in.readInt();
        product_name = in.readString();
        product_category = in.readString();
        product_price = in.readString();
        product_image = in.readString();
        order_id = in.readString();
        payment_status = in.readString();
        customer_email = in.readString();
        customer_name = in.readString();
        customer_mobileno = in.readString();
        shipping_address = in.readString();
        shipping_city = in.readString();
        shipping_country = in.readString();
        order_status = in.readString();
    }

    public static final Creator<OrderModel> CREATOR = new Creator<OrderModel>() {
        @Override
        public OrderModel createFromParcel(Parcel in) {
            return new OrderModel(in);
        }

        @Override
        public OrderModel[] newArray(int size) {
            return new OrderModel[size];
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

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPayment_status() {
        return payment_status;
    }

    public void setPayment_status(String payment_status) {
        this.payment_status = payment_status;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_mobileno() {
        return customer_mobileno;
    }

    public void setCustomer_mobileno(String customer_mobileno) {
        this.customer_mobileno = customer_mobileno;
    }

    public String getShipping_address() {
        return shipping_address;
    }

    public void setShipping_address(String shipping_address) {
        this.shipping_address = shipping_address;
    }

    public String getShipping_city() {
        return shipping_city;
    }

    public void setShipping_city(String shipping_city) {
        this.shipping_city = shipping_city;
    }

    public String getShipping_country() {
        return shipping_country;
    }

    public void setShipping_country(String shipping_country) {
        this.shipping_country = shipping_country;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
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
        parcel.writeString(order_id);
        parcel.writeString(payment_status);
        parcel.writeString(customer_email);
        parcel.writeString(customer_name);
        parcel.writeString(customer_mobileno);
        parcel.writeString(shipping_address);
        parcel.writeString(shipping_city);
        parcel.writeString(shipping_country);
        parcel.writeString(order_status);
    }
}
