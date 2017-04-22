package com.example.asadaboomtham.testlogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.asadaboomtham.testlogin.R;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class TestLogin extends AppCompatActivity {

    LoginButton loginButton;
    TextView txtLoginStatus;
    CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_test_login);
        loginButton = (LoginButton) findViewById(R.id.fb_login_bn);
        txtLoginStatus = (TextView)findViewById(R.id.txtLoginstatus);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                txtLoginStatus.setText("Login Success \n" +
                        loginResult.getAccessToken().getUserId()+
                        "\n" + loginResult.getAccessToken().getToken());
            }

            @Override
            public void onCancel() {
                txtLoginStatus.setText("Login Cancelled");
            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
