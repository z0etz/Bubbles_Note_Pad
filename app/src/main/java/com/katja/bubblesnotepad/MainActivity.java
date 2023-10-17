package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements MainContract.View{

    MainContract.Presenter presenter;

    Button bNewNote;

    ListView rvNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void showNotes() {

    }
}