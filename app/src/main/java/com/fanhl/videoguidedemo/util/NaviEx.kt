package com.fanhl.videoguidedemo.util

import android.content.Intent
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

fun navi(kClass: KClass<out ComponentActivity>) {
    applicationContext.startActivity(Intent(applicationContext, kClass.java))
}