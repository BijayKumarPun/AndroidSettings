package com.homay.just.androidsettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * The MainActivity.java hosts overflow menu. Pressing 'Settings' from the overflow menu opens SettingsActivity.
 * The SettingsActivity hosts SettingsFrgament as the main content.
 * SettingsFragment inflates the my_preferences.xml file.
 * <p>
 * <p>
 * Call PreferenceManager.setDefaultValues(..) to start the sharedPreference with the default value. All default values are stated in the R.xml.my_preferences file.
 * <p>
 * <p>
 * The SharedPreference instance can be accessed from anywhere using PreferenceManager.getDefaultSharedPreferences(Context)
 */

public class MainActivity extends AppCompatActivity {
    TextView textViewPreference;

    @Override
    protected void onResume() {
        super.onResume();
        //This is in case the sharedPreference values are changed when inside the SettingsFragment
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Get the data from the sharedPreferences object using the key
        String myEditTextString = sharedPreferences.getString("keyEditText", "This is default value");

        //Showing the data
        textViewPreference.setText(myEditTextString);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invalidateOptionsMenu(); //This calls onCreateOptionsMenu(..)

        //Initialization
        textViewPreference = findViewById(R.id.editText_pref_value);


        PreferenceManager.setDefaultValues(this, R.xml.my_preferences, false);
        //Set readAgain param to True if you want to set the default values every time the app starts


        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //Get the data from the sharedPreferences object using the key
        String myEditTextString = sharedPreferences.getString("keyEditText", "This is default value");

        //Showing the data
        textViewPreference.setText(myEditTextString);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the overflow menu
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case (R.id.menu_settings):
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
        }
        return false;
    }


}
