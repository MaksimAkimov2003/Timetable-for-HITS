package com.example.auditories_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.auditories_screen.presentation.AuditoriesViewModel
import com.example.auditories_screen.presentation.AuditoriesViewModelState
import com.example.screens.Screen
import com.example.screens.navigateWithParams
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import com.example.userstorage.domain.entity.TimetableType
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuditoriesScreen(
	viewModel: AuditoriesViewModel = koinViewModel(),
	navController: NavController
) {
	val vmState = viewModel.state.observeAsState(initial = AuditoriesViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getAuditories()
	})

	when (val currentState = vmState.value) {
		is AuditoriesViewModelState.Loading -> Loading()
		is AuditoriesViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { itemValue ->
				val data = TimetableType.Auditory
				data.value = "1"

				//TODO("Заменить дефолтное data.value = 1 на data.value = itemValue")

				navController.navigateWithParams(
					route = Screen.WeekTimetableScreen.route,
					params = bundleOf("KEY" to data)
				)
			},
			content = ChoosingScreenModel(
				title = ScreenTypes.AuditoriesScreen.title,
				hint = ScreenTypes.AuditoriesScreen.hint
			),
			itemsList = currentState.auditories
		)
	}

}