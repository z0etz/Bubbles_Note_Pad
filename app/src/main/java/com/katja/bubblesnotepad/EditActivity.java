package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
            if (noteToEdit.getNoteName() != null) {
                etNoteTitle.setText(noteToEdit.getNoteName());
            }
            else {
                etNoteTitle.setText("");
            }
            if (noteToEdit.getNoteText() != null) {
                etNoteText.setText(noteToEdit.getNoteText());
            }
            else {
                etNoteText.setText("");
            }
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
                    customToast(title + " saved");
                    presenter.updateNote(noteToEdit);
                    finish();
                } else {
                    // Create a new note
                    customToast(title + " saved");
                    presenter.saveNote(title, text);
                    finish();
                }
            }
        });

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.cancelEdit();
                finish();
            }
        });

        bDeleteNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noteToEdit != null) {
                    // Throw toast and remove the note currently displayed
                    customToast(noteToEdit.getNoteName() + " deleted");
//                    Alternativ med standard-toast, sparad för framtida referens;
//                    Toast.makeText(EditActivity.this, noteToEdit.getNoteName() + " deleted", Toast.LENGTH_LONG).show();
//                    Snyggare alternativ till toast, som dock inte "följer med" när ny aktivitet startas (anteckning inför framtida projekt):
//                    Snackbar.make(findViewById(R.id.editActivityConstraintLayout), title + " saved", Snackbar.LENGTH_LONG);
                    presenter.removeNote(noteToEdit);
                    finish();
                } else {
                    // Cancel creating a new note
                    presenter.cancelEdit();
                    finish();
                }
            }
        });
    }


    @Override
    public void showNotes(List<Note> notes) {

    }

    @Override
    public void customToast(String text) {
        // Inflate the custom layout
        LayoutInflater inflater = getLayoutInflater();
        View customToastView = inflater.inflate(R.layout.custom_toast, null);

        // Set the text message
        TextView messageTextView = customToastView.findViewById(R.id.custom_toast_message);
        messageTextView.setText(text);

        // Create and show the Toast
        Toast customToast = new Toast(getApplicationContext());
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.setView(customToastView);
        customToast.show();

    }
}
