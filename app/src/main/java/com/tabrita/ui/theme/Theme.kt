package com.tabrita.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = TaBritaColors.Accent,
    onPrimary = TaBritaColors.OnSurface,
    secondary = TaBritaColors.AccentLight,
    background = TaBritaColors.Background,
    surface = TaBritaColors.Surface,
    surfaceVariant = TaBritaColors.SurfaceVariant,
    onSurface = TaBritaColors.OnSurface,
    onSurfaceVariant = TaBritaColors.OnSurfaceVariant,
    outline = TaBritaColors.Outline,
    error = TaBritaColors.Error,
    onError = TaBritaColors.OnSurface
)

private val LightColorScheme = lightColorScheme(
    primary = TaBritaColors.Accent,
    onPrimary = TaBritaColors.LightOnSurface,
    secondary = TaBritaColors.AccentLight,
    background = TaBritaColors.LightBackground,
    surface = TaBritaColors.LightSurface,
    surfaceVariant = TaBritaColors.LightSurfaceVariant,
    onSurface = TaBritaColors.LightOnSurface,
    onSurfaceVariant = TaBritaColors.LightOnSurfaceVariant,
    outline = TaBritaColors.LightOutline,
    error = TaBritaColors.Error,
    onError = TaBritaColors.LightOnSurface
)

/**
 * TaBrita Theme - Modern, aesthetic, eye-catching news experience.
 * Dark mode is the hero experience. Light mode fully supported.
 */
@Composable
fun TaBritaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // We use strong branded colors for consistency
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
        typography = TaBritaTypography,
        content = content
    )
}
