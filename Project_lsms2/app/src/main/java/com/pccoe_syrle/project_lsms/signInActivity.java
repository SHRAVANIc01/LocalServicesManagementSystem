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

    EditText username,password,email,address,phoneNumber,mname;
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
        mname = findViewById(R.id.name);
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

        ss.setSpan(cs, 25,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(ss);
        login.setMovementMethod(LinkMovementMethod.getInstance());

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String susername,spassword,sname,semail,saddress,sphoneNumber;
                susername = username.getText().toString();
                sname = mname.getText().toString();
                spassword = password.getText().toString();
                semail = email.getText().toString();
                saddress = address.getText().toString();
                sphoneNumber = phoneNumber.getText().toString();
                if(!susername.equals("") && !spassword.equals("") && !semail.equals("") && !saddress.equals("") && !sphoneNumber.equals("")) {
                    Handler handler = new Handler();
                    handler.post(() -> {
                        String[] field = new String[6];
                        field[0] = "Username";
                        field[1] = "Name";
                        field[2] = "Phonenumber";
                        field[3] = "Address";
                        field[4] = "Email";
                        field[5] = "Password";

                        String[] data = new String[6];
                        data[0] = susername;
                        data[1] = sname;
                        data[2] = sphoneNumber;
                        data[3] = saddress;
                        data[4] = semail;
                        data[5] = spassword;

                        PutData pd = new PutData("http://192.168.167.140/Loginsignin/signup.php","POST",field,data);
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
                    });
                }
                else {
                    Toast.makeText(signInActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}