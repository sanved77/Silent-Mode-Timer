package com.sanved.silentmodescheduler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartScreen extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_screen);

        SharedPreferences prefs;
        final SharedPreferences.Editor ed;
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        ed = prefs.edit();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ed.clear();
                ed.commit();
            }
        });

    }
}