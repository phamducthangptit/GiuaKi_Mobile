package com.example.midterm.data.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LopTinChiTheoGV implements Serializable {
    @SerializedName("HOCKY")
    private int hocKy;
    @SerializedName("MAGV")
    private String maGV;
    @SerializedName("MALOP")
    private String maLop;
    @SerializedName("MAMH")
    private String maMH;
    @SerializedName("TENMH")
    private String tenMH;
    @SerializedName("MAKHOA")
    private String maKhoa;
    @SerializedName("MALTC")
    private int maLTC;
    @SerializedName("NIENKHOA")
    private String nienKhoa;
    @SerializedName("NHOM")
    private int nhom;

    public LopTinChiTheoGV() {
    }

    public LopTinChiTheoGV(int hocKy, String maGV, String maLop, String maMH, String tenMH, String maKhoa, int maLTC, String nienKhoa, int nhom) {
        this.hocKy = hocKy;
        this.maGV = maGV;
        this.maLop = maLop;
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.maKhoa = maKhoa;
        this.maLTC = maLTC;
        this.nienKhoa = nienKhoa;
        this.nhom = nhom;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getHocKy() {
        return hocKy;
    }

    public void setHocKy(int hocKy) {
        this.hocKy = hocKy;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(int maLTC) {
        this.maLTC = maLTC;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }
}
