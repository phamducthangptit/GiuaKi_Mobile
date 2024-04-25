package com.example.midterm.adapter;

import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.api.ApiService;
import com.example.midterm.data.model.Nganh;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterNganh extends ArrayAdapter {
    Context context;
    int resource;
    List<Nganh> data;
    private int index = 0;
    public CustomAdapterNganh(@NonNull Context context, int resource, @NonNull List<Nganh> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null);;
        TextView tvTenNganh = convertView.findViewById(R.id.tvTenNganh);
        ImageButton imgBtnChinhSua = convertView.findViewById(R.id.imbtnChinhSua);
        ImageButton imbtnXoa = convertView.findViewById(R.id.imbtnXoa);
        Nganh nganh = data.get(position);
        tvTenNganh.setText(nganh.getTenNganh());
        imgBtnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                openDialogSuaNganh(nganh);
            }
        });
        imbtnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                openDialogXoaNganh(nganh);
            }
        });
        return convertView;
    }

    private void openDialogXoaNganh(Nganh nganh) {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_xoa_nganh);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        Button btnOk = dialog.findViewById(R.id.btnOk);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        TextView tvtenNganh = dialog.findViewById(R.id.tvtenNganh);
        tvtenNganh.setText(nganh.getTenNganh());
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiService.apiService.xoaNganh(nganh.getId()).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            dialog.dismiss();
                            Toast.makeText(context, "Xóa hệ ngành thành công", Toast.LENGTH_LONG).show();
                            data.remove(index);
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Xóa hệ đào tạo thất bại", Toast.LENGTH_LONG).show();
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

    private void openDialogSuaNganh(Nganh nganh) {
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_sua_nganh);

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
        edtTenNganh.setText(nganh.getTenNganh());
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Nganh nganhMoi = new Nganh();
                nganhMoi.setId(nganh.getId());
                nganhMoi.setTenNganh(edtTenNganh.getText().toString());
                ApiService.apiService.capNhatNganh(nganhMoi).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 200){
                            dialog.dismiss();
                            Toast.makeText(context, "Cập nhật ngành thành công", Toast.LENGTH_LONG).show();
                            data.get(index).setTenNganh(nganhMoi.getTenNganh());
                            notifyDataSetChanged();
                        } else {
                            Toast.makeText(context, "Cập nhật ngành thất bại", Toast.LENGTH_LONG).show();
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
}
