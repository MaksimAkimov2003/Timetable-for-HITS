package com.example.weektimetable.domain.entity

data class DirectionEntity(
    val id: String,
    val name: String,
    val number: String,
    val faculty: FacultyEntity
)
