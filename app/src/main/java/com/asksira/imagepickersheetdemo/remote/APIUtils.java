package com.asksira.imagepickersheetdemo.remote;

import java.io.File;

public class APIUtils {

    private APIUtils(){

    }

    public static final String API_URL ="https://www.jtenun.com/api/";

    public static FileService getFileService(){
        return RetrofitClient.getClient(API_URL).create(FileService.class);
    }


}
