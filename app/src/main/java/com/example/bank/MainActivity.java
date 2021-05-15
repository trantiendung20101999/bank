package com.example.bank;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TextView txtNameId;
    private TextView txtWellcome;
    private ImageButton imbLogout;
    private ImageButton imgBackpress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActionBar();
        init();
        imbLogout.setVisibility(View.GONE);

        Intent intent= getIntent();

        String name = intent.getStringExtra("name");
        String id = intent.getStringExtra("id");
        if(name !=null && id !=null) {
                txtWellcome.setText("Hello "+name);
                txtNameId.setText(name + "-" + id);
                imbLogout.setVisibility(View.VISIBLE);
        }

    }

    public void init()
    {
        tabLayout = findViewById( R.id.tabMain);
        viewPager = findViewById(R.id.viewpager);
        txtNameId=findViewById(R.id.txtNameId);
        buttonEffect(imbLogout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),3);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_bank_trongnh);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_bank_24_7);
    }

    public void initActionBar()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        txtWellcome = findViewById(R.id.txtWellcome);
        imbLogout = findViewById(R.id.imgLogout);
        imbLogout.setOnClickListener(this);
        imgBackpress  = findViewById( R.id.imbbackpress);
        buttonEffect(imgBackpress);
        imgBackpress.setOnClickListener(this);
    }


    public static void buttonEffect(View button){
        button.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        v.getBackground().setColorFilter(0xe0FFD700, PorterDuff.Mode.SRC_ATOP);
                        v.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        v.getBackground().clearColorFilter();
                        v.invalidate();
                        break;
                    }
                }
                return false;
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        LoginManager.getInstance().logOut();
        System.out.println("dsfdsfdsfdsfdsd");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LoginManager.getInstance().logOut();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LoginManager.getInstance().logOut();
    }

    @Override
    public void onClick(View v) {
        if(v== imgBackpress)
        {
            onBackPressed();
        }
        if(v==imbLogout)
        {
            Intent intent1 = new Intent(MainActivity.this,MainActivity.class);
            LoginManager.getInstance().logOut();
            startActivity(intent1);
        }
    }
}