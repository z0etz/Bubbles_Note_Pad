package com.katja.bubblesnotepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    MainContract.Presenter presenter;

    Button bNewNote;

    RecyclerView rvNoteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvNoteList = findViewById(R.id.rvNoteList);
        bNewNote = findViewById(R.id.bNewNote);

        presenter = new MainPresenter(new NoteManager(), this, new Navigator(this));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvNoteList.setLayoutManager(layoutManager);

        presenter.onNoteListCreated();

        bNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.createNoteClicked();

            }
        });

    }

    @Override
    public void showNotes(List<Note> notes) {
        NoteAdapter adapter = new NoteAdapter(this,notes);
        rvNoteList.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        presenter.onNoteListCreated();
        super.onResume();
    }
}
