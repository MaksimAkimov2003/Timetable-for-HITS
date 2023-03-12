package com.example.feature.teachers_screen.data.model

import com.google.gson.annotations.SerializedName

data class TeacherModel(
	@SerializedName("id")
	val id: String,

	@SerializedName("Name")
	val name: String
)
