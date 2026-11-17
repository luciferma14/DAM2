package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

@Composable
fun MiFormularioEjercicio5(modifier: Modifier) {

    var nombre by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var hamburguesa by rememberSaveable { mutableStateOf("") }
    var picanteMin by rememberSaveable { mutableFloatStateOf(2f) }
    var picanteMax by rememberSaveable { mutableFloatStateOf(6f) }
    var extraQueso by rememberSaveable { mutableStateOf(false) }
    var menuAbierto by rememberSaveable { mutableStateOf(false) }
    var confirmado by rememberSaveable { mutableStateOf(false) }

    val pedidoCompleto = nombre.isNotBlank()
            && telefono.isNotBlank()

    val colorPicante = when {
        (picanteMin + picanteMax) / 2 < 5 -> Color.Red
        (picanteMin + picanteMax) / 2 <= 7 -> Color.Yellow
        else -> Color.Green
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        MiNombrePedido(nombre) {
            nombre = it
            confirmado = false
        }

        MiTelefonoPedido(telefono) {
            telefono = it
            confirmado = false
        }

        MiHamburguesa(
            hamburguesaSeleccionada = hamburguesa,
            menuAbierto = menuAbierto,
            onMenuChange = { menuAbierto = it },
            onHamburguesaChange = {
                hamburguesa = it
                confirmado = false
            }
        )

        MiPicante(
            minimo = picanteMin,
            maximo = picanteMax,
            onValueChange = {
                picanteMin = it.start
                picanteMax = it.endInclusive
                confirmado = false
            }
        )

        MiExtraQueso(extraQueso) {
            extraQueso = it
            confirmado = false
        }

        Spacer(Modifier.height(20.dp))

        MiResumenPedido(
            nombre,
            telefono,
            hamburguesa,
            picanteMin,
            picanteMax,
            extraQueso
        )

        Spacer(Modifier.height(20.dp))

        MiBotonPedido(habilitado = pedidoCompleto) {
            confirmado = true
        }

        if (confirmado) {
            Spacer(Modifier.height(16.dp))
            Text(
                "¡Pedido confirmado correctamente!",
                color = Color(0xFF2E7D32)
            )
        }
    }
}

@Composable
fun MiNombrePedido(
    valor: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = valor,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Nombre") }
    )

    Spacer(Modifier.height(15.dp))
}

@Composable
fun MiTelefonoPedido(
    valor: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = valor,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        label = { Text("Teléfono") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Phone
        )
    )

    Spacer(Modifier.height(15.dp))
}

@Composable
fun MiHamburguesa(
    hamburguesaSeleccionada: String,
    menuAbierto: Boolean,
    onMenuChange: (Boolean) -> Unit,
    onHamburguesaChange: (String) -> Unit
) {
    Text("Elige tu hamburguesa:")

    Box(modifier = Modifier.fillMaxWidth()) {

        TextField(
            value = hamburguesaSeleccionada,
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            singleLine = true,
            placeholder = { Text("Seleccionar hamburguesa") },
            trailingIcon = { Text("▼") }
        )

        // Captura el clic sobre el campo
        Box(
            modifier = Modifier
                .matchParentSize()
                .clickable {
                    onMenuChange(true)
                }
        )

        DropdownMenu(
            expanded = menuAbierto,
            onDismissRequest = {
                onMenuChange(false)
            }
        ) {
            listOf(
                "Clásica",
                "Doble queso",
                "BBQ",
                "Vegetal"
            ).forEach { opcion ->

                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        onHamburguesaChange(opcion)
                        onMenuChange(false)
                    }
                )
            }
        }
    }

    Spacer(Modifier.height(15.dp))
}

@Composable
fun MiPicante(
    minimo: Float,
    maximo: Float,
    onValueChange: (ClosedFloatingPointRange<Float>) -> Unit
) {
    val nivelMedio = (minimo + maximo) / 2

    val colorPicante = when {
        nivelMedio < 5 -> Color.Red
        nivelMedio <= 7 -> Color.Yellow
        else -> Color.Green
    }

    Text("Nivel de picante:")

    Text(
        "Rango: ${minimo.toInt()} - ${maximo.toInt()}"
    )

    RangeSlider(
        value = minimo..maximo,
        onValueChange = onValueChange,
        valueRange = 0f..10f,
        colors = SliderDefaults.colors(
            thumbColor = Color(0xFF4B6198),
            activeTrackColor = colorPicante,
            inactiveTrackColor = Color.LightGray
        )
    )

    Spacer(Modifier.height(15.dp))
}

@Composable
fun MiExtraQueso(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Extra de queso",
            modifier = Modifier.weight(1f)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}

@Composable
fun MiResumenPedido(
    nombre: String,
    telefono: String,
    hamburguesa: String,
    minimo: Float,
    maximo: Float,
    queso: Boolean
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Resumen del pedido:")

            Spacer(Modifier.height(10.dp))

            Text("Nombre: ${nombre.ifBlank { "No indicado" }}")
            Text("Teléfono: ${telefono.ifBlank { "No indicado" }}")
            Text("Hamburguesa: ${hamburguesa.ifBlank { "No seleccionada" }}")
            Text("Picante: ${minimo.toInt()} - ${maximo.toInt()}")
            Text("Extra de queso: ${if (queso) "Sí" else "No"}")
        }
    }
}

@Composable
fun MiBotonPedido(
    habilitado: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4B6198),
            contentColor = Color.White,
            disabledContainerColor = Color.Gray
        )
    ) {
        Text("Confirmar pedido")
    }
}