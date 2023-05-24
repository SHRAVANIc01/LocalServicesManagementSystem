package com.pccoe_syrle.project_lsms.ui.profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.pccoe_syrle.project_lsms.CustomerClass;
import com.pccoe_syrle.project_lsms.MainActivity;
import com.pccoe_syrle.project_lsms.SettingsActivity;
import com.pccoe_syrle.project_lsms.WalletActivity;
import com.pccoe_syrle.project_lsms.databinding.FragmentProfileBinding;
import com.pccoe_syrle.project_lsms.loginActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
    long bal;
    Bitmap bitmap;
    CircleImageView profilePic;
    CustomerClass profile;
    TextView profileName, profileEmail, profilePhone, profileAddress,profileUsername,balance,logout;
    LinearLayout settingButton, balanceButton;
    SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View root = binding.getRoot();
        settingButton = binding.settingsButton;
        profilePic = binding.profilePicture;
        profileName = binding.profileName;
        profileAddress = binding.profileAddress;
        profileEmail = binding.profileEmail;
        profilePhone = binding.profilePhone;
        profileUsername = binding.profileUsername;
        balanceButton = binding.balanceButton;
        balance = binding.wallet;
        logout = binding.logout;
        profile = new CustomerClass();
        sharedPreferences = getActivity().getSharedPreferences("LSMSshared", Context.MODE_PRIVATE);

        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url ="http://192.168.167.140/Loginsignin/fetchProfile.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if(jsonObject.getString("status").equals("success")) {
                        String name = jsonObject.getString("name");
                        bal = jsonObject.getLong("balance");
                        long phone = jsonObject.getLong("phonenumber");
                        String adrs = jsonObject.getString("address");
                        String email = jsonObject.getString("email");

                        profile = new CustomerClass(name, email, adrs, phone);

                        profileName.setText(profile.getName());
                        profileAddress.setText(profile.getAddress());
                        profileEmail.setText(profile.getEmail());
                        profilePhone.setText(profile.getPhone());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putLong("balance",bal);
                        editor.putString("name",name);
                        editor.apply();
                        balance.setText(bal+"");
                    }
                    else{
                        Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }){
            protected Map<String, String> getParams(){
                Map<String, String> paramV = new HashMap<>();
                paramV.put("Email", sharedPreferences.getString("email",""));
                return paramV;
            }
        };
        queue.add(stringRequest);

        profileUsername.setText(sharedPreferences.getString("username",""));

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    Uri uri = data.getData();
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                        profilePic.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        settingButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SettingsActivity.class);
            getActivity().startActivity(intent);
        });

        profilePic.setOnClickListener(v -> {
            Intent gallery = new Intent(Intent.ACTION_PICK);
            gallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            activityResultLauncher.launch(gallery);
        });

        balanceButton.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), WalletActivity.class);
            getActivity().startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            Intent logout= new Intent(getContext(), loginActivity.class);
            getActivity().startActivity(logout);
            getActivity().finish();
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
