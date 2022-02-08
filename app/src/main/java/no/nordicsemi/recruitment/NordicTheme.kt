package no.nordicsemi.recruitment

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource

@Composable
fun NordicTheme(content: @Composable () -> Unit) {
    val darkColorPalette = darkColors(
        primary = colorResource(id = R.color.md_theme_primary),
        onPrimary = colorResource(id = R.color.md_theme_onPrimary),
        secondary = colorResource(id = R.color.md_theme_secondary),
        onSecondary = colorResource(id = R.color.md_theme_onSecondary),
        background = colorResource(id = R.color.md_theme_background),
        onBackground = colorResource(id = R.color.md_theme_onBackground),
        surface = colorResource(id = R.color.md_theme_surface),
        onSurface = colorResource(id = R.color.md_theme_onSurface),
        error = colorResource(id = R.color.md_theme_error),
        onError = colorResource(id = R.color.md_theme_onError),
    )

    val lightColorPalette = lightColors(
        primary = colorResource(id = R.color.md_theme_primary),
        onPrimary = colorResource(id = R.color.md_theme_onPrimary),
        secondary = colorResource(id = R.color.md_theme_secondary),
        onSecondary = colorResource(id = R.color.md_theme_onSecondary),
        background = colorResource(id = R.color.md_theme_background),
        onBackground = colorResource(id = R.color.md_theme_onBackground),
        surface = colorResource(id = R.color.md_theme_surface),
        onSurface = colorResource(id = R.color.md_theme_onSurface),
        error = colorResource(id = R.color.md_theme_error),
        onError = colorResource(id = R.color.md_theme_onError),
    )

    val colorScheme = if (isSystemInDarkTheme()) {
        darkColorPalette
    } else {
        lightColorPalette
    }

    MaterialTheme(
        colors = colorScheme,
        content = content
    )
}
