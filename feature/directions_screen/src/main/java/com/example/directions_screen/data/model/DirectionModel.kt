package com.example.directions_screen.data.model

import com.google.gson.annotations.SerializedName

data class DirectionModel(
	@SerializedName("id")
	val id: String,

	@SerializedName("Name")
	val name: String
)
