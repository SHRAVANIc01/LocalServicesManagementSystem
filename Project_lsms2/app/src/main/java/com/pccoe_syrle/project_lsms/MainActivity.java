package com.pccoe_syrle.project_lsms;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.pccoe_syrle.project_lsms.databinding.ActivityMainBinding;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    EditText username,password;
    Button login;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.login_activity);

        getSupportActionBar().hide();

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.buttonLogin);

        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                new Task().execute();
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

    class Task extends AsyncTask<Void ,Void ,Void>{
        String user, pass, error;

        @Override
        protected Void doInBackground(Void... voids)
        {
            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection conn = DriverManager.getConnection("mysql://192.168.1.9/lsms","lsms","lsms");
                Statement st = conn.createStatement();
                ResultSet resultSet = st.executeQuery("show tables");
            }
            catch(Exception e){
                error = e.toString();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused)
        {
            Toast.makeText(MainActivity.this, "showing tables", Toast.LENGTH_SHORT).show();
            super.onPostExecute(unused);
        }
    }
}