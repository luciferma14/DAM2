package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun MiConstraintLayout1(modifier: Modifier = Modifier) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {//Ocupa toda la pantalla
        val (boxRed, boxGray, boxGreen, boxMagenta, boxYellow, boxBlack, boxCyan, boxBlue, boxDarkGray) =
            createRefs()

        Box(Modifier.size(75.dp).background(Color.Red).constrainAs(boxRed){
            top.linkTo(boxYellow.bottom)
            start.linkTo(boxYellow.end)
        })

        Box(Modifier.size(75.dp).background(Color.Gray).constrainAs(boxGray){
            top.linkTo(boxYellow.bottom)
            end.linkTo(boxYellow.start)
        })

        Box(Modifier.size(75.dp).background(Color.Green).constrainAs(boxGreen){
            bottom.linkTo(boxYellow.top)
            start.linkTo(boxYellow.end)
        })

        Box(Modifier.size(75.dp).background(Color.Magenta).constrainAs(boxMagenta
        ){
            bottom.linkTo(boxYellow.top)
            end.linkTo(boxYellow.start)
        })

        Box(Modifier.size(75.dp).background(Color.Yellow).constrainAs(boxYellow){
            bottom.linkTo(parent.bottom) //enganchamos la parte inferior a la parte inferior del padre
            end.linkTo(parent.end)
            top.linkTo(parent.top)
            start.linkTo(parent.start)
        })

        Box(Modifier.size(75.dp).background(Color.Black).constrainAs(boxBlack){
            start.linkTo(boxCyan.end)
            top.linkTo(boxDarkGray.top)
            bottom.linkTo(boxGreen.top)
        })

        Box(Modifier.size(175.dp).background(Color.Cyan).constrainAs(boxCyan)   {
            bottom.linkTo(boxMagenta.top)
            end.linkTo(boxMagenta.end)
        })

        Box(Modifier.size(175.dp).background(Color.Blue).zIndex(-1f).constrainAs(boxBlue){
            top.linkTo(boxYellow.bottom)
            end.linkTo(boxRed.start)
            start.linkTo(boxGray.end)
        })

        Box(Modifier.size(175.dp).background(Color.DarkGray).constrainAs(boxDarkGray){
            bottom.linkTo(boxGreen.top)
            start.linkTo(boxGreen.start)
        })
    }
}