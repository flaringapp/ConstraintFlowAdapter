package com.flaringapp.constraintflowadapter

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.constraintlayout.helper.widget.Flow
import androidx.constraintlayout.widget.ConstraintLayout
import java.lang.ref.WeakReference

abstract class BaseConstraintFlowAdapter {

    private var layoutRef: WeakReference<ConstraintLayout>? = null

    protected val layout: ConstraintLayout?
        get() = layoutRef?.get()

    private var flowId: Int = -1

    private val flow: Flow?
        get() = layout?.findViewById(flowId) as? Flow

    private val layoutInflater: LayoutInflater?
        get() = layout?.let { LayoutInflater.from(it.context) }

    fun init(layout: ConstraintLayout) {
        val flow = layout.children.find { it is Flow }
            ?: throw IllegalStateException(
                "${this::class.simpleName} requires ${Flow::class.simpleName} inside layout"
            )

        init(layout, flow.id)
    }

    fun init(layout: ConstraintLayout, @IdRes flowId: Int) {
        if (layoutRef?.get() != null) layoutRef?.clear()
        layoutRef = WeakReference(layout)

        this.flowId = flowId

        val flow: View? = layout.findViewById(flowId)
            ?: throw IllegalStateException("${this::class.simpleName} requires " +
                    "${Flow::class.simpleName} widget to be inside " +
                    "${ConstraintLayout::class.simpleName}")

        if (flow !is Flow) throw IllegalStateException(
            "${this::class.simpleName} requires ${Flow::class.simpleName} " +
                    "instead of provided ${flow!!::class.simpleName} with id $flowId"
        )

        initViews()
    }

    fun release() {
        layoutRef?.clear()
    }

    abstract fun initViews()

    protected fun addView(view: View) {
        if (layout?.contains(view) == false) {
            layout?.addView(view)
        }

        if (flow?.referencedIds?.contains(view.id) == false) {
            flow?.addView(view)
        }
    }

    protected fun removeView(view: View) {
        layout?.removeView(view)
        flow?.removeView(view)
    }

    protected fun inflateView(@LayoutRes layoutRes: Int): View? {
        return layoutInflater?.inflate(layoutRes, layout, false)
    }

}