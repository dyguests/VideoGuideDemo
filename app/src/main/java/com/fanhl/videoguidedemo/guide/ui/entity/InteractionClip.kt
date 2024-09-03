package com.fanhl.videoguidedemo.guide.ui.entity

/**
 * @param useVideoBackground 是否使用视频背景，默认使用。若选择否，会隐藏VideoView
 */
data class InteractionClip(
    val useVideoBackground: Boolean = true,
    // val builder: InteractionViewBuilder
) : IClip {
}