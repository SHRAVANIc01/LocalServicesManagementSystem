package com.pccoe_syrle.project_lsms;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WalletActivity extends AppCompatActivity {

    TextView amount;
    Button addBalance;
    EditText balance;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wallet_activity);

        addBalance = findViewById(R.id.addbalance);
        balance = findViewById(R.id.editTextNumber);
        amount = findViewById(R.id.amount);

        addBalance.setOnClickListener(v -> {
            int a = Integer.parseInt(balance.getText().toString());
            int b = Integer.parseInt(amount.getText().toString());

            amount.setText((a+b)+"");
        });
    }
}
