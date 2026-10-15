package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun MiTextFieldPadre(modifier: Modifier){
    var usuario by rememberSaveable{mutableStateOf("Lucía")}
    var mivalor1 by rememberSaveable { mutableStateOf("") }
    var mivalor2 by rememberSaveable { mutableStateOf("") }
    var mivalor3 by rememberSaveable { mutableStateOf("") }
    var mivalor4 by rememberSaveable { mutableStateOf("") }
    var mivalor5 by rememberSaveable { mutableStateOf("") }
    var mivalor6 by rememberSaveable { mutableStateOf("") }
    var mivalor7 by rememberSaveable { mutableStateOf("") }
    var mivalor8 by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        MiTextField(usu = usuario) {usuario = it}
        MiSegundoTextField(valor = mivalor1) {mivalor1 = it}
        MiTercerTextField(valor = mivalor2) {mivalor2 = it}
        MiCuartoTextField(valor = mivalor3) {mivalor3 = it}
        MiTextField2(usu = mivalor4) {mivalor4 = it}
        MiQuintoTextField(valor = mivalor5) {mivalor5 = it}
        MiTextFieldAvanzado(valor = mivalor6) {mivalor6 = it}
        MiPasswordTextField(valor = mivalor7) {mivalor7 = it }
        MiOutlinedTextField(valor = mivalor8) {mivalor8 = it }
    }
}
@Composable
fun MiTextField(usu:String, onUserChange:(String) -> Unit) {
    TextField(usu,onValueChange = {onUserChange(it)},readOnly=usu.isEmpty())
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiSegundoTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,onValueChange = {onValueChange(it)})
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiTercerTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,onValueChange = {onValueChange(it)},placeholder = {
        Text("Hola Caracola")
    })
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiCuartoTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,onValueChange = {onValueChange(it)},placeholder = {
        Box(Modifier.size(40.dp).background(Color.Green))
    })
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiTextField2(usu: String, onUserChange: (String) -> Unit) {
    TextField(usu,
        onValueChange = { onUserChange(it) },
        label = { Text("Introduce tu usuario:") })
    Spacer(Modifier.height(20.dp))
}
@Composable
fun MiQuintoTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,
        onValueChange = { onValueChange(it) },
        label = { Text("Introduce tu email:") })
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiTextFieldAvanzado(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,
        onValueChange = {
            if(it.contains("a")){
                onValueChange(it.replace("a",""))
            }
            else {
                onValueChange(it)
            }
        })
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiPasswordTextField(valor: String, onValueChange: (String) -> Unit) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }
    TextField(value = valor,
        onValueChange = { onValueChange(it) },
        singleLine = true,
        label = { Text("Introduce tu contraseña") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordHidden) PasswordVisualTransformation()
        else VisualTransformation.None,
        trailingIcon = {
            Text(text = if (passwordHidden) "Mostrar" else "Ocultar",
                modifier = Modifier.clickable { passwordHidden = !passwordHidden
                })
        })
    Spacer(Modifier.height(20.dp))
}

@Composable
fun MiOutlinedTextField(valor: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = valor, onValueChange = { onValueChange(it) })
}