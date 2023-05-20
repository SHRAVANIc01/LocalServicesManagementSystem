package com.pccoe_syrle.project_lsms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class signInActivity extends AppCompatActivity {
    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent i = getIntent();
        setContentView(R.layout.signup_activity);
        String value = i.getStringExtra("com.pccoe_lsms.shravani.signup");

        login = findViewById(R.id.switchLog);
        String text = getString(R.string.log);
        SpannableString ss = new SpannableString(text);

        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(signInActivity.this,MainActivity.class);
                signInActivity.this.startActivity(intent);
            }
        };

        ss.setSpan(cs, 26,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login.setText(ss);
        login.setMovementMethod(LinkMovementMethod.getInstance());
    }
}