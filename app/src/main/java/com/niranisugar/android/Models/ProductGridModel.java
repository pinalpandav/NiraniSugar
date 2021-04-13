package com.niranisugar.android.Models;

public class ProductGridModel {

    int id;
    String product_name;
    String product_slug;
    String product_thumbnail;
    double product_price;
    String product_prev_price;
    String product_attributes;
    String product_size;
    String product_size_price;
    String product_discount_date;

    public ProductGridModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_slug() {
        return product_slug;
    }

    public void setProduct_slug(String product_slug) {
        this.product_slug = product_slug;
    }

    public String getProduct_thumbnail() {
        return product_thumbnail;
    }

    public void setProduct_thumbnail(String product_thumbnail) {
        this.product_thumbnail = product_thumbnail;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public String getProduct_prev_price() {
        return product_prev_price;
    }

    public void setProduct_prev_price(String product_prev_price) {
        this.product_prev_price = product_prev_price;
    }

    public String getProduct_attributes() {
        return product_attributes;
    }

    public void setProduct_attributes(String product_attributes) {
        this.product_attributes = product_attributes;
    }

    public String getProduct_size() {
        return product_size;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public String getProduct_size_price() {
        return product_size_price;
    }

    public void setProduct_size_price(String product_size_price) {
        this.product_size_price = product_size_price;
    }

    public String getProduct_discount_date() {
        return product_discount_date;
    }

    public void setProduct_discount_date(String product_discount_date) {
        this.product_discount_date = product_discount_date;
    }
}
