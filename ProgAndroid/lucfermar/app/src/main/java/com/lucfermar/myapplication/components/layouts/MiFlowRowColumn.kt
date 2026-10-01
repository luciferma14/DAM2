package com.lucfermar.myapplication.components.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiFlowRowColumn(modifier = Modifier.fillMaxSize())
        }
    }
}

val DIA = Color(0xFF01074A)
val PSP = Color(0xFFD5DE49)
val AD   = Color(0xFFE6B34E)
val DI   = Color(0xFFFF421F)
val PMDM  = Color(0xFFD19FBC)
val PIM  = Color(0xFF0086FC)
val IPE = Color(0xFFFFDD00)
val NP = Color(0xFFF0FC7C)
val SGE = Color(0xFF88D7E3)
val DIG = Color(0xFFFF7300)
val SOS = Color(0xFF1A24EB)
val TUT = Color(0xFFF2DCB8)
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MiFlowRowColumn(modifier: Modifier) {
    // FlowColumn: apila las filas una debajo de otra
    FlowColumn(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(8.dp)
    ) {

        // Fila con los días: fondo azul oscuro y letra blanca
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Lunes", DIA, Color.White)
            Celda("Martes", DIA, Color.White)
            Celda("Miércoles", DIA, Color.White)
            Celda("Jueves", DIA, Color.White)
            Celda("Viernes", DIA, Color.White)
        }

        // Primera hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Prog. de servicios y procesos", PSP, Color.Black)
            Celda("", Color.White, Color.Black)
            Celda("", Color.White, Color.Black)
            Celda("Prog. multimedia y disp. moviles", PMDM, Color.Black)
            Celda("Sis. de gestión empresarial", SGE, Color.Black)
        }

        // Segunda hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Prog. de servicios y procesos", PSP, Color.Black)
            Celda("Prog. multimedia y disp. moviles", PMDM, Color.Black)
            Celda("Nube pública", NP, Color.Black)
            Celda("Prog. multimedia y disp. moviles", PMDM, Color.Black)
            Celda("Sis. gestión empresarial", SGE, Color.Black)
        }

        // Tercera hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Acceso a datos", AD, Color.Black)
            Celda("Prog. multimedia y disp. moviles", PMDM, Color.Black)
            Celda("Nube pública", NP, Color.Black)
            Celda("Digitalización", DIG, Color.Black)
            Celda("IPE", IPE, Color.Black)
        }

        // Cuarta hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Acceso a datos", AD, Color.Black)
            Celda("Proyecto intermodular", PIM, Color.Black)
            Celda("Proyecto intermodular", PIM, Color.Black)
            Celda("Sostenibilidad", SOS, Color.Black)
            Celda("Acceso a datos", AD, Color.Black)
        }

        // Quinta hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Desarrollo de interfaces", DI, Color.Black)
            Celda("IPE", IPE, Color.Black)
            Celda("Proyecto intermodular", PIM, Color.Black)
            Celda("Tutoría", TUT, Color.Black)
            Celda("Acceso a datos", AD, Color.Black)
        }

        // Sexta hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Desarrollo de interfaces", DI, Color.Black)
            Celda("IPE", IPE, Color.Black)
            Celda("Sis. de gestión empresarial", SGE, Color.Black)
            Celda("Desarrollo de interfaces", DI, Color.Black)
            Celda("", Color.White, Color.Black)
        }

        // Séptima hora
        FlowRow(modifier = Modifier.fillMaxWidth(), maxItemsInEachRow = 5) {
            Celda("Desarrollo de interfaces", DI, Color.Black)
            Celda("Nube pública", NP, Color.Black)
            Celda("Sis. de gestión empresarial", SGE, Color.Black)
            Celda("Desarrollo de interfaces", DI, Color.Black)
            Celda("", Color.White, Color.Black)
        }
    }
}

// Una celda de la tabla
// Una celda de la tabla
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowScope.Celda(texto: String, fondo: Color, colorTexto: Color) {
    Box(
        modifier = Modifier
            .weight(1f)       // todas las celdas miden lo mismo
            .height(72.dp)    // más alta para que el texto largo ocupe varias líneas
            .padding(1.dp)
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = texto,
            color = colorTexto,
            fontSize = 13.sp,           // letra pequeña para que quepan los textos completos
            lineHeight = 11.sp,
            textAlign = TextAlign.Center
        )
    }
}

