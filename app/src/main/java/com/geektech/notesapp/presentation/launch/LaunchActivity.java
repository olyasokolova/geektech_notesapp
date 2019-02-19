package com.geektech.notesapp.presentation.launch;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.intro.IntroActivity;
import com.geektech.notesapp.presentation.main.MainActivity;

public class LaunchActivity extends AppCompatActivity {

    private static String PREF_FIRST_LAUNCH = "first_launch";
    SharedPreferences mPref;
    boolean firstLaunch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        mPref = getSharedPreferences(PREF_FIRST_LAUNCH, Context.MODE_PRIVATE);

        if (isFirstLaunch()) {
            IntroActivity.start(this);
            FirstLaunch();
        } else {
            MainActivity.start(this);
        }
    }


    //TODO: Save to shared prefs on first launch
    private boolean isFirstLaunch() {
        if(mPref.contains(PREF_FIRST_LAUNCH)) {
            return mPref.getBoolean(PREF_FIRST_LAUNCH,true);
        }
        return true;
    }

    private void FirstLaunch(){
        firstLaunch = false;
        SharedPreferences.Editor editor = mPref.edit();
        editor.putBoolean(PREF_FIRST_LAUNCH, firstLaunch);
        editor.apply();
    }
}
