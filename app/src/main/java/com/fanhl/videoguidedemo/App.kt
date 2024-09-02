package com.fanhl.videoguidedemo

import android.app.Application
import com.fanhl.videoguidedemo.util.ContextUtil

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        ContextUtil.init(this)
    }
}
