package com.test.application.cardslist.navigation

sealed class Screen(val route: String) {
    object CardsScreen : Screen("cards_screen")
}