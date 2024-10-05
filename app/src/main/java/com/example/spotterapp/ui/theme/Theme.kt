package com.example.spotterapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp // Import for sp text size unit
import com.example.spotterapp.R

// Define your custom colors
val LightRed = Color(0xFFFFCDD2) // Light red background
val Black = Color(0xFF000000)    // Black for button background
val White = Color(0xFFFFFFFF)    // White for text in buttons
val TextBlack = Color(0xFF000000) // Black text outside buttons

// Define Color Schemes
private val DarkColorScheme = darkColorScheme(
    primary = Black,          // Black for buttons
    onPrimary = White,        // White text on buttons
    background = Color.DarkGray, // Dark background for dark mode
    onBackground = White      // Text color on dark background
)

private val LightColorScheme = lightColorScheme(
    primary = Black,          // Black for buttons
    onPrimary = White,        // White text on buttons
    background = LightRed,    // Light red background
    onBackground = TextBlack  // Black text outside buttons
)

// Define custom typography with Quantico font
val Quantico = FontFamily(
    Font(R.font.quantico_regular, FontWeight.Normal),
    Font(R.font.quantico_bold, FontWeight.Bold)
)

val CustomTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Quantico,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        color = TextBlack // Black text outside buttons
    ),
    labelLarge = TextStyle(  // Use labelLarge for button text style in Material 3
        fontFamily = Quantico,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = White // White text in buttons
    ),
    titleLarge = TextStyle(
        fontFamily = Quantico,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        color = TextBlack // Black text outside buttons
    )
)

@Composable
fun SpotterAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = CustomTypography,  // Apply custom Quantico typography
        content = content
    )
}
