package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import net.objecthunter.exp4j.ExpressionBuilder

@Composable
fun MiCalculadora(modifier: Modifier = Modifier) {
    // Estado con el texto de la pantalla; rememberSaveable evita perderlo al girar el móvil
    var expresion by rememberSaveable { mutableStateOf("") }

    // Función lambda que se pasa a los botones: actualiza el estado con la tecla pulsada
    val pulsar: (String) -> Unit = { tecla -> expresion = procesarTecla(expresion, tecla) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF1C1C1C))
            .padding(8.dp)
    ) {
        // Pantalla: ocupa 4 partes del alto y el texto va abajo a la derecha
        Box(
            modifier = Modifier.weight(3f).fillMaxWidth().padding(end = 12.dp),
            contentAlignment = Alignment.BottomEnd
        ) {
            Text(
                text = expresion.ifEmpty { "0" },
                color = Color.White,
                fontSize = 60.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis    // "..." si no cabe
            )
        }

        // Fila 1
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Tecla("C", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("+ -", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("%", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("/", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
        }
        // Fila 2
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Tecla("7", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("8", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("9", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("X", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
        }
        // Fila 3
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Tecla("4", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("5", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("6", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("-", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
        }
        // Fila 4
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Tecla("1", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("2", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("3", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("+", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
        }
        // Fila 5: 0 (peso 2, ocupa dos columnas)
        Row(modifier = Modifier.weight(1f).fillMaxWidth()) {
            Tecla("0", Modifier.weight(2f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla(".", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
            Tecla("=", Modifier.weight(1f).fillMaxHeight().padding(5.dp), pulsar)
        }
    }
}
@Composable
fun Tecla(texto: String, modifier: Modifier, onTecla: (String) -> Unit) {
    // Color de fondo según el tipo de tecla
    val colorFondo = when (texto) {
        "C", "+ -", "%" -> Color(0xFFB5B5B5)              // gris claro
        "/", "X", "-", "+", "=" -> Color(0xFFFF9800)      // naranja
        else -> Color(0xFF2D2D2D)                         // gris oscuro (números y punto)
    }
    // Texto negro en las teclas gris claro y blanco en el resto
    val colorTexto = if (texto == "C" || texto == "+ -" || texto == "%") Color.Black else Color.White

    Button(
        onClick = { onTecla(texto) },
        modifier = modifier,
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(
            containerColor = colorFondo,
            contentColor = colorTexto
        )
    ) {
        Text(texto, fontSize = 33.sp, fontWeight = FontWeight.Bold)
    }
}


// Recibe lo que hay en pantalla y la tecla pulsada, y devuelve el nuevo texto
fun procesarTecla(actual: String, tecla: String): String {
    val base = if (actual == "Error") "" else actual     // tras un error se empieza de cero
    return when (tecla) {
        "C" -> ""
        "+ -" -> cambiarSigno(base)
        "%" -> porcentaje(base)
        "=" -> calcular(base)
        else -> base + tecla
    }
}

// Si empieza por "-" se lo quita; si no, se lo pone
fun cambiarSigno(texto: String): String {
    if (texto.isEmpty()) return texto
    return if (texto.startsWith("-")) texto.drop(1) else "-$texto"
}

// Calcula la expresión y divide el resultado entre 100
fun porcentaje(texto: String): String {
    val resultado = calcular(texto)                      // primero se calcula
    if (resultado == "" || resultado == "Error") return resultado
    return formatear(resultado.toDouble() / 100)         // después se divide entre 100
}

// Calcula la expresión con exp4j
fun calcular(texto: String): String {
    if (texto.isEmpty()) return ""
    return try {
        // exp4j entiende "*" y no "X", así que lo cambiamos
        val resultado = ExpressionBuilder(texto.replace("X", "*")).build().evaluate()
        formatear(resultado)
    } catch (e: Exception) {
        "Error"
    }
}

// Si el número es entero quita el ".0" (12.0 -> 12), sino lo deja igual
fun formatear(numero: Double): String {
    return if (numero == numero.toLong().toDouble()) numero.toLong().toString()
    else numero.toString()
}