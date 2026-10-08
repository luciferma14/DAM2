package com.lucfermar.examen_ra1.components.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
@Composable
fun Ejercicio1(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState()).background(Color.LightGray)) {
        val (boxTecho1, boxTecho2, boxTecho3, boxTecho4, boxTecho5, boxTecho6, boxCasa,
            boxVentana1, boxVentana2, boxPuerta, boxPomo, boxNombre) = createRefs()



        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho1){
            start.linkTo(boxTecho2.end)
            bottom.linkTo(boxTecho3.top)
        })

        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho2){
            start.linkTo(boxTecho4.end)
            bottom.linkTo(boxTecho5.top)
        })

        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho3){
            start.linkTo(boxTecho5.end)
            bottom.linkTo(boxTecho6.top)
        })

        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho4){
            bottom.linkTo(boxTecho6.top)
            bottom.linkTo(boxCasa.top)
            start.linkTo(boxCasa.start)
        })

        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho5){
            start.linkTo(boxTecho4.end)
            end.linkTo(boxTecho6.start)
            bottom.linkTo(boxCasa.top)
        })

        Box(Modifier.size(60.dp).background(Color.Red).constrainAs(boxTecho6){
            end.linkTo(boxCasa.end)
            bottom.linkTo(boxCasa.top)
        })

        Box(Modifier.size(300.dp).background(Color.Blue).constrainAs(boxCasa){
            bottom.linkTo(parent.bottom)
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        Box(Modifier.size(60.dp).background(Color.Yellow).constrainAs(boxVentana1){
            top.linkTo(boxTecho1.bottom)
            start.linkTo(boxTecho4.end)
            bottom.linkTo(boxCasa.bottom)
        })

        Box(Modifier.size(60.dp).background(Color.Yellow).constrainAs(boxVentana2){
            top.linkTo(boxTecho1.bottom)
            end.linkTo(boxTecho6.start)
            bottom.linkTo(boxCasa.bottom)
        })

        Box(Modifier.height(130.dp).width(80.dp).background(Color.Gray).constrainAs(boxPuerta){
            bottom.linkTo(boxCasa.bottom)
            start.linkTo(boxCasa.start)
            start.linkTo(boxVentana1.end)
            end.linkTo(boxVentana2.start)
        })

        Box(Modifier.height(13.dp).width(30.dp).background(Color.Black).constrainAs(boxPomo){
            end.linkTo(boxPuerta.end)
            top.linkTo(boxPuerta.top)
            bottom.linkTo(boxCasa.bottom)
        })

        Text("Lucía Ferrandis Martínez")
    }
}