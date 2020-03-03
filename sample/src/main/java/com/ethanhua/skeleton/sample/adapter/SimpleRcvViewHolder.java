package com.ethanhua.skeleton.sample.adapter;

import android.util.SparseArray;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;


public class SimpleRcvViewHolder extends RecyclerView.ViewHolder {
    private final SparseArray<View> views = new SparseArray<>();

    public SimpleRcvViewHolder(View itemView) {
        super(itemView);
    }

    @SuppressWarnings("unchecked")
    public <V extends View> V getView(int resId) {
        View v = views.get(resId);
        if (null == v) {
            v = itemView.findViewById(resId);
            views.put(resId, v);
        }
        return (V) v;
    }
}