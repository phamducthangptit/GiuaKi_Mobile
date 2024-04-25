package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class He {
    @SerializedName("ID_HE")
    private int id;

    @SerializedName("TEN_HE")
    private String tenHe;

    public He() {
    }

    public He(int id, String tenHe) {
        this.id = id;
        this.tenHe = tenHe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenHe() {
        return tenHe;
    }

    public void setTenHe(String tenHe) {
        this.tenHe = tenHe;
    }

    @Override
    public String toString() {
        return tenHe;
    }
}
