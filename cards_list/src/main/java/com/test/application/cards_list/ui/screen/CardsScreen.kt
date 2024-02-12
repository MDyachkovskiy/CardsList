package com.test.application.cards_list.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.application.cards_list.view_model.CardsViewModel
import com.test.application.ui.CardsListTheme
import com.test.application.ui.DynamicSize
import com.test.application.ui.White
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterial3Api
@Composable
fun CardsScreen(
    viewModel: CardsViewModel = getViewModel()
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val density = LocalDensity.current.density

    MaterialTheme{
        Scaffold(
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                CenterAlignedTopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = White
                    ),
                    title = {
                        Text(
                            stringResource(id = com.test.application.core.R.string.cards_list_title),
                            style = MaterialTheme.typography.displayLarge,
                            maxLines = 1,
                            modifier = Modifier.padding(
                                top = DynamicSize.getMarginRed(density),
                                bottom = DynamicSize.getMarginRed(density))
                        )
                    },
                    scrollBehavior = scrollBehavior
                )
            }
        ) { innerPadding ->
            CardsListContent(viewModel, Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun DefaultPreview() {
    CardsListTheme {
        CardsScreen()
    }
}