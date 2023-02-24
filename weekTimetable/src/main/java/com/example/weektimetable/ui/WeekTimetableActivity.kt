package com.example.weektimetable.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weektimetable.presentation.WeekTimetableViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeekTimetableActivity: ComponentActivity() {

	private val viewModel: WeekTimetableViewModel by viewModel()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {

		}
	}
}

