package com.example.jetpackcompinstagram

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompinstagram.login.ui.LoginViewModel
import com.example.jetpackcompinstagram.login.ui.MyInstagramScreen
import com.example.jetpackcompinstagram.ui.theme.JetpackCompInstagramTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackCompInstagramTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MyInstagramScreen(viewModel, Modifier.padding(innerPadding))
//                    ColorAnimationSimple(Modifier.padding(innerPadding))
//                    SizeAnimation(Modifier.padding(innerPadding))
//                    VisibilityAnimation(Modifier.padding(innerPadding))
                    CrossfadeExampleAnimation(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JetpackCompInstagramTheme {
        Greeting("Android")
    }
}