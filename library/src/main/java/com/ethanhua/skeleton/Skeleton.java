package com.ethanhua.skeleton;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class Skeleton {

    public static RecyclerViewSkeletonScreen.builder bind(RecyclerView recyclerView) {
        return new RecyclerViewSkeletonScreen.builder(recyclerView);
    }

    public static ViewSkeletonScreen bind(View view) {
        return new ViewSkeletonScreen(view);
    }

}
