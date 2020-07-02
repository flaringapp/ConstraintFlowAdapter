package com.flaringapp.constraintflowadapter

import android.view.View
import android.view.ViewGroup

val ViewGroup.children: Sequence<View>
    get() = object : Sequence<View> {
        override fun iterator() = this@children.iterator()
    }

inline operator fun ViewGroup.contains(view: View) = indexOfChild(view) != -1

operator fun ViewGroup.iterator() = object : MutableIterator<View> {
    private var index = 0
    override fun hasNext() = index < childCount
    override fun next() = getChildAt(index++) ?: throw IndexOutOfBoundsException()
    override fun remove() = removeViewAt(--index)
}