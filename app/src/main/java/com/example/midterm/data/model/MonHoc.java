package com.example.midterm.data.model;

public class MonHoc {
    private String maMH;
    private String tenMH;
    private int soTietLT;
    private int soTietTH;
    private int soTinChi;

    public MonHoc() {
    }

    public MonHoc(String maMH, String tenMH, int soTietLT, int soTietTH, int soTinChi) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.soTietLT = soTietLT;
        this.soTietTH = soTietTH;
        this.soTinChi = soTinChi;
    }

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public int getSoTietLT() {
        return soTietLT;
    }

    public void setSoTietLT(int soTietLT) {
        this.soTietLT = soTietLT;
    }

    public int getSoTietTH() {
        return soTietTH;
    }

    public void setSoTietTH(int soTietTH) {
        this.soTietTH = soTietTH;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }
}
