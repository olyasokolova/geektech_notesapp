package com.geektech.notesapp.presentation.main;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.geektech.notesapp.R;
import com.geektech.notesapp.presentation.intro.IntroActivity;
import com.geektech.notesapp.presentation.note.NoteActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private FloatingActionButton fab;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                NoteActivity.start(this);
                finish();
                break;

        }

    }
}
