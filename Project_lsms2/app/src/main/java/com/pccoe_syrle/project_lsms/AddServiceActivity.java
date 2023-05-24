package com.pccoe_syrle.project_lsms;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddServiceActivity extends AppCompatActivity {
    EditText sName , sAddress , sPrice, sPassword, sPhone;
    Button AddService;
    SharedPreferences sharedPreferences;
    TextView backToSetting;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_service_activity);

        sName = findViewById(R.id.servicename);
        sAddress = findViewById(R.id.address);
        sPrice = findViewById(R.id.price);
        sPassword = findViewById(R.id.password);
        sPhone = findViewById(R.id.phoneNumber);
        AddService = findViewById(R.id.buttonAddService);
        backToSetting = findViewById(R.id.switchSetting);

        sharedPreferences = getSharedPreferences("LSMSshared", Context.MODE_PRIVATE);

        AddService.setOnClickListener(v -> {
            String name = sName.getText().toString();
            String adrs = sAddress.getText().toString();
            long price = Integer.parseInt(sPrice.getText().toString());
            String password = sPassword.getText().toString();
            long phone = Integer.parseInt(sPhone.getText().toString());

            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            String url ="http://192.168.167.140/Loginsignin/AddService.php";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if(response.equals("Adding Successfull")){
                        Toast.makeText(AddServiceActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(AddServiceActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }){
                protected Map<String, String> getParams(){
                    Map<String, String> paramV = new HashMap<>();
                    paramV.put("ServiceName",name);
                    paramV.put("ServiceAddress",adrs);
                    paramV.put("Price",price+"");
                    paramV.put("Password",password);
                    paramV.put("Phonenumber",phone+"");
                    paramV.put("Email", sharedPreferences.getString("email",""));
                    paramV.put("Name", sharedPreferences.getString("name",""));
                    return paramV;
                }
            };
            queue.add(stringRequest);
        });

        backToSetting.setOnClickListener(v -> {
            Intent intent = new Intent(this, SettingsActivity.class);
            AddServiceActivity.this.startActivity(intent);
            finish();
        });
    }
}
