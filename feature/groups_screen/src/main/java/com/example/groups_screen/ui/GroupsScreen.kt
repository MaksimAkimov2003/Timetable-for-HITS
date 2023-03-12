package com.example.groups_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.groups_screen.presentation.GroupsViewModel
import com.example.groups_screen.presentation.GroupsViewModelState
import com.example.screens.Screen
import com.example.screens.navigateWithParams
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import com.example.userstorage.domain.entity.TimetableType
import org.koin.androidx.compose.koinViewModel

@Composable
fun GroupsScreen(
	viewModel: GroupsViewModel = koinViewModel(),
	disciplineId: String,
	navController: NavController
) {
	val vmState = viewModel.state.observeAsState(initial = GroupsViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getGroups(disciplineId)
	})

	when (val currentState = vmState.value) {
		is GroupsViewModelState.Loading -> Loading()
		is GroupsViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { itemValue ->
				viewModel.saveData(groupNumber = itemValue)

				val data = TimetableType.Group
				data.value = "1"

				//TODO("Заменить дефолтное data.value = 1 на data.value = itemValue")

				navController.navigateWithParams(
					route = Screen.WeekTimetableScreen.route,
					params = bundleOf("KEY" to data)
				)
			},
			content = ChoosingScreenModel(
				title = ScreenTypes.GroupsScreen.title,
				hint = ScreenTypes.GroupsScreen.hint
			),
			itemsList = currentState.groups
		)
	}

}