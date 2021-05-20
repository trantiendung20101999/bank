package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.api.ApiService;
import com.example.bank.model.Account;
import com.example.bank.model.BankSavingBookResponse;
import com.example.bank.model.UserLoginResponse;
import com.facebook.login.Login;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TakeBankSavingBookActivity extends AppCompatActivity implements View.OnClickListener {


    TextView txtMoney,txtStartDate,txtTerm,txtType,txtLai;

    Button btnRut, btnLaiTerm,btnLaiEnd;

    BankSavingBookResponse bankSavingBookResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_bank_saving_book);

        init();
        initActionBar();
        Intent intent = getIntent();
        if(intent!=null)
        {
            bankSavingBookResponse = (BankSavingBookResponse) intent.getSerializableExtra("savingbook");
            if(bankSavingBookResponse!=null) {
                txtMoney.setText("Tiền gửi: " + bankSavingBookResponse.getMoney()+"");
                txtStartDate.setText("Ngày tạo sổ : " + bankSavingBookResponse.getStartDate());
                txtTerm.setText("Kỳ hạn - lãi suất : " + bankSavingBookResponse.getInterestrate().getTimes() + " - " + bankSavingBookResponse.getInterestrate().getInterestRate()+"%");
                txtType.setText("Loại tiết kiệm: " + bankSavingBookResponse.getInterestrate().getTypeOfSaving());
            }

        }
    }

    public void init()
    {
        txtMoney = findViewById(R.id.txtMoney);
        txtStartDate=findViewById(R.id.txtStartDate);
        txtTerm = findViewById(R.id.txtTerm);
        txtType=findViewById(R.id.txtTypeSaving);
        txtLai = findViewById(R.id.txtLai);

        btnRut = findViewById(R.id.btnRutSo);
        btnLaiTerm = findViewById(R.id.btnTinhLaiTerm);
        btnLaiEnd = findViewById(R.id.btnTinhLaiEnd);
        btnRut.setOnClickListener(this);
        btnLaiEnd.setOnClickListener(this);
        btnLaiTerm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v==btnRut)
        {
            ApiService.apiService.DelBankSavingBook(bankSavingBookResponse.getUser().getId(),"Bearer "+MainActivity.u.getAccessToken())
                    .enqueue(new Callback<Integer>() {
                        @Override
                        public void onResponse(Call<Integer> call, Response<Integer> response) {
                            Intent intent = new Intent(v.getContext(), LoginActivity.class);
                            intent.putExtra("alert","Đăng nhập lại để cập nhật số tiền ");
                            intent.putExtra("username",bankSavingBookResponse.getUser().getUsername());
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Integer> call, Throwable t) {

                        }
                    });
        }
        if(v==btnLaiTerm)
        {
            ApiService.apiService.getMoneyCurrent(bankSavingBookResponse.getUser().getId(),"Bearer "+MainActivity.u.getAccessToken())
                    .enqueue(new Callback<Double>() {
                        @Override
                        public void onResponse(Call<Double> call, Response<Double> response) {
                            Double i = response.body();
                            if(i==bankSavingBookResponse.getMoney())
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                builder.setMessage("Tiền lãi đến thời điểm hiện tại: "+ 0 +"VND");
                                builder.create().show();

                            }
                            else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                                builder.setMessage("Tiền lãi đến thời điểm hiện tại: "+ i+"VND");
                                builder.create().show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Double> call, Throwable t) {

                        }
                    });
        }
        if(v==btnLaiEnd)
        {
            ApiService.apiService.getMoneyOnTime(bankSavingBookResponse.getUser().getId(),"Bearer "+MainActivity.u.getAccessToken())
                    .enqueue(new Callback<Double>() {
                        @Override
                        public void onResponse(Call<Double> call, Response<Double> response) {
                            Double i = response.body();

                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setMessage("Tiền lãi đến Hết định kỳ :"+ i+"VND");
                            builder.create().show();


                        }

                        @Override
                        public void onFailure(Call<Double> call, Throwable t) {

                        }
                    });
        }
    }
    public void initActionBar()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ImageButton imbLogout;
        ImageButton imgBackpress;
        imbLogout = findViewById(R.id.imgLogout);
        imbLogout.setVisibility(View.GONE);

        TextView txtWellcome = findViewById(R.id.txtWellcome);
        txtWellcome.setText("Sổ tiết kiệm ");

        imgBackpress  = findViewById( R.id.imbbackpress);
        MainActivity.buttonEffect(imgBackpress);
        imgBackpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}