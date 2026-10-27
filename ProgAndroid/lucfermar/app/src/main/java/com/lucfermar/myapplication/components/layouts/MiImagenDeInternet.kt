package com.lucfermar.myapplication.components.layouts

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun MiImagenDeInternet() {
    AsyncImage(
        model = "https://www.anicura.es/cdn-cgi/image/f=auto,fit=cover,w=640,h=640,g=auto,sharpen=1/AdaptiveImages/powerinit/52437/_SNI2031.jpg?stamp=a2efc90c9d13cd9fdc0f5f7a2e3b2231238dc8cf",
        contentDescription = "Imagen de Jetpack Compose",
        modifier = Modifier.size(250.dp)
            .clip(RoundedCornerShape(50))
            .border(width = 7.dp,shape = CircleShape, brush = Brush.linearGradient(colors =
            listOf(Color.Red, Color.Blue, Color.Magenta, Color.Cyan))),

        onError = {
            Log.i("Image", "Ha ocurrido un error ${it.result.throwable.message}")
        })
}