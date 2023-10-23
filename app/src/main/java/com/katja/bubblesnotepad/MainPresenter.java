package com.katja.bubblesnotepad;

import android.content.Context;
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
        noteManager.loadNotesFromSharedPreferences();
    }

    //Methods
    @Override
    public void createNoteClicked() {
        navigator.navigateToActivity(EditActivity.class);

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
    public Note editNoteClicked(Note note) {
        navigator.navigateToActivityWithExtra(EditActivity.class, "noteToEdit", note);
        return note;
    }

    @Override
    public void updateNote(Note updatedNote) {
        noteManager.updateNote(updatedNote); // Update the note currently in Edit mode
        navigator.navigateToActivity(MainActivity.class);
    }

    @Override
    public void onNoteListCreated() {
        List<Note> notes = noteManager.getNotes();
        view.showNotes(notes);
        noteManager.initializeNextId();
    }

}
