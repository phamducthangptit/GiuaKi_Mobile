package com.example.midterm.data.model;

import com.google.gson.annotations.SerializedName;

public class DiemHocKi {
    @SerializedName("MAMH")
    private String maMH;

    @SerializedName("TENMH")
    private String tenMH;

    @SerializedName("DIEMTHI")
    private float diemThi;

    @SerializedName("DIEMTK")
    private float diemTK10;

    @SerializedName("SOTINCHI")
    private int soTinChi;

    private float diemTK4;

    private String diemTKC;

    private boolean ketQua;


    public DiemHocKi() {
    }

    public DiemHocKi(String maMH, String tenMH, float diemThi, float diemTK10, int soTinChi, float diemTK4) {
        this.maMH = maMH;
        this.tenMH = tenMH;
        this.diemThi = diemThi;
        this.diemTK10 = diemTK10;
        this.soTinChi = soTinChi;
        this.diemTK4 = diemTK4;
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

    public float getDiemThi() {
        return diemThi;
    }

    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }

    public float getDiemTK10() {
        return diemTK10;
    }

    public void setDiemTK10(float diemTK10) {
        this.diemTK10 = diemTK10;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setSoTinChi(int soTinChi) {
        this.soTinChi = soTinChi;
    }

    public float getDiemTK4() {
        return diemTK4;
    }

    public void setDiemTK4VaDiemTKC(float diemTK10) {
        if(diemTK10 >= 9 && diemTK10 <= 10){
            this.diemTK4 = 4;
            this.diemTKC = "A+";
            this.ketQua = true;
        }

        if (diemTK10 >= 8.5 & diemTK10 < 9){
            this.diemTK4 = (float) 3.7;
            this.diemTKC = "A";
            this.ketQua = true;
        }

        if (diemTK10 >= 8 && diemTK10 < 8.5){
            this.diemTK4 = (float) 3.5;
            this.diemTKC = "B+";
            this.ketQua = true;
        }

        if(diemTK10 >= 7 && diemTK10 < 8){
            this.diemTK4 = (float) 3.0;
            this.diemTKC = "B";
            this.ketQua = true;
        }

        if(diemTK10 >= 6.5 && diemTK10 < 7){
            this.diemTK4 = (float) 2.5;
            this.diemTKC = "C+";
            this.ketQua = true;
        }

        if(diemTK10 >= 5.5 && diemTK10 < 6.5){
            this.diemTK4 = (float) 2.0;
            this.diemTKC = "C";
            this.ketQua = true;
        }

        if(diemTK10 >= 5 && diemTK10 < 5.5){
            this.diemTK4 = (float) 1.5;
            this.diemTKC = "D+";
            this.ketQua = true;
        }

        if(diemTK10 >= 4 && diemTK10 < 5){
            this.diemTK4 = (float) 1.0;
            this.diemTKC = "D";
            this.ketQua = true;
        }
        if(diemTK10 < 4){
            this.diemTK4 = (float) 0;
            this.diemTKC = "F";
            this.ketQua = false;
        }
    }

    public String getDiemTKC() {
        return diemTKC;
    }


    public void setKetQua(boolean ketQua) {
        this.ketQua = ketQua;
    }

    public boolean getKetQua(){
        return ketQua;
    }
}
