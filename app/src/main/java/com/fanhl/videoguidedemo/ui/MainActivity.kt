package com.fanhl.videoguidedemo.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fanhl.videoguidedemo.ui.theme.VideoGuideDemoTheme
import com.fanhl.videoguidedemo.util.navi

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VideoGuideDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        innerPadding
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(innerPadding: PaddingValues) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Main",
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = { context.navi(AliceActivity::class) }) {
            Text(text = "Goto Alice")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    VideoGuideDemoTheme {
        MainScreen(innerPadding = PaddingValues())
    }
}