package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.R;

public class DetailInforRegisActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edPhone,edAdderss,edDob,edIdCard;
    Button btnNext;
    String phone,address,dob,idCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_infor_regis);
        init();
        initActionBar();
        btnNext.setOnClickListener(this);
    }
    public void init()
    {
        edPhone = findViewById(R.id.edPhone);
        edAdderss = findViewById(R.id.edAddress);
        edDob = findViewById(R.id.edDob);
        edIdCard = findViewById(R.id.edIdcard);
        btnNext = findViewById(R.id.btn_next);
        phone="";
        address="";
        dob="";
        idCard="";
    }

    public void getInfor()
    {
        phone = edPhone.getText().toString();
        address = edAdderss.getText().toString();
        dob = edDob.getText().toString();
    }
    @Override
    public void onClick(View v) {
        if(v==btnNext)
        {
            getInfor();
            Intent intent = new Intent(DetailInforRegisActivity.this,RegisterActivity.class);
            intent.putExtra("phone",phone);
            intent.putExtra("address",address);
            intent.putExtra("dob",dob);
            intent.putExtra("idCard",idCard);
            startActivity(intent);
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
        txtWellcome.setText("Đăng ký ");

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