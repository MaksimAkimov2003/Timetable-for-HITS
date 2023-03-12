package com.example.faculties_screen.data.model

import com.google.gson.annotations.SerializedName

data class FacultyModel(
	@SerializedName("id")
	val id: String,

	@SerializedName("Name")
	val name: String
)
