package com.lucfermar.myapplication.components.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lucfermar.myapplication.R


@Preview
@Composable
fun MiIcono() {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){
        Column{
            Icon(
                painter = painterResource(R.drawable.outline_accessibility_24),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Blue
            )

            Icon(
                painter = painterResource(R.drawable.avion),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Blue
            )
        }

        Column{
            Icon(
                painter = painterResource(R.drawable.outline_adb_24),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Blue
            )

            Icon(
                painter = painterResource(R.drawable.golf),
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                tint = Color.Blue
            )
        }
    }
}