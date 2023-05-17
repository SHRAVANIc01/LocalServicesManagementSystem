package com.pccoe_syrle.project_lsms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class signInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.signup_activity);
        String value = intent.getStringExtra("com.pccoe_lsms.shravani.signup");
    }
}