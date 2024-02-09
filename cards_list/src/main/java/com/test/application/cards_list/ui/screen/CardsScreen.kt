package com.test.application.cards_list.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.test.application.cards_list.ui.element.CardItem
import com.test.application.cards_list.view_model.CardsViewModel

@Composable
fun CardsScreen(
    viewModel: CardsViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {

    LazyColumn {
        items(cards) { card ->
            CardItem(
                cardName = card.name,
                cardBackgroundColor = MaterialTheme.colorScheme.surface,
                logo = Icons.Default.AccountBox,
                points = card.points,
                highlightTextColor = MaterialTheme.colorScheme.onSecondary,
                pointsText = "баллы",
                textColor = MaterialTheme.colorScheme.onSurface,
                cashback = card.cashback,
                level = card.level,
                mainColor = MaterialTheme.colorScheme.primary,
                backgroundColor = MaterialTheme.colorScheme.background,
                accentColor = MaterialTheme.colorScheme.secondary
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}