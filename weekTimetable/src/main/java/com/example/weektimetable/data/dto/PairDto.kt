package com.example.weektimetable.data.dto

import com.google.gson.annotations.SerializedName

data class PairDto (
    @SerializedName("LessonType")
    val lessonType: String,
    @SerializedName("Proffessor")
    val professor: String,
    @SerializedName("Groups")
    val groups: List<GroupDto>,
    @SerializedName("Auditory")
    val auditory: Int
)