package com.niranisugar.android.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class AddOrder implements Parcelable {

    public String productIDs,productQtys,ProductPrice,Address,TotalAmount;
    public int userID,addressID;

    public AddOrder() {
    }

    protected AddOrder(Parcel in) {
        productIDs = in.readString();
        productQtys = in.readString();
        ProductPrice = in.readString();
        userID = in.readInt();
        addressID = in.readInt();
        Address = in.readString();
        TotalAmount = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(productIDs);
        dest.writeString(productQtys);
        dest.writeString(ProductPrice);
        dest.writeInt(userID);
        dest.writeInt(addressID);
        dest.writeString(Address);
        dest.writeString(TotalAmount);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AddOrder> CREATOR = new Creator<AddOrder>() {
        @Override
        public AddOrder createFromParcel(Parcel in) {
            return new AddOrder(in);
        }

        @Override
        public AddOrder[] newArray(int size) {
            return new AddOrder[size];
        }
    };

    public String getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        TotalAmount = totalAmount;
    }

    public String getProductIDs() {
        return productIDs;
    }

    public void setProductIDs(String productIDs) {
        this.productIDs = productIDs;
    }

    public String getProductQtys() {
        return productQtys;
    }

    public void setProductQtys(String productQtys) {
        this.productQtys = productQtys;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
