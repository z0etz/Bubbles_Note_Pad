package com.katja.bubblesnotepad;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class NoteManager {
    private static final String SHARED_PREF_NAME = "notes";
    private static final String SHARED_PREF_KEY = "notes_key";
    private static ArrayList<Note> notes = new ArrayList<>();
    private static int nextId = 0; // Initiate ID counter
    Context context;

    //Constructor
    public NoteManager(Context context){

        this.context = context;
    }

    public void createNote(String noteName, String noteText) {
        int newId = nextId;
        nextId++;

        Note note = new Note(newId, noteName, noteText);
        notes.add(note);
        saveNotesToSharedPreferences();
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void updateNote(Note updatedNote) {
        // Get the list of notes, find the matching note and update its properties
        List<Note> notes = getNotes();

        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);

            if (note.getId() == updatedNote.getId()) {

                note.setNoteName(updatedNote.getNoteName());
                note.setNoteText(updatedNote.getNoteText());

                // Update the note in the list of notes and exit the loop
                notes.set(i, note);
                break;
            }
        }
        saveNotesToSharedPreferences();
    }


    public void removeNote(Note note){
        // Get the list of notes and find the matching note
        List<Note> notes = new ArrayList<>(getNotes());

        for (int i = 0; i < notes.size(); i++) {
            Note currentNote = notes.get(i);

            System.out.println("Current note is: " + currentNote.getId());
            System.out.println(note.getId());

            if (currentNote.getId() == note.getId()) {
                this.notes.remove(i);
                System.out.println(note.getId() + " removed");
                break;
            }
        }
        saveNotesToSharedPreferences();
    }

    public void saveNotesToSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String notesJson = gson.toJson(notes);
        editor.putString(SHARED_PREF_KEY, notesJson);
        editor.apply();
    }

    public void loadNotesFromSharedPreferences() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String jsonArray = sharedPreferences.getString(SHARED_PREF_KEY, null);
        if (jsonArray != null) {
            Gson gson = new Gson();
            notes = gson.fromJson(jsonArray, new TypeToken<ArrayList<Note>>() {}.getType());
        }
    }

    public void initializeNextId() {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        String jsonArray = sharedPreferences.getString(SHARED_PREF_KEY, null);

        if (jsonArray != null) {
            Gson gson = new Gson();
            List<Note> existingNotes = gson.fromJson(jsonArray, new TypeToken<List<Note>>() {}.getType());

            // Find the highest ID among existing notes
            for (Note note : existingNotes) {
                if (note.getId() >= nextId) {
                    nextId = note.getId() + 1;
                }
            }
        }
    }

}

