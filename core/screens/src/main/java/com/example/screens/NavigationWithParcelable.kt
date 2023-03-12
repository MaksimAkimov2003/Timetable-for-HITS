package com.example.screens

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.navigateWithParams(
	route: String,
	params: Bundle?,
	builder: NavOptionsBuilder.() -> Unit = {}
) {
	this.currentBackStackEntry?.arguments?.putAll(params)

	navigate(route, builder)
}