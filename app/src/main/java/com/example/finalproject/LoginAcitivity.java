package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import com.example.finalproject.Model.Asset;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginAcitivity extends AppCompatActivity {

    APIInterface apiInterface;
    TextView txtToken;
    Button buttonSignIn, buttonToBack;
    String usr;
    String pwd;
    WebView LoginWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        LoadElement();
        ActionListener();
    }



    public void ActionListener() {
        BackBtnListener();
        SignInListener();
    }

    private void BackBtnListener() {
        buttonToBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToBack = new Intent(LoginAcitivity.this, MainActivity.class);
                startActivity(intentToBack);
            }
        });
    }

    private void SignInListener() {
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { SignIn(); }
        });
    }

    private void SignIn() {
        //Khởi tạo retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://uiot.ixxc.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Tạo một đối tượng API Service từ Retrofit
        apiInterface = retrofit.create(APIInterface.class);

        // Thực hiện POST request
        Call<Asset> call = apiInterface.authenticate("openremote", usr, pwd, "password", "cakho12345@gmail.com");
        call.enqueue(new Callback<Asset>() {
            @Override
            public void onResponse(Call<Asset> call, Response<Asset> response) {
                if (response.isSuccessful()) {
                    Asset result = response.body();
                    String token = result.getAccessToken();
                    Toast.makeText(LoginAcitivity.this,token,Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAcitivity.this, Dashboard.class);
                    startActivity(intent);
                } else {
                    // Xử lý lỗi ở đây
                    Toast.makeText(LoginAcitivity.this, "Có lỗi xảy ra.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Asset> call, Throwable t) {
                // Xử lý lỗi kết nối ở đây
                Log.e("RetrofitError", t.getMessage());
                Toast.makeText(LoginAcitivity.this, "Lỗi kết nối.", Toast.LENGTH_SHORT).show();

            }

        });

    }

    private void LoadElement(){
        buttonToBack = findViewById(R.id.back_button);
        buttonSignIn = findViewById(R.id.sign_in_button_login);
        LoginWebview = findViewById(R.id.login_webview);
        TextInputEditText usrInput = findViewById(R.id.usrInputLogin);
        TextInputEditText pssInput = findViewById(R.id.pwdInputlogin);
        usr = String.valueOf(usrInput.getContext());
        pwd = String.valueOf((pssInput.getContext()));
    }


}