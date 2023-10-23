package com.katja.bubblesnotepad;

import java.io.Serializable;

public class Note implements Serializable {
    private String noteName;

    private String noteText;

    private int id; // Unique identifier

    // Constructor
    public Note(int id, String noteName, String noteText) {
        this.noteName = noteName;
        this.noteText = noteText;
        this.id = id;
    }

    //Getters and setters
    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }

    public int getId() {
        return id;
    }

    //Other methods
    public String toString() {
        return noteName;
    }
}