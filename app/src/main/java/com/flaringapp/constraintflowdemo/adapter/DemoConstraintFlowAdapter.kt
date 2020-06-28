package com.flaringapp.constraintflowdemo.adapter

import android.view.View
import android.widget.TextView
import com.flaringapp.constraintflowdemo.R

class DemoConstraintFlowAdapter(
    private val items: List<ConstraintFlowItem>
): BaseConstraintFlowAdapter() {

    override fun initViews() {
        items.forEach {
            addView(createView(it))
        }
    }

    private fun createView(item: ConstraintFlowItem): View {
        val view = inflateView(R.layout.view_flow_item) as TextView
        view.id = View.generateViewId()
        view.text = item.text
        return view
    }

}