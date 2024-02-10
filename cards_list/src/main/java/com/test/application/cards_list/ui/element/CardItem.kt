package com.test.application.cards_list.ui.element

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.test.application.cards_list.R
import com.test.application.ui.Black
import com.test.application.ui.Blue
import com.test.application.ui.DynamicSize
import com.test.application.ui.DynamicTextSize
import com.test.application.ui.LightGrey
import com.test.application.ui.PurpleGrey40
import com.test.application.ui.Red


@Composable
fun CardItem(
    cardName: String,
    cardBackgroundColor: Color,
    logo: String,
    points: Int,
    highlightTextColor: Color,
    pointsText: String,
    textColor: Color,
    cashback: String,
    level: String,
    mainColor: Color,
    backgroundColor: Color,
    accentColor: Color
) {
    val density = LocalDensity.current.density
    val shape = RoundedCornerShape(corner = CornerSize(16.dp))
    Card(
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp)
            .shadow(elevation = 4.dp, shape = shape),
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        ConstraintLayout(
            Modifier
                .padding(DynamicSize.getMarginRed(density))
        ) {

            //"Bonus Money"
            val (cardNameTextRef, logoRef) = createRefs()
            Text(
                text = cardName,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeOne(density),
                    fontWeight = FontWeight.Normal,
                    color = highlightTextColor
                ),
                modifier = Modifier
                    .constrainAs(cardNameTextRef) {
                        top.linkTo(logoRef.top)
                        bottom.linkTo(logoRef.bottom)
                        start.linkTo(parent.start)
                    }
            )


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(logo)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(logoRef) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                    }
                    .size(DynamicSize.getLogoSize(density))
            )

            val dividerRef = createRef()
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .constrainAs(dividerRef) {
                        top.linkTo(logoRef.bottom, margin = DynamicSize.getMarginYellow(density))
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
            )

            //"200"
            val pointsTextRef = createRef()
            Text(
                text = points.toString(),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeZero(density),
                    fontWeight = FontWeight.Bold,
                    color = highlightTextColor
                ),
                modifier = Modifier
                    .constrainAs(pointsTextRef) {
                        top.linkTo(dividerRef.bottom, margin = DynamicSize.getMarginRed(density))
                        start.linkTo(cardNameTextRef.start)
                    }
            )

            //"баллов"
            val pointsLabelTextRef = createRef()
            Text(
                text = pointsText,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeTwo(density),
                    fontWeight = FontWeight.Normal,
                    color = textColor
                ),
                modifier = Modifier
                    .constrainAs(pointsLabelTextRef) {
                        start.linkTo(
                            pointsTextRef.end,
                            margin = DynamicSize.getMarginYellow(density)
                        )
                        bottom.linkTo(pointsTextRef.bottom)
                    }
            )

            //"Кешбэк"
            val cashbackLabelRef = createRef()
            Text(
                text = stringResource(id = com.test.application.core.R.string.cashback_label),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeFour(density),
                    fontWeight = FontWeight.Normal,
                    color = textColor
                ),
                modifier = Modifier
                    .constrainAs(cashbackLabelRef) {
                        top.linkTo(pointsTextRef.bottom, margin = DynamicSize.getMarginRed(density))
                        start.linkTo(pointsTextRef.start)
                    }
            )

            //"1%"
            val cashbackRef = createRef()
            Text(
                text = cashback,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeThree(density),
                    fontWeight = FontWeight.Normal,
                    color = highlightTextColor
                ),
                modifier = Modifier
                    .constrainAs(cashbackRef) {
                        top.linkTo(
                            cashbackLabelRef.bottom,
                            margin = DynamicSize.getMarginYellow(density)
                        )
                        start.linkTo(cashbackLabelRef.start)
                    }
            )

            //"Уровень"
            val levelLabelRef = createRef()
            Text(
                text = stringResource(id = com.test.application.core.R.string.level_label),
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeFour(density),
                    fontWeight = FontWeight.Normal,
                    color = textColor
                ),
                modifier = Modifier
                    .constrainAs(levelLabelRef) {
                        start.linkTo(
                            cashbackLabelRef.end,
                            margin = DynamicSize.getMarginBlue(density)
                        )
                        top.linkTo(cashbackLabelRef.top)
                    }
            )

            //Базовый уровень
            val levelRef = createRef()
            Text(
                text = level,
                style = TextStyle(
                    fontFamily = FontFamily.Default,
                    fontSize = DynamicTextSize.getTextSizeThree(density),
                    fontWeight = FontWeight.Normal,
                    color = highlightTextColor
                ),
                modifier = Modifier
                    .constrainAs(levelRef) {
                        start.linkTo(levelLabelRef.start)
                        top.linkTo(
                            levelLabelRef.bottom,
                            margin = DynamicSize.getMarginYellow(density)
                        )
                    }
            )

            val dividerSecondRef = createRef()
            HorizontalDivider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .constrainAs(dividerSecondRef) {
                        top.linkTo(levelRef.bottom, margin = DynamicSize.getMarginYellow(density))
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
            )

            val btnDetails = createRef()
            DetailsButton(
                backgroundColor = backgroundColor,
                mainColor = mainColor,
                density = density,
                modifier = Modifier
                    .constrainAs(btnDetails) {
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        top.linkTo(dividerSecondRef.bottom, margin = DynamicSize.getMarginYellow(density))
                    }
            )

            val icTrash = createRef()
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                contentDescription = null,
                tint = accentColor,
                modifier = Modifier
                    .constrainAs(icTrash) {
                        top.linkTo(btnDetails.top)
                        bottom.linkTo(btnDetails.bottom)
                        end.linkTo(btnDetails.start, margin = DynamicSize.getMarginBlue(density))
                    }
                    .size(DynamicSize.getIconSize(density))
            )

            val icEye = createRef()
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_eye),
                contentDescription = null,
                tint = mainColor,
                modifier = Modifier
                    .constrainAs(icEye) {
                        top.linkTo(btnDetails.top)
                        bottom.linkTo(btnDetails.bottom)
                        end.linkTo(icTrash.start, margin = DynamicSize.getMarginBlue(density))
                    }
                    .size(DynamicSize.getIconSize(density))
            )


        }
    }
}

@Preview(showBackground = true)
@Composable
fun CardItemPreview() {
    CardItem(
        cardName = "Bonus Money",
        logo = "http://bonusmoney.info/image/mail/logo3.png",
        points = 200,
        pointsText = "баллов",
        cashback = "5%",
        textColor = LightGrey,
        level = "Gold",
        cardBackgroundColor = PurpleGrey40,
        highlightTextColor = Black,
        mainColor = Blue,
        backgroundColor = LightGrey,
        accentColor = Red
    )
}