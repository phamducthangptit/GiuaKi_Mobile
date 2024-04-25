package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterNganh;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.Nganh;
import com.example.midterm.data.model.TTGiangVienAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachNganh extends AppCompatActivity {
    private ListView lvNganh;
    private ImageButton imgBtnThemNganh;
    private TextView tvTenGiangVien, tvTenKhoa;
    private List<Nganh> data = new ArrayList<>();
    private String maGV = "GV01";
    CustomAdapterNganh adapterNganh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_nganh);
        Init();
        DocDuLieuNganh();
        DocThongTinCaNhan();
        setEvent();
    }

    private void DocThongTinCaNhan() {
        ApiService.apiService.thongTinGiangVien(maGV).enqueue(new Callback<TTGiangVienAPI>() {
            @Override
            public void onResponse(Call<TTGiangVienAPI> call, Response<TTGiangVienAPI> response) {
                if(response.code() == 200){
                    TTGiangVienAPI thongTin = response.body();
                    tvTenGiangVien.setText(thongTin.getHo() + " " + thongTin.getTen());
                    tvTenKhoa.setText(thongTin.getTenKhoa());
                }
            }

            @Override
            public void onFailure(Call<TTGiangVienAPI> call, Throwable throwable) {

            }
        });
    }

    private void DocDuLieuNganh() {
        ApiService.apiService.danhSachNganh().enqueue(new Callback<List<Nganh>>() {
            @Override
            public void onResponse(Call<List<Nganh>> call, Response<List<Nganh>> response) {
                if(response.code() == 200){
                    data.addAll(response.body());
                    setAdapterNganh();
                }
            }

            @Override
            public void onFailure(Call<List<Nganh>> call, Throwable throwable) {

            }
        });
    }
    private void setAdapterNganh(){
        adapterNganh = new CustomAdapterNganh(this, R.layout.layout_item_nganh, data);
        lvNganh.setAdapter(adapterNganh);
    }

    private void setEvent(){
        imgBtnThemNganh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDiaLogThemNganh();
            }
        });
    }
    private void openDiaLogThemNganh(){
        final Dialog dialog = new Dialog(DanhSachNganh.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_them_nganh);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        Button btnOk = dialog.findViewById(R.id.btnOk);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        EditText edtTenNganh = dialog.findViewById(R.id.edtTenNganh);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nganh nganh = new Nganh();
                nganh.setTenNganh(edtTenNganh.getText().toString());
                ApiService.apiService.themMoiNganh(nganh).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(DanhSachNganh.this, "Thêm hệ ngành thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            updateAdapter(nganh);
                        } else {
                            Toast.makeText(DanhSachNganh.this, "Thêm hệ ngành thất bại", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {

                    }
                });
            }
        });
        dialog.setCancelable(true);
        dialog.show();
    }
    private void updateAdapter(Nganh nganh){
        ApiService.apiService.timNganhTheoTen(nganh.getTenNganh()).enqueue(new Callback<Nganh>() {
            @Override
            public void onResponse(Call<Nganh> call, Response<Nganh> response) {
                if(response.code() == 200){
                    nganh.setId(response.body().getId());
                    data.add(nganh);
                    adapterNganh.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Nganh> call, Throwable throwable) {

            }
        });
    }
    private void Init() {
        lvNganh = findViewById(R.id.lvNganh);
        imgBtnThemNganh = findViewById(R.id.imgBtnThemNganh);
        tvTenGiangVien = findViewById(R.id.tvTenGiangVien);
        tvTenKhoa = findViewById(R.id.tvTenKhoa);
    }
}