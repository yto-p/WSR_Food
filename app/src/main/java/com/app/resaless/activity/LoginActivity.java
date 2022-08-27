package com.app.resaless.activity;

import androidx.appcompat.app.AppCompatActivity;
import com.app.resaless.R;
import com.app.resaless.api.FoodApi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    public Button btn_login;
    public TextView signUp;
    public String tutorial;
    public EditText email, password;
    public SharedPreferences sPref;
    final String TUTORIAL_TEXT="tutorial_text";
    public final static String TOKEN_KEY="token";

    private final FoodApi foodApi = new FoodApi();

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
            foodApi.login(email.getText().toString(), password.getText().toString(), new FoodApi.LoginCallback() {
                @Override
                public void onConnectionFailure() {
                    runOnUiThread( () ->
                            Toast.makeText(LoginActivity.this, "Connection failed", Toast.LENGTH_SHORT).show()
                    );
                }

                @Override
                public void onLoginFailure() {
                    runOnUiThread(() ->
                            Toast.makeText(LoginActivity.this, "User credentials invalid", Toast.LENGTH_SHORT).show()
                    );
                }

                @Override
                public void onSuccessLogin(int token) {
                    runOnUiThread(() -> {
                        sPref.edit().putInt(TOKEN_KEY, token).apply();
                        finish();
                    });
                }
            });
        });
        signUp.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });
    }

    public void initViews(){
        btn_login = findViewById(R.id.btn_login);
        signUp = findViewById(R.id.link_signup);
        email = findViewById(R.id.editEmail);
        password = findViewById(R.id.editPass);
    }

    public void initLogic(){
        sPref=getSharedPreferences("data", MODE_PRIVATE);
        tutorial=sPref.getString(TUTORIAL_TEXT, null);
    }
}
