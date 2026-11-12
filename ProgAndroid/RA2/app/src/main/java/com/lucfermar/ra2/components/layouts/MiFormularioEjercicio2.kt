package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun MiFormularioEjercicio2(modifier: Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var apellidos by rememberSaveable { mutableStateOf("") }
    var genero by rememberSaveable { mutableStateOf("") }
    var contra by rememberSaveable { mutableStateOf("") }
    var aceptaCondiciones by rememberSaveable { mutableStateOf(false) }
    var registrado by rememberSaveable { mutableStateOf(false) }

    // El botón solo se habilita si todo está completo
    val formularioCompleto = nombre.isNotBlank()
            && apellidos.isNotBlank()
            && genero.isNotBlank()
            && contra.isNotBlank()
            && aceptaCondiciones

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        MiNombre(valor = nombre) { nombre = it; registrado = false }
        MiApellido(valor = apellidos) { apellidos = it; registrado = false }
        MiGenero(generoSeleccionado = genero) { genero = it; registrado = false }
        MiContrasenya(valor = contra) { contra = it; registrado = false }
        MiCondicionesUso(checked = aceptaCondiciones) { aceptaCondiciones = it; registrado = false }
        Spacer(Modifier.height(20.dp))
        MiBoton(habilitado = formularioCompleto) { registrado = true }

        if (registrado) {
            Spacer(Modifier.height(16.dp))
            Text("¡Registro completado correctamente!", color = Color(0xFF2E7D32))
        }
    }
}

@Composable
fun MiNombre(valor: String, onValueChange: (String) -> Unit) {
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text("Nombre") }
    )
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiApellido(valor: String, onValueChange: (String) -> Unit) {
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        placeholder = { Text("Apellidos") }
    )
    Spacer(Modifier.height(20.dp))
}

@Composable
fun RadioButtonComponente(
    nombre: String,
    NombreSeleccionado: String,
    ItemSeleccionado: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = nombre == NombreSeleccionado,
            onClick = { ItemSeleccionado(nombre) }
        )
        Text(nombre, modifier = Modifier.clickable { ItemSeleccionado(nombre) })
    }
}

@Composable
fun MiGenero(generoSeleccionado: String, onGeneroChange: (String) -> Unit) {
    Text("Género:")
    Column {
        RadioButtonComponente("Masculino", generoSeleccionado, onGeneroChange)
        RadioButtonComponente("Femenino", generoSeleccionado, onGeneroChange)
    }
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiContrasenya(valor: String, onValueChange: (String) -> Unit) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(
        value = valor,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = { Text("Contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            Text(
                text = if (passwordHidden) "Mostrar" else "Ocultar",
                modifier = Modifier
                    .padding(end = 8.dp)
                    .clickable { passwordHidden = !passwordHidden }
            )
        }
    )
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiCondicionesUso(checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Switch(checked = checked, onCheckedChange = onCheckedChange)
        Text("Aceptar las condiciones de uso", modifier = Modifier.weight(1f))
    }
}

@Composable
fun MiBoton(habilitado: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = habilitado,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF4CAF50),          // verde habilitado
            contentColor = Color.White,
            disabledContainerColor = Color.Gray,         // gris deshabilitado
            disabledContentColor = Color.White
        )
    ) {
        Text("Registrarse")
    }
}