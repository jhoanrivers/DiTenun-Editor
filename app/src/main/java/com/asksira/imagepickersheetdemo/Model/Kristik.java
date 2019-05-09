package com.asksira.imagepickersheetdemo.Model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Kristik extends RealmObject {

    @PrimaryKey
    private  int id;
    @SerializedName("name")
    private String name;
    @SerializedName("bytes")
    private byte[] bytes;

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

}
