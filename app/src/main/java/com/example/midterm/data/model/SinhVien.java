package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class SinhVien {
    @SerializedName("masv")
    private String maSV;

    @SerializedName("ho")
    private String ho;

    @SerializedName("ten")
    private String ten;

    public SinhVien() {
    }

    public SinhVien(String maSV, String ho, String ten) {
        this.maSV = maSV;
        this.ho = ho;
        this.ten = ten;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
