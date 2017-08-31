package com.ethanhua.skeleton;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class RecyclerViewSkeletonScreen implements SkeletonScreen {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mActualAdapter;
    private ShimmerAdapter mSkeletonAdapter;

    private RecyclerViewSkeletonScreen(Builder builder) {
        this.mRecyclerView = builder.mRecyclerView;
        this.mActualAdapter = builder.mActualAdapter;

        this.mSkeletonAdapter = new ShimmerAdapter();
        this.mSkeletonAdapter.setItemCount(builder.mItemCount);
        this.mSkeletonAdapter.setLayoutReference(builder.mItemResID);
        this.mSkeletonAdapter.setColor(builder.mColor);
    }

    @Override
    public void show() {
        mRecyclerView.setAdapter(mSkeletonAdapter);
        if (!mRecyclerView.isComputingLayout()) {
            mRecyclerView.setLayoutFrozen(true);
        }
    }

    @Override
    public void hide() {
        mRecyclerView.setAdapter(mActualAdapter);
        if (!mRecyclerView.isComputingLayout()) {
            mRecyclerView.setLayoutFrozen(false);
        }
    }

    public static class Builder {
        private RecyclerView.Adapter mActualAdapter;
        private RecyclerView mRecyclerView;
        private int mItemCount = 10;
        private int mItemResID = R.layout.layout_default_item_skeleton;
        private int mColor;
        private Context mContext;

        public Builder(RecyclerView recyclerView, Context context) {
            this.mRecyclerView = recyclerView;
            this.mContext = context;

            this.mColor = ContextCompat.getColor(context, R.color.shimmer_color);
        }

        public Builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        public Builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        public Builder color(int color) {
            this.mColor = ContextCompat.getColor(mContext, color);
            return this;
        }

        public Builder load(int skeletonLayoutResID) {
            this.mItemResID = skeletonLayoutResID;
            return this;
        }

        public RecyclerViewSkeletonScreen show() {
            RecyclerViewSkeletonScreen recyclerViewSkeleton = new RecyclerViewSkeletonScreen(this);
            recyclerViewSkeleton.show();

            return recyclerViewSkeleton;
        }
    }
}
