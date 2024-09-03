package com.fanhl.videoguidedemo.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

fun Context.navi(kClass: KClass<out ComponentActivity>, intentBlock: (Intent.() -> Unit)? = null) {
    this.startActivity(Intent(this, kClass.java).apply { intentBlock?.invoke(this) })
}
fun Context.naviForResult(kClass: KClass<out ComponentActivity>, requestCode: Int, intentBlock: (Intent.() -> Unit)? = null) =
    (this as? Activity)?.naviForResult(kClass, requestCode, intentBlock) ?: throw IllegalStateException("Context is not Activity")

fun Activity.naviForResult(kClass: KClass<out ComponentActivity>, requestCode: Int, intentBlock: (Intent.() -> Unit)? = null) {
    this.startActivityForResult(Intent(this, kClass.java).apply { intentBlock?.invoke(this) }, requestCode)
}