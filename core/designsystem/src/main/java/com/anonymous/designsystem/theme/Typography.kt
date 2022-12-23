package com.anonymous.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.anonymous.designsystem.R

val Shabnam = FontFamily(
    Font(R.font.dana_light, FontWeight.W300),
    Font(R.font.dana_regular, FontWeight.W400),
    Font(R.font.dana_medium, FontWeight.W500),
    Font(R.font.dana_bold, FontWeight.W600),
)

val ChandChandTypography = Typography(
    displayLarge = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W300,
        fontSize = 96.sp,
        letterSpacing = (-1.5).sp
    ),
    displayMedium = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W300,
        fontSize = 60.sp,
        letterSpacing = (-0.5).sp
    ),
    displaySmall = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.Normal,
        fontSize = 48.sp,
        letterSpacing = 0.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 30.sp,
        letterSpacing = 0.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp
    ),
    titleSmall = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.1.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 14.sp,
        letterSpacing = 0.25.sp
    ),
    labelSmall = TextStyle(
        fontFamily = Shabnam,
        fontWeight = FontWeight.W600,
        fontSize = 12.sp,
        letterSpacing = 1.sp
    )
)