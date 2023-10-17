package com.katja.bubblesnotepad;

import java.util.ArrayList;

public class NoteManager {
    private static ArrayList<Note> notes = new ArrayList<>();

    public Note createNote(String noteName, String noteText){
        Note note = new Note(noteName, noteText);
        notes.add(note);
        return note;
    }
}
