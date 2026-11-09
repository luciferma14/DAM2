package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MiRadioButton(modifier: Modifier = Modifier) {
    var estado by rememberSaveable { mutableStateOf(false) }
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = estado,
            onClick = { estado = true },
            enabled = true,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Red,
                unselectedColor = Color.Blue,
                disabledSelectedColor = Color.Green,
                disabledUnselectedColor = Color.Magenta
            )
        )
        Text("1 ESO")
    }
}

@Composable
fun RadioButtonComponent(nombre: String, NombreSeleccionado: String,
                         ItemSeleccionado: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically){
        RadioButton(selected = nombre == NombreSeleccionado,onClick=
            {ItemSeleccionado(nombre)})
        Text(nombre, modifier = Modifier.clickable { ItemSeleccionado(nombre) })
    }
}

@Composable
fun MiListaRadioButton(modifier: Modifier = Modifier) {
    var OpcionSeleccionada by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        RadioButtonComponent(nombre = "1 ESO", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
        RadioButtonComponent(nombre = "2 ESO", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
        RadioButtonComponent(nombre = "3 ESO", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
        RadioButtonComponent(nombre = "4 ESO", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
        RadioButtonComponent(nombre = "1 BACHILLER", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
        RadioButtonComponent(nombre = "2 BACHILLER", NombreSeleccionado =
            OpcionSeleccionada) { OpcionSeleccionada = it }
    }
}