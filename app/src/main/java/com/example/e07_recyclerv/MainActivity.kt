package com.example.e07_recyclerv

import android.annotation.SuppressLint
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
import com.example.e07_recyclerv.ui.theme.E07RecyclerVTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            E07RecyclerVTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Preview(
        showBackground = true,
        showSystemUi = true
)
@Composable
fun GreetingPreview() {
    E07RecyclerVTheme {
        MyApp(modifier = Modifier)
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MyApp(modifier: Modifier){
    Scaffold (modifier = Modifier){ innerpadding ->
        SuperHeroGridView(modifier = Modifier.padding(innerpadding))
        //SimpleRecycleView(modifier = Modifier.padding(innerpadding))
        //SuperHeroStickView(modifier = Modifier.padding(innerpadding))
        //TestImage()

    }
}