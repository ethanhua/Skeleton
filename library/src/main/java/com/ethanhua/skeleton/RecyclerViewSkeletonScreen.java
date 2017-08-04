package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class RecyclerViewSkeletonScreen implements SkeletonScreen {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mActualAdapter;
    private ShimmerAdapter mSkeletonAdapter;


    private RecyclerViewSkeletonScreen(RecyclerView recyclerView,
                                       RecyclerView.Adapter adapter,
                                       int itemResID,
                                       int itemCount) {
        this.mRecyclerView = recyclerView;
        mActualAdapter = adapter;
        int mItemResID = itemResID;
        int mItemCount = itemCount;
        mSkeletonAdapter = new ShimmerAdapter();
        mSkeletonAdapter.setItemCount(mItemCount);
        mSkeletonAdapter.setLayoutReference(mItemResID);
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

        public Builder(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        public Builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        public Builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        public Builder load(int skeletonLayoutResID) {
            this.mItemResID = skeletonLayoutResID;
            return this;
        }

        public RecyclerViewSkeletonScreen show() {
            RecyclerViewSkeletonScreen recyclerViewSkeleton = new RecyclerViewSkeletonScreen(mRecyclerView, mActualAdapter, mItemResID, mItemCount);
            recyclerViewSkeleton.show();
            return recyclerViewSkeleton;
        }
    }
}
