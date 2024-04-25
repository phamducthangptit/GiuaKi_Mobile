package com.example.midterm.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.midterm.R;
import com.example.midterm.adapter.CustomAdapterLop;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.He;
import com.example.midterm.data.model.Lop;
import com.example.midterm.data.model.TTGiangVienAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachLop extends AppCompatActivity {
    private List<String> trangThai = new ArrayList<>();
    private Spinner spTrangThai;
    private ArrayAdapter adapter_spTrangThai;
    private  List<Lop> data = new ArrayList<>();

    private  List<Lop> data_all = new ArrayList<>();
    private CustomAdapterLop adapterLop;
    private ListView lvLop;
    private ImageButton imgBtnThemLop;
    private String maKhoa;
    private SearchView searchViewLop;
    private TextView tvTenGiangVien, tvTenKhoa, tvTenKhoa1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_lop);
        Init();
        setDuLieuTrangThaiLop();
        DocDuLieuLop();
        setEnvent();
    }


    private void setEnvent() {
        imgBtnThemLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), maKhoa, Toast.LENGTH_LONG).show();
                // show dialog
                openDialog(maKhoa);
            }
        });
        spTrangThai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                DocDuLieuLop();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        searchViewLop.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                data.clear();
                if(newText.equals("")){
                    for(Lop lop : data_all){
                        if(lop.getTrangThai() == (getTrangThaiSpinner() == 1)){
                            data.add(lop);
                        }
                    }
                    setAdapterLop();
                } else {
                    for(Lop lop : data_all){
                        if(lop.getMaLop().toLowerCase().contains(newText.toLowerCase()) && lop.getTrangThai() == (getTrangThaiSpinner() == 1)){
                            data.add(lop);
                        }
                    }
                }
                setAdapterLop();
                return false;
            }
        });
    }

    private void openDialog(String maKhoa){
        final Dialog dialog = new Dialog(DanhSachLop.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_them_lop);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        EditText edtMaLop = dialog.findViewById(R.id.edtMaLop);
        EditText edtTenLop = dialog.findViewById(R.id.edtTenLop);
        EditText edtNamBatDau = dialog.findViewById(R.id.edtNamBatDau);
        EditText edtNamKetThuc = dialog.findViewById(R.id.edtNamKetThuc);
        Spinner spHeDaoTao = dialog.findViewById(R.id.spHeDaoTao);
        List<He> danhSachHeDaoTao = new ArrayList<>();

        ApiService.apiService.danhSachHeDaoTao().enqueue(new Callback<List<He>>() {
            @Override
            public void onResponse(Call<List<He>> call, Response<List<He>> response) {
                if(response.code() == 200){
                    danhSachHeDaoTao.addAll(response.body());
                    ArrayAdapter adapter_spHeDaoTao = new ArrayAdapter(dialog.getContext(), R.layout.layout_spinner, danhSachHeDaoTao);
                    spHeDaoTao.setAdapter(adapter_spHeDaoTao);
                }
            }

            @Override
            public void onFailure(Call<List<He>> call, Throwable throwable) {

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop lop = new Lop();
                lop.setMaLop(edtMaLop.getText().toString());
                lop.setTenLop(edtTenLop.getText().toString());
                lop.setTrangThai(true);
                lop.setMaKhoa(maKhoa);
                lop.setKhoaHoc(edtNamBatDau.getText().toString() + "-" + edtNamKetThuc.getText().toString());
                String tmp = spHeDaoTao.getSelectedItem().toString();
                for(He he : danhSachHeDaoTao){
                    if(tmp.equals(he.getTenHe())){
                        lop.setIdHe(he.getId());
                    }
                }
                ApiService.apiService.themLop(lop).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            dialog.dismiss();
                            data.add(lop);
                            adapterLop.notifyDataSetChanged();
                            Toast.makeText(DanhSachLop.this, "Thêm lớp thành công", Toast.LENGTH_LONG).show();
                        } else if (response.code() == 400) {
                            Toast.makeText(DanhSachLop.this, "Thêm lớp thất bại", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
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

    private void setDuLieuTrangThaiLop() {
        trangThai.add("Mở");
        trangThai.add("Đóng");
        adapter_spTrangThai = new ArrayAdapter(DanhSachLop.this, R.layout.layout_spinner, trangThai);
        spTrangThai.setAdapter(adapter_spTrangThai);
    }

    public void DocDuLieuLop(){
        String maGV = "GV01";
        ApiService.apiService.thongTinGiangVien(maGV).enqueue(new Callback<TTGiangVienAPI>() {
            @Override
            public void onResponse(Call<TTGiangVienAPI> call, Response<TTGiangVienAPI> response) {
                if(response.code() == 200){
                    TTGiangVienAPI thongTin = response.body();
                    tvTenGiangVien.setText(thongTin .getHo() + " " + thongTin.getTen());
                    tvTenKhoa.setText(thongTin.getTenKhoa());
                    maKhoa = thongTin.getMaKhoa();
                    tvTenKhoa1.setText(thongTin.getTenKhoa());
                }
            }

            @Override
            public void onFailure(Call<TTGiangVienAPI> call, Throwable throwable) {

            }
        });
        ApiService.apiService.danhSachLopCuaKhoa(maGV, getTrangThaiSpinner()).enqueue(new Callback<List<Lop>>() {
            @Override
            public void onResponse(Call<List<Lop>> call, Response<List<Lop>> response) {
                if(response.code() == 200){
                    data.clear();
                    data_all.clear();
                    List<Lop> listLop = response.body();
                    for(Lop tmp : listLop)
                        tmp.setTrangThai(getTrangThaiSpinner() == 1);
                    data.addAll(listLop);
                    data_all.addAll(data);
                    setAdapterLop();
                }
            }
            @Override
            public void onFailure(Call<List<Lop>> call, Throwable throwable) {

            }
        });
    }

    private void setAdapterLop(){
        adapterLop = new CustomAdapterLop(this, R.layout.layout_item_lop, data);
        lvLop.setAdapter(adapterLop);
    }

    public int getTrangThaiSpinner(){
        if(spTrangThai.getSelectedItem().toString().equals("Mở"))
            return 1;
        return 0;
    }


    private void Init() {
        spTrangThai = findViewById(R.id.spTrangThai);
        lvLop = findViewById(R.id.lvLop);
        imgBtnThemLop = findViewById(R.id.imgBtnThemLop);
        searchViewLop = findViewById(R.id.searchViewLop);
        tvTenGiangVien = findViewById(R.id.tvTenGiangVien);
        tvTenKhoa = findViewById(R.id.tvTenKhoa);
        tvTenKhoa1 = findViewById(R.id.tvTenKhoa1);
    }
}