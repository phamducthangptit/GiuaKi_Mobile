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
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.He;
import com.example.midterm.data.model.Lop;
import com.example.midterm.view.DanhSachLop;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterLop extends ArrayAdapter {
    Context context;
    int resource;
    List<Lop> data;
    public CustomAdapterLop(@NonNull Context context, int resource, @NonNull List<Lop> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null); // nap giao dien
        TextView tvMaLop = convertView.findViewById(R.id.tvMaLop);
        TextView tvTenLop = convertView.findViewById(R.id.tvTenLop);
        TextView tvKhoaHoc = convertView.findViewById(R.id.tvKhoaHoc);
        ImageButton imbtnChinhSua = convertView.findViewById(R.id.imbtnChinhSua);
        ImageButton imbtnXoa = convertView.findViewById(R.id.imbtnXoa);
        Lop lop = data.get(position);
        tvMaLop.setText(lop.getMaLop());
        tvTenLop.setText(lop.getTenLop());
        tvKhoaHoc.setText(lop.getKhoaHoc());
        imbtnChinhSua.setImageResource(R.drawable.edit);
        imbtnXoa.setImageResource(R.drawable.delete_24px);
        imbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogXoaLop(lop);
            }
        });
        imbtnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogSuaLop(lop);
            }
        });
        return convertView;
    }

    private void openDialogXoaLop(Lop lop) {
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_xoa_lop);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);
        TextView tvTenLop = dialog.findViewById(R.id.tvTenLop);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        tvTenLop.setText(lop.getTenLop());

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), lop.getMaLop(), Toast.LENGTH_LONG).show();
                ApiService.apiService.xoaLop(lop.getMaLop()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            dialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Xóa lớp thất bại!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable throwable) {

                    }
                });

            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(true);
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((DanhSachLop) context).DocDuLieuLop();
            }
        });
        dialog.show();
    }

    private void openDialogSuaLop(Lop lop){
        final Dialog dialog = new Dialog(this.context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_sua_lop);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        EditText edtMaLop = dialog.findViewById(R.id.edtMaLop);
        EditText edtTenLop = dialog.findViewById(R.id.edtTenLop);
        EditText edtNamBatDau = dialog.findViewById(R.id.edtNamBatDau);
        EditText edtNamKetThuc = dialog.findViewById(R.id.edtNamKetThuc);
        RadioButton rdbDong = dialog.findViewById(R.id.rdbDong);
        RadioButton rdbMo = dialog.findViewById(R.id.rdbMo);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnOk = dialog.findViewById(R.id.btnOk);

        Spinner spHeDaoTao = dialog.findViewById(R.id.spHeDaoTao);
        List<He> danhSachHeDaoTao = new ArrayList<>();

        ApiService.apiService.danhSachHeDaoTao().enqueue(new Callback<List<He>>() {
            @Override
            public void onResponse(Call<List<He>> call, Response<List<He>> response) {
                if(response.code() == 200){
                    danhSachHeDaoTao.addAll(response.body());
                    ArrayAdapter adapter_spHeDaoTao = new ArrayAdapter(dialog.getContext(), R.layout.layout_spinner, danhSachHeDaoTao);
                    spHeDaoTao.setAdapter(adapter_spHeDaoTao);
                    int index = 0;
                    for(int i = 0; i < danhSachHeDaoTao.size(); i++){
                        if(danhSachHeDaoTao.get(i).getId() == lop.getIdHe()){
                            index = i;
                            break;
                        }
                    }
                    spHeDaoTao.setSelection(index);
                }
            }

            @Override
            public void onFailure(Call<List<He>> call, Throwable throwable) {

            }
        });

        edtMaLop.setText(lop.getMaLop());
        edtTenLop.setText(lop.getTenLop());
        edtNamBatDau.setText(lop.getKhoaHoc().substring(0, 4));
        edtNamKetThuc.setText(lop.getKhoaHoc().substring(5));
        if(lop.getTrangThai()){ // lop dang mo
            rdbMo.setChecked(true);
            rdbDong.setChecked(false);
        } else { // lop dong
            rdbMo.setChecked(false);
            rdbDong.setChecked(true);
        }

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Lop tmp = new Lop();
                tmp.setMaLop(edtMaLop.getText().toString());
                tmp.setTenLop(edtTenLop.getText().toString());
                tmp.setKhoaHoc(edtNamBatDau.getText().toString() + "-" + edtNamKetThuc.getText().toString());
                tmp.setTrangThai(rdbMo.isChecked() ? true : false);
                for(He he : danhSachHeDaoTao){
                    if(he.getTenHe().equals(spHeDaoTao.getSelectedItem().toString())){
                        tmp.setIdHe(he.getId());
                        break;
                    }
                }
//                Toast.makeText(view.getContext(), "Click xoa " + tmp.getIdHe(), Toast.LENGTH_LONG).show();
                ApiService.apiService.capNhatLop(tmp).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
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
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                ((DanhSachLop) context).DocDuLieuLop();
            }
        });
        dialog.show();
    }
}
