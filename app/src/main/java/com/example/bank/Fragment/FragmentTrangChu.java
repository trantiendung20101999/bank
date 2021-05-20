package com.example.bank.Fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bank.LoginActivity;
import com.example.bank.MainActivity;
import com.example.bank.R;
import com.example.bank.SavingActivity;
import com.example.bank.TakeBankSavingBookActivity;
import com.example.bank.api.ApiService;
import com.example.bank.model.BankSavingBookResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTrangChu extends Fragment {

    private ImageButton imbTaiKhoan,imbDienThoai,imbThanhToan,imbGopy,imbTietKiem,imbDautu;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trangchu,container,false);

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());

        imbTaiKhoan = v.findViewById(R.id.imbTaiKhoan);
        imbDienThoai=v.findViewById(R.id.imbNapDT);
        imbThanhToan = v.findViewById(R.id.imbThanhtoan);
        imbGopy = v.findViewById(R.id.imbGopY);
        imbTietKiem = v.findViewById(R.id.imbTietKiem);
        imbDautu = v.findViewById(R.id.imbDauTu);
        imbTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.u==null) {
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    builder.setMessage("Họ và tên: "+MainActivity.u.getFullName()+"\n"+
                            "Số tiền : "+MainActivity.u.getMoney());
                    builder.create().show();
                }
            }
        });

        imbTietKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.u==null) {
                    Intent intent = new Intent(v.getContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else
                {
                    ApiService.apiService.getBankSavingBook(MainActivity.u.getId(),"Bearer "+MainActivity.u.getAccessToken())
                            .enqueue(new Callback<BankSavingBookResponse>() {
                                @Override
                                public void onResponse(Call<BankSavingBookResponse> call, Response<BankSavingBookResponse> response) {

                                    BankSavingBookResponse bankSavingBookResponse = response.body();
                                    if(bankSavingBookResponse!=null)
                                    {
                                        Intent intent = new Intent(v.getContext(), TakeBankSavingBookActivity.class);
                                        intent.putExtra("savingbook",bankSavingBookResponse);
                                        startActivity(intent);
                                    }
                                }

                                @Override
                                public void onFailure(Call<BankSavingBookResponse> call, Throwable t) {
                                    Intent intent = new Intent(v.getContext(), SavingActivity.class);
                                    intent.putExtra("user",MainActivity.u);
                                    startActivity(intent);

                                }
                            });
                }
            }
        });

        imbDienThoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Tính năng chưa được hỗ trợ ");
                builder.create().show();
            }
        });
        imbThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Tính năng chưa được hỗ trợ ");
                builder.create().show();
            }
        });
        imbGopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Tính năng chưa được hỗ trợ ");
                builder.create().show();
            }
        });
        imbDautu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setMessage("Tính năng chưa được hỗ trợ ");
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
