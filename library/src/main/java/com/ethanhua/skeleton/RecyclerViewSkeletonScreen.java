package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class RecyclerViewSkeletonScreen implements SkeletonScreen {


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mActualAdapter;
    private ShimmerAdapter mSkeletonAdapter;
    private int mItemCount;
    private int mItemResId;


    private RecyclerViewSkeletonScreen(RecyclerView recyclerView,
                                       RecyclerView.Adapter adapter,
                                       int itemCount) {
        this.mRecyclerView = recyclerView;
        mActualAdapter = adapter;
        mItemCount = itemCount;
        mSkeletonAdapter = new ShimmerAdapter();
        mSkeletonAdapter.setItemCount(mItemCount);
    }

    @Override
    public SkeletonScreen show(int resId) {
        if (mItemResId == resId) {
            return this;
        }
        mItemResId = resId;
        mSkeletonAdapter.setLayoutReference(resId);
        mRecyclerView.setAdapter(mSkeletonAdapter);
        return this;
    }

    @Override
    public SkeletonScreen hide() {
        mRecyclerView.setAdapter(mActualAdapter);
        return this;
    }


    public static class builder {
        private RecyclerView.Adapter mActualAdapter;
        private RecyclerView mRecyclerView;
        private int mItemCount = 10;

        public builder(RecyclerView recyclerView) {
            this.mRecyclerView = recyclerView;
        }

        public builder adapter(RecyclerView.Adapter adapter) {
            this.mActualAdapter = adapter;
            return this;
        }

        public builder count(int itemCount) {
            this.mItemCount = itemCount;
            return this;
        }

        public RecyclerViewSkeletonScreen show(int resId) {
            RecyclerViewSkeletonScreen recyclerViewSkeleton = new RecyclerViewSkeletonScreen(mRecyclerView, mActualAdapter, mItemCount);
            recyclerViewSkeleton.show(resId);
            return recyclerViewSkeleton;
        }
    }
}
