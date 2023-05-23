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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DBhelperService {

    public static JSONArray jsonArray;

    private static ArrayList<ServiceProviderClass> listitem;

    public static ArrayList<ServiceProviderClass> fetchData(Context context){

        listitem = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://192.168.1.5/Loginsignin/fetchData.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    jsonArray = new JSONArray(response);
                    for(int i =0;i<5;i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        long phone = jsonObject.getInt("phonenumber");
                        String adrs = jsonObject.getString("address");
                        String email = jsonObject.getString("email");
                        String service = jsonObject.getString("service");

                        listitem.add(new ServiceProviderClass(name, email, adrs, service, phone));
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("username","hello");
                return paramV;
            }
        };
        queue.add(stringRequest);

        return listitem;
    }
}
