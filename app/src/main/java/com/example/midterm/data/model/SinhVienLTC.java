package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class SinhVienLTC {
    @SerializedName("TEN")
    private String ten;
    @SerializedName("HO")
    private String ho;
    @SerializedName("MASV")
    private String maSV;
    @SerializedName("MALOP")
    private String maLop;
    @SerializedName("DIEM")
    private float diem;

    public SinhVienLTC() {
    }

    public SinhVienLTC(String ten, String ho, String maSV, String maLop, float diem) {
        this.ten = ten;
        this.ho = ho;
        this.maSV = maSV;
        this.maLop = maLop;
        this.diem = diem;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHo() {
        return ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public float getDiem() {
        return diem;
    }

    public void setDiem(float diem) {
        this.diem = diem;
    }
}
