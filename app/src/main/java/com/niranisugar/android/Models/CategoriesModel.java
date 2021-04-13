package com.niranisugar.android.Models;

public class CategoriesModel {

    public int categories_id;
    public String categories_image;
    public String categories_title;
    public int isFeatured;
    public  String categories_photo;
    public String categories_slug;

    public CategoriesModel() {
    }

    public int getCategories_id() {
        return categories_id;
    }

    public void setCategories_id(int categories_id) {
        this.categories_id = categories_id;
    }

    public String getCategories_image() {
        return categories_image;
    }

    public void setCategories_image(String categories_image) {
        this.categories_image = categories_image;
    }

    public String getCategories_title() {
        return categories_title;
    }

    public void setCategories_title(String categories_title) {
        this.categories_title = categories_title;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCategories_photo() {
        return categories_photo;
    }

    public void setCategories_photo(String categories_photo) {
        this.categories_photo = categories_photo;
    }

    public String getCategories_slug() {
        return categories_slug;
    }

    public void setCategories_slug(String categories_slug) {
        this.categories_slug = categories_slug;
    }
}
