package com.bsquare.app.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.bsquare.app.R

data class TextStyles(
    val default: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 15.sp,
        color = Color.Black,
    ),
    val getStartedStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 26.sp,
        color = Color.Black
    ),
    val dialogHeadingStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 20.sp,
        color = Color.Black,

        ),
    val dialogBtnStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 12.sp,
        color = Color.Black,
    ),
    val dialogMessageStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 17.sp,
        color = Color.Black,
    ),
    val getMovingFarmologyStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_extra_bold)),
        fontSize = 22.sp,
        color = Color.Black,
    ),
    val mobileNumberStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 16.sp,
        color = Color.Black
    ),
    val continueBtnStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_light)),
        fontSize = 20.sp,
        color = Color.White
    ),
    val otpInputStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 25.sp,
        color = Color.Black
    ),
    val descriptionStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_light)),
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    val resendCodeStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_bold)),
        fontSize = 16.sp,
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    val dashboardTextStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_extra_bold)),
        fontSize = 25.sp,
        color = Color.White,

        ),
    val drawerMenuItemStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 22.sp,
        color = Color.Black
    ),
    val pendingOrderItemStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 15.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,

        ),
    val noItemsStlye: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_regular)),
        fontSize = 22.sp,
        color = Color.LightGray,
        textAlign = TextAlign.Center
    ),
    val paymentAmountStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_extra_bold)),
        fontSize = 55.sp,
        color = Color.White
    ),
    val payStyle: TextStyle = TextStyle(
        fontFamily = FontFamily(Font(R.font.poppins_medium)),
        fontSize = 22.sp,
        color = Color.White
    )
)

val LocalTextStyles = compositionLocalOf { TextStyles() }

val MaterialTheme.appTextStyles
    @Composable
    @ReadOnlyComposable
    get() = LocalTextStyles.current
