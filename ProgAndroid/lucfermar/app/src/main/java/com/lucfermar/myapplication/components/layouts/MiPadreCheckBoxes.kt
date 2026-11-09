package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CheckBoxConText(
    modifier: Modifier = Modifier,
    miestadoCheckbox: EstadoCheckbox,
    siCheckedCambia: (EstadoCheckbox) -> Unit
) {
    // Si el estado trae color lo usa; si no, el color primario del tema
    val colorMarcado = miestadoCheckbox.color ?: MaterialTheme.colorScheme.primary

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { siCheckedCambia(miestadoCheckbox) }
    ) {
        Checkbox(
            checked = miestadoCheckbox.checked,
            onCheckedChange = { siCheckedCambia(miestadoCheckbox) },
            enabled = true,
            colors = CheckboxDefaults.colors(
                checkedColor = colorMarcado,
                uncheckedColor = Color.Gray,
                checkmarkColor = Color.White,
                disabledCheckedColor = Color.LightGray,
                disabledUncheckedColor = Color.LightGray
            )
        )
        Spacer(Modifier.width(12.dp))
        Text(miestadoCheckbox.label)
    }
}

@Composable
fun MiPadreCheckBoxes(modifier: Modifier = Modifier) {
    var estado by remember {
        mutableStateOf(
            listOf(
                EstadoCheckbox("ampa", "Es miembro del AMPA", color = Color.Green),
                EstadoCheckbox("repetidor", "Está repitiendo el curso", color = Color.Red),
                EstadoCheckbox("fcts", "Aporta empresa para FCTs", true, Color.Blue),
            )
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        estado.forEach { miEstado ->
            CheckBoxConText(miestadoCheckbox = miEstado) {
                // Controlamos lo que se va a hacer en el onCheckedChange:
                estado = estado.map {
                    if (it.id == miEstado.id) {
                        it.copy(checked = !it.checked)
                    } else {
                        it
                    }
                }
            }
        }
    }
}