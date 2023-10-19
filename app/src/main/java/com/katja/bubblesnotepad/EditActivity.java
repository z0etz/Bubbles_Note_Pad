package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class EditActivity extends AppCompatActivity implements MainContract.View{

    MainContract.Presenter presenter;

    EditText etNoteTitle;
    EditText etNoteText;
    Note noteToEdit; // This holds the note being edited

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Button bCancel, bSaveNote, bDeleteNote;

        etNoteTitle = findViewById(R.id.etNoteTitle);

        etNoteText = findViewById(R.id.etNoteText);

        presenter = new MainPresenter(new NoteManager(this), this, this, new Navigator(this));

        // Check if you are in edit mode (noteToEdit is passed from MainActivity)
        if (getIntent().hasExtra("noteToEdit")) {
            noteToEdit = (Note) getIntent().getSerializableExtra("noteToEdit");
            // Pre-fill the EditText fields with existing note data
            etNoteTitle.setText(noteToEdit.getNoteName());
            etNoteText.setText(noteToEdit.getNoteText());
        }

        bSaveNote = findViewById(R.id.bSaveNote);
        bCancel = findViewById(R.id.bCancel);
        bDeleteNote = findViewById(R.id.bDeleteNote);


        bSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etNoteTitle.getText().toString();
                String text = etNoteText.getText().toString();
                if (title.isEmpty()){
                    title = "";
                }
                if (text.isEmpty()){
                    text = "";
                }
                if (noteToEdit != null) {
                    // Update the existing note
                    noteToEdit.setNoteName(title);
                    noteToEdit.setNoteText(text);
                    presenter.updateNote(noteToEdit);
                } else {
                    // Create a new note
                    presenter.saveNote(title, text);
                }

                // TODO: Gör egen design för Toast
                Toast.makeText(EditActivity.this, title + " saved", Toast.LENGTH_LONG).show();
//                Snyggare alternativ till toast, som dock inte "följer med" när ny aktivitet startas (anteckning inför framtida projekt):
//                Snackbar.make(findViewById(R.id.editActivityConstraintLayout), title + " saved", Snackbar.LENGTH_LONG);

            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelEdit();
            }
        });

        bDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteToEdit != null) {
                    // Remove the note currently displayed
                    Toast.makeText(EditActivity.this, noteToEdit.getNoteName() + " deleted", Toast.LENGTH_LONG).show();
                    presenter.removeNote(noteToEdit);
                } else {
                    // Cancel creating a new note
                    presenter.cancelEdit();
                }
            }
        });
    }


    @Override
    public void showNotes(List<Note> notes) {

    }
}
