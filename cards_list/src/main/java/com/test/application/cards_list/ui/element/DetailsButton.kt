package com.test.application.cards_list.ui.element

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.test.application.ui.DynamicTextSize

@Composable
fun DetailsButton(
    backgroundColor: Color,
    mainColor: Color,
    density: Float,
    onClick: () -> Unit = {},
    modifier: Modifier
    ) {
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = backgroundColor,
        contentColor = mainColor
    )
    val contentPadding = PaddingValues(
        start = 32.dp,
        top = 10.dp,
        end = 32.dp,
        bottom = 10.dp
    )

    Button(
        contentPadding = contentPadding,
        colors = buttonColors,
        shape = RoundedCornerShape(12.dp),
        onClick = onClick,
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = com.test.application.core.R.string.btn_details),
            style = TextStyle(
                fontSize = DynamicTextSize.getTextSizeThree(density),
                color = mainColor
            )
        )
    }
}