package com.example.bank;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentTrangChu extends Fragment {

    private ImageButton imbTaiKhoan,imbDienThoai,imbThanhToan,imbGopy,imbTietKiem,imbDautu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trangchu,container,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Tính năng chưa được hỗ trợ ");

        imbTaiKhoan = v.findViewById(R.id.imbTaiKhoan);
        imbDienThoai=v.findViewById(R.id.imbNapDT);
        imbThanhToan = v.findViewById(R.id.imbThanhtoan);
        imbGopy = v.findViewById(R.id.imbGopY);
        imbTietKiem = v.findViewById(R.id.imbTietKiem);
        imbDautu = v.findViewById(R.id.imbDauTu);
        imbTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        imbDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().show();
            }
        });
        imbThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().show();
            }
        });
        imbGopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().show();
            }
        });
        imbDautu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.create().show();
            }
        });


        MainActivity.buttonEffect(imbDienThoai);
        MainActivity.buttonEffect(imbTaiKhoan);
        MainActivity.buttonEffect(imbThanhToan);
        MainActivity.buttonEffect(imbGopy);
        MainActivity.buttonEffect(imbDautu);
        MainActivity.buttonEffect(imbTietKiem);


        return v;
    }

}
