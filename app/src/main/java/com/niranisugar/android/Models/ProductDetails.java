package com.niranisugar.android.Models;

public class ProductDetails {

    int id;
    String sku,product_type,category_id,subcat_id,name,slug,photo,thumbnail,size,size_qty,size_price,color;
    double price;
    String orignal_price;
    String gst,tcs,prv_price,details,stock,policy,views;

    public ProductDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSize_qty() {
        return size_qty;
    }

    public void setSize_qty(String size_qty) {
        this.size_qty = size_qty;
    }

    public String getSize_price() {
        return size_price;
    }

    public void setSize_price(String size_price) {
        this.size_price = size_price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrignal_price() {
        return orignal_price;
    }

    public void setOrignal_price(String orignal_price) {
        this.orignal_price = orignal_price;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getTcs() {
        return tcs;
    }

    public void setTcs(String tcs) {
        this.tcs = tcs;
    }

    public String getPrv_price() {
        return prv_price;
    }

    public void setPrv_price(String prv_price) {
        this.prv_price = prv_price;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPolicy() {
        return policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }
}
