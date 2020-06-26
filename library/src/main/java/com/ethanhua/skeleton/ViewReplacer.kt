package com.ethanhua.skeleton

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by ethanhua on 2017/8/2.
 */
class ViewReplacer(private val sourceView: View) {
    var targetView: View? = null
        private set
    private var targetViewResID = -1
    private var currentView: View?
        private set
    private var sourceParentView: ViewGroup? = null
    private val sourceViewLayoutParams: ViewGroup.LayoutParams = sourceView.layoutParams
    private var sourceViewIndexInParent = 0
    private val sourceViewId: Int
    fun replace(targetViewResID: Int) {
        if (this.targetViewResID == targetViewResID) {
            return
        }
        if (init()) {
            this.targetViewResID = targetViewResID
            replace(LayoutInflater.from(sourceView.context).inflate(this.targetViewResID, sourceParentView, false))
        }
    }

    fun replace(targetView: View) {
        if (currentView === targetView) {
            return
        }
        if (targetView.parent != null) {
            (targetView.parent as ViewGroup).removeView(targetView)
        }
        if (init()) {
            this.targetView = targetView
            sourceParentView?.removeView(currentView)
            targetView.id = sourceViewId
            sourceParentView?.addView(this.targetView, sourceViewIndexInParent, sourceViewLayoutParams)
            currentView = this.targetView
        }
    }

    fun restore() {
        sourceParentView?.removeView(currentView)
        sourceParentView?.addView(sourceView, sourceViewIndexInParent, sourceViewLayoutParams)
        currentView = sourceView
        targetView = null
        targetViewResID = -1
    }

    private fun init(): Boolean {
            sourceParentView = sourceView.parent as ViewGroup
            if (sourceParentView == null) {
                Log.e(TAG, "the source view have not attach to any view")
                return false
            }
            val count = sourceParentView?.childCount?:0
            for (index in 0 until count) {
                if (sourceView === sourceParentView?.getChildAt(index)) {
                    sourceViewIndexInParent = index
                    break
                }
            }

        return true
    }

    companion object {
        private val TAG = ViewReplacer::class.java.name
    }

    init {
        currentView = sourceView
        sourceViewId = sourceView.id
    }
}