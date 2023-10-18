package com.katja.bubblesnotepad;

import java.util.List;

public interface MainContract {
    interface View {
        void showNotes(List<Note> notes);


    }

    interface Presenter {
        void createNoteClicked();

        void removeNote(Note note);

        void saveNote(String noteName, String noteText);

        Note editNote(Note note);

        void onNoteListCreated();


    }
}