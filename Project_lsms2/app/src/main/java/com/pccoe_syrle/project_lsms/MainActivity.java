package com.pccoe_syrle.project_lsms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.pccoe_syrle.project_lsms.ui.home.HomePageActivity;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    TextView signSwitchText;
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
        signSwitchText = findViewById(R.id.signTextButton);

        signSwitchText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,signInActivity.class);
                i.putExtra(key,"Sign in");
                MainActivity.this.startActivity(i);
            }
        });

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