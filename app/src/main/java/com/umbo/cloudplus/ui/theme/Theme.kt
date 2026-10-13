package com.umbo.cloudplus.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkBackgroundColor = Color(0xFF16191B)
private val DarkSurfaceColor = Color(0xFF202427)
private val DarkSurfaceVariant = Color(0xFF2B3135)
private val DarkPrimaryColor = Color(0xFF38B6FF)
private val DarkTealContainer = Color(0xFF2C4347)
private val DarkOnSurface = Color(0xFFE2E8EE)
private val DarkOnSurfaceVariant = Color(0xFF9EACB5)

private val LightColors = lightColorScheme(
    primary = Color(0xFF006494),
    surface = Color(0xFFF8F9FA),
    background = Color(0xFFF8F9FA),
)

private val DarkColors = darkColorScheme(
    primary = DarkPrimaryColor,
    background = DarkBackgroundColor,
    surface = DarkSurfaceColor,
    surfaceVariant = DarkSurfaceVariant,
    onBackground = DarkOnSurface,
    onSurface = DarkOnSurface,
    onSurfaceVariant = DarkOnSurfaceVariant,
    secondaryContainer = DarkTealContainer,
)

@Composable
fun cloudPlusTheme(
    darkTheme: Boolean = true, // Default to dark theme to match Unlim Cloud UI
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = cloudPlusTypography,
        content = content,
    )
}
