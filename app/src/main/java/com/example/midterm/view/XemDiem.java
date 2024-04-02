package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterDiemHocKi;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.DiemHocKi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemDiem extends AppCompatActivity {
    private Spinner spNamHoc, spHocKi;
    private ArrayAdapter adapter_namHoc, adapter_hocKi;
    private int code;

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
        DocDuLieuSpinner();
    }

    private void DocDuLieuDiem(String maSV) {
        data.clear();
        ApiService.apiService.diemHocKi(maSV, spNamHoc.getSelectedItem().toString(), Integer.parseInt(spHocKi.getSelectedItem().toString())).enqueue(new Callback<List<DiemHocKi>>() {
            @Override
            public void onResponse(Call<List<DiemHocKi>> call, Response<List<DiemHocKi>> response) {
                if(response.code() == 200){
                    data.addAll(response.body());
                    setAdapterDiemHocKi();
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

    private void Init() {
        spNamHoc = findViewById(R.id.spNamHoc);
        spHocKi = findViewById(R.id.spHocKi);
        lvDiem = findViewById(R.id.lvDiem);
    }
}