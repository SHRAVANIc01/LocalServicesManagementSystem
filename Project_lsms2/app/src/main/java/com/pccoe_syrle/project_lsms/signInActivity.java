package com.pccoe_syrle.project_lsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class signInActivity extends AppCompatActivity {
    TextView login;

    EditText username,password,email,address,phoneNumber;
    Button signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.signup_activity);
        String value = i.getStringExtra("com.pccoe_lsms.shravani.signup");

        login = findViewById(R.id.switchLog);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        phoneNumber = findViewById(R.id.phoneNumber);
        signup = findViewById(R.id.buttonSign);
        String text = getString(R.string.log);
        SpannableString ss = new SpannableString(text);

        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(signInActivity.this, loginActivity.class);
                signInActivity.this.startActivity(intent);
            }
        };

        ss.setSpan(cs, 26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(ss);
        login.setMovementMethod(LinkMovementMethod.getInstance());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername,spassword,semail,saddress,sphoneNumber;
                susername = username.getText().toString();
                spassword = password.getText().toString();
                semail = email.getText().toString();
                saddress = address.getText().toString();
                sphoneNumber = phoneNumber.getText().toString();
                if(!susername.equals("") && !spassword.equals("") && !semail.equals("") && !saddress.equals("") && !sphoneNumber.equals("")) {
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "username";
                            field[1] = "phonenumber";
                            field[2] = "address";
                            field[3] = "email";
                            field[4] = "password";

                            String[] data = new String[5];
                            data[0] = susername;
                            data[1] = sphoneNumber;
                            data[2] = saddress;
                            data[3] = semail;
                            data[4] = spassword;

                            PutData pd = new PutData("http://192.168.1.5/Loginsignin/signup.php","POST",field,data);
                            if (pd.startPut()){
                                if(pd.onComplete()){
                                    String result = pd.getResult();
                                    if(result.equals("Sign up Success")){
                                        Toast.makeText(signInActivity.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(signInActivity.this, result, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(signInActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}