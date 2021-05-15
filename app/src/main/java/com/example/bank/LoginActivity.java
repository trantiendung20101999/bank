package com.example.bank;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LoginButton btnFacebook;
    ImageButton btnFaceBookLogin;
    CallbackManager callbackManager;
    private TextView txtWellcome;
    private ImageButton imbLogout;
    private ImageButton imgBackpress;
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

                        final String[] id = new String[1];
                        final String[] name = new String[1];
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
                                            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                            intent.putExtra("id",id[0]);
                                            intent.putExtra("name",name[0]);
                                            startActivity(intent);
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
        btnFaceBookLogin.setOnClickListener(this);
    }

    public void init()
    {
        btnFacebook = findViewById(R.id.btn_loggin);
        btnFaceBookLogin = findViewById(R.id.btn_facebook_loggin);
        btnFacebook.setPermissions(Arrays.asList("email"));
        MainActivity.buttonEffect(btnFaceBookLogin);
    }

    public void initActionBar()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        txtWellcome = findViewById(R.id.txtWellcome);
        imbLogout = findViewById(R.id.imgLogout);
        imbLogout.setVisibility(View.GONE);
        imgBackpress  = findViewById( R.id.imbbackpress);
        MainActivity.buttonEffect(imgBackpress);
        imgBackpress.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v) {
        if(v==imgBackpress)
        {
            onBackPressed();
        }
        if(v==btnFaceBookLogin)
        {
            btnFacebook.performClick();
        }
    }
}