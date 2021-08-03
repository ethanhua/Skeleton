package com.ethanhua.skeleton

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by ethanhua on 2017/7/29.
 */
object Skeleton {
    @JvmStatic
    fun bind(recyclerView: RecyclerView): RecyclerViewSkeletonScreen.Builder {
        return RecyclerViewSkeletonScreen.Builder(recyclerView)
    }
    @JvmStatic
    fun bind(view: View): ViewSkeletonScreen.Builder {
        return ViewSkeletonScreen.Builder(view)
    }
}