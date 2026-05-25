package com.example.ui.theme

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

private val DarkColorScheme =
  darkColorScheme(
    primary = BrandPrimary,
    secondary = BrandSecondary,
    tertiary = BrandTertiary,
    background = BrandOnSurface,
    surface = Color(0xFF2E3132),
    onPrimary = BrandOnPrimary,
    onSecondary = BrandOnSecondary,
    onBackground = BrandBackground,
    onSurface = BrandBackground
  )

private val LightColorScheme =
  lightColorScheme(
    primary = BrandPrimary,
    secondary = BrandSecondary,
    tertiary = BrandTertiary,
    background = BrandBackground,
    surface = BrandSurface,
    onPrimary = BrandOnPrimary,
    onSecondary = BrandOnSecondary,
    onBackground = BrandOnSurface,
    onSurface = BrandOnSurface,
    surfaceVariant = BrandSurfaceContainerValue,
    onSurfaceVariant = BrandOnSurfaceVariant,
    outline = BrandOutline,
    outlineVariant = BrandOutlineVariant
  )

@Composable
fun MyApplicationTheme(
  darkTheme: Boolean = isSystemInDarkTheme(),
  // Disable dynamic color by default for strict branding
  dynamicColor: Boolean = false,
  content: @Composable () -> Unit,
) {
  val colorScheme =
    when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }

      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }

  MaterialTheme(colorScheme = colorScheme, typography = Typography, content = content)
}
