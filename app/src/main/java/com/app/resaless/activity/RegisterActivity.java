package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.resaless.R;
import com.app.resaless.api.FoodApi;

public class RegisterActivity extends AppCompatActivity {

    public Button register, cancel;
    ProgressBar pb;
    private EditText email, password, login;
    private final FoodApi foodApi = new FoodApi();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        setOnClickListeners();
    }

    public void initViews(){
        register = findViewById(R.id.btn_register);
        cancel = findViewById(R.id.btn_cancel);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);
        login = findViewById(R.id.editName);
        pb = findViewById(R.id.progress_bar);
    }

    public void setOnClickListeners(){
        register.setOnClickListener(v -> {
            pb.setVisibility(View.VISIBLE);
            foodApi.register(
                    email.getText().toString(),
                    password.getText().toString(),
                    login.getText().toString(),
                    (isConnectionSuccess, isRegisterSuccess) -> runOnUiThread(() -> {
                        if (isConnectionSuccess){
                            if (isRegisterSuccess){
                                Toast.makeText(this, "Successful registration", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                Toast.makeText(this, "Registration failed", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Toast.makeText(this, "Connection failed", Toast.LENGTH_SHORT).show();
                        }
                        pb.setVisibility(View.GONE);
                    })
            );
        });
        cancel.setOnClickListener(v -> {
            finish();
        });
    }
}