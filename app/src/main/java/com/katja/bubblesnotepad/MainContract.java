package com.katja.bubblesnotepad;

import com.google.gson.Gson;

import java.util.List;

public interface MainContract {
    interface View {
        void showNotes(List<Note> notes);


    }

    interface Presenter {
        void createNoteClicked();

        void noteClicked();

        void cancelEdit();

        void removeNote(Note note);

        void saveNote(String noteName, String noteText);

        Note editNote(Note note);

        default void updateNote(Note updatedNote) {

        }

        void onNoteListCreated();

    }
}