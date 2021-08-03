package com.ethanhua.skeleton;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class Skeleton {

    public static RecyclerViewSkeletonScreen.Builder bind(RecyclerView recyclerView) {
        return new RecyclerViewSkeletonScreen.Builder(recyclerView);
    }

    public static ViewSkeletonScreen.Builder bind(View view) {
        return new ViewSkeletonScreen.Builder(view);
    }

}
