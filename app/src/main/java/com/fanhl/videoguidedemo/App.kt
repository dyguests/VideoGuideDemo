package com.fanhl.videoguidedemo

import android.app.Application
import android.content.Context
import com.fanhl.videoguidedemo.util.ContextUtil

class App : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        ContextUtil.init(base)
    }
}
