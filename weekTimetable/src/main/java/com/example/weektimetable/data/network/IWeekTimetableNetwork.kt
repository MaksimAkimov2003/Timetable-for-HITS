package com.example.weektimetable.data.network

import com.example.weektimetable.data.dto.WeekDto

interface IWeekTimetableNetwork {
    suspend fun getWeekTimetableByGroup(number: String): WeekDto
    suspend fun getWeekTimetableByAuditory(id: String): WeekDto
    suspend fun getWeekTimetableByTeacher(id: String): WeekDto
}