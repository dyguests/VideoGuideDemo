package com.fanhl.videoguidedemo.guide.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.fanhl.videoguidedemo.guide.ui.ui.theme.VideoGuideDemoTheme

class GuideActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val exitable = intent.getBooleanExtra(KEY_EXITABLE, false)
        Log.d(TAG, "onCreate: exitable=$exitable")
        setContent {
            VideoGuideDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding),
                        innerPadding,
                    )
                }
            }
        }
    }

    companion object {
        private const val TAG = "GuideActivity"

        const val KEY_EXITABLE = "KEY_EXITABLE"
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, innerPadding: PaddingValues) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "Hello $!",
            modifier = Modifier
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    VideoGuideDemoTheme {
        Greeting("Android", innerPadding = PaddingValues())
    }
}