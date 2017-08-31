package com.ethanhua.skeleton;

import android.content.Context;
import android.support.v4.content.ContextCompat;
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
    private final int mColor;

    private ViewSkeletonScreen(Builder builder) {
        this.mActualView = builder.mView;
        this.mSkeletonResID = builder.mSkeletonLayoutResID;
        this.mColor = builder.mColor;

        this.mViewReplacer = new ViewReplacer(builder.mView);
    }

    private ShimmerLayout generateShimmerContainerLayout() {
        ViewParent viewParent = mActualView.getParent();
        if (viewParent == null) {
            Log.e(TAG, "the source view have not attach to any view");
            return null;
        }
        ViewGroup parentView = (ViewGroup) viewParent;
        ShimmerLayout shimmerLayout = (ShimmerLayout) LayoutInflater.from(mActualView.getContext()).inflate(R.layout.layout_shimmer, parentView, false);
        shimmerLayout.setShimmerColor(mColor);

        View innerView = LayoutInflater.from(mActualView.getContext()).inflate(mSkeletonResID, shimmerLayout, false);

        shimmerLayout.addView(innerView);

        return shimmerLayout;
    }

    @Override
    public void show() {
        ShimmerLayout shimmerLayout = generateShimmerContainerLayout();
        if (shimmerLayout != null) {
            mViewReplacer.replace(shimmerLayout);

            shimmerLayout.startShimmerAnimation();
        }
    }

    @Override
    public void hide() {
        mViewReplacer.restore();
    }

    public static class Builder {
        private final View mView;
        private int mSkeletonLayoutResID;
        private int mColor;
        private Context mContext;

        public Builder(View view, Context context) {
            this.mView = view;
            this.mContext = context;

            this.mColor = ContextCompat.getColor(context, R.color.shimmer_color);
        }

        public Builder color(int color) {
            this.mColor = ContextCompat.getColor(mContext, color);
            return this;
        }

        public Builder load(int skeletonLayoutResID) {
            this.mSkeletonLayoutResID = skeletonLayoutResID;
            return this;
        }

        public ViewSkeletonScreen show() {
            ViewSkeletonScreen skeletonScreen = new ViewSkeletonScreen(this);
            skeletonScreen.show();
            return skeletonScreen;
        }
    }
}
