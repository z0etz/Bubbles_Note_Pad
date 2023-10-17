package com.katja.bubblesnotepad;

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
    public Note createNoteClicked() {
        navigator.navigateToActivity(EditActivity.class);
        return null;
    }

    @Override
    public void removeNote(Note note) {

    }

    @Override
    public Note editNote(Note note) {
        return null;
    }
}
