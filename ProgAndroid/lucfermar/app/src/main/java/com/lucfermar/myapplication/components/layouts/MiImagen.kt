package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucfermar.myapplication.R

@Preview
@Composable
fun MiImagen() {
    Image(
        painter = painterResource(
            R.drawable.gato),
        contentDescription = "foreground"
    )
}

@Preview
@Composable
fun MiImagen2() {
    Image(
        painter = painterResource(
            R.drawable.gato),
        contentDescription = "foreground",
        modifier = Modifier.height(200.dp).width(30.dp),
        contentScale = ContentScale.Fit
    )
}

@Preview
@Composable
fun MiImagen3() {
    Image(
        painter = painterResource(
            R.drawable.gato
        ),
        contentDescription = "foreground",
                modifier = Modifier.size(300.dp).clip(RoundedCornerShape(topEnd=40.dp, bottomStart = 70.dp) ),
        contentScale = ContentScale.FillWidth
    )
}

@Preview
@Composable
fun MiImagen4() {
    Image(
        painter = painterResource(
            R.drawable.gato
        ),
        contentDescription = "foreground",
        modifier = Modifier.size(300.dp)
            .clip(RoundedCornerShape(50))
            .border(width = 7.dp,shape = CircleShape, brush = Brush.linearGradient(colors =
                listOf(Color.Red, Color.Blue, Color.Magenta, Color.Cyan)))
    )
}