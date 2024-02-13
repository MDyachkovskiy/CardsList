package com.test.application.cards_list.ui.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.application.core.R

@Composable
fun NoCardText() {
    Box(
        modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.no_card_company),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.background(Color.LightGray)
        )
    }
}

@Preview
@Composable
private fun NoCardTextPreview() {
    NoCardText()
}