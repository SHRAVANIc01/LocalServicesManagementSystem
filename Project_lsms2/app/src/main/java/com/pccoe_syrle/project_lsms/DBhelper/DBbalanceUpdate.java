package com.pccoe_syrle.project_lsms.DBhelper;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pccoe_syrle.project_lsms.ServiceProviderClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DBbalanceUpdate {

    public static void updateBalance(long balance, Context context, String email){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.167.140/Loginsignin/UpdateBalance.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("Balance", balance+"");
                paramV.put("Email", email);
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}
