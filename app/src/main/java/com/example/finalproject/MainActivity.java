package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonToLoginActivity, buttonToSingUpActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);
        LoadElement();
        buttonToLoginActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginScreen();
            }
        });

        buttonToSingUpActivity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openSingUpScreen();
            }
        });


    }

    public void openLoginScreen() {
        Intent intentToLogin = new Intent(MainActivity.this, LoginAcitivity.class);
        startActivity(intentToLogin);
    }

    public void openSingUpScreen() {
        Intent intentToSingUp = new Intent(MainActivity.this, SignUp.class);
        startActivity(intentToSingUp);
    }

    private void LoadElement(){

        buttonToLoginActivity = findViewById(R.id.sign_in_button_homepage);
        buttonToSingUpActivity = findViewById(R.id.sign_up_button_homepage);
    }

}