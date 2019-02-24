package com.geektech.notesapp.presentation.note;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.geektech.notesapp.R;

public class NoteActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteDesc;

    public static void start(Activity activity) {
        activity.startActivity(new Intent(activity, NoteActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        init();
    }

    private void init(){
        noteTitle = findViewById(R.id.note_title);
        noteDesc = findViewById(R.id.note_desc);
    }
}
