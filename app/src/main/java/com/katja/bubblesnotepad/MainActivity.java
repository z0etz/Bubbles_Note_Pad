package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;
import android.util.Log;
import com.katja.bubblesnotepad.NoteAdapter;

public class MainActivity extends AppCompatActivity implements MainContract.View, NoteAdapter.OnItemClickListener {

    MainContract.Presenter presenter;

    RecyclerView rvNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNoteList = findViewById(R.id.rvNoteList);
        Button bNewNote = findViewById(R.id.bNewNote);

        // Initiate presenter and show saved notes in RecyclerView
        presenter = new MainPresenter(new NoteManager(), this, this, new Navigator(this));
        presenter.loadAllNotes();
        presenter.onNoteListCreated();

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvNoteList.setLayoutManager(layoutManager);
        // Apply ItemDecoration to be able to scroll the last note in the RecyclerView past the goldfish
        // TODO: Fixa problemet med att bubblan nere till vänster stretchar till 3x sin storlek när jämnt antal element visas
        int bottomSpace = getResources().getDimensionPixelSize(R.dimen.bottom_space);
        rvNoteList.addItemDecoration(new BottomSpaceItemDecoration(bottomSpace));

        // Open edit mode of a note when it is clicked
        bNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createNoteClicked();

            }
        });

    }

    //Method to show notes in RecyclerView
    @Override
    public void showNotes(List<Note> notes) {
        System.out.println(notes);
        NoteAdapter adapter = new NoteAdapter(this,notes,this);
        rvNoteList.setAdapter(adapter);

    }

    //Method to open Edit mode for a note when it is clicked, pass the clicked note to the EditActivity for editing
    @Override
    public void onItemClick(Note note) {
        presenter.editNote(note);
    }
    protected void onPause() {
        super.onPause(); // Call the superclass method first
        presenter.saveAllNotes();
    }

}
