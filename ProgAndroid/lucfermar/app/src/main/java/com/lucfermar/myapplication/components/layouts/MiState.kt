package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier


@Composable
fun MiState(modifier: Modifier){
    var number = rememberSaveable { mutableStateOf(0) }
    Text(
        text = "Púlsame: ${number.value}",
        modifier = modifier.clickable { number.value += 1 }
    )
}