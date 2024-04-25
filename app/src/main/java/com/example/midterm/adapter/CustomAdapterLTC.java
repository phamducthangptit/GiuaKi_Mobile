package com.example.midterm.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.LopTinChi;
import com.example.midterm.data.model.MonHoc;
import com.example.midterm.view.DanhSachLop;
import com.example.midterm.view.DanhSachLopTinChi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterLTC extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<LopTinChi> data;

    private List<String> dataMonHoc = new ArrayList<>();
    private List<String> dataLop = new ArrayList<>();
    private List<String> dataGiangVien = new ArrayList<>();
    private int index = 0;
    private int checkSuaLTC = 0, checkXoaLTC = 0;
    private ArrayAdapter adapter_spLocTheo, adapter_spDkLoc, adapter_spMonHoc, adapter_spLop, adapter_spGiangVien;;
    public CustomAdapterLTC(@NonNull Context context, int resource, @NonNull List<LopTinChi> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null); // nap giao dien
        TextView tvTenMH = convertView.findViewById(R.id.tvTenMH);
        TextView tvNienKhoa = convertView.findViewById(R.id.tvNienKhoa);
        TextView tvNhom = convertView.findViewById(R.id.tvNhom);
        TextView tvSTC = convertView.findViewById(R.id.tvSTC);
        TextView tvSoLuong = convertView.findViewById(R.id.tvSoLuong);
        ImageButton imbtnXoa = convertView.findViewById(R.id.imbtnXoa);
        ImageButton imbtnChinhSua = convertView.findViewById(R.id.imbtnChinhSua);
        LopTinChi ltc = data.get(position);
        tvTenMH.setText(ltc.getTenMH());
        tvNienKhoa.setText(ltc.getNienKhoa());
        tvNhom.setText(ltc.getNhom() + "");
        tvSTC.setText(ltc.getSoTC() + "");
        tvSoLuong.setText(ltc.getSoSVToiThieu() + "");

        imbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Ma ltc " + ltc.getMaLTC(), Toast.LENGTH_LONG).show();
                openDialogXoaLTC(ltc);
            }
        });

        imbtnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "Ma ltc " + ltc.getMaLTC(), Toast.LENGTH_LONG).show();
                openDialogChinhSuaLTC(ltc);
                index = position;
            }
        });

        return convertView;
    }
    private void openDialogXoaLTC(LopTinChi ltc){
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_xoa_ltc);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        TextView tvTenMH = dialog.findViewById(R.id.tvTenMH);
        TextView tvTenLop = dialog.findViewById(R.id.tvTenLop);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        tvTenMH.setText(ltc.getTenMH());
        tvTenLop.setText(ltc.getTenLop());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkXoaLTC = 0;
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.apiService.xoaLTC(ltc.getMaLTC()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            Toast.makeText(view.getContext(), "Xóa lớp tín chỉ thành công", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                            checkXoaLTC = 1;
                        } else{
                            Toast.makeText(view.getContext(), "Xóa lớp tín chỉ thất bại", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {

                    }
                });

            }
        });
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(checkXoaLTC == 1)
                    ((DanhSachLopTinChi) context).setDuLieuListViewLTCXoa(ltc);
            }
        });
        dialog.show();
    }
    private void openDialogChinhSuaLTC(LopTinChi ltc){
        checkSuaLTC = 0;
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_sua_ltc);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        Spinner spMonHoc = dialog.findViewById(R.id.spMonHoc);
        Spinner spLop = dialog.findViewById(R.id.spLop);
        Spinner spGiangVien = dialog.findViewById(R.id.spGiangVien);
        //set adapter cho cac spinner
        setAdapterSpDialog(spMonHoc, spLop, spGiangVien, ltc.getMaKhoa(), ltc);

        EditText edtNamBatDau = dialog.findViewById(R.id.edtNamBatDau);
        EditText edtNamKetThuc = dialog.findViewById(R.id.edtNamKetThuc);
        EditText edtNhom = dialog.findViewById(R.id.edtNhom);
        EditText edtSoSV = dialog.findViewById(R.id.edtSoSV);
        EditText edtHocKi = dialog.findViewById(R.id.edtHocKi);
        RadioButton rdbMo = dialog.findViewById(R.id.rdbMo);
        RadioButton rdbHuy = dialog.findViewById(R.id.rdbHuy);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnOk = dialog.findViewById(R.id.btnOk);

        //set du lieu
        edtNamBatDau.setText(ltc.getNienKhoa().substring(0, 4));
        edtNamKetThuc.setText(ltc.getNienKhoa().substring(5)); //2022-2022
        edtNhom.setText(ltc.getNhom() + "");
        edtSoSV.setText(ltc.getSoSVToiThieu() + "");
        edtHocKi.setText(ltc.getHocKY() + "");
        rdbMo.setChecked(!ltc.isHuyLop());
        rdbHuy.setChecked(ltc.isHuyLop());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkSuaLTC = 0;
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LopTinChi ltcTmp = new LopTinChi();
                ltcTmp.setMaLTC(ltc.getMaLTC());
                ltcTmp.setTenMH(spMonHoc.getSelectedItem().toString());
                ltcTmp.setTenLop(spLop.getSelectedItem().toString());
                ltcTmp.setTenGV(spGiangVien.getSelectedItem().toString());
                ltcTmp.setNienKhoa(edtNamBatDau.getText() + "-" + edtNamKetThuc.getText());
                ltcTmp.setNhom(Integer.parseInt(edtNhom.getText().toString()));
                ltcTmp.setSoSVToiThieu(Integer.parseInt(edtSoSV.getText().toString()));
                ltcTmp.setHocKY(Integer.parseInt(edtHocKi.getText().toString()));
                ltcTmp.setHuyLop(rdbHuy.isChecked());
//                Toast.makeText(view.getContext(), spMonHoc.getSelectedItem().toString(), Toast.LENGTH_LONG).show();
                ApiService.apiService.danhSachMonHoc().enqueue(new Callback<List<MonHoc>>() {
                    @Override
                    public void onResponse(Call<List<MonHoc>> call, Response<List<MonHoc>> response) {
                        if(response.code() == 200){
                            for(MonHoc mh : response.body()){
                                if(mh.getTenMH().trim().equals(spMonHoc.getSelectedItem().toString().trim())){
                                    ltcTmp.setSoTC(mh.getSoTinChi());
                                }
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<List<MonHoc>> call, Throwable throwable) {

                    }
                });
                ApiService.apiService.capNhatLTC(ltcTmp).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            checkSuaLTC = 1;
                            Toast.makeText(view.getContext(), "Thay đổi thông tin thành công", Toast.LENGTH_LONG).show();
                            data.get(index).setTenMH(ltcTmp.getTenMH());
                            data.get(index).setTenLop(ltcTmp.getTenLop());
                            data.get(index).setTenGV(ltcTmp.getTenGV());
                            data.get(index).setNienKhoa(ltcTmp.getNienKhoa());
                            data.get(index).setNhom(ltcTmp.getNhom());
                            data.get(index).setSoSVToiThieu(ltcTmp.getSoSVToiThieu());
                            data.get(index).setHocKY(ltcTmp.getHocKY());
                            data.get(index).setHuyLop(ltcTmp.isHuyLop());
                            data.get(index).setSoTC(ltcTmp.getSoTC());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(view.getContext(), "Thay đổi thông tin thất bại", Toast.LENGTH_LONG).show();
                        }
                        dialog.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {

                    }
                });

            }
        });
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if(checkSuaLTC == 1)
                    ((DanhSachLopTinChi) context).updateAll();
            }
        });
        dialog.show();
    }

    private void setAdapterSpDialog(Spinner spMonHoc, Spinner spLop, Spinner spGiangVien, String maKhoa, LopTinChi ltc){
        dataLop.clear();
        dataGiangVien.clear();
        dataMonHoc.clear();
        // set dataLop
        ApiService.apiService.danhSachLocThemLTC(2, maKhoa).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code() == 200){
                    dataLop.addAll(response.body());
                    adapter_spLop = new ArrayAdapter(context, R.layout.layout_spinner, dataLop);
                    spLop.setAdapter(adapter_spLop);
                    for(int i = 0; i < dataLop.size(); i++){
                        if(dataLop.get(i).equals(ltc.getTenLop().trim())){
                            spLop.setSelection(i);
                            break;
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        });
        //set dataMonHoc
        ApiService.apiService.danhSachLocThemLTC(1, maKhoa).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code() == 200){
                    dataMonHoc.addAll(response.body());
                    adapter_spMonHoc = new ArrayAdapter(context, R.layout.layout_spinner, dataMonHoc);
                    spMonHoc.setAdapter(adapter_spMonHoc);
                    for(int i = 0; i < dataMonHoc.size(); i++){
                        if(dataMonHoc.get(i).equals(ltc.getTenMH().trim())){
                            spMonHoc.setSelection(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        });
        //set dataGiangVien
        ApiService.apiService.danhSachLocThemLTC(3, maKhoa).enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.code() == 200){
                    dataGiangVien.addAll(response.body());
                    adapter_spGiangVien = new ArrayAdapter(context, R.layout.layout_spinner, dataGiangVien);
                    spGiangVien.setAdapter(adapter_spGiangVien);
                    for(int i = 0; i < dataGiangVien.size(); i++){
                        if(dataGiangVien.get(i).equals(ltc.getTenGV().trim())){
                            spGiangVien.setSelection(i);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable throwable) {

            }
        });
    }
}
