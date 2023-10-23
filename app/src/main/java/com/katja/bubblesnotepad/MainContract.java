package com.katja.bubblesnotepad;

import java.util.List;

public interface MainContract {
    interface View {
        void showNotes(List<Note> notes);

        void customToast(String text);


    }

    interface Presenter {
        void createNoteClicked();

        void cancelEdit();

        void removeNote(Note note);

        void saveNote(String noteName, String noteText);

        Note editNoteClicked(Note note);

        default void updateNote(Note updatedNote) {

        }

        void onNoteListCreated();

    }
}