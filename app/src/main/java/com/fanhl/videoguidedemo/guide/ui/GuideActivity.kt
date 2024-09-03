package com.fanhl.videoguidedemo.guide.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanhl.videoguidedemo.R
import com.fanhl.videoguidedemo.databinding.ActivityGuideBinding
import com.fanhl.videoguidedemo.guide.ui.core.GuidePlayer
import com.fanhl.videoguidedemo.guide.ui.entity.timeline


class GuideActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideBinding

    private var exitable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // 设置全屏模式
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            window.insetsController?.let { controller ->
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = (
                    View.SYSTEM_UI_FLAG_FULLSCREEN
                            or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    )
        }
        binding = ActivityGuideBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        exitable = intent.getBooleanExtra(KEY_EXITABLE, false)

        initView()
        initData()
    }

    private fun initView() {
        binding.btnExit.visibility = (if (exitable) View.VISIBLE else View.GONE)
        binding.btnExit.setOnClickListener {
            finish()
        }
    }

    private fun initData() {
        // // 获取视频文件的 URI
        // val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.demo1compress1)
        // binding.videoView.setVideoURI(videoUri)
        // binding.videoView.start()

        val guidePlayer = GuidePlayer(this, binding.videoView, binding.clInteraction) {
            finish()
        }
        guidePlayer.play(timeline)
    }

    companion object {
        private const val TAG = "GuideActivity"

        const val KEY_EXITABLE = "KEY_EXITABLE"
    }
}