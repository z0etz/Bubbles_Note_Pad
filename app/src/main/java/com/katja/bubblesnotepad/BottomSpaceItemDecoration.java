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
        if (parent.getChildLayoutPosition(view) == state.getItemCount() - 1) {
            // Apply space only to the last item
            outRect.bottom = space;
        }

        // Alternativ metod, tanken var att lösa ett problem med stretching, men resultatet blev samma.
//    @Override
//    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        int position = parent.getChildLayoutPosition(view);
//        if (position == state.getItemCount() - 1) {
//            // If it's the last item, apply space to the bottom
//            outRect.bottom = space;
//        } else {
//            // If it's not the last item, apply no space
//            outRect.bottom = 0;
//        }
    }
}