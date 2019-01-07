package com.homay.just.androidsettings;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.util.Log;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * PreferenceFragmentCompat supports backward compatibility
 */
public class SettingsFragment extends PreferenceFragmentCompat {
    private String TAG = "Inside SettingsFragment";

    SharedPreferences.OnSharedPreferenceChangeListener myListener;

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.my_preferences);


        myListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

                Log.i(TAG, "onSharedPreferenceChanged: " + sharedPreferences.getString(key, "nothing"));
                if (key.equals("keyEditText")) {
                    Preference preference = findPreference(key);
                    preference.setSummary(sharedPreferences.getString(key, ""));
                }
                if (sharedPreferences instanceof EditTextPreference) {
                    EditTextPreference e = (EditTextPreference) sharedPreferences;
                    e.setSummary(sharedPreferences.getString(key, "something"));
                }
            }
        };


        //Setting default values on start
        //Iterate through each preference; Check if they are a Visible preference object or just an instance of PreferenceCategory;
        //If visible preference then find its type and set summary
        //Else iterate again through sub categories of PreferenceCategory, find the type of sub category and set summary.
        //Repeat

        for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
            pickPreferenceObject(getPreferenceScreen().getPreference(i));
        }

    }

    private void pickPreferenceObject(Preference preference) {
        if (preference instanceof android.support.v7.preference.PreferenceCategory) {
            android.support.v7.preference.PreferenceCategory cat = (android.support.v7.preference.PreferenceCategory) preference;
            for (int i = 0; i < cat.getPreferenceCount(); i++) {
                pickPreferenceObject(cat.getPreference(i));
            }
        } else {
            initSummary(preference);
        }
    }

    private void initSummary(Preference preference) {
        //Notice 'android.support.v7.preference.EditTextPreference ' vs 'EditTextPreference'
        if (preference instanceof android.support.v7.preference.EditTextPreference) {
            android.support.v7.preference.EditTextPreference editTextPreference = (android.support.v7.preference.EditTextPreference) preference;
            editTextPreference.setSummary(editTextPreference.getText());
        } else if (preference instanceof android.support.v7.preference.ListPreference) {
            //Place your own code here
        } else {
            //Place your own code here
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(myListener);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(myListener);

    }
}
