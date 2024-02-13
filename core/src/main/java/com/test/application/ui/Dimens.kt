package com.test.application.ui

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object DynamicSize {

    fun getMarginRed(density: Float): Dp {
        return if (density < 2.0) 12.dp else 16.dp
    }

    fun getMarginYellow(density: Float): Dp {
        return if (density < 2.0) 6.dp else 8.dp
    }

    fun getMarginBlue(density: Float): Dp {
        return if (density < 2.0) 34.dp else 48.dp
    }

    fun getLogoSize(density: Float): Dp {
        return if (density < 2.0) 32.dp else 40.dp
    }

    fun getIconSize(density: Float): Dp {
        return if (density < 2.0) 12.dp else 20.dp
    }

    fun getPreloaderSize(density: Float): Dp {
        return if (density < 2.0) 32.dp else 40.dp
    }
}

object DynamicTextSize {
    fun getTextSizeZero(density: Float): TextUnit {
        return if (density < 2.0) 20.sp else 24.sp
    }

    fun getTextSizeOne(density: Float): TextUnit {
        return if (density < 2.0) 16.sp else 20.sp
    }

    fun getTextSizeTwo(density: Float): TextUnit {
        return if (density < 2.0) 12.sp else 16.sp
    }

    fun getTextSizeThree(density: Float): TextUnit {
        return if (density < 2.0) 10.sp else 14.sp
    }
    fun getTextSizeFour(density: Float): TextUnit {
        return if (density < 2.0) 8.sp else 12.sp
    }


}

