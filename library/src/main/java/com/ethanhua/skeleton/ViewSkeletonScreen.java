package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ViewSkeletonScreen implements SkeletonScreen {

    private View mActualView, mSkeletonView, mCurrentView;
    private int mSkeletonResID, mActualViewIndexInParent;
    private ViewGroup mParentView;
    private ViewGroup.LayoutParams mActualViewLayoutParams;

    private ViewSkeletonScreen(View view, int resId) {
        this.mActualView = view;
        this.mSkeletonResID = resId;
        init();
    }

    private void init() {
        mActualViewLayoutParams = mActualView.getLayoutParams();
        mParentView = (ViewGroup) mActualView.getParent();
        if (mParentView == null) {
            mParentView = (ViewGroup) mActualView.getRootView().findViewById(android.R.id.content);
        }
        int count = mParentView.getChildCount();
        for (int index = 0; index < count; index++) {
            if (mActualView == mParentView.getChildAt(index)) {
                mActualViewIndexInParent = index;
                break;
            }
        }
    }

    private ShimmerFrameLayout generateShimmerContainerLayout() {
        ShimmerFrameLayout shimmerFragmentLayout = (ShimmerFrameLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, mParentView, false);
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerFragmentLayout, false);
        shimmerFragmentLayout.addView(innerView);
        shimmerFragmentLayout.setAutoStart(true);
        return shimmerFragmentLayout;
    }

    @Override
    public void show() {
        if (mCurrentView!=null&&mCurrentView == mSkeletonView) {
            return;
        }
        mParentView.removeView(mActualView);
        mSkeletonView = generateShimmerContainerLayout();
        mParentView.addView(mSkeletonView, mActualViewIndexInParent, mActualViewLayoutParams);
        mCurrentView = mSkeletonView;
    }

    @Override
    public void hide() {
        if (mCurrentView == mSkeletonView) {
            mParentView.removeView(mSkeletonView);
            mParentView.addView(mActualView, mActualViewIndexInParent, mActualViewLayoutParams);
        }
    }

    public static class Builder {
        private View mView;
        private int mResId;


        public Builder(View view) {
            this.mView = view;
        }

        public Builder placeHolder(int resId) {
            this.mResId = resId;
            return this;
        }

        public ViewSkeletonScreen show() {
            ViewSkeletonScreen viewSkeletonScreen = new ViewSkeletonScreen(mView, mResId);
            viewSkeletonScreen.show();
            return viewSkeletonScreen;
        }
    }
}
