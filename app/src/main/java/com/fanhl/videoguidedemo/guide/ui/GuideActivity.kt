package com.fanhl.videoguidedemo.guide.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.fanhl.videoguidedemo.R
import com.fanhl.videoguidedemo.databinding.ActivityGuideBinding

class GuideActivity : AppCompatActivity() {
    lateinit var binding: ActivityGuideBinding
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
        val exitable = intent.getBooleanExtra(KEY_EXITABLE, false)
    }

    companion object {
        private const val TAG = "GuideActivity"

        const val KEY_EXITABLE = "KEY_EXITABLE"
    }
}