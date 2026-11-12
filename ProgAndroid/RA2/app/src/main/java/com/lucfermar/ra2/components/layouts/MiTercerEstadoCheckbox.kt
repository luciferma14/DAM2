package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.unit.dp

@Composable
fun MiTercerEstadoCheckbox(modifier: Modifier = Modifier) {
    var hijo1 by rememberSaveable { mutableStateOf(false) }
    var hijo2 by rememberSaveable { mutableStateOf(false) }
    var EstadoDelpadre by rememberSaveable { mutableStateOf(ToggleableState.Off) }
    LaunchedEffect(hijo1, hijo2) {
        //Cuando pulso sobre uno de los hijos
        //Si los dos hijos son true, el padre es true
        //Si los dos hijos son false, el padre es false
        //En caso contrario, el padre queda con un valor indeterminado
        EstadoDelpadre = when {
            hijo1 && hijo2 -> ToggleableState.On
            !hijo1 && !hijo2 -> ToggleableState.Off
            else -> ToggleableState.Indeterminate
        }
    }
    Column(modifier = modifier){
        Row(verticalAlignment = Alignment.CenterVertically){
            TriStateCheckbox(EstadoDelpadre, onClick = {
                //Cuando pulso sobre el padre, se modifican los hijos
                val nuevoEstado = EstadoDelpadre != ToggleableState.On
                hijo1 = nuevoEstado
                hijo2 = hijo1
            })
            Text("Padre")
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)){
            Checkbox(hijo1, onCheckedChange = {hijo1 = it})
            Text("Primero")
        }
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 20.dp)){
            Checkbox(hijo2, onCheckedChange = {hijo2 = it})
            Text("Segundo")
        }
    }
}