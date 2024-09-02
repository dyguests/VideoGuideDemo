package com.fanhl.videoguidedemo.guide.ui

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanhl.videoguidedemo.R
import com.fanhl.videoguidedemo.databinding.ActivityGuideBinding


class GuideActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideBinding

    private var exitable: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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
        // 获取视频文件的 URI
        val videoUri = Uri.parse("android.resource://" + packageName + "/" + R.raw.demo1compress1)
        binding.videoView.setVideoURI(videoUri)
        binding.videoView.start()
    }

    companion object {
        private const val TAG = "GuideActivity"

        const val KEY_EXITABLE = "KEY_EXITABLE"
    }
}