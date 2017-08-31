package com.ethanhua.skeleton;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by ethanhua on 2017/7/29.
 */

public class Skeleton {

    public static RecyclerViewSkeletonScreen.Builder bind(RecyclerView recyclerView, Context context) {
        return new RecyclerViewSkeletonScreen.Builder(recyclerView, context);
    }

    public static ViewSkeletonScreen.Builder bind(View view, Context context) {
        return new ViewSkeletonScreen.Builder(view, context);
    }

}
