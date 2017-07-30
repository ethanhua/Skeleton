package com.ethanhua.skeleton.sample.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ethanhua.skeleton.sample.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class PersonAdapter extends RecyclerView.Adapter<SimpleRcvViewHolder> {
    private List mDatas;

    public PersonAdapter(List datas) {
        this.mDatas = datas == null ? new ArrayList<>() : datas;
    }

    @Override
    public SimpleRcvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SimpleRcvViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_person, parent, false));
    }

    @Override
    public void onBindViewHolder(SimpleRcvViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

}
