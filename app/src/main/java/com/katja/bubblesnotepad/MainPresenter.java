package com.katja.bubblesnotepad;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    NoteManager noteManager;
    MainContract.View view;
    Navigator navigator;
    Context context;

    //Constructor
    public MainPresenter(NoteManager noteManager, Context context, MainContract.View view, Navigator navigator) {
        this.context = context;
        this.noteManager = noteManager;
        this.view = view;
        this.navigator = navigator;
    }

    //Methods
    @Override
    public void createNoteClicked() {
        navigator.navigateToActivity(EditActivity.class);
    }

    @Override
    public void noteClicked() {

    }

    @Override
    public void cancelEdit() {
        navigator.navigateToActivity(MainActivity.class);
    }

    @Override
    public void removeNote(Note note) {
        noteManager.removeNote(note); // Remove the note currently in Edit mode
        navigator.navigateToActivity(MainActivity.class);
    }

    public void saveNote(String noteName, String noteText) {
        noteManager.createNote(noteName, noteText);
        navigator.navigateToActivity(MainActivity.class);
    }

    @Override
    public Note editNote(Note note) {
        navigator.navigateToActivityWithExtra(EditActivity.class, "noteToEdit", note);
        return note;
    }

    @Override
    public void updateNote(Note updatedNote) {
        noteManager.updateNote(updatedNote); // Update the note in your data source
        navigator.navigateToActivity(MainActivity.class);
    }

    @Override
    public void onNoteListCreated() {
        List<Note> notes = noteManager.getNotes();
        view.showNotes(notes);
    }

    @Override
    public String saveAllNotes() {
        noteManager.saveNotesToSharedPreferences(context);
        // TODO: Vill jag returnera något?
        return null;
    }

    @Override
    public void loadAllNotes() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("notes", Context.MODE_PRIVATE);
        String jsonArray = sharedPreferences.getString("notes_key", null);
        if (jsonArray != null) {
            noteManager.loadNotesFromSharedPreferences(jsonArray);
        }

    }
}
