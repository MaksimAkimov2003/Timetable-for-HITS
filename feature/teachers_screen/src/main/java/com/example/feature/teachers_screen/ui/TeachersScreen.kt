package com.example.feature.teachers_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.feature.teachers_screen.presentation.TeachersViewModel
import com.example.feature.teachers_screen.presentation.TeachersViewModelState
import com.example.screens.Screen
import com.example.screens.navigateWithParams
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import com.example.userstorage.domain.entity.TimetableType
import org.koin.androidx.compose.koinViewModel

@Composable
fun TeachersScreen(
	viewModel: TeachersViewModel = koinViewModel(),
	navController: NavController
) {
	val vmState = viewModel.state.observeAsState(initial = TeachersViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getTeachers()
	})

	when (val currentState = vmState.value) {
		is TeachersViewModelState.Loading -> Loading()
		is TeachersViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { itemValue ->
				val teacherId = searchTeacherIDByName(
					name = itemValue,
					currentState = currentState
				)

				viewModel.saveData(teacherId)

				val data = TimetableType.Teacher
				data.value = "1"

				navController.navigateWithParams(
					route = Screen.WeekTimetableScreen.route,
					params = bundleOf("KEY" to data)
				)

				//TODO("Заменить дефолтное data.value = 1 на data.value = teacherId")
			},
			content = ChoosingScreenModel(
				title = ScreenTypes.TeachersScreen.title,
				hint = ScreenTypes.TeachersScreen.hint
			),
			itemsList = currentState.teachers.values.toList()
		)
	}

}

private fun searchTeacherIDByName(
	name: String,
	currentState: TeachersViewModelState.Content
): String {
	var answer = ""

	for ((key, value) in currentState.teachers) {
		if (value == name) {
			answer = key
		}
	}
	return answer
}