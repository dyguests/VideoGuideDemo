package com.fanhl.videoguidedemo.util

import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

fun Context.navi(kClass: KClass<out ComponentActivity>) {
    this.startActivity(Intent(this, kClass.java))
}