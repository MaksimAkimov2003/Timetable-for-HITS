package com.example.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.auditories_screen.ui.AuditoriesScreen
import com.example.directions_screen.ui.DirectionsScreen
import com.example.faculties_screen.ui.FacultiesScreen
import com.example.feature.teachers_screen.ui.TeachersScreen
import com.example.groups_screen.ui.GroupsScreen
import com.example.mainmenu.ui.MainMenuScreen
import com.example.start_screen.StartScreen
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.usecase.GetUserDataUseCase
import org.koin.java.KoinJavaComponent.inject

private fun getStartDestination(): String {
	val getUserDataUseCase by inject<GetUserDataUseCase>(GetUserDataUseCase::class.java)

	if (getUserDataUseCase.invoke().data == TimetableType.Unauthorized) {
		return com.example.screens.Screen.StartScreen.route
	}

	return com.example.screens.Screen.MainMenuScreen.route
}

@Composable
fun Navigation() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = getStartDestination()) {
		composable(route = com.example.screens.Screen.StartScreen.route) {
			StartScreen(navController = navController)
		}

		composable(route = com.example.screens.Screen.FacultiesScreen.route) {
			FacultiesScreen(navController = navController)
		}

		composable(route = com.example.screens.Screen.TeachersScreen.route) {
			TeachersScreen(navController = navController)
		}

		composable(route = com.example.screens.Screen.MainMenuScreen.route) {
			MainMenuScreen(navController = navController)
		}

		composable(route = com.example.screens.Screen.AuditoriesScreen.route) {
			AuditoriesScreen(navController = navController)
		}

		composable(
			route = com.example.screens.Screen.DirectionsScreen.route + "/{facultyId}",
			arguments = listOf(
				navArgument(name = "facultyId") {
					type = NavType.StringType
					defaultValue = ""
					nullable = false
				}
			)
		) { entry ->
			entry.arguments?.getString("facultyId")?.let {
				DirectionsScreen(
					facultyId = it,
					navController = navController
				)
			}
		}

		composable(
			route = com.example.screens.Screen.GroupsScreen.route + "/{disciplineId}",
			arguments = listOf(
				navArgument(name = "disciplineId") {
					type = NavType.StringType
					defaultValue = ""
					nullable = false
				}
			)
		) { entry ->
			entry.arguments?.getString("disciplineId")?.let {
				GroupsScreen(
					disciplineId = it,
					navController = navController
				)
			}
		}
	}
}