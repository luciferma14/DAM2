package com.lucfermar.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucfermar.myapplication.components.layouts.MiButton
import com.lucfermar.myapplication.components.layouts.MiCalculadora
import com.lucfermar.myapplication.components.layouts.MiIcono
import com.lucfermar.myapplication.components.layouts.MiImagen
import com.lucfermar.myapplication.components.layouts.MiImagen2
import com.lucfermar.myapplication.components.layouts.MiImagen3
import com.lucfermar.myapplication.components.layouts.MiImagen4
import com.lucfermar.myapplication.components.layouts.MiImagenDeInternet
import com.lucfermar.myapplication.components.layouts.MiProgreso
import com.lucfermar.myapplication.components.layouts.MiProgresoAnimacionEjercicio
import com.lucfermar.myapplication.components.layouts.MiState
import com.lucfermar.myapplication.components.layouts.MiText
import com.lucfermar.myapplication.components.layouts.MiTextField
import com.lucfermar.myapplication.components.layouts.MiTextFieldPadre

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                //Greeting(
                //name = "Lucía",
                //modifier = Modifier.padding(innerPadding)
                //)
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
                MiProgresoAnimacionEjercicio(modifier = Modifier.fillMaxSize().padding(innerPadding))
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