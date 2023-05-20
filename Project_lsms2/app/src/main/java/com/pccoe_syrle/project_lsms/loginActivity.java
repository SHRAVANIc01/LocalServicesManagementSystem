package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class loginActivity extends AppCompatActivity {

    String key = "com.pccoe_lsms.shravani.signup";
    EditText username,password;
    Button login;
    TextView signSwitchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);
        signSwitchText = findViewById(R.id.signTextButton);

        signSwitchText.setOnClickListener(v -> {
            Intent i = new Intent(loginActivity.this,signInActivity.class);
            i.putExtra(key,"Sign in");
            loginActivity.this.startActivity(i);
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            intent.putExtra(key,"Homepage");
            loginActivity.this.startActivity(intent);
        });
    }
}
