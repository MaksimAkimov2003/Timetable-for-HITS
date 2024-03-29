package com.example.weektimetable.presentation

import android.text.format.DateFormat
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userstorage.domain.entity.TimetableType
import com.example.userstorage.domain.usecase.GetUserDataUseCase
import com.example.weektimetable.domain.entity.WeekDateEntity
import com.example.weektimetable.domain.entity.WeekEntity
import com.example.weektimetable.domain.usecase.GetWeekTimetableByAuditoryUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByGroupUseCase
import com.example.weektimetable.domain.usecase.GetWeekTimetableByTeacherUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.*

class WeekTimetableViewModel(
	private val getUserDataUseCase: GetUserDataUseCase,
	private val getWeekTimetableByGroupUseCase: GetWeekTimetableByGroupUseCase,
	private val getWeekTimetableByAuditoryUseCase: GetWeekTimetableByAuditoryUseCase,
	private val getWeekTimetableByTeacherUseCase: GetWeekTimetableByTeacherUseCase
): ViewModel() {

	private val _state = MutableStateFlow<WeekTimetableState>(WeekTimetableState.Initial)
	val state: StateFlow<WeekTimetableState> = _state
	private val requestStack = Stack<Job>()

	private fun getCurrentWeek(): WeekDateEntity {
		val milliseconds = 1
		val second = 1000 * milliseconds
		val minute = 60 * second
		val hour = 60 * minute
		val day = 24 * hour
		val week = 7 * day
		val now = Calendar.getInstance()
		val startWeek = now.timeInMillis -
				now.get(Calendar.MILLISECOND) * milliseconds -
				now.get(Calendar.SECOND) * second -
				now.get(Calendar.MINUTE) * minute -
				now.get(Calendar.HOUR_OF_DAY) * hour -
				(now.get(Calendar.DAY_OF_WEEK) - now.firstDayOfWeek)  * day
		val endWeek = startWeek + week - day
		return WeekDateEntity(startWeek, endWeek)
	}

	fun getCurrentDay(): Long {
		val milliseconds = 1
		val second = 1000 * milliseconds
		val minute = 60 * second
		val hour = 60 * minute
		val day = 24 * hour
		val week = 7 * day
		val now = Calendar.getInstance()
		return now.timeInMillis -
				now.get(Calendar.MILLISECOND) * milliseconds -
				now.get(Calendar.SECOND) * second -
				now.get(Calendar.MINUTE) * minute -
				now.get(Calendar.HOUR_OF_DAY) * hour
	}

	private fun getLastWeek(currentWeek: WeekDateEntity): WeekDateEntity {
		val week = 1000 * 60 * 60 * 24 * 7
		return WeekDateEntity(
			startDate = currentWeek.startDate - week,
			endDate = currentWeek.endDate - week
		)
	}

	private fun getNextWeek(currentWeek: WeekDateEntity): WeekDateEntity {
		val week = 1000 * 60 * 60 * 24 * 7
		return WeekDateEntity(
			startDate = currentWeek.startDate + week,
			endDate = currentWeek.endDate + week
		)
	}

	private fun getWeekNumberFromFirstSeptember(week: WeekDateEntity): Int {
		val weekMillis = 1000 * 60 * 60 * 24 * 7f
		val now = Calendar.getInstance()
		now.timeInMillis = week.endDate
		val firstSeptember = Calendar.getInstance()
		firstSeptember.set(firstSeptember.get(Calendar.YEAR), Calendar.SEPTEMBER, 1)
		if(firstSeptember.after(now)) {
			firstSeptember.set(firstSeptember.get(Calendar.YEAR) - 1, Calendar.SEPTEMBER, 1)
		}
		return kotlin.math.ceil((now.timeInMillis - firstSeptember.timeInMillis) / weekMillis).toInt()
	}

	private fun getCurrentDateByWeek(week: WeekDateEntity): String {
		return DateFormat.format("MMMM yyyy · ${getWeekNumberFromFirstSeptember(week)} неделя", week.startDate).toString()
	}

	private fun getCurrentWeekFromState() = when(_state.value) {
		is WeekTimetableState.Loading 	-> { (_state.value as WeekTimetableState.Loading).currentWeek }
		is WeekTimetableState.Error		-> { (_state.value as WeekTimetableState.Error).currentWeek }
		is WeekTimetableState.Content	-> { (_state.value as WeekTimetableState.Content).currentWeek }
		else							-> { throw java.lang.IllegalArgumentException("ViewModel state should be init") }
	}

	private fun createLoadingState(week: WeekDateEntity, timetableType: TimetableType, currentDay: Long, isListTimetableType: Boolean): WeekTimetableState.Loading {
		return WeekTimetableState.Loading(
			timetableType = timetableType,
			currentDate = getCurrentDateByWeek(week),
			currentWeek = week,
			currentDay = currentDay,
			isListTimetable = isListTimetableType
		)
	}

	fun setToday() {
		when (_state.value) {
			is WeekTimetableState.Initial -> {
				throw java.lang.IllegalStateException("ViewModel state should be init")
			}
			is WeekTimetableState.Loading -> {
				val currentState = _state.value as WeekTimetableState.Loading
				loadTimetable(currentState.timetableType, currentState.isListTimetable, getCurrentDay())
			}
			is WeekTimetableState.Error   -> {
				val currentState = _state.value as WeekTimetableState.Error
				loadTimetable(currentState.timetableType, currentState.isListTimetable, getCurrentDay())

			}
			is WeekTimetableState.Content -> {
				val currentState = _state.value as WeekTimetableState.Content
				loadTimetable(currentState.timetableType, currentState.isListTimetable, getCurrentDay())
			}
		}

	}

	fun loadTimetable(timetableType: TimetableType, isListTimetableType: Boolean, currentDay: Long) {
		val loadingState = createLoadingState(
			week = getCurrentWeek(),
			timetableType = timetableType,
			currentDay = currentDay,
			isListTimetableType = isListTimetableType
		)
		_state.value = loadingState
		makeTimetableRequest(loadingState)
	}

	fun loadLastWeek(timetableType: TimetableType, isListTimetableType: Boolean) {
		val loadingState = createLoadingState(
			week = getLastWeek(getCurrentWeekFromState()),
			timetableType = timetableType,
			currentDay = getLastWeek(getCurrentWeekFromState()).startDate,
			isListTimetableType = isListTimetableType
		)
		_state.value = loadingState
		makeTimetableRequest(loadingState)
	}

	fun loadNextWeek(timetableType: TimetableType, isListTimetableType: Boolean) {
		val loadingState = createLoadingState(
			week = getNextWeek(getCurrentWeekFromState()),
			timetableType = timetableType,
			currentDay = getNextWeek(getCurrentWeekFromState()).startDate,
			isListTimetableType = isListTimetableType
		)
		_state.value = loadingState
		makeTimetableRequest(loadingState)
	}

	fun setCurrentDay(currentDay: Long) {
		when (_state.value) {
			is WeekTimetableState.Initial -> {
				throw java.lang.IllegalStateException("ViewModel state should be init")
			}
			is WeekTimetableState.Loading -> {
				_state.value = (_state.value as WeekTimetableState.Loading).copy(currentDay = currentDay)
			}
			is WeekTimetableState.Error   -> {
				_state.value = (_state.value as WeekTimetableState.Error).copy(currentDay = currentDay)

			}
			is WeekTimetableState.Content -> {
				_state.value = (_state.value as WeekTimetableState.Content).copy(currentDay = currentDay)
			}
		}
	}

	fun changeTimetable() {
		when (_state.value) {
			is WeekTimetableState.Initial -> {
				throw java.lang.IllegalStateException("ViewModel state should be init")
			}
			is WeekTimetableState.Loading -> {
				_state.value = (_state.value as WeekTimetableState.Loading).copy(isListTimetable = !(_state.value as WeekTimetableState.Loading).isListTimetable)
			}
			is WeekTimetableState.Error   -> {
				_state.value = (_state.value as WeekTimetableState.Error).copy(isListTimetable = !(_state.value as WeekTimetableState.Error).isListTimetable)

			}
			is WeekTimetableState.Content -> {
				_state.value = (_state.value as WeekTimetableState.Content).copy(isListTimetable = !(_state.value as WeekTimetableState.Content).isListTimetable)

			}
		}
	}

	private fun makeTimetableRequest(loadingState: WeekTimetableState.Loading) {
		requestStack.clearJobs()
		requestStack.add(launch(
			run = {
				delay(300)
				_state.value = WeekTimetableState.Content(
					timetableType = loadingState.timetableType,
					currentDate = loadingState.currentDate,
					currentWeek = loadingState.currentWeek,
					timetable = when(loadingState.timetableType) {
						TimetableType.Group 	-> { getWeekTimetableByGroupUseCase(loadingState.timetableType.value, loadingState.currentWeek) }
						TimetableType.Auditory 	-> { getWeekTimetableByAuditoryUseCase(loadingState.timetableType.value, loadingState.currentWeek) }
						TimetableType.Teacher 	-> { getWeekTimetableByTeacherUseCase(loadingState.timetableType.value, loadingState.currentWeek) }
						else					-> { WeekEntity(listOf()) }
					},
					currentDay = loadingState.currentDay,
					isListTimetable = loadingState.isListTimetable
				)
			}, catchError = {
				Log.e("error", it.message?: "")
				_state.value = WeekTimetableState.Error(
					timetableType = loadingState.timetableType,
					currentDate = loadingState.currentDate,
					currentWeek = loadingState.currentWeek,
					currentDay = loadingState.currentDay,
					isListTimetable = loadingState.isListTimetable
				)
			}))
	}
}

fun Stack<Job>.clearJobs() {
	this.forEach {
		it.cancel()
	}
	this.clear()
}


fun ViewModel.launch(run: suspend () -> Unit, catchError: (e: Throwable) -> Unit): Job =
	viewModelScope.launch(
		CoroutineExceptionHandler { _, e ->
			catchError(e)
		}
	) {
		run()
	}



