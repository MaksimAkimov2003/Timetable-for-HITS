package com.example.weektimetable.ui

import android.content.Context
import android.text.format.DateFormat
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.resources.theme.background
import com.example.resources.theme.textHint
import com.example.resources.theme.transparent
import com.example.screens.Screen
import com.example.userstorage.domain.entity.TimetableType
import com.example.weektimetable.domain.entity.PairEntity
import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.presentation.WeekTimetableState
import com.example.weektimetable.presentation.WeekTimetableViewModel
import com.example.weektimetable.ui.scrolltable.ScrollTable
import com.example.weektimetable.ui.scrolltable.TimetableAdapter
import org.koin.androidx.compose.koinViewModel

@Composable
fun WeekTimetableScreen(
	viewModel: WeekTimetableViewModel = koinViewModel(),
	context: Context = LocalContext.current,
	timetableType: TimetableType,
	navController: NavController
) {
	viewModel.loadTimetable(timetableType, false, viewModel.getCurrentDay())
	Box(
		modifier = Modifier
			.background(MaterialTheme.colors.secondaryVariant)
			.fillMaxSize()
	) {
		Image(
			modifier = Modifier
				.fillMaxSize(),
			painter = painterResource(id = com.example.core.resources.R.drawable.week_timetable_background),
			contentDescription = ""
		)
		Box(modifier = Modifier.padding(16.dp, 16.dp, 16.dp, 32.dp)) {
			DrawContent(viewModel, context, timetableType, navController)
		}
	}
}

@Composable
private fun DrawContent(viewModel: WeekTimetableViewModel, context: Context, timetableType: TimetableType, navController: NavController) {
	when (viewModel.state.collectAsState().value) {
		is WeekTimetableState.Initial -> {
			throw java.lang.IllegalStateException("ViewModel state should be init")
		}

		is WeekTimetableState.Loading -> {
			RenderLoadingState(viewModel.state.collectAsState().value as WeekTimetableState.Loading, viewModel, timetableType, navController)
		}

		is WeekTimetableState.Error   -> {
			RenderErrorState(viewModel.state.collectAsState().value as WeekTimetableState.Error, viewModel, timetableType, navController)
		}

		is WeekTimetableState.Content -> {
			RenderContentState(viewModel.state.collectAsState().value as WeekTimetableState.Content, viewModel, context, timetableType, navController)
		}
		else -> {}
	}
}

@Composable
private fun RenderLoadingState(state: WeekTimetableState.Loading, viewModel: WeekTimetableViewModel, timetableType: TimetableType, navController: NavController) {
	Column {
		DrawTimetableHeader(
			getTitle(state.timetableType),
			state.currentDate,
			state.currentWeek, viewModel, timetableType, state.isListTimetable, state.currentDay
		)
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f),
			contentAlignment = Alignment.Center
		) {
			DrawLoadingSpinner()
		}
			DrawBottomBar(viewModel, timetableType, navController)
	}
}

@Composable
private fun RenderErrorState(state: WeekTimetableState.Error, viewModel: WeekTimetableViewModel, timetableType: TimetableType, navController: NavController) {
	Column {
		DrawTimetableHeader(
			getTitle(state.timetableType),
			state.currentDate,
			state.currentWeek, viewModel, timetableType, state.isListTimetable, state.currentDay
		)
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f),
			contentAlignment = Alignment.Center
		) {
			Column {
				Text(text = "Не удалось загрузить данные.", textAlign = TextAlign.Center, style = MaterialTheme.typography.body1)
				Button(onClick = { viewModel.loadTimetable(timetableType, state.isListTimetable, state.currentDay) }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
					Text(text = "Попробовать снова.", style = MaterialTheme.typography.body2)
				}
			}
		}
		DrawBottomBar(viewModel, timetableType, navController)
	}
}

@Composable
private fun RenderContentState(state: WeekTimetableState.Content, viewModel: WeekTimetableViewModel, context: Context, timetableType: TimetableType, navController: NavController) {
	Column {
		DrawTimetableHeader(
			title = getTitle(state.timetableType),
			date = state.currentDate,
			currentWeek = state.currentWeek, viewModel, timetableType, state.isListTimetable,state.currentDay
		)
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
				.background(transparent, MaterialTheme.shapes.large),
			contentAlignment = Alignment.TopCenter
		) {
			if(!state.isListTimetable) {
				ScrollTable(TimetableAdapter(state.timetable.days, context))
			}
			else {
				DrawListTimetable(state)
			}
		}
		DrawBottomBar(viewModel, timetableType, navController)
	}
}

private data class TimeSlot(val start: String, val end: String)
@Composable
private fun DrawListTimetable(state: WeekTimetableState.Content) {
	val dayMillis = 1000 * 60 * 60 * 24
	println(((state.currentDay - state.currentWeek.startDate)/dayMillis).toInt())
	val currentDay = state.timetable.days[((state.currentDay - state.currentWeek.startDate)/dayMillis).toInt()].timeSlots
	val timeslots = listOf(
		TimeSlot("8:45", "10:20"),
		TimeSlot("10:35", "12:10"),
		TimeSlot("12:25", "14:00"),
		TimeSlot("14:45", "16:20"),
		TimeSlot("16:35", "18:10"),
		TimeSlot("18:25", "20:00"),
		TimeSlot("20:15", "21:50")
	)
	Column(modifier = Modifier
		.padding(horizontal = 8.dp)
		.verticalScroll(rememberScrollState())
		.fillMaxWidth()) {
		var isLastPair = false
		var lastPairIndex = 0
		Box(modifier = Modifier.height(16.dp))
		timeslots.forEachIndexed { i, it ->
			currentDay.forEach { slot ->
				if(i + 1 == slot.slotNumber) {
					if(isLastPair && slot.pairs != null && lastPairIndex != i - 1) {
						Box(modifier = Modifier
							.fillMaxWidth()
							.padding(8.dp)
							.background(
								MaterialTheme.colors.background,
								MaterialTheme.shapes.medium
							)) {
							Text("${timeslots[lastPairIndex + 1].start} - ${timeslots[i-1].end}", textAlign = TextAlign.Center, modifier = Modifier.padding(4.dp).fillMaxWidth())
						}
					}
					slot.pairs?.forEach { pair ->
						DrawPairCard(pair = pair, it, i + 1)
						isLastPair = true
						lastPairIndex = i
					}
				}
			}
		}
		Box(modifier = Modifier.height(16.dp))
	}
}

@Composable
private fun DrawPairCard(pair: PairEntity, timeslot: TimeSlot, timeslotNumber: Int) {
	val color = when(pair.lessonType) {
		"Семинар" -> { Color(0xFFD26191) }
		"Контрольная точка" -> { Color(0xFFEA3B3B) }
		"Лекция" -> { Color(0xFFFAE955) }
		"Практика" -> { Color(0xFF6EC96C) }
		else -> { Color(0xFF4AEDD0) }
	}
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(vertical = 2.dp)
			.background(
				MaterialTheme.colors.background,
				MaterialTheme.shapes.medium
			)
			.offset(0.dp, (-8).dp)
	) {
		Box(modifier = Modifier
			.align(Alignment.End)
			.offset(8.dp, 0.dp)) {
			Box(modifier = Modifier
				.clip(MaterialTheme.shapes.small)
				.background(color, RoundedCornerShape(100))
			) {
				Text(text = pair.lessonType, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), textAlign = TextAlign.Center)
			}
		}
		Text(text = pair.discipline, style = MaterialTheme.typography.subtitle1, modifier = Modifier.padding(horizontal = 8.dp))
		Row(modifier = Modifier.padding(vertical = 2.dp)) {
			Icon(painter = painterResource(id = com.example.core.resources.R.drawable.professor_icon), contentDescription = "", modifier = Modifier.padding(horizontal = 8.dp), tint = textHint)
			Text(text = pair.professor, style = MaterialTheme.typography.caption)
		}
		Row(modifier = Modifier.padding(vertical = 2.dp)) {
			Icon(painter = painterResource(id = com.example.core.resources.R.drawable.auditory_icon), contentDescription = "", modifier = Modifier.padding(horizontal = 8.dp), tint = textHint)
			Text(text = pair.auditory, style = MaterialTheme.typography.caption)
		}
		Row(modifier = Modifier.padding(vertical = 2.dp)) {
			Icon(painter = painterResource(id = com.example.core.resources.R.drawable.group_icon), contentDescription = "", modifier = Modifier.padding(horizontal = 8.dp), tint = textHint)
			Text(text = pair.groups[0].number, style = MaterialTheme.typography.caption)
		}
		Row(modifier = Modifier.padding(vertical = 2.dp)) {
			Text(text = "${timeslot.start} - ${timeslot.end}", modifier = Modifier.padding(horizontal = 8.dp), style = MaterialTheme.typography.h6)
			Text(text = "$timeslotNumber-ая пара", style = MaterialTheme.typography.caption, modifier = Modifier
				.padding(horizontal = 8.dp)
				.fillMaxWidth(), textAlign = TextAlign.End)
		}
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

private fun getTitle(timetableType: TimetableType) = when (timetableType) {
	TimetableType.Group    -> {
		"${timetableType.prefix} ${timetableType.value}"
	}

	TimetableType.Teacher  -> {
		"${timetableType.prefix} ${timetableType.value}"
	}

	TimetableType.Auditory -> {
		"${timetableType.prefix} ${timetableType.value}"
	}

	else                   -> {
		""
	}
}

private fun formatCurrentWeek(week: WeekDateEntity) =
	"${DateFormat.format("MMMM dd", week.startDate)} - ${DateFormat.format("MMMM dd", week.endDate)}"

@Composable
private fun ColumnScope.DrawTimetableHeader(title: String, date: String, currentWeek: WeekDateEntity, viewModel: WeekTimetableViewModel, timetableType: TimetableType, isListTimetable: Boolean, currentDay: Long) {
	Box(
		modifier = Modifier
			.align(Alignment.CenterHorizontally)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)
	) {
		Column(
			modifier = Modifier
				.padding(16.dp, 4.dp)
		) {
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
	Box(
		modifier = Modifier
			.padding(top = 8.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp, 4.dp)
		) {
			IconButton(onClick = { viewModel.loadLastWeek(timetableType, isListTimetable) }) {
				Icon(
					painter = painterResource(id = com.example.core.resources.R.drawable.arrow_to_left),
					contentDescription = "",
					tint = MaterialTheme.colors.primary
				)
			}
			if(!isListTimetable) {
				Text(
					text = formatCurrentWeek(currentWeek),
					style = MaterialTheme.typography.body2,
					modifier = Modifier
						.weight(1f)
						.align(Alignment.CenterVertically),
					textAlign = TextAlign.Center
				)
			}
			else {
				val dayMillis = 1000 * 60 * 60 * 24
				for(i in 0 .. (currentWeek.endDate - currentWeek.startDate)/dayMillis) {
					val interactionSource = remember { MutableInteractionSource() }
					Column(modifier = Modifier.weight(1f).clickable(interactionSource = interactionSource, indication = null) { viewModel.setCurrentDay(currentWeek.startDate + i * dayMillis) }) {
						Text(text = DateFormat.format("EE", currentWeek.startDate + i * dayMillis).toString(), style = MaterialTheme.typography.caption, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
						Box(modifier = Modifier.background(if(currentDay == currentWeek.startDate + i * dayMillis)MaterialTheme.colors.primary else MaterialTheme.colors.background, RoundedCornerShape(100)).aspectRatio(1f), contentAlignment = Alignment.Center) {
							Text(text = DateFormat.format("dd", currentWeek.startDate + i * dayMillis).toString(), style = MaterialTheme.typography.body1, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
						}
					}
				}
			}
			IconButton(onClick = { viewModel.loadNextWeek(timetableType, isListTimetable) }) {
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
private fun DrawBottomBar(viewModel: WeekTimetableViewModel, timetableType: TimetableType, navController: NavController) {
	Box(
		modifier = Modifier
			.padding(bottom = 8.dp)
			.clip(MaterialTheme.shapes.medium)
			.background(MaterialTheme.colors.background)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp, 4.dp)
		) {
			Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
				IconButton(onClick = { viewModel.changeTimetable() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.week_timetable_icon),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
			}
			Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
				IconButton(onClick = { viewModel.setToday() }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.today_icon),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
			}
			Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
				IconButton(onClick = { navController.navigate(Screen.MainMenuScreen.route) }) {
					Icon(
						painter = painterResource(id = com.example.core.resources.R.drawable.to_menu_icon),
						contentDescription = "",
						tint = MaterialTheme.colors.primary
					)
				}
			}
		}
	}
}



