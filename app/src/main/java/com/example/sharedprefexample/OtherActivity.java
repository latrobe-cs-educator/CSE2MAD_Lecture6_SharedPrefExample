package com.example.sharedprefexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    private TextView userNamePref, syncPicPref;
    private SharedPreferences sharedpreferences;
    private String name;
    private Boolean boolValue;
    private String TAG = "OtherAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        //get package name & amend pref
        String MyPREFERENCES = getApplicationContext().getPackageName() + ".PREFERENCE_FILE_KEY";
        Log.d(TAG, MyPREFERENCES);
        userNamePref = (TextView) findViewById(R.id.userPrefTV);
        syncPicPref = (TextView) findViewById(R.id.syncPrefTV);

        //Create or access share preference file
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        //get values from prefs
        name = sharedpreferences.getString("Username", "");
        Log.d(TAG, "Username retrieved from shared pref: " + name);
        boolValue = sharedpreferences.getBoolean("SyncBoolean", false);
        Log.d(TAG, "SyncBoolean retrieved from shared pref: " + boolValue.toString());

        //set text views
        userNamePref.setText(sharedpreferences.getString("Username", ""));
        syncPicPref.setText(boolValue.toString());
    }
}