package com.fanhl.videoguidedemo.guide.ui.core

import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.fanhl.videoguidedemo.guide.ui.entity.IClip
import com.fanhl.videoguidedemo.guide.ui.entity.VideoClip
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GuidePlayer(
    private val activity: ComponentActivity,
    private val videoView: VideoView,
    private val interactionLayer: ConstraintLayout,
) {
    fun play(timeline: List<IClip>) {
        activity.lifecycleScope.launch {
            Log.d(TAG, "play timeline start")
            for (clip in timeline) {
                isActive || return@launch
                play(clip)
            }
            Log.d(TAG, "play timeline end")
        }
    }

    private suspend fun play(clip: IClip) {
        Log.d(TAG, "play clip:$clip start")
        when (clip) {
            is VideoClip -> {
                playVideo(clip)
            }
        }
        Log.d(TAG, "play clip:$clip end")
    }

    private suspend fun playVideo(clip: VideoClip) {
        check(clip.sourceType == VideoClip.SOURCE_RAW) { "sourceType is not SOURCE_RAW" }
        val rawSource = clip.rawSource ?: throw IllegalArgumentException("rawSource is null")
        val videoUri = Uri.parse("android.resource://" + activity.packageName + "/" + rawSource)
        videoView.setVideoURI(videoUri)
        interactionLayer.removeAllViews()

        // 创建一个 CompletableDeferred 用于等待视频播放完成
        val playbackCompleted = CompletableDeferred<Unit>()

        // 设置播放完成监听器
        videoView.setOnCompletionListener {
            // 移除监听器，防止内存泄漏
            videoView.setOnCompletionListener(null)
            // 当视频播放完成时，complete playbackCompleted
            playbackCompleted.complete(Unit)
        }

        videoView.start()

        // 等待视频播放完成
        playbackCompleted.await()
    }


    companion object {
        private const val TAG = "GuidePlayer"
    }
}