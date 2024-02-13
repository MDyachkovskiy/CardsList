package com.test.application.cards_list.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.application.cards_list.ui.element.CardItem
import com.test.application.cards_list.ui.element.NoCardText
import com.test.application.cards_list.ui.element.StartPreloader
import com.test.application.cards_list.utils.toColor
import com.test.application.cards_list.view_model.CardsViewModel
import com.test.application.ui.DynamicSize

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalMaterial3Api
@Composable
fun CardsListContent(
    viewModel: CardsViewModel,
    modifier: Modifier = Modifier
) {
    val companies = viewModel.cardsFlow.collectAsLazyPagingItems()
    val density = LocalDensity.current.density
    val refreshState = rememberPullRefreshState(
        refreshing = companies.loadState.refresh is LoadState.Loading,
        onRefresh = { companies.refresh() }
    )

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = DynamicSize.getMarginRed(density),
                start = DynamicSize.getMarginRed(density),
                end = DynamicSize.getMarginRed(density)
            )

    ){
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
                Spacer(Modifier.height(DynamicSize.getMarginRed(density)))
            }

            if (companies.loadState.append is LoadState.Loading) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(DynamicSize.getMarginRed(density)),
                        contentAlignment = Alignment.Center
                    ) {
                        StartPreloader(density)
                    }
                }
            }
        }

        when {
            companies.loadState.refresh is LoadState.Error -> {
                val e = companies.loadState.refresh as LoadState.Error
                Text("Ошибка загрузки: ${e.error.localizedMessage}",
                    modifier = Modifier.align(Alignment.Center))
            }

            companies.loadState.append is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(DynamicSize.getMarginRed(density)),
                    contentAlignment = Alignment.Center
                ) {
                    StartPreloader(density)
                }
            }

            companies.loadState.refresh is LoadState.NotLoading && companies.itemCount == 0 -> {
                NoCardText()
            }
        }

        PullRefreshIndicator(
            refreshing = companies.loadState.refresh is LoadState.Loading,
            state = refreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}