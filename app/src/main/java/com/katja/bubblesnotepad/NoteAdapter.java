package com.katja.bubblesnotepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private List<Note> notes;
    private OnItemClickListener clickListener;

    // Constructor
    public NoteAdapter(Context context, List<Note> notes, OnItemClickListener clickListener) {
        this.notes = notes;
        this.clickListener = clickListener;
    }

    // Interface for item click callbacks
    public interface OnItemClickListener {
        void onItemClick(Note note);
    }


    // Methods
    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bubble, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        Note note = notes.get(position);
        holder.tv.setText(note.getNoteName());

        // Set a click listener for the item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(note);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public NoteViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.noteTitle);
        }
    }
}