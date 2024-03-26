package com.example.midterm.data.api;

import static java.net.InetAddress.getLocalHost;

import com.example.midterm.data.model.DiemSinhVienLTC;
import com.example.midterm.data.model.LopTinChiTheoGV;
import com.example.midterm.data.model.SinhVienLTC;
import com.example.midterm.data.model.TTGiangVienAPI;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
    String baseURL = "http://10.251.2.73:8080/api/";
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
}
