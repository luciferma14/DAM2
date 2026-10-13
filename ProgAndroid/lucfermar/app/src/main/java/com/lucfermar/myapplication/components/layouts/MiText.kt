package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun MiText(modifier: Modifier) {
    Column(modifier = modifier) {
        Text(text = "Lucía en lila", color = Color.Magenta)
        Text(text = "Lucía en grande", fontSize = 25.sp)
        Text(text = "FontStyle", fontStyle = FontStyle.Italic)
        Text(text = "FontWeight", fontWeight = FontWeight.Bold)
        Text(
            text = "Lucía en grande, azul y negrita",
            fontSize = 25.sp,
            color = Color.Blue,
            fontWeight = FontWeight.ExtraBold
        )
        Text(text = "LetterSpacing", letterSpacing = 20.sp)
        Text(text = "TextDecoration", textDecoration = TextDecoration.LineThrough)
        Text(
            "TextDecoration", textDecoration = TextDecoration.Underline + TextDecoration.LineThrough
        )
        Text(
            "Combina TextDecoration",
            textDecoration = TextDecoration.combine(
                listOf(
                    TextDecoration.Underline,
                    TextDecoration.LineThrough
                )
            )
        )
        Text("Enlace",
            textDecoration = TextDecoration.Underline,
            color = Color.Blue,
            modifier = Modifier.clickable { }
        )
        Text("Alineación",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Text(
            "En un lugar de la Mancha, de cuyo nombre no quiero acordarme, no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero, adarga antigua, rocín flaco y galgo corredor.",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().background(Color.LightGray),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}