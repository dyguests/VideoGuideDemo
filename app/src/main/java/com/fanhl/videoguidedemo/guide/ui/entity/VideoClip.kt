package com.fanhl.videoguidedemo.guide.ui.entity

import androidx.annotation.RawRes

data class VideoClip(
    val sourceType: String,
    val source: String? = null,
    @RawRes val rawSource: Int? = null,
) : IClip {

    override fun toString(): String {
        return "VideoClip(sourceType='$sourceType', source=$source, rawSource=$rawSource)"
    }

    companion object {
        const val SOURCE_RAW = "raw"
        const val SOURCE_LOCAL = "local"
        const val SOURCE_REMOTE = "remote"
    }
}