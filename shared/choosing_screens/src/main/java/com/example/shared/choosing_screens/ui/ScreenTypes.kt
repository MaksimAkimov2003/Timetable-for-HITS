package com.example.shared.choosing_screens.ui

sealed class ScreenTypes {
	object TeachersScreen : ScreenTypes() {

		const val title: String = "What's your name?"
		const val hint: String = "Name"
	}

	object FacultiesScreen : ScreenTypes() {

		const val title: String = "What's your faculty?"
		const val hint: String = "Faculty"
	}

	object DirectionsScreen : ScreenTypes() {

		const val title: String = "What's your Direction?"
		const val hint: String = "Direction"
	}

	object GroupsScreen : ScreenTypes() {

		const val title: String = "Fill in your group number"
		const val hint: String = "Group number"
	}

	object AuditoriesScreen : ScreenTypes() {

		const val title: String = "Fill in auditory number"
		const val hint: String = "Auditory number"
	}

	object StartScreen : ScreenTypes() {

		const val title: String = "First, who will use the application?"
		const val subtitle: String = "You can't change your choice later)"
	}
}
