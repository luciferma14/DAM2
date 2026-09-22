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
import com.lucfermar.myapplication.ui.theme.LucfermarTheme
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
                    MiLayoutCombinado(modifier = Modifier.fillMaxSize())

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