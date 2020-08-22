package com.example.sharedprefexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Switch sw;
    private TextView nameTV;
    private SharedPreferences sharedpreferences;
    private Boolean syncBool = false;
    private String TAG = "MainAct";
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get package name & amend pref
        String MyPREFERENCES = getApplicationContext().getPackageName() + ".PREFERENCE_FILE_KEY";

        //Create or access share preference file
        sharedpreferences = getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);

        //Textview
        nameTV = (TextView) findViewById(R.id.nameTV);
        sw = (Switch) findViewById(R.id.syncPic);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        //Button
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendSave(view);
                Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                startActivity(intent);
            }
        });

        //Switch
        sw = (Switch) findViewById(R.id.syncPic);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    syncBool = true;
                    Log.d(TAG, "switch true");
                } else {
                    syncBool = false;
                    Log.d(TAG, "switch false");
                }
            }
        });
    }

    public void sendSave(View view) {
        SharedPreferences.Editor myEdit = sharedpreferences.edit();
        // Storing the keys and their values
        myEdit.putString(
                //key name
                "Username",
                //key data
                nameTV.getText().toString());
        myEdit.putBoolean(
                "SyncBoolean",syncBool);
        myEdit.apply();
        Log.d(TAG, "pref saved");
    }
}