package com.example.bank;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.example.bank.model.Account;
import com.example.bank.model.UserLoginResponse;
import com.example.bank.model.UserResResponse;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LoginButton btnFacebook;
    ImageButton btnFaceBookLogin;
    CallbackManager callbackManager;
    private TextView txtAlert;

    private EditText edUsername,edPassword;
    private Button btnLoggin;
    private Button btnSignup;
    Handler handler;
    public static final String[] id = new String[1];
    public static final String[] name = new String[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FacebookSdk.sdkInitialize(getApplicationContext());

        init();
        initActionBar();

        callbackManager = CallbackManager.Factory.create();
        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {


                        AccessToken accessToken = AccessToken.getCurrentAccessToken();
                        GraphRequest request = GraphRequest.newMeRequest(
                                accessToken,
                                new GraphRequest.GraphJSONObjectCallback() {
                                    @Override
                                    public void onCompleted(
                                            JSONObject object,
                                            GraphResponse response) {

                                        try {
                                            id[0] = object.getString("id");
                                            name[0] = object.getString("name");
                                            ApiService.apiService.signinlogin(id[0])
                                                    .enqueue(new Callback<UserLoginResponse>() {
                                                        @Override
                                                        public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                                                            UserLoginResponse u = response.body();
                                                            if(u!=null)
                                                            {
                                                                Log.e("false","ok");
                                                                Log.e("dddd",u.getUsername());
                                                                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                                                intent.putExtra("user",u);
                                                                startActivity(intent);
                                                            }
                                                            else
                                                            {
                                                                Log.e("true",id[0]);
                                                                txtAlert.setText("Tài khoản facebook chưa được liên kết , đăng nhập để liên kết");
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
                                                                //txtAlert.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).start();
                                                            }
                                                        }

                                                        @Override
                                                        public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                                                            Log.e("false2",id[0]);

                                                        }
                                                    });

                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                });

                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name");
                        request.setParameters(parameters);
                        request.executeAsync();
                    }

                    @Override
                    public void onCancel() {
                    }

                    @Override
                    public void onError(FacebookException error) {
                    }
                }

        );
        Intent intent = getIntent();
        if(intent!=null)
        {
            if(intent.getStringExtra("alert")!=null)
            {
                edUsername.setText(intent.getStringExtra("username"));
                txtAlert.setVisibility(View.VISIBLE);
                txtAlert.setText(intent.getStringExtra("alert"));
                txtAlert.setBackgroundResource(R.drawable.background_success);
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
                                txtAlert.setText("Tài khoản hoặc mật khẩu không chính xác ");
                                txtAlert.setBackgroundResource(R.drawable.background_alert);
                                break;
                            }
                        }
                    }
                };
            }

            if(intent.getStringExtra("res")!=null)
            {
                txtAlert.setVisibility(View.VISIBLE);
                txtAlert.setText(intent.getStringExtra("res"));
                txtAlert.setBackgroundResource(R.drawable.background_success);
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
                                txtAlert.setText("Tài khoản hoặc mật khẩu không chính xác ");
                                txtAlert.setBackgroundResource(R.drawable.background_alert);
                                break;
                            }
                        }
                    }
                };
            }
        }
        btnSignup.setOnClickListener(this);
        btnLoggin.setOnClickListener(this);
        btnFaceBookLogin.setOnClickListener(this);
    }

    public void init()
    {
        txtAlert = findViewById(R.id.txtAlert);
        btnFacebook = findViewById(R.id.btn_loggin);
        btnFaceBookLogin = findViewById(R.id.btn_facebook_loggin);
        btnFacebook.setPermissions(Arrays.asList("email"));
        edUsername=findViewById(R.id.edUsername);
        edPassword=findViewById(R.id.edpassword);
        btnLoggin = findViewById(R.id.btn_loggin_normal);
        btnSignup = findViewById(R.id.btn_signup_normal);
        MainActivity.buttonEffect(btnLoggin);
        MainActivity.buttonEffect(btnFaceBookLogin);
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
        txtWellcome.setText("Đăng nhập ");
        imgBackpress  = findViewById( R.id.imbbackpress);
        MainActivity.buttonEffect(imgBackpress);
        imgBackpress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {

        if(v==btnFaceBookLogin)
        {
            btnFacebook.performClick();
        }
        if(v==btnSignup)
        {
            Intent intent = new Intent(LoginActivity.this, DetailInforRegisActivity.class);
            startActivity(intent);
        }
        if(v==btnLoggin)
        {
            String username = edUsername.getText().toString();
            String password = edPassword.getText().toString();
            Account account = new Account(username,password);
            ApiService.apiService.signIn(account).enqueue(new Callback<UserLoginResponse>() {
                @Override
                public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                    UserLoginResponse u = response.body();
                    if(u!=null)
                    {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("user",u);
                        startActivity(intent);
                    }
                    else
                    {

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
                        //txtAlert.animate().scaleY(1).setInterpolator(new DecelerateInterpolator()).start();
                        Log.e("false","mat khau khong dung");
                    }
                }

                @Override
                public void onFailure(Call<UserLoginResponse> call, Throwable t) {
                    Log.e("false","connect false");
                }
            });
        }
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
}