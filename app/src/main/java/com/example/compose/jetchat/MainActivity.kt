package com.example.compose.jetchat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.example.compose.jetchat.components.JetChatScaffold
import com.example.compose.jetchat.components.JetchatAppBar
import com.example.compose.jetchat.databinding.ContentMainBinding
import com.example.compose.jetchat.ui.theme.JetChatAppTheme

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetChatAppTheme {
                // A surface container using the 'background' color from the theme
                val scaffoldState = rememberScaffoldState()
                val drawerOpen by viewModel.drawerShouldBeOpened.collectAsState()
                if (drawerOpen) {
                    LaunchedEffect(Unit)
                    {
                        scaffoldState.drawerState.open()
                        viewModel.resetOpenDrawerAction()
                    }
                }

                val scope = rememberCoroutineScope()
                if (scaffoldState.drawerState.isOpen) {
                }

                JetChatScaffold(scaffoldState = scaffoldState,
                    onProfileClicked = {},
                    onChatClicked = {}) {
                    AndroidViewBinding(ContentMainBinding::inflate)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetChatAppTheme {
        Greeting("Android")
    }
}