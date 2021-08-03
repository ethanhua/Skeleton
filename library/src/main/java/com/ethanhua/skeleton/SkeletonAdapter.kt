package com.ethanhua.skeleton

import android.support.annotation.IntRange
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import io.supercharge.shimmerlayout.ShimmerLayout

/**
 * Created by ethanhua on 2017/7/29.
 */
class SkeletonAdapter : RecyclerView.Adapter<ViewHolder>() {
    private var itemCount = 0
    private var layoutReference = 0
    private var layoutArrayReferences: IntArray? = null
    private var color = 0
    private var shimmer = false
    private var shimmerDuration = 0
    private var shimmerAngle = 0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        if (doesArrayOfLayoutsExist()) {
            layoutReference = viewType
        }
        return if (shimmer) {
            ShimmerViewHolder(inflater, parent, layoutReference)
        } else object : ViewHolder(inflater.inflate(layoutReference, parent, false)) {}
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (shimmer) {
            val layout = holder.itemView as ShimmerLayout
            layout.setShimmerAnimationDuration(shimmerDuration)
            layout.setShimmerAngle(shimmerAngle)
            layout.setShimmerColor(color)
            layout.startShimmerAnimation()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            getCorrectLayoutItem(position)
        } else super.getItemViewType(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return itemCount
    }

    fun setLayoutReference(layoutReference: Int) {
        this.layoutReference = layoutReference
    }

    fun setArrayOfLayoutReferences(layoutReferences: IntArray?) {
        this.layoutArrayReferences = layoutReferences
    }

    fun setItemCount(itemCount: Int) {
        this.itemCount = itemCount
    }

    fun setShimmerColor(color: Int) {
        this.color = color
    }

    fun shimmer(shimmer: Boolean) {
        this.shimmer = shimmer
    }

    fun setShimmerDuration(shimmerDuration: Int) {
        this.shimmerDuration = shimmerDuration
    }

    fun setShimmerAngle(@IntRange(from = 0, to = 30) shimmerAngle: Int) {
        this.shimmerAngle = shimmerAngle
    }

    fun getCorrectLayoutItem(position: Int): Int {
        return if (doesArrayOfLayoutsExist()) {
            layoutArrayReferences?.let { references->
                references[position % references.size]
            }?:0
        } else layoutReference
    }

    private fun doesArrayOfLayoutsExist(): Boolean {
        return layoutArrayReferences != null && layoutArrayReferences?.size != 0
    }
}