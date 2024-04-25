package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class LopTinChi {
    @SerializedName("MALTC")
    private int maLTC;
    @SerializedName("MAMH")
    private String maMH;
    @SerializedName("TENMH")
    private String tenMH;

    @SerializedName("SOTINCHI")
    private int soTC;

    @SerializedName("MAGV")
    private String maGV;

    @SerializedName("TENGV")
    private String tenGV;

    @SerializedName("MALOP")
    private String maLop;

    @SerializedName("TENLOP")
    private String tenLop;

    @SerializedName("NHOM")
    private int nhom;

    @SerializedName("HOCKY")
    private int hocKY;

    @SerializedName("NIENKHOA")
    private String nienKhoa;

    @SerializedName("SOSVTOITHIEU")
    private int soSVToiThieu;

    @SerializedName("HUYLOP")
    private boolean huyLop;


    @SerializedName("MAKHOA")
    private String maKhoa;

    public LopTinChi() {
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

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getMaLop() {
        return maLop;
    }

    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public int getNhom() {
        return nhom;
    }

    public void setNhom(int nhom) {
        this.nhom = nhom;
    }

    public int getHocKY() {
        return hocKY;
    }

    public void setHocKY(int hocKY) {
        this.hocKY = hocKY;
    }

    public String getNienKhoa() {
        return nienKhoa;
    }

    public void setNienKhoa(String nienKhoa) {
        this.nienKhoa = nienKhoa;
    }

    public int getSoSVToiThieu() {
        return soSVToiThieu;
    }

    public void setSoSVToiThieu(int soSVToiThieu) {
        this.soSVToiThieu = soSVToiThieu;
    }

    public boolean isHuyLop() {
        return huyLop;
    }

    public void setHuyLop(boolean huyLop) {
        this.huyLop = huyLop;
    }


    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public int getMaLTC() {
        return maLTC;
    }

    public void setMaLTC(int maLTC) {
        this.maLTC = maLTC;
    }
}
