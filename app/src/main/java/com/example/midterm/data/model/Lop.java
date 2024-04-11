package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class Lop {
    @SerializedName("tenLop")
    private String tenLop;

    @SerializedName("maLop")
    private String maLop;

    @SerializedName("khoaHoc")
    private String khoaHoc;
    @SerializedName("trangThai")
    private boolean trangThai;
    @SerializedName("maKhoa")
    private String maKhoa;

    @SerializedName("idHe")
    private int idHe;

    public Lop() {
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getKhoaHoc() {
        return khoaHoc;
    }

    public void setKhoaHoc(String khoaHoc) {
        this.khoaHoc = khoaHoc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public void setMaKhoa(String maKhoa) {
        this.maKhoa = maKhoa;
    }

    public int getIdHe() {
        return idHe;
    }

    public void setIdHe(int idHe) {
        this.idHe = idHe;
    }
}
