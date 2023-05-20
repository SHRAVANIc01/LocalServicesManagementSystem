package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Start_Checker_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent I = new Intent(this, loginActivity.class);
        Start_Checker_Activity.this.startActivity(I);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
