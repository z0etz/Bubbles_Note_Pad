package com.katja.bubblesnotepad;

public class Note {
    private String noteName;

    private String noteText;

    // Constructor
    public Note(String noteName, String noteText) {
        this.noteName = noteName;
        this.noteText = noteText;
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

    //Other methods
    public String toString() {
        return noteName + ": " + noteText;
    }
}