package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterSinhVienLTC;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.LopTinChiTheoGV;
import com.example.midterm.data.model.SinhVienLTC;
import com.example.midterm.data.model.TTGiangVienAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachSinhVienLTC extends AppCompatActivity {
    private ImageButton imgBtnBack;
    private TextView tvTenMon, tvMaMon, tvNhom;
    private ListView lvSinhVienLTC;
    private List<SinhVienLTC> data = new ArrayList<>();
    CustomAdapterSinhVienLTC adapterSinhVienLTC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien_ltc);
        Init();
        DocDL();
        setEvent();
    }

    public void DocDL() {
        data.clear();
        LopTinChiTheoGV ltc = (LopTinChiTheoGV) getIntent().getSerializableExtra("LTC");
        tvTenMon.setText(ltc.getTenMH().trim() + "");
        tvMaMon.setText(ltc.getMaMH().trim() + "");
        tvNhom.setText(ltc.getNhom() + "");
        ApiService.apiService.danhSachSinhVienLTC(ltc.getMaLTC()).enqueue(new Callback<List<SinhVienLTC>>() {
            @Override
            public void onResponse(Call<List<SinhVienLTC>> call, Response<List<SinhVienLTC>> response) {
                List<SinhVienLTC> sinhVienLTCS = response.body();
                if(!sinhVienLTCS.isEmpty())
                    data.addAll(sinhVienLTCS);
                setAdapterSinhVienLTC();
            }
            @Override
            public void onFailure(Call<List<SinhVienLTC>> call, Throwable throwable) {

            }
        });
    }

    private void setAdapterSinhVienLTC(){
        adapterSinhVienLTC = new CustomAdapterSinhVienLTC(this, R.layout.layout_item_sinhvienltc, data);
        lvSinhVienLTC.setAdapter(adapterSinhVienLTC);
    }
    private void setEvent() {
        imgBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void Init() {
        imgBtnBack = findViewById(R.id.imgBtnBack);
        tvTenMon = findViewById(R.id.tvTenMH);
        tvMaMon = findViewById(R.id.tvMaMH);
        tvNhom = findViewById(R.id.tvNhom);
        lvSinhVienLTC = findViewById(R.id.lvSinhVienLTC);
    }
}