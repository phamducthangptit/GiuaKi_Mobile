package com.example.midterm.adapter;

import android.content.Context;
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
import com.example.midterm.data.model.Lop;

import java.util.List;

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
                Toast.makeText(view.getContext(), "Click xoa " + lop.getMaLop(), Toast.LENGTH_LONG).show();
            }
        });
        imbtnChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), "Click chinh sua " + lop.getMaLop(), Toast.LENGTH_LONG).show();
            }
        });
        return convertView;
    }
}
