package com.example.bank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bank.api.ApiService;
import com.example.bank.model.UserResResponse;
import com.example.bank.model.UserResSend;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername,edPassword,edFullname,edEmail;
    String username,password,fullname,email,phone,dob,address,idCard;
    TextView txtAlert;
    Button btnRes;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initActionBar();
        Intent intent = getIntent();
        if(intent!=null)
        {
            phone = intent.getStringExtra("phone");
            dob = intent.getStringExtra("dob");
            address = intent.getStringExtra("address");
            idCard = intent.getStringExtra("idCard");
        }
        init();

        btnRes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = edUsername.getText().toString();
                password = edPassword.getText().toString();
                fullname = edFullname.getText().toString();
                email = edEmail.getText().toString();
                String facebookId=LoginActivity.id[0];
                UserResSend urs = new UserResSend(username,password,fullname,email,phone,dob,address,idCard,facebookId);
                ApiService.apiService.signUp(urs).enqueue(new Callback<UserResResponse>() {
                    @Override
                    public void onResponse(Call<UserResResponse> call, Response<UserResResponse> response) {


                        if(response.code()==400) {
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
                                String message = jsonObject.getString("message");
                                if(message.equalsIgnoreCase("Error: Username is already taken!"))
                                {
                                    txtAlert.setText("Tên tài khoản đã tồn tạị");
                                    txtAlert.setVisibility(View.VISIBLE);
                                    txtAlert.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).start();
                                    new CountDown().start();
                                    handler = new Handler(){
                                        @Override
                                        public void handleMessage(@NonNull Message msg) {
                                            switch (msg.what)
                                            {
                                                case 1000:
                                                {
                                                    break;
                                                }
                                                case 123:
                                                {
                                                    txtAlert.animate().scaleY(0).setInterpolator(new DecelerateInterpolator()).start();
                                                    break;
                                                }
                                            }
                                        }
                                    };
                                }
                                else if(message.equalsIgnoreCase("Error: Email is already in use!"))
                                {
                                    txtAlert.setText("Email đã được sử dụng ");
                                    txtAlert.setVisibility(View.VISIBLE);
                                    txtAlert.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).start();
                                    new CountDown().start();
                                    handler = new Handler(){
                                        @Override
                                        public void handleMessage(@NonNull Message msg) {
                                            switch (msg.what)
                                            {
                                                case 1000:
                                                {
                                                    break;
                                                }
                                                case 123:
                                                {
                                                    txtAlert.animate().scaleY(0).setInterpolator(new DecelerateInterpolator()).start();
                                                    break;
                                                }
                                            }
                                        }
                                    };
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else
                        {
                            UserResResponse urr= response.body();
                            if(urr!=null && urr.getMessage()!=null)
                            {
                                    Intent intent1 = new Intent(RegisterActivity.this,LoginActivity.class);
                                    intent1.putExtra("res","Đăng ký thành công ");
                                    startActivity(intent1);

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<UserResResponse> call, Throwable t) {

                    }
                });
            }
        });


    }
    public void init()
    {
        edUsername = findViewById(R.id.edUsername);
        edPassword = findViewById(R.id.edpassword);
        edFullname = findViewById(R.id.edFullname);
        edEmail = findViewById(R.id.edEmail);
        txtAlert = findViewById(R.id.txtAlert);
        btnRes = findViewById(R.id.btn_signup_normal);
    }
    class CountDown extends Thread
    {
        @Override
        public void run() {
            int time=3;
            while(time>0)
            {
                time--;
                Message msg = new Message();
                msg.what = 1000;
                msg.arg1 = time;
                handler.sendMessage(msg);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    System.out.println(e);
                }
            }
            handler.sendEmptyMessage(123);
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