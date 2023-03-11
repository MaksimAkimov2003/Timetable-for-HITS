package com.example.resources.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
	body1 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 16.sp
	),
	subtitle1 = TextStyle(
		fontWeight = FontWeight.SemiBold,
		fontSize = 17.sp,
		fontFamily = FontFamily.Default,
		color = onSurface
	),
	subtitle2 = TextStyle(
		fontWeight = FontWeight.Medium,
		fontSize = 15.sp,
		color = onPrimary,
		fontFamily = FontFamily.Default
	),
	body2 = TextStyle(
		fontWeight = FontWeight.Medium,
		fontSize = 15.sp,
		color = onSurface,
		fontFamily = FontFamily.Default,
	),
	h5 = TextStyle(
		fontFamily = FontFamily.Default,
		fontWeight = FontWeight.Normal,
		fontSize = 24.sp,
		color = onSecondary
	)

)