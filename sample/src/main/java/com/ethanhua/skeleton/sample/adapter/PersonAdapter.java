package com.ethanhua.skeleton.sample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.sample.R;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class PersonAdapter extends RecyclerView.Adapter<SimpleRcvViewHolder> {

    @Override
    public SimpleRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleRcvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

}
