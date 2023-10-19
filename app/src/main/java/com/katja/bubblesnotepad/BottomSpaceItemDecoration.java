package com.katja.bubblesnotepad;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BottomSpaceItemDecoration extends RecyclerView.ItemDecoration {
    //Hur mycket utrymme som läggs till längst ner i recycler view
    private int space;

    public BottomSpaceItemDecoration(int space) {

        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);
        if (position == state.getItemCount() - 1) {
            // Apply space only to the last visible item
            outRect.bottom = space;
        }
    }

}