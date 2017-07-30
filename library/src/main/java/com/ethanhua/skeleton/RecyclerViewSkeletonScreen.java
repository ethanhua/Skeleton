package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class RecyclerViewSkeletonScreen implements SkeletonScreen {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mActualAdapter;
    private ShimmerAdapter mSkeletonAdapter;
    private int mAdapterItemResId;
    private int mItemCount;


    private RecyclerViewSkeletonScreen(RecyclerView recyclerView,
                                       RecyclerView.Adapter adapter,
                                       int adapterItemResId,
                                       int itemCount) {
        this.mRecyclerView = recyclerView;
        mActualAdapter = adapter;
        mAdapterItemResId = adapterItemResId;
        mItemCount = itemCount;
        init();
    }

    private void init() {
        mSkeletonAdapter = new ShimmerAdapter();
        mSkeletonAdapter.setLayoutReference(mAdapterItemResId);
        mSkeletonAdapter.setItemCount(mItemCount);
    }


    @Override
    public void show() {
        mRecyclerView.setAdapter(mSkeletonAdapter);
    }

    @Override
    public void hide() {
        mRecyclerView.setAdapter(mActualAdapter);
    }


    public static class builder {
        private RecyclerView.Adapter mActualAdapter;
        private int mAdapterItemResId;
        private RecyclerView mRecyclerView;
        private int mItemCount = 10;

        public builder(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        public builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        public builder placeHolder(int itemLayoutResId) {
            this.mAdapterItemResId = itemLayoutResId;
            return this;
        }

        public builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        public RecyclerViewSkeletonScreen show() {
            RecyclerViewSkeletonScreen recyclerViewSkeleton = new RecyclerViewSkeletonScreen(mRecyclerView, mActualAdapter, mAdapterItemResId, mItemCount);
            recyclerViewSkeleton.show();
            return recyclerViewSkeleton;
        }

    }
}
