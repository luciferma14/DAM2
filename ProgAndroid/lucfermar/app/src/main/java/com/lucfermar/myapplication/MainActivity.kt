package com.lucfermar.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lucfermar.myapplication.components.layouts.MiConstraintLayout1
import com.lucfermar.myapplication.ui.theme.LucfermarTheme
import components.layouts.MiBox
import components.layouts.MiConstraintLayout
//import components.layouts.MiConstraintLayout1
import components.layouts.MiLayoutCombinado
import components.layouts.MiRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LucfermarTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Greeting(
                        //name = "Lucía",
                        //modifier = Modifier.padding(innerPadding)
                    //)
                    //MiBox()
                    //MiColumn()
                    //MiRow(modifier = Modifier.fillMaxSize())
                    //MiLayoutCombinado(modifier = Modifier.fillMaxSize())
                    //MiConstraintLayout(modifier = Modifier.fillMaxSize())
                    MiConstraintLayout1(modifier = Modifier.fillMaxSize())

                }
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