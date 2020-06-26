package com.ethanhua.skeleton

import android.support.annotation.ArrayRes
import android.support.annotation.ColorRes
import android.support.annotation.IntRange
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView

/**
 * Created by ethanhua on 2017/7/29.
 */
class RecyclerViewSkeletonScreen private constructor(builder: Builder) : SkeletonScreen {
    private val recyclerView: RecyclerView?
    private val actualAdapter: RecyclerView.Adapter<*>?
    private val skeletonAdapter: SkeletonAdapter?
    private val recyclerViewFrozen: Boolean?

    init {
        recyclerView = builder.recyclerView
        actualAdapter = builder.actualAdapter
        skeletonAdapter = SkeletonAdapter()
        skeletonAdapter.itemCount = builder.itemCount
        skeletonAdapter.setLayoutReference(builder.itemResID)
        skeletonAdapter.setArrayOfLayoutReferences(builder.itemsResIDArray)
        skeletonAdapter.shimmer(builder.shimmer)
        skeletonAdapter.setShimmerColor(builder.shimmerColor)
        skeletonAdapter.setShimmerAngle(builder.shimmerAngle)
        skeletonAdapter.setShimmerDuration(builder.shimmerDuration)
        recyclerViewFrozen = builder.frozen
    }


    override fun show() {
        recyclerView?.adapter = skeletonAdapter
        if (recyclerView?.isComputingLayout == false && recyclerViewFrozen == true) {
            recyclerView.isLayoutFrozen = true
        }
    }

    override fun hide() {
        recyclerView?.adapter = actualAdapter
    }

    class Builder(val recyclerView: RecyclerView) {

        var actualAdapter: RecyclerView.Adapter<*>? = null
            private set
        var shimmer = true
            private set
        var itemCount = 10
            private set
        var itemResID = R.layout.layout_default_item_skeleton
            private set
        var itemsResIDArray: IntArray? = null
            private set
        var shimmerColor: Int
            private set
        var shimmerDuration = 1000
            private set
        var shimmerAngle = 20
            private set
        var frozen = true
            private set


        /**
         * @param adapter the target recyclerView actual adapter
         */
        fun adapter(adapter: RecyclerView.Adapter<*>?) = apply {
            this.actualAdapter = adapter
        }

        /**
         * @param itemCount the child item count in recyclerView
         */
        fun count(itemCount: Int) = apply {
            this.itemCount = itemCount
        }

        /**
         * @param shimmer whether show shimmer animation
         */
        fun shimmer(shimmer: Boolean) = apply {
            this.shimmer = shimmer
        }

        /**
         * the duration of the animation , the time it will take for the highlight to move from one end of the layout
         * to the other.
         *
         * @param shimmerDuration Duration of the shimmer animation, in milliseconds
         */
        fun duration(shimmerDuration: Int) = apply {
            this.shimmerDuration = shimmerDuration
        }

        /**
         * @param shimmerColor the shimmer color
         */
        fun color(@ColorRes shimmerColor: Int) = apply {
            this.shimmerColor = ContextCompat.getColor(recyclerView.context, shimmerColor)
        }

        /**
         * @param shimmerAngle the angle of the shimmer effect in clockwise direction in degrees.
         */
        fun angle(@IntRange(from = 0, to = 30) shimmerAngle: Int) = apply {
            this.shimmerAngle = shimmerAngle
        }

        /**
         * @param skeletonLayoutResID the loading skeleton layoutResID
         */
        fun load(@LayoutRes skeletonLayoutResID: Int) = apply {
            this.itemResID = skeletonLayoutResID
        }

        /**
         * @param skeletonLayoutResIDs the loading array of skeleton layoutResID
         */
        fun loadArrayOfLayouts(@ArrayRes skeletonLayoutResIDs: IntArray) = apply {
            this.itemsResIDArray = skeletonLayoutResIDs
        }

        /**
         * @param frozen whether frozen recyclerView during skeleton showing
         * @return
         */
        fun frozen(frozen: Boolean) = apply {
            this.frozen = frozen
        }

        fun show(): RecyclerViewSkeletonScreen {
            val recyclerViewSkeleton = RecyclerViewSkeletonScreen(this)
            recyclerViewSkeleton.show()
            return recyclerViewSkeleton
        }

        init {
            shimmerColor = ContextCompat.getColor(recyclerView.context, R.color.shimmer_color)
        }
    }


}