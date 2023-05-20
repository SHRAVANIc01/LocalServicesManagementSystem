package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

public class Start_Checker_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
