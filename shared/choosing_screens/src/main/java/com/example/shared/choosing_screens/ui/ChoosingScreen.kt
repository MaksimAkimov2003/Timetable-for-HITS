package com.example.shared.choosing_screens.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resources.theme.TimetableTheme
import com.example.resources.theme.choosingScreens
import com.example.shared.choosing_screens.data.ChoosingScreenModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ChoosingScreenContent(
	onItemClick: (
		itemValue: String,
	) -> Unit,
	content: ChoosingScreenModel,
	itemsList: List<String>,
) {
	TimetableTheme() {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(color = MaterialTheme.colors.background)
		) {
			Text(
				text = content.title,
				style = MaterialTheme.typography.subtitle1,
				modifier = Modifier
					.padding(
						top = 36.dp,
						start = 24.dp
					)
			)

			var textFieldValue by remember { mutableStateOf(content.hint) }
			var displayedList by remember { mutableStateOf(itemsList) }
			var isFirstInput by remember { mutableStateOf(true) }

			TextField(
				value = textFieldValue,
				onValueChange = { newValue ->
					textFieldValue = newValue
					isFirstInput = false
				},
				singleLine = true,
				textStyle = MaterialTheme.typography.subtitle2,
				shape = RoundedCornerShape(8.dp),
				modifier = Modifier
					.fillMaxWidth()
					.padding(
						start = 24.dp,
						end = 24.dp,
						top = 16.dp,
						bottom = 16.dp
					)
					.background(
						color = choosingScreens,
						shape = RoundedCornerShape(8.dp)
					)
					.onFocusChanged {
						if (it.isFocused) {
							textFieldValue = ""
						}
					},
				colors = TextFieldDefaults.textFieldColors(
					focusedIndicatorColor = Color.Transparent,
					disabledIndicatorColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, errorIndicatorColor = Color.Transparent
				)

			)

			ItemsList(
				itemsList = displayedList,
				onItemClick = onItemClick,
			)

			if (!isFirstInput) {
				LaunchedEffect(key1 = textFieldValue, block = {
					displayedList = changeDisplayedList(
						currentList = itemsList,
						textFieldValue = textFieldValue
					)
				})
			}

		}
	}

}

@Composable
private fun ItemsList(
	itemsList: List<String>,
	onItemClick: (
		itemValue: String,
	) -> Unit,
) {
	TimetableTheme() {
		LazyColumn(contentPadding = PaddingValues(start = 24.dp)) {
			var padding = 0.dp
			itemsIndexed(items = itemsList) { index, item ->
				if (index != 0) {
					padding = 16.dp
				}
				Text(
					text = item,
					style = MaterialTheme.typography.body2,
					modifier = Modifier
						.padding(
							top = padding,
							bottom = 16.dp
						)
						.clickable {
							onItemClick.invoke(item)
						}
				)
				Divider(color = choosingScreens)
			}

		}
	}
}

@Composable
fun ChoosingScreenEmptyContent() {

}

@Preview
@Composable
fun Loading() {
	TimetableTheme {
		var progress by remember { mutableStateOf(0.2f) }

		val animatedProgress = animateFloatAsState(
			targetValue = progress,
			animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
		).value

		LaunchedEffect(key1 = progress, block = {
			launch {
				delay(timeMillis = 500)
				if (progress < 1f) {
					progress += 0.2f
				} else {
					progress = 0.1f
				}
			}
		})

		Box(
			contentAlignment = Alignment.Center,
			modifier = Modifier
				.fillMaxSize()
				.background(color = MaterialTheme.colors.background)
		) {
			CircularProgressIndicator(
				strokeWidth = 8.dp,
				progress = animatedProgress,
				modifier = Modifier
					.size(64.dp)
			)
		}
	}
}

@Composable
fun ToolbarSteps(stepNumber: Int?) {

}