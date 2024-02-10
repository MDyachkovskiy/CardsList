package com.test.application.cards_list.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.test.application.cards_list.ui.element.CardItem
import com.test.application.cards_list.utils.toColor
import com.test.application.cards_list.view_model.CardsViewModel
import com.test.application.utils.DataState
import org.koin.androidx.compose.getViewModel

@ExperimentalPagingApi
@Composable
fun CardsScreen(
    viewModel: CardsViewModel = getViewModel()
) {

    val companies = viewModel.cardsFlow.collectAsLazyPagingItems()
    val dataState by viewModel.dataState.collectAsState()
    
    Box(modifier = Modifier.fillMaxSize()){
        when(dataState) {
            is DataState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is DataState.Success -> {
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

                    if (companies.loadState.append is LoadState.Loading) {
                        item {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                        }
                    }
                }
            }

            is DataState.Error -> {
                Text("Ошибка при загрузке данных", modifier = Modifier.align(Alignment.Center))
            }
        }
        when {
            companies.loadState.refresh is LoadState.Loading -> {}

            companies.loadState.refresh is LoadState.Error -> {
                val e = companies.loadState.refresh as LoadState.Error
                Text("Ошибка загрузки: ${e.error.localizedMessage}",
                    modifier = Modifier.align(Alignment.Center))
            }
            companies.loadState.append is LoadState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.BottomCenter).padding(16.dp))
            }
            companies.loadState.refresh is LoadState.NotLoading && companies.itemCount == 0 -> {
                Text("Карты не найдены", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}