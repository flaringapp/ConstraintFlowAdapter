package com.flaringapp.constraintflowdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.flaringapp.constraintflowdemo.adapter.ConstraintFlowItem
import com.flaringapp.constraintflowdemo.adapter.DemoConstraintFlowAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = DemoConstraintFlowAdapter(items)
        adapter.init(flowLayout, flow.id)
    }

    private val items = listOf(
        "T", "A", "G", "S", " ", "S", "M", "A", "L", "L",
        "Tag 1",
        "Tag 2 longer tag",
        "Tag 3 this is the longest tag",
        "Tag 4 this is same long tag",
        "Tag 5",
        "Tag 6 some different tag",
        "Tag 7 yess"
    ).map { ConstraintFlowItem(it) }
}