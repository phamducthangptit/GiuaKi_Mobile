package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterLTC;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.LopTinChiTheoGV;
import com.example.midterm.data.model.TTGiangVienAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachMonHocGV extends AppCompatActivity {
    private ListView lvMonHoc;
    private ImageButton imgBtnBack, imgBtnLogout;
    private TextView tvHoten, tvTenKhoa;
    private List<LopTinChiTheoGV> data = new ArrayList<>();
    CustomAdapterLTC adapterLTC;
    public static  TTGiangVienAPI thongTinGV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_mon_hoc_gv);
        Init();
        DocDL();
    }

    private void DocDL() {
        String maGV = "GV01";
        //thông tin giảng viên
        ApiService.apiService.thongTinGiangVien(maGV).enqueue(new Callback<TTGiangVienAPI>() {
            @Override
            public void onResponse(Call<TTGiangVienAPI> call, Response<TTGiangVienAPI> response) {
                thongTinGV = response.body();
                tvHoten.setText(thongTinGV.getHo() + " " + thongTinGV.getTen());
                tvTenKhoa.setText(thongTinGV.getTenKhoa());
            }

            @Override
            public void onFailure(Call<TTGiangVienAPI> call, Throwable throwable) {
                Toast.makeText(DanhSachMonHocGV.this, "Call API Error TTGV", Toast.LENGTH_LONG).show();
            }
        });
        // danh sách ltc

        ApiService.apiService.danhSachLTCTheoMaGV(maGV).enqueue(new Callback<List<LopTinChiTheoGV>>() {
            @Override
            public void onResponse(Call<List<LopTinChiTheoGV>>call, Response<List<LopTinChiTheoGV>> response) {
                List<LopTinChiTheoGV> listLTC = response.body();
                if(!listLTC.isEmpty()){
                    data.addAll(listLTC);
                }
                setAdapterLvMonHoc();
            }

            @Override
            public void onFailure(Call<List<LopTinChiTheoGV>> call, Throwable throwable) {
            }
        });
    }

    private void setAdapterLvMonHoc() {
        adapterLTC = new CustomAdapterLTC(this, R.layout.layout_item_monhoc, data);
        lvMonHoc.setAdapter(adapterLTC);
    }

    private void Init() {
        lvMonHoc = findViewById(R.id.lvMonHoc);
        imgBtnBack = findViewById(R.id.imgBtnBack);
        imgBtnLogout = findViewById(R.id.imgBtnLogout);
        tvHoten = findViewById(R.id.tvTenGiangVien);
        tvTenKhoa = findViewById(R.id.tvTenKhoa);
    }
}