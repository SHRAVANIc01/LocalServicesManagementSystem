package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class loginActivity extends AppCompatActivity {

    String key = "com.pccoe_lsms.shravani.signup";
    EditText username,password;
    Button login;
    TextView signSwitchText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);
        signSwitchText = findViewById(R.id.signTextButton);

        signSwitchText.setOnClickListener(v -> {
            Intent i = new Intent(loginActivity.this,signInActivity.class);
            i.putExtra(key,"Sign in");
            loginActivity.this.startActivity(i);
        });

        login.setOnClickListener(v -> {
            String susername,spassword;
            susername = username.getText().toString();
            spassword = password.getText().toString();
            if(!susername.equals("") && !spassword.equals("")) {
                Handler handler = new Handler();
                handler.post(() -> {
                    String[] field = new String[2];
                    field[0] = "username";
                    field[1] = "password";

                    String[] data = new String[2];
                    data[0] = susername;
                    data[1] = spassword;

                    PutData pd = new PutData("http://192.168.1.5/Loginsignin/login.php","POST",field,data);
                    if (pd.startPut()){
                        if(pd.onComplete()){
                            String result = pd.getResult();
                            if(result.equals("Login Success")){
                                Toast.makeText(loginActivity.this, result, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(loginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            else {
                                Toast.makeText(loginActivity.this, result, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
            else {
                Toast.makeText(loginActivity.this, "Username or password error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
