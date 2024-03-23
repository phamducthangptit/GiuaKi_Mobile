package com.example.midterm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.model.SinhVienLTC;

import java.util.List;

public class CustomAdapterSinhVienLTC extends ArrayAdapter {
    Context context;
    int resource;
    List<SinhVienLTC> data;
    public CustomAdapterSinhVienLTC(@NonNull Context context, int resource, @NonNull List<SinhVienLTC> data) {
        super(context, resource, data);
        this.context = context;
        this.resource = resource;
        this.data = data;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(resource, null); // nap giao dien
        TextView tvMaSv = convertView.findViewById(R.id.tvMaSV);
        TextView tvTen = convertView.findViewById(R.id.tvTenSV);
        TextView tvDiem = convertView.findViewById(R.id.tvDiem);
        ImageButton imgbtnChinhSuaDiem = convertView.findViewById(R.id.imbtnChinhSuaDiem);
        SinhVienLTC sv = data.get(position);
        tvMaSv.setText(sv.getMaSV());
        tvTen.setText(sv.getHo() + " " + sv.getTen());
        tvDiem.setText(sv.getDiem() + "");
        return convertView;
    }
}
