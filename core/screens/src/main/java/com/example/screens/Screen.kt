package com.example.screens

sealed class Screen(val route: String) {
	object StartScreen : Screen("startScreen")
	object WeekTimetableScreen : Screen("weekTimetableScreen")
	object MainMenuScreen : Screen("mainMenuScreen")
	object AuditoriesScreen : Screen("auditoriesScreen")
	object DirectionsScreen : Screen("directionsScreen")
	object FacultiesScreen : Screen("facultiesScreen")
	object GroupsScreen : Screen("groupsScreen")
	object TeachersScreen : Screen("teachersScreen")

	fun withArgs(vararg args: String): String {
		return buildString {
			append(route)
			args.forEach { arg ->
				append("/$arg")
			}
		}
	}
}
