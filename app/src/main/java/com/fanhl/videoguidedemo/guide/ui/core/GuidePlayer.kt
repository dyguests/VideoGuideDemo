package com.fanhl.videoguidedemo.guide.ui.core

import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.lifecycleScope
import com.fanhl.videoguidedemo.databinding.ViewInteraction1Binding
import com.fanhl.videoguidedemo.guide.ui.entity.IClip
import com.fanhl.videoguidedemo.guide.ui.entity.InteractionClip
import com.fanhl.videoguidedemo.guide.ui.entity.VideoClip
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class GuidePlayer(
    private val activity: ComponentActivity,
    private val videoView: VideoView,
    private val interactionContainer: ConstraintLayout,
    private val onCompleted: (() -> Unit)? = null,
) {
    fun play(timeline: List<IClip>) {
        activity.lifecycleScope.launch {
            Log.d(TAG, "play timeline start")
            for (clip in timeline) {
                isActive || return@launch
                play(clip)
            }
            onCompleted?.apply {
                Log.d(TAG, "onCompleted")
                invoke()
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
            is InteractionClip -> {
                playInteraction(clip)
            }
        }
        Log.d(TAG, "play clip:$clip end")
    }

    private suspend fun playVideo(clip: VideoClip) {
        check(clip.sourceType == VideoClip.SOURCE_RAW) { "sourceType is not SOURCE_RAW" }
        val rawSource = clip.rawSource ?: throw IllegalArgumentException("rawSource is null")
        val videoUri = Uri.parse("android.resource://" + activity.packageName + "/" + rawSource)
        videoView.setVideoURI(videoUri)
        interactionContainer.removeAllViews()

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

    private suspend fun playInteraction(clip: InteractionClip) {
        interactionContainer.removeAllViews()
        val binding = ViewInteraction1Binding.inflate(activity.layoutInflater, interactionContainer, true)

        val playbackCompleted = CompletableDeferred<Unit>()

        binding.btnNext.setOnClickListener {
            playbackCompleted.complete(Unit)
        }

        playbackCompleted.await()
    }


    companion object {
        private const val TAG = "GuidePlayer"
    }
}