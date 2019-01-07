package com.homay.just.androidsettings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //This sets the default content to be the Fragment
        //android.R.id.content
        //Fragment SettingsFragment is initialized
        getSupportFragmentManager().beginTransaction().add(android.R.id.content, new SettingsFragment()).commit();
    }


}

