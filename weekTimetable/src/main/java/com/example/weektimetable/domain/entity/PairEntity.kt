package com.example.weektimetable.domain.entity

data class PairEntity (
    val lessonType: String,
    val discipline: String,
    val professor: String,
    val groups: List<GroupEntity>,
    val auditory: String
)