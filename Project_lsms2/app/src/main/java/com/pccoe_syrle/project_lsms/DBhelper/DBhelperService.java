package com.pccoe_syrle.project_lsms.DBhelper;

import android.content.Context;
import android.widget.Toast;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBhelperService {

    public interface DataCallback {
        void onDataFetched(ArrayList<ServiceProviderClass> listitem);

        void onError(String errorMessage);
    }

    public static void fetchData(Context context, final DataCallback callback) {

        ArrayList<ServiceProviderClass> listitem = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "http://192.168.167.140/Loginsignin/fetchData.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        long phone = jsonObject.getLong("phonenumber");
                        String address = jsonObject.getString("address");
                        String email = jsonObject.getString("email");
                        String service = jsonObject.getString("service");
                        Long price = jsonObject.getLong("price");

                        listitem.add(new ServiceProviderClass(name, email, address, service, phone, price));
                    }
                    callback.onDataFetched(listitem);
                } catch (JSONException e) {
                    callback.onError("Error parsing JSON data");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError("Something went wrong");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> paramV = new HashMap<>();
                paramV.put("username", "hello");
                return paramV;
            }
        };
        queue.add(stringRequest);
    }
}
