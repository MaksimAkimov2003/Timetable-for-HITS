package com.example.faculties_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.faculties_screen.presentation.FacultiesViewModel
import com.example.faculties_screen.presentation.FacultiesViewModelState
import com.example.screens.Screen
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import org.koin.androidx.compose.koinViewModel

@Composable
fun FacultiesScreen(
	viewModel: FacultiesViewModel = koinViewModel(),
	navController: NavController
) {
	val vmState = viewModel.state.observeAsState(initial = FacultiesViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getFaculties()
	})

	when (val currentState = vmState.value) {
		is FacultiesViewModelState.Loading -> Loading()
		is FacultiesViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { itemValue ->
				val facultyID = searchFacultyIDByName(
					name = itemValue,
					currentState = currentState
				)
				navController
					.navigate(Screen.DirectionsScreen.withArgs(facultyID))
			},
			content = ChoosingScreenModel(
				title = ScreenTypes.FacultiesScreen.title,
				hint = ScreenTypes.FacultiesScreen.hint
			),
			itemsList = currentState.faculties.values.toList(),
		)
	}

}

private fun searchFacultyIDByName(
	name: String,
	currentState: FacultiesViewModelState.Content
): String {
	var answer = ""

	for ((key, value) in currentState.faculties) {
		if (value == name) {
			answer = key
		}
	}
	return answer
}