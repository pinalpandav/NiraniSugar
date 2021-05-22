package com.niranisugar.android.Models;

public class ImageSliderModel {
    public ImageSliderModel(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageSliderModel() {
    }

    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}