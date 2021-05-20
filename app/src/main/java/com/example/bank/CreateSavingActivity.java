package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CreateSavingActivity extends AppCompatActivity {

    public static EditText edMoney;
    RecyclerView rev;
    TextView txtWellcome;
    CreateSavingRevAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_saving);

        initActionBar();
        edMoney = findViewById(R.id.edMoney);
        rev =findViewById(R.id.rev);
        Intent intent = getIntent();
        String type = intent.getStringExtra("type");
        if(type.equalsIgnoreCase("normal")) {
            adapter = new CreateSavingRevAdapter(getNormal(),"normal");
            txtWellcome.setText("Tiết kiệm thường ");
        }
        else
        {
            adapter = new CreateSavingRevAdapter(getPhatloc(),"phatloc");
            txtWellcome.setText("Tiết kiệm phát lộc ");

        }

        rev.setAdapter(adapter);
        rev.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

    }

    public List<String> getNormal()
    {
        List<String> list = new ArrayList<>();
        list.add("1 Tháng 2.5%");
        list.add("2 Tháng 2.8%");
        list.add("3 Tháng 3.0%");
        list.add("4 Tháng 3.2%");
        list.add("5 Tháng 3.4%");
        list.add("6 Tháng 5%");
        list.add("7 Tháng 5.2%");
        list.add("8 Tháng 5.4%");
        list.add("9 Tháng 5.6%");
        list.add("10 Tháng 5.8%");
        list.add("11 Tháng 6.0%");
        list.add("12 Tháng 7%");

        return list;
    }
    public List<String> getPhatloc()
    {
        List<String> list = new ArrayList<>();
        list.add("1 Tháng 2.5%");
        list.add("2 Tháng 3.0%");
        list.add("3 Tháng 3.5%");
        list.add("4 Tháng 3.7%");
        list.add("5 Tháng 4.0%");
        list.add("6 Tháng 7%");
        list.add("7 Tháng 7.3%");
        list.add("8 Tháng 7.5%");
        list.add("9 Tháng 8.0%");
        list.add("10 Tháng 8.2%");
        list.add("11 Tháng 6.4%");
        list.add("12 Tháng 9.0%");

        return list;
    }
    public void initActionBar()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        ImageButton imbLogout;
        ImageButton imgBackpress;
        imbLogout = findViewById(R.id.imgLogout);
        imbLogout.setVisibility(View.GONE);
        txtWellcome = findViewById(R.id.txtWellcome);
        txtWellcome.setText(" ");
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