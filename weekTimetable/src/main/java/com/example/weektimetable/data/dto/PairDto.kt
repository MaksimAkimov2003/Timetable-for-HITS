package com.example.weektimetable.data.dto

data class PairDto (
    val lessonType: String,
    val professor: String,
    val groups: List<GroupDto>,
    val auditory: Int
)