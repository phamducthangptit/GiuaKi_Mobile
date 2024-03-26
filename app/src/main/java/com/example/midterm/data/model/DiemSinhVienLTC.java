package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class DiemSinhVienLTC {
    @SerializedName("MALTC")
    private int maLTC;
    @SerializedName("MASV")
    private String maSV;
    @SerializedName("DIEM_CC")
    private int diemCC;
    @SerializedName("DIEM_GK")
    private float diemGK;
    @SerializedName("DIEM_CK")
    private float diemCK;

    public DiemSinhVienLTC() {
    }

    public DiemSinhVienLTC(int maLTC, String maSV, int diemCC, float diemGK, float diemCK) {
        this.maLTC = maLTC;
        this.maSV = maSV;
        this.diemCC = diemCC;
        this.diemGK = diemGK;
        this.diemCK = diemCK;
    }

    public int getDiemCC() {
        return diemCC;
    }

    public void setDiemCC(int diemCC) {
        this.diemCC = diemCC;
    }

    public float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    public float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }


}
