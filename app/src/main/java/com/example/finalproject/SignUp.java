package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.finalproject.Model.Asset;


import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    Button buttonToBack, btnSignUp;

    String usr;
    String email;
    String pwd;
    String coPwd;
    TextView txtToken;


    WebView webViewSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        LoadElement();
        BackBtnOnClick();
        SignUpOnClick();
    }

    public void BackBtnOnClick() {
        buttonToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentToBack = new Intent(v.getContext(), MainActivity.class);
                startActivity(intentToBack);
            }
        });
    }

    public void SignUpOnClick() {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SignUp(); }
        });
    }

    private void SignUp(){
        webViewSignUp.getSettings().setJavaScriptEnabled(true);
        webViewSignUp.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                if (url.contains("uiot.ixxc.dev/manager/")) {
                    // Handle successful registration and navigate to a new activity
                    Toast.makeText(SignUp.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignUp.this, Dashboard.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(SignUp.this, "Tên người dùng hoăc email đã tồn tại", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                if(url.contains("openid-connect/registrations")){
                    String usrScript =  "document.getElementById('username').value ='" + usr +"';";
                    String emailScript =  "document.getElementById('email').value ='" + email +"';";
                    String pwdScript =  "document.getElementById('password').value ='" + pwd +"';";
                    String rePwdScript =  "document.getElementById('password-confirm').value ='" + coPwd +"';";
                    String submitFormScript = "document.querySelector('form').submit();";

                    webViewSignUp.evaluateJavascript(usrScript, null);
                    webViewSignUp.evaluateJavascript(emailScript, null);
                    webViewSignUp.evaluateJavascript(pwdScript, null);
                    webViewSignUp.evaluateJavascript(rePwdScript, null);
                    webViewSignUp.evaluateJavascript(submitFormScript,null);

                }
            }
        });
        String url = "https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/registrations?client_id=openremote&redirect_uri=https%3A%2F%2Fuiot.ixxc.dev%2Fmanager%2F&response_mode=fragment&response_type=code&scope=openid";
        webViewSignUp.loadUrl(url);
    }

    private void LoadElement(){
        txtToken = findViewById(R.id.txtToken);
        buttonToBack = findViewById(R.id.back_button);
        btnSignUp = findViewById(R.id.sign_up_button_2);
        TextInputEditText usrInput = findViewById(R.id.usrInputLogin);
        TextInputEditText emailInput = findViewById(R.id.emailInput);
        TextInputEditText passwordInput = findViewById(R.id.pwdInputlogin);
        TextInputEditText passConfirmInput = findViewById(R.id.passwordconfirmInput);
        usr = String.valueOf(usrInput.getContext());
        email = String.valueOf(emailInput.getContext());
        pwd = String.valueOf(passwordInput.getContext());
        coPwd = String.valueOf(passConfirmInput.getContext());
        webViewSignUp = findViewById(R.id.sign_up_webview);


    }


}