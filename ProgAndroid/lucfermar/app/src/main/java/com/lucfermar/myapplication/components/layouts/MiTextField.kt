package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MiTextFieldPadre(modifier: Modifier){
    var usuario by rememberSaveable{mutableStateOf("Lucía")}
    var mivalor by rememberSaveable { mutableStateOf("") }
    Column(modifier = modifier) {
        //MiTextField(usu = usuario) {usuario = it}
        MiSegundoTextField(valor = mivalor) {mivalor = it}
    }
}
@Composable
fun MiTextField(usu:String, onUserChange:(String) -> Unit) {
    TextField(usu,onValueChange = {onUserChange(it)},readOnly=usu.isEmpty())
}

@Composable
fun MiSegundoTextField(valor: String, onValueChange: (String) -> Unit) {
    TextField(valor,onValueChange = {onValueChange(it)})
}