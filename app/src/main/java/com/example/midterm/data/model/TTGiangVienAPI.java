package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TTGiangVienAPI implements Serializable {
    @SerializedName("HO")
    private String ho;
    @SerializedName("TEN")
    private String ten;
    @SerializedName("MAKHOA")
    private String maKhoa;
    @SerializedName("TENKHOA")
    private String tenKhoa;

    public TTGiangVienAPI() {
    }

    public TTGiangVienAPI(String ho, String ten, String tenKhoa) {
        this.ho = ho;
        this.ten = ten;
        this.tenKhoa = tenKhoa;
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

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }
}
