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

import com.pccoe_syrle.project_lsms.DBhelper.DBbalanceUpdate;

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
        long bal = sharedPreferences.getLong("balance",0);
        amount.setText(bal+"");

        addBalance.setOnClickListener(v -> {
            long a = Integer.parseInt(balance.getText().toString());
            long b = Integer.parseInt(amount.getText().toString());

            amount.setText((a+b)+"");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("balance",(a+b));
            editor.apply();

            DBbalanceUpdate.updateBalance((a+b), getApplicationContext(), sharedPreferences.getString("email",""));
        });

    }
}
