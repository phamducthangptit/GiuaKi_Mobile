package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class Nganh {
    @SerializedName("ID_NGANH")
    private int id;

    @SerializedName("TEN")
    private String tenNganh;

    public Nganh() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }
}
