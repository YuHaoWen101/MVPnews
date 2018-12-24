package com.example.ge63vr.presenter

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewGroup


class MyViewPager : ViewPager {


    private var parent: ViewGroup? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {}

    fun setNestedpParent(parent: ViewGroup) {
        this.parent = parent
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (parent != null) {
            parent!!.requestDisallowInterceptTouchEvent(true)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(arg0: MotionEvent): Boolean {
        if (parent != null) {
            parent!!.requestDisallowInterceptTouchEvent(true)
        }
        return super.onInterceptTouchEvent(arg0)
    }

    override fun onTouchEvent(arg0: MotionEvent): Boolean {
        if (parent != null) {
            parent!!.requestDisallowInterceptTouchEvent(true)
        }
        return super.onTouchEvent(arg0)
    }

}