package com.example.shared.choosing_screens.ui

import kotlinx.coroutines.delay

suspend fun changeDisplayedList(
	currentList: List<String>,
	textFieldValue: String
): List<String> {
	if (textFieldValue == "") {
		return currentList
	}

	delay(100)

	val newList = mutableListOf<String>()

	for (itemValue in currentList) {
		if (itemValue.contains(textFieldValue)) {
			newList.add(itemValue)
		}
	}

	return newList
}