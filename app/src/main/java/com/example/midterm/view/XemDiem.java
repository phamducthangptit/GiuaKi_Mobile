package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterDiemHocKi;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.DiemHocKi;
import com.example.midterm.data.model.SinhVien;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemDiem extends AppCompatActivity {
    private Spinner spNamHoc, spHocKi;
    private TextView tvSTCDki, tvSTCDat, tvSTCKhongDat, tvDiemTk10, tvDiemTk4;
    private TextView tvHoTenSv, tvMssv;
    private ArrayAdapter adapter_namHoc, adapter_hocKi;

    private List<String> danhSachNamHoc = new ArrayList<>();
    private List<String> danhSachHocKi = new ArrayList<>();

    private List<DiemHocKi> data = new ArrayList<>();

    private CustomAdapterDiemHocKi adapterDiemHocKi;

    private ListView lvDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem);
            Init();
            DocThongTinCaNhan();
            DocDuLieuSpinner();
        }

        private void DocThongTinCaNhan() {
            String maSV = "N15DCCN001";
            ApiService.apiService.thongTinCaNhan(maSV).enqueue(new Callback<SinhVien>() {
                @Override
                public void onResponse(Call<SinhVien> call, Response<SinhVien> response) {
                    if(response.code() == 200){
                        SinhVien sv = response.body();
                        tvHoTenSv.setText(sv.getHo() + " " + sv.getTen());
                        tvMssv.setText(sv.getMaSV());

                    }
                }

                @Override
            public void onFailure(Call<SinhVien> call, Throwable throwable) {

            }
        });
    }

    private void DocDuLieuDiem(String maSV) {
        data.clear();
        ApiService.apiService.diemHocKi(maSV, spNamHoc.getSelectedItem().toString(), Integer.parseInt(spHocKi.getSelectedItem().toString())).enqueue(new Callback<List<DiemHocKi>>() {
            @Override
            public void onResponse(Call<List<DiemHocKi>> call, Response<List<DiemHocKi>> response) {
                if(response.code() == 200){
                    data.addAll(response.body());
                    setAdapterDiemHocKi();
                    setThongKe(data);
                }
            }

            @Override
            public void onFailure(Call<List<DiemHocKi>> call, Throwable throwable) {

            }
        });
    }

    private void setAdapterDiemHocKi(){
        adapterDiemHocKi = new CustomAdapterDiemHocKi(this, R.layout.layout_item_diemhocki, data);
        lvDiem.setAdapter(adapterDiemHocKi);
    }

    private void DocDuLieuSpinner() {
        String maSV = "N15DCCN001";
        // Gan du lieu cho nam hoc
        ApiService.apiService.danhSachNamHoc(maSV).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                danhSachNamHoc.clear();
                List<String> data = response.body();
                danhSachNamHoc.addAll(data);
                adapter_namHoc = new ArrayAdapter(XemDiem.this, R.layout.layout_spinner, danhSachNamHoc);
                spNamHoc.setAdapter(adapter_namHoc);
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        });
        spNamHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ApiService.apiService.danhSachHK(maSV, danhSachNamHoc.get(i)).enqueue(new Callback<List<String>>() {
                    @Override
                    public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                        danhSachHocKi.clear();
                        List<String> data = response.body();
                        danhSachHocKi.addAll(data);
                        adapter_hocKi = new ArrayAdapter(XemDiem.this, R.layout.layout_spinner, danhSachHocKi);
                        spHocKi.setAdapter(adapter_hocKi);
                        DocDuLieuDiem(maSV);
                    }

                    @Override
                    public void onFailure(Call<List<String>> call, Throwable throwable) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spHocKi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DocDuLieuDiem(maSV);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void setThongKe(List<DiemHocKi> data){
        int tongSTC = 0, stcDat = 0, stcKhongDat = 0;
        float diemTk10 = 0, diemTk4 = 0, tongDiem10 = 0, tongDiem4 = 0;
        for(DiemHocKi dhk : data){
            dhk.setDiemTK4VaDiemTKC(dhk.getDiemTK10());
            tongSTC += dhk.getSoTinChi();
            if(dhk.getKetQua()){
                stcDat += dhk.getSoTinChi();
            }
            tongDiem10 += dhk.getDiemTK10();
            tongDiem4 += dhk.getDiemTK4();
        }
        stcKhongDat = tongSTC - stcDat;
        diemTk10 = (float) (Math.round(tongDiem10 / data.size() * 100.0) / 100.0);
        diemTk4 = (float) (Math.round(tongDiem4 /data.size() * 100.0) / 100.0);

        tvSTCDki.setText(tongSTC + "");
        tvSTCDat.setText(stcDat + "");
        tvSTCKhongDat.setText(stcKhongDat + "");
        tvDiemTk10.setText(diemTk10 + "");
        tvDiemTk4.setText(diemTk4 + "");
    }

    private void Init() {
        data.clear();
        tvHoTenSv = findViewById(R.id.tvTenSV);
        tvMssv = findViewById(R.id.tvMSSV);
        spNamHoc = findViewById(R.id.spNamHoc);
        spHocKi = findViewById(R.id.spHocKi);
        lvDiem = findViewById(R.id.lvDiem);
        tvSTCDki = findViewById(R.id.tvSTCDki);
        tvSTCDat = findViewById(R.id.tvSTCDat);
        tvSTCKhongDat = findViewById(R.id.tvSTCKhongDat);
        tvDiemTk10 = findViewById(R.id.tvDiemTk10);
        tvDiemTk4 = findViewById(R.id.tvDiemTk4);
    }
}