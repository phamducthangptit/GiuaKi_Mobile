package com.example.midterm.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
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
import com.example.midterm.data.model.DiemSinhVienLTC;
import com.example.midterm.data.model.SinhVienLTC;
import com.example.midterm.view.DanhSachSinhVienLTC;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomAdapterSinhVienLTC extends ArrayAdapter {
    Context context;
    int resource;
    List<SinhVienLTC> data;
    public DiemSinhVienLTC diemSinhVienLTC;
    public static int index;
    public static float diemTK;

    DanhSachSinhVienLTC danhSachSinhVienLTC = new DanhSachSinhVienLTC();
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
        imgbtnChinhSuaDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                index = position;
                String hoTen = sv.getHo() + " " + sv.getTen();
                diemSinhVienLTC = new DiemSinhVienLTC();
                // goi api de lay diem
                ApiService.apiService.diemSinhVien(sv.getMaLTC(), sv.getMaSV()).enqueue(new Callback<DiemSinhVienLTC>() {
                    @Override
                    public void onResponse(Call<DiemSinhVienLTC> call, Response<DiemSinhVienLTC> response) {
                        diemSinhVienLTC = response.body();
                        openDialog(sv.getMaLTC(), sv.getMaSV(), hoTen, diemSinhVienLTC);
                    }

                    @Override
                    public void onFailure(Call<DiemSinhVienLTC> call, Throwable throwable) {

                    }
                });

            }
        });
        return convertView;
    }
    private void openDialog(int maLTC, String maSV, String hoTen, DiemSinhVienLTC diemSinhVienLTC){
        final Dialog dialog = new Dialog(this.getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog_nhapdiem);

        Window window = dialog.getWindow();
        if(window == null) return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAtrributes = window.getAttributes();
        windowAtrributes.gravity = Gravity.CENTER;
        window.setAttributes(windowAtrributes);

        Button btnHuy = dialog.findViewById(R.id.btnHuy);
        Button btnOk = dialog.findViewById(R.id.btnOk);
        TextView tvHoTen = dialog.findViewById(R.id.tvHoTen);
        TextView tvMaSV = dialog.findViewById(R.id.tvMaSV);
        EditText edtDiemCC = dialog.findViewById(R.id.edtDiemCC);
        EditText edtDiemGK = dialog.findViewById(R.id.edtDiemGK);
        EditText edtDiemCK = dialog.findViewById(R.id.edtDiemCK);

        tvHoTen.setText(hoTen);
        tvMaSV.setText(maSV);
        edtDiemCC.setText(diemSinhVienLTC.getDiemCC() + "");
        edtDiemGK.setText(diemSinhVienLTC.getDiemGK() + "");
        edtDiemCK.setText(diemSinhVienLTC.getDiemCK() + "");

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int diemCC = Integer.valueOf(edtDiemCC.getText().toString());
                float diemGK = Float.valueOf(edtDiemGK.getText().toString());
                float diemCK = Float.valueOf(edtDiemCK.getText().toString());
                DiemSinhVienLTC diemSv = new DiemSinhVienLTC(maLTC, maSV, diemCC, diemGK, diemCK);
                ApiService.apiService.capNhatDiem(diemSv).enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Log.d("code_API", response.code() + "");
                        if(response.code() == 200){
                            diemTK = (float) ((0.1 * diemCC) + (0.3 * diemGK) + (0.6 * diemCK));
                            ChangeData();
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

    public void ChangeData(){
        data.get(index).setDiem(diemTK);
        notifyDataSetChanged();
    }
}
