package com.nodosacademy.cuenticas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.nodosacademy.cuenticas.home.ui.HomeScreen
import com.nodosacademy.cuenticas.home.viewmodel.HomeViewModel
import com.nodosacademy.cuenticas.ui.theme.CuenticasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel = HomeViewModel()
        enableEdgeToEdge()
        setContent {
            CuenticasTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeScreen(modifier = Modifier.padding(innerPadding), homeViewModel.state, onEvent = {
                        homeViewModel.onEvent(it)
                    })
                }
            }
        }
    }
}


