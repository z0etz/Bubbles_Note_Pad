package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditActivity extends AppCompatActivity implements MainContract.View{

    MainContract.Presenter presenter;

    Button bCancel;
    Button bSaveNote;
    Button vDeletenote;

    EditText etNoteTitle;
    EditText etNoteText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNoteTitle = findViewById(R.id.etNoteTitle);

        etNoteText = findViewById(R.id.etNoteText);

        presenter = new MainPresenter(new NoteManager(), this, new Navigator(this));

        bSaveNote = findViewById(R.id.bSaveNote);

        bSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNoteTitle.getText().toString();
                String text = etNoteText.getText().toString();
                presenter.saveNote(title, text);
            }

        });
    }
    @Override
    public void showNotes(List<Note> notes) {

    }
}
