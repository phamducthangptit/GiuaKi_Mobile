package com.example.midterm.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.midterm.R;
import com.example.midterm.data.model.LopTinChiTheoGV;
import com.example.midterm.view.DanhSachMonHocGV;
import com.example.midterm.view.DanhSachSinhVienLTC;

import java.util.List;

public class CustomAdapterLTC extends ArrayAdapter {
    Context context;
    int resource;
    List<LopTinChiTheoGV> data;

    public CustomAdapterLTC(@NonNull Context context, int resource, @NonNull List<LopTinChiTheoGV> data) {
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
        TextView tvNhom = convertView.findViewById(R.id.tvNhom);
        ImageButton imbtnChinhSua = convertView.findViewById(R.id.imbtnChinhSua);
        LopTinChiTheoGV ltc = data.get(position);
        tvMaMH.setText(ltc.getMaMH());
        tvTenMH.setText(ltc.getTenMH());
        tvNhom.setText(ltc.getNhom() + "");
        imbtnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachSinhVienLTC.class);
                intent.putExtra("LTC", ltc);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
