package com.lucfermar.myapplication.components.layouts

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun MiButton(modifier: Modifier) {
    Column(modifier = modifier) {
        Button(onClick = { Log.i("Lucía", "Botón pulsado")},
            border = BorderStroke(2.dp, Color.Black),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Magenta,
                contentColor = Color.Black)
        )
        {
            Text("Púlsame")
        }
        Spacer(Modifier.height(20.dp))

        OutlinedButton(onClick = {}, colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.LightGray
        )) {
            Text("Outlined")
        }
        Spacer(Modifier.height(20.dp))

        ElevatedButton(onClick = {}, elevation =
            ButtonDefaults.elevatedButtonElevation()) {
            Text("ElevatedButton")
        }
    }
}

