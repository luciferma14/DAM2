package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiCheckBox(modifier: Modifier = Modifier) {
    var estado by rememberSaveable { mutableStateOf(false) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { estado = !estado }) {
        Checkbox(
            checked = estado,
            onCheckedChange = { estado = it },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Red,
                checkmarkColor = Color.Blue,
                uncheckedColor = Color.Blue,
                disabledCheckedColor = Color.Yellow,
                disabledUncheckedColor = Color.Green
            )
        )
        Spacer(Modifier.width(12.dp))
        Text("Está repitiendo el curso")
    }
}