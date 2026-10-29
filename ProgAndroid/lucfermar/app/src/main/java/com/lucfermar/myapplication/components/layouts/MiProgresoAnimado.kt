package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.lucfermar.myapplication.R
import kotlinx.coroutines.delay

@Composable
fun MiProgresoAnimado(modifier: Modifier = Modifier) {
    var muestraAnimacion by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000)
        muestraAnimacion = false
    }

    if (muestraAnimacion) {
        EjecutaCargaAnimacion(modifier)
    } else {
        PantallaPrincipal("Lucía", modifier)
    }
}

@Composable
fun EjecutaCargaAnimacion1(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        val composition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(R.raw.cat)
        )
        val progress by animateLottieCompositionAsState(
            composition = composition,
            iterations = LottieConstants.IterateForever
        )
        LottieAnimation(
            composition = composition,
            progress = { progress },
            modifier = Modifier.size(200.dp)
        )
    }
}

@Composable
fun PantallaPrincipal1(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hola $name!",
            color = Color.White,
            fontSize = 24.sp
        )
    }
}
