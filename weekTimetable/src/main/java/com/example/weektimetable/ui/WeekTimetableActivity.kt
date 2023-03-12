package com.example.weektimetable.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.resources.theme.TimetableTheme
import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.presentation.WeekTimetableState
import com.example.weektimetable.presentation.WeekTimetableViewModel
import com.example.weektimetable.ui.scrolltable.ScrollTable
import com.example.weektimetable.ui.scrolltable.TimetableAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.text.format.DateFormat
import androidx.compose.foundation.Image
import com.example.userstorage.domain.entity.TimetableType

class WeekTimetableActivity: ComponentActivity() {

	private val viewModel: WeekTimetableViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		viewModel.loadTimetable()
		setContent {
			TimetableTheme {
				Box(modifier = Modifier
					.background(MaterialTheme.colors.secondaryVariant)
					.fillMaxSize()) {
					Image(modifier = Modifier
						.fillMaxSize(),
						painter = painterResource(id = com.example.core.resources.R.drawable.week_timetable_background),
						contentDescription = "")
					Box(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 32.dp)) {
						DrawContent()
					}
				}
			}
		}
	}

	@Composable
	private fun DrawContent() {
		when(viewModel.state.collectAsState().value) {
			is WeekTimetableState.Initial	-> { throw java.lang.IllegalStateException("ViewModel state should be init") }
			is WeekTimetableState.Loading 	-> { RenderLoadingState(viewModel.state.collectAsState().value as WeekTimetableState.Loading) }
			is WeekTimetableState.Error		-> { RenderErrorState(viewModel.state.collectAsState().value as WeekTimetableState.Error) }
			is WeekTimetableState.Content	-> { RenderContentState(viewModel.state.collectAsState().value as WeekTimetableState.Content) }
		}
	}

	@Composable
	private fun RenderLoadingState(state: WeekTimetableState.Loading) {
		Column {
			DrawHeader(
				getTitle(state.timetableType),
				state.currentDate,
				formatCurrentWeek(state.currentWeek))
			Box(modifier = Modifier
				.fillMaxWidth()
				.weight(1f),
				contentAlignment = Alignment.Center) {
				DrawLoadingSpinner()
			}
//			DrawBottomBar()
		}
	}

	@Composable
	private fun RenderErrorState(state: WeekTimetableState.Error) {
		Column {
			DrawHeader(
				getTitle(state.timetableType),
				state.currentDate,
				formatCurrentWeek(state.currentWeek))
			Box(modifier = Modifier
				.fillMaxWidth()
				.weight(1f),
				contentAlignment = Alignment.Center) {
				Column {
					Text(text = "Не удалось загрузить данные.", textAlign = TextAlign.Center, style = MaterialTheme.typography.body1)
					Button(onClick = { viewModel.loadTimetable() }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
						Text(text = "Попробовать снова.", style = MaterialTheme.typography.body2)
					}
				}
			}
//			DrawBottomBar()
		}
	}

	@Composable
	private fun RenderContentState(state: WeekTimetableState.Content) {
		Column {
			DrawHeader(
				title = getTitle(state.timetableType),
				date = state.currentDate,
				currentWeek = formatCurrentWeek(state.currentWeek))
			Box(modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
				.clip(MaterialTheme.shapes.large),
				contentAlignment = Alignment.Center) {
				ScrollTable(TimetableAdapter(state.timetable.days, applicationContext))
			}
//			DrawBottomBar()
		}
	}

	@Composable
	private fun DrawLoadingSpinner() {
		val strokeWidth = 5.dp
		val primary = MaterialTheme.colors.primary
		val onPrimary = MaterialTheme.colors.onPrimary
		CircularProgressIndicator(
			modifier = Modifier.drawBehind {
				drawCircle(
					primary,
					radius = size.width / 2 - strokeWidth.toPx() / 2,
					style = Stroke(strokeWidth.toPx())
				)
			},
			color = onPrimary,
			strokeWidth = strokeWidth
		)
	}

	private fun getTitle(timetableType: TimetableType) = when(timetableType) {
			TimetableType.Group		-> { "${timetableType.prefix} ${timetableType.value}" }
			TimetableType.Teacher	-> { "${timetableType.prefix} ${timetableType.value}" }
			TimetableType.Auditory	-> { "${timetableType.prefix} ${timetableType.value}" }
		}

	private fun formatCurrentWeek(week: WeekDateEntity) =
		"${DateFormat.format("MMMM dd", week.startDate)} - ${DateFormat.format("MMMM dd", week.endDate)}"

	@Composable
	private fun ColumnScope.DrawHeader(title: String, date: String, currentWeek: String) {
		Box(modifier = Modifier
			.align(Alignment.CenterHorizontally)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)) {
			Column(modifier = Modifier
				.padding(16.dp, 4.dp)) {
				Text(
					text = title,
					style = MaterialTheme.typography.body1,
					modifier = Modifier.align(Alignment.CenterHorizontally),
					textAlign = TextAlign.Center
				)
				Text(
					text = date,
					style = MaterialTheme.typography.body2,
					modifier = Modifier.align(Alignment.CenterHorizontally)
				)
			}
		}
		Box(modifier = Modifier
			.padding(vertical = 8.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)) {
			Row(modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp, 4.dp)) {
				IconButton(onClick = { viewModel.loadLastWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_left),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
				Text(text = currentWeek,
					style = MaterialTheme.typography.body2,
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically),
					textAlign = TextAlign.Center)
				IconButton(onClick = { viewModel.loadNextWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_right),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
			}
		}
	}

	@Composable
	private fun DrawBottomBar() {
		Box(modifier = Modifier
			.padding(vertical = 8.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)) {
			Row(modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp, 4.dp)) {
				IconButton(onClick = { viewModel.loadLastWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_left),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
				IconButton(onClick = { viewModel.loadNextWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_right),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
				IconButton(onClick = { viewModel.loadNextWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_right),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
				IconButton(onClick = { viewModel.loadNextWeek() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_right),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
			}
		}
	}
}



