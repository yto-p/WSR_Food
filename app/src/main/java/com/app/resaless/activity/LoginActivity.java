package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.app.resaless.R;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    public Button btn_login;
    public TextView signUp;
    public String tutorial;

    public SharedPreferences sPref;
    final String TUTORIAL_TEXT="tutorial_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initLogic();
//        if (tutorial==null){
            startActivity(new Intent(this, TutorialActivity.class));
//        }
        btn_login.setOnClickListener(v -> {
            finish();
        });
        signUp.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    public void initViews(){
        btn_login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.link_signup);
    }

    public void initLogic(){
        sPref=getSharedPreferences("data", MODE_PRIVATE);
        tutorial=sPref.getString(TUTORIAL_TEXT, null);
    }
}
