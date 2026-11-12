package com.lucfermar.ra2.components.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.lucfermar.ra2.R

@Composable
fun MiSwitch(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        var StadoDelswitch by rememberSaveable { mutableStateOf(true) }
        Switch(checked = StadoDelswitch,
            onCheckedChange = { StadoDelswitch = it },
            colors = SwitchDefaults.colors(Color.Green, uncheckedThumbColor = Color.Red),
            thumbContent = {Icon(
                painter = painterResource(R.drawable.outline_accessibility_24),
                contentDescription = "Accesible",
            )},
            enabled = true
        )
    }
}