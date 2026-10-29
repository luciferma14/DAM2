package com.lucfermar.myapplication.components.layouts

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp

@Composable
fun MiProgreso(modifier: Modifier = Modifier) {
    var progreso by rememberSaveable { mutableFloatStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(targetValue = progreso)
    var isLoading by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
        Spacer(Modifier.height(24.dp))

        CircularProgressIndicator(
            //progress = {progreso}, Brusco
            progress = {animatedProgress}, // animado
            Modifier.size(140.dp),
            color = Color.Red,
            strokeWidth = 10.dp,
            trackColor = Color.Blue,
            strokeCap = StrokeCap.Round
        )
        Spacer(Modifier.height(24.dp))

        LinearProgressIndicator()
        Spacer(Modifier.height(24.dp))

        LinearProgressIndicator(color = Color.Red)
        Spacer(Modifier.height(24.dp))

        LinearProgressIndicator(
            color = Color.Red,
            trackColor = Color.Blue,
            strokeCap = StrokeCap.Square
        )
        Spacer(Modifier.height(24.dp))

        LinearProgressIndicator(
            progress = {0.5f},
            color = Color.Red,
            trackColor = Color.Blue,
            strokeCap = StrokeCap.Square
        )

        //Mueve el circulo de arriba con progress
        Row(Modifier.padding(24.dp)){
            Button(onClick = {progreso -= 0.1f}) {Text("<-")}
            Spacer(Modifier.width(24.dp))
            Button(onClick = {progreso += 0.1f}) {Text("->")}
        }

        if(isLoading) {
            CircularProgressIndicator(
                progress = { animatedProgress },
                Modifier.size(140.dp),
                color = Color.Red,
                strokeWidth = 10.dp,
                trackColor = Color.Blue,
                strokeCap = StrokeCap.Square
            )
        }

        Button(onClick = { isLoading = !isLoading }) { Text("Visible / Oculto") }
    }
}