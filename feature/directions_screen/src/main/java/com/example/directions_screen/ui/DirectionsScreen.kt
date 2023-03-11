package com.example.directions_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import com.example.directions_screen.presentation.DirectionsViewModel
import com.example.directions_screen.presentation.DirectionsViewModelState
import com.example.screens.Screen
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import org.koin.androidx.compose.koinViewModel

@Composable
fun DirectionsScreen(
	viewModel: DirectionsViewModel = koinViewModel(),
	facultyId: String,
	navController: NavController
) {
	val vmState = viewModel.state.observeAsState(initial = DirectionsViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getDirections(facultyId)
	})

	when (val currentState = vmState.value) {
		is DirectionsViewModelState.Loading -> Loading()
		is DirectionsViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { itemValue ->
				val directionId = searchDirectionIDByName(
					name = itemValue,
					currentState = currentState
				)
				navController
					.navigate(Screen.GroupsScreen.withArgs(directionId))
			},
			content = ChoosingScreenModel(
				title = ScreenTypes.DirectionsScreen.title,
				hint = ScreenTypes.DirectionsScreen.hint
			),
			itemsList = currentState.directions.values.toList()
		)
	}

}

private fun searchDirectionIDByName(
	name: String,
	currentState: DirectionsViewModelState.Content
): String {
	var answer = ""

	for ((key, value) in currentState.directions) {
		if (value == name) {
			answer = key
		}
	}
	return answer
}