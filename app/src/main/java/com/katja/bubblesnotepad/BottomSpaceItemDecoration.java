package com.katja.bubblesnotepad;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BottomSpaceItemDecoration extends RecyclerView.ItemDecoration {
    //How much empty space is added at the bottom of the RecyclerView
    private final int space;

    public BottomSpaceItemDecoration(int space) {

        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildLayoutPosition(view);

        // Apply space to the last visible bubble
        if (position == state.getItemCount() - 1) {
            outRect.bottom = space;
        }

        // Apply space to the second to last visible bubble if there is an even number of
        // bubbles displayed (to solve stretching issue)
        if (state.getItemCount() % 2 == 0){
            if (position == state.getItemCount() - 2) {
                outRect.bottom = space;
        }
        }
    }

}