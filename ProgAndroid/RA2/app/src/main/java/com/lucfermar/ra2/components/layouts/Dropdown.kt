package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.window.PopupProperties
import com.lucfermar.ra2.R

@Composable
fun MiDropDownItem(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        DropdownMenuItem(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            colors = MenuDefaults.itemColors(
                textColor = Color.Red,
                leadingIconColor = Color.Blue,
                trailingIconColor = Color.Green,
                disabledTextColor = Color.Yellow,
                disabledLeadingIconColor = Color.Yellow,
                disabledTrailingIconColor = Color.Yellow,
            ),
            text = {
                Text("Opción 1")
            },
            leadingIcon = {
                Icon(
                    painter = painterResource(R.drawable.fichero),
                    contentDescription = ""
                )
            },
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.carpeta),
                    contentDescription = ""
                )
            },
            enabled = true,
            onClick = {})
    }
}

@Composable
fun MiDropDownMenu(modifier: Modifier = Modifier) {
    var expandido by rememberSaveable { mutableStateOf(false) }
    Box(modifier = modifier) {
        Button(onClick = { expandido = true }) {
            Text("Ver opciones")
        }
        DropdownMenu(
            expanded = expandido,
            onDismissRequest = { expandido = false },
            offset = DpOffset(16.dp, 16.dp),
            properties = PopupProperties(
                focusable = true,
                dismissOnClickOutside = false,
                dismissOnBackPress = false,
                clippingEnabled = false
            )
        ) {
            DropdownMenuItem(text = { Text("Opción 1") }, onClick = { expandido = false })
            DropdownMenuItem(text = { Text("Opción 2") }, onClick = { expandido = false })
            DropdownMenuItem(text = { Text("Opción 3") }, onClick = { expandido =false })
            DropdownMenuItem(text = { Text("Opción 4") }, onClick = { expandido = false })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiExposedDropDownMenu(modifier: Modifier = Modifier) {
    var expandido by rememberSaveable { mutableStateOf(false) }
    var seleccionado by rememberSaveable { mutableStateOf("") }
    ExposedDropdownMenuBox(modifier = modifier, expanded = expandido,
        onExpandedChange = { expandido = !expandido }) {
        TextField(
            value = seleccionado,
            onValueChange = {},
            readOnly = true,
            label = { Text("Módulos") },
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expandido)
            }
        )
        DropdownMenu(
            expanded = expandido, onDismissRequest = { expandido = false }) {
            DropdownMenuItem(text = { Text("Bases de datos") }, onClick = {
                seleccionado = "Bases de datos"
                expandido = false
            })
            DropdownMenuItem(text = { Text("Desarrollo de interfaces") }, onClick
            = {
                    seleccionado = "Desarrollo de interfaces"
                    expandido = false
                })
            DropdownMenuItem(text = { Text("Programación") }, onClick = {
                seleccionado = "Programación"
                expandido = false
            })
            DropdownMenuItem(text = { Text("Entornos de desarrollo") }, onClick =
                {
                    seleccionado = "Entornos de desarrollo"
                    expandido = false
                })
        }
    }
}