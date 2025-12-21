package com.design.template

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.design.template.ui.navigation.AppNavigation
import com.design.template.ui.theme.DocumentScannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        android.util.Log.d("MainActivity", "MainActivity created")

        enableEdgeToEdge()

        setContent {
            DocumentScannerTheme {
                AppNavigation()
            }
        }
    }
}
