package com.asksira.imagepickersheetdemo.network;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface TenunNetworkInterface {


    @Multipart
    @POST("api/kristik")
    Call<ResponseBody> kristikEditor(
            @Header("Authorization") String token,
            @Part("square_size") int squareSize,
            @Part("color_amount") int colorAmount,
            @Part MultipartBody.Part file
    );


}
