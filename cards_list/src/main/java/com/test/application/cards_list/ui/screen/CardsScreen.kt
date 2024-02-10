package com.test.application.cards_list.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.application.cards_list.ui.element.CardItem
import com.test.application.cards_list.utils.toColor
import com.test.application.cards_list.view_model.CardsViewModel

@ExperimentalPagingApi
@Composable
fun CardsScreen(
    viewModel: CardsViewModel = viewModel()
) {

    val companies = viewModel.cardsFlow.collectAsLazyPagingItems()

    if (companies.loadState.refresh is LoadState.Loading) {
        CircularProgressIndicator()
    } else if (companies.loadState.refresh is LoadState.Error) {
        Text("Ошибка при загрузке данных")
    }


    LazyColumn {
        items(companies.itemCount) { index ->
            val company = companies[index]

            company?.let {

                val appDashboard = company.mobileAppDashboard
                val customerMarks = company.customerMarkParameters

                CardItem(
                    cardName = appDashboard.companyName,
                    cardBackgroundColor = appDashboard.cardBackgroundColor.toColor(),
                    logo = appDashboard.logo,
                    points = customerMarks.mark,
                    highlightTextColor = appDashboard.highlightTextColor.toColor(),
                    pointsText = "баллы",
                    textColor = appDashboard.textColor.toColor(),
                    cashback = customerMarks.loyaltyLevel.number.toString(),
                    level = customerMarks.loyaltyLevel.name,
                    mainColor = appDashboard.mainColor.toColor(),
                    backgroundColor = appDashboard.backgroundColor.toColor(),
                    accentColor = appDashboard.accentColor.toColor()
                )

            }
            Spacer(Modifier.height(8.dp))
        }
    }
}