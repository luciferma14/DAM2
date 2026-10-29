package com.lucfermar.myapplication.components.layouts
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
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

@Composable
fun MiProgresoAnimacionEjercicio(modifier: Modifier = Modifier) {
    var muestraAnimacion by remember { mutableStateOf(true) }



    LaunchedEffect(Unit) {
        //delay(4000)
        muestraAnimacion = true
    }

    if (muestraAnimacion) {
        EjecutaCargaAnimacion(modifier)
    } else {
        PantallaPrincipal("Lucía", modifier)
    }
}

@Composable
fun EjecutaCargaAnimacion(modifier: Modifier = Modifier) {
    var progreso by rememberSaveable { mutableFloatStateOf(0.5f) }
    val animatedProgress by animateFloatAsState(targetValue = progreso)
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

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
            progress = {animatedProgress},
            modifier = Modifier.size(200.dp)
        )

        Row(Modifier.padding(24.dp)){
            Button(onClick = {progreso -= 0.1f}) {Text("<-")}
            Spacer(Modifier.width(24.dp))
            Button(onClick = {progreso += 0.1f}) {Text("->")}
        }
    }
}

@Composable
fun PantallaPrincipal(name: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Hola $name!",
            color = Color.Black,
            fontSize = 24.sp
        )
    }
}
