package com.example.resources.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

internal val DarkColorPalette = darkColors(
	primary = primary,
	primaryVariant = primaryVariant,
	secondary = secondary,
	secondaryVariant = secondaryVariant,
	background = background,
	surface = surface,
	error = error,
	onPrimary = onPrimary,
	onSecondary = onSecondary,
	onBackground = onBackground,
	onSurface =  onSurface,
	onError = onError
)

internal val LightColorPalette = lightColors(
	primary = primary,
	primaryVariant = primaryVariant,
	secondary = secondary,
	secondaryVariant = secondaryVariant,
	background = background,
	surface = surface,
	error = error,
	onPrimary = onPrimary,
	onSecondary = onSecondary,
	onBackground = onBackground,
	onSurface =  onSurface,
	onError = onError
)

@Composable
fun TimetableTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	val colors = if (darkTheme) {
		DarkColorPalette
	} else {
		LightColorPalette
	}

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}