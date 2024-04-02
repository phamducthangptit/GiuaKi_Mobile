package com.example.midterm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.model.DiemHocKi;

import java.util.List;

public class CustomAdapterDiemHocKi extends ArrayAdapter {
    Context context;
    int resource;
    List<DiemHocKi> data;
    public CustomAdapterDiemHocKi(@NonNull Context context, int resource, @NonNull List<DiemHocKi> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null); // nap giao dien
        TextView tvMaMH = convertView.findViewById(R.id.tvMaMH);
        TextView tvTenMH = convertView.findViewById(R.id.tvTenMH);
        TextView tvSTC = convertView.findViewById(R.id.tvSTC);
        TextView tvDiemThi = convertView.findViewById(R.id.tvDiemThi);
        TextView tvDiemTK10 = convertView.findViewById(R.id.tvDiemTK10);
        TextView tvDiemTK4 = convertView.findViewById(R.id.tvDiemTK4);
        TextView tvDiemTKC = convertView.findViewById(R.id.tvDiemTKC);
        ImageView imgTrangThai = convertView.findViewById(R.id.imgTrangThai);
        DiemHocKi diemHocKi = data.get(position);
        diemHocKi.setDiemTK4VaDiemTKC(diemHocKi.getDiemTK10());
        tvMaMH.setText(diemHocKi.getMaMH());
        tvTenMH.setText(diemHocKi.getTenMH());
        tvSTC.setText(diemHocKi.getSoTinChi() + "");
        tvDiemThi.setText(diemHocKi.getDiemThi() + "");
        tvDiemTK10.setText(diemHocKi.getDiemTK10() + "");
        tvDiemTK4.setText(diemHocKi.getDiemTK4() + "");
        tvDiemTKC.setText(diemHocKi.getDiemTKC());
        if(diemHocKi.getKetQua()){
            imgTrangThai.setImageResource(R.drawable.done_20px);
        } else {
            imgTrangThai.setImageResource(R.drawable.close_20px);
        }
        return convertView;
    }
}
