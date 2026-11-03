package com.lucfermar.myapplication.components.layouts

import androidx.compose.ui.graphics.Color
data class EstadoCheckbox(
    val id: String,
    val label: String,
    val checked: Boolean = false, //campo no obligatorio
    val color: Color = Color.Black
)
