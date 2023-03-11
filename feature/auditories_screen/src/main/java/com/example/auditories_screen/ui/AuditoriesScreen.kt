package com.example.auditories_screen.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import com.example.auditories_screen.presentation.AuditoriesViewModel
import com.example.auditories_screen.presentation.AuditoriesViewModelState
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import com.example.shared.choosing_screens.ui.ChoosingScreenContent
import com.example.shared.choosing_screens.ui.Loading
import com.example.shared.choosing_screens.ui.ScreenTypes
import org.koin.androidx.compose.koinViewModel

@Composable
fun AuditoriesScreen(viewModel: AuditoriesViewModel = koinViewModel()) {
	val vmState = viewModel.state.observeAsState(initial = AuditoriesViewModelState.Loading)

	LaunchedEffect(key1 = vmState, block = {
		viewModel.getAuditories()
	})

	when (val currentState = vmState.value) {
		is AuditoriesViewModelState.Loading -> Loading()
		is AuditoriesViewModelState.Content -> ChoosingScreenContent(
			onItemClick = { /*TODO*/ },
			content = ChoosingScreenModel(
				title = ScreenTypes.AuditoriesScreen.title,
				hint = ScreenTypes.AuditoriesScreen.hint
			),
			itemsList = currentState.auditories
		)
	}

}