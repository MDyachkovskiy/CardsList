package com.test.application.cardslist.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.test.application.cards_list.ui.screen.CardsScreen

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.CardsScreen.route,
        modifier = modifier
    ) {
        composable(Screen.CardsScreen.route) {
            CardsScreen()
        }
    }
}