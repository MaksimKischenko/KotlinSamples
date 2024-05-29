package com.example.composerules.derivedState

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun DerivedStateScreen(
    modifier: Modifier
) {

    var username by remember { mutableStateOf("") }

    val submitEnabled = remember {
        derivedStateOf { username.isNotEmpty() }
    }



    SideEffect {
        Log.d("MyLog", "RUN ${submitEnabled.value}")
    }


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(text = "Enter city name")
        TextField(
            value = username,
            onValueChange = {
                username = it
            }
        )
        OutlinedButton(
            onClick = {

        }, enabled = submitEnabled.value) {
            Text(text = "Save")
        }
    }
}

@Composable
fun ScrollToTopButton(lazyListState: LazyListState, threshold: Int) {
    // There is a bug here
    val isEnabled by remember(threshold) {
        derivedStateOf { lazyListState.firstVisibleItemIndex > threshold }
    }

    Button(onClick = { }, enabled = isEnabled) {
        Text("Scroll to top")
    }
}