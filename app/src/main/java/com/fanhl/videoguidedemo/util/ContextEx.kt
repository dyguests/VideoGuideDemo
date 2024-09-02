package com.fanhl.videoguidedemo.util

import android.content.Context

object ContextUtil {
    private lateinit var _applicationContext: Context
    val applicationContext: Context
        get() = _applicationContext

    fun init(context: Context?) {
        _applicationContext = context?.applicationContext ?: throw NullPointerException("context is null")
    }

}

val applicationContext: Context
    get() = ContextUtil.applicationContext