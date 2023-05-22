package com.pccoe_syrle.project_lsms;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class testFetchActivity extends AppCompatActivity {
    Button button;
    ServiceProviderClass people;
    TextView textView;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testing);

        button = findViewById(R.id.testFetch);
        textView = findViewById(R.id.testview);
        people = new ServiceProviderClass();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url ="http://192.168.1.5/Loginsignin/fetchData.php";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                                textView.setText(response);
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject = jsonArray.getJSONObject(1);
                                    people.setName(jsonObject.getString("name"));
                                    people.setPhone(jsonObject.getInt("phonenumber"));
                                    people.setAddress(jsonObject.getString("address"));
                                    people.setEmail(jsonObject.getString("email"));
                                    people.setService(jsonObject.getString("service"));
                                    textView.setText(people.getService());
                                } catch (JSONException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        textView.setText("That didn't work!");
                    }
                }){
                    protected Map<String, String> getParams(){
                        Map<String, String> paramV = new HashMap<>();
//                        paramV.put("username","hello");
                        return paramV;
                    }
                };
                queue.add(stringRequest);
            }
        });
    }
}