package com.katja.bubblesnotepad;

import java.util.List;

public class MainPresenter implements MainContract.Presenter {

    NoteManager noteManager;

    MainContract.View view;

    Navigator navigator;

    public MainPresenter(NoteManager noteManager, MainContract.View view, Navigator navigator){
        this.noteManager = noteManager;
        this.view = view;
        this.navigator = navigator;
    }
    @Override
    public void createNoteClicked() {
        navigator.navigateToActivity(EditActivity.class);
    }

    @Override
    public void removeNote(Note note) {

    }

    public void saveNote(String noteName, String noteText){
        noteManager.createNote(noteName, noteText);
        navigator.navigateToActivity(MainActivity.class);
    }
    @Override
    public Note editNote(Note note) {
        return null;
    }

    @Override
    public void onNoteListCreated() {
        List<Note> notes = noteManager.getNotes();
        view.showNotes(notes);
    }
}

