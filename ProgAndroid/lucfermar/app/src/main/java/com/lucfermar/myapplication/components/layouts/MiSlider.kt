package com.lucfermar.myapplication.components.layouts

import android.R.attr.track
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.RangeSliderState
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.SliderState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiSlider(modifier: Modifier = Modifier) {
    var miValor by rememberSaveable { mutableFloatStateOf(0.5f) }
    Column(modifier = modifier.padding(horizontal = 30.dp)) {
        Slider(value = miValor,
            {miValor = it},
            colors = SliderDefaults.colors(
                thumbColor = Color.Red,
                disabledThumbColor = Color.DarkGray,
                activeTrackColor = Color.Red,
                activeTickColor = Color.Blue,
                disabledActiveTrackColor = Color.Gray,
                disabledActiveTickColor = Color.DarkGray,
                inactiveTickColor = Color.Green,
                inactiveTrackColor = Color.Blue,
                disabledInactiveTickColor = Color.DarkGray,
                disabledInactiveTrackColor = Color.Gray
            ))
        Text(miValor.toString())
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiSliderAvanzado(modifier: Modifier = Modifier) {
    var ejemplo by rememberSaveable { mutableStateOf("¿Seguro?") }
    val mis_colores = SliderDefaults.colors(
        thumbColor = Color.Red,
        disabledThumbColor = Color.DarkGray,
        activeTrackColor = Color.Red,
        activeTickColor = Color.Blue,
        disabledActiveTrackColor = Color.Gray,
        disabledActiveTickColor = Color.DarkGray,
        inactiveTickColor = Color.Green,
        inactiveTrackColor = Color.Blue,
        disabledInactiveTickColor = Color.DarkGray,
        disabledInactiveTrackColor = Color.Gray
    )

        val estado = remember {
        SliderState(
            value = 5f,
            steps = 9,
            onValueChangeFinished = {ejemplo= "SIIIII"},
            valueRange = 0f..10f
        )
    }
    Column(modifier = modifier.padding(horizontal = 32.dp)) {
        Slider(estado,
            colors = mis_colores,
            thumb = {state: SliderState -> Text(estado.value.toString())})
        Text(ejemplo)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiRangeSlider(modifier: Modifier = Modifier) {
    val mis_colores = SliderDefaults.colors(
        thumbColor = Color.Red,
        disabledThumbColor = Color.DarkGray,
        activeTrackColor = Color.Red,
        activeTickColor = Color.Blue,
        disabledActiveTrackColor = Color.Gray,
        disabledActiveTickColor = Color.DarkGray,
        inactiveTickColor = Color.Green,
        inactiveTrackColor = Color.Blue,
        disabledInactiveTickColor = Color.DarkGray,
        disabledInactiveTrackColor = Color.Gray
    )
    var estado = remember {
        RangeSliderState(
            activeRangeStart = 3f,
            activeRangeEnd = 6f,
            valueRange = 0f..10f,
            steps = 9,
            onValueChangeFinished = {})
    }
    Column(modifier = modifier.padding(horizontal = 30.dp)) {
        RangeSlider(
            state = estado,
            colors = mis_colores,
            startThumb = {
                Box (
                    Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Green),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(it.activeRangeStart.toString())
                }
            },
            endThumb = {
                Box (
                    Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .background(Color.Magenta),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(String.format("%.1f",it.activeRangeEnd))
                }
            }
        )
    }
}