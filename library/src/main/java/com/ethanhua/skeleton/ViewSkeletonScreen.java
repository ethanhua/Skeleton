package com.ethanhua.skeleton;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ViewSkeletonScreen implements SkeletonScreen {

    private View mActualView;
    private View mSkeletonView;
    private View mCurrentView;
    private int mSkeletonResId = -1;
    private int mActualViewIndexInParent;
    private ViewGroup mParentView;
    private ViewGroup.LayoutParams mActualViewLayoutParams;

    public ViewSkeletonScreen(View view) {
        this.mActualView = view;
        init();
    }

    private void init() {
        mActualViewLayoutParams = mActualView.getLayoutParams();
        mCurrentView = mActualView;
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

    private ShimmerFrameLayout generateShimmerContainerLayout(int skeletonResId) {
        ShimmerFrameLayout shimmerFragmentLayout = (ShimmerFrameLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, mParentView, false);
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(skeletonResId, shimmerFragmentLayout, false);
        shimmerFragmentLayout.addView(innerView);
        shimmerFragmentLayout.setAutoStart(true);
        return shimmerFragmentLayout;
    }

    @Override
    public SkeletonScreen show(int resId) {
        if (mSkeletonResId == resId) {
            return this;
        }
        mSkeletonResId = resId;
        mParentView.removeView(mCurrentView);
        mSkeletonView = generateShimmerContainerLayout(resId);
        mParentView.addView(mSkeletonView, mActualViewIndexInParent, mActualViewLayoutParams);
        mCurrentView = mSkeletonView;
        return this;
    }

    @Override
    public SkeletonScreen hide() {
        if (mSkeletonResId == -1) {
            return this;
        }
        mParentView.removeView(mSkeletonView);
        mParentView.addView(mActualView, mActualViewIndexInParent, mActualViewLayoutParams);
        mSkeletonResId = -1;
        return this;
    }

}
