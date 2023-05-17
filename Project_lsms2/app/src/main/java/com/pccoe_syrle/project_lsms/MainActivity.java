package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.RecyclerView;
import com.pccoe_syrle.project_lsms.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    TextView textView;
    String key = "com.pccoe_lsms.shravani.signup";
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.login_activity);


        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);
        textView = findViewById(R.id.switchSign);

        String text = getString(R.string.sign);
        SpannableString ss = new SpannableString(text);

        ClickableSpan click1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent intent = new Intent(MainActivity.this, signInActivity.class);
                intent.putExtra(key,"signup kro");
                MainActivity.this.startActivity(intent);
            }
        };

        ss.setSpan(click1, 24,31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, HomePageActivity.class);
                intent.putExtra(key,"Homepage");
                MainActivity.this.startActivity(intent);
            }
        });

//        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
    }


}