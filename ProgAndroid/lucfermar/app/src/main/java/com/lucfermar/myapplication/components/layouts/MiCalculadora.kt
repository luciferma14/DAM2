package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder

private val operadores = listOf("+", "-", "x", "/", "%")

private val filas = listOf(
    listOf("C", "<-", "%", "/"),
    listOf("7", "8", "9", "x"),
    listOf("4", "5", "6", "-"),
    listOf("1", "2", "3", "+"),
    listOf("0", ".", "=")
)

@Composable
fun MiCalculadora(modifier: Modifier = Modifier) {
    var pantalla by rememberSaveable { mutableStateOf("0") }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation ==
            android.content.res.Configuration.ORIENTATION_LANDSCAPE

    val paddingGeneral = if (isLandscape) 4.dp else 12.dp
    val tamanoPantalla = if (isLandscape) 36.sp else 56.sp

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(paddingGeneral)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1.2f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = pantalla,
                color = Color.White,
                fontSize = tamanoPantalla,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.fillMaxWidth()
            )
        }

        filas.forEach { fila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                fila.forEach { texto ->
                    BotonCalculadora(
                        texto = texto,
                        modifier = Modifier
                            .weight(if (texto == "0") 2f else 1f)
                            .fillMaxHeight()
                            .padding(4.dp),
                        onClick = {
                            pantalla = pulsarBoton(pantalla, texto)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BotonCalculadora(
    texto: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorFondo = when {
        texto in operadores || texto == "=" -> Color(0xFFFF9500)
        texto == "C" || texto == "<-" -> Color(0xFFA5A5A5)
        else -> Color(0xFF333333)
    }

    val colorTexto = if (texto == "C" || texto == "<-") {
        Color.Black
    } else {
        Color.White
    }

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo,
            contentColor = colorTexto
        )
    ) {
        Text(texto, fontSize = 28.sp)
    }
}

fun pulsarBoton(pantalla: String, boton: String): String {
    val actual = if (pantalla == "Error") "0" else pantalla

    return when {
        boton == "C" -> "0"

        boton == "<-" -> actual.dropLast(1).ifEmpty { "0" }

        boton == "=" -> calcular(actual)

        boton in operadores -> when {
            actual == "0" && boton == "-" -> "-"
            actual == "-" -> actual
            actual.last().toString() in operadores ->
                actual.dropLast(1) + boton
            else -> actual + boton
        }

        boton == "." -> {
            val ultimoNumero = actual.split("+", "-", "x", "/", "%").last()
            if (ultimoNumero.contains(".")) actual else actual + "."
        }

        else -> if (actual == "0") boton else actual + boton
    }
}

fun calcular(expresion: String): String {
    if (expresion.last().toString() in operadores) return expresion

    return try {
        val expresionCalculable = expresion.replace("x", "*")

        val resultado = ExpressionBuilder(expresionCalculable)
            .build()
            .evaluate()

        if (resultado == resultado.toLong().toDouble()) {
            resultado.toLong().toString()
        } else {
            resultado.toString()
        }
    } catch (e: Exception) {
        "Error"
    }
}