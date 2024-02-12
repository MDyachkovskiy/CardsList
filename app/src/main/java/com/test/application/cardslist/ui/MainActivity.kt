package com.test.application.cardslist.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.test.application.cardslist.navigation.AppNavHost
import com.test.application.ui.CardsListTheme

@ExperimentalMaterial3Api
@Composable
fun MyApp() {
    val navController = rememberNavController()
    MaterialTheme {
        Scaffold(

        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DefaultPreview() {
    CardsListTheme {
        MyApp()
    }
}