package com.katja.bubblesnotepad;

public interface MainContract {
    interface View {
        void showNotes();


    }

    interface Presenter{
        Note createNoteClicked();

        void removeNote(Note note);

        Note editNote(Note note);

    }

}
