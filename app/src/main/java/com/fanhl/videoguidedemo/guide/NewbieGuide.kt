package com.fanhl.videoguidedemo.guide

import android.content.Context
import android.util.Log
import com.fanhl.videoguidedemo.guide.ui.Guide2Activity
import com.fanhl.videoguidedemo.util.navi

object NewbieGuide {
    private const val TAG = "NewbieGuide"
    fun start(
        context: Context,
        exitable: Boolean = false,
    ) {
        Log.d(TAG, "start")
        context.navi(Guide2Activity::class) {
            putExtra(Guide2Activity.KEY_EXITABLE, exitable)
        }
    }
}