package com.niranisugar.android.Models;

public class CartModel {

   public String itemName,itemSubName,quantity,image;

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemSubName() {
        return itemSubName;
    }

    public void setItemSubName(String itemSubName) {
        this.itemSubName = itemSubName;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CartModel(String itemName, String itemSubName, String quantity, String image) {
        this.itemName = itemName;
        this.itemSubName = itemSubName;
        this.quantity = quantity;
        this.image = image;
    }
}
