package com.pccoe_syrle.project_lsms;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WalletActivity extends AppCompatActivity {

    static TextView amount;
    Button addBalance;
    EditText balance;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity);
        Intent intent = getIntent();

        addBalance = findViewById(R.id.addbalance);
        balance = findViewById(R.id.editTextNumber);
        amount = findViewById(R.id.amount);
        sharedPreferences = getSharedPreferences("LSMSshared", MODE_PRIVATE);

        addBalance.setOnClickListener(v -> {
            int a = Integer.parseInt(balance.getText().toString());
            int b = Integer.parseInt(amount.getText().toString());

            amount.setText((a+b)+"");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("balance",(a+b)+"");
            editor.apply();
        });
        String bal = sharedPreferences.getString("balance","");
        amount.setText(bal);
    }
}
