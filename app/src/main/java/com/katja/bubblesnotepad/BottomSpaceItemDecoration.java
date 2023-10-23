package com.katja.bubblesnotepad;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class BottomSpaceItemDecoration extends RecyclerView.ItemDecoration {
    //Hur mycket utrymme som läggs till längst ner i recycler view
    private final int space;

    public BottomSpaceItemDecoration(int space) {

        this.space = space;
    }

    @Override
    //TODO: Fixa problemet med outRect och view nedan - "Not annotated parameter overrides @NonNull parameter"
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