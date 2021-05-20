package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class SavingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        initActionBar();
        Button btnNormal = findViewById(R.id.btnNormal);
        Button btnPhatloc = findViewById(R.id.btnPhatLoc);
        btnPhatloc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingActivity.this , CreateSavingActivity.class);
                intent.putExtra("type","phatloc");
                startActivity(intent);
            }
        });
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SavingActivity.this , CreateSavingActivity.class);
                intent.putExtra("type","normal");
                startActivity(intent);
            }
        });
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
        txtWellcome.setText("Tiết kiệm ");

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