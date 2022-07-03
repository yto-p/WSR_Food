package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.app.resaless.R;

public class RegisterActivity extends AppCompatActivity {

    public Button register, cancel;

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
    }

    public void setOnClickListeners(){
        register.setOnClickListener(v -> {
            finish();
        });
        cancel.setOnClickListener(v -> {
            finish();
        });
    }
}