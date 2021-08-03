package com.ethanhua.skeleton;

import androidx.annotation.ColorRes;
import androidx.annotation.IntRange;
import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import io.supercharge.shimmerlayout.ShimmerLayout;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class ViewSkeletonScreen implements SkeletonScreen {
    private static final String TAG = ViewSkeletonScreen.class.getName();
    private final ViewReplacer mViewReplacer;
    private final View mActualView;
    private final int mSkeletonResID;
    private final int mShimmerColor;
    private final boolean mShimmer;
    private final int mShimmerDuration;
    private final int mShimmerAngle;

    private ViewSkeletonScreen(Builder builder) {
        mActualView = builder.mView;
        mSkeletonResID = builder.mSkeletonLayoutResID;
        mShimmer = builder.mShimmer;
        mShimmerDuration = builder.mShimmerDuration;
        mShimmerAngle = builder.mShimmerAngle;
        mShimmerColor = builder.mShimmerColor;
        mViewReplacer = new ViewReplacer(builder.mView);
    }

    private ShimmerLayout generateShimmerContainerLayout(ViewGroup parentView) {
        final ShimmerLayout shimmerLayout = (ShimmerLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, parentView, false);
        shimmerLayout.setShimmerColor(mShimmerColor);
        shimmerLayout.setShimmerAngle(mShimmerAngle);
        shimmerLayout.setShimmerAnimationDuration(mShimmerDuration);
        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerLayout, false);
        ViewGroup.LayoutParams lp = innerView.getLayoutParams();
        if (lp != null) {
            shimmerLayout.setLayoutParams(lp);
        }
        shimmerLayout.addView(innerView);
        shimmerLayout.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                shimmerLayout.startShimmerAnimation();
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                shimmerLayout.stopShimmerAnimation();
            }
        });
        shimmerLayout.startShimmerAnimation();
        return shimmerLayout;
    }

    private View generateSkeletonLoadingView() {
        ViewParent viewParent = mActualView.getParent();
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view");
            return null;
        }
        ViewGroup parentView = (ViewGroup) viewParent;
        if (mShimmer) {
            return generateShimmerContainerLayout(parentView);
        }
        return LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, parentView, false);
    }

    @Override
    public void show() {
        View skeletonLoadingView = generateSkeletonLoadingView();
        if (skeletonLoadingView != null) {
            mViewReplacer.replace(skeletonLoadingView);
        }
    }

    @Override
    public void hide() {
        if (mViewReplacer.getTargetView() instanceof ShimmerLayout) {
            ((ShimmerLayout) mViewReplacer.getTargetView()).stopShimmerAnimation();
        }
        mViewReplacer.restore();
    }

    public static class Builder {
        private final View mView;
        private int mSkeletonLayoutResID;
        private boolean mShimmer = true;
        private int mShimmerColor;
        private int mShimmerDuration = 1000;
        private int mShimmerAngle = 20;

        public Builder(View view) {
            this.mView = view;
            this.mShimmerColor = ContextCompat.getColor(mView.getContext(), R.color.shimmer_color);
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        public Builder load(@LayoutRes int skeletonLayoutResID) {
            this.mSkeletonLayoutResID = skeletonLayoutResID;
            return this;
        }

        /**
         * @param shimmerColor the shimmer color
         */
        public Builder color(@ColorRes int shimmerColor) {
            this.mShimmerColor = ContextCompat.getColor(mView.getContext(), shimmerColor);
            return this;
        }

        /**
         * @param shimmer whether show shimmer animation
         */
        public ViewSkeletonScreen.Builder shimmer(boolean shimmer) {
            this.mShimmer = shimmer;
            return this;
        }

        /**
         * the duration of the animation , the time it will take for the highlight to move from one end of the layout
         * to the other.
         *
         * @param shimmerDuration Duration of the shimmer animation, in milliseconds
         */
        public ViewSkeletonScreen.Builder duration(int shimmerDuration) {
            this.mShimmerDuration = shimmerDuration;
            return this;
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        public ViewSkeletonScreen.Builder angle(@IntRange(from = 0, to = 30) int shimmerAngle) {
            this.mShimmerAngle = shimmerAngle;
            return this;
        }

        public ViewSkeletonScreen show() {
            ViewSkeletonScreen skeletonScreen = new ViewSkeletonScreen(this);
            skeletonScreen.show();
            return skeletonScreen;
        }

    }
}
