package com.test.application.cards_list.ui.element

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.application.ui.Black
import com.test.application.ui.CardsListTheme
import com.test.application.ui.DynamicSize

@Composable
fun StartPreloader(density: Float) {
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        CardsListTheme {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(DynamicSize.getPreloaderSize(density)),
                    color = Black
                )
                Spacer(modifier = Modifier.height(DynamicSize.getMarginYellow(density)))
                Text(
                    text = stringResource(id = com.test.application.core.R.string.start_loader),
                    style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview
@Composable
private fun StartPreloaderPreview() {
    val density = LocalDensity.current.density
    StartPreloader(density)
}