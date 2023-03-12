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
import com.example.screens.Screen.*
import com.example.start_screen.StartScreen
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.usecase.GetUserDataUseCase
import com.example.weektimetable.ui.WeekTimetableScreen
import org.koin.java.KoinJavaComponent.inject

private fun getStartDestination(): String {
	val getUserDataUseCase by inject<GetUserDataUseCase>(GetUserDataUseCase::class.java)

	if (getUserDataUseCase.invoke().data == TimetableType.Unauthorized) {
		return StartScreen.route
	}

	return WeekTimetableScreen.route
}

@Composable
fun Navigation() {
	val navController = rememberNavController()
	NavHost(navController = navController, startDestination = getStartDestination()) {
		composable(route = StartScreen.route) {
			StartScreen(navController = navController)
		}

		composable(route = FacultiesScreen.route) {
			FacultiesScreen(navController = navController)
		}

		composable(route = TeachersScreen.route) {
			TeachersScreen(navController = navController)
		}

		composable(route = MainMenuScreen.route) {
			MainMenuScreen(navController = navController)
		}

		composable(route = AuditoriesScreen.route) {
			AuditoriesScreen(navController = navController)
		}

		composable(
			route = DirectionsScreen.route + "/{facultyId}",
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
			route = GroupsScreen.route + "/{disciplineId}",
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

		composable(route = WeekTimetableScreen.route) {
			if (navController.previousBackStackEntry?.arguments == null) {
				val getUserDataUseCase by inject<GetUserDataUseCase>(GetUserDataUseCase::class.java)
				val savedType = getUserDataUseCase.invoke().data

				val data = TimetableType.Group
				data.value = "1"

				//TODO("Заменить дефолтное data.value = 1 на data.value = savedType.value")

				WeekTimetableScreen(timetableType = data, navController = navController)
			} else {
				navController.previousBackStackEntry?.arguments?.getParcelable<TimetableType>("KEY")?.let {
					WeekTimetableScreen(timetableType = it, navController = navController)
				}
			}
		}
	}
}