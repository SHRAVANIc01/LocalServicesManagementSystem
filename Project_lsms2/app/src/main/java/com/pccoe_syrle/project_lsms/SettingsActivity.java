package com.pccoe_syrle.project_lsms;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {
    ListView settingList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        settingList=findViewById(R.id.settings_list);
        ArrayList<ModelClass2> settings = new ArrayList<ModelClass2>();
        settings.add(new ModelClass2(R.drawable.account_person_24,"Appearance"));
        settings.add(new ModelClass2(R.drawable.account_person_24,"about"));
        settings.add(new ModelClass2(R.drawable.account_person_24,"permissions"));
        settings.add(new ModelClass2(R.drawable.account_person_24,"favourites"));

        SettingsArrayAdapter adapter_setting = new SettingsArrayAdapter(this, settings);
        settingList.setAdapter(adapter_setting);
    }
}
