package com.katja.bubblesnotepad;

// App built according to MVP
// GitHub: https://github.com/z0etz/Bubbles_Note_Pad.git (Jag har nu i efterhand förstått att jag inte bör jobba direkt mot master även när jag bygger en app själv)
// Kommentarer på svenska kan bortses ifrån, de är sparade minneasanteckningar för min egen del.

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

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
        presenter = new MainPresenter(new NoteManager(this), this, this, new Navigator(this));
        presenter.onNoteListCreated();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rvNoteList.setLayoutManager(layoutManager);
        // Apply ItemDecoration to be able to scroll the last note in the RecyclerView past the goldfish
        int bottomSpace = getResources().getDimensionPixelSize(R.dimen.bottom_space);
        rvNoteList.addItemDecoration(new BottomSpaceItemDecoration(bottomSpace));

        // Open edit mode of a note when it is clicked
        bNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createNoteClicked();
                finish();

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

    @Override
    public void customToast(String text) {

    }

    //Method to open Edit mode for a note when it is clicked, pass the clicked note to the EditActivity for editing
    @Override
    public void onItemClick(Note note) {
        presenter.editNoteClicked(note);
    }

}
