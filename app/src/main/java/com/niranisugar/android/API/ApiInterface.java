package com.niranisugar.android.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @Multipart
    @POST("login")
    Call<String> Login(@Part("email") String email,@Part("password") String password);

    @Multipart
    @POST("newRegister")
    Call<String> Register(@Part("name") String name,
                          @Part("email") String email,
                          @Part("phone") String phoneno,
                          @Part("address") String address,
                          @Part("password") String password,
                          @Part("password_confirmation") String confirmationpassword);

    @POST("profile")
    Call<String> Profile(@Header("apiToken") String access_token);

    @GET("category")
    Call<String> GetCategory();

    @GET("slider")
    Call<String> GetSliderImages();

    @GET("product/feature")
    Call<String> GetFeatures();

    @Multipart
    @POST("productbycategory")
    Call<String> GetProductByCategory(@Part("id") int cat_id);

    @Multipart
    @POST("product")
    Call<String> GetProductDetails(@Part("id") int product_id);

    @GET("notification")
    Call<String> GetNotification(@Header("apiToken") String token);

    @GET("address")
    Call<String> GetAddress(@Header("apiToken") String token);

    @Multipart
    @POST("address/add")
    Call<String> AddAddress(@Header("apiToken") String token,
                            @Part("name") String name,
                            @Part("address") String address,
                            @Part("city") String city,
                            @Part("state") String state,
                            @Part("country") String country,
                            @Part("postal_code") String postalcode,
                            @Part("phone_no") String phoneno,
                            @Part("landmark") String landmark);




}