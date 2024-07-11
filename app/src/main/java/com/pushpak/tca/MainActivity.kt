package com.pushpak.tca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pushpak.tca.action.CounterAction
import com.pushpak.tca.ui.theme.TCATheme
import com.pushpak.tca.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TCATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    CountIncrease(viewModel = viewModel<MainViewModel>())
                }
            }
        }
    }
}

@Composable
fun CountIncrease(viewModel:MainViewModel) {
    val state = viewModel.state.collectAsState()
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Counter : ${state.value.count}")
        Button(onClick = {
            viewModel.dispatch(CounterAction.Increment)
        }) {
            Text(text = "+")
        }
        Button(onClick = {
            viewModel.dispatch(CounterAction.Decrement)
        }) {
            Text(text = "-")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CountIncreasePreview() {
    TCATheme {
        CountIncrease(MainViewModel())
    }
}