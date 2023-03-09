package com.example.directions_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.directions_screen.presentation.DirectionsViewModel
import com.example.directions_screen.presentation.DirectionsViewModelState
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import org.koin.androidx.compose.koinViewModel

@Composable
fun DirectionsScreen(
	viewModel: DirectionsViewModel = koinViewModel(),
	facultyId: String
) {
	val vmState = viewModel.state.observeAsState(initial = DirectionsViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getDirections(facultyId)
	})

	when (val currentState = vmState.value) {
		is DirectionsViewModelState.Loading -> Loading()
		is DirectionsViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { /*TODO*/ },
			content = ChoosingScreenModel(
				title = ScreenTypes.DirectionsScreen.title,
				hint = ScreenTypes.DirectionsScreen.hint
			),
			itemsList = currentState.directions.values.toList()
		)
	}

}