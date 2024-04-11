package com.example.midterm.data.api;


import com.example.midterm.data.model.DiemHocKi;
import com.example.midterm.data.model.DiemSinhVienLTC;
import com.example.midterm.data.model.He;
import com.example.midterm.data.model.Lop;
import com.example.midterm.data.model.LopTinChiTheoGV;
import com.example.midterm.data.model.SinhVien;
import com.example.midterm.data.model.SinhVienLTC;
import com.example.midterm.data.model.TTGiangVienAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
//    IP ip = new IP();
    String baseURL = "http://192.168.1.7:8080/api/";
    Gson gson = new GsonBuilder().create();

    ApiService apiService = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);

    @GET("lop-tin-chi")
    Call<List<LopTinChiTheoGV>> danhSachLTCTheoMaGV(@Query("ma-gv") String maGV);

    @GET("giang-vien/thong-tin-ca-nhan")
    Call<TTGiangVienAPI> thongTinGiangVien(@Query("ma-gv") String maGV);

    @GET("lop-tin-chi/danh-sach-sinh-vien")
    Call<List<SinhVienLTC>> danhSachSinhVienLTC(@Query("ma-ltc") int maLTC);

    @GET("lop-tin-chi/xem-diem")
    Call<DiemSinhVienLTC> diemSinhVien(@Query("ma-ltc") int maLTC, @Query("ma-sv") String maSV);

    @POST("lop-tin-chi/nhap-diem")
    Call<Void> capNhatDiem(@Body DiemSinhVienLTC diemSinhVienLTC);

    @GET("xem-diem/danh-sach-hoc-ki")
    Call<List<String>> danhSachHK(@Query("ma-sinh-vien") String maSV, @Query("nien-khoa") String nienKhoa);

    @GET("xem-diem/danh-sach-nam-hoc")
    Call<List<String>> danhSachNamHoc(@Query("ma-sinh-vien") String maSV);

    @GET("xem-diem/diem-hoc-ki")
    Call<List<DiemHocKi>> diemHocKi(@Query("ma-sinh-vien") String maSV, @Query("nien-khoa") String nienKhoa, @Query("hoc-ki") int hocKi);

    @GET("sinh-vien/thong-tin-ca-nhan")
    Call<SinhVien> thongTinCaNhan(@Query("ma-sv") String maSV);

    @GET("lop/danh-sach-lop")
    Call<List<Lop>> danhSachLopCuaKhoa(@Query("ma-gv") String maGV, @Query("trang-thai") int trangThai);

    @GET("he-dao-tao/danh-sach")
    Call<List<He>> danhSachHeDaoTao();

    @POST("lop/them-lop")
    Call<Void> themLop(@Body Lop lop);
}
