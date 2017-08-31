package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ShimmerAdapter extends RecyclerView.Adapter<ShimmerViewHolder> {

    private int mItemCount;
    private int mLayoutReference;
    private int mColor;

    @Override
    public ShimmerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ShimmerViewHolder(inflater, parent, mLayoutReference);
    }

    @Override
    public void onBindViewHolder(ShimmerViewHolder holder, int position) {
        ShimmerLayout layout = (ShimmerLayout) holder.itemView;

        layout.setShimmerColor(mColor);
        layout.startShimmerAnimation();
    }

    @Override
    public int getItemCount() {
        return mItemCount;
    }

    public void setLayoutReference(int layoutReference) {
        this.mLayoutReference = layoutReference;
    }

    public void setItemCount(int itemCount) {
        this.mItemCount = itemCount;
    }

    public void setColor(int color) {
        this.mColor = color;
    }
}
