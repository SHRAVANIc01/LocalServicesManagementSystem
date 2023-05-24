package com.pccoe_syrle.project_lsms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.pccoe_syrle.project_lsms.DBhelper.DBbalanceUpdate;

public class ServiceDetailsActivity extends AppCompatActivity {

    private TextView textViewServiceName, textViewPrice;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.invoice);
        sharedPreferences = getSharedPreferences("LSMSshared", MODE_PRIVATE);

        // Get the invoice details from your data source or intent extras
        String serviceName = "Service Name";
        long price = 0;
        price = sharedPreferences.getLong("price",0);
        long bal = 0;
        bal = sharedPreferences.getLong("balance",0);

        if(bal<price){
            Toast.makeText(this, "Insufficient balance", Toast.LENGTH_SHORT).show();
        }
        else {
            // Find the TextViews in the layout
            textViewServiceName = findViewById(R.id.textViewServiceName);
            textViewPrice = findViewById(R.id.textViewPrice);

            // Set the invoice details to the TextViews
            textViewServiceName.setText("Service Name: " + serviceName);
            textViewPrice.setText("Price: ₹" + price);

            bal = bal - price;

            DBbalanceUpdate.updateBalance(bal,getApplicationContext(), sharedPreferences.getString("email",""));
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putLong("balance",bal);
            editor.apply();
        }
    }
}
