package com.fanhl.videoguidedemo.guide

import android.content.Context
import android.util.Log

object NewbieGuide {
    private const val TAG = "NewbieGuide"
    fun start(
        context: Context,
        exitable: Boolean = false,
    ) {
        Log.d(TAG, "start")
    }
}