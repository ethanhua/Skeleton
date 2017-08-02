package com.ethanhua.skeleton;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ViewSkeletonScreen implements SkeletonScreen {
    private static final String TAG = ViewSkeletonScreen.class.getName();
    private final ViewReplacer mViewReplacer;
    private final View mActualView;
    private final int mSkeletonResID;

    private ViewSkeletonScreen(View view, int skeletonResID) {
        this.mActualView = view;
        this.mSkeletonResID = skeletonResID;
        mViewReplacer = new ViewReplacer(view);
    }

    private ShimmerFrameLayout generateShimmerContainerLayout() {
        ViewParent viewParent = mActualView.getParent();
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view");
            return null;
        }
        ViewGroup parentView = (ViewGroup) viewParent;
        ShimmerFrameLayout shimmerFragmentLayout = (ShimmerFrameLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, parentView, false);
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerFragmentLayout, false);
        shimmerFragmentLayout.addView(innerView);
        shimmerFragmentLayout.setAutoStart(true);
        return shimmerFragmentLayout;
    }

    @Override
    public void show() {
        ShimmerFrameLayout shimmerFrameLayout = generateShimmerContainerLayout();
        if (shimmerFrameLayout != null) {
            mViewReplacer.replace(shimmerFrameLayout);
        }
    }

    @Override
    public void hide() {
        mViewReplacer.restore();
    }

    public static class Builder {
        private final View mView;
        private int mSkeletonLayoutResID;

        public Builder(View view) {
            this.mView = view;
        }

        public Builder load(int skeletonLayoutResID) {
            this.mSkeletonLayoutResID = skeletonLayoutResID;
            return this;
        }

        public ViewSkeletonScreen show() {
            ViewSkeletonScreen skeletonScreen = new ViewSkeletonScreen(mView, mSkeletonLayoutResID);
            skeletonScreen.show();
            return skeletonScreen;
        }
    }
}
