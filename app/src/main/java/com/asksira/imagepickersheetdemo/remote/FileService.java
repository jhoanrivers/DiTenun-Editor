package com.asksira.imagepickersheetdemo.remote;

import com.asksira.imagepickersheetdemo.Model.Kristik;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {
    @Multipart
    @POST("kristik")
    Call<Kristik> kristik(@Part MultipartBody.Part file);

}
