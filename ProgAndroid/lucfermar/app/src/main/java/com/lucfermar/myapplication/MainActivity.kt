package com.lucfermar.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucfermar.myapplication.components.layouts.MiDropDownItem
import com.lucfermar.myapplication.components.layouts.MiDropDownMenu
import com.lucfermar.myapplication.components.layouts.MiExposedDropDownMenu
import com.lucfermar.myapplication.components.layouts.MiListaRadioButton
import com.lucfermar.myapplication.components.layouts.MiPadreCheckBoxes
import com.lucfermar.myapplication.components.layouts.MiRadioButton
import com.lucfermar.myapplication.components.layouts.MiRangeSlider
import com.lucfermar.myapplication.components.layouts.MiSlider
import com.lucfermar.myapplication.components.layouts.MiSliderAvanzado
import com.lucfermar.myapplication.components.layouts.MiTercerEstadoCheckBox
import com.lucfermar.myapplication.components.layouts.RadioButtonComponent


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //Greeting(
                //name = "Lucía",
                //modifier = Modifier.padding(innerPadding))
                //MiBox()
                //MiColumn()
                //MiRow(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiLayoutCombinado(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiConstraintLayout(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiConstraintLayout1(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiEjercicio1(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiFlowRowColumn(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiState(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiText(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiTextFieldPadre(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiButton(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiCalculadora(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiImagen4()
                //MiImagenDeInternet()
                //MiIcono()
                //MiProgreso(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiProgresoAnimacionEjercicio(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiSwitch(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiCheckBox(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiPadreCheckBoxes(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiTercerEstadoCheckBox(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiListaRadioButton(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiSlider(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiSliderAvanzado(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiRangeSlider(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiDropDownItem(modifier = Modifier.fillMaxSize().padding(innerPadding))
                //MiDropDownMenu(modifier = Modifier.padding(innerPadding))
                MiExposedDropDownMenu(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LucfermarTheme {
        Greeting("Android")
    }
}

@Composable
fun LucfermarTheme(content: @Composable () -> Unit) {
    TODO("Not yet implemented")
}